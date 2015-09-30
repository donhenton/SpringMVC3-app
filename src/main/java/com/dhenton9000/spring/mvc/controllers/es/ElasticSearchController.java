package com.dhenton9000.spring.mvc.controllers.es;

import com.dhenton9000.spring.mvc.controllers.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.apache.log4j.*;


/**
 * The controller for the elasticsearch pages
 * @author Don
 *
 */
@Controller
public class ElasticSearchController {
	
	
	
	private static Logger log = LogManager.getLogger(ElasticSearchController.class);
	
	
	@RequestMapping("/packtpub-es")
	public ModelAndView homePage() {
		String message = "Hello World, Spring 3.0!";
		return new ModelAndView("tiles.packtpub.es", "message", message);
	}
	
	 
        
        
}
