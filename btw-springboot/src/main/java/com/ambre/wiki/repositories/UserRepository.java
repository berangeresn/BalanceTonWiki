
package com.ambre.wiki.repositories;

import com.ambre.wiki.entities.User;
import com.ambre.wiki.entities.UserRole;
import com.ambre.wiki.helpers.EncryptPasswordHelper;
import com.ambre.wiki.repositories.BaseRepository;

import javax.persistence.Query;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class UserRepository extends BaseRepository {

	/**
	 * Get a user by id 
	 * @param id
	 * @return
	 */
	public User createUser(String lastName, String firstName, String emailAddress, String password, String user_role_id) {
		
	String passwordEncrypted = EncryptPasswordHelper.encryptedPassword(password);
		
        User myUser = new User();
        UserRole myUserRole = (UserRole)em.find(UserRole.class, Long.valueOf(user_role_id));
        myUser.setFirstName(firstName);
        myUser.setLastName(lastName);
        myUser.setEmailAddress(emailAddress);
        myUser.setPassword(passwordEncrypted);
        myUser.setUserRole(myUserRole);
        em.persist(myUser);
        em.flush();
        return myUser;
    }
    
	/**
	 * Get a user by id 
	 * @param id
	 * @return
	 */
	public User getUserById(Long id){        
        return (User) em.find(User.class, id); 
	}
    
	/**
	 * 
	 * @param emailAddress
         * @param password
	 * @return
	 */
       public User getUserByEmailAndPassword(String emailAddress, String password) {
       String passwordEncrypted = EncryptPasswordHelper.encryptedPassword(password);
       return (User) em.createQuery("SELECT u FROM "+User.class.getName()+" u WHERE email_address=:email AND password =:pass").setParameter("email", emailAddress).setParameter("pass", passwordEncrypted).getSingleResult(); 
	}
       
       public User getUserByEmail(String emailAddress) {     
       return (User) em.createQuery("SELECT u FROM "+User.class.getName()+" u WHERE email_address=:email").setParameter("email", emailAddress).getSingleResult(); 
	}
     
	/**
	 * Update a user
	 * @param id
	 * @param lastName
	 * @param firstName
	 * @param emailAddress
	 * @param image
	 * @param activity
	 * @return
	 */
	public User updateUser(Long id, String lastName, String firstName, String emailAddress, String image, String activity) {
		User myUser = null;
		if (id>0) {
			myUser = (User)em.find(User.class, id);
			if (myUser!=null) {
				myUser.setLastName(lastName);
				myUser.setFirstName(firstName);
				myUser.setEmailAddress(emailAddress);
				myUser.setImage(image);
				myUser.setActivity(activity);
				em.merge(myUser);
				em.flush();
			}
		}
		return myUser;
	}

        
   	/**
   	 * Update the password of a user by user id
   	 * @param user
   	 * @param password
   	 */
   	public boolean updateUserPasswordById(Long id, String password) {
   		if (id>0) {
   	   		User myUser = this.getUserById(id);
   	   		return this.updateUserPassword(myUser, password);
   		}
   		return false;
   	}  
   	
   	/**
   	 * Update the password of a user
   	 * @param myUser
   	 * @param password
   	 */
   	public boolean updateUserPassword(User myUser, String password) {
   		if (password.length()>0) {
   	   		if (myUser!=null) {
   	 			String passwordEncrypted = EncryptPasswordHelper.encryptedPassword(password);
   	 			myUser.setPassword(passwordEncrypted);
   	 	        em.persist(myUser);
   	 	        em.flush();
   	 	        return true;
   	   		}
		}
   		return false;
   	}

   	/**
   	 * Find users by userstatus if exists and by keyword if exists
   	 * @param userStatusId
   	 * @param pattern
   	 * @return
   	 */
   	public Iterable<User> findUsersByUserRoleIdAndKeyword(Long userRoleId, String keyword) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT u ")
		   .append(" FROM ").append(User.class.getName()).append(" u ")
		   .append(" WHERE (1=1) ");
		if (userRoleId!=null) {
   			if (userRoleId>0) {
			   sql.append(" AND (u.userRole.id = :userroleid) ");
			}
		}
		if (keyword!=null) {
   			if (keyword.length()>0) {
   				sql.append(" AND ( ");
   				sql.append("   (u.lastName LIKE :pattern) ");
   				sql.append("   OR (u.firstName LIKE :pattern) ");
   				sql.append("   OR (u.activity LIKE :pattern) ");
   				sql.append(" ) ");
			}
		}
		sql.append(" ORDER BY u.id ASC ");
		Query q = em.createQuery(sql.toString());
		if (userRoleId!=null) {
   			if (userRoleId>0) { 
   				q.setParameter("userroleid", userRoleId); 
   			}
		}
		if (keyword!=null) {
   			if (keyword.length()>0) { 
   				q.setParameter("pattern", "%" + keyword + "%"); 
   			}
		}
		return (Iterable<User>) q.getResultList();
   	}
   	
   	/*
    public ArrayList<Wiki> searchWiki(String keyword) { 
        String pattern = "%" + keyword + "%";
        Query q = em.createQuery("SELECT w FROM "+Wiki.class.getName()+" w LEFT JOIN "+User.class.getName()+""
                + " u ON w.user.id = u.id WHERE w.title LIKE :pattern OR w.content LIKE :pattern OR u.firstName LIKE :pattern OR u.lastName LIKE :pattern OR "
                + " (SELECT c FROM "+Category.class.getName()+" c WHERE c.category LIKE :pattern) MEMBER OF w.category").setParameter("pattern", pattern);
        
        return (ArrayList<Wiki>)q.getResultList();
    } 
    */  	
   	
   	
}

    
    