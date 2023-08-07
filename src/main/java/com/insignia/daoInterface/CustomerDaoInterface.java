package com.insignia.daoInterface;


import java.util.List;

import com.insignia.customExceptions.InvalidInputParametersException;
import com.insignia.entity.CustomerBasicDetailsEntity;
import com.insignia.model.AddressRequest;
import com.insignia.model.AddressResponse;
import com.insignia.model.CustomerBasicDetailsRequest;
import com.insignia.model.CustomerManagementServiceRequest;
import com.insignia.model.CustomerManagementServiceResponse;
import com.insignia.model.CustomerPersonalDetailsRequest;
import com.insignia.model.CustomerPersonalDetailsResponse;
import com.insignia.model.RolesAndPermissionsRequest;
import com.insignia.model.RolesAndPermissionsResponse;

public interface CustomerDaoInterface {
	
	public CustomerBasicDetailsEntity saveAllCustomerDetails(CustomerBasicDetailsEntity cbde);
	
	public CustomerBasicDetailsEntity getAllCustomerData(Long customerSequenceNumber) throws InvalidInputParametersException;
	
	public CustomerPersonalDetailsResponse updateCustomerPersonalDetails(CustomerPersonalDetailsRequest customerPersonalBasicRequest);
	
	public void updateCustomerBasicDetails(CustomerBasicDetailsRequest customerBasicDetailsRequest);
	
	public AddressResponse updateAddressDetails(AddressRequest addressReq);
	
	public RolesAndPermissionsResponse updateRolesAndPermissions(RolesAndPermissionsRequest rolesAndPermissionsRequest);
	
	public void deleteCustomerAssociatedDetails(Long customerSequenceNumber);	
	
	public Boolean isTokenNotValid(Long customer_sequence_number);
	
	

	

	

	
	
	
}
