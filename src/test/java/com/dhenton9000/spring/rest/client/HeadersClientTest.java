/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dhenton9000.spring.rest.client;

import com.fasterxml.jackson.databind.JsonNode;
 
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.ext.Provider;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.glassfish.jersey.client.ClientConfig;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;

/**
 * This class demonstrates adding headers to a request. To see the added
 * headers set up tcp mon to act as a listener as instructed below.
 * 
 * @author dhenton
 */
public class HeadersClientTest {
    
     private static Logger LOG = LogManager.getLogger(HeadersClientTest.class);
    
    protected URI getBaseURI() {

        //used for tcpmon set Listen port to 5555, 
        //Act as listener hostname: localhost port 8080 (local springmvc-app)
        //click add
        //   return UriBuilder.fromUri("http://localhost:5555/app/backbone").build();
        return UriBuilder.fromUri("http://localhost:8080/app/backbone").build();
        // return UriBuilder.fromUri("http://donhenton-springmvc3.herokuapp.com/app/backbone").build();

    }
    /**
     * this demonstrates writing headers to the request
     * @throws java.io.IOException
     */
     @Test
    public void testConfigRequestFilter() throws IOException {
        ClientConfig config = new ClientConfig();
        config.register(RequestFilter.class);
        Client client = ClientBuilder.newClient(config);
        WebTarget target = client.target(getBaseURI());
        Response response = target.path("restaurant").request()
                .accept(MediaType.APPLICATION_JSON).get(Response.class);
        if (response.getStatus() != 200) {
            fail("status problem for request " + response.getStatus());
        }

        InputStream jsonStringResponse = response.readEntity(InputStream.class);
        
        ObjectMapper mapper = new ObjectMapper();
        JsonNode restaurants = mapper.readTree(jsonStringResponse);
        
        //restaurants.
        LOG.debug("restaurants: "+restaurants.toString()) ;
        assertTrue(restaurants.size() >5);
        assertTrue(RequestFilter.gotHit);
        RequestFilter.gotHit = false;

    }
    
    

    @Provider
    static class ResponseFilter implements ClientResponseFilter {

        public ResponseFilter() {
        }

        static boolean gotHit = false;

        @Override
        public void filter(ClientRequestContext requestContext,
                ClientResponseContext responseContext) throws IOException {
            LOG.debug("filter hit");
            gotHit = true;

        }
    }
    
     @Provider
    static class RequestFilter implements ClientRequestFilter {

        public RequestFilter() {
        }

        static boolean gotHit = false;

        @Override
        public void filter(ClientRequestContext requestContext) throws IOException {
             
            List<Object> items = new ArrayList<>();
            items.add("bonzo");
            requestContext.getHeaders().put("bonzo", items);
            gotHit = true;
            
        }

         
    }
    
    
    
}
