package org.cleaning.chatty.controller;

import org.cleaning.chatty.model.Message;
import org.cleaning.chatty.model.MessageType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

public class ChatControllerTest {

	@Mock
	private SimpMessagingTemplate template;

	@InjectMocks
	private ChatController chatController;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testSendMessage() {
		Message message = Message.builder()
				.senderName("user1")
				.messageType(MessageType.CHAT)
				.message("Hello World")
				.build();

		Message response = chatController.sendMessage(message);

		assertEquals(message, response);
	}

	@Test
	public void testSendPrivateMessage() {
		Message message = Message.builder()
				.senderName("user1")
				.receiverName("user2")
				.messageType(MessageType.CHAT)
				.message("Hello Private")
				.build();

		chatController.sendPrivateMessage(message);

		verify(template).convertAndSendToUser(eq("user2"), eq("/private"), eq(message));
	}
}
