/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ambre.wiki.helpers;

import com.ambre.wiki.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.impl.crypto.MacProvider;

import javax.crypto.SecretKey;
import javax.enterprise.context.ApplicationScoped;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.TemporalAmount;
import java.util.Date;

/**
 *
 * @author Administrateur
 */
@ApplicationScoped
public class JwtManager {
    private static final String CLAIM_ROLE = "role";

    private static final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;
    private static final SecretKey SECRET_KEY = MacProvider.generateKey( SIGNATURE_ALGORITHM );
    private static final TemporalAmount TOKEN_VALIDITY = Duration.ofHours( 4L );

    /**
     * Builds a JWT with the given subject and role and returns it as a JWS signed compact String.
     */
    public String createToken( final String email, final String role ) {
        final Instant now = Instant.now();
        final Date expiryDate = Date.from( now.plus( TOKEN_VALIDITY ) );
        return Jwts.builder()
                   .setSubject( email )
                   .claim( CLAIM_ROLE, role )
                   .setExpiration( expiryDate )
                   .setIssuedAt( Date.from( now ) )
                   .signWith( SIGNATURE_ALGORITHM, SECRET_KEY )
                   .compact();
    }

    /**
     * Parses the given JWS signed compact JWT, returning the claims.
     * If this method returns without throwing an exception, the token can be trusted.
     */
    public Jws<Claims> parseToken( final String compactToken )
            throws ExpiredJwtException,
                   UnsupportedJwtException,
                   MalformedJwtException,
                   SignatureException,
                   IllegalArgumentException {
        return Jwts.parser()
                   .setSigningKey( SECRET_KEY )
                   .parseClaimsJws( compactToken );
    }
}
