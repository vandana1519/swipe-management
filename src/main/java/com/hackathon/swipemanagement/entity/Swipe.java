package com.hackathon.swipemanagement.entity;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Swipe")
public class Swipe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long swipeId;
	private LocalDateTime swipeIn;
	private LocalDateTime swipeOut;

	private Long hoursWorked;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "swipe_employee", referencedColumnName = "employeeId")
	private Employees employee;

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

	public Long getHoursWorked() {
		return hoursWorked;
	}

	public void setHoursWorked(Long hoursWorked) {
		this.hoursWorked = hoursWorked;
	}

	public Facility getFacility() {
		return facility;
	}

	public Employees getEmployee() {
		return employee;
	}

	public void setEmployee(Employees employee) {
		this.employee = employee;
	}

	public void setFacility(Facility facility) {
		this.facility = facility;
	}

}
