package com.example.SpringJpaDemo.config;

import java.util.HashSet;
import java.util.Set;

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
		
		
		Set<Organization> orgs = new HashSet<Organization>(orgDao.getOrganizations());
		if(orgs.isEmpty()){
			for(String name : new String[]{"USPS", "UPS", "FedEx"}){
				Organization org = new Organization(name);
				org = orgDao.save(org);
				orgs.add(org);
			}
		}
		
		if(personDao.getPeople().isEmpty()){
			{
				Person person = new Person("Jimbo", "Jones");
				person.setEmployers(orgs);
				personDao.save(person);
			}
			
			{
				Person person = new Person("Franky", "Figgs");
				person.setEmployers(orgs);
				personDao.save(person);
			}
		}
	}
}
