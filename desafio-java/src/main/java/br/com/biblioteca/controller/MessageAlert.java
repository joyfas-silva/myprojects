package br.com.biblioteca.controller;

import br.com.biblioteca.enums.AlertTypeEnum;

public class MessageAlert {
	
	private String type;
	private String msg;
	
	public MessageAlert(AlertTypeEnum alertTypeEnum, String msg) {
		this.type = alertTypeEnum.getType();
		this.msg = msg;
	}
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
