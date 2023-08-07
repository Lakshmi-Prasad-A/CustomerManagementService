package com.insignia.daoImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.insignia.customExceptions.InvalidInputParametersException;
import com.insignia.daoInterface.CustomerDaoInterface;
import com.insignia.entity.AddressDetails;
import com.insignia.entity.CustomerBasicDetailsEntity;
import com.insignia.entity.CustomerPersonalDetails;
import com.insignia.entity.RolesAndPermissions;
import com.insignia.model.AddressRequest;
import com.insignia.model.AddressResponse;
import com.insignia.model.CustomerBasicDetailsRequest;
import com.insignia.model.CustomerManagementServiceResponse;
import com.insignia.model.CustomerPersonalDetailsRequest;
import com.insignia.model.CustomerPersonalDetailsResponse;
import com.insignia.model.RolesAndPermissionsRequest;
import com.insignia.model.RolesAndPermissionsResponse;
import com.insignia.repo.AddressRepository;
import com.insignia.repo.CustomerBasicDetailsRepository;
import com.insignia.repo.CustomerPersonalDetailsRepository;
import com.insignia.repo.RolesAndPermissionRepository;

@Repository
public class CustomerDaoImpl implements CustomerDaoInterface {

	@Autowired
	private CustomerPersonalDetailsRepository customerPersonalDetailsRepo;

	@Autowired
	private CustomerBasicDetailsRepository customerBasicDetailsRepo;

	@Autowired
	private AddressRepository addressRepo;

	@Autowired
	private RolesAndPermissionRepository rolesAndPermissionsRepo;

	@Autowired
	private EntityManager entityManager;
	
	public CustomerDaoImpl(AddressRepository addressRepo, CustomerPersonalDetailsRepository customerPersonalDetailsRepo, RolesAndPermissionRepository  rolesAndPermissionsRepo, EntityManager entityManager) {
		super();
		this.addressRepo = addressRepo;
		this.customerPersonalDetailsRepo=customerPersonalDetailsRepo;
		this.rolesAndPermissionsRepo=rolesAndPermissionsRepo;
		this.entityManager = entityManager;
		
	}


	
	
	@Override
	public CustomerBasicDetailsEntity saveAllCustomerDetails(CustomerBasicDetailsEntity cbde) {
		return customerBasicDetailsRepo.save(cbde);
	}
		
//	CustomerBasicDetailsEntity cbd = new CustomerBasicDetailsEntity("Cars006", "HT005", "RU003", "Lakshmi", "LakshmiNagu5@gmail.com", "Lakshmi", null, null, null,null);
//	cbd.setCustomerSequenceNumber(1L);
//	CustomerPersonalDetails cpd = new CustomerPersonalDetails("Nagu", "Appana", "Lakshmi", "29", null, null, null, null, null, null);
//	//cpd.setCustomerSequenceNumber(44L);
//	
//	List<AddressDetails> adList = new ArrayList<>();
//	AddressDetails ad  = new AddressDetails("MarketStreet", "Cinema", "Siva Temple", "Kakinada", "East", "INdia", "8765", "oiiuuemk", "inieni", "imiien", false, false);
//	adList.add(ad);
//	//ad.setCustomerSequenceNumber(1L);
//	
//	List<RolesAndPermissions> rapList = new ArrayList<>();
//	RolesAndPermissions rap = new RolesAndPermissions("admin");
//	rapList.add(rap);
//	
//	//rap.setCustomerSequenceNumber(1L);
//	
//	cbd.setCustomerPersonalEntity(cpd);
//	cbd.setAddressDetails(adList);
//	cbd.setRolesAndPermissions(rapList);
//	
//
//	try {
//		
//		 CustomerBasicDetailsEntity save = customerBasicDetailsRepo.save(cbd);
//    
//    System.err.println(save);
//    
//	}catch (Exception e) {
//		// TODO: handle exception
//		e.printStackTrace();
//	}
//    
//}


