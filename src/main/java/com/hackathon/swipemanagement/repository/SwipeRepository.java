package com.hackathon.swipemanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hackathon.swipemanagement.entity.Swipe;

@Repository
public interface SwipeRepository extends JpaRepository<Swipe, Long> {

	@Query(value = "select * from swipe s where s.swipe_employee= :employeeId and s.facility_swipe= :facilityId", nativeQuery = true)
	public List<Swipe> getSwipeDetails(Long employeeId, Long facilityId);
	
	@Query(value = "select * from swipe s where s.swipe_employee= :employeeId and month(s.swipe_in) = :currentMonth", nativeQuery = true)
	public List<Swipe> getSwipeDetailsListByEmployeeId(Long employeeId, int currentMonth);
	
	
	@Query(value = "select * from swipe s where s.swipe_employee= :employeeId and date(s.swipe_in) = :date", nativeQuery = true)
	public List<Swipe> getSwipeDetailsListByDate(Long employeeId, String date);
	
	@Query(value = "select * from swipe s where s.facility_swipe= :facilityId and date(s.swipe_in) = :date", nativeQuery = true)
	public List<Swipe> getSwipeDetailsListByDateFacility(Long facilityId, String date);

	@Query(value = "select * from swipe s where s.facility_swipe= :facilityId and month(s.swipe_in) = :currentMonth", nativeQuery = true)
	public List<Swipe> getSwipeDetailsListByFacilityId(Long facilityId, int currentMonth);

}
