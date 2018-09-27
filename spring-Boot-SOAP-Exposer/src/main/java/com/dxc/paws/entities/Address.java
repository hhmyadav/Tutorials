package com.dxc.paws.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="ADDRESS",schema = "CLIENT")
public class Address implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ADDRESS_ID", unique = true, nullable = false, length = 40)
	private String addressId;
 
	@Column(name = "CITY", length = 100)
	private String city;
  	
	@Column(name = "LINE1", length = 100)
	private String line1;
    
	@Column(name = "LINE2", length = 100)
	private String line2;
 
	@Column(name = "LINE3", length = 100)
	private String line3;
    
	@Column(name = "LINE4", length = 100)
	private String line4;

	@Column(name="STATE")
	private Integer state;
  
	@Column(name = "ZIP", length = 32)
	private String zip;

	public Address() {
	}

	public String getAddressId() {
		return addressId;
	}

	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}


	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}


	public String getLine1() {
		return line1;
	}

	public void setLine1(String line1) {
		this.line1 = line1;
	}

	public String getLine2() {
		return line2;
	}

	public void setLine2(String line2) {
		this.line2 = line2;
	}

	public String getLine3() {
		return line3;
	}

	public void setLine3(String line3) {
		this.line3 = line3;
	}

	public String getLine4() {
		return line4;
	}

	public void setLine4(String line4) {
		this.line4 = line4;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}


	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	

}