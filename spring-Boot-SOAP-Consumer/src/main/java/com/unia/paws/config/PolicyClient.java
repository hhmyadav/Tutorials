
package com.unia.paws.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import PAWS.wsdl.GetPolicyRequest;
import PAWS.wsdl.GetPolicyResponse;

public class PolicyClient extends WebServiceGatewaySupport {

	private static final Logger log = LoggerFactory.getLogger(PolicyClient.class);

	public GetPolicyResponse getPolicy(String policyNumber) {

		GetPolicyRequest request = new GetPolicyRequest();
		request.setPolicyNumber(policyNumber);

		log.info("Requesting policy for " + policyNumber);

		GetPolicyResponse response = (GetPolicyResponse) getWebServiceTemplate()
				.marshalSendAndReceive("http://cscpantss009:8040/unia-paws-awd-0.1.0/ws/PAWS.wsdl", request,
						new SoapActionCallback(
								"http://response.paws.dxc.com"));

		return response;
	}

}
