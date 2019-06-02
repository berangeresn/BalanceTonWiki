package com.ambre.wiki.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ambre.wiki.entities.User;
import com.ambre.wiki.entities.UserRole;
import com.ambre.wiki.repositories.UserRoleRepository;

@Service
public class UserRoleService {

	@Autowired
	private UserRoleRepository myRepository;
	
	/**
	 * Find a user status by id
	 * @param id
	 * @return
	 */
	public UserRole findUserRoleById(Long id) {
		return myRepository.findUserRoleById(id);
	}

	/**
	 * Read all the user status
	 * @return
	 */
	public Iterable<UserRole> findAllUserRole() {
		return myRepository.findAllUserRole();
	}
	
	/**
	 * Add a user status
	 * @param status
	 * @return
	 */
	public UserRole addUserRole(String role) {
		return myRepository.addUserRole(role);
	}
	
	/**
	 * Update a user status
	 * @param id
	 * @param status
	 * @return
	 */
	public UserRole updateUserRole(Long id, String role) {
		return myRepository.updateUserRole(id, role);
	}	
	
	
	/**
	 * 
	 * @param myUser
	 * @param statusId
	 * @return
	 */
	public boolean updateRoleOfUser(User myUser, Long roleId) {
		return myRepository.updateRoleOfUser(myUser, roleId);
	}

	
}
