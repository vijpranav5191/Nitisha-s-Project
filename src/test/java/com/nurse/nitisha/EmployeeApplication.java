package com.nurse.nitisha;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.nurse.nitisha.model.Department;
import com.nurse.nitisha.model.Facility;
import com.nurse.nitisha.model.Nurse;
import com.nurse.nitisha.model.Region;
import com.nurse.nitisha.model.Task;
import com.nurse.nitisha.repository.DepartmentRepository;
import com.nurse.nitisha.repository.FacilityRepository;
import com.nurse.nitisha.repository.NurseRepository;
import com.nurse.nitisha.repository.RegionRepository;
import com.nurse.nitisha.repository.TaskRepository;
import com.nurse.nitisha.utils.Utils;

@SpringBootApplication
@EnableJpaAuditing
public class EmployeeApplication{
	
	@Autowired
	DepartmentRepository departmentRepository;
	
	@Autowired
	FacilityRepository facilityRepository;
	
	@Autowired
	NurseRepository nurseRepository;
	
	@Autowired
	RegionRepository regionRepository;
	
	@Autowired
	TaskRepository taskRepository;
	
	
	public static void main(String[] args) {
		SpringApplication.run(EmployeeApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(final RegionRepository regionRepository) {
		return new CommandLineRunner() {
			public void run(String... args) throws Exception {
				Region[] regions = {new Region("DownTown", "LAS VEGAS"),
						new Region("DownTown", "LOS ANGELES"),
						new Region("DownTown", "BUFFALO"),
						new Region("DownTown", "SAN JOSE"),
						new Region("DownTown", "SAN MATEO"),
						new Region("DownTown", "SAN FRANSCISCO"),
						new Region("DownTown", "SAN DIEGO")
						
				};
				Nurse[] supernurses = {
						new Nurse("Nitisha", "niti", "qwerty"),
						new Nurse("Senha", "sneha", "qwerty"),
						new Nurse("Zahil", "zahil", "qwerty"),
						new Nurse("Amit", "amit", "qwerty"),
				};
				Nurse[] nurses = {
						new Nurse("Pranav", "pvij", "qwerty"),
						new Nurse("Manish", "manish", "qwerty"),
						new Nurse("Ayush", "ayush", "qwerty"),
						new Nurse("Pranjal", "pranjal", "qwerty"),
				};
				
				Task[] tasks = {
						new Task("1", "department_task"),
						new Task("1", "custom_task"),
						new Task("1", "department_task"),
						new Task("1", "custom_task"),
						new Task("1", "department_task"),
				};
				
				for(Nurse supnurse: supernurses) {
					nurseRepository.save(supnurse);
					for(Nurse nurse: nurses) {
						nurse.setSupervisor(supnurse);
						nurseRepository.save(nurse);
					}
				}		
				
				for(Region region: regions) {
					Facility[] facilities = {
							new Facility("GMC Medical hospital", region),
							new Facility("Cancer Medical hospital", region),
							new Facility("Heart Medical hospital", region)
					};
					regionRepository.save(region);
					for(Facility facility: facilities) {
						Department[] departments = {
								new Department("Heart",facility),
								new Department("Bone",facility),
								new Department("Ortho",facility),
								new Department("Gyno",facility)
						};
						facilityRepository.save(facility);
						for(Department department: departments) {
							department.setFacility(facility);
							departmentRepository.save(department);
							for(Task task: tasks) {
								task.setNurse(nurses[1]);
								task.setDepartment(department);
								taskRepository.save(task);
							}
						}
					}
				}
			}
		};
	}
}





