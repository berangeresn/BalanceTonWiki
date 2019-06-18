
package com.ambre.wiki.entities;

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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="hashtag")
@SequenceGenerator(name="hashtag_sequence_id", initialValue = 1, sequenceName = "hashtag_sequence", allocationSize = 1)
public class Hashtag implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hashtag_sequence_id")
    private Long id;
    
    @Column (name = "title", nullable = false)
    private String title;
    
    @ManyToMany (targetEntity = Comment.class)
        @JoinTable(name="comment_hashtag",
            joinColumns={@JoinColumn(name="hashtag_id", referencedColumnName="id")},
            inverseJoinColumns={@JoinColumn(name="comment_id", referencedColumnName="id", unique = true)}
        )
	private Set<Comment> commentHashtag;

    public Hashtag() { }

    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Set<Comment> getCommentHashtag() {
		return commentHashtag;
	}

	public void setCommentHashtag(Set<Comment> commentHashtag) {
		this.commentHashtag = commentHashtag;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
    public String toString() {
        return "com.mycompany.wiki.entities.Hashtage[ id=" + id + " ]";
    }
    
}
