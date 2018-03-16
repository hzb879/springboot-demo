package com.hzzb.springboot.websocket.demo.web;

import java.security.Principal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {
	
	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	
	@MessageMapping("/common")
	@SendTo("/topic/common") // 默认发给@MessageMapping:destination路径前加/topic的destination,此注解可不写
	public String handlerMsg(String msg, Principal principal) {
		System.out.println("收到消息: " + msg);
		return principal.getName() + ":  " + msg;
	}
	
	@MessageMapping("/talk")
	public void talkToSpecialUser(Map<String, String> payload, Principal principal) {
		System.out.println(principal.getName() + 
				"发送: {" + payload.get("data") + 
				"} 给: " + payload.get("toUser"));
		messagingTemplate.convertAndSendToUser(payload.get("toUser"), "/queue/my_msg", payload.get("data"));
	}
	
}
