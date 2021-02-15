package com.hackathon.swipemanagement.repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hackathon.swipemanagement.entity.Swipe;

@Repository
public interface SwipeRepository extends JpaRepository<Swipe, Long> {

	@Query(value = "select * from swipe s where s.swipe_employee= :employeeId and s.facility_swipe= :facilityId", nativeQuery = true)
	public Swipe getSwipeDetails(Long employeeId, Long facilityId);
	
	@Query(value = "select * from swipe s where s.swipe_employee= :employeeId", nativeQuery = true)
	public List<Swipe> getSwipeDetailsList(Long employeeId);
	


}
