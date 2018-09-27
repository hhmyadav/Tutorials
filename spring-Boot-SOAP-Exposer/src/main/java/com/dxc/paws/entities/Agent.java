package com.dxc.paws.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "AGENT", schema = "CYBERLIFE", uniqueConstraints = {

		@UniqueConstraint(columnNames = { "AGENT_NUMBER"}) })
public class Agent implements java.io.Serializable {

	private static final long serialVersionUID = -3654824651565698943L;

	private String clientId;
	private String agentNumber;
	private String contractId;

	private AgentParty agentParty;

	@Id
	@Column(name = "CLIENT_ID", nullable = false, length = 40)
	public String getClientId() {
		return this.clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	@Column(name = "AGENT_NUMBER", nullable = false, length = 20)
	public String getAgentNumber() {
		return this.agentNumber;
	}

	public void setAgentNumber(String agentNumber) {
		this.agentNumber = agentNumber;
	}

	@Column(name = "CONTRACT_ID")
	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	@OneToOne(fetch = FetchType.EAGER)
	@PrimaryKeyJoinColumn
	public AgentParty getAgentParty() {
		return agentParty;
	}

	public void setAgentParty(AgentParty agentParty) {
		this.agentParty = agentParty;
	}

}
