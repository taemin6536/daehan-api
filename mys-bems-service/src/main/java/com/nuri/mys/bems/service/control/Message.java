package com.nuri.mys.bems.service.control;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
@Getter
@Setter
public class Message<T extends Event> extends Header {
	private static final long serialVersionUID = 1L;

	private List<T> payload;
	
	public Message() {
		payload = new ArrayList<T>();
	}
	
	public List<T> getPayload() {
		if(payload == null) {
			payload = new ArrayList<T>();
		}

		return payload;
	}
	
	public void setPayload(List<T> payload) {
		this.payload = payload;
	}
	
	public void addPayload(T model) {
		if(model == null) {
			return;
		}
		
		getPayload().add(model);
	}
	
}
