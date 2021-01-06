package com.springwebsocket.websocket_practice.controller;

import com.springwebsocket.websocket_practice.domain.Greeting;
import com.springwebsocket.websocket_practice.domain.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class GreetingController {

    // 수신
    // config.setApplicationDestinationPrefixes("/app") 이기때문에 /app/hello
    @MessageMapping("/hello")   
    // 송신
    @SendTo("/topic/greetings")
    public Greeting greeting(Message message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }
}
