package com.fradantim.spacexmanagement.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fradantim.spacexmanagement.dto.GenericRequest;
import com.fradantim.spacexmanagement.dto.Issue;
import com.fradantim.spacexmanagement.dto.trello.Card;

import io.swagger.v3.oas.annotations.Operation;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/")
public class GenericResource {

	@Autowired
	private IssueResource issueResource;

	@Operation(description = "${generic-resource.description}")
	@PostMapping
	public Mono<Card> createItem(@RequestBody GenericRequest request) {
		if (request == null || request.getType() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The type field is mandatory.");
		}

		switch (request.getType()) {
		case bug: {
			// TODO
		}
		case issue: {
			return issueResource.createIssue(Issue.fromGenericRequest(request));
		}
		case task: {
			// TODO
		}
		}

		return Mono.empty();
	}
}
