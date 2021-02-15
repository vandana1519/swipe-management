package com.hackathon.swipemanagement.entity;

import java.util.List;

import javax.persistence.CascadeType;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import javax.persistence.Table;

@Entity
@Table(name = "Employee")
public class Employees {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long employeeId;

	private String employeeName;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Swipe> swipe;

	public List<Swipe> getSwipe() {
		return swipe;
	}

	public void setSwipe(List<Swipe> swipe) {
		this.swipe = swipe;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

}