	@Transactional
	@Modifying
	@Override
	public void updateCustomerBasicDetails(CustomerBasicDetailsRequest customerBasicDetailsRequest) {

		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("UPDATE customer_basic_details SET ");

		if (customerBasicDetailsRequest.isApplicationIdUpdated()) {
			queryBuilder.append("application_id= '" + customerBasicDetailsRequest.getApplicationId()).append("'");

		}
		if (customerBasicDetailsRequest.isUserIdUpdated()) {
			queryBuilder.append(",tenant_id= '" + customerBasicDetailsRequest.getTenantId()).append("'");

		}

		if (customerBasicDetailsRequest.isUserIdUpdated()) {
			queryBuilder.append(",customer_id= '" + customerBasicDetailsRequest.getUserId()).append("'");

		}
		if (customerBasicDetailsRequest.isPasswordUpdated()) {
			queryBuilder.append(",customer_password= '" + customerBasicDetailsRequest.getPassword()).append("'");

		}
		if (customerBasicDetailsRequest.isEmailIdUpdated()) {
			queryBuilder.append(",customer_email= '" + customerBasicDetailsRequest.getEmailId()).append("'");

		}
		if (customerBasicDetailsRequest.isUserNameUpdated()) {
			queryBuilder.append(",user_name= '" + customerBasicDetailsRequest.getUserName()).append("'");

		}

		queryBuilder.append(" WHERE customer_sequence_number = :customer_sequence_number");

		String queryString = queryBuilder.toString();

		System.out.println(queryString);

		Query query = entityManager.createNativeQuery(queryString);

		query.setParameter("customer_sequence_number", customerBasicDetailsRequest.getCustomerSequenceNumber());

		query.executeUpdate();

	}

	@Transactional
	@Override
	public RolesAndPermissionsResponse updateRolesAndPermissions(
			RolesAndPermissionsRequest rolesAndPermissionsRequest) {

		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("UPDATE roles_and_permissions SET ");

		if (rolesAndPermissionsRequest.isRoleNameUpdated()) {
			queryBuilder.append("role_name= '" + rolesAndPermissionsRequest.getRoleName()).append("'");
		}
		if (rolesAndPermissionsRequest.isPermission1Updated()) {
			queryBuilder.append(",permission1= '" + rolesAndPermissionsRequest.getPermission1()).append("'");
		}
		if (rolesAndPermissionsRequest.isPermission2Updated()) {
			queryBuilder.append(",permission2= '" + rolesAndPermissionsRequest.getPermission2()).append("'");
		}
		if (rolesAndPermissionsRequest.isPermission3Updated()) {
			queryBuilder.append(",permission3= '" + rolesAndPermissionsRequest.getPermission3()).append("'");
		}
		if (rolesAndPermissionsRequest.isPermission4Updated()) {
			queryBuilder.append(",permission4= '" + rolesAndPermissionsRequest.getPermission4()).append("'");
		}
		if (rolesAndPermissionsRequest.isRoleApprovedDateUpdated()) {
			queryBuilder.append(",role_approved_date= '" + rolesAndPermissionsRequest.getRoleApprovedDate())
					.append("'");
		}
		if (rolesAndPermissionsRequest.isRoleRevokedDateUpdated()) {
			queryBuilder.append(",role_revoked_date= '" + rolesAndPermissionsRequest.getRoleRevokedDate()).append("'");
		}
		if (rolesAndPermissionsRequest.isPermissionChangeDateUpdated()) {
			queryBuilder.append(",permission_change_date= '" + rolesAndPermissionsRequest.getPermissionChangeDate())
					.append("'");
		}
		if (rolesAndPermissionsRequest.isUpdatedPermissionsUpdated()) {
			queryBuilder.append(",updated_permissions= '" + rolesAndPermissionsRequest.getUpdatedPermissions())
					.append("'");
		}
		queryBuilder.append(" WHERE role_id=:role_id and customer_sequence_number = :customer_sequence_number");

		String queryString = queryBuilder.toString();
		System.out.println(queryString);

		Query query = entityManager.createNativeQuery(queryString);

		query.setParameter("customer_sequence_number", rolesAndPermissionsRequest.getCustomerSequenceNumber());
		query.setParameter("role_id", rolesAndPermissionsRequest.getRoleId());

		query.executeUpdate();

		String selectQueryString = "SELECT * FROM roles_and_permissions WHERE role_id = :role_id";

		Query selectQuery = entityManager.createNativeQuery(selectQueryString, RolesAndPermissions.class);

		selectQuery.setParameter("role_id", rolesAndPermissionsRequest.getRoleId());

		RolesAndPermissions updatedAddress = (RolesAndPermissions) selectQuery.getSingleResult();

		RolesAndPermissionsResponse response = createResponse(updatedAddress);

		return response;
	}

	private RolesAndPermissionsResponse createResponse(RolesAndPermissions updatedAddress) {
		RolesAndPermissionsResponse response = new RolesAndPermissionsResponse();
		response.setRoleId(updatedAddress.getRoleId());
		response.setRoleName(updatedAddress.getRoleName());
		response.setPermission1(updatedAddress.getPermission1());
		response.setPermission2(updatedAddress.getPermission2());
		response.setPermission3(updatedAddress.getPermission3());
		response.setPermission4(updatedAddress.getPermission4());
		response.setRoleApprovedDate(updatedAddress.getRoleApprovedDate());
		response.setRoleRevokedDate(updatedAddress.getRoleRevokedDate());
		response.setPermissionChangeDate(updatedAddress.getPermissionChangeDate());
		response.setUpdatedPermissions(updatedAddress.getUpdatedPermissions());

		return response;
	}

