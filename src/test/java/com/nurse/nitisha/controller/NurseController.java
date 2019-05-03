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
import com.nurse.nitisha.model.Department;
import com.nurse.nitisha.model.Nurse;
import com.nurse.nitisha.model.Task;
import com.nurse.nitisha.objects.AddTaskRequest;
import com.nurse.nitisha.objects.LoginRequest;
import com.nurse.nitisha.repository.DepartmentRepository;
import com.nurse.nitisha.repository.TaskRepository;
import com.nurse.nitisha.response.LoginResponse;
import com.nurse.nitisha.response.TaskResponse;
import com.nurse.nitisha.utils.Utils;

@RestController
@RequestMapping("/nurse")
public class NurseController {
	
	@Autowired
	NurseDAO nurseDAO;
	
	@Autowired
	TaskRepository taskRepository;
	
	@Autowired
	DepartmentRepository departmentRepository;
	
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
	
	@GetMapping("/tasks/{username}/{dept}")
	public TaskResponse getTask(@PathVariable(value = "username") String username, @PathVariable(value = "dept") String dept){
		Nurse nurse = nurseDAO.getNurseByUserName(username);
		Department department = null;
		List<Department> depts = departmentRepository.getByDepartmentName(dept);
		for(Department d: depts) {
			department =  d;
			break;
		}
		List<Task> tasksByNurse = taskRepository.getByNurse(nurse);
		List<Task> tasksByDepartment = taskRepository.getByDepartment(department);
		tasksByNurse.addAll(tasksByDepartment);
		
		TaskResponse taskResponse = new TaskResponse();
		taskResponse.tasks = tasksByNurse;
		taskResponse.nurse = nurse;
		return taskResponse;
	}
	
	@GetMapping("/tasks/{username}")
	public TaskResponse getTask(@PathVariable(value = "username") String username){
		Nurse nurse = nurseDAO.getNurseByUserName(username);
		List<Task> tasksByNurse = taskRepository.getByNurse(nurse);
		TaskResponse taskResponse = new TaskResponse();
		taskResponse.tasks = tasksByNurse;
		taskResponse.nurse = nurse;
		return taskResponse;
	}
	
	@PostMapping("/addItem")
	public Task addTask(@Valid @RequestBody AddTaskRequest addTaskRequest){
		Nurse nurse = nurseDAO.getNurseByUserName(addTaskRequest.username);
		Task task = new Task(addTaskRequest.taskDetails, addTaskRequest.taskType);
		Department department = null;
		List<Department> depts = departmentRepository.getByDepartmentName(addTaskRequest.dept);
		for(Department d: depts) {
			department =  d;
			break;
		}
		task.setNurse(nurse);
		task.setDepartment(department);
		return taskRepository.save(task);
	}
	
	
	@PostMapping("/updateItem")
	public Task updateTask(@Valid @RequestBody AddTaskRequest addTaskRequest){
		Task task = taskRepository.getOne(addTaskRequest.id);
		if(addTaskRequest.username != null) {
			Nurse nurse = nurseDAO.getNurseByUserName(addTaskRequest.username);
			task.setNurse(nurse);
		}
		if(addTaskRequest.taskDetails != null) {
			task.setTaskDetails(addTaskRequest.taskDetails);
		}
		if(addTaskRequest.taskType != null) {
			task.setTaskType(addTaskRequest.taskType);
		}
		return taskRepository.save(task);
	}
	
	@PostMapping("/login")
	public LoginResponse loginNurse(@Valid @RequestBody LoginRequest loginrequest) {
		String password = Utils.genHash(loginrequest.password);
		String username = loginrequest.username;
		
		List<Nurse> nurses = nurseDAO.nurseRepository.getByUsernameAndPassword(username, password);
		for(Nurse nurse: nurses) {
			LoginResponse response = new LoginResponse();
			response.name = nurse.getName();
			response.username = nurse.getUsername();
			response.token = nurse.getToken();
			response.supervisorId = "" + nurse.getSupervisor().getId();
			return response;
		}
		return null;
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