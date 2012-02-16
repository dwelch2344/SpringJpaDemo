package com.example.SpringJpaDemo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.SpringJpaDemo.dao.OrganizationDao;
import com.example.SpringJpaDemo.model.Organization;

@Controller
@RequestMapping("/org/")
public class OrganizationController {
	
	private static final Logger logger = LoggerFactory.getLogger(OrganizationController.class);

	@Autowired private OrganizationDao orgDao;
	
	@RequestMapping(method=RequestMethod.GET,value="{id}")
	public ModelAndView listPeople(@PathVariable Long id) {
		logger.debug("Received request to list persons by org id: " + id);
		ModelAndView mav = new ModelAndView("org");
		Organization org = orgDao.getById(id);
		logger.debug("Loaded Org : " + org.getName());
		logger.debug("Employees : " + org.getEmployees().size());
		mav.addObject("org", org);
		return mav;
		
	}
	
}
