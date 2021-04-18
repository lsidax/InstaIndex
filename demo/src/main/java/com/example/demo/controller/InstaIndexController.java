package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.DocumentDAO;
import com.example.demo.dao.QueryResultDAO;
import com.example.demo.dao.ReplyDAO;
import com.example.demo.service.DocumentHandlerService;

@RestController
@RequestMapping("/index")
public class InstaIndexController {

	@Autowired
	DocumentHandlerService documentHandlerService;

	@Value("${app.tokenizer}")
	private String tokenizer = " ";

	@Value("${app.normalizer}")
	private String normalizer = " ";

	@PostMapping("/document")
	public ResponseEntity<String> addDocument(@RequestBody @Valid DocumentDAO document) {

		ReplyDAO reply = documentHandlerService.addDocument(document, tokenizer, normalizer);

		return new ResponseEntity<String>(reply.getMsg(), reply.getStatus());

	}

	@GetMapping("/search")
	public ResponseEntity<QueryResultDAO> addDocument(@RequestParam(value = "query") String query) {

		ReplyDAO reply = documentHandlerService.getDocumentForQuery(query, normalizer);

		if (reply.isHasError()) {
			return new ResponseEntity<QueryResultDAO>(reply.getStatus());
		} else {
			return new ResponseEntity<QueryResultDAO>((QueryResultDAO) reply.getData(), reply.getStatus());
		}

	}

}
