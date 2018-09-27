package com.dxc.paws.service;

import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.NonUniqueResultException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dxc.paws.entities.Address;
import com.dxc.paws.entities.AgentParty;
import com.dxc.paws.entities.ClientAddress;
import com.dxc.paws.entities.Party;
import com.dxc.paws.entities.PersonDetail;
import com.dxc.paws.entities.Policy;
import com.dxc.paws.entities.Portfolio;
import com.dxc.paws.repository.PolicyRepository;
import com.dxc.paws.response.GetMultiplePolicyRequest;
import com.dxc.paws.response.GetMultiplePolicyResponse;
import com.dxc.paws.response.GetMultiplePolicyResponse.PolicyDetails;
import com.dxc.paws.response.GetPolicyRequest;
import com.dxc.paws.response.GetPolicyResponse;
import com.dxc.paws.response.PolicyDetail;

@Service
@Transactional
public class GetPolicyServiceImpl implements GetPolicyService {

 @Autowired	
 PolicyRepository policyRepository ;	
 
 @Autowired
 AvService avService ;
 
 @Autowired
 UtilityService utilService ;
 
	private static final Logger logger = LoggerFactory.getLogger(GetPolicyServiceImpl.class);


	  @Override
	  public PolicyDetails getPolicyDetailsByMultiplePolicyRequest(GetMultiplePolicyRequest multiplePolicyRequest) throws Exception
	  {
		  
		  logger.info("------ getPolicyDetailsByMultiplePolicyRequest(GetMultiplePolicyRequest multiplePolicyRequest) ------ Started ------");
		  
		 String firstName = multiplePolicyRequest.getFirstName();
		 String lastName = multiplePolicyRequest.getLastName();
		 String policyNumber =  multiplePolicyRequest.getPolicyNumber();
		 String ssn = multiplePolicyRequest.getSsn();
		  
		  PolicyDetails policyDetails = new PolicyDetails();
		  
		  Policy policy = null;
			
		  
		  List <PolicyDetail> listPolicyDetail =  policyDetails.getPolicyDetail();
		  List<Policy> listPolicy ;
		  
		  
		  if (!UtilityService.isNullOrEmpty(policyNumber)) 
		  {
				if(policyNumber.contains("*")){
					policyNumber = policyNumber.substring(0, policyNumber.length() - 1);
					listPolicy =  policyRepository.findByLikePolicyNumber(policyNumber);
				}
				else
				 listPolicy = policyRepository.findByPolicyNumber(policyNumber.toUpperCase());
		  } 
		  else if (!UtilityService.isNullOrEmpty(ssn)) 
			listPolicy = policyRepository.findBySSN(ssn);
		  else if (!UtilityService.isNullOrEmpty(firstName) && !UtilityService.isNullOrEmpty(lastName)) 
			listPolicy = policyRepository.findByFirstNameAndLastName(firstName,lastName);
		  else 
			return null;
			
            
		    if (listPolicy.size() == 0) 
				return null;
			else 
			{
				Iterator<Policy> it = listPolicy.iterator();
				while (it.hasNext()) {
					policy = (Policy) it.next();
					PolicyDetail policyDetail = getPolicyDetail(policy);
					if (policyDetail != null)
						 listPolicyDetail.add(policyDetail);
				}
			 }
		 
		  logger.info("------ getPolicyDetailsByMultiplePolicyRequest(GetMultiplePolicyRequest multiplePolicyRequest) ------ Finished ------");
		  
	      return policyDetails;
		  
	  }
	  
