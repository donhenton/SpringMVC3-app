/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dhenton9000.spring.mvc.controllers.websocket;

import java.util.Random;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author dhenton
 */
@Controller
@RequestMapping(value = "/websocket/*")
public class WebsocketController {

    @Autowired
    private SimpMessagingTemplate template;
    private TaskScheduler scheduler = new ConcurrentTaskScheduler();
    private static final Logger LOG = LoggerFactory.getLogger(WebsocketController.class);
    
    
    @RequestMapping("/graph")
    public ModelAndView gotoGraphUpdate() {

        return new ModelAndView("tiles.websocket.graph");
    }

    @RequestMapping("/chat")
    public ModelAndView gotoChat() {

        return new ModelAndView("tiles.websocket.chat");
    }

    

    public void sendDataUpdates() {

        this.template.convertAndSend(
                "/topic/dataRandomInput", new Random().nextInt(100));

    }

    @PostConstruct
    private void broadcastRandomDataPerioidically() {
        LOG.debug("@@Periodic Broadcast setup 1 for the graph update");
        scheduler.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                // LOG.debug("@@Periodic Broadcast setup 2");
                sendDataUpdates();
            }
        }, 3000);

    }

}
