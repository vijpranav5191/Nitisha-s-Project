package com.nurse.nitisha.repository;

import com.nurse.nitisha.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
