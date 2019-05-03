package com.nurse.nitisha.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nurse.nitisha.dao.NurseDAO;
import com.nurse.nitisha.model.Nurse;

@RestController
@RequestMapping("/task")
public class NurseController {
	
	@Autowired
	NurseDAO nurseDAO;
	
	/*save nurse to database*/
	
	@PostMapping("/nurse")
	public Nurse createNurse(@Valid @RequestBody Nurse nurse){
		return nurseDAO.save(nurse);
	}

	/* get all nurses*/
	@GetMapping("/nurse")	
	public List<Nurse> getAllNurses(){
		return nurseDAO.findAll();
	}
	
	/*get nurse with an id*/
	@GetMapping("/nurse/{id}")
	public ResponseEntity<Nurse>getNurseById(@PathVariable(value = "id") Long nurseId){
		Nurse nurse = nurseDAO.findOne(nurseId);
		if(nurse == null){
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(nurse);
		
	}
	/*update an nurse by nurseId*/
		
	@PutMapping("/nurse/{id}")
	public ResponseEntity<Nurse> updateNurse(@PathVariable(value = "id") Long nurseId , @Valid @RequestBody Nurse nurseDetails) {
		Nurse nurse = nurseDAO.findOne(nurseId);
		if(nurse == null){
			return ResponseEntity.notFound().build();
		}
		nurse.setPassword(nurseDetails.getPassword());
		//nurse.setSupervisorId(nurseDetails.());
		nurse.setUsername(nurseDetails.getUsername());
		
		Nurse updateNurse = nurseDAO.save(nurse);
		return ResponseEntity.ok().body(updateNurse);
		
	}
	
	/*Delete an Nurse*/
	@DeleteMapping("/nurse/{id}")
	public ResponseEntity<Nurse> deleteEmployee(@PathVariable(value = "id") Long nurseId){
		Nurse nurse = nurseDAO.findOne(nurseId);
		if(nurse == null){
			return ResponseEntity.notFound().build();
		}
		nurseDAO.delete(nurse);
		return ResponseEntity.ok().build();
	}

}