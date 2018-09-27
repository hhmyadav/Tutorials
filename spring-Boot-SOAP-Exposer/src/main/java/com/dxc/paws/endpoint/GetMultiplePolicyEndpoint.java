package com.dxc.paws.endpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.dxc.paws.response.GetMultiplePolicyRequest;
import com.dxc.paws.response.GetMultiplePolicyResponse;
import com.dxc.paws.response.GetMultiplePolicyResponse.PolicyDetails;
import com.dxc.paws.service.GetPolicyService;
import com.dxc.paws.service.UtilityService;

@Endpoint
public class GetMultiplePolicyEndpoint {
	private static final Logger logger = LoggerFactory.getLogger(GetPolicyEndPoint.class);
	private static final String NAMESPACE_URI = "http://response.paws.dxc.com/";

	@Autowired
	GetPolicyService policyService;

	@Autowired
	UtilityService utilService;

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getMultiplePolicyRequest")
	@ResponsePayload
	public GetMultiplePolicyResponse getMultiplePolicy(@RequestPayload GetMultiplePolicyRequest request) throws Exception {

		logger.info("GetMultiplePolicyRequest Arrived. Below are the request parameters");

		utilService.requestLogging(request); // logging request parameters

		GetMultiplePolicyResponse policyResponse = new GetMultiplePolicyResponse();
        PolicyDetails policyDetails = null ;

		
		try
		{
			 policyDetails = policyService.getPolicyDetailsByMultiplePolicyRequest(request);
			 if(policyDetails == null)
				 throw new Exception();
			 policyResponse = (GetMultiplePolicyResponse) policyService.setSuccessStatus(policyResponse);   // Success Status
		}
        catch (Exception e) 
		{
        	policyResponse = (GetMultiplePolicyResponse) policyService.setFailedStatus(policyResponse,e);  // Failed Status
        	return policyResponse;
        }
		
		
		policyResponse.setPolicyDetails(policyDetails);

		return policyResponse;
	}

}
