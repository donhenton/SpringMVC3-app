package com.dhenton9000.json;

import com.dhenton9000.restaurant.model.Restaurant;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.MapperFeature;
 
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.StringWriter;
import java.io.Writer;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
 

public class JacksonDemo {

	private static Logger log = LogManager.getLogger(JacksonDemo.class);

	public void demonstrateShowingNulls() {
		ObjectMapper mapper = new ObjectMapper();
		Restaurant res = new Restaurant();
		res.setVersion(1);
		res.setZipCode("zip");

		Writer w = new StringWriter();
		try {
			mapper.writeValue(w, res);
		} catch (Exception e) {
			log.error(e.getClass().getName() + " " + e.getMessage());

		}
		// helper.tearDown();
		log.info("\n\n" + w.toString());
	}

	public void demonstrateNotShowingNulls() {
		ObjectMapper mapper = new ObjectMapper();
		 
		//
		mapper.getSerializationConfig().withSerializationInclusion(
				Include.NON_NULL);
             
		//mapper.configure( ff., false);
		// this is older style see demonstrateNotShowingNulls2 for newer

		Restaurant res = new Restaurant();
		res.setVersion(1);
		res.setZipCode("zip");

		Writer w = new StringWriter();
		try {
			mapper.writeValue(w, res);
		} catch (Exception e) {
			log.error(e.getClass().getName() + " " + e.getMessage());

		}
		// helper.tearDown();
		log.info("\n\n" + w.toString());
	}

	public void demonstrateNotShowingNulls2() {
		ObjectMapper mapper = new ObjectMapper();
		 
                
		mapper.getSerializationConfig().withSerializationInclusion(
				Include.NON_NULL);

		Restaurant res = new Restaurant();
		res.setVersion(1);
		res.setZipCode("zip");

		Writer w = new StringWriter();
		try {
			mapper.writeValue(w, res);
		} catch (Exception e) {
			log.error(e.getClass().getName() + " " + e.getMessage());

		}
		// helper.tearDown();
		log.info("\n\n" + w.toString());
	}

	public static void main(String[] args) {

		JacksonDemo j = new JacksonDemo();
		j.demonstrateShowingNulls();
		j.demonstrateNotShowingNulls();
		j.demonstrateNotShowingNulls2();

	}

}
