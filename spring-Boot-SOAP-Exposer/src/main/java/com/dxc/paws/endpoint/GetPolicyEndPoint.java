package com.dxc.paws.endpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.dxc.paws.response.GetPolicyRequest;
import com.dxc.paws.response.GetPolicyResponse;
import com.dxc.paws.response.PolicyDetail;
import com.dxc.paws.service.GetPolicyService;
import com.dxc.paws.service.UtilityService;

@Endpoint
public class GetPolicyEndPoint {
	private static final Logger logger = LoggerFactory.getLogger(GetPolicyEndPoint.class);
	private static final String NAMESPACE_URI = "http://response.paws.dxc.com/";

	@Autowired
	GetPolicyService policyService;

	@Autowired
	UtilityService utilService;

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPolicyRequest")
	@ResponsePayload
	public GetPolicyResponse getPolicy(@RequestPayload GetPolicyRequest request) throws Exception {

		logger.info("GetPolicyRequest Arrived. Below are the  request parameters");
  
		GetPolicyResponse policyResponse = new GetPolicyResponse();
		PolicyDetail policyDetail = null;
		
		
		try
		{   
			 utilService.requestLogging(request); // logging request parameters
			
			 policyDetail = policyService.getPolicyDetailByRequest(request);
			 if(policyDetail == null)
				 throw new Exception();
			 policyResponse = (GetPolicyResponse) policyService.setSuccessStatus(policyResponse);   // Success or Fail Status
		}
        catch (Exception e) 
		{
        	policyResponse = (GetPolicyResponse) policyService.setFailedStatus(policyResponse,e);  // Failed Status
        	return policyResponse;
        }
		
		policyResponse.setPolicyDetail(policyDetail);

		return policyResponse;
	}

}
