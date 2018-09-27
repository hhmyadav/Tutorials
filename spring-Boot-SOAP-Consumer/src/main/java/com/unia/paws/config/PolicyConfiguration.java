
package com.unia.paws.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class PolicyConfiguration {

	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		// this package must match the package in the <generatePackage> specified in
		// pom.xml
		marshaller.setContextPath("PAWS.wsdl");
		return marshaller;
	}

	@Bean
	public PolicyClient countryClient(Jaxb2Marshaller marshaller) {
		PolicyClient client = new PolicyClient();
		client.setDefaultUri("http://cscpantss009:8040/unia-paws-awd-0.1.0/ws");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		return client;
	}

}
