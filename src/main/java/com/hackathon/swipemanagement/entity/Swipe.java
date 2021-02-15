package com.hackathon.swipemanagement.entity;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Swipe")
public class Swipe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long swipeId;
	private LocalDateTime swipeIn;
	private LocalDateTime swipeOut;
	private LocalTime hoursWorked;

	@OneToMany()
	@JoinColumn(name = "employee_swipe", referencedColumnName = "employeeId")
	private List<Employees> employee;

	@OneToOne()
	@JoinColumn(name = "facility_swipe", referencedColumnName = "facilityId")
	private Facility facility;

	public Long getSwipeId() {
		return swipeId;
	}

	public void setSwipeId(Long swipeId) {
		this.swipeId = swipeId;
	}

	public LocalDateTime getSwipeIn() {
		return swipeIn;
	}

	public void setSwipeIn(LocalDateTime swipeIn) {
		this.swipeIn = swipeIn;
	}

	public LocalDateTime getSwipeOut() {
		return swipeOut;
	}

	public void setSwipeOut(LocalDateTime swipeOut) {
		this.swipeOut = swipeOut;
	}

	public LocalTime getHoursWorked() {
		return hoursWorked;
	}

	public void setHoursWorked(LocalTime hoursWorked) {
		this.hoursWorked = hoursWorked;
	}

	public List<Employees> getEmployee() {
		return employee;
	}

	public void setEmployee(List<Employees> employee) {
		this.employee = employee;
	}

	public Facility getFacility() {
		return facility;
	}

	public void setFacility(Facility facility) {
		this.facility = facility;
	}

}
