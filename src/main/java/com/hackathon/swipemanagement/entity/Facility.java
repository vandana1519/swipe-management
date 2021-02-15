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
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Facility")
public class Facility {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long facilityId;
	private String facilityName;
	private String facilityLocation;
	private String facilityCity;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "employee_id", referencedColumnName = "employeeId")
	private List<Employees> employee;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "swipe_id", referencedColumnName = "swipeId")
	private Swipe swipe;

	public List<Employees> getEmployee() {
		return employee;
	}

	public void setEmployee(List<Employees> employee) {
		this.employee = employee;
	}

	public Swipe getSwipe() {
		return swipe;
	}

	public void setSwipe(Swipe swipe) {
		this.swipe = swipe;
	}

	public Long getFacilityId() {
		return facilityId;
	}

	public void setFacilityId(Long facilityId) {
		this.facilityId = facilityId;
	}

	public String getFacilityName() {
		return facilityName;
	}

	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
	}

	public String getFacilityLocation() {
		return facilityLocation;
	}

	public void setFacilityLocation(String facilityLocation) {
		this.facilityLocation = facilityLocation;
	}

	public String getFacilityCity() {
		return facilityCity;
	}

	public void setFacilityCity(String facilityCity) {
		this.facilityCity = facilityCity;
	}

}