	@Transactional
	@Modifying
	@Override
	public AddressResponse updateAddressDetails(AddressRequest addressReq) {
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("UPDATE address_details SET ");

		if (addressReq.isAddressLine1Updated()) {
			queryBuilder.append("address_line_1= '" + addressReq.getAddressLine1()).append("'");

		}
		if (addressReq.isAddressLine2Updated()) {
			queryBuilder.append(",address_line_2= '" + addressReq.getAddressLine2()).append("'");

		}

		if (addressReq.isLandMarkUpdated()) {
			queryBuilder.append(",landmark= '" + addressReq.getLandmark()).append("'");

		}

		if (addressReq.isCityUpdated()) {
			queryBuilder.append(",city= '" + addressReq.getCity()).append("'");

		}
		if (addressReq.isStateUpdated()) {
			queryBuilder.append(",state= '" + addressReq.getState()).append("'");

		}
		if (addressReq.isCountryUpdated()) {
			queryBuilder.append(",country= '" + addressReq.getCountry()).append("'");

		}
		if (addressReq.isZipCodeUpdated()) {
			queryBuilder.append(",Zip_code= '" + addressReq.getZipCode()).append("'");

		}
		if (addressReq.isMobileNumberUpdated()) {
			queryBuilder.append(",mobile_number= '" + addressReq.getMobileNumber()).append("'");

		}
		if (addressReq.isLandLineNumberUpdated()) {
			queryBuilder.append(",landline_number= '" + addressReq.getLandlineNumber()).append("'");

		}
		if (addressReq.isEmailAddressUpdated()) {
			queryBuilder.append(",email_id= '" + addressReq.getEmailId()).append("'");

		}
		if (addressReq.isDefaultAddressUpdated()) {
			queryBuilder.append(",is_default_address= '" + addressReq.getIsDefaultAddress()).append("'");

		}
		if (addressReq.isBillingAddressUpdated()) {
			queryBuilder.append(",is_billing_address= '" + addressReq.getIsBillingAddress()).append("'");

		}

		queryBuilder.append(
				" WHERE sequence_number=:sequence_number and customer_sequence_number = :customer_sequence_number");

		String queryString = queryBuilder.toString();

		Query query = entityManager.createNativeQuery(queryString);

		query.setParameter("customer_sequence_number", addressReq.getCustomerSequenceNumber());
		query.setParameter("sequence_number", addressReq.getSequenceNumber());

		query.executeUpdate();

		String selectQueryString = "SELECT * FROM address_details WHERE sequence_number = :sequence_number";

		Query selectQuery = entityManager.createNativeQuery(selectQueryString, AddressDetails.class);

		selectQuery.setParameter("sequence_number", addressReq.getSequenceNumber());

		AddressDetails updatedAddress = (AddressDetails) selectQuery.getSingleResult();

		AddressResponse response = createResponse(updatedAddress);

		return response;
	}

	private AddressResponse createResponse(AddressDetails updatedAddress) {
		AddressResponse response = new AddressResponse();
		response.setSequenceNumber(updatedAddress.getSequenceNumber());
		response.setAddressLine1(updatedAddress.getAddressLine1());
		response.setAddressLine2(updatedAddress.getAddressLine2());
		response.setLandmark(updatedAddress.getLandmark());
		response.setCity(updatedAddress.getCity());
		response.setState(updatedAddress.getState());
		response.setCountry(updatedAddress.getCountry());
		response.setZipCode(updatedAddress.getZipCode());
		response.setMobileNumber(updatedAddress.getMobileNumber());
		response.setLandlineNumber(updatedAddress.getLandlineNumber());
		response.setEmailId(updatedAddress.getEmailId());
		response.setDefaultAddress(updatedAddress.isDefaultAddress());
		response.setBillingAddress(updatedAddress.isBillingAddress());
		return response;
	}

