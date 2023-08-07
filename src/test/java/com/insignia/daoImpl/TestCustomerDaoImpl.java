//package com.insignia.daoImpl;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.doNothing;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.persistence.EntityManager;
//import javax.persistence.Query;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.context.ActiveProfiles;
//
//import com.insignia.customExceptions.InvalidInputParametersException;
//import com.insignia.entity.AddressDetails;
//import com.insignia.entity.CustomerBasicDetailsEntity;
//import com.insignia.entity.CustomerPersonalDetails;
//import com.insignia.entity.RolesAndPermissions;
//import com.insignia.model.AddressRequest;
//import com.insignia.model.AddressResponse;
//import com.insignia.model.CustomerBasicDetailsRequest;
//import com.insignia.model.CustomerManagementServiceRequest;
//import com.insignia.model.CustomerManagementServiceResponse;
//import com.insignia.model.CustomerPersonalDetailsRequest;
//import com.insignia.model.CustomerPersonalDetailsResponse;
//import com.insignia.model.RolesAndPermissionsRequest;
//import com.insignia.model.RolesAndPermissionsResponse;
//import com.insignia.repo.AddressRepository;
//import com.insignia.repo.CustomerBasicDetailsRepository;
//import com.insignia.repo.CustomerPersonalDetailsRepository;
//import com.insignia.repo.RolesAndPermissionRepository;
//
//@ExtendWith(MockitoExtension.class)
//@DataJpaTest
//@ActiveProfiles("test")
//
//public class TestCustomerDaoImpl {
//
//	@Mock
//	private CustomerBasicDetailsRepository customerBasicDetailsRepo;
//
//	@Mock
//	private CustomerPersonalDetailsRepository customerPersonalDetailsRepo;
//
//	@Mock
//	private RolesAndPermissionRepository rolesAndPermissionsRepo;
//
//	@Mock
//	private AddressRepository addressDetailsRepo;
//
//	@InjectMocks
//	private CustomerDaoImpl customerDaoImpl;
//
//	CustomerManagementServiceRequest customerManagementServiceRequest = new CustomerManagementServiceRequest();
//	CustomerManagementServiceResponse customerManagementServiceResponse = new CustomerManagementServiceResponse();
//
//	AddressRequest addressRequest = new AddressRequest();
//	AddressResponse addressRes = new AddressResponse();
//	AddressDetails addressDetails = new AddressDetails();
//	CustomerBasicDetailsRequest customerBasicDetailsRequest = new CustomerBasicDetailsRequest();
//	CustomerBasicDetailsEntity customerBasicDetailsEntity = new CustomerBasicDetailsEntity();
//	CustomerPersonalDetailsRequest customerPersonalDetailsRequest = new CustomerPersonalDetailsRequest();
//	CustomerPersonalDetails customerPersonalDetails = new CustomerPersonalDetails();
//	CustomerPersonalDetailsResponse customerPersonalDetailsResponse = new CustomerPersonalDetailsResponse();
//	RolesAndPermissionsRequest rolesAndPermissionsRequest = new RolesAndPermissionsRequest();
//	RolesAndPermissionsResponse rolesAndPermissionsResponse = new RolesAndPermissionsResponse();
//
//	public void dataInitilization() {
//
//		customerBasicDetailsRequest.setApplicationId("112");
//		customerBasicDetailsRequest.setTenantId("1124");
//		customerBasicDetailsRequest.setUserId("2545");
//		customerBasicDetailsRequest.setPassword("5485");
//		customerBasicDetailsRequest.setCustomerSequenceNumber(8L);
//		customerBasicDetailsRequest.setEmailId("lakshminagu54@gmail.com");
//		customerBasicDetailsRequest.setUserName("Lakshmi");
//
//		customerManagementServiceRequest.setCustomerBasicDetailsRequest(customerBasicDetailsRequest);
//
//		rolesAndPermissionsRequest.setRoleId(5);
//		rolesAndPermissionsRequest.setCustomerSequenceNumber(8L);
//		rolesAndPermissionsRequest.setPermission1(null);
//		rolesAndPermissionsRequest.setPermission2(null);
//		rolesAndPermissionsRequest.setPermission3(null);
//		rolesAndPermissionsRequest.setPermission4(null);
//		rolesAndPermissionsRequest.setPermissionChangeDate(null);
//		rolesAndPermissionsRequest.setRoleApprovedDate(null);
//		rolesAndPermissionsRequest.setRoleName("admin");
//		rolesAndPermissionsRequest.setRoleRevokedDate(null);
//		rolesAndPermissionsRequest.setUpdatedPermissions("yes");
//
//		customerManagementServiceRequest.setRolesAndPermissionsRequest(rolesAndPermissionsRequest);
//
//		customerPersonalDetailsRequest.setCustomerSequenceNumber(8L);
//		customerPersonalDetailsRequest.setSequenceNumber(5);
//		customerPersonalDetailsRequest.setFirstName("lakshmi");
//		customerPersonalDetailsRequest.setLastName("Pragallapati");
//		customerPersonalDetailsRequest.setMiddleName("Nagu");
//		customerPersonalDetailsRequest.setAge("29");
//		customerPersonalDetailsRequest.setGender("female");
//		customerPersonalDetailsRequest.setCustomerMobileNumber("1234567891");
//		customerPersonalDetailsRequest.setAlternativeMobileNumber("765897653");
//		customerPersonalDetailsRequest.setCustomerEmailId("lakshmisidarth4@gmail.com");
//		customerPersonalDetailsRequest.setAlternativeEmailId("sidharthlakshmi4@gmail.com");
//		customerPersonalDetailsRequest.setCustomerLandlineNumber("8765987");
//
//		customerManagementServiceRequest.setCustomerPersonalDetailsRequest(customerPersonalDetailsRequest);
//
//		addressRequest.setCustomerSequenceNumber(8L);
//		addressRequest.setAddressLine1("VinayakaTemple");
//		addressRequest.setAddressLine2("CinemaRoad");
//		addressRequest.setLandmark("Opp:ApolloHospital");
//		addressRequest.setCity("Kakinada");
//		addressRequest.setState("AndhraPradesh");
//		addressRequest.setCountry("India");
//		addressRequest.setEmailId("lakshmisidharth678@gmail.com");
//		addressRequest.setZipCode("8765");
//		addressRequest.setMobileNumber("9875689378");
//		addressRequest.setLandlineNumber("98765895");
//		addressRequest.setisBillingAddress(false);
//		addressRequest.setIsDefaultAddress(false);
//
//		customerManagementServiceRequest.setAddressRequest(addressRequest);
//
//		rolesAndPermissionsResponse.setPermission1(null);
//		rolesAndPermissionsResponse.setPermission2(null);
//		rolesAndPermissionsResponse.setPermission3(null);
//		rolesAndPermissionsResponse.setPermission4(null);
//		rolesAndPermissionsResponse.setPermissionChangeDate(null);
//		rolesAndPermissionsResponse.setRoleApprovedDate(null);
//		rolesAndPermissionsResponse.setRoleName("admin");
//		rolesAndPermissionsResponse.setRoleRevokedDate(null);
//		rolesAndPermissionsResponse.setUpdatedPermissions("yes");
//
//		customerManagementServiceResponse.setRolesAndPermissionsResponse(rolesAndPermissionsResponse);
//
//		addressRes.setAddressLine1("VinayakaTemple");
//		addressRes.setAddressLine2("CinemaRoad");
//		addressRes.setLandmark("Opp:ApolloHospital");
//		addressRes.setCity("Kakinada");
//		addressRes.setState("AndhraPradesh");
//		addressRes.setCountry("India");
//		addressRes.setEmailId("lakshmisidharth678@gmail.com");
//		addressRes.setZipCode("8765");
//		addressRes.setMobileNumber("9875689378");
//		addressRes.setLandlineNumber("98765895");
//		addressRes.setBillingAddress(false);
//		addressRes.setDefaultAddress(false);
//
//		customerManagementServiceResponse.setAddressResponse(addressRes);
//
//		customerPersonalDetailsResponse.setFirstName("lakshmi");
//		customerPersonalDetailsResponse.setLastName("Pragallapati");
//		customerPersonalDetailsResponse.setMiddleName("Nagu");
//		customerPersonalDetailsResponse.setAge("29");
//		customerPersonalDetailsResponse.setGender("female");
//		customerPersonalDetailsResponse.setCustomerMobileNumber("1234567891");
//		customerPersonalDetailsResponse.setAlternativeMobileNumber("765897653");
//		customerPersonalDetailsResponse.setCustomerEmailId("lakshmisidarth4@gmail.com");
//		customerPersonalDetailsResponse.setAlternativeEmailId("sidharthlakshmi4@gmail.com");
//		customerPersonalDetailsResponse.setCustomerLandlineNumber("8765987");
//
//		customerManagementServiceResponse.setCustomerPersonalDetailsResponse(customerPersonalDetailsResponse);
//
//	}
//
//	@Test
//	public void testSaveAddress() throws InvalidInputParametersException {
//
////		dataInitilization();
////
////		doNothing().when(customerBasicDetailsRepo).saveCustomerBasicDetails(any(), any(), any(), any(), any(), any());
////
////		customerDaoImpl.saveCustomerBasicDetails(customerBasicDetailsRequest);
////
////		doNothing().when(rolesAndPermissionsRepo).saveRolesAndPermissions(any(), any(), any(), any(), any(), any(),
////				any(), any(), any(), any());
////
////		customerDaoImpl.saveRolesAndPermissions(rolesAndPermissionsRequest);
////
////		doNothing().when(customerPersonalDetailsRepo).saveCustomerPersonalDetails(any(), any(), any(), any(), any(),
////				any(), any(), any(), any(), any(), any());
////
////		customerDaoImpl.saveCustomerPersonalDetails(customerPersonalDetailsRequest);
////
////		doNothing().when(addressDetailsRepo).saveAddressDetails(any(), any(), any(), any(), any(), any(), any(), any(),
////				any(), any(), any(), any(), any());
////
////		customerDaoImpl.saveAddressDetails(addressRequest);
//	}
//
//	@Test
//	public void isTokenNotValid() {
//
//		Long customer_sequence_number = 8L;
//
//		customerDaoImpl.isTokenNotValid(customer_sequence_number);
//
//		verify(customerBasicDetailsRepo, times(1)).isTokenNotValid(customer_sequence_number);
//	}
//
//	@Test
//	public void getAllCustomerDetails() {
//		dataInitilization();
//
//		List<RolesAndPermissions> rolesAndPermissionsList = new ArrayList<>();
//
//		RolesAndPermissions rolesAndPermissionsRequest = new RolesAndPermissions();
//
//		rolesAndPermissionsRequest.setRoleId(5);
//		rolesAndPermissionsRequest.setCustomerSequenceNumber(8L);
//		rolesAndPermissionsRequest.setPermission1(null);
//		rolesAndPermissionsRequest.setPermission2(null);
//		rolesAndPermissionsRequest.setPermission3(null);
//		rolesAndPermissionsRequest.setPermission4(null);
//		rolesAndPermissionsRequest.setPermissionChangeDate(null);
//		rolesAndPermissionsRequest.setRoleApprovedDate(null);
//		rolesAndPermissionsRequest.setRoleName("admin");
//		rolesAndPermissionsRequest.setRoleRevokedDate(null);
//		rolesAndPermissionsRequest.setUpdatedPermissions("yes");
//
//		RolesAndPermissions rolesAndPermissionsRequest2 = new RolesAndPermissions();
//		rolesAndPermissionsRequest2.setRoleId(6);
//		rolesAndPermissionsRequest2.setCustomerSequenceNumber(8L);
//		rolesAndPermissionsRequest2.setPermission1(null);
//		rolesAndPermissionsRequest2.setPermission2(null);
//		rolesAndPermissionsRequest2.setPermission3(null);
//		rolesAndPermissionsRequest2.setPermission4(null);
//		rolesAndPermissionsRequest2.setPermissionChangeDate(null);
//		rolesAndPermissionsRequest2.setRoleApprovedDate(null);
//		rolesAndPermissionsRequest2.setRoleName("user");
//		rolesAndPermissionsRequest2.setRoleRevokedDate(null);
//		rolesAndPermissionsRequest2.setUpdatedPermissions("yes");
//
//		rolesAndPermissionsList.add(rolesAndPermissionsRequest);
//		rolesAndPermissionsList.add(rolesAndPermissionsRequest2);
//
//		CustomerBasicDetailsEntity customerBasicDetailsRequest = new CustomerBasicDetailsEntity();
//
//		customerBasicDetailsRequest.setApplicationId("112");
//		customerBasicDetailsRequest.setTenantId("1124");
//		customerBasicDetailsRequest.setCustomerId("2545");
//		customerBasicDetailsRequest.setCustomerPassword("5485");
//		customerBasicDetailsRequest.setCustomerSequenceNumber(8L);
//		customerBasicDetailsRequest.setCustomerEmail("lakshminagu54@gmail.com");
//		customerBasicDetailsRequest.setUserName("Lakshmi");
//
//		CustomerPersonalDetails customerPersonalDetailsRequest = new CustomerPersonalDetails();
//
//		customerPersonalDetailsRequest.setCustomerSequenceNumber(8L);
//		customerPersonalDetailsRequest.setSequenceNumber(5L);
//		customerPersonalDetailsRequest.setFirstName("lakshmi");
//		customerPersonalDetailsRequest.setLastName("Pragallapati");
//		customerPersonalDetailsRequest.setMiddleName("Nagu");
//		customerPersonalDetailsRequest.setAge("29");
//		customerPersonalDetailsRequest.setGender("female");
//		customerPersonalDetailsRequest.setCustomerMobileNumber("1234567891");
//		customerPersonalDetailsRequest.setAlternativeMobileNumber("765897653");
//		customerPersonalDetailsRequest.setCustomerEmailId("lakshmisidarth4@gmail.com");
//		customerPersonalDetailsRequest.setAlternativeEmailId("sidharthlakshmi4@gmail.com");
//		customerPersonalDetailsRequest.setCustomerLandlineNumber("8765987");
//
//		List<AddressDetails> addressDetailsList = new ArrayList<>();
//
//		AddressDetails addressRequest = new AddressDetails();
//		addressRequest.setCustomerSequenceNumber(8L);
//		addressRequest.setAddressLine1("VinayakaTemple");
//		addressRequest.setAddressLine2("CinemaRoad");
//		addressRequest.setLandmark("Opp:ApolloHospital");
//		addressRequest.setCity("Kakinada");
//		addressRequest.setState("AndhraPradesh");
//		addressRequest.setCountry("India");
//		addressRequest.setEmailId("lakshmisidharth678@gmail.com");
//		addressRequest.setZipCode("8765");
//		addressRequest.setMobileNumber("9875689378");
//		addressRequest.setLandlineNumber("98765895");
//		addressRequest.setBillingAddress(false);
//		addressRequest.setDefaultAddress(false);
//
//		AddressDetails addressRequest2 = new AddressDetails();
//		addressRequest2.setCustomerSequenceNumber(8L);
//		addressRequest2.setAddressLine1("VinayakaTemple");
//		addressRequest2.setAddressLine2("CinemaRoad");
//		addressRequest2.setLandmark("Opp:ApolloHospital");
//		addressRequest2.setCity("Kakinada");
//		addressRequest2.setState("AndhraPradesh");
//		addressRequest2.setCountry("India");
//		addressRequest2.setEmailId("lakshmisidharth678@gmail.com");
//		addressRequest2.setZipCode("8765");
//		addressRequest2.setMobileNumber("9875689378");
//		addressRequest2.setLandlineNumber("98765895");
//		addressRequest2.setBillingAddress(false);
//		addressRequest2.setDefaultAddress(false);
//
//		addressDetailsList.add(addressRequest);
//		addressDetailsList.add(addressRequest2);
//
//		Long customerSequenceNumber = 9L;
//
//		when(rolesAndPermissionsRepo.getRolesAndPermissionsList(customerSequenceNumber))
//				.thenReturn(rolesAndPermissionsList);
//
//		when(addressDetailsRepo.getAddressList(customerSequenceNumber)).thenReturn(addressDetailsList);
//
//		when(customerPersonalDetailsRepo.getCustomerPersonalDetails(customerSequenceNumber))
//				.thenReturn(customerPersonalDetailsRequest);
//
//		customerDaoImpl.getAllCustomersWithDetails(customerSequenceNumber);
//
//	}
//
//	@Test
//	public void testDeleteCustomerAssociatedDetails() {
//
//		EntityManager entityManager = mock(EntityManager.class);
//
//		Query selectQuery = mock(Query.class);
//		Query nativeQuery = mock(Query.class);
//
//		customerDaoImpl = new CustomerDaoImpl(addressDetailsRepo, customerPersonalDetailsRepo, rolesAndPermissionsRepo,
//				entityManager);
//
//		when(entityManager.createNativeQuery(anyString())).thenReturn(nativeQuery);
//
//		Long customerSequenceNumber = 123L;
//
//		// Mock the Query objects
//		Query mockDeleteRolesAndPermissionsQuery = mock(Query.class);
//		Query mockDeleteTokensQuery = mock(Query.class);
//		Query mockDeleteAddressQuery = mock(Query.class);
//		Query mockDeleteCustomerPersonalDetailsQuery = mock(Query.class);
//		Query mockDeleteCustomerBasicDetailsQuery = mock(Query.class);
//
//		// Set up the necessary mock behavior
//		when(entityManager.createNativeQuery(
//				"DELETE FROM roles_and_permissions WHERE customer_sequence_number =:customer_sequence_number"))
//				.thenReturn(mockDeleteRolesAndPermissionsQuery);
//		when(entityManager.createNativeQuery(
//				"DELETE FROM tokens_table WHERE customer_sequence_number =:customer_sequence_number"))
//				.thenReturn(mockDeleteTokensQuery);
//		when(entityManager.createNativeQuery(
//				"DELETE FROM address_details WHERE customer_sequence_number =:customer_sequence_number"))
//				.thenReturn(mockDeleteAddressQuery);
//		when(entityManager.createNativeQuery(
//				"DELETE FROM customer_personal_details WHERE customer_sequence_number =:customer_sequence_number"))
//				.thenReturn(mockDeleteCustomerPersonalDetailsQuery);
//		when(entityManager.createNativeQuery(
//				"DELETE FROM customer_basic_details WHERE customer_sequence_number =:customer_sequence_number"))
//				.thenReturn(mockDeleteCustomerBasicDetailsQuery);
//
//		customerDaoImpl.deleteCustomerAssociatedDetails(customerSequenceNumber);
//
//		// Verify that the setParameter and executeUpdate methods are called on the
//		// mocked Query objects
//		verify(mockDeleteRolesAndPermissionsQuery).setParameter("customer_sequence_number", customerSequenceNumber);
//		verify(mockDeleteTokensQuery).setParameter("customer_sequence_number", customerSequenceNumber);
//		verify(mockDeleteAddressQuery).setParameter("customer_sequence_number", customerSequenceNumber);
//		verify(mockDeleteCustomerPersonalDetailsQuery).setParameter("customer_sequence_number", customerSequenceNumber);
//		verify(mockDeleteCustomerBasicDetailsQuery).setParameter("customer_sequence_number", customerSequenceNumber);
//
//		// Verify that the executeUpdate method is called on the mocked Query objects
//		verify(mockDeleteRolesAndPermissionsQuery).executeUpdate();
//		verify(mockDeleteTokensQuery).executeUpdate();
//		verify(mockDeleteAddressQuery).executeUpdate();
//		verify(mockDeleteCustomerPersonalDetailsQuery).executeUpdate();
//		verify(mockDeleteCustomerBasicDetailsQuery).executeUpdate();
//	}
//
//}
