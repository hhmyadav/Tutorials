package com.dxc.paws.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csc.fsg.bpo.avutil.AVMapper;
import com.dxc.paws.entities.LkHostSystem;

@Service
public class AvServiceImpl implements AvService{
       
	private static final Logger logger = LoggerFactory.getLogger(AvServiceImpl.class);


	@Autowired
	AVMapper avMapper ;
		
	private String avSystem ; 
	 
	@Override
	public String setAvSystem(LkHostSystem hostSystem) 
	{
		
		if (hostSystem == null) {
			logger.warn("LK_HOST_SYSTEM_ID match not found for policyId " + hostSystem);
			avSystem = "-1";
		} else {
			avSystem = hostSystem.getHostSystem();
		}

		logger.debug("AVSystem=" + avSystem);
		
		return avSystem;
	}
	
	@Override
	public Short getRegionId(String region) {
	
		Integer regionId = avMapper.getInternalValue("Region", "wmA", region.toUpperCase());
		Short regionShort = regionId.shortValue();
		
		return regionShort ;
	}
	
	
	@Override
	public String getHostValue(final String domainName,final Object internalValue)
	{
	   if(internalValue == null)
		 return null;
		
		String hostValue;
		if (!domainName.equals("DOMAIN")) 
		{
			Integer internal = new Integer(Integer.parseInt(internalValue.toString()));

		    try {
				hostValue = avMapper.getSystemValue(domainName, avSystem, internal);
			} catch (RuntimeException e) {
				hostValue = null;
			}

		} 
		else 
		 hostValue = "NO HOST VALUE YET";
		
		if (hostValue == null)
			hostValue = "(null)";
		
		return hostValue ;
	}
	
	
	
	@Override
	public String getDescription(String domainName, Object internalValue) {
	
		if (internalValue == null)
			return null;
		String description;
		
		if (!domainName.equals("DOMAIN")) 
		{
			Integer internal = new Integer(Integer.parseInt(internalValue.toString()));

			try {
				description = avMapper.getDescription(domainName, internal);
			} catch (Exception e) {
				description = "NO DESCRIPTION";
			}
		} else 
		{
			description = "NO DESCRIPTION YET";
		}
		
		return description;
	}

	
	
}
