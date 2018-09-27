package com.dxc.paws.service;

import org.springframework.stereotype.Service;

import com.dxc.paws.entities.LkHostSystem;

@Service
public interface AvService {

	
	String setAvSystem(LkHostSystem hostSystem);

	String getHostValue(String domainName, Object internalValue);
    
	String getDescription(String domainName,Object internalValue);

	Short getRegionId(String region);
}
