/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dhenton9000.spring.websocket.events;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.StompSubProtocolHandler;

/**
 *
 * @author dhenton
 */
public class StompConnectedEvent implements ApplicationListener<SessionConnectedEvent> {
 
    private final static Logger LOG = LoggerFactory.getLogger(StompConnectedEvent.class);

    @Override
    public void onApplicationEvent(SessionConnectedEvent sce) {
        
        LOG.debug("Session Connected "+sce);
        LOG.debug("source "+sce.getSource().getClass().getName());
        
       // StompSubProtocolHandler source = (StompSubProtocolHandler) sce.getSource();
        
    }
}
