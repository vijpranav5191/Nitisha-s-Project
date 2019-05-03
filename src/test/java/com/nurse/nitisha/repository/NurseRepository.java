package com.nurse.nitisha.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.nurse.nitisha.model.Nurse;

public interface NurseRepository extends JpaRepository<Nurse, Long>{

	List<Nurse> getByUsernameAndPassword(String username, String password);
}

