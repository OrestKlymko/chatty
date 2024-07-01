package org.cleaning.chatty.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Message {
	private String senderName;
	private String receiverName;
	private MessageType messageType;
	private String message;
}
