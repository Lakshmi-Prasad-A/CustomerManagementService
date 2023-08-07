package com.insignia.serviceImpl;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import com.insignia.constants.AddressDetailsConstants;
import com.insignia.customExceptions.InvalidInputParametersException;
import com.insignia.customExceptions.TokenExpiredException;
import com.insignia.daoInterface.CustomerDaoInterface;
import com.insignia.encryption.PasswordEncoder;
import com.insignia.entity.AddressDetails;
import com.insignia.entity.CustomerBasicDetailsEntity;
import com.insignia.entity.CustomerPersonalDetails;
import com.insignia.entity.RolesAndPermissions;
import com.insignia.model.AddressRequest;
import com.insignia.model.AddressResponse;
import com.insignia.model.CustomerBasicDetailsRequest;
import com.insignia.model.CustomerManagementServiceRequest;
import com.insignia.model.CustomerManagementServiceResponse;
import com.insignia.model.CustomerPersonalDetailsRequest;
import com.insignia.model.CustomerPersonalDetailsResponse;
import com.insignia.model.RolesAndPermissionsRequest;
import com.insignia.model.RolesAndPermissionsResponse;
import com.insignia.serviceInterface.CustomerServiceInterface;

@Service
public class CustomerServiceImpl implements CustomerServiceInterface {

	@Autowired
	private CustomerDaoInterface customerDao;

