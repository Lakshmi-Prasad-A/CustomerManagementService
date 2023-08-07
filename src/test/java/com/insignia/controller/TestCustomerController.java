/*
 * package com.insignia.controller;
 * 
 * import static org.junit.jupiter.api.Assertions.assertEquals; import static
 * org.mockito.Mockito.doNothing; import static org.mockito.Mockito.doThrow;
 * import static org.mockito.Mockito.times; import static
 * org.mockito.Mockito.verify; import static org.mockito.Mockito.when;
 * 
 * import org.junit.jupiter.api.Test; import
 * org.junit.jupiter.api.extension.ExtendWith; import org.mockito.InjectMocks;
 * import org.mockito.Mock; import org.mockito.junit.jupiter.MockitoExtension;
 * import org.springframework.http.HttpStatus; import
 * org.springframework.http.ResponseEntity;
 * 
 * import com.insignia.customExceptions.InvalidInputParametersException; import
 * com.insignia.customExceptions.TokenExpiredException; import
 * com.insignia.entity.AddressDetails; import
 * com.insignia.entity.CustomerBasicDetailsEntity; import
 * com.insignia.entity.CustomerPersonalDetails; import
 * com.insignia.model.AddressRequest; import com.insignia.model.AddressResponse;
 * import com.insignia.model.CustomerBasicDetailsRequest; import
 * com.insignia.model.CustomerManagementServiceRequest; import
 * com.insignia.model.CustomerManagementServiceResponse; import
 * com.insignia.model.CustomerPersonalDetailsRequest; import
 * com.insignia.model.CustomerPersonalDetailsResponse; import
 * com.insignia.model.RolesAndPermissionsRequest; import
 * com.insignia.model.RolesAndPermissionsResponse; import
 * com.insignia.serviceInterface.CustomerServiceInterface;
 * 
 * @ExtendWith(MockitoExtension.class) public class TestCustomerController {
 * 
 * @InjectMocks private CustomerController customerController;
 * 
 * @Mock private CustomerServiceInterface customerService;
 * 
 * @Mock private TokenExpiredException tokenExpire;
 * 
 * CustomerManagementServiceRequest customerManagementServiceRequest = new
 * CustomerManagementServiceRequest(); CustomerManagementServiceResponse
 * customerManagementServiceResponse = new CustomerManagementServiceResponse();
 * 
 * AddressRequest addressRequest = new AddressRequest(); AddressResponse
 * addressRes = new AddressResponse(); AddressDetails addressDetails = new
 * AddressDetails(); CustomerBasicDetailsRequest customerBasicDetailsRequest =
 * new CustomerBasicDetailsRequest(); CustomerBasicDetailsEntity
 * customerBasicDetailsEntity = new CustomerBasicDetailsEntity();
 * CustomerPersonalDetailsRequest customerPersonalDetailsRequest = new
 * CustomerPersonalDetailsRequest(); CustomerPersonalDetails
 * customerPersonalDetails = new CustomerPersonalDetails();
 * CustomerPersonalDetailsResponse customerPersonalDetailsResponse = new
 * CustomerPersonalDetailsResponse(); RolesAndPermissionsRequest
 * rolesAndPermissionsRequest = new RolesAndPermissionsRequest();
 * RolesAndPermissionsResponse rolesAndPermissionsResponse = new
 * RolesAndPermissionsResponse();
 * 
 * public void dataInitilization() {
 * 
 * customerBasicDetailsRequest.setApplicationId("112");
 * customerBasicDetailsRequest.setTenantId("1124");
 * customerBasicDetailsRequest.setUserId("2545");
 * customerBasicDetailsRequest.setPassword("5485");
 * customerBasicDetailsRequest.setCustomerSequenceNumber(8L);
 * customerBasicDetailsRequest.setEmailId("lakshminagu54@gmail.com");
 * customerBasicDetailsRequest.setUserName("Lakshmi");
 * 
 * customerManagementServiceRequest.setCustomerBasicDetailsRequest(
 * customerBasicDetailsRequest);
 * 
 * rolesAndPermissionsRequest.setRoleId(5);
 * rolesAndPermissionsRequest.setCustomerSequenceNumber(8L);
 * rolesAndPermissionsRequest.setPermission1(null);
 * rolesAndPermissionsRequest.setPermission2(null);
 * rolesAndPermissionsRequest.setPermission3(null);
 * rolesAndPermissionsRequest.setPermission4(null);
 * rolesAndPermissionsRequest.setPermissionChangeDate(null);
 * rolesAndPermissionsRequest.setRoleApprovedDate(null);
 * rolesAndPermissionsRequest.setRoleName("admin");
 * rolesAndPermissionsRequest.setRoleRevokedDate(null);
 * rolesAndPermissionsRequest.setUpdatedPermissions("yes");
 * 
 * customerManagementServiceRequest.setRolesAndPermissionsRequest(
 * rolesAndPermissionsRequest);
 * 
 * customerPersonalDetailsRequest.setCustomerSequenceNumber(8L);
 * customerPersonalDetailsRequest.setSequenceNumber(5);
 * customerPersonalDetailsRequest.setFirstName("lakshmi");
 * customerPersonalDetailsRequest.setLastName("Pragallapati");
 * customerPersonalDetailsRequest.setMiddleName("Nagu");
 * customerPersonalDetailsRequest.setAge("29");
 * customerPersonalDetailsRequest.setGender("female");
 * customerPersonalDetailsRequest.setCustomerMobileNumber("1234567891");
 * customerPersonalDetailsRequest.setAlternativeMobileNumber("765897653");
 * customerPersonalDetailsRequest.setCustomerEmailId("lakshmisidarth4@gmail.com"
 * ); customerPersonalDetailsRequest.setAlternativeEmailId(
 * "sidharthlakshmi4@gmail.com");
 * customerPersonalDetailsRequest.setCustomerLandlineNumber("8765987");
 * 
 * customerManagementServiceRequest.setCustomerPersonalDetailsRequest(
 * customerPersonalDetailsRequest);
 * 
 * addressRequest.setCustomerSequenceNumber(8L);
 * addressRequest.setAddressLine1("VinayakaTemple");
 * addressRequest.setAddressLine2("CinemaRoad");
 * addressRequest.setLandmark("Opp:ApolloHospital");
 * addressRequest.setCity("Kakinada"); addressRequest.setState("AndhraPradesh");
 * addressRequest.setCountry("India");
 * addressRequest.setEmailId("lakshmisidharth678@gmail.com");
 * addressRequest.setZipCode("8765");
 * addressRequest.setMobileNumber("9875689378");
 * addressRequest.setLandlineNumber("98765895");
 * addressRequest.setisBillingAddress(false);
 * addressRequest.setIsDefaultAddress(false);
 * 
 * customerManagementServiceRequest.setAddressRequest(addressRequest);
 * 
 * rolesAndPermissionsResponse.setPermission1(null);
 * rolesAndPermissionsResponse.setPermission2(null);
 * rolesAndPermissionsResponse.setPermission3(null);
 * rolesAndPermissionsResponse.setPermission4(null);
 * rolesAndPermissionsResponse.setPermissionChangeDate(null);
 * rolesAndPermissionsResponse.setRoleApprovedDate(null);
 * rolesAndPermissionsResponse.setRoleName("admin");
 * rolesAndPermissionsResponse.setRoleRevokedDate(null);
 * rolesAndPermissionsResponse.setUpdatedPermissions("yes");
 * 
 * customerManagementServiceResponse.setRolesAndPermissionsResponse(
 * rolesAndPermissionsResponse);
 * 
 * addressRes.setAddressLine1("VinayakaTemple");
 * addressRes.setAddressLine2("CinemaRoad");
 * addressRes.setLandmark("Opp:ApolloHospital"); addressRes.setCity("Kakinada");
 * addressRes.setState("AndhraPradesh"); addressRes.setCountry("India");
 * addressRes.setEmailId("lakshmisidharth678@gmail.com");
 * addressRes.setZipCode("8765"); addressRes.setMobileNumber("9875689378");
 * addressRes.setLandlineNumber("98765895");
 * addressRes.setBillingAddress(false); addressRes.setDefaultAddress(false);
 * 
 * customerManagementServiceResponse.setAddressResponse(addressRes);
 * 
 * customerPersonalDetailsResponse.setFirstName("lakshmi");
 * customerPersonalDetailsResponse.setLastName("Pragallapati");
 * customerPersonalDetailsResponse.setMiddleName("Nagu");
 * customerPersonalDetailsResponse.setAge("29");
 * customerPersonalDetailsResponse.setGender("female");
 * customerPersonalDetailsResponse.setCustomerMobileNumber("1234567891");
 * customerPersonalDetailsResponse.setAlternativeMobileNumber("765897653");
 * customerPersonalDetailsResponse.setCustomerEmailId(
 * "lakshmisidarth4@gmail.com");
 * customerPersonalDetailsResponse.setAlternativeEmailId(
 * "sidharthlakshmi4@gmail.com");
 * customerPersonalDetailsResponse.setCustomerLandlineNumber("8765987");
 * 
 * customerManagementServiceResponse.setCustomerPersonalDetailsResponse(
 * customerPersonalDetailsResponse);
 * 
 * }
 * 
 * @Test public void testSaveCustomerDetails() throws
 * InvalidInputParametersException { dataInitilization();
 * customerBasicDetailsRequest.setApplicationId(null);
 * customerBasicDetailsRequest.setTenantId(null);
 * customerBasicDetailsRequest.setUserId(null);
 * customerBasicDetailsRequest.setPassword(null);
 * 
 * addressRequest.setAddressLine1(null); addressRequest.setCity(null);
 * addressRequest.setState(null); addressRequest.setCountry(null);
 * addressRequest.setZipCode(null);
 * 
 * ResponseEntity<?> saveAllCustomerDetails = customerController
 * .saveAllCustomerDetails(customerManagementServiceRequest);
 * assertEquals(HttpStatus.BAD_REQUEST, saveAllCustomerDetails.getStatusCode());
 * 
 * ResponseEntity<?> updateCustomerDetails = customerController
 * .updateAllCustomerDetails(customerManagementServiceRequest);
 * assertEquals(HttpStatus.BAD_REQUEST, updateCustomerDetails.getStatusCode());
 * }
 * 
 * @Test public void testSaveAllCustomerDetails() throws
 * InvalidInputParametersException, Exception { dataInitilization();
 * 
 * Integer sequenceNumber = 1;
 * 
 * Long customerSequenceNumber = 8L; Object[] customerData = { 8L,
 * "JyothiTemple", "Kanakaraopeta", "Vizag" }; CustomerManagementServiceResponse
 * expectedResult = new CustomerManagementServiceResponse();
 * 
 * when(customerService.saveAllCustomerDetails(customerManagementServiceRequest)
 * ) .thenReturn(customerManagementServiceResponse); ResponseEntity<?> response
 * =
 * customerController.saveAllCustomerDetails(customerManagementServiceRequest);
 * assertEquals(HttpStatus.OK, response.getStatusCode());
 * 
 * when(customerService.updateAllCustomerDetails(
 * customerManagementServiceRequest))
 * .thenReturn(customerManagementServiceResponse); ResponseEntity<?>
 * updateAllCustomerDetails = customerController
 * .updateAllCustomerDetails(customerManagementServiceRequest);
 * assertEquals(HttpStatus.OK, response.getStatusCode());
 * 
 * doNothing().when(customerService).deleteCustomerAssociatedDetails(
 * customerSequenceNumber);
 * 
 * ResponseEntity<?> deleteCustomerAssociatedDetails = customerController
 * .deleteCustomerAssociatedDetails(customerSequenceNumber);
 * verify(customerService,
 * times(1)).deleteCustomerAssociatedDetails(customerSequenceNumber);
 * 
 * when(customerService.getAllCustomersWithDetails(customerSequenceNumber)).
 * thenReturn(expectedResult); ResponseEntity<?> getAllCustomersWithDetails =
 * customerController .getAllCustomersWithDetails(customerSequenceNumber);
 * assertEquals(HttpStatus.OK, response.getStatusCode());
 * 
 * assertEquals(HttpStatus.OK, response.getStatusCode());
 * assertEquals("Record Successfully Deleted",
 * deleteCustomerAssociatedDetails.getBody());
 * 
 * }
 * 
 * @Test public void testForTokenExpired() throws
 * InvalidInputParametersException, Exception { dataInitilization();
 * 
 * Integer sequenceNumber = 1; Long customerSequenceNumber = 8L;
 * 
 * when(customerService.saveAllCustomerDetails(customerManagementServiceRequest)
 * ) .thenThrow(new TokenExpiredException("")); ResponseEntity<?>
 * saveCustomerDetails = customerController
 * .saveAllCustomerDetails(customerManagementServiceRequest);
 * 
 * when(customerService.updateAllCustomerDetails(
 * customerManagementServiceRequest)) .thenThrow(new TokenExpiredException(""));
 * ResponseEntity<?> updateAllCustomerDetails = customerController
 * .updateAllCustomerDetails(customerManagementServiceRequest);
 * 
 * doThrow(new TokenExpiredException("")).when(customerService)
 * .deleteCustomerAssociatedDetails(customerSequenceNumber);
 * customerController.deleteCustomerAssociatedDetails(customerSequenceNumber);
 * 
 * when(customerService.getAllCustomersWithDetails(customerSequenceNumber))
 * .thenThrow(new TokenExpiredException("")); ResponseEntity<?>
 * getAllCustomersWithDetails = customerController
 * .getAllCustomersWithDetails(customerSequenceNumber);
 * 
 * assertEquals(HttpStatus.BAD_REQUEST, saveCustomerDetails.getStatusCode()); }
 * 
 * @Test public void testForException() throws InvalidInputParametersException,
 * TokenExpiredException { dataInitilization(); Integer sequenceNumber = 1; Long
 * customerSequenceNumber = 8L;
 * 
 * when(customerService.updateAllCustomerDetails(
 * customerManagementServiceRequest)) .thenThrow(new NullPointerException(""));
 * ResponseEntity<?> updateAllCustomerDetails = customerController
 * .updateAllCustomerDetails(customerManagementServiceRequest);
 * 
 * doThrow(new NullPointerException("")).when(customerService)
 * .deleteCustomerAssociatedDetails(customerSequenceNumber);
 * customerController.deleteCustomerAssociatedDetails(customerSequenceNumber);
 * 
 * when(customerService.getAllCustomersWithDetails(customerSequenceNumber))
 * .thenThrow(new NullPointerException("")); ResponseEntity<?>
 * getAllCustomersWithDetails = customerController
 * .getAllCustomersWithDetails(customerSequenceNumber);
 * 
 * assertEquals(HttpStatus.BAD_REQUEST,
 * updateAllCustomerDetails.getStatusCode()); } }
 */