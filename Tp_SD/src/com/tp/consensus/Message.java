package com.tp.consensus;

import java.io.Serializable;

public class Message implements Serializable {
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
	public String toString(){
		return "id : " + id + " info : " + info;
	}
}
