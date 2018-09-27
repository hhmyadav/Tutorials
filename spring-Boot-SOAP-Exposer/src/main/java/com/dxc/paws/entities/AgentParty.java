package com.dxc.paws.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AGENT_PARTY", schema = "CYBERLIFE")
public class AgentParty implements java.io.Serializable {

	private static final long serialVersionUID = -5738296304227181211L;

	private String clientId;
	private String searchFirstName;
	private String searchLastName;
	private String bpoAgentNumber;

	@Id
	@Column(name = "CLIENT_ID", unique = true, nullable = false, length = 40)
	public String getClientId() {
		return this.clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
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

	@Column(name = "BPO_AGENT_NUMBER", length = 20)
	public String getBpoAgentNumber() {
		return this.bpoAgentNumber;
	}

	public void setBpoAgentNumber(String bpoAgentNumber) {
		this.bpoAgentNumber = bpoAgentNumber;
	}

}
