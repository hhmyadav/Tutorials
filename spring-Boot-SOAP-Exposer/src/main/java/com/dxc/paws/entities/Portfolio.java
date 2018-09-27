package com.dxc.paws.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "PORTFOLIO", schema = "CLIENT", uniqueConstraints = @UniqueConstraint(columnNames = { "CLIENT_ID",
		"PARTICIPATION_ID", "CLIENT_CONTRACT_ID" }))
public class Portfolio implements java.io.Serializable {

	
	private static final long serialVersionUID = -13704970475539210L;
	
	private String clientContractId;
	private String clientId;
    private int participationId;
	private String contractId;
    
	private Party party;
    private Agent agent;
    
    
    

	@Id
	@Column(name = "CLIENT_CONTRACT_ID", unique = true, nullable = false, length = 40)
	public String getClientContractId() {
		return this.clientContractId;
	}

	public void setClientContractId(String clientContractId) {
		this.clientContractId = clientContractId;
	}

	@Column(name = "CLIENT_ID", nullable = false, length = 40)
	public String getClientId() {
		return this.clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	@Column(name = "PARTICIPATION_ID", nullable = false)
	public int getParticipationId() {
		return this.participationId;
	}

	public void setParticipationId(int participationId) {
		this.participationId = participationId;
	}

	@Column(name = "CONTRACT_ID", nullable = false, length = 40)
	public String getContractId() {
		return this.contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
    
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CLIENT_ID", insertable = false, updatable = false)
	@NotFound(action = NotFoundAction.IGNORE)
	public Party getParty() {
		return party;
	}

	public void setParty(Party party) {
		this.party = party;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumns({
			@JoinColumn(name = "AGENT_NUMBER", insertable = false, updatable = false, referencedColumnName = "AGENT_NUMBER"),
			@JoinColumn(name = "AGENT_CONTRACT_ID", insertable = false, updatable = false, referencedColumnName = "CONTRACT_ID") })
	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

}
