package com.nurse.nitisha.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nurse.nitisha.model.Facility;
import com.nurse.nitisha.repository.FacilityRepository;

@Service
public class FacilityDAO {
	@Autowired
	FacilityRepository facilityRepository;
	
	/* to save nurse */
	
	public Facility save(Facility facility){
		return facilityRepository.save(facility);
	}
	
	/* to search nurse*/
	
	public List<Facility> findAll(){
		return facilityRepository.findAll();
	}
	
	/* to get nurse by id*/
	public Facility findOne(long facilityId){
		return facilityRepository.findOne(facilityId);
	}
	
	/* to delete nurse by object*/
	public void delete(Facility facility) {
		facilityRepository.delete(facility);
	}
}
