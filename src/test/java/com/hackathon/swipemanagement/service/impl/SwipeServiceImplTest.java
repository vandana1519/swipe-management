package com.hackathon.swipemanagement.service.impl;

import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.hackathon.swipemanagement.dto.SwipeResponseDto;
import com.hackathon.swipemanagement.entity.Employees;
import com.hackathon.swipemanagement.entity.Facility;
import com.hackathon.swipemanagement.entity.Swipe;
import com.hackathon.swipemanagement.exception.NotFoundException;
import com.hackathon.swipemanagement.repository.EmployeeRepository;
import com.hackathon.swipemanagement.repository.FacilityRepository;
import com.hackathon.swipemanagement.repository.SwipeRepository;

@SpringBootTest
class SwipeServiceImplTest {
	
	@InjectMocks
	SwipeServiceImpl swipeServiceImpl;
	
	@Mock
	SwipeRepository swipeRepository;
	
	@Mock
	EmployeeRepository employeeRepository;
	
	@Mock
	FacilityRepository facilityRepository;
	
	static Swipe swipe = new Swipe();
	static Swipe swipe1 = new Swipe();
	static Employees employee = new Employees();
	static Facility facility = new Facility();	
	static SwipeResponseDto swipeResponseDto = new SwipeResponseDto();
	static List<SwipeResponseDto> swipeResponseDtoList = new ArrayList<>();
	static List<Swipe> swipeList = new ArrayList<>();
	static List<Swipe> swipeListDateDiff = new ArrayList<>();
	
	@BeforeAll
	public static void setup() {
		
		employee.setEmployeeId(1L);
		employee.setEmployeeName("Abc");
		
		facility.setFacilityId(1L);
		facility.setFacilityCity("Pune");
		facility.setFacilityLocation("Qubix Business Park");
		facility.setFacilityName("HCL");
		
		swipe.setSwipeId(1L);
		swipe.setSwipeIn(LocalDateTime.now());
		swipe.setSwipeIn(LocalDateTime.now());
		swipe.setFacility(facility);
		swipe.setEmployee(employee);
		swipe.setMinutesWorked(8L);
		swipeList.add(swipe);
		
		swipe1.setSwipeId(1L);
		swipe1.setSwipeIn(LocalDateTime.now().minusDays(2));
		swipe1.setSwipeIn(LocalDateTime.now());
		swipe1.setFacility(facility);
		swipe1.setEmployee(employee);
		swipe1.setMinutesWorked(8L);
		swipeListDateDiff.add(swipe1);
		
		swipeResponseDto.setEmployeeId(1L);
		swipeResponseDto.setFacilityId(1L);
		swipeResponseDto.setHoursWorked(LocalTime.now());
		swipeResponseDto.setDate(LocalDate.now());
		swipeResponseDtoList.add(swipeResponseDto);

	}
	
	@Test
	void captureSwipeDetailsTestSuccess() throws NotFoundException {
		
		Mockito.when(employeeRepository.findByEmployeeId(Mockito.anyLong())).thenReturn(employee);
		Mockito.when(facilityRepository.findByFacilityId(Mockito.anyLong())).thenReturn(facility);
		Mockito.when(swipeRepository.getSwipeDetails(Mockito.anyLong(), Mockito.anyLong())).thenReturn(swipeList);
		Mockito.doReturn(swipe).when(swipeRepository).saveAndFlush(swipe);
		
		String swipeResponse = swipeServiceImpl.captureSwipeDetails(1L, 1L);
		Assertions.assertNotNull(swipeResponse);
		
	}	
	
	@Test
	void captureSwipeDetailsTestSuccessForDiffDate() throws NotFoundException {
		
		Mockito.when(employeeRepository.findByEmployeeId(Mockito.anyLong())).thenReturn(employee);
		Mockito.when(facilityRepository.findByFacilityId(Mockito.anyLong())).thenReturn(facility);
		swipe1.setSwipeIn(LocalDateTime.now().minusDays(2));
		swipeListDateDiff.add(swipe1);
		Mockito.when(swipeRepository.getSwipeDetails(Mockito.anyLong(), Mockito.anyLong())).thenReturn(swipeListDateDiff);
		Mockito.doReturn(swipe1).when(swipeRepository).save(swipe1);
		
		String swipeResponse = swipeServiceImpl.captureSwipeDetails(1L, 1L);
		Assertions.assertNotNull(swipeResponse);
		
	}
	
	@Test
	void captureSwipeDetailsTestSuccessForNull() throws NotFoundException {
		
		Mockito.when(employeeRepository.findByEmployeeId(Mockito.anyLong())).thenReturn(employee);
		Mockito.when(facilityRepository.findByFacilityId(Mockito.anyLong())).thenReturn(facility);
		Mockito.when(swipeRepository.getSwipeDetails(Mockito.anyLong(), Mockito.anyLong())).thenReturn(Collections.emptyList());
		Mockito.doReturn(swipe).when(swipeRepository).save(swipe);
		
		String swipeResponse = swipeServiceImpl.captureSwipeDetails(1L, 1L);
		Assertions.assertNotNull(swipeResponse);
		
	}
	