	  @Override
	  public PolicyDetail getPolicyDetailByRequest(GetPolicyRequest policyRequest) throws Exception 
	  {
		  logger.info("------ getPolicyDetailByRequest(GetPolicyRequest policyRequest) ------ Started ------");
		  
		   String policyNumber = policyRequest.getPolicyNumber();
		   String policyIdStr = policyRequest.getPolicyId();
		   String region = policyRequest.getRegion();
		 
		   Short regionId = null ; 
		   Integer policyId = null ;
		   
		   
		  PolicyDetail policyDetail = null;
		  List<Policy> listPolicy = null ;
		  Policy policy = null ;
		  
		 
		  if(!UtilityService.isNullOrEmpty(region))
			 regionId = avService.getRegionId(region);
		  else
		   logger.warn("Entered Region : null Or Empty");
		
		  
		 if(!UtilityService.isNullOrEmpty(policyIdStr))
		 {  
			 if(UtilityService.isInteger(policyIdStr))
			 policyId = Integer.valueOf(policyIdStr);
			 else
			 {  
				 logger.error("Entered PolicyId is not Integer : " + policyIdStr);
				 throw new Exception("Error, Invalid Policy Id ! PolicyId should be numeric only");
			 }
		  }
		 
		
		    if(regionId!=null && !UtilityService.isNullOrEmpty(policyNumber)) 
		    {
    	       listPolicy = policyRepository.findByPolicyNumberAndRegionId(policyNumber, regionId);
			
    	      if (listPolicy.size() > 1) {
    				logger.warn("More than one resulting policy with policyNumber=" + policyNumber + "   And RegionId = " + regionId);
    				throw new NonUniqueResultException("result returns more than one elements");
    			} else if(listPolicy.size() < 1) {
    				logger.warn("No Result Found For PolicyNumber = " + policyNumber + "   And RegionId = " + regionId);
    				return null ;
    			} else
    				policy = (Policy) listPolicy.get(0);
    		}
		    else if(!UtilityService.isNullOrEmpty(policyNumber))
		    {
		    	listPolicy = policyRepository.findByPolicyNumber(policyNumber);
				
                if (listPolicy.size() > 1) {
    				logger.warn("More than one resulting policy with policyNumber=" + policyNumber);
    				throw new NonUniqueResultException("result returns more than one elements");
    			} else if(listPolicy.size() < 1) {
    				logger.warn("No Result Found For PolicyNumber=" + policyNumber);
    				return null ;
    			}
    			else
    				policy = (Policy) listPolicy.get(0);
		         
		    }
		    else if (policyId != null && policyId!= 0) 
		      	policy = policyRepository.findOne(policyId);
		      
		     
		    if(policy==null)
		    	 return null ;
		   
		     policyDetail = getPolicyDetail(policy);
		    
		 logger.info("------ getPolicyDetailByRequest(GetPolicyRequest policyRequest) ------ Finished ------");
		 
		 return policyDetail;
		  
	  }
	 
		   
	@Override
	public PolicyDetail getPolicyDetail(Policy policy) throws Exception {

		logger.debug("------ getPolicyDetail(Policy policy) ------ Started ------");
		
		
		PolicyDetail policyDetail = new PolicyDetail();
		
		
		avService.setAvSystem(policy.getLkHostSystem());
		
		
		policyDetail.setRegionCode(avService.getHostValue("Region",new Short(policy.getRegionId())));         // RegionCode
		
		policyDetail.setCompanyCode(avService.getHostValue("CompanyCode", new Short(policy.getCompanyId()))); //CompanyCode
		
		if(policy.getStatusId()!=null)
		policyDetail.setPolicyStatus(avService.getHostValue("PolicyStatus", policy.getStatusId()));           // PolicyStatus
		
		if(policy.getProductTypeId()!= null)
		{
			policyDetail.setProductCode(avService.getHostValue("PolicyProductCode", policy.getProductTypeId()));  // ProductCode
		
			String productCodeDescription = avService.getDescription("PolicyProductCode",policy.getProductTypeId());
		     
			if(productCodeDescription.toUpperCase().equals("TRADITIONAL"))
			{
				policyDetail.setFixedOrVariable("Fixed");                                                         // Fixed Or Variable
				policyDetail.setLifeOrAnnuity("Life");                                                            // Life Or Annuity
			}
			else
			{
				int index = productCodeDescription.indexOf(" ");
				
				String fixedOrVariable = productCodeDescription.substring(0,index);
				String lifeOrAnnuity = productCodeDescription.substring(index+1);
				
				policyDetail.setFixedOrVariable(fixedOrVariable);                                                 // Fixed Or Variable
				policyDetail.setLifeOrAnnuity(lifeOrAnnuity);                                                     // Life Or Annuity
			}
		}
		
		
		
		if(policy.getFaceAmount()!=null)
		policyDetail.setFaceAmount(policy.getFaceAmount() == null ? "":policy.getFaceAmount().toString());   // FaceAmount
			
		
		policyDetail = setPartyDetails(policyDetail, policy);   // Insured , Agent , Owner Details
		
		
		if(policy.getBlockInd()!=null)
		policyDetail.setBlockIndicator(policy.getBlockInd() == null ? "":policy.getBlockInd());  //Block Indicator 
	
		policyDetail.setParticipantNumber("");        
        policyDetail.setGroupNumber("");
        policyDetail.setGovernmentIndicator("");
      
		
        logger.info("------ getPolicyDetail(Policy policy) ------ Finished ------");
		
		return policyDetail;
	}
	
		
	
	
	@Override
	public PolicyDetail setPartyDetails(PolicyDetail policyDetail , Policy policy)
	{  
		
		logger.debug("------ setPartyDetails(PolicyDetail policyDetail , Policy policy) ------ Started ------");
		
		
		boolean insuredDone = false ;
		boolean agentDone = false ;
		boolean ownerDone = false ;
		
		  	 
		for (Portfolio portfolio : policy.getRoles()) 
        {
			String role = avService.getHostValue("RelationRoleCode",new Integer(portfolio.getParticipationId()));
			String roleDescription = avService.getDescription("RelationRoleCode",new Integer(portfolio.getParticipationId()));
			
			if( !insuredDone && (role.toUpperCase().equals("INS") || role.toUpperCase().equals("PAR") || roleDescription.toUpperCase().equals("INSURED") || roleDescription.toUpperCase().equals("ANNUITANT")))
			{
				policyDetail = setPartyDetailWithRole(policyDetail, portfolio, "INSURED");
				insuredDone = true ;
			}
			else if(!agentDone && (role.toUpperCase().equals("AGENT") || roleDescription.toUpperCase().equals("AGENT")) || portfolio.getParticipationId()==37 )
			{
				policyDetail = setPartyDetailWithRole(policyDetail, portfolio,"AGENT");
				agentDone = true ;
			}
			else if(!ownerDone && (role.toUpperCase().equals("OWN") || roleDescription.toUpperCase().equals("OWNER")) )
			{
				policyDetail = setPartyDetailWithRole(policyDetail, portfolio ,"OWNER");
				ownerDone = true ;
			}
			
        }
		
		logger.debug("------ setPartyDetails(PolicyDetail policyDetail , Policy policy) ------ Finished ------");
		
		
		return policyDetail;
	}
	
