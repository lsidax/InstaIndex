package com.example.demo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.demo.dao.DocumentDAO;
import com.example.demo.dao.QueryResultDAO;
import com.example.demo.dao.ReplyDAO;

@Service
public class DocumentHandlerService {

	HashMap<String, HashSet<String>> documentMap = new HashMap();
	HashMap<String, DocumentDAO> docIdMap = new HashMap();

	public ReplyDAO addDocument(DocumentDAO document, String tokenizer, String normalizer) {

		ReplyDAO replytData = new ReplyDAO();

		if (document == null) {
			replytData.setMsg("Data Not Received.");
			replytData.setStatus(HttpStatus.BAD_REQUEST);
			replytData.setHasError(true);
			return replytData;

		}
		if (document.getId() == null || document.getId().trim().isEmpty()) {
			replytData.setMsg("Invalid Document Id.");
			replytData.setStatus(HttpStatus.BAD_REQUEST);
			replytData.setHasError(true);
			return replytData;
		}
		if (document.getText() == null || document.getText().trim().isEmpty()) {
			replytData.setMsg("Invalid Document Data.");
			replytData.setStatus(HttpStatus.BAD_REQUEST);
			replytData.setHasError(true);
			return replytData;
		}
		if (docIdMap.containsKey(document.getId())) {
			replytData.setMsg("Duplicate Document Id.");
			replytData.setStatus(HttpStatus.BAD_REQUEST);
			replytData.setHasError(true);
			return replytData;
		}

		docIdMap.put(document.getId(), document);

		String tempText = null;
		if (normalizer.trim().equalsIgnoreCase("UPPER_CASE")) {
			tempText = document.getText().trim().toUpperCase();
		} else {
			tempText = document.getText().trim().toLowerCase();
		}

		tokenizer = tokenizer.trim();

		if (tokenizer.equals("")) {
			tokenizer = " ";
		}

		for (String key : tempText.split(tokenizer)) {
			if (!documentMap.containsKey(key.trim())) {
				documentMap.put(key.trim(), new HashSet());
			}
			documentMap.get(key.trim()).add(document.getId());
		}

		replytData.setMsg("Document Added Sucessfully");
		replytData.setStatus(HttpStatus.OK);
		return replytData;
	}

	public ReplyDAO getDocumentForQuery(String queryReceived, String normalizer) {
		ReplyDAO replytData = new ReplyDAO();

		if (queryReceived == null || queryReceived.trim().isEmpty()) {
			replytData.setMsg("Invalid Query Received.");
			replytData.setStatus(HttpStatus.BAD_REQUEST);
			replytData.setHasError(true);
			return replytData;

		}

		String query = null;

		if (normalizer.trim().equalsIgnoreCase("UPPER_CASE")) {
			query = queryReceived.trim().toUpperCase();
		} else {
			query = queryReceived.trim().toLowerCase();
		}

		if (!documentMap.containsKey(query)) {
			replytData.setMsg("No Document Found For Query.");
			replytData.setStatus(HttpStatus.NO_CONTENT);
			replytData.setHasError(true);
			return replytData;

		}

		HashSet<String> docIdLst = documentMap.get(query);
		if (docIdLst == null || docIdLst.size() <= 0) {
			replytData.setMsg("No Document Found For Query.");
			replytData.setStatus(HttpStatus.NO_CONTENT);
			replytData.setHasError(true);
			return replytData;

		}

		List<DocumentDAO> documentList = new ArrayList();

		for (String docId : docIdLst) {
			if (docIdMap.containsKey(docId)) {
				documentList.add(docIdMap.get(docId));
			}
		}

		QueryResultDAO docsData = new QueryResultDAO(documentList.size(), documentList);
		replytData.setData(docsData);
		replytData.setStatus(HttpStatus.OK);

		return replytData;

	}

}
