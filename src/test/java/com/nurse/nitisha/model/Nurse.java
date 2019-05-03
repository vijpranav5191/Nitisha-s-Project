package com.nurse.nitisha.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.nurse.nitisha.utils.Utils;

@Entity
@Table(name="Nurse")
@EntityListeners(AuditingEntityListener.class)
public class Nurse {
	
	public Nurse(){}
	
	public Nurse(String name, String username, String password){
		this.name = name;
		this.username = username;
		this.password = Utils.genHash(password);
		this.token = Utils.genHash(username + password);
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@NotBlank
	private String name;
	
	@NotBlank
	private String username;
	
	@NotBlank
	private String password;
	
	@NotBlank
	private String token;
	
	@ManyToOne
	@JoinColumn
	private Nurse supervisor;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date createDate;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Nurse getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(Nurse supervisor) {
		this.supervisor = supervisor;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}	
	
}