	@Transactional
	@Modifying
	@Override
	public CustomerManagementServiceResponse saveAllCustomerDetails(CustomerManagementServiceRequest customerManagementServiceDetails)
			throws Exception {
		CustomerManagementServiceResponse customerManagementServiceResponse = new CustomerManagementServiceResponse();

		try {

			CustomerBasicDetailsEntity cbde = new CustomerBasicDetailsEntity();

			if (customerManagementServiceDetails.getCustomerBasicDetailsRequest() != null) {

				String password = PasswordEncoder.getEncryptedPassword(
						customerManagementServiceDetails.getCustomerBasicDetailsRequest().getPassword());
				
				CustomerBasicDetailsRequest request = customerManagementServiceDetails
						.getCustomerBasicDetailsRequest();

				
				cbde = new CustomerBasicDetailsEntity(request.getApplicationId(), request.getTenantId(),
						request.getUserId(), password, request.getEmailId(), request.getUserName(), null, null, null,null);
				
				System.out.println(cbde);
				
				customerManagementServiceResponse.setSuccessMessage("Successfully created Customer Basic Details");
			}
			if (customerManagementServiceDetails.getRolesAndPermissionsRequestList() != null) {
			    List<RolesAndPermissionsRequest> rolesAndPermissionsRequestList = customerManagementServiceDetails.getRolesAndPermissionsRequestList();
			    
			    List<RolesAndPermissions> rapList = new ArrayList<>();

			    for (RolesAndPermissionsRequest rolesAndPermissionsRequest : rolesAndPermissionsRequestList) {
			        RolesAndPermissions rap = new RolesAndPermissions();
			        rap.setRoleName(rolesAndPermissionsRequest.getRoleName());
			        rap.setPermission1(rolesAndPermissionsRequest.getPermission1());
			        rap.setPermission2(rolesAndPermissionsRequest.getPermission2());
			        rap.setPermission3(rolesAndPermissionsRequest.getPermission3());
			        rap.setPermission4(rolesAndPermissionsRequest.getPermission4());
			        rap.setRoleApprovedDate(rolesAndPermissionsRequest.getRoleApprovedDate());
			        rap.setRoleRevokedDate(rolesAndPermissionsRequest.getRoleRevokedDate());
			        rap.setPermissionChangeDate(rolesAndPermissionsRequest.getPermissionChangeDate());
			        rap.setUpdatedPermissions(rolesAndPermissionsRequest.getUpdatedPermissions());

			        rapList.add(rap);
			        
			    }

			    cbde.setRolesAndPermissions(rapList);
			    
			    System.out.println(rapList);
			}

			if (customerManagementServiceDetails.getCustomerPersonalDetailsRequest() != null) {
				CustomerPersonalDetails cpde = new CustomerPersonalDetails();

				CustomerPersonalDetailsRequest customerPersonalDetailsRequest = customerManagementServiceDetails
						.getCustomerPersonalDetailsRequest();

				cpde = new CustomerPersonalDetails(
						customerPersonalDetailsRequest.getFirstName(), customerPersonalDetailsRequest.getLastName(),
						customerPersonalDetailsRequest.getMiddleName(), customerPersonalDetailsRequest.getAge(),
						customerPersonalDetailsRequest.getGender(), customerPersonalDetailsRequest.getCustomerEmailId(),
						customerPersonalDetailsRequest.getAlternativeEmailId(),
						customerPersonalDetailsRequest.getCustomerMobileNumber(),
						customerPersonalDetailsRequest.getAlternativeMobileNumber(),
						customerPersonalDetailsRequest.getCustomerLandlineNumber());

				System.out.println(cpde);
				cbde.setCustomerPersonalEntity(cpde);
				System.out.println(cpde);
			    
			    
			    
				
			}
			if (customerManagementServiceDetails.getAddressRequestList() != null) {
			    List<AddressRequest> addressRequestList = customerManagementServiceDetails.getAddressRequestList();
			    List<AddressDetails> adList = new ArrayList<>(); 
			    
			    for (AddressRequest addressRequest : addressRequestList) {
			        AddressDetails ad = new AddressDetails();
			        ad.setAddressLine1(addressRequest.getAddressLine1());
			        ad.setAddressLine2(addressRequest.getAddressLine2());
			        ad.setLandmark(addressRequest.getLandmark());
			        ad.setCity(addressRequest.getCity());
			        ad.setState(addressRequest.getState());
			        ad.setCountry(addressRequest.getCountry());
			        ad.setZipCode(addressRequest.getZipCode());
			        ad.setMobileNumber(addressRequest.getMobileNumber());
			        ad.setLandlineNumber(addressRequest.getLandlineNumber());
			        ad.setEmailId(addressRequest.getEmailId());
			        ad.setDefaultAddress(addressRequest.getIsDefaultAddress());
			        ad.setBillingAddress(addressRequest.getIsBillingAddress());

			        adList.add(ad);
			        System.out.println(ad);
			    }

			    cbde.setAddressDetails(adList);
			    System.out.println(adList);

			}

			customerDao.saveAllCustomerDetails(cbde);
			
			
////////////////////////////////////////////////////
			
			//CustomerPersonalDetailsResponse//
			
			 Optional<CustomerPersonalDetailsRequest> optionalPersonalDetailsRequest = Optional.ofNullable(
			            customerManagementServiceDetails.getCustomerPersonalDetailsRequest());

			    if (optionalPersonalDetailsRequest.isPresent()) {
			        CustomerPersonalDetailsRequest customerPersonalDetailsRequest = optionalPersonalDetailsRequest.get();
			        
			        CustomerPersonalDetailsResponse customerPersonalDetailsResponse = createCustomerPersonalDetailsResponse(
							customerPersonalDetailsRequest);

					customerManagementServiceResponse.setCustomerPersonalDetailsResponse(customerPersonalDetailsResponse);
			        
			    }
			    
			    //AddressResponse//
			    
			    Optional<List<AddressRequest>> optionalAddressRequestList = Optional.ofNullable(
			            customerManagementServiceDetails.getAddressRequestList());

			    if (optionalAddressRequestList.isPresent()) {
			        List<AddressRequest> addressRequestList = optionalAddressRequestList.get();

			        List<AddressResponse> responseList = createAddressDetailsResponse(addressRequestList);

			        customerManagementServiceResponse.setAddressResponseList(responseList);
			    }
			    
			    
			    //RolesAndPermisionsResponse//
			    Optional<List<RolesAndPermissionsRequest>> optionalRolesAndPermissionsRequestList = Optional.ofNullable(
			            customerManagementServiceDetails.getRolesAndPermissionsRequestList());

			    if (optionalRolesAndPermissionsRequestList.isPresent()) {
			        List<RolesAndPermissionsRequest> rolesAndPermissionsRequests = optionalRolesAndPermissionsRequestList.get();

			        List<RolesAndPermissionsResponse> rolesAndPermissionsResponseList = createRolesAndPermissionsResponse(
							rolesAndPermissionsRequests);

			        customerManagementServiceResponse.setRolesAndPermissionsResponseList(rolesAndPermissionsResponseList);

			       
			    }
			
			
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException
				| BadPaddingException e) {

			throw new Exception();
		}

		return customerManagementServiceResponse;
	}

