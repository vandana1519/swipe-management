package com.hackathon.swipemanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hackathon.swipemanagement.dto.SwipeResponseDto;
import com.hackathon.swipemanagement.exception.NotFoundException;
import com.hackathon.swipemanagement.service.SwipeService;

@RestController
@RequestMapping("/employee")
public class SwipeController {

	@Autowired
	SwipeService swipeService;

	@PostMapping("/swipeInOut")
	public ResponseEntity<String> captureSwipeDetails(@RequestParam Long employeeId, @RequestParam Long facilityId) {
		String swipeDetailResponse;
		try {
			swipeDetailResponse = swipeService.captureSwipeDetails(employeeId, facilityId);
		} catch (NotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
		}
		return new ResponseEntity<>(swipeDetailResponse, HttpStatus.OK);

	}
	
	@GetMapping("/swipeReport/byEmployeeId")
	public ResponseEntity getSwipeReportByEmployeeId(@RequestParam(value ="employeeId") Long employeeId, @RequestParam(value ="date", required = false) String date) {
		List<SwipeResponseDto> swipeList;
		try {
			swipeList = swipeService.getSwipeReportByEmployeeId(employeeId,date);
		} catch (NotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
		}
		return new ResponseEntity<>(swipeList, HttpStatus.OK);

	}
	
	@GetMapping("/swipeReport/byFacilityId")
	public ResponseEntity getSwipeReportByFacilityId(@RequestParam(value ="facilityId") Long facilityId, @RequestParam(value ="date", required = false) String date) {
		List<SwipeResponseDto> swipeList;
		try {
			swipeList = swipeService.getSwipeReportByFacilityId(facilityId,date);
		} catch (NotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
		}
		return new ResponseEntity<>(swipeList, HttpStatus.OK);

	}

}
