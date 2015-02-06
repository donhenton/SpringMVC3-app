/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dhenton9000.spring.mvc.controllers.angular;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


/**
 * 
 * @author dhenton
 */
@Controller
@RequestMapping(value = "/angular/*")
public class AngularController {

	 

	@RequestMapping("/restaurant")
	public ModelAndView gotoJasonDemo() {

		return new ModelAndView("tiles.angular.restaurant");
	}

	 
}