	@Transactional
	@Override
	public CustomerPersonalDetailsResponse updateCustomerPersonalDetails(
			CustomerPersonalDetailsRequest customerPersonalBasicRequest) {

		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("UPDATE customer_personal_details SET ");

		if (customerPersonalBasicRequest.isFirstNameUpdated()) {
			queryBuilder.append("first_name= '" + customerPersonalBasicRequest.getFirstName()).append("'");
		}
		if (customerPersonalBasicRequest.isLastNameUpdated()) {
			queryBuilder.append(",last_name= '" + customerPersonalBasicRequest.getLastName()).append("'");
		}
		if (customerPersonalBasicRequest.isMiddleNameUpdated()) {
			queryBuilder.append(",middle_name= '" + customerPersonalBasicRequest.getMiddleName()).append("'");
		}
		if (customerPersonalBasicRequest.isAgeUpdated()) {
			queryBuilder.append(",age= '" + customerPersonalBasicRequest.getAge()).append("'");
		}
		if (customerPersonalBasicRequest.isGenderUpdated()) {
			queryBuilder.append(",gender= '" + customerPersonalBasicRequest.getGender()).append("'");
		}
		if (customerPersonalBasicRequest.isCustomerEmailIdUpdated()) {
			queryBuilder.append(",email_id= '" + customerPersonalBasicRequest.getCustomerEmailId()).append("'");
		}
		if (customerPersonalBasicRequest.isAlternativeEmailIdUpdated()) {
			queryBuilder.append(",alternative_email_id= '" + customerPersonalBasicRequest.getAlternativeEmailId())
					.append("'");
		}
		if (customerPersonalBasicRequest.isCustomerMobileNumberUpdated()) {
			queryBuilder.append(",customer_mobile_number= '" + customerPersonalBasicRequest.getCustomerMobileNumber())
					.append("'");
		}
		if (customerPersonalBasicRequest.isAlternativeMobileNumberUpdated()) {
			queryBuilder
					.append(",alternative_mobile_number= '" + customerPersonalBasicRequest.getAlternativeMobileNumber())
					.append("'");
		}
		if (customerPersonalBasicRequest.isCustomerLandlineUpdated()) {
			queryBuilder
					.append(",customer_landline_number= '" + customerPersonalBasicRequest.getCustomerLandlineNumber())
					.append("'");
		}

		queryBuilder.append(
				" WHERE sequence_number=:sequence_number and customer_sequence_number = :customer_sequence_number");

		String queryString = queryBuilder.toString();

		Query query = entityManager.createNativeQuery(queryString);

		query.setParameter("customer_sequence_number", customerPersonalBasicRequest.getCustomerSequenceNumber());

		query.executeUpdate();

		String selectQueryString = "SELECT * FROM customer_personal_details WHERE sequence_number = :sequence_number";

		Query selectQuery = entityManager.createNativeQuery(selectQueryString, CustomerPersonalDetails.class);
		selectQuery.setParameter("sequence_number", customerPersonalBasicRequest.getSequenceNumber());

		CustomerPersonalDetails updatedAddress = (CustomerPersonalDetails) selectQuery.getSingleResult();

		CustomerPersonalDetailsResponse customerPersonalDetailsResponse = createResponse(updatedAddress);

		return customerPersonalDetailsResponse;
	}

	private CustomerPersonalDetailsResponse createResponse(CustomerPersonalDetails updatedAddress) {
		CustomerPersonalDetailsResponse customerPersonalDetailsResponse = new CustomerPersonalDetailsResponse();
		customerPersonalDetailsResponse.setFirstName(updatedAddress.getFirstName());
		customerPersonalDetailsResponse.setLastName(updatedAddress.getLastName());
		customerPersonalDetailsResponse.setMiddleName(updatedAddress.getMiddleName());
		customerPersonalDetailsResponse.setAge(updatedAddress.getAge());
		customerPersonalDetailsResponse.setGender(updatedAddress.getGender());
		customerPersonalDetailsResponse.setCustomerEmailId(updatedAddress.getCustomerEmailId());
		customerPersonalDetailsResponse.setAlternativeEmailId(updatedAddress.getAlternativeEmailId());
		customerPersonalDetailsResponse.setCustomerMobileNumber(updatedAddress.getCustomerMobileNumber());
		customerPersonalDetailsResponse.setAlternativeMobileNumber(updatedAddress.getAlternativeMobileNumber());
		customerPersonalDetailsResponse.setCustomerLandlineNumber(updatedAddress.getCustomerLandlineNumber());
		return customerPersonalDetailsResponse;
	}

	@Transactional
	@Override
	public void deleteCustomerAssociatedDetails(Long customerSequenceNumber) {

		CustomerBasicDetailsEntity customer = customerBasicDetailsRepo.findById(customerSequenceNumber).orElse(null);
        if (customer != null) {
        	customerBasicDetailsRepo.delete(customer);
        }
	}

	@Override
	public Boolean isTokenNotValid(Long customer_sequence_number) {
		return customerBasicDetailsRepo.isTokenNotValid(customer_sequence_number).isEmpty();
	}
	

	@Override
	public CustomerBasicDetailsEntity getAllCustomerData(Long customerSequenceNumber) throws InvalidInputParametersException {
	    if (customerSequenceNumber != null) {
	        CustomerBasicDetailsEntity customer = customerBasicDetailsRepo.findById(customerSequenceNumber).orElse(null);

	        if (customer != null) {
	        	
	        	return customer;    
	        }	    
	    }
	    throw new InvalidInputParametersException("NO Data Available");
	    
	    }
}

	

	


