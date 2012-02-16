package com.example.SpringJpaDemo.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;


@Entity
public class Organization implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name = "PERSON_ORGANIZATION", 
		joinColumns = { @JoinColumn(name = "employer_id") }, 
		inverseJoinColumns = { @JoinColumn(name = "person_id") 
	})
	private Set<Person> employees = new HashSet<Person>();

	public Organization() {}
	
	public Organization(String name) {
		this();
		this.name = name;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public Set<Person> getEmployees() {
		return employees;
	}
	
}
