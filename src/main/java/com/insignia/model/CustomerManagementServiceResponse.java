package com.insignia.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class CustomerManagementServiceResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	private CustomerPersonalDetailsResponse customerPersonalDetailsResponse;

	private List<AddressResponse> addressResponseList;

	private List<RolesAndPermissionsResponse> rolesAndPermissionsResponseList;

	private RolesAndPermissionsResponse rolesAndPermissionsResponse;

	private AddressResponse addressResponse;

	private String successMessage;

	private String errorCode;

	private String errorMessage;

	public CustomerPersonalDetailsResponse getCustomerPersonalDetailsResponse() {
		return customerPersonalDetailsResponse;
	}

	public void setCustomerPersonalDetailsResponse(CustomerPersonalDetailsResponse customerPersonalDetailsResponse) {
		this.customerPersonalDetailsResponse = customerPersonalDetailsResponse;
	}

	public List<AddressResponse> getAddressResponseList() {
		return addressResponseList;
	}

	public void setAddressResponseList(List<AddressResponse> addressResponseList) {
		this.addressResponseList = addressResponseList;
	}

	public List<RolesAndPermissionsResponse> getRolesAndPermissionsResponseList() {
		return rolesAndPermissionsResponseList;
	}

	public void setRolesAndPermissionsResponseList(List<RolesAndPermissionsResponse> rolesAndPermissionsResponseList) {
		this.rolesAndPermissionsResponseList = rolesAndPermissionsResponseList;
	}

	public RolesAndPermissionsResponse getRolesAndPermissionsResponse() {
		return rolesAndPermissionsResponse;
	}

	public void setRolesAndPermissionsResponse(RolesAndPermissionsResponse rolesAndPermissionsResponse) {
		this.rolesAndPermissionsResponse = rolesAndPermissionsResponse;
	}

	public AddressResponse getAddressResponse() {
		return addressResponse;
	}

	public void setAddressResponse(AddressResponse addressResponse) {
		this.addressResponse = addressResponse;
	}

	public String getSuccessMessage() {
		return successMessage;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public CustomerManagementServiceResponse(String errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public CustomerManagementServiceResponse() {

	}

	@Override
	public String toString() {
		return "CustomerManagementServiceResponse [customerPersonalDetailsResponse=" + customerPersonalDetailsResponse
				+ ", addressResponseList=" + addressResponseList + ", rolesAndPermissionsResponseList="
				+ rolesAndPermissionsResponseList + ", rolesAndPermissionsResponse=" + rolesAndPermissionsResponse
				+ ", addressResponse=" + addressResponse + ", successMessage=" + successMessage + ", errorCode="
				+ errorCode + ", errorMessage=" + errorMessage + "]";
	}

}
