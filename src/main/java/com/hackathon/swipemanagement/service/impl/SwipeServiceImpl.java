package com.hackathon.swipemanagement.service.impl;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hackathon.swipemanagement.dto.SwipeResponseDto;
import com.hackathon.swipemanagement.entity.Employees;
import com.hackathon.swipemanagement.entity.Facility;
import com.hackathon.swipemanagement.entity.Swipe;
import com.hackathon.swipemanagement.exception.NotFoundException;
import com.hackathon.swipemanagement.repository.EmployeeRepository;
import com.hackathon.swipemanagement.repository.FacilityRepository;
import com.hackathon.swipemanagement.repository.SwipeRepository;
import com.hackathon.swipemanagement.service.SwipeService;

@Service
public class SwipeServiceImpl implements SwipeService {

	@Autowired
	SwipeRepository swipeRepository;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	FacilityRepository facilityRepository;

	@Override
	public String captureSwipeDetails(Long employeeId, Long facilityId) throws NotFoundException {
		
		if(employeeRepository.findByEmployeeId(employeeId) == null) {
			throw new NotFoundException("Employee not found!!");
		}else if(facilityRepository.findByFacilityId(facilityId) == null) {
			throw new NotFoundException("Facility not found!!");
		}				
		Swipe swipe2 = new Swipe();
		Facility facility = new Facility();
		Employees employee = new Employees();
		List<Swipe> swipeList = swipeRepository.getSwipeDetails(employeeId, facilityId);
		if (!(swipeList.isEmpty())) {
			for (Swipe swipe : swipeList) {
				Date date1 = Timestamp.valueOf(swipe.getSwipeIn());
				Date date2 = new Date(System.currentTimeMillis());
				if (DateUtils.isSameDay(date1, date2)) {
					swipe.setSwipeOut(LocalDateTime.now());
					Swipe swipe1 = swipeRepository.saveAndFlush(swipe);
					LocalDateTime swipeIn = swipe1.getSwipeIn();
					LocalDateTime swipeOut = swipe1.getSwipeOut();
					Duration duration = Duration.between(swipeIn, swipeOut);
					Long minutes = duration.toMinutes();
					swipe1.setMinutesWorked(minutes);
					swipeRepository.saveAndFlush(swipe1);
				}
				else {
					employee.setEmployeeId(employeeId);
					facility.setFacilityId(facilityId);
					swipe2.setEmployee(employee);
					swipe2.setFacility(facility);
					swipe2.setMinutesWorked(0L);
					swipe2.setSwipeIn(LocalDateTime.now());
					swipe2.setSwipeOut(LocalDateTime.now());
					swipeRepository.save(swipe2);
				}
			}
		} else {
			employee.setEmployeeId(employeeId);
			facility.setFacilityId(facilityId);
			swipe2.setEmployee(employee);
			swipe2.setFacility(facility);
			swipe2.setMinutesWorked(0L);
			swipe2.setSwipeIn(LocalDateTime.now());
			swipe2.setSwipeOut(LocalDateTime.now());
			swipeRepository.save(swipe2);
		}

		return "Swipe details captured successfully.";
	}

	@Override
	public List<SwipeResponseDto> getSwipeReportByEmployeeId(Long employeeId, String date) throws NotFoundException {
		
		if(employeeRepository.findByEmployeeId(employeeId) == null) {
			throw new NotFoundException("Employee not found!!");
		}
		
		List<SwipeResponseDto> swipeResponseDtoList = new ArrayList<>();
		List<Swipe> swipeList;
		if (date == null) {
			int currentMonth = LocalDateTime.now().toLocalDate().getMonthValue();
			swipeList = swipeRepository.getSwipeDetailsListByEmployeeId(employeeId, currentMonth);
		} else {
			swipeList = swipeRepository.getSwipeDetailsListByDate(employeeId, date);
		}
		for (Swipe swipe : swipeList) {
			SwipeResponseDto swipeResponseDto = new SwipeResponseDto();
			swipeResponseDto.setEmployeeId(swipe.getEmployee().getEmployeeId());
			swipeResponseDto.setFacilityId(swipe.getFacility().getFacilityId());
			swipeResponseDto.setDate(swipe.getSwipeIn().toLocalDate());
			swipeResponseDto.setHoursWorked(LocalTime.MIN.plus(Duration.ofMinutes(swipe.getMinutesWorked())));
			swipeResponseDtoList.add(swipeResponseDto);
		}
		return swipeResponseDtoList;
	}

	@Override
	public List<SwipeResponseDto> getSwipeReportByFacilityId(Long facilityId, String date) throws NotFoundException {
		
		if(facilityRepository.findByFacilityId(facilityId) == null) {
			throw new NotFoundException("Facility not found!!");
		}
		
		List<SwipeResponseDto> swipeResponseDtoList = new ArrayList<>();
		List<Swipe> swipeList;
		if (date == null) {
			int currentMonth = LocalDateTime.now().toLocalDate().getMonthValue();
			swipeList = swipeRepository.getSwipeDetailsListByFacilityId(facilityId, currentMonth);
		} else {
			swipeList = swipeRepository.getSwipeDetailsListByDateFacility(facilityId, date);
		}

		for (Swipe swipe : swipeList) {
			SwipeResponseDto swipeResponseDto = new SwipeResponseDto();
			swipeResponseDto.setEmployeeId(swipe.getEmployee().getEmployeeId());
			swipeResponseDto.setFacilityId(swipe.getFacility().getFacilityId());
			swipeResponseDto.setDate(swipe.getSwipeIn().toLocalDate());
			swipeResponseDto.setHoursWorked(LocalTime.MIN.plus(Duration.ofMinutes(swipe.getMinutesWorked())));
			swipeResponseDtoList.add(swipeResponseDto);
		}
		return swipeResponseDtoList;
	}

}
