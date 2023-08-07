/*
 * package com.insignia.serviceImpl;
 * 
 * import static org.junit.jupiter.api.Assertions.assertEquals; import static
 * org.junit.jupiter.api.Assertions.assertThrows; import static
 * org.mockito.ArgumentMatchers.any; import static
 * org.mockito.Mockito.doNothing; import static org.mockito.Mockito.never;
 * import static org.mockito.Mockito.times; import static
 * org.mockito.Mockito.verify; import static org.mockito.Mockito.when;
 * 
 * import org.junit.jupiter.api.Test; import
 * org.junit.jupiter.api.extension.ExtendWith; import org.mockito.InjectMocks;
 * import org.mockito.Mock; import org.mockito.junit.jupiter.MockitoExtension;
 * 
 * import com.insignia.customExceptions.InvalidInputParametersException; import
 * com.insignia.customExceptions.TokenExpiredException; import
 * com.insignia.daoInterface.CustomerDaoInterface; import
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
 * com.insignia.model.RolesAndPermissionsResponse;
 * 
 * @ExtendWith(MockitoExtension.class) public class TestCustomerServiceImpl {
 * 
 * @InjectMocks private CustomerServiceImpl customerServiceImpl;
 * 
 * @Mock private CustomerDaoInterface customerRepo;
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
 * addressRequest.setLandlineNumber("98765895:");
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
 * @Test public void testSaveCustomerBasicDetails() throws Exception {
 * CustomerManagementServiceRequest request = new
 * CustomerManagementServiceRequest(); CustomerBasicDetailsRequest
 * basicDetailsRequest = new CustomerBasicDetailsRequest();
 * basicDetailsRequest.setPassword("sample_password");
 * request.setCustomerBasicDetailsRequest(basicDetailsRequest);
 * CustomerManagementServiceResponse response =
 * customerServiceImpl.saveAllCustomerDetails(request); verify(customerRepo,
 * times(1)).saveCustomerBasicDetails(basicDetailsRequest);
 * assertEquals("Successfully created Customer Basic Details",
 * response.getSuccessMessage());
 * 
 * }
 * 
 * @Test public void
 * testForSaveRolesAndCustomerPersonalDetailsAndAddressDetails() throws
 * Exception { dataInitilization();
 * customerManagementServiceRequest.setCustomerBasicDetailsRequest(null);
 * 
 * when(customerRepo.saveRolesAndPermissions(any(RolesAndPermissionsRequest.
 * class))) .thenReturn(rolesAndPermissionsResponse); // Set
 * someSavedCustomerPersonalDetails as per your // implementation
 * CustomerManagementServiceResponse response = customerServiceImpl
 * .saveAllCustomerDetails(customerManagementServiceRequest);
 * 
 * verify(customerRepo,
 * times(1)).saveRolesAndPermissions(rolesAndPermissionsRequest);
 * assertEquals(rolesAndPermissionsResponse,
 * response.getRolesAndPermissionsResponse()); }
 * 
 * @Test public void testForSuccessUpdateAllCustomerDetails() throws
 * InvalidInputParametersException, TokenExpiredException {
 * 
 * Long customerSequenceNumber = 8L; CustomerManagementServiceRequest request1 =
 * new CustomerManagementServiceRequest(); CustomerBasicDetailsRequest
 * basicDetailsRequest = new CustomerBasicDetailsRequest();
 * basicDetailsRequest.setCustomerSequenceNumber(8L);
 * request1.setCustomerBasicDetailsRequest(basicDetailsRequest);
 * doNothing().when(customerRepo).updateCustomerBasicDetails(any(
 * CustomerBasicDetailsRequest.class));
 * 
 * CustomerManagementServiceRequest request2 = new
 * CustomerManagementServiceRequest(); RolesAndPermissionsRequest
 * rolesAndPermissionsRequest = new RolesAndPermissionsRequest();
 * rolesAndPermissionsRequest.setCustomerSequenceNumber(8L);
 * request2.setRolesAndPermissionsRequest(rolesAndPermissionsRequest);
 * RolesAndPermissionsResponse rolesAndPermissionsResponse = new
 * RolesAndPermissionsResponse();
 * when(customerRepo.updateRolesAndPermissions(any(RolesAndPermissionsRequest.
 * class))) .thenReturn(rolesAndPermissionsResponse);
 * 
 * CustomerManagementServiceRequest request3 = new
 * CustomerManagementServiceRequest(); CustomerPersonalDetailsRequest
 * personalDetailsRequest = new CustomerPersonalDetailsRequest();
 * personalDetailsRequest.setCustomerSequenceNumber(8L);
 * request3.setCustomerPersonalDetailsRequest(personalDetailsRequest);
 * CustomerPersonalDetailsResponse personalDetailsResponse = new
 * CustomerPersonalDetailsResponse();
 * when(customerRepo.updateCustomerPersonalDetails(any(
 * CustomerPersonalDetailsRequest.class))) .thenReturn(personalDetailsResponse);
 * 
 * CustomerManagementServiceRequest request4 = new
 * CustomerManagementServiceRequest(); AddressRequest addressRequest = new
 * AddressRequest(); addressRequest.setCustomerSequenceNumber(8L);
 * request4.setAddressRequest(addressRequest); AddressResponse addressResponse =
 * new AddressResponse();
 * when(customerRepo.updateAddressDetails(any(AddressRequest.class))).thenReturn
 * (addressResponse);
 * 
 * CustomerManagementServiceResponse response1 =
 * customerServiceImpl.updateAllCustomerDetails(request1);
 * CustomerManagementServiceResponse response2 =
 * customerServiceImpl.updateAllCustomerDetails(request2);
 * CustomerManagementServiceResponse response3 =
 * customerServiceImpl.updateAllCustomerDetails(request3);
 * CustomerManagementServiceResponse response4 =
 * customerServiceImpl.updateAllCustomerDetails(request4);
 * 
 * verify(customerRepo,
 * times(1)).updateCustomerBasicDetails(basicDetailsRequest);
 * verify(customerRepo,
 * times(1)).updateRolesAndPermissions(rolesAndPermissionsRequest);
 * verify(customerRepo,
 * times(1)).updateCustomerPersonalDetails(personalDetailsRequest);
 * verify(customerRepo, times(1)).updateAddressDetails(addressRequest);
 * 
 * assertEquals("Successfully Updated Customer Basic Details",
 * response1.getSuccessMessage()); assertEquals(rolesAndPermissionsResponse,
 * response2.getRolesAndPermissionsResponse());
 * assertEquals(personalDetailsResponse,
 * response3.getCustomerPersonalDetailsResponse());
 * assertEquals(addressResponse, response4.getAddressResponse()); }
 * 
 * @Test void testForTokenExpiredExceptionCustomerBasicDetails() { Integer
 * sequenceNumber = 1; Long customerSequenceNumber = 8L; dataInitilization();
 * 
 * when(customerRepo.isTokenNotValid(customerSequenceNumber)).thenReturn(true);
 * assertThrows(TokenExpiredException.class, () -> {
 * customerServiceImpl.updateAllCustomerDetails(customerManagementServiceRequest
 * ); }); verify(customerRepo,
 * never()).updateCustomerBasicDetails(any(CustomerBasicDetailsRequest.class));
 * 
 * when(customerRepo.isTokenNotValid(customerSequenceNumber)).thenReturn(true);
 * assertThrows(TokenExpiredException.class, () -> {
 * customerServiceImpl.deleteCustomerAssociatedDetails(customerSequenceNumber);
 * }); verify(customerRepo,
 * never()).deleteCustomerAssociatedDetails(customerSequenceNumber);
 * 
 * when(customerRepo.isTokenNotValid(customerSequenceNumber)).thenReturn(true);
 * assertThrows(TokenExpiredException.class, () -> {
 * customerServiceImpl.getAllCustomersWithDetails(customerSequenceNumber); });
 * verify(customerRepo,
 * never()).getAllCustomersWithDetails(customerSequenceNumber);
 * 
 * }
 * 
 * @Test void testTokenExpiredExceptionRolesAndPermissions() { Integer
 * sequenceNumber = 1; Long customerSequenceNumber = 8L; dataInitilization();
 * customerManagementServiceRequest.setCustomerBasicDetailsRequest(null);
 * 
 * when(customerRepo.isTokenNotValid(customerSequenceNumber)).thenReturn(true);
 * assertThrows(TokenExpiredException.class, () -> {
 * customerServiceImpl.updateAllCustomerDetails(customerManagementServiceRequest
 * ); }); verify(customerRepo,
 * never()).updateRolesAndPermissions(any(RolesAndPermissionsRequest.class)); }
 * 
 * @Test void testTokenExpiredExceptionCustomerPersonalDetails() { Integer
 * sequenceNumber = 1; Long customerSequenceNumber = 8L; dataInitilization();
 * customerManagementServiceRequest.setCustomerBasicDetailsRequest(null);
 * customerManagementServiceRequest.setRolesAndPermissionsRequest(null);
 * 
 * when(customerRepo.isTokenNotValid(customerSequenceNumber)).thenReturn(true);
 * assertThrows(TokenExpiredException.class, () -> {
 * customerServiceImpl.updateAllCustomerDetails(customerManagementServiceRequest
 * ); }); verify(customerRepo,
 * never()).updateCustomerPersonalDetails(any(CustomerPersonalDetailsRequest.
 * class));
 * 
 * }
 * 
 * @Test void testTokenExpiredExceptionAddressDetails() { Integer sequenceNumber
 * = 1; Long customerSequenceNumber = 8L; dataInitilization();
 * customerManagementServiceRequest.setCustomerBasicDetailsRequest(null);
 * customerManagementServiceRequest.setRolesAndPermissionsRequest(null);
 * customerManagementServiceRequest.setCustomerPersonalDetailsRequest(null);
 * 
 * when(customerRepo.isTokenNotValid(customerSequenceNumber)).thenReturn(true);
 * assertThrows(TokenExpiredException.class, () -> {
 * customerServiceImpl.updateAllCustomerDetails(customerManagementServiceRequest
 * ); }); verify(customerRepo,
 * never()).updateAddressDetails(any(AddressRequest.class));
 * 
 * } }
 */