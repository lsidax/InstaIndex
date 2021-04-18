package com.example.demo.dao;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class DocumentDAO implements Serializable {

	@NotNull(message = "Id can't be null.")
	private String id;

	@NotNull(message = "Document can't be null.")
	private String text;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
