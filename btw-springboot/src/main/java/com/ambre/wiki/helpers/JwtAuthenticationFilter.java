/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ambre.wiki.helpers;

import com.ambre.wiki.entities.User;
import com.ambre.wiki.service.UserService;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpResponse;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.security.auth.login.FailedLoginException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

/**
 *
 * @author Administrateur
 */
@WebFilter(filterName = "JwtAuthenticationFilter", urlPatterns = {"/v1/*"})
public class JwtAuthenticationFilter implements Filter {
    
    private static final java.util.logging.Logger LOG = Logger.getLogger( JwtAuthenticationFilter.class.getName() );

    private static final String AUTH_HEADER_KEY = "Authorization";
    private static final String AUTH_HEADER_VALUE_PREFIX = "Bearer "; // with trailing space to separate token

    private static final int STATUS_CODE_UNAUTHORIZED = 401;
    private static final int STATUS_CODE_FORBIDDEN = 403;
    
    private static final JwtManager TOKEN_MANAGER = new JwtManager();
    @Autowired
    UserService userService;

    @Override
    public void init( FilterConfig filterConfig ) throws ServletException {
        LOG.info( "JwtAuthenticationFilter initialized" );
    }

    @Override
    public void doFilter( final ServletRequest servletRequest,
                          final ServletResponse servletResponse,
                          final FilterChain filterChain ) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest; 
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        setAccessControlHeaders((HttpServletResponse)servletResponse);        
        boolean loggedIn = false;        
        try {

            String jwt = getBearerToken( httpRequest );
            
         
            if ( jwt != null && !jwt.isEmpty() ) {
                User myUser = userService.getUserByEmail((String)TOKEN_MANAGER.parseToken(jwt).getBody().get("sub"));
                    if(myUser!=null){
                    loggedIn = true;
//                    LOG.info( "Logged in using JWT" );
                    String URI = httpRequest.getRequestURI();
                    
                    boolean valide = false; 
                    
                    
                        for (String url : myUser.getUserRole().getUrls()) {
                            if (URI.contains(url)) {
                                valide = true;
                             }
                        } if (valide) {
                            servletRequest.setAttribute("user", myUser);
                            filterChain.doFilter( servletRequest, servletResponse );
                        }else {
                            httpResponse.setContentLength( 0 );
                            httpResponse.setStatus(STATUS_CODE_FORBIDDEN);
                        }
                }else{
                    throw new FailedLoginException();
                }                                
            } else {
                LOG.info( "No JWT provided, go on unauthenticated" );
            }            

            if ( loggedIn ) {
                httpRequest.logout();
                LOG.info( "Logged out" );
            }
        } catch ( final Exception e ) {
            LOG.log( Level.WARNING, "Failed logging in with security token", e );
            
            httpResponse.setContentLength( 0 );
            httpResponse.setStatus( STATUS_CODE_UNAUTHORIZED );
        }
        
        
 
    }

    @Override
    public void destroy() {
        LOG.info( "JwtAuthenticationFilter destroyed" );
    }

    /**
     * Get the bearer token from the HTTP request.
     * The token is in the HTTP request "Authorization" header in the form of: "Bearer [token]"
     */
    private String getBearerToken( HttpServletRequest request ) {
        String authHeader = request.getHeader( AUTH_HEADER_KEY );
        if ( authHeader != null && authHeader.startsWith( AUTH_HEADER_VALUE_PREFIX ) ) {
            return authHeader.substring( AUTH_HEADER_VALUE_PREFIX.length() );
        }
        return null;
    }
    
    private void setAccessControlHeaders(HttpServletResponse resp) {
      resp.setHeader("Access-Control-Allow-Origin", "http://localhost");
      resp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
    }
    
}
