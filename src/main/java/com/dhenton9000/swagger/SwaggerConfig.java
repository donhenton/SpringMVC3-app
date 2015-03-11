/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dhenton9000.swagger;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author dhenton
 */

@Configuration
@EnableSwagger //Loads the spring beans required by the framework
public class SwaggerConfig {
    
    
    private SpringSwaggerConfig springSwaggerConfig;

   /**
    * Required to autowire SpringSwaggerConfig
     * @param springSwaggerConfig
    */
   @Autowired
   public void setSpringSwaggerConfig(SpringSwaggerConfig springSwaggerConfig) {
        
      this.springSwaggerConfig = springSwaggerConfig;
   }

   /**
    * Every SwaggerSpringMvcPlugin bean is picked up by the swagger-mvc 
    * framework - allowing for multiple
    * swagger groups i.e. same code base multiple swagger resource listings.
    * can be comma separated list ".restau*","alpha" ...
     * @return 
    */
   @Bean
   public SwaggerSpringMvcPlugin customImplementation(){
      
       
       String title = "Restaurant Swagger Api";
       String description ="Full API for Maintaining Restaurants";
       String termsOfServiceUrl = null;
       String contact = "donhenton@gmail.com"; 
       String license = "MIT Full" ; 
       String licenseUrl ="http://en.wikipedia.org/wiki/MIT_License" ;
       
       
       ApiInfo info = new ApiInfo(  title,  description,   termsOfServiceUrl, contact, license,  licenseUrl) ;
       
       
       return new SwaggerSpringMvcPlugin(this.springSwaggerConfig)
              .includePatterns(".*restaurant.*").apiVersion("1.0-alpha").apiInfo(info);
   }

    
    
    
}
//http://localhost:8080/app/swagger/sdoc

//http://localhost:8080/app/api-docs