	private List<RolesAndPermissionsResponse> createRolesAndPermissionsResponse(
			List<RolesAndPermissionsRequest> rolesAndPermissionsRequests) {
		List<RolesAndPermissionsResponse> rolesAndPermissionsResponseList = new ArrayList<>();

		for (RolesAndPermissionsRequest rolesAndPermissionsRequest : rolesAndPermissionsRequests) {
		    RolesAndPermissionsResponse rolesAndPermissionsResponse = new RolesAndPermissionsResponse();
		    rolesAndPermissionsResponse.setRoleName(rolesAndPermissionsRequest.getRoleName());
		    rolesAndPermissionsResponse.setPermission1(rolesAndPermissionsRequest.getPermission1());
		    rolesAndPermissionsResponse.setPermission2(rolesAndPermissionsRequest.getPermission2());
		    rolesAndPermissionsResponse.setPermission3(rolesAndPermissionsRequest.getPermission3());
		    rolesAndPermissionsResponse.setPermission4(rolesAndPermissionsRequest.getPermission4());
		    rolesAndPermissionsResponse.setRoleApprovedDate(rolesAndPermissionsRequest.getRoleApprovedDate());
		    rolesAndPermissionsResponse.setRoleRevokedDate(rolesAndPermissionsRequest.getRoleRevokedDate());
		    rolesAndPermissionsResponse.setPermissionChangeDate(rolesAndPermissionsRequest.getPermissionChangeDate());
		    rolesAndPermissionsResponse.setUpdatedPermissions(rolesAndPermissionsRequest.getUpdatedPermissions());

		    rolesAndPermissionsResponseList.add(rolesAndPermissionsResponse);
		}
		return rolesAndPermissionsResponseList;
	}

	private CustomerPersonalDetailsResponse createCustomerPersonalDetailsResponse(
			CustomerPersonalDetailsRequest customerPersonalDetailsRequest) {
		CustomerPersonalDetailsResponse customerPersonalDetailsResponse = new CustomerPersonalDetailsResponse();
		
		customerPersonalDetailsResponse.setFirstName(customerPersonalDetailsRequest.getFirstName());
		customerPersonalDetailsResponse.setLastName(customerPersonalDetailsRequest.getLastName());
		customerPersonalDetailsResponse.setMiddleName(customerPersonalDetailsRequest.getMiddleName());
		customerPersonalDetailsResponse.setAge(customerPersonalDetailsRequest.getAge());
		customerPersonalDetailsResponse.setGender(customerPersonalDetailsRequest.getGender());
		customerPersonalDetailsResponse.setCustomerEmailId(customerPersonalDetailsRequest.getCustomerEmailId());
		customerPersonalDetailsResponse.setAlternativeEmailId(customerPersonalDetailsRequest.getAlternativeEmailId());
		customerPersonalDetailsResponse.setCustomerMobileNumber(customerPersonalDetailsRequest.getCustomerMobileNumber());
		customerPersonalDetailsResponse.setAlternativeMobileNumber(customerPersonalDetailsRequest.getAlternativeMobileNumber());
		customerPersonalDetailsResponse.setCustomerLandlineNumber(customerPersonalDetailsRequest.getCustomerLandlineNumber());
		return customerPersonalDetailsResponse;
	}

	private List<AddressResponse> createAddressDetailsResponse(List<AddressRequest> addressRequestList) {
		List<AddressResponse> responseList = new ArrayList<>();

		for (AddressRequest addressRequest : addressRequestList) {
		    AddressResponse addressResponse = new AddressResponse();
		    addressResponse.setAddressLine1(addressRequest.getAddressLine1());
		    addressResponse.setAddressLine2(addressRequest.getAddressLine2());
		    addressResponse.setLandmark(addressRequest.getLandmark());
		    addressResponse.setCity(addressRequest.getCity());
		    addressResponse.setState(addressRequest.getState());
		    addressResponse.setCountry(addressRequest.getCountry());
		    addressResponse.setZipCode(addressRequest.getZipCode());
		    addressResponse.setMobileNumber(addressRequest.getMobileNumber());
		    addressResponse.setLandlineNumber(addressRequest.getLandlineNumber());
		    addressResponse.setEmailId(addressRequest.getEmailId());
		    addressResponse.setDefaultAddress(addressRequest.getIsDefaultAddress());
		    addressResponse.setBillingAddress(addressRequest.getIsBillingAddress());

		    responseList.add(addressResponse);
		    
		}
		return responseList;
	}

	


