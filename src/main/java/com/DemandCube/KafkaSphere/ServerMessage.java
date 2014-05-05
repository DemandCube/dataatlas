package com.DemandCube.KafkaSphere;

public class ServerMessage {

	private String message;

	public ServerMessage() {}

	public ServerMessage(String msg) {
		this.message  = msg;
	}

	public void setMessage(String msg) {
		this.message = msg;
	}

	public String getMessage() {
		return message;
	}

};
