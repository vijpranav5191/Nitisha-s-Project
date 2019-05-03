package com.nurse.nitisha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.nurse.nitisha.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long>{

}
