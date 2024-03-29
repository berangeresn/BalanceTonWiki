
package com.ambre.wiki.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table (name="news")
@SequenceGenerator(name="news_sequence_id", initialValue = 1, sequenceName = "news_sequence", allocationSize = 1)
public class News implements Serializable {

	private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "news_sequence_id")
    private Long id;
    
    @Column (name = "title", nullable = false)
    private String title;
    
    @Column (name = "created_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createdAt;

    /**
     * Define the default constructor 
     * (needed to declare an entity bean)
     */
    public News() {
    }

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

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * Get the serial version UID
	 * Generated by Eclipse (Getters and Setters)  
	 * @return
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
