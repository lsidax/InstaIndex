package com.example.demo.dao;

import java.io.Serializable;
import java.util.List;

public class QueryResultDAO implements Serializable {

	private int count;
	private List<DocumentDAO> documents;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<DocumentDAO> getDocuments() {
		return documents;
	}

	public void setDocuments(List<DocumentDAO> documents) {
		this.documents = documents;
	}

	public QueryResultDAO(int count, List<DocumentDAO> documents) {
		super();
		this.count = count;
		this.documents = documents;
	}

	public QueryResultDAO() {
		super();
	}

}