	@Override
	public PolicyDetail setPartyDetailWithRole(PolicyDetail policyDetail , Portfolio portfolio , String role)
	{
			
		logger.debug("------ setPartyDetailWithRole(PolicyDetail policyDetail , Portfolio portfolio , String role) ------ Started -----");
		
		        Party party = null;
				
				if(role.equals("AGENT") && portfolio.getAgent()!=null)
				{
					
						AgentParty agentParty = portfolio.getAgent().getAgentParty();
				    
						if(agentParty!=null)
						{ policyDetail.setAgentFirstName(agentParty.getSearchFirstName() == null ? "":agentParty.getSearchFirstName());
						  policyDetail.setAgentLastName(agentParty.getSearchLastName() == null ? "":agentParty.getSearchLastName());
						  policyDetail.setAgentNumber(agentParty.getBpoAgentNumber() == null ? "":agentParty.getBpoAgentNumber());
						}
				}
				else 
				 party = portfolio.getParty();
				
			   
				if (party == null) 
			        logger.warn(String.format("Party record is not found for portfolio client_contract_id[%s]",portfolio.getClientContractId()));
				else 
				{
					
					  PersonDetail personDetail =  party.getPersonDetail();
					
					    if (personDetail == null) 
							logger.warn(String.format("Person Detail record is not found for party client_id[%s]", party.getClientId()));
						else 
						{
							
							if(role.equals("INSURED"))
							{   
								policyDetail.setInsuredFirstName(personDetail.getFirstName() == null ? "":personDetail.getFirstName());                                                       //Insured First Name
								policyDetail.setInsuredMiddleName(personDetail.getMiddleName() == null ? "":personDetail.getMiddleName());                                                    //Insured Middle Name
								policyDetail.setInsuredLastName(personDetail.getLastName() == null ? "":personDetail.getLastName());                                                          //Insured Last Name
								policyDetail.setInsuredBirthDate(personDetail.getDateOfBirth() == null ? "": new SimpleDateFormat("yyyy-MM-dd").format(personDetail.getDateOfBirth()));       //Insured Birth Date
							
								policyDetail.setInsuredSSN(party.getGovId() == null ? "":party.getGovId().trim());                                                                            // InsuredSSN
								policyDetail.setInsuredEmail(party.getEmailAddress() == null ? "":party.getEmailAddress());
								policyDetail.setInsuredPhone(party.getBpoPhoneNumber() == null ? "":party.getBpoPhoneNumber());
								
								    	
					      	   Set<ClientAddress> clientAddresses = party.getClientAddresses();
					         
						      	   
						    	for (ClientAddress clientAddress : clientAddresses) {
									
									Address addr = clientAddress.getAddress();
									
									policyDetail.setInsuredAddressLine1(addr.getLine1() == null ? "":addr.getLine1());                                // Insured Address Line 1
									policyDetail.setInsuredAddressLine2(addr.getLine2() == null ? "":addr.getLine2());                                // Insured Address Line 2
									policyDetail.setInsuredAddressLine3(addr.getLine3() == null ? "":addr.getLine3());                                // Insured Address Line 3
									policyDetail.setInsuredAddressLine4(addr.getLine4() == null ? "":addr.getLine4());                                // Insured Address Line 4
									policyDetail.setInsuredCity(addr.getCity() == null ? "":addr.getCity());                                          // Insured City
									policyDetail.setInsuredZipCode(addr.getZip() == null ? "":addr.getZip());                                         // Insured Zip
									policyDetail.setInsuredStateCode(avService.getHostValue("State", addr.getState()));	                              // Insured StateCode
								   
									 break ;
									    	
								   }
							}
							else if(role.equals("OWNER"))
							{
							   
								policyDetail.setOwnerFirstName(personDetail.getFirstName() == null ? "":personDetail.getFirstName());                                                    
								policyDetail.setOwnerMiddleName(personDetail.getMiddleName() == null ? "":personDetail.getMiddleName());             // Owner Middle Name
                                policyDetail.setOwnerLastName(personDetail.getLastName() == null ? "":personDetail.getLastName());                   // Owner Last Name
                                policyDetail.setOwnerSSN(party.getGovId() == null ? "":party.getGovId().trim()); 
							}
							
						}
	               
					  if (role.equals("OWNER") && !UtilityService.isNullOrEmpty(party.getSearchCompanyName()))
						{
						  policyDetail.setOwnerOrganizationName(party.getSearchCompanyName());
						  policyDetail.setOwnerSSN(party.getGovId() == null ? "":party.getGovId().trim());
						}
					    
				}
				
				
				logger.debug("------ setPartyDetailWithRole(PolicyDetail policyDetail , Portfolio portfolio , String role) ------ Finished ------");
				
		return policyDetail;
	}
	
	
	
