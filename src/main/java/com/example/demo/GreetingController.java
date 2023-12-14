package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.socket.BinaryMessage;

import java.io.File;
import java.nio.ByteBuffer;

@Controller
public class GreetingController {
    private SimpMessagingTemplate template;

    @Autowired
    public GreetingController(SimpMessagingTemplate template){
        this.template=template;
    }
    @MessageMapping("/greetings")
    public void greet(@Payload String text){
        System.out.println(text);
        this.template.convertAndSend("/chat/1",text);//리액트에서 구독

    }
    @MessageMapping("/binary")
    public void greet(@Payload byte[] b){
        System.out.println(b.length);
        this.template.convertAndSend("/chat/1",b);//리액트에서 구독

    }
}
