package com.hackathon.swipemanagement.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class SwipeResponseDto {

	private Long employeeId;
	private Long facilityId;
	private LocalDate date;
	private LocalTime hoursWorked;

	
	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}
	public Long getFacilityId() {
		return facilityId;
	}

	public void setFacilityId(Long facilityId) {
		this.facilityId = facilityId;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getHoursWorked() {
		return hoursWorked;
	}

	public void setHoursWorked(LocalTime hoursWorked) {
		this.hoursWorked = hoursWorked;
	}

}
