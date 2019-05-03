package com.nurse.nitisha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nurse.nitisha.model.Department;
import com.nurse.nitisha.model.Nurse;
import com.nurse.nitisha.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long>{

	List<Task> getByDepartment(Department department);
	List<Task> getByDepartmentAndNurse(Department department, Nurse nurse);
	List<Task> getByNurse(Nurse nurse);
	List<Task> getById(long id);
	
}
