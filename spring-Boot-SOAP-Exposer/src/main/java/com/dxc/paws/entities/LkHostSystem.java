package com.dxc.paws.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "LK_HOST_SYSTEM", schema = "BPOCLIENTEXT", uniqueConstraints = @UniqueConstraint(columnNames = "HOST_SYSTEM"))
public class LkHostSystem implements java.io.Serializable {


	private static final long serialVersionUID = -409161742962408026L;
	private int lkHostSystemId;
	private String hostSystem;

	public LkHostSystem() {
	}

	public LkHostSystem(int lkHostSystemId, String hostSystem) {
		this.lkHostSystemId = lkHostSystemId;
		this.hostSystem = hostSystem;
	}

	@Id
	@Column(name = "LK_HOST_SYSTEM_ID", unique = true, nullable = false)
	public int getLkHostSystemId() {
		return this.lkHostSystemId;
	}

	public void setLkHostSystemId(int lkHostSystemId) {
		this.lkHostSystemId = lkHostSystemId;
	}

	@Column(name = "HOST_SYSTEM", unique = true, nullable = false, length = 32)
	public String getHostSystem() {
		return this.hostSystem;
	}

	public void setHostSystem(String hostSystem) {
		this.hostSystem = hostSystem;
	}

}
