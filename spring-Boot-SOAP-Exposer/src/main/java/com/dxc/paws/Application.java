package com.dxc.paws;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories
@PropertySource(value="classpath:${fileName}")
public class Application extends SpringBootServletInitializer {

	private static final Logger logger = LoggerFactory.getLogger(Application.class);
      
	 public static String ENVIRONMENT;

	 @Value("${environment}")
	  public void setEnvironment(String environment) {
	  ENVIRONMENT = environment;
	    }
	
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    	
    	return application.sources(Application.class);
    }

   public static void main(String[] args) throws Exception {
       SpringApplication.run(Application.class, args);
         
       logger.info("--Environment-- : " + ENVIRONMENT);
       logger.info("-----Application Started------");
      
     }
   
   
   
   
}