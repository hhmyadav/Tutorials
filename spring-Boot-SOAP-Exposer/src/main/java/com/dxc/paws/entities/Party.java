package com.dxc.paws.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PARTY", schema = "CLIENT")
public class Party implements java.io.Serializable {

	private static final long serialVersionUID = 7423919288764906297L;

	private PersonDetail personDetail;
	private Set<ClientAddress> clientAddresses;

	private String clientId;
	private String govId;
	private String searchFirstName;
	private String searchLastName;
	private String searchCompanyName;
	private String bpoPhoneNumber;
	private String emailAddress;

	@Id
	@Column(name = "CLIENT_ID", unique = true, nullable = false, length = 40)
	public String getClientId() {
		return this.clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	@Column(name = "GOV_ID", length = 40)
	public String getGovId() {
		return this.govId;
	}

	public void setGovId(String govId) {
		this.govId = govId;
	}

	@Column(name = "SEARCH_FIRST_NAME", length = 32)
	public String getSearchFirstName() {
		return this.searchFirstName;
	}

	public void setSearchFirstName(String searchFirstName) {
		this.searchFirstName = searchFirstName;
	}

	@Column(name = "SEARCH_LAST_NAME", length = 100)
	public String getSearchLastName() {
		return this.searchLastName;
	}
    
	public void setSearchLastName(String searchLastName) {
		this.searchLastName = searchLastName;
	}

	@Column(name = "SEARCH_COMPANY_NAME", length = 100)
	public String getSearchCompanyName() {
		return this.searchCompanyName;
	}
	
	public void setSearchCompanyName(String searchCompanyName) {
		this.searchCompanyName = searchCompanyName;
	}
	
	@Column(name = "BPO_PHONE_NUMBER", length = 20)
	public String getBpoPhoneNumber() {
		return this.bpoPhoneNumber;
	}

	public void setBpoPhoneNumber(String bpoPhoneNumber) {
		this.bpoPhoneNumber = bpoPhoneNumber;
	}

	@Column(name = "EMAIL_ADDRESS", length = 40)
	public String getEmailAddress() {
		return this.emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	@OneToOne
	@JoinColumn(name = "CLIENT_ID")
	public PersonDetail getPersonDetail() {
		return this.personDetail;
	}

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "CLIENT_ID")
	public Set<ClientAddress> getClientAddresses() {
		return clientAddresses;
	}

	public void setPersonDetail(PersonDetail personDetail) {
		this.personDetail = personDetail;
	}

	public void setClientAddresses(Set<ClientAddress> clientAddresses) {
		this.clientAddresses = clientAddresses;
	}

}
