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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name="Facility")
@EntityListeners(AuditingEntityListener.class)

public class Facility {
	public Facility(){}
	
	public Facility(String address, Region region){
		this.address = address;
		this.region = region;
		this.createDate = new Date();
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	@NotBlank
	private String address;
	
	@ManyToOne
	@JoinColumn
	private Region region;
	

	private Date createDate;
	
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public long getId() {
		return id;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
