package com.nurse.nitisha.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nurse.nitisha.model.Department;
import com.nurse.nitisha.repository.DepartmentRepository;

@Service
public class DepartmentDAO {

	@Autowired
	DepartmentRepository departmentRepository;
	
	/* to save nurse */
	
	public Department save(Department department){
		return departmentRepository.save(department);
	}
	
	public Department getDepartment(String dept){
		List<Department> depts = departmentRepository.getByDepartmentName(dept);
		for(Department d: depts) {
			return d;
		}
		return null;
	}
	
	/* to search nurse*/
	
	public List<Department> findAll(){
		return departmentRepository.findAll();
	}
	
	/* to get nurse by id*/
	public Department findOne(long departmentId){
		return departmentRepository.findOne(departmentId);
	}
	
	/* to delete nurse by object*/
	public void delete(Department department) {
		departmentRepository.delete(department);
	}
}
