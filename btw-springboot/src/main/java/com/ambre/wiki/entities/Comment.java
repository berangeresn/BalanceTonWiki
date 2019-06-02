
package com.ambre.wiki.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;


@Entity
@Table (name="comment")
@SequenceGenerator(name="Comment_sequence_id", initialValue = 1, sequenceName = "comment_sequence", allocationSize = 1)
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;
    
        @Id
        @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "Comment_sequence_id")
        private Long id;
        
        @Column (name = "content", nullable = false)
        private String content;
        
        
        @CreationTimestamp
        @Temporal(TemporalType.TIMESTAMP)
        @Column (name = "created_at", nullable = false)
        private Date createdAt;
        
        @JsonIgnore
        @ManyToOne 
        @JoinColumn (name="wiki_id", nullable=false)
        private Wiki wiki; 
        
        @JsonIgnore
        @ManyToOne 
        @JoinColumn (name="user_id", nullable=false)
        private User user;
        
        
        @OneToMany (targetEntity = Likes.class, mappedBy = "likesComment", cascade = CascadeType.ALL)
        private Set<Likes> likes;

        
        @OneToMany (targetEntity = Dislikes.class, mappedBy = "dislikesComment", cascade = CascadeType.ALL)
        private Set<Dislikes> dislikes;
        
        
        @OneToMany (targetEntity = Comment.class,cascade = CascadeType.PERSIST)
        @JoinColumn (name="comment_id", nullable=true)
        private Set<Comment> comment;
        
        
        @ManyToMany(targetEntity = Hashtag.class, mappedBy = "comment_hashtag")
        private Set<User> hashtags;

    public Set<User> getHashtags() {
        return hashtags;
    }

    public void setHashtags(Set<User> hashtags) {
        this.hashtags = hashtags;
    }
    
    
    public void setId(Long id) {
        this.id = id;
    }
    

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreated_at(Date created_at) {
        this.createdAt = created_at;
    }

    public void setWiki(Wiki wiki) {
        this.wiki = wiki;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

   public Date getCreated_at() {
        return createdAt;
    }

    public Wiki getWiki() {
        return wiki;
    }
    
     public void setUser(User user) {
        this.user = user;
    }
     
     public User getUser() {
        return user;
    }
     
     public Set<Comment> getComment() {
        return comment;
    }
      
     public void setComment(Set<Comment> Comment) {
        this.comment = Comment;
    }

    public Set<Likes> getLikes() {
        return likes;
    }

    public void setLikes(Set<Likes> likes) {
        this.likes = likes;
    }

    public Set<Dislikes> getDislikes() {
        return dislikes;
    }

    public void setDislikes(Set<Dislikes> dislikes) {
        this.dislikes = dislikes;
    }


      
}
