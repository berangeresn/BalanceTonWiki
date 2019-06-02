
package com.ambre.wiki.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient; 

@Entity
@Table (name = "users")
@SequenceGenerator(name="users_sequence_id", initialValue = 1, sequenceName = "user_sequence",  allocationSize = 1)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
   
        @Id
        @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "users_sequence_id")
        private Long id;
   
        @Column (name = "last_name", nullable = false)
        private String lastName;

        @Column (name = "first_name", nullable = false)
        private String firstName;
   
        @Column (name = "email_address", nullable = false)
        private String emailAddress;
    
        @Column (name = "password", nullable = false)
        private String password;
      
    
        @Column (name = "image", nullable = true)
        private String image;
       
    
        @Column (name = "activity", nullable = true)
        private String activity;
    
        @JsonIgnore
        @OneToMany (targetEntity = Wiki.class,mappedBy = "user")        
        private Set<Wiki> wiki; 
        
        @JsonIgnore
        @OneToMany (targetEntity = Comment.class, mappedBy = "user")        
        private Set<Comment> comment;
        
       
    
        @ManyToOne (targetEntity = UserRole.class)
        @JoinColumn (name="user_role_id", nullable=true)
        private UserRole userRole;
        
        @ManyToMany (targetEntity = Wiki.class)
        @JoinTable(name="wiki_correction",
            joinColumns={@JoinColumn(name="correcteur_id", referencedColumnName="id")},
            inverseJoinColumns={@JoinColumn(name="wiki_id", referencedColumnName="id", unique = true)}
        )
        private Set<Wiki> corrections;
        
        @Transient
        String Token; 

    public Set<Wiki> getCorrections() {
        return corrections;
    }

    public void setCorrections(Set<Wiki> corrections) {
        this.corrections = corrections;
    }
     
        
    public String getImage() {
        return image;
    }

    public String getActivity() {
        return activity;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }
        
    public void setToken(String Token) {
        this.Token = Token;
    }

    public String getToken() {
        return Token;
    }
        
    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public UserRole getUserRole() {
        return userRole;
    }
   
    public Long getId() {
        return id;
    }

    

    public String getPassword() {
        return password;
    }

    public void setId(Long id) {
        this.id = id;
    }

   
    public void setPassword(String password) {
        this.password = password;
    }
    
      public void setComment(Set<Comment> comment) {
        this.comment = comment;
    }

    public Set<Comment> getComment() {
        return comment;
    }
     public Set<Wiki> getWiki() {
        return wiki;
    }
    public void setWiki(Set<Wiki> Wiki) {
        this.wiki = Wiki;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    
             
}
