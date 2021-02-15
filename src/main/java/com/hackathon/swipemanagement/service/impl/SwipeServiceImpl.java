package com.hackathon.swipemanagement.service.impl;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hackathon.swipemanagement.dto.SwipeRequestReportDto;
import com.hackathon.swipemanagement.dto.SwipeResponseDto;
import com.hackathon.swipemanagement.entity.Employees;
import com.hackathon.swipemanagement.entity.Facility;
import com.hackathon.swipemanagement.entity.Swipe;
import com.hackathon.swipemanagement.repository.SwipeRepository;
import com.hackathon.swipemanagement.service.SwipeService;

@Service
public class SwipeServiceImpl implements SwipeService {

	@Autowired
	SwipeRepository swipeRepository;

	@Override
	public String captureSwipeDetails(Long employeeId, Long facilityId) {

		Swipe swipe = swipeRepository.getSwipeDetails(employeeId, facilityId);
		if (swipe == null) {
			System.out.println("inside if**");
			Swipe swipe2 = new Swipe();
			Employees employee = new Employees();
			employee.setEmployeeId(employeeId);

			Facility facility = new Facility();
			facility.setFacilityId(facilityId);

			swipe2.setEmployee(employee);
			swipe2.setFacility(facility);
			// swipe.setHoursWorked(0L);
			swipe2.setSwipeIn(LocalDateTime.now());

			swipeRepository.save(swipe2);
		} else {
			swipe.setSwipeOut(LocalDateTime.now());
			Swipe swipe1 = swipeRepository.saveAndFlush(swipe);
			LocalDateTime swipeIn = swipe1.getSwipeIn();
			LocalDateTime swipeOut = swipe1.getSwipeOut();
			Duration duration = Duration.between(swipeIn, swipeOut);
			System.out.println("Hours:::" + duration.toHours());
			Long hours = duration.toHours();
			swipe1.setHoursWorked(hours);
			swipeRepository.saveAndFlush(swipe1);

		}

		return "Swipe details captured successfully.";
	}

	@Override
	public List<SwipeResponseDto> getSwipeReport(SwipeRequestReportDto requestDto) {
		List<Swipe> swipeList = swipeRepository.getSwipeDetailsList(requestDto.getEmployeeId());
		return null;
	}

}
