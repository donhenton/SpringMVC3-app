package com.dhenton9000.json;

import com.dhenton9000.restaurant.model.Restaurant;
import com.dhenton9000.restaurant.model.RestaurantDTO;
import com.dhenton9000.restaurant.model.ReviewDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
 

 


public class JacksonSampler {

	private static Logger log = LogManager.getLogger(JacksonSampler.class);
	public void doRead()
	{
		ObjectMapper mapper = new ObjectMapper();

		ReviewDTO r = new ReviewDTO();
		Writer w = new StringWriter();
		try {
			mapper.writeValue(w, r);
		} catch ( Exception e) {
			log.error(e.getClass().getName()+" "+e.getMessage());
			
		}  
		//helper.tearDown();
		log.info("\n\n"+w.toString());
	}
	
	
	//{"name":null,"id":null,"state":null,"zipCode":"zip","city":null,"idAsLong":null,"reviews":[],"version":1}
	public void doRestaurantTest()
	{
		ObjectMapper mapper = new ObjectMapper();
		Restaurant res = new Restaurant();
		res.setVersion(1);
		res.setZipCode("zip");
		RestaurantDTO rDTO = new RestaurantDTO(res);
		rDTO.setName("fred");
		Writer w = new StringWriter();
		try {
			mapper.writeValue(w, rDTO);
		} catch ( Exception e) {
			log.error(e.getClass().getName()+" "+e.getMessage());
			
		}  
		//helper.tearDown();
		log.info("\n\n"+w.toString());
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<Restaurant>> violations = validator.validate(res);
		if (violations.size() != 0) {
			for (ConstraintViolation<Restaurant> vv: violations)
			{
				log.error(vv.getMessage());
			}
			
		}
	}
	
	public static void main(String[] args) {
		
		JacksonSampler j = new JacksonSampler();
		j.doRestaurantTest();

	}

}
