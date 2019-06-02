
package com.ambre.wiki.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;


@Entity
@SequenceGenerator(name="likes_sequence_id", initialValue = 1, sequenceName = "likes_sequence", allocationSize = 1)
public class Likes implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "likes_sequence_id")
    private Long id;
    
    @JsonIgnore   
    @ManyToOne 
    @JoinColumn (name="wiki_id")
    private Wiki likesWiki;
    
    @JsonIgnore  
    @ManyToOne 
    @JoinColumn (name="comment_id")
    private Comment likesComment;
    
    
    @OneToOne (targetEntity = User.class)
    private User user; 

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Wiki getLikesWiki() {
        return likesWiki;
    }

    public void setLikesWiki(Wiki likesWiki) {
        this.likesWiki = likesWiki;
    }

    public Comment getLikesComment() {
        return likesComment;
    }

    public void setLikesComment(Comment likesComment) {
        this.likesComment = likesComment;
    }


    
}
