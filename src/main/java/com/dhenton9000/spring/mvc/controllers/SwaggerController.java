/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dhenton9000.spring.mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author dhenton
 */
@Controller
@RequestMapping("/swagger/*")
public class SwaggerController {
    
    @RequestMapping(value="/sdoc", method=RequestMethod.GET)
	public void usingRequestToSwagger(Model model) {
		model.addAttribute("foo", "bar");
		model.addAttribute("fruit", "apple");
		 
		 
	}

    
}
