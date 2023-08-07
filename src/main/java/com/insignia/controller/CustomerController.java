package com.insignia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.insignia.constant.CustomerManagementConstants;
import com.insignia.constants.AddressDetailsConstants;
import com.insignia.customExceptions.InvalidInputParametersException;
import com.insignia.customExceptions.TokenExpiredException;
import com.insignia.model.AddressResponse;
import com.insignia.model.CustomerManagementServiceRequest;
import com.insignia.model.CustomerManagementServiceResponse;
import com.insignia.serviceInterface.CustomerServiceInterface;
import com.insignia.validations.AddressDetailsValidation;
import com.insignia.validations.CustomerBasicDetailsValidation;
import com.insignia.validations.CustomerPersonalDetailsValidation;
import com.insignia.validations.RolesAndPermissonsValidations;

@RestController
public class CustomerController {

	@Autowired
	private CustomerServiceInterface serviceRepo;

	@PostMapping("/saveCustomerDetails")
	public ResponseEntity<?> saveAllCustomerDetails(@RequestBody CustomerManagementServiceRequest customerDetails)
			throws Exception {
		try {

			// Customer Basic Details Validations
			if (customerDetails.getCustomerBasicDetailsRequest() != null) {

				CustomerBasicDetailsValidation.ValidateUserId(
						customerDetails.getCustomerBasicDetailsRequest().getUserId(),
						CustomerManagementConstants.userIdlength);

				CustomerBasicDetailsValidation.ValidateApplicationId(
						customerDetails.getCustomerBasicDetailsRequest().getApplicationId(),
						CustomerManagementConstants.applicationIdlength);
				CustomerBasicDetailsValidation.ValidateTenantId(
						customerDetails.getCustomerBasicDetailsRequest().getTenantId(),
						CustomerManagementConstants.tenantIdlength);
				CustomerBasicDetailsValidation.ValidatePassword(
						customerDetails.getCustomerBasicDetailsRequest().getPassword(),
						CustomerManagementConstants.passwordlength);
				CustomerBasicDetailsValidation.ValidateCustomerEmail(
						customerDetails.getCustomerBasicDetailsRequest().getEmailId(),
						CustomerManagementConstants.customerEmail);
				CustomerBasicDetailsValidation.ValidateUsername(
						customerDetails.getCustomerBasicDetailsRequest().getUserName(),
						CustomerManagementConstants.userName);
			}
			if (customerDetails.getAddressRequest() != null) {
				// Address Details Validations
//				AddressDetailsValidation.ValidateCustomerSequenceNumber(
//						customerDetails.getAddressRequest().getCustomerSequenceNumber(),
//						CustomerManagementConstants.customerSequenceNumber);
				AddressDetailsValidation.ValidateAddressLine1(customerDetails.getAddressRequest().getAddressLine1(),
						CustomerManagementConstants.addresLine1length);
				AddressDetailsValidation.ValidateCity(customerDetails.getAddressRequest().getCity(),
						CustomerManagementConstants.city);
				AddressDetailsValidation.ValidateLandmark(customerDetails.getAddressRequest().getLandmark(),
						CustomerManagementConstants.landMark);
				AddressDetailsValidation.ValidateState(customerDetails.getAddressRequest().getState(),
						CustomerManagementConstants.state);
				AddressDetailsValidation.ValidateCountry(customerDetails.getAddressRequest().getCountry(),
						CustomerManagementConstants.country);
				AddressDetailsValidation.ValidateZipCode(customerDetails.getAddressRequest().getZipCode(),
						CustomerManagementConstants.zipCode);
				AddressDetailsValidation.ValidateAddressLine2(customerDetails.getAddressRequest().getZipCode(),
						CustomerManagementConstants.addresLine2length);
				AddressDetailsValidation.ValidateEmailId(customerDetails.getAddressRequest().getZipCode(),
						CustomerManagementConstants.emailId);
				AddressDetailsValidation.ValidateMobileNumber(customerDetails.getAddressRequest().getMobileNumber(),
						CustomerManagementConstants.mobilenumber);
				AddressDetailsValidation.ValidateLandLineNumber(customerDetails.getAddressRequest().getLandlineNumber(),
						CustomerManagementConstants.landlineNumber);

			}

			// CustomerPersonal Details Validations
			if (customerDetails.getCustomerPersonalDetailsRequest() != null) {

//				CustomerPersonalDetailsValidation.ValidateCustomerSequenceNumber(
//						customerDetails.getCustomerPersonalDetailsRequest().getCustomerSequenceNumber(),
//						CustomerManagementConstants.customerSequenceNumber);
				CustomerPersonalDetailsValidation.ValidateFirstName(
						customerDetails.getCustomerPersonalDetailsRequest().getFirstName(),
						CustomerManagementConstants.firstName);
				CustomerPersonalDetailsValidation.ValidateLastName(
						customerDetails.getCustomerPersonalDetailsRequest().getLastName(),
						CustomerManagementConstants.secondName);
				CustomerPersonalDetailsValidation.ValidateMiddleName(
						customerDetails.getCustomerPersonalDetailsRequest().getMiddleName(),
						CustomerManagementConstants.middleName);
				CustomerPersonalDetailsValidation.ValidateAge(
						customerDetails.getCustomerPersonalDetailsRequest().getAge(), CustomerManagementConstants.age);
				CustomerPersonalDetailsValidation.ValidateGender(
						customerDetails.getCustomerPersonalDetailsRequest().getGender(),
						CustomerManagementConstants.gender);
				CustomerPersonalDetailsValidation.ValidateCustomerEmailId(
						customerDetails.getCustomerPersonalDetailsRequest().getCustomerEmailId(),
						CustomerManagementConstants.customerEmailId);
				CustomerPersonalDetailsValidation.ValidateAlternativeEmailId(
						customerDetails.getCustomerPersonalDetailsRequest().getAlternativeEmailId(),
						CustomerManagementConstants.alternativeEmailId);
				CustomerPersonalDetailsValidation.ValidateCustomeMobileNumber(
						customerDetails.getCustomerPersonalDetailsRequest().getCustomerMobileNumber(),
						CustomerManagementConstants.customerMobileNumber);
				CustomerPersonalDetailsValidation.ValidateAlternativeMobileNumber(
						customerDetails.getCustomerPersonalDetailsRequest().getAlternativeMobileNumber(),
						CustomerManagementConstants.alternativeMobileNumber);
				CustomerPersonalDetailsValidation.ValidateCustomerLandlineNumber(
						customerDetails.getCustomerPersonalDetailsRequest().getCustomerLandlineNumber(),
						CustomerManagementConstants.customerLandlineNumber);

			}
			// CustomerPersonal Details Validations
			if (customerDetails.getRolesAndPermissionsRequest() != null) {

//				RolesAndPermissonsValidations.ValidateCustomerSequenceNumber(
//						customerDetails.getRolesAndPermissionsRequest().getCustomerSequenceNumber(),
//						CustomerManagementConstants.customerSequenceNumber);
				RolesAndPermissonsValidations.ValidateRoleName(
						customerDetails.getRolesAndPermissionsRequest().getRoleName(),
						CustomerManagementConstants.roleName);
				RolesAndPermissonsValidations.ValidateUpdatePermissions(
						customerDetails.getRolesAndPermissionsRequest().getUpdatedPermissions(),
						CustomerManagementConstants.updatePermissions);

			}
//			serviceRepo.saveAllCustomerDetails(customerDetails);
			return ResponseEntity.ok(serviceRepo.saveAllCustomerDetails(customerDetails));

		} catch (InvalidInputParametersException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new CustomerManagementServiceResponse(ex.getErrorCode(), ex.getStrMsg()));
		}
//		catch (Exception e) {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//					.body(new CustomerManagementServiceResponse(AddressDetailsConstants.validateUnexpectedErrorCode,
//							AddressDetailsConstants.validateUnexpectedErrorMessage));
//		}

	}

	
	
	@PutMapping("/updateCustomerDetails")
	public ResponseEntity<?> updateAllCustomerDetails(@RequestBody CustomerManagementServiceRequest customerDetails) {
		try {
			// Customer Basic Details Validations
			if (customerDetails.getCustomerBasicDetailsRequest() != null) {

				CustomerBasicDetailsValidation.ValidateUserId(
						customerDetails.getCustomerBasicDetailsRequest().getUserId(),
						CustomerManagementConstants.userIdlength);

				CustomerBasicDetailsValidation.ValidateApplicationId(
						customerDetails.getCustomerBasicDetailsRequest().getApplicationId(),
						CustomerManagementConstants.applicationIdlength);
				CustomerBasicDetailsValidation.ValidateTenantId(
						customerDetails.getCustomerBasicDetailsRequest().getTenantId(),
						CustomerManagementConstants.tenantIdlength);
				CustomerBasicDetailsValidation.ValidatePassword(
						customerDetails.getCustomerBasicDetailsRequest().getPassword(),
						CustomerManagementConstants.passwordlength);
				CustomerBasicDetailsValidation.ValidateCustomerEmail(
						customerDetails.getCustomerBasicDetailsRequest().getEmailId(),
						CustomerManagementConstants.customerEmail);
				CustomerBasicDetailsValidation.ValidateUsername(
						customerDetails.getCustomerBasicDetailsRequest().getUserName(),
						CustomerManagementConstants.userName);
			}
			if (customerDetails.getAddressRequest() != null) {
				// Address Details Validations
				AddressDetailsValidation.ValidateCustomerSequenceNumber(
						customerDetails.getAddressRequest().getCustomerSequenceNumber(),
						CustomerManagementConstants.customerSequenceNumber);
				AddressDetailsValidation.ValidateAddressLine1(customerDetails.getAddressRequest().getAddressLine1(),
						CustomerManagementConstants.addresLine1length);
				AddressDetailsValidation.ValidateCity(customerDetails.getAddressRequest().getCity(),
						CustomerManagementConstants.city);
				AddressDetailsValidation.ValidateLandmark(customerDetails.getAddressRequest().getLandmark(),
						CustomerManagementConstants.landMark);
				AddressDetailsValidation.ValidateState(customerDetails.getAddressRequest().getState(),
						CustomerManagementConstants.state);
				AddressDetailsValidation.ValidateCountry(customerDetails.getAddressRequest().getCountry(),
						CustomerManagementConstants.country);
				AddressDetailsValidation.ValidateZipCode(customerDetails.getAddressRequest().getZipCode(),
						CustomerManagementConstants.zipCode);
				AddressDetailsValidation.ValidateAddressLine2(customerDetails.getAddressRequest().getZipCode(),
						CustomerManagementConstants.addresLine2length);
				AddressDetailsValidation.ValidateEmailId(customerDetails.getAddressRequest().getZipCode(),
						CustomerManagementConstants.emailId);
				AddressDetailsValidation.ValidateMobileNumber(customerDetails.getAddressRequest().getMobileNumber(),
						CustomerManagementConstants.mobilenumber);
				AddressDetailsValidation.ValidateLandLineNumber(customerDetails.getAddressRequest().getLandlineNumber(),
						CustomerManagementConstants.landlineNumber);

			}

			// CustomerPersonal Details Validations
			if (customerDetails.getCustomerPersonalDetailsRequest() != null) {

				CustomerPersonalDetailsValidation.ValidateCustomerSequenceNumber(
						customerDetails.getCustomerPersonalDetailsRequest().getCustomerSequenceNumber(),
						CustomerManagementConstants.customerSequenceNumber);
				CustomerPersonalDetailsValidation.ValidateFirstName(
						customerDetails.getCustomerPersonalDetailsRequest().getFirstName(),
						CustomerManagementConstants.firstName);
				CustomerPersonalDetailsValidation.ValidateLastName(
						customerDetails.getCustomerPersonalDetailsRequest().getLastName(),
						CustomerManagementConstants.secondName);
				CustomerPersonalDetailsValidation.ValidateMiddleName(
						customerDetails.getCustomerPersonalDetailsRequest().getMiddleName(),
						CustomerManagementConstants.middleName);
				CustomerPersonalDetailsValidation.ValidateAge(
						customerDetails.getCustomerPersonalDetailsRequest().getAge(), CustomerManagementConstants.age);
				CustomerPersonalDetailsValidation.ValidateGender(
						customerDetails.getCustomerPersonalDetailsRequest().getGender(),
						CustomerManagementConstants.gender);
				CustomerPersonalDetailsValidation.ValidateCustomerEmailId(
						customerDetails.getCustomerPersonalDetailsRequest().getCustomerEmailId(),
						CustomerManagementConstants.customerEmailId);
				CustomerPersonalDetailsValidation.ValidateAlternativeEmailId(
						customerDetails.getCustomerPersonalDetailsRequest().getAlternativeEmailId(),
						CustomerManagementConstants.alternativeEmailId);
				CustomerPersonalDetailsValidation.ValidateCustomeMobileNumber(
						customerDetails.getCustomerPersonalDetailsRequest().getCustomerMobileNumber(),
						CustomerManagementConstants.customerMobileNumber);
				CustomerPersonalDetailsValidation.ValidateAlternativeMobileNumber(
						customerDetails.getCustomerPersonalDetailsRequest().getAlternativeMobileNumber(),
						CustomerManagementConstants.alternativeMobileNumber);
				CustomerPersonalDetailsValidation.ValidateCustomerLandlineNumber(
						customerDetails.getCustomerPersonalDetailsRequest().getCustomerLandlineNumber(),
						CustomerManagementConstants.customerLandlineNumber);

			}
			// CustomerPersonal Details Validations
			if (customerDetails.getRolesAndPermissionsRequest() != null) {

				RolesAndPermissonsValidations.ValidateCustomerSequenceNumber(
						customerDetails.getRolesAndPermissionsRequest().getCustomerSequenceNumber(),
						CustomerManagementConstants.customerSequenceNumber);
				RolesAndPermissonsValidations.ValidateRoleName(
						customerDetails.getRolesAndPermissionsRequest().getRoleName(),
						CustomerManagementConstants.roleName);
				RolesAndPermissonsValidations.ValidateUpdatePermissions(
						customerDetails.getRolesAndPermissionsRequest().getUpdatedPermissions(),
						CustomerManagementConstants.updatePermissions);

			}
			return ResponseEntity.ok(serviceRepo.updateAllCustomerDetails(customerDetails));

		} catch (InvalidInputParametersException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new CustomerManagementServiceResponse(ex.getErrorCode(), ex.getStrMsg()));

		} catch (TokenExpiredException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new AddressResponse(
					AddressDetailsConstants.validateTokenErrorCode, AddressDetailsConstants.validateToken));

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new CustomerManagementServiceResponse(AddressDetailsConstants.validateUnexpectedErrorCode,
							AddressDetailsConstants.validateUnexpectedErrorMessage));
		}
	}

	@DeleteMapping("/deleteCustomerDetails/{customerSequenceNumber}")
	public ResponseEntity<?> deleteCustomerAssociatedDetails(@PathVariable Long customerSequenceNumber) {
		try {
			serviceRepo.deleteCustomerAssociatedDetails(customerSequenceNumber);
			return ResponseEntity.ok("Record Successfully Deleted");

		} catch (TokenExpiredException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new AddressResponse(
					AddressDetailsConstants.validateTokenErrorCode, AddressDetailsConstants.validateToken));

		} 
//		catch (Exception e) {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//					.body(new CustomerManagementServiceResponse(AddressDetailsConstants.validateUnexpectedErrorCode,
//							AddressDetailsConstants.validateUnexpectedErrorMessage));
//		}

	}

	@GetMapping("/getCustomerDetails/{customerSequenceNumber}")
	public ResponseEntity<?> getAllCustomerData(@PathVariable Long customerSequenceNumber) {
try {
		return ResponseEntity.ok(serviceRepo.getAllCustomerData(customerSequenceNumber));
		
	} catch (TokenExpiredException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new AddressResponse(
				AddressDetailsConstants.validateTokenErrorCode, AddressDetailsConstants.validateToken));

	} catch (Exception e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new CustomerManagementServiceResponse(AddressDetailsConstants.validateUnexpectedErrorCode,
						AddressDetailsConstants.validateUnexpectedErrorMessage));
	}
}

}
