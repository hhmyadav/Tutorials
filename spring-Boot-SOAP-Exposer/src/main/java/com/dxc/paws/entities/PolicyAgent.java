package com.dxc.paws.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns; //PANFRM-4003
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "POLICY_AGENT", schema = "CYBERLIFE")
public class PolicyAgent implements java.io.Serializable {

	private static final long serialVersionUID = -4533523000655612671L;

	private int policyAgentId;
	private int policyId;
	private Agent agent;

	@Id
	@Column(name = "POLICY_AGENT_ID", unique = true, nullable = false)
	public int getPolicyAgentId() {
		return this.policyAgentId;
	}

	public void setPolicyAgentId(int policyAgentId) {
		this.policyAgentId = policyAgentId;
	}

	@Column(name = "POLICY_ID", nullable = false)
	public int getPolicyId() {
		return this.policyId;
	}

	public void setPolicyId(int policyId) {
		this.policyId = policyId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumns({
			@JoinColumn(name = "AGENT_NUMBER", insertable = false, updatable = false, referencedColumnName = "AGENT_NUMBER"),
			@JoinColumn(name = "CONTRACT_ID", insertable = false, updatable = false, referencedColumnName = "CONTRACT_ID") })
    public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

}
