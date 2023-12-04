package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

@Controller
public class GreetingController {
    private SimpMessagingTemplate template;

    @Autowired
    public GreetingController(SimpMessagingTemplate template){
        this.template=template;
    }
    @MessageMapping("/greetings")
    public void greet(String text){
        System.out.println(text);
        this.template.convertAndSend("/topic/1",text);//리액트에서 구독
        this.template.convertAndSend("/topic/test",text);//index.html에서 구독
    }
}
