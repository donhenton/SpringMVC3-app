/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dhenton9000.spring.websocket.handlers;
import java.security.Principal;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;
/**
 *
 * This handler assigned a user id if none found. Can be used for anonymous
 * access and user channels without logging in
 * 
 * @author dhenton
 */
public class TestHandshakeHandler extends DefaultHandshakeHandler {

    private static final Logger LOG = LoggerFactory.getLogger(TestHandshakeHandler.class);

    @Override
    protected Principal determineUser(ServerHttpRequest request, 
            WebSocketHandler wsHandler, Map<String, Object> attributes) {
        
        
        WebSocketHttpHeaders headers = new WebSocketHttpHeaders(request.getHeaders());
        
        for (String key:headers.keySet())
        {
            LOG.debug("header key "+key+" "+headers.get(key));
        }
        
        
        Principal originalPrincipal = request.getPrincipal();
        if (originalPrincipal != null)
        {
            LOG.debug("returning original principal "+originalPrincipal.getName());
           return originalPrincipal; 
        }
        else
        {
            LOG.debug("original principal null ");
        }
        final String principalName =  UUID.randomUUID().toString(); 
        LOG.debug("assigning principal name : "+principalName);
        
        Principal principal = new Principal() {

            @Override
            public String getName() {
              
              return principalName;
                
            } 
        };
        
        return principal;
    }
    
    
    
    
}
