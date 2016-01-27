package com.tp.consensus;

public class Message {
	private int id;
	private Object info;
	
	public Message(int id, Object info) {
		this.id = id;
		this.info = info;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Object getInfo() {
		return info;
	}
	public void setInfo(Object info) {
		this.info = info;
	}
}
