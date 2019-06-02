/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ambre.wiki.service;

import com.ambre.wiki.entities.User;
import com.ambre.wiki.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Administrateur
 */
@Service
public class UserService {
	
	@Autowired
	private UserRepository us;

	public User createUser(String lastName, String firstName, String emailAddress, String password, String user_role_id) {
		return us.createUser(lastName, firstName, emailAddress, password, user_role_id); 
	}

	public User getUserById(Long id) {
		return us.getUserById(id);
	}
 
	public User updateUser(Long id, String lastName, String firstName, String emailAddress, String image, String activity) {
		return us.updateUser(id, lastName, firstName, emailAddress, image, activity);
	}
	
	public User getUserByEmailAndPassword(String emailAddress, String password){
		return us.getUserByEmailAndPassword(emailAddress, password);
	}
        public User getUserByEmail(String emailAddress){
		return us.getUserByEmail(emailAddress);
	}

	public boolean updateUserPasswordById(Long id, String password) {
		return us.updateUserPasswordById(id, password);
	}
	
	public boolean updateUserPassword(User myUser, String password) {
		return us.updateUserPassword(myUser, password);
	}	
	
	
	public Iterable<User> findUsersByUserStatusIdAndKeyword(Long userRoleId, String keyword) {
		return us.findUsersByUserRoleIdAndKeyword(userRoleId, keyword);
	}
	
}
