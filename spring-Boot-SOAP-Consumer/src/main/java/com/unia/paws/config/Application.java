
package com.unia.paws.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import PAWS.wsdl.GetPolicyResponse;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner lookup(PolicyClient policyClient) {
		return args -> {
			String policyNumber = "0003649296";
            
			
			if (args.length > 0) {
				policyNumber = args[0];
			}
			
			GetPolicyResponse response = policyClient.getPolicy(policyNumber);
			System.err.println(response.getStatus());
			System.err.println(response.getPolicyDetail().getOwnerOrganizationName());
		};
	}

}
