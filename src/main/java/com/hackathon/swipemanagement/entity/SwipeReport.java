package com.hackathon.swipemanagement.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Swipe_Report")
public class SwipeReport {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long swipeReportId;
	private Long employeeId;
	private Long facilityId;
	private Long swipeId;
	private LocalTime totalHoursWorked;
	private LocalDate date;

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Long getSwipeReportId() {
		return swipeReportId;
	}

	public void setSwipeReportId(Long swipeReportId) {
		this.swipeReportId = swipeReportId;
	}

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

	public Long getSwipeId() {
		return swipeId;
	}

	public void setSwipeId(Long swipeId) {
		this.swipeId = swipeId;
	}

	public LocalTime getTotalHoursWorked() {
		return totalHoursWorked;
	}

	public void setTotalHoursWorked(LocalTime totalHoursWorked) {
		this.totalHoursWorked = totalHoursWorked;
	}

}
