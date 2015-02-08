/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dhenton9000.spring.mvc.controllers.websocket;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import javax.security.Principal;
import java.security.Principal;

/**
 *
 * @author dhenton
 */
@Controller
public class ChatController {

    private static final Logger LOG = LoggerFactory.getLogger(ChatController.class);
    @Autowired
    private SimpMessagingTemplate template;
    private final SimpleDateFormat sdf = new SimpleDateFormat("MM/DD/YY hh:mm:ss");

    

    @MessageMapping("/bonzoAction")
    public void doBonzoAction(ChatMessage message, Principal principal) {

        LOG.debug("doing Bozo Action " + principal.getName());
        LOG.debug("Message " + message);
        String time;

        time = sdf.format(new Date());
        String currentMessage = message.getMessage();
        message.setMessage(String.format("Bonzo '%s' (%s)", currentMessage, time));
        template.convertAndSend("/topic/bonzo", message);
        String u = UUID.randomUUID().toString();
        message.setMessage(String.format("Frodo '%s'", u));
        template.convertAndSend("/queue/frodo", message);
        //template.convertAndSendToUser(u, time, u);

    }

    @MessageMapping("/userAction")
    //  @SendTo("/queue/userChannel")
    public void doUserAction(ChatMessage message, Principal principal) {

       // Map<String, Object> map = new HashMap<>();
        // map.put(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON);
        String userName = principal.getName();

        String info = sdf.format(new Date()) + " user [" + userName+"] payload "+ UUID.randomUUID().toString();

        ChatMessage m = new ChatMessage();
        m.setMessage(info);

        template.convertAndSendToUser(userName, "/queue/userChannel", m);
        //  return principal.getName() + " snark";
    }
}


//https://github.com/spring-projects/spring-framework/tree/master/spring-websocket
