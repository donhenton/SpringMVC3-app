/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dhenton9000.spring.websocket.events;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

/**
 *
 * @author dhenton
 */
public class StompDisconnectEvent implements ApplicationListener<SessionDisconnectEvent> {
 
    private final static Logger LOG = LoggerFactory.getLogger(StompDisconnectEvent.class);

    @Override
    public void onApplicationEvent(SessionDisconnectEvent sde) {
        
         LOG.debug("close for session "+sde.getSessionId()+ " ["+sde.getCloseStatus().toString()+"]");
    }
    
}
