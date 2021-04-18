package com.example.demo.dao;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

public class ReplyDAO implements Serializable{
	
	private String msg;
	private HttpStatus status;
	private Object data;
	private boolean hasError;
	
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public boolean isHasError() {
		return hasError;
	}
	public void setHasError(boolean hasError) {
		this.hasError = hasError;
	}


}
