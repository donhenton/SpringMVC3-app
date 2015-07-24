package com.dhenton9000.spring.mvc.controllers.testreporting;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

 
@Controller
@RequestMapping(value = "/testreporting/*")

public class TestReportingController {

	@RequestMapping("JSONGoldFiles")
	public ModelAndView gotoJSONGold() {

		return new ModelAndView("tiles.testreporting.jsoncomparisons");
	}
	
	@RequestMapping("ImageTestComparison")
	public ModelAndView gotoImageComparison() {

		return new ModelAndView("tiles.testreporting.imagecomparisons");
	}
         
}
