<table>
  <tr>
    <td><img src="./images/aaa-mockmvc-icon.png" alt="aaa-mockmvc-icon" style="vertical-align: middle;"/></td>
    <td><h1>AAA-MockMvc</h1></td>
  </tr>
</table>

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=co-mmer_aaa-mockmvc&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=co-mmer_aaa-mockmvc)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=co-mmer_aaa-mockmvc&metric=coverage)](https://sonarcloud.io/summary/new_code?id=co-mmer_aaa-mockmvc)
[![SQALE Rating](https://sonarcloud.io/api/project_badges/measure?project=co-mmer_aaa-mockmvc&metric=sqale_rating)](https://sonarcloud.io/summary/new_code?id=co-mmer_aaa-mockmvc)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=co-mmer_aaa-mockmvc&metric=security_rating)](https://sonarcloud.io/summary/new_code?id=co-mmer_aaa-mockmvc)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=co-mmer_aaa-mockmvc&metric=vulnerabilities)](https://sonarcloud.io/summary/new_code?id=co-mmer_aaa-mockmvc)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=co-mmer_aaa-mockmvc&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=co-mmer_aaa-mockmvc)
![Java](https://img.shields.io/badge/Java-17-blueviolet)
![Maven Central](https://img.shields.io/maven-central/v/io.github.co-mmer/aaa-mockmvc)

## Overview

A fluent testing framework for Spring's MockMvc that enforces the Arrange-Act-Assert (AAA)
pattern and reduces boilerplate in controller tests.
The library guides developers through each testing phase with a strongly-typed,
step-by-step API, ensuring a consistent and intuitive test structure. Common tasks such as
request setup, ObjectMapper-based serialization, and response assertions are fully abstracted,
allowing developers to focus on the test logic itself rather than technical overhead.

<img src="images/aaa-mockmvc-example1.png" alt="aaa-mockmvc-example"/>

___

## Table of Contents

- [Installation](#Installation)
- [Setup](#setup)
- [Writing Tests](#writing-tests)
- [Example](#example)
- [License](#License)

---

## Installation

To include AAAMockMvc in the project, add the following dependency to the `pom.xml`.
The sources can also be downloaded directly within the IDE (e.g. IntelliJ IDEA) to access the
documentation of the classes.

```xml

<dependency>
  <groupId>io.github.co-mmer</groupId>
  <artifactId>aaa-mockmvc</artifactId>
  <version>1.4.2</version>
  <scope>test</scope>
</dependency>

```

---

## Setup

To write tests using this library, certain configurations are necessary. Below are the steps
required to set up your testing environment effectively.

### Bean

To configure `AAAMockMvc` for tests, four main options are available. Each configuration offers
flexibility in using `AAAMockMvc` within the test setup to interact with the MVC testing framework,
tailored to the specific project requirements.

<details>
<summary>Setup A: WebApplicationContext with Default ObjectMapper</summary>


In this option, `AAAMockMvc` is configured using the `WebApplicationContext`.
The framework will use a default ObjectMapper (`new ObjectMapper()`).

#### Steps:

1. Define a configuration class.
2. Create a `AAAMockMvc` bean using the `WebApplicationContext`.

```java

@Configuration
public class AAAMockMvcConfig {

  @Bean
  public AAAMockMvc aaaMockMvc(WebApplicationContext context) {
    return new AAAMockMvc(context);
  }
}
```

</details>

<details>
<summary>Setup B: WebApplicationContext with Custom ObjectMapper</summary>

In this option, both the `WebApplicationContext` and a custom `ObjectMapper` can be passed
to `AAAMockMvc`.

#### Steps:

1. Define a configuration class.
2. Create a ObjectMapper bean with custom configuration.
3. Pass the custom ObjectMapper instance to the AAAMockMvc bean

```java

@Configuration
public class AAAMockMvcConfig {

  @Bean
  public AAAMockMvc aaaMockMvc(WebApplicationContext context, ObjectMapper objectMapper) {
    return new AAAMockMvc(context, objectMapper);
  }

  @Bean
  public ObjectMapper objectMapper() {
    // Custom ObjectMapper configuration
    return new ObjectMapper();
  }
}
```

</details>

<details>
<summary>Setup C: Custom MockMvc and Default ObjectMapper</summary>

This option allows for configuring `AAAMockMvc` with a custom `MockMvc` instance.
The framework will use a default ObjectMapper (`new ObjectMapper()`).

#### Steps:

1. Define a configuration class.
2. Create a MockMvc bean with custom configuration.
3. Pass the custom MockMvc instance to the AAAMockMvc bean

```java

@Configuration
public class AAAMockMvcConfig {

  @Bean
  public AAAMockMvc aaaMockMvc(MockMvc mockMvc) {
    return new AAAMockMvc(mockMvc);
  }

  @Bean
  public MockMvc mockMvc(WebApplicationContext context) {
    // Custom MockMvc configuration
    return MockMvcBuilders.webAppContextSetup(context)
        .addFilters(new CharacterEncodingFilter("UTF-8", true))
        .build();
  }
}
```

</details>

<details>
<summary>Setup D: Custom MockMvc and Custom ObjectMapper</summary>

This option allows for full customization by passing both a custom `MockMvc` instance and a
custom `ObjectMapper` to `AAAMockMvc`. This provides maximum flexibility for projects that need
specific configurations.

#### Steps:

1. Define a configuration class.
2. Create a MockMvc bean with custom configuration.
3. Create a ObjectMapper bean with custom configuration.
4. Pass the custom MockMvc and ObjectMapper instance to the AAAMockMvc bean

```java

@Configuration
public class AAAMockMvcConfig {

  @Bean
  public AAAMockMvc aaaMockMvc(WebApplicationContext context, ObjectMapper objectMapper) {
    return new AAAMockMvc(context, objectMapper);
  }

  @Bean
  public MockMvc mockMvc(WebApplicationContext context) {
    // Custom MockMvc configuration
    return MockMvcBuilders.webAppContextSetup(context)
        .addFilters(new CharacterEncodingFilter("UTF-8", true))
        .build();
  }

  @Bean
  public ObjectMapper objectMapper() {
    // Custom ObjectMapper configuration
    return new ObjectMapper();
  }
}
```

</details>

### Test

There are two options for utilizing the AAAMockMvc in test classes:

<details>
<summary>Setup A: AAAMockMvc</summary>

AAAMockMvc can be directly autowired into the test class. This method allows the API methods to
be
used directly in the tests.

```java

public class ControllerTest {

  @Autowired
  private AAAMockMvc aaaMockMvc;

  @Test
  void WHEN_calling_endpoint_THEN_return_expected_status() {
    aaaMockMvc.get()
        .arrange()
        .arrangeUrl(GET_SIMPLE)
        .act()
        .actPerform()
        .asserts()
        .assertStatus()
        .assertStatusIsOk();
  }
}
```

</details>

<details>
<summary>Setup B: AAAMockMvcAbstract</summary>


Alternatively, extending the abstract class `AAAMockMvcAbstract` is another option, which
provides all necessary methods like get(), post(), etc. This approach is useful for encapsulating
common test
behaviors and reducing boilerplate code in test classes.

```java

public class ControllerTest extends AAAMockMvcAbstract {

  @Test
  void WHEN_calling_endpoint_THEN_return_expected_content() {
    get()
        .arrange()
        .arrangeUrl(GET_SIMPLE)
        .act()
        .actPerform()
        .asserts()
        .assertStatus()
        .assertStatusIsOk();
  }
}
```

</details>

___

## Writing Tests

In the provided library, every test follows the AAA structure using the following three phases:

1. **Arrange**: Set up the necessary conditions for the test (e.g., define URL, parameters,
   headers).
2. **Act**: Perform the operation (e.g., make the API request).
3. **Assert**: Validate the result (e.g., check HTTP status, response content).
4. **Answer**: Optionally, the result of the request can be accessed using the `answer()` method,
   allowing for further examination and validation of the response. Additionally, the returned
   object can be passed back as the body of a subsequent request using `arrangeJson(T content)`.
   This enables used to set up the next, creating a seamless flow in
   testing. [For more details, see the example below.](#example)

### Arrange

- [Arrange Url](docs/arrange/ArrangeUrl.md)
- [Arrange Head](docs/arrange/ArrangeHead.md)
- [Arrange Body Raw](docs/arrange/ArrangeBodyRaw.md)
- [Arrange Body Binary](docs/arrange/ArrangeBodyBinray.md)

### Assert

- [Assert Status](docs/assert/AssertStatus.md)
- [Assert ContentAsString](docs/assert/AssertString.md)
- [Assert ContentAsClass 🔸 (New)](docs/assert/AssertClass.md)
- [Assert ContentAsByte](docs/assert/AssertByte.md)
- [Assert ContentAsCollection 🔸 (New)](docs/assert/AssertCollection.md)
- [Assert ContentAsMap 🔸 (New)](docs/assert/AssertMap.md)
- [Assert Head](docs/assert/AssertHead.md)
- [Assert Custom](docs/assert/AssertCustom.md)

### Answer

- [Answer](docs/answer/Answer.md)

---

## Example

This example demonstrates a registration process, where the user's email address must first
be verified by sending a code. After the email verification, the user can proceed with the
registration, including providing the verification code. The process is split into two main steps:

1. **Email Verification**: A verification code is sent to the provided email address.
2. **Registration**: After receiving the verification code, the user sends it back to the backend
   for validation, allowing them to complete their registration.

### Main Code

<details>
<summary>Implementation of Registration Scenario</summary>

### 1. Verification Response Model

The `VerificationResponse` class represents the response that contains the verification code, UUID,
and email after the verification process:

```java
public class VerificationResponse {

  private String code;
  private String uuid;
  private String mail;
}
```

### 2. Verification Controller

The `VerificationController` is responsible for handling the verification request. It receives a
request with the user's email and generates a verification response containing a unique UUID and a
dummy verification code sent to the provided email.

```java

@RestController
@RequestMapping(Api.BASE)
public class VerificationController {

  @PostMapping(Api.CREATE_VERIFICATION)
  public ResponseEntity<VerificationResponse> createVerification(
      @RequestBody VerificationRequest verification) {

    var response = VerificationResponse
        .builder()
        .mail(verification.getMail())
        .uuid(UUID.randomUUID().toString())
        .build();

    // Simulate sending a verification code to the user's email
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}

```

### 3. Registration Models

The `RegistrationAddress` class holds the user's address details, while the `RegistrationRequest`
contains both the address and the verification response (which includes the verification code, UUID,
and email):

```java
public class RegistrationAddress {

  private String street;
  private String houseNumber;
  private String zip;
  private String city;
  private String country;
}

public class VerificationResponse {

  private String code;
  private String uuid;
  private String mail;
}

public class RegistrationRequest {

  private RegistrationAddress address;
  private VerificationResponse verification;
}
```

### 4. Registration Controller

The `RegistrationController` handles the registration process. Once the user sends the verification
code, the backend will validate it and, if valid, complete the registration process.

```java

@RestController
@RequestMapping(Api.BASE)
public class RegistrationController {

  @PostMapping(Api.CREATE_REGISTRATION)
  public ResponseEntity<Void> createRegistration(@RequestBody RegistrationRequest registration) {
    // Simulate the process of verifying the code entered by the user
    return new ResponseEntity<>(HttpStatus.CREATED);
  }
}

```

</details>

### Test Code

The following examples demonstrate the same test scenario written in two different ways. First, the
test is implemented using MockMvc, the traditional approach for performing HTTP tests in Spring.
This is followed by the same test written with AAAMockMvc.

To keep the tests straightforward and focused on the core testing logic, the use of private helper
methods has been intentionally avoided. This ensures clarity and helps highlight the differences
between the two testing approaches.

### Test with MockMvc

```java

@Autowired
private MockMvc mockMvc;

@Autowired
private ObjectMapper objectMapper;

@Test
@SneakyThrows
void GIVEN_valid_code_WHEN_registration_THEN_status_201() {

  var verificationRequest = VerificationRequest.builder()
      .firstname(FIRSTNAME)
      .lastname(LASTNAME)
      .mail(EMAIL)
      .build();

  var content = MockMvcRequestBuilders.post(SEND_MAIL_VERIFICATION)
      .contentType(MediaType.APPLICATION_JSON)
      .content(objectMapper.writeValueAsString(verificationRequest));

  var verificationResponseJson = mockMvc.perform(content)
      .andReturn()
      .getResponse()
      .getContentAsString();

  var verificationResponse = objectMapper.readValue(
      verificationResponseJson, VerificationResponse.class);

  var registrationRequest = RegistrationRequest.builder()
      .verification(VerificationResponse.builder()
          .code(VALID_CODE)
          .mail(verificationResponse.getMail())
          .uuid(verificationResponse.getUuid())
          .build())
      .build();

  content = MockMvcRequestBuilders.post(POST_CREATE_REGISTRATION)
      .contentType(MediaType.APPLICATION_JSON)
      .content(objectMapper.writeValueAsString(registrationRequest));

  mockMvc.perform(content).andExpect(status().isCreated());
}

```

### Test with AAAMockMvc

```java

@Test
@SneakyThrows
void GIVEN_valid_code_WHEN_registration_THEN_status_201() {

  var verificationRequest = VerificationRequest.builder()
      .firstname(FIRSTNAME)
      .lastname(LASTNAME)
      .mail(EMAIL)
      .build();

  var verificationResponse = post()
      .arrange()
      .arrangeUrl(SEND_VERIFICATION)
      .arrangeBody()
      .arrangeJson(verificationRequest)
      .act()
      .actPerform()
      .answer()
      .answerAsObject(VerificationResponse.class);

  var registrationRequest = RegistrationRequest
      .builder()
      .verification(VerificationResponse.builder()
          .code(VALID_CODE)
          .mail(verificationResponse.getMail())
          .uuid(verificationResponse.getUuid())
          .build())
      .build();

  post()
      .arrange()
      .arrangeUrl(CREATE_REGISTRATION)
      .arrangeBody()
      .arrangeJson(registrationRequest)
      .act()
      .actPerform()
      .asserts()
      .assertStatus()
      .assertStatusIsCreated();
}

```

---

## License

This project is licensed under the Apache License, Version 2.0. See `LICENSE.txt` for more
information.