	public CustomerBasicDetailsEntity getAllCustomerData(Long customerSequenceNumber) 
		
		throws TokenExpiredException, InvalidInputParametersException {
			if (isTokenNotValid(customerSequenceNumber))
				throw new TokenExpiredException(AddressDetailsConstants.validateTokenErrorCode,
						AddressDetailsConstants.validateToken);
        return customerDao.getAllCustomerData(customerSequenceNumber);
    }
	
	

	@Override
	public CustomerManagementServiceResponse updateAllCustomerDetails(CustomerManagementServiceRequest customerDetails)
			throws InvalidInputParametersException, TokenExpiredException {

		CustomerManagementServiceResponse customerManagementServiceResponse = new CustomerManagementServiceResponse();

		if (customerDetails.getCustomerBasicDetailsRequest() != null) {
			if (isTokenNotValid(customerDetails.getCustomerBasicDetailsRequest().getCustomerSequenceNumber()))
				throw new TokenExpiredException(AddressDetailsConstants.validateTokenErrorCode,
						AddressDetailsConstants.validateToken);
			customerDao.updateCustomerBasicDetails(customerDetails.getCustomerBasicDetailsRequest());
			customerManagementServiceResponse.setSuccessMessage("Successfully Updated Customer Basic Details");
		}

		if (customerDetails.getRolesAndPermissionsRequest() != null) {
			if (isTokenNotValid(customerDetails.getRolesAndPermissionsRequest().getCustomerSequenceNumber()))
				throw new TokenExpiredException(AddressDetailsConstants.validateTokenErrorCode,
						AddressDetailsConstants.validateToken);
			RolesAndPermissionsResponse rolesAndPermissionsResponse = customerDao
					.updateRolesAndPermissions(customerDetails.getRolesAndPermissionsRequest());

			customerManagementServiceResponse.setRolesAndPermissionsResponse(rolesAndPermissionsResponse);
		}

		if (customerDetails.getCustomerPersonalDetailsRequest() != null) {
			if (isTokenNotValid(customerDetails.getCustomerPersonalDetailsRequest().getCustomerSequenceNumber()))
				throw new TokenExpiredException(AddressDetailsConstants.validateTokenErrorCode,
						AddressDetailsConstants.validateToken);
			CustomerPersonalDetailsResponse personalDetailsResponse = customerDao
					.updateCustomerPersonalDetails(customerDetails.getCustomerPersonalDetailsRequest());
			customerManagementServiceResponse.setCustomerPersonalDetailsResponse(personalDetailsResponse);
		}

		if (customerDetails.getAddressRequest() != null) {
			if (isTokenNotValid(customerDetails.getAddressRequest().getCustomerSequenceNumber()))
				throw new TokenExpiredException(AddressDetailsConstants.validateTokenErrorCode,
						AddressDetailsConstants.validateToken);
			AddressResponse addressResponse = customerDao.updateAddressDetails(customerDetails.getAddressRequest());
			customerManagementServiceResponse.setAddressResponse(addressResponse);
		}

		return customerManagementServiceResponse;
	}

	@Override
	public void deleteCustomerAssociatedDetails(Long customerSequenceNumber) throws TokenExpiredException {
		if (isTokenNotValid(customerSequenceNumber))
			throw new TokenExpiredException(AddressDetailsConstants.validateTokenErrorCode,
					AddressDetailsConstants.validateToken);
		
		customerDao.deleteCustomerAssociatedDetails(customerSequenceNumber);

	}

	private Boolean isTokenNotValid(Long customer_sequence_number) {
		return customerDao.isTokenNotValid(customer_sequence_number);
	}

}
