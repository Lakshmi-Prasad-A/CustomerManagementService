package com.insignia.serviceInterface;

import java.util.List;

import com.insignia.customExceptions.InvalidInputParametersException;
import com.insignia.customExceptions.TokenExpiredException;
import com.insignia.entity.CustomerBasicDetailsEntity;
import com.insignia.model.CustomerManagementServiceRequest;
import com.insignia.model.CustomerManagementServiceResponse;

public interface CustomerServiceInterface {

	public CustomerManagementServiceResponse saveAllCustomerDetails(CustomerManagementServiceRequest customerDetails) throws InvalidInputParametersException, Exception ;

	public CustomerManagementServiceResponse updateAllCustomerDetails(CustomerManagementServiceRequest customerDetails) throws TokenExpiredException, InvalidInputParametersException;

	public void deleteCustomerAssociatedDetails(Long customerSequenceNumber) throws TokenExpiredException;
	
	public CustomerBasicDetailsEntity getAllCustomerData(Long customerSequenceNumber) throws TokenExpiredException, InvalidInputParametersException;

	
}
