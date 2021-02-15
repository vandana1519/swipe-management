package com.hackathon.swipemanagement.service;

import java.time.LocalDate;
import java.util.List;

import com.hackathon.swipemanagement.dto.SwipeRequestReportDto;
import com.hackathon.swipemanagement.dto.SwipeResponseDto;

public interface SwipeService {

	public String captureSwipeDetails(Long employeeId, Long facilityId);

	public List<SwipeResponseDto> getSwipeReport(SwipeRequestReportDto requestDto);

}
