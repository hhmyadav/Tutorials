package com.dxc.paws.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CLIENT_ADDRESS", schema = "CLIENT")
public class ClientAddress implements java.io.Serializable {

	private static final long serialVersionUID = 6459208622317526184L;

	private Address address;
  
	@Id
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "ADDRESS_ID", insertable = false, updatable = false)
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
