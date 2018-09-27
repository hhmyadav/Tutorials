package com.dxc.paws.service;

import org.springframework.stereotype.Service;

import com.dxc.paws.entities.Policy;
import com.dxc.paws.entities.Portfolio;
import com.dxc.paws.response.GetMultiplePolicyRequest;
import com.dxc.paws.response.GetMultiplePolicyResponse.PolicyDetails;
import com.dxc.paws.response.GetPolicyRequest;
import com.dxc.paws.response.PolicyDetail;

@Service
public interface GetPolicyService {

	
	public PolicyDetails getPolicyDetailsByMultiplePolicyRequest(GetMultiplePolicyRequest multiplePolicyRequest) throws Exception;
	
	public PolicyDetail getPolicyDetailByRequest(GetPolicyRequest policyRequest) throws Exception;

	public PolicyDetail setPartyDetails(PolicyDetail policyDetail, Policy policy);

	public PolicyDetail setPartyDetailWithRole(PolicyDetail policyDetail, Portfolio portfolio, String role);

	public PolicyDetail getPolicyDetail(Policy policy) throws Exception;

	Object setSuccessStatus(Object obj);

	Object setFailedStatus(Object obj, Exception e);
	

}
