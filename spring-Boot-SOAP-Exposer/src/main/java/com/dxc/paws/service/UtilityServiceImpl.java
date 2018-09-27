package com.dxc.paws.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.dxc.paws.response.GetMultiplePolicyRequest;
import com.dxc.paws.response.GetPolicyRequest;

@Service
public class UtilityServiceImpl implements UtilityService {

	private static final Logger logger = LoggerFactory.getLogger(UtilityServiceImpl.class);

   

	@Override
	public void requestLogging(Object request) {
           
		
		if (request instanceof GetPolicyRequest) {
			logger.info("----- Request For Single Policy -----");

			GetPolicyRequest policyRequest = (GetPolicyRequest)request ;
			
			logger.info(UtilityService.isNullOrEmpty(policyRequest.getPolicyNumber())? "" :"PolicyNumber: " + policyRequest.getPolicyNumber());
			logger.info(UtilityService.isNullOrEmpty(policyRequest.getPolicyId()) ? "" :"PolicyId: " + policyRequest.getPolicyId());
			logger.info(UtilityService.isNullOrEmpty(policyRequest.getRegion()) ? "" :"Region: " + policyRequest.getRegion());
			logger.info(UtilityService.isNullOrEmpty(policyRequest.getCallingApplication()) ?"" : "CallingAppliactions: " + policyRequest.getCallingApplication());
		
		} else if (request instanceof GetMultiplePolicyRequest) {
			logger.info("----- Request For Multiple Policy -----");
			
			GetMultiplePolicyRequest multiplePolicyRequest = (GetMultiplePolicyRequest)request ;
			
			logger.info(UtilityService.isNullOrEmpty(multiplePolicyRequest.getPolicyNumber()) ?"" : "PolicyNumber: " + multiplePolicyRequest.getPolicyNumber());
			logger.info(UtilityService.isNullOrEmpty(multiplePolicyRequest.getFirstName()) ?"" : "FirstName: " + multiplePolicyRequest.getFirstName());
			logger.info(UtilityService.isNullOrEmpty(multiplePolicyRequest.getLastName()) ?"" : "LastName: " + multiplePolicyRequest.getLastName());
			logger.info(UtilityService.isNullOrEmpty(multiplePolicyRequest.getRegion()) ?"" : "Region: " + multiplePolicyRequest.getRegion());
			logger.info(UtilityService.isNullOrEmpty(multiplePolicyRequest.getSsn()) ?"" : "SSN: " + multiplePolicyRequest.getSsn());
			logger.info(UtilityService.isNullOrEmpty(multiplePolicyRequest.getCallingApplication())? "" : "Calling Application: " + multiplePolicyRequest.getCallingApplication());
		}
	}

}
