package com.dhenton9000.spring.mvc.controllers.d3;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value = "/d3/*")

public class D3Controller {

	@RequestMapping("/treeDemo")
	public ModelAndView gotoTreeDemo() {

		return new ModelAndView("tiles.d3.demos.treedemo");
	}
        
        @RequestMapping("/graphUpdates")
	public ModelAndView gotoGraphUpdates() {

		return new ModelAndView("tiles.d3.demos.graphupdates");
	}
	
	@RequestMapping("/dynamicGraph")
	public ModelAndView gotoDynamicGraph() {

		return new ModelAndView("tiles.d3.demos.dynamicgraph");
	}
        @RequestMapping("/fade")
	public ModelAndView gotoFadeGraph() {

		return new ModelAndView("tiles.d3.demos.fade");
	}
        
         @RequestMapping("/d3labels")
	public ModelAndView gotoD3Labels() {

		return new ModelAndView("tiles.d3.demos.d3labels");
	}
}
