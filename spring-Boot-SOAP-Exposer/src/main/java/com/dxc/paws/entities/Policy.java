package com.dxc.paws.entities;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "POLICY", schema = "CYBERLIFE", uniqueConstraints = { @UniqueConstraint(columnNames = "HOST_KEY") })
public class Policy implements java.io.Serializable {

	private static final long serialVersionUID = 8536567784669240291L;
	private int policyId;
	private short companyId;
	private String policyNumber;
	private short regionId;
	private Integer statusId;
	private BigDecimal faceAmount;
	private Integer productTypeId;
	private String hostKey;
	private LkHostSystem lkHostSystem;
	private String blockInd;

	private Set<Portfolio> roles = new HashSet<Portfolio>(0);
	
	public Policy() {
	}

	@Id
	@Column(name = "POLICY_ID", nullable = false)
	public int getPolicyId() {
		return this.policyId;
	}

	public void setPolicyId(int policyId) {
		this.policyId = policyId;
	}

	@Column(name = "COMPANY_ID", nullable = false)
	public short getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(short companyId) {
		this.companyId = companyId;
	}

	@Column(name = "POLICY_NUMBER", nullable = false, length = 15)
	public String getPolicyNumber() {
		return this.policyNumber;
	}

	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}

	@Column(name = "REGION_ID", nullable = false)
	public short getRegionId() {
		return this.regionId;
	}

	public void setRegionId(short regionId) {
		this.regionId = regionId;
	}

	@Column(name = "STATUS_ID")
	public Integer getStatusId() {
		return this.statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	@Column(name = "FACE_AMOUNT", precision = 14)
	public BigDecimal getFaceAmount() {
		return this.faceAmount;
	}

	public void setFaceAmount(BigDecimal faceAmount) {
		this.faceAmount = faceAmount;
	}

	@Column(name = "PRODUCT_TYPE_ID")
	public Integer getProductTypeId() {
		return this.productTypeId;
	}

	public void setProductTypeId(Integer productTypeId) {
		this.productTypeId = productTypeId;
	}

	@Column(name = "HOST_KEY", unique = true, nullable = false, length = 60)
	public String getHostKey() {
		return this.hostKey;
	}

	public void setHostKey(String hostKey) {
		this.hostKey = hostKey;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "LK_HOST_SYSTEM_ID", nullable = false)
	@NotNull
	public LkHostSystem getLkHostSystem() {
		return lkHostSystem;
	}

	public void setLkHostSystem(LkHostSystem lkHostSystem) {
		this.lkHostSystem = lkHostSystem;
	}

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "BPO_POLICY_ID")
	public Set<Portfolio> getRoles() {
		return roles;
	}

	public void setRoles(Set<Portfolio> roles) {
		this.roles = roles;
	}

	@Column(name = "BLOCK_IND")
	public String getBlockInd() {
		return blockInd;
	}

	public void setBlockInd(String blockInd) {
		this.blockInd = blockInd;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
