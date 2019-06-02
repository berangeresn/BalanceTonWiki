package com.ambre.wiki.controllers;

import com.ambre.wiki.entities.User;
import com.ambre.wiki.entities.UserRole;
import com.ambre.wiki.service.UserService;
import com.ambre.wiki.service.UserRoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userrole")
public class UserRoleController {

	@Autowired
	private UserRoleService myService;
	
	@Autowired
	private UserService myUserService;
	

	@GetMapping(path="/{id}")
	public @ResponseBody UserRole findUserRoleById(@PathVariable("id") Long userRoleId) {
		UserRole myUserStatus = myService.findUserRoleById(userRoleId);
		return myUserStatus;
	}
	
	@GetMapping(path="/all")
	public @ResponseBody Iterable<UserRole> findAllUserRole() {
		return myService.findAllUserRole();
	}
	
	@PutMapping(path="/{id}/user/{userid}")
	public @ResponseBody boolean updateRoleOfUser(@PathVariable("id") Long userRoleId, @PathVariable("userid") Long userId) {
		User myUser = myUserService.getUserById(userId);
		return myService.updateRoleOfUser(myUser, userRoleId);
	}
	
	@PostMapping(path="/add")
	public @ResponseBody UserRole addUserRole (@RequestParam String role) {
		return myService.addUserRole(role);
	}
	
	@PutMapping(path="/update/{id}")
	public @ResponseBody UserRole updateUserRole(@PathVariable("id") Long userRoleId, @RequestParam String role) {
		return myService.updateUserRole(userRoleId, role);
	}
	
}
