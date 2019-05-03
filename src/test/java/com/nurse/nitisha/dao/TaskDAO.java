package com.nurse.nitisha.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nurse.nitisha.model.Task;
import com.nurse.nitisha.repository.TaskRepository;


@Service
public class TaskDAO {

	@Autowired
	TaskRepository taskRepository;
	
	/* to save nurse */
	
	public Task save(Task task){
		return taskRepository.save(task);
	}
	
	/* to search nurse*/
	
	public List<Task> findAll(){
		return taskRepository.findAll();
	}
	
	/* to get nurse by id*/
	public Task findOne(long taskId){
		return taskRepository.findOne(taskId);
	}
	
	/* to delete nurse by object*/
	public void delete(Task task) {
		taskRepository.delete(task);
	}
}