	@Override
	public Object setFailedStatus(Object obj,Exception e) {
    
	
		if (obj instanceof GetPolicyResponse) 
		{
			GetPolicyResponse policyResponse = (GetPolicyResponse) obj;
			
			if (e.getMessage()!=null ? e.getMessage().equals("result returns more than one elements"):false) 
			{
				policyResponse.setStatus("409");
				policyResponse.setDescription("Multiple Results Found");
			}
			else if(e.getMessage()!=null)
			{
				policyResponse.setStatus("Error");
				policyResponse.setDescription("Exception : "+e+ " Message : "+ e.getMessage() +" Cause : "+e.getCause() );		
			}
			else
			{
				policyResponse.setStatus("204");
				policyResponse.setDescription("policy Data Not Found");
			}
		    return policyResponse;
		}
		else if(obj instanceof GetMultiplePolicyResponse)
		{
			GetMultiplePolicyResponse policyResponse = (GetMultiplePolicyResponse) obj;
			
			if(e.getMessage()!=null)
			{
				policyResponse.setStatus("Error");
				policyResponse.setDescription("Exception : "+e+ " Message : "+ e.getMessage() +" Cause : "+e.getCause() );		
			}
			else
			{
				policyResponse.setStatus("204");
				policyResponse.setDescription("policy Data Not Found");
			}
		    return policyResponse;
		}
		
	 return null ;
		
	}
	
	@Override
	public Object setSuccessStatus(Object obj) {
    
		if (obj instanceof GetPolicyResponse) {
			GetPolicyResponse policyResponse = (GetPolicyResponse) obj;
			
			policyResponse.setStatus("200");
			policyResponse.setDescription("Success");
			return policyResponse;
		}
		else if(obj instanceof GetMultiplePolicyResponse)
		{
			GetMultiplePolicyResponse policyResponse = (GetMultiplePolicyResponse) obj;
			policyResponse.setStatus("200");
			policyResponse.setDescription("Success");
			return policyResponse;
		}
		
		return null;
	}
	
	
	
	
}
