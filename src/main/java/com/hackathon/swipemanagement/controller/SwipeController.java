package com.hackathon.swipemanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hackathon.swipemanagement.service.SwipeService;

@RestController
@RequestMapping("/employee")
public class SwipeController {

	@Autowired
	SwipeService swipeService;

	@PostMapping("/swipeInOut")
	public ResponseEntity<String> captureSwipeDetails(@RequestParam Long employeeId, @RequestParam Long facilityId) {
		String swipeDetails = swipeService.captureSwipeDetails(employeeId, facilityId);
		return new ResponseEntity<>(swipeDetails, HttpStatus.CREATED);

	}

}
