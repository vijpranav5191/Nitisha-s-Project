package com.nurse.nitisha.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nurse.nitisha.model.Region;
import com.nurse.nitisha.repository.RegionRepository;

@Service
public class RegionDAO {


	@Autowired
	RegionRepository regionRepository;
	
	/* to save nurse */
	
	public Region save(Region region){
		return regionRepository.save(region);
	}
	
	/* to search nurse*/
	

	public List<Region> findAll(){
		return regionRepository.findAll();
	}
	
	/* to get nurse by id*/
	public Region findOne(long regionId){
		return regionRepository.findOne(regionId);
	}
	
	/* to delete nurse by object*/
	public void delete(Region region) {
		regionRepository.delete(region);
	}
}