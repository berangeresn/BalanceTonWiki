
package com.ambre.wiki.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;


@Entity
@SequenceGenerator(name="dislikes_sequence_id", initialValue = 1, sequenceName = "dislikes_sequence", allocationSize = 1)
public class Dislikes implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dislikes_sequence_id")
    private Long id;   
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn (name="wiki_id", nullable=true)
    private Wiki dislikesWiki;
    
    @JsonIgnore
    @ManyToOne 
    @JoinColumn (name="comment_id", nullable=true)
    private Comment dislikesComment;
    
    
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

    public Wiki getDislikesWiki() {
        return dislikesWiki;
    }

    public void setDislikesWiki(Wiki dislikesWiki) {
        this.dislikesWiki = dislikesWiki;
    }

    public Comment getDislikesComment() {
        return dislikesComment;
    }

    public void setDislikesComment(Comment dislikesComment) {
        this.dislikesComment = dislikesComment;
    }



     
}
