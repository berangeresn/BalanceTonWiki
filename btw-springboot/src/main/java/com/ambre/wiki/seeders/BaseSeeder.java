package com.ambre.wiki.seeders;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

@Transactional
public class BaseSeeder {
	
    @Autowired
    EntityManagerFactory emf;
    
    @PersistenceContext
    EntityManager em;
    
}
