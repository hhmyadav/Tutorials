package com.dxc.paws.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dxc.paws.entities.Policy;

@Repository
public interface PolicyRepository extends JpaRepository<Policy, Integer> {

	
	List<Policy> findByPolicyNumber(String policyNumber);
	
	List<Policy> findByPolicyNumberAndRegionId(String policyNumber , Short regionId);
			
	@Query("select pol from Policy pol where pol.policyNumber like (concat(:policyNumber,'%'))")
	List<Policy> findByLikePolicyNumber(@Param("policyNumber")String policyNumber);
	
	@Query("select pol from Policy pol where pol.policyNumber IN (select port.contractId from Party as par, Portfolio as port where par.govId = :govId and port.clientId = par.clientId)")
	List<Policy> findBySSN(@Param("govId") String govId);
	
	@Query("select pol from Policy pol where pol.policyNumber IN (select port.contractId from Party as par, Portfolio as port where par.searchFirstName = :searchFirstName and par.searchLastName = :searchLastName and port.clientId = par.clientId)")
	List<Policy> findByFirstNameAndLastName(@Param("searchFirstName") String searchFirstName ,@Param("searchLastName")  String searchLastName);
      
	
	
}
