package com.dxc.paws;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;


@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {
	
	@Bean
	public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		servlet.setApplicationContext(applicationContext);
		servlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean(servlet, "/ws/*");
	}


	@Bean(name = "PAWS")
	public DefaultWsdl11Definition ecvsService() throws Exception {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("PAWSPort");
		wsdl11Definition.setLocationUri("/ws");
		wsdl11Definition.setTargetNamespace("http://response.paws.dxc.com/");
		wsdl11Definition.setServiceName("PAWSWebService");
		wsdl11Definition.setSchema(getUniaPawsSchema());
		
		return wsdl11Definition;
	}
	
    @Bean
	public XsdSchema getUniaPawsSchema() {
		return new SimpleXsdSchema(new ClassPathResource("uniaServices.xsd"));
	}
    
  
  
   
}
