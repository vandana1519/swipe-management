package com.hackathon.swipemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hackathon.swipemanagement.entity.Facility;

public interface FacilityRepository extends JpaRepository<Facility, Long>{
	
	public Facility findByFacilityId(Long facilityId);

}
