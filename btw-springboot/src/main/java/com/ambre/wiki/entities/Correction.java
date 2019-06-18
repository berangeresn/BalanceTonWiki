
package com.ambre.wiki.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table (name = "correction")
@SequenceGenerator(name="corrction_sequence_id", initialValue = 1, sequenceName = "correction_sequence",  allocationSize = 1)
public class Correction implements Serializable {

    private static final long serialVersionUID = 1L;
   
        @Id
        @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "corrction_sequence_id")
        private Long id;
   
        @CreationTimestamp
        @Temporal(TemporalType.TIMESTAMP)
        @Column (name = "corrected_at", nullable = false)
        private Date date;
         
        @ManyToOne
        @JoinColumn
        private Wiki wiki;
        
        @ManyToMany(targetEntity = User.class, mappedBy = "corrections")
        private Set<User> correcteurs; 
   
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Wiki getWiki() {
        return wiki;
    }

    public void setWiki(Wiki wiki) {
        this.wiki = wiki;
    }



    public Set<User> getCorrecteurs() {
        return correcteurs;
    }

    public void setCorrecteurs(Set<User> correcteurs) {
        this.correcteurs = correcteurs;
    }

     
}
