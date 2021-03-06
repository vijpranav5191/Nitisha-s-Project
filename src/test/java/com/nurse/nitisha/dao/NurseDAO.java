package com.nurse.nitisha.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nurse.nitisha.model.Nurse;
import com.nurse.nitisha.repository.NurseRepository;


@Service
public class NurseDAO {

	@Autowired
	public NurseRepository nurseRepository;
	
	/* to save nurse */
	
	public Nurse save(Nurse nurse){
		return nurseRepository.save(nurse);
	}
	
	/* to search nurse*/
	
	public Nurse getNurseByUserName(String username) {
		for(Nurse nurse: nurseRepository.getByUsername(username)) {
			return nurse;
		}
		return null;
	}
	
	
	public List<Nurse> findAll(){
		return nurseRepository.findAll();
	}
	
	/* to get nurse by id*/
	public Nurse findOne(long nurseId){
		return nurseRepository.findOne(nurseId);
	}
	
	/* to delete nurse by object*/
	public void delete(Nurse nurse) {
		nurseRepository.delete(nurse);
	}
}
