package org.cleaning.chatty.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cleaning.chatty.model.ChatMessage;
import org.cleaning.chatty.model.MessageType;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
@RequiredArgsConstructor
@Slf4j
public class WebSocketEventListener {

	private final SimpMessageSendingOperations messageOperations;
	@EventListener
	public void disconnectListener(SessionDisconnectEvent event) {
		StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
		String username = "";
		if (accessor.getSessionAttributes() != null &&
				accessor.getSessionAttributes().containsKey("username")) {
			username = (String) accessor.getSessionAttributes().get("username");
		}
		if(username.isEmpty()){
			log.info("User disconnected: {}",username);

			ChatMessage chatMessage = ChatMessage.builder()
					.messageType(MessageType.LEAVE)
					.sender(username)
					.build();
			messageOperations.convertAndSend("/topic/public",chatMessage);
		}

	}
}
