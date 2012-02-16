package com.example.SpringJpaDemo.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.example.SpringJpaDemo.dao.OrganizationDao;
import com.example.SpringJpaDemo.dao.PersonDao;
import com.example.SpringJpaDemo.model.Organization;
import com.example.SpringJpaDemo.model.Person;

@Configuration
public class DataPopulator {

	@Autowired
	private OrganizationDao orgDao;
	
	@Autowired
	private PersonDao personDao;
	
	@PostConstruct
	public void onAppStart(){
		for(String name : new String[]{"USPS", "UPS", "FedEx"}){
			orgDao.save(new Organization(name));
		}
		
		personDao.save(new Person("Jimbo", "Jones"));
		personDao.save(new Person("Franky", "Figgs"));
	}
}
