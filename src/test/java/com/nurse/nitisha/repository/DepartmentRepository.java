package com.nurse.nitisha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.nurse.nitisha.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long>{

}