package com.hackathon.swipemanagement.service;

import java.util.List;

import com.hackathon.swipemanagement.dto.SwipeResponseDto;
import com.hackathon.swipemanagement.exception.NotFoundException;

public interface SwipeService {

	public String captureSwipeDetails(Long employeeId, Long facilityId) throws NotFoundException;

	public List<SwipeResponseDto> getSwipeReportByEmployeeId(Long employeeId, String date) throws NotFoundException;

	public List<SwipeResponseDto> getSwipeReportByFacilityId(Long facilityId, String date) throws NotFoundException;

}
