package com.hackathon.swipemanagement.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.hackathon.swipemanagement.dto.SwipeResponseDto;
import com.hackathon.swipemanagement.entity.Employees;
import com.hackathon.swipemanagement.entity.Facility;
import com.hackathon.swipemanagement.entity.Swipe;
import com.hackathon.swipemanagement.exception.NotFoundException;
import com.hackathon.swipemanagement.service.SwipeService;

@WebMvcTest(value = SwipeController.class)
class SwipeControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	SwipeService swipeService;

	static Swipe swipe = new Swipe();
	static Employees employee = new Employees();
	static Facility facility = new Facility();	
	static SwipeResponseDto swipeResponseDto = new SwipeResponseDto();
	static List<SwipeResponseDto> swipeResponseDtoList = new ArrayList<>();
	
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
		
		swipeResponseDto.setEmployeeId(1L);
		swipeResponseDto.setFacilityId(1L);
		swipeResponseDto.setHoursWorked(LocalTime.now());
		swipeResponseDto.setDate(LocalDate.now());
		swipeResponseDtoList.add(swipeResponseDto);

	}
	
	@Test
	void captureSwipeDetailsTestSuccess() throws Exception {
		
		Mockito.when(swipeService.captureSwipeDetails(Mockito.anyLong(), Mockito.anyLong())).thenReturn(new String ("Swipe details captured successfully."));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/employee/swipeInOut").accept(MediaType.APPLICATION_JSON)
				.param("employeeId", "1").param("facilityId", "1");

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
	}
	
	@Test
	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
	void captureSwipeDetailsTestException() throws Exception {
		
		Mockito.doThrow(NotFoundException.class).when(swipeService).captureSwipeDetails(Mockito.anyLong(), Mockito.anyLong());
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/employee/swipeInOut").accept(MediaType.APPLICATION_JSON)
				.param("employeeId", "2").param("facilityId", "1");

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(HttpStatus.EXPECTATION_FAILED.value(), result.getResponse().getStatus());
	}
	
	@Test
	void getSwipeReportByEmployeeIdTestSuccess() throws Exception {
		
		Mockito.doReturn(swipeResponseDtoList).when(swipeService).getSwipeReportByEmployeeId(employee.getEmployeeId(), "2021-02-15");
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/employee/swipeReport/byEmployeeId").accept(MediaType.APPLICATION_JSON)
				.param("employeeId", "1").param("date", "2021-02-15");

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
	}
	
	@Test
	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
	void getSwipeReportByEmployeeIdTestException() throws Exception {
		
		Mockito.doThrow(NotFoundException.class).when(swipeService).getSwipeReportByEmployeeId(Mockito.anyLong(),Mockito.anyString());
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/employee/swipeReport/byEmployeeId").accept(MediaType.APPLICATION_JSON)
				.param("employeeId", "1").param("date", "2021-02-15");

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(HttpStatus.EXPECTATION_FAILED.value(), result.getResponse().getStatus());
	}
	
	@Test
	void getSwipeReportByFacilityIdTestSuccess() throws Exception {
		
		Mockito.doReturn(swipeResponseDtoList).when(swipeService).getSwipeReportByFacilityId(facility.getFacilityId(), "2021-02-15");
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/employee/swipeReport/byFacilityId").accept(MediaType.APPLICATION_JSON)
				.param("facilityId", "1").param("date", "2021-02-15");

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
	}
	
	@Test
	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
	void getSwipeReportByFacilityIdTestException() throws Exception {
		
		Mockito.doThrow(NotFoundException.class).when(swipeService).getSwipeReportByFacilityId(Mockito.anyLong(),Mockito.anyString());
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/employee/swipeReport/byFacilityId").accept(MediaType.APPLICATION_JSON)
				.param("facilityId", "5").param("date", "2021-02-15");

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(HttpStatus.EXPECTATION_FAILED.value(), result.getResponse().getStatus());
	}
	
}
