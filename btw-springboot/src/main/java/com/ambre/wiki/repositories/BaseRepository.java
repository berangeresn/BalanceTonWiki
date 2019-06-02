
package com.ambre.wiki.repositories;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseRepository {
    
    @Autowired
    EntityManagerFactory emf;
    @PersistenceContext
    EntityManager em;
}
