package org.cleaning.chatty.controller;

import lombok.AllArgsConstructor;
import org.cleaning.chatty.model.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class ChatController {

	private final SimpMessagingTemplate template;

	@MessageMapping("/message")
	@SendTo("/chatroom/public")
	public Message sendMessage(@Payload Message message) {
		return message;
	}

	@MessageMapping("/private-message")
	public void sendPrivateMessage(@Payload Message message) {
		template.convertAndSendToUser(message.getReceiverName(), "/private", message);
	}
}
