package com.example.SpringJpaDemo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.SpringJpaDemo.model.Organization;

@Repository
public class OrganizationDao {

	private static final String SELECT_ALL_ORGS = "select o from Organization o";
	
	@PersistenceContext
	private EntityManager em;
	
	public List<Organization> getOrganizations(){
		return em.createQuery(SELECT_ALL_ORGS, Organization.class)
				.getResultList();
	}
	
	@Transactional
	public Organization save(Organization org){
		if(org.getId() == null){
			em.persist(org);
		}else{
			org = em.merge(org);
		}
		em.flush();
		return org;
	}

	public Organization getById(Long id) {
		return em.find(Organization.class, id);
	}

}
