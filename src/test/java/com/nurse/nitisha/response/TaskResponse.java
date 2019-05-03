package com.nurse.nitisha.response;

import java.util.List;

import com.nurse.nitisha.model.Department;
import com.nurse.nitisha.model.Nurse;
import com.nurse.nitisha.model.Task;

public class TaskResponse {
	public List<Task> tasks;
	public Nurse nurse;
	public Department department;
}