	@Test
	@ExceptionHandler(NotFoundException.class)
	void captureSwipeDetailsTestEmployeeException() throws NotFoundException {
		Mockito.when(employeeRepository.findByEmployeeId(Mockito.anyLong())).thenReturn(null);

		Assertions.assertThrows(NotFoundException.class, new Executable() {

			@Override
			public void execute() throws Throwable {
				String swipeResponse = swipeServiceImpl.captureSwipeDetails(1L, 1L);
				Assertions.assertEquals("Employee not found!!", swipeResponse);
			}
		});

	}
	
	@Test
	@ExceptionHandler(NotFoundException.class)
	void captureSwipeDetailsTestFacilityException() throws NotFoundException {
		Mockito.when(facilityRepository.findByFacilityId(Mockito.anyLong())).thenReturn(null);

		Assertions.assertThrows(NotFoundException.class, new Executable() {

			@Override
			public void execute() throws Throwable {
				String swipeResponse = swipeServiceImpl.captureSwipeDetails(1L, 1L);
				Assertions.assertEquals("Facility not found!!", swipeResponse);
			}
		});

	}
	
	@Test
	void getSwipeReportByEmployeeIdTestSuccessForDateNull() throws NotFoundException {
		
		Mockito.when(employeeRepository.findByEmployeeId(Mockito.anyLong())).thenReturn(employee);
		Mockito.doReturn(swipeList).when(swipeRepository).getSwipeDetailsListByEmployeeId(Mockito.anyLong(), Mockito.anyInt());
		
		List<SwipeResponseDto> swipeResponseDto = swipeServiceImpl.getSwipeReportByEmployeeId(1L, null);
		Assertions.assertNotNull(swipeResponseDto);		
		
	}
	
	@Test
	void getSwipeReportByEmployeeIdTestSuccess() throws NotFoundException {
		
		Mockito.when(employeeRepository.findByEmployeeId(Mockito.anyLong())).thenReturn(employee);
		Mockito.doReturn(swipeList).when(swipeRepository).getSwipeDetailsListByDate(Mockito.anyLong(), Mockito.anyString());
		
		List<SwipeResponseDto> swipeResponseDto = swipeServiceImpl.getSwipeReportByEmployeeId(1L, "2021-02-15");
		Assertions.assertNotNull(swipeResponseDto);		
		
	}
	
	@Test
	@ExceptionHandler(NotFoundException.class)
	void getSwipeReportByEmployeeIdTestEmployeeException() throws NotFoundException {
		Mockito.when(employeeRepository.findByEmployeeId(Mockito.anyLong())).thenReturn(null);

		Assertions.assertThrows(NotFoundException.class, new Executable() {

			@Override
			public void execute() throws Throwable {
				List<SwipeResponseDto> swipeResponseDto = swipeServiceImpl.getSwipeReportByEmployeeId(5L, "2021-02-15");
				assertNull(swipeResponseDto);
			}
		});

	}
	
	@Test
	void getSwipeReportByFacilityIdTestSuccessForDateNull() throws NotFoundException {
		
		Mockito.when(facilityRepository.findByFacilityId(Mockito.anyLong())).thenReturn(facility);
		Mockito.doReturn(swipeList).when(swipeRepository).getSwipeDetailsListByFacilityId(Mockito.anyLong(), Mockito.anyInt());
		
		List<SwipeResponseDto> swipeResponseDto = swipeServiceImpl.getSwipeReportByFacilityId(1L, null);
		Assertions.assertNotNull(swipeResponseDto);		
		
	}
	
	@Test
	void getSwipeReportByFacilityIdTestSuccess() throws NotFoundException {
		
		Mockito.when(facilityRepository.findByFacilityId(Mockito.anyLong())).thenReturn(facility);
		Mockito.doReturn(swipeList).when(swipeRepository).getSwipeDetailsListByDateFacility(Mockito.anyLong(), Mockito.anyString());
		
		List<SwipeResponseDto> swipeResponseDto = swipeServiceImpl.getSwipeReportByFacilityId(1L, "2021-02-15");
		Assertions.assertNotNull(swipeResponseDto);		
		
	}
	
	@Test
	@ExceptionHandler(NotFoundException.class)
	void getSwipeReportByFacilityIdTestEmployeeException() throws NotFoundException {
		Mockito.when(facilityRepository.findByFacilityId(Mockito.anyLong())).thenReturn(null);

		Assertions.assertThrows(NotFoundException.class, new Executable() {

			@Override
			public void execute() throws Throwable {
				List<SwipeResponseDto> swipeResponseDto = swipeServiceImpl.getSwipeReportByFacilityId(5L, "2021-02-15");
				assertNull(swipeResponseDto);
			}
		});

	}

}
