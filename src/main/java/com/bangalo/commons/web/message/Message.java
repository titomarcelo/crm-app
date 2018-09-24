package com.bangalo.commons.web.message;

public class Message {

	public static final String MSG = "msg";

	private MessageType type;
	private String text;

	public static Message get(MessageType type, String text) {
		return new Message(type, text);
	}

	private Message(MessageType type, String text) {
		this.type = type;
		this.text = text;
	}

	public MessageType getType() {
		return type;
	}

	public void setType(MessageType type) {
		this.type = type;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getStyle() {
		return getType().getStyle();
	}

}
