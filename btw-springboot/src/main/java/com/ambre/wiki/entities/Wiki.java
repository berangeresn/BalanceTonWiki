
package com.ambre.wiki.entities;

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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table (name="wiki")
@SequenceGenerator(name = "wiki_sequence", initialValue = 1,sequenceName = "wiki_sequence", allocationSize = 1)
public class Wiki implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "wiki_sequence")
    private Long id;

    @Column (name = "title", nullable = false)
    private String title;

    @Column (name = "content", nullable = false)
    private String content;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column (name = "created_at", nullable = false)
    private Date createdAt;
    
    @OneToMany (targetEntity = Likes.class, mappedBy = "likesWiki",cascade = {CascadeType.ALL})
    private Set<Likes> likes;
    

    @OneToMany (targetEntity = Dislikes.class, mappedBy = "dislikesWiki", cascade = {CascadeType.ALL})
    private Set<Dislikes> dislikes;

    @ManyToOne
    @JoinColumn (name="user_id", nullable=false)
    private User user; 
    
    @OneToMany (targetEntity = Comment.class, mappedBy = "wiki")
    private Set<Comment> comment;
    
    @ManyToMany (targetEntity = Category.class, cascade = CascadeType.ALL)
    @JoinTable(name="wikis_categories",
        joinColumns={@JoinColumn(name="wiki_id", referencedColumnName="id")},
        inverseJoinColumns={@JoinColumn(name="category_id", referencedColumnName="id")} 
        )
    private Set<Category> category;

    @ManyToOne (targetEntity = WikiStatus.class)
    @JoinColumn (name="wiki_status_id", nullable=true)
    private WikiStatus wikistatus;

    @ManyToMany(targetEntity = User.class, mappedBy = "corrections")
    private Set<User> correcteurs; 

    public Set<User> getCorrecteurs() {
        return correcteurs;
    }

    public void setCategories(Set<Category> categories) {
        this.category = categories;
    }

    public void setCorrecteurs(Set<User> correcteurs) {
        this.correcteurs = correcteurs;
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

   
    public void setWikistatus(WikiStatus wikistatus) {
        this.wikistatus = wikistatus;
    }

    public WikiStatus getWikistatus() {
        return wikistatus;
    }

    public User getUser() {
        return user;
    }

    public Set<Category> getCategories() {
        return category;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Date getCreated_at() {
        return createdAt;
    }
     public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreated_at(Date created_at) {
        this.createdAt = created_at;
    }
    
     public void setUser(User user) {
        this.user = user;
    }

     
    public void setComment(Set<Comment> comment) {
        this.comment = comment;
    }

    public Set<Comment> getComment() {
        return comment;
    }
 
}
