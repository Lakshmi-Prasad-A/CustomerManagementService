package com.insignia.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "customer_basic_details")
public class CustomerBasicDetailsEntity {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "customer_sequence_number")
	private Long customerSequenceNumber;

	@Column(name = "application_id")
	private String applicationId;

	@Column(name = "tenant_id")
	private String tenantId;

	@Column(name = "customer_id")
	private String customerId;

	@Column(name = "customer_password")
	private String customerPassword;

	@Column(name = "customer_email")
	private String customerEmail;

	@Column(name = "user_name")
	private String userName;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_sequence_number")
	private List<AddressDetails> addressDetails = new ArrayList<>();

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL,optional = false)
	@JoinColumn(name = "customer_sequence_number",unique=true,referencedColumnName = "customerSequenceNumber")
	private CustomerPersonalDetails customerPersonalEntity;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_sequence_number")
	private List<RolesAndPermissions> rolesAndPermissions = new ArrayList<>();
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL,optional = false)
	@JoinColumn(name="customer_sequence_number")
	private TokensEntity tokensEntity;

	
	

	public Long getCustomerSequenceNumber() {
		return customerSequenceNumber;
	}

	public void setCustomerSequenceNumber(Long customerSequenceNumber) {
		this.customerSequenceNumber = customerSequenceNumber;
	}

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerPassword() {
		return customerPassword;
	}

	public void setCustomerPassword(String customerPassword) {
		this.customerPassword = customerPassword;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<AddressDetails> getAddressDetails() {
		return addressDetails;
	}

	public void setAddressDetails(List<AddressDetails> addressDetails) {
		this.addressDetails = addressDetails;
	}

	public CustomerPersonalDetails getCustomerPersonalEntity() {
		return customerPersonalEntity;
	}

	public void setCustomerPersonalEntity(CustomerPersonalDetails customerPersonalEntity) {
		this.customerPersonalEntity = customerPersonalEntity;
	}

	public List<RolesAndPermissions> getRolesAndPermissions() {
		return rolesAndPermissions;
	}

	public void setRolesAndPermissions(List<RolesAndPermissions> rolesAndPermissions) {
		this.rolesAndPermissions = rolesAndPermissions;
	}

	public TokensEntity getTokensEntity() {
		return tokensEntity;
	}

	public void setTokensEntity(TokensEntity tokensEntity) {
		this.tokensEntity = tokensEntity;
	}

	public CustomerBasicDetailsEntity(String applicationId, String tenantId, String customerId, String customerPassword,
			String customerEmail, String userName, CustomerPersonalDetails customerEntity,
			List<AddressDetails> addressDetails, List<RolesAndPermissions> rolesAndPermissions,TokensEntity tokensEntity ) {
		super();
		this.applicationId = applicationId;
		this.tenantId = tenantId;
		this.customerId = customerId;
		this.customerPassword = customerPassword;
		this.customerEmail = customerEmail;
		this.userName = userName;
		this.customerPersonalEntity = customerEntity;
		this.addressDetails = addressDetails;
		this.rolesAndPermissions = rolesAndPermissions;
		this.tokensEntity=tokensEntity;

	}

	public CustomerBasicDetailsEntity() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "CustomerBasicDetailsEntity [customerSequenceNumber=" + customerSequenceNumber + ", applicationId="
				+ applicationId + ", tenantId=" + tenantId + ", customerId=" + customerId + ", customerPassword="
				+ customerPassword + ", customerEmail=" + customerEmail + ", userName=" + userName + ", addressDetails="
				+ addressDetails + ", customerPersonalEntity=" + customerPersonalEntity + ", rolesAndPermissions="
				+ rolesAndPermissions + ", tokensEntity=" + tokensEntity + "]";
	}

	

}
