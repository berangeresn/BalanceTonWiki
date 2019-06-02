
package com.ambre.wiki.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table (name="category")
@SequenceGenerator(name="Category_sequence_id", initialValue = 1, sequenceName = "category_sequence", allocationSize = 1)
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Category_sequence_id")
    private Long id;
    
    @Column (name = "category", nullable = false)
    private String category;
    
    
    @JsonIgnore
    @ManyToMany(targetEntity = Wiki.class, mappedBy = "category")
    private Set<Wiki> wikis; 

    
    public Set<Wiki> getWikis() {
        return wikis;
    }

    public void setWikis(Set<Wiki> wikis) {
        this.wikis = wikis;
    }
     
    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Category)) {
            return false;
        }
        Category other = (Category) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.wiki.entities.Category[ id=" + id + " ]";
    }
    
}
