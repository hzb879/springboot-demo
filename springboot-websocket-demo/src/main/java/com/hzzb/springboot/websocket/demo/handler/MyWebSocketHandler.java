package com.hzzb.springboot.websocket.demo.handler;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class MyWebSocketHandler extends TextWebSocketHandler{
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		System.out.println("收到客户端消息:..." + message.getPayload());
		session.sendMessage(new TextMessage("欢迎访问websocket服务"));
	}
	
}
