/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dhenton9000.spring.websocket.events;

import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;  
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.web.socket.messaging.SessionConnectEvent;

/**
 * http://www.sergialmar.com/2014/03/detect-websocket-connects-and-disconnects-in-spring-4/
 *
 * @author dhenton
 */
public class StompConnectEvent implements ApplicationListener<SessionConnectEvent> {

    private final Logger logger = LoggerFactory.getLogger(StompConnectEvent.class);

    @Override
    public void onApplicationEvent(SessionConnectEvent event) {
        StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());
        MessageHeaders mH = sha.getMessageHeaders();
        Set<String> keySet = mH.keySet();
        for (String key : keySet) {
            logger.debug("key " + key + " value " + mH.get(key).toString());
        }
        logger.debug("Connect event sessionId: [" + sha.getSessionId() + "]");
    }
}
