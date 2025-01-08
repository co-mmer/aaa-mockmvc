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

This project provides a framework for structuring unit tests following the AAA (Arrange, Act,
Assert) pattern with a focus on clarity and readability. The library offers a fluent API that guides
the developer through each phase, ensuring that only contextually relevant methods are available at
each step. This design choice enhances the ease of writing, maintaining, and understanding tests.
The key goal is to provide a clean separation of concerns within the test, allowing developers to
express their test logic fluently and naturally.

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
The sources can also be downloaded directly within the IDE (e.g., IntelliJ IDEA) to access the
documentation of the classes.

```xml

<dependency>
  <groupId>io.github.co-mmer</groupId>
  <artifactId>aaa-mockmvc</artifactId>
  <version>1.4.0</version>
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
  AAAMockMvc aaaMockMvc(WebApplicationContext context, ObjectMapper objectMapper) {
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
  AAAMockMvc aaaMockMvc(MockMvc mockMvc) {
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
  AAAMockMvc aaaMockMvc(WebApplicationContext context, ObjectMapper objectMapper) {
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
    // Custom MockMvc configuration
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
   testing. [For more details, see the example below.](#test-case-update-resource-with-put-and-validate-response-object)

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

- [Registration Scenario](#registration-scenario)
- [Test Implementation](#test-implementation)

This example demonstrates a typical registration process, where the user's email address must first
be verified by sending a code. After the email verification, the user can proceed with the
registration, including providing the verification code.

The process is split into two main steps:

1. **Email Verification**: A verification code is sent to the provided email address.
2. **Registration**: After receiving the verification code, the user sends it back to the backend
   for validation, allowing them to complete their registration.

Below are the necessary code snippets to represent this scenario:

## Registration Scenario

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

## Test Implementation

The table below shows the same test scenario written in two different ways. On the left side, the
test is written using **MockMvc**, which is the traditional way to perform HTTP tests in Spring. On
the right side, the same test is written using the **AAA-Mock** framework, which is an open-source
testing framework designed to make the test code more readable and concise.

It is important to note that in both examples, the use of helper private methods has been
intentionally avoided to keep the tests straightforward and focused on the core testing logic. This
ensures clarity and helps demonstrate the difference between the two testing approaches.

<table>
  <tr>
    <td>

**Test with MockMvc**

```java

@Autowired
private MockMvc mockMvc;

@Autowired
private ObjectMapper objectMapper;

@Test
@SneakyThrows
void GIVEN_registration_with_valid_verification_WHEN_createRegistration_THEN_return_status_201() {

  var verificationRequest = VerificationRequest.builder()
      .firstname(FIRSTNAME)
      .lastname(LASTNAME)
      .mail(EMAIL)
      .build();

  var verificationResponseJson = mockMvc.perform(
          MockMvcRequestBuilders.post(POST_CREATE_VERIFICATION)
              .contentType(MediaType.APPLICATION_JSON)
              .content(objectMapper.writeValueAsString(verificationRequest)))
      .andReturn()
      .getResponse()
      .getContentAsString();

  var verificationResponse = objectMapper.readValue(verificationResponseJson,
      VerificationResponse.class);

  var registrationRequest = RegistrationRequest.builder()
      .verification(VerificationResponse.builder()
          .code(VALID_CODE)
          .mail(verificationResponse.getMail())
          .uuid(verificationResponse.getUuid())
          .build())
      .build();

  mockMvc.perform(MockMvcRequestBuilders.post(POST_CREATE_REGISTRATION)
          .contentType(MediaType.APPLICATION_JSON)
          .content(objectMapper.writeValueAsString(registrationRequest)))
      .andExpect(status().isCreated());
}

```

</td>
    <td>

**Test with AAA-MockMvc**

```java

@Test
@SneakyThrows
void GIVEN_registration_with_valid_verification_WHEN_createRegistration_THEN_return_status_201() {

  var verificationRequest = VerificationRequest.builder()
      .firstname(FIRSTNAME)
      .lastname(LASTNAME)
      .mail(EMAIL)
      .build();

  var verificationResponse = post()
      .arrange()
      .arrangeUrl(POST_CREATE_VERIFICATION)
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
      .arrangeUrl(POST_CREATE_REGISTRATION)
      .arrangeBody()
      .arrangeJson(registrationRequest)
      .act()
      .actPerform()
      .asserts()
      .assertStatus()
      .assertStatusIsCreated();
}

```

</td>
  </tr>
</table>

---

## License

This project is licensed under the Apache License, Version 2.0. See `LICENSE.txt` for more
information.

