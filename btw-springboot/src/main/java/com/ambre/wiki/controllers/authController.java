/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ambre.wiki.controllers;


import com.ambre.wiki.entities.User;
import com.ambre.wiki.helpers.JwtManager;
import com.ambre.wiki.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/access")
public class authController {
    @Autowired
    UserService userService;
    
    @RequestMapping(value="/login", method = RequestMethod.POST)
    public ResponseEntity<User> createWiki(@RequestParam String email, 
                                           @RequestParam String password)
    {    
        User user = userService.getUserByEmailAndPassword(email, password);
        JwtManager jwt = new JwtManager();
        if(user == null){            
            return new ResponseEntity<User>(user,HttpStatus.UNAUTHORIZED);
        }
        user.setToken(jwt.createToken(email, password));
        return new ResponseEntity<User>(user,HttpStatus.OK);
    }
    
     @RequestMapping(value="/register", method = RequestMethod.POST)
    public ResponseEntity<User>createUser(@RequestParam String lastName, @RequestParam String firstName, @RequestParam String emailAddress, @RequestParam String password, @RequestParam String user_role) {
    return new ResponseEntity<User>(userService.createUser(lastName, firstName, emailAddress, password, user_role), HttpStatus.OK); 
    }
}
