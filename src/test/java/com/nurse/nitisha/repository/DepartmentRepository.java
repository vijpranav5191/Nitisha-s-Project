package com.nurse.nitisha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.nurse.nitisha.model.Department;
import com.nurse.nitisha.model.Nurse;

public interface DepartmentRepository extends JpaRepository<Department, Long>{

	List<Department> getByDepartmentName(String departmentName);
}