package com.example.SpringJpaDemo.controller;

import java.beans.PropertyEditorSupport;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.SpringJpaDemo.dao.OrganizationDao;
import com.example.SpringJpaDemo.dao.PersonDao;
import com.example.SpringJpaDemo.model.Organization;
import com.example.SpringJpaDemo.model.Person;

@Controller
@RequestMapping("/person/")
public class PersonController {
	
	private static final Logger logger = LoggerFactory.getLogger(PersonController.class);

	@Autowired
	private PersonDao personDao;
	
	@Autowired private OrganizationDao orgDao;
	
	@RequestMapping(method=RequestMethod.GET,value="edit")
	public ModelAndView editPerson(@RequestParam(value="id",required=false) Long id) {		
		logger.debug("Received request to edit person id : "+id);				
		ModelAndView mav = new ModelAndView();		
 		mav.setViewName("edit");
 		Person person = null;
 		if (id == null) {
 			person = new Person();
 		} else {
 			person = personDao.find(id);
 		}
 		
 		List<Organization> orgs = orgDao.getOrganizations();
 		mav.addObject("organizations", orgs.toArray());
 		mav.addObject("person", person);
		return mav;
		
	}
	
	@RequestMapping(method=RequestMethod.POST,value="edit") 
	public String savePerson(@ModelAttribute Person person) {
		logger.debug("Received postback on person "+person);
		personDao.save(person);
		return "redirect:list";
	}
	
	@RequestMapping(method=RequestMethod.GET,value="list")
	public ModelAndView listPeople() {
		logger.debug("Received request to list persons");
		ModelAndView mav = new ModelAndView();
		List<Person> people = personDao.getPeople();
		logger.debug("Person Listing count = "+people.size());
		mav.addObject("people",people);
		mav.setViewName("list");
		return mav;
		
	}
	
	@InitBinder
	public void initBinder(ServletRequestDataBinder binder){
		binder.registerCustomEditor(Organization.class, "employer", new PropertyEditorSupport(){
			
			@Override
			public String getAsText() {
				String result = null;
				Object value = getValue();
				if(value == null){
					result = "";
				}else if(value instanceof Long){
					result = value.toString();
				}else if(value instanceof Organization){
					result = String.valueOf( ((Organization) value).getId() );
				}
				return result;
			}
			
			@Override
			public void setAsText(String text) throws IllegalArgumentException {
				if(text == null || text.trim().isEmpty()){
					setValue(null);
				}else{
					Long id = Long.valueOf(text);
					Organization org = orgDao.getById(id);
					setValue(org);
				}
			}
			
			@Override
			public Object getValue() {
				// TODO Auto-generated method stub
				Object o = super.getValue();
				return o;
			}
			
			@Override
			public Object getSource() {
				// TODO Auto-generated method stub
				return super.getSource();
			}
		
		});
		
	}

}
