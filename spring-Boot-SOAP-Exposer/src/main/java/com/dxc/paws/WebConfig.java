package com.dxc.paws;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.csc.fsg.bpo.avutil.AVFileLoader;
import com.csc.fsg.bpo.avutil.AVMapper;

@Configuration
@ComponentScan(basePackages = "com.dxc.paws")
public class WebConfig {

	private static final Logger logger = Logger.getLogger(WebConfig.class);
	
	@Bean
	public AVMapper getUniaAvMapper(@Value("${avFolderResource}") String avFolderResource) {

		logger.info("AvMapperFactory folder["+avFolderResource+"]");
		
		AVMapper avMapper = new AVMapper();
		AVFileLoader loader = new AVFileLoader();
		loader.setAvFolder(avFolderResource);
		loader.init();
		avMapper.setLoader(loader);
        
		return avMapper;
	}

}
