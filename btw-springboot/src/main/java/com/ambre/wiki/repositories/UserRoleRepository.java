
package com.ambre.wiki.repositories;

import com.ambre.wiki.entities.UserRole;
import com.ambre.wiki.constants.UserStatusConstants;
import com.ambre.wiki.entities.User;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class UserRoleRepository extends BaseRepository {

	/**
	 * Add a user status
	 * @param Status
	 * @return
	 */
    public UserRole addUserRole(String role) {
    	if ((role==null) || (role.isEmpty())) { return null; }
		UserRole urole = new UserRole();   
		urole.setRole(role);
		em.persist(urole);
		return urole;
    }
    
    /**
     * 
     * @param id
     * @param status
     * @return
     */
    public UserRole updateUserRole(Long id, String role) {
    	if ((role==null) || (role.isEmpty())) {	return null; }
    	UserRole myUserRole = this.findUserRoleById(id);
        if (myUserRole!=null) {
    		myUserRole.setRole(role);
            em.persist(myUserRole);
        }
        return myUserRole; 
    }
    
    
    /**
     * Read a user status by id
     * @param id
     * @return
     */
    public UserRole findUserRoleById(Long id) {
    	if (id==null) { return null; }
    	if (id<=0) { return null; }
    	return (UserRole) em.find(UserRole.class, id);
    }    
    
    /**
     * Read all the user status
     * @return
     */
	public Iterable<UserRole> findAllUserRole() {
		StringBuilder from = new StringBuilder();
		TypedQuery<UserRole> lQuery = em.createQuery(from.append("from ").append(UserRole.class.getName()).toString(), UserRole.class);
		return (Iterable<UserRole>) lQuery.getResultList();
	}
    
	/**
	 * Update the status of a user
	 * @param myUser
	 * @param statusId
	 * @return
	 */
	public boolean updateRoleOfUser(User myUser, Long roleId) {
    	boolean updated = false;
    	if ((myUser!=null) && (roleId!=null)) {
			if ((roleId>0) && (roleId<=UserStatusConstants.USER_STATUS_MAX_ID)) {
				UserRole myUserRole = this.findUserRoleById(roleId);
				if (myUserRole!=null) {
					myUser.setUserRole(myUserRole);
					em.persist(myUser);
					em.flush();
					updated = true;
				}
			}
		}    	
		return updated;
	}
	   
}
