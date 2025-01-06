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
- [Examples](#examples)
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
- [Assert Content](docs/assert/AssertContent.md)
- [Assert Byte](docs/assert/AssertByte.md)
- [Assert Collection 🔸 (New)](docs/assert/AssertCollection.md)
- [Assert Map 🔸 (New)](docs/assert/AssertMap.md)
- [Assert Head](docs/assert/AssertHead.md)
- [Assert Custom](docs/assert/AssertCustom.md)

### Answer

- [Answer](docs/answer/Answer.md)

---

## Examples

### Test Case: Validate Response with Expected List of DTOs

In this test case, a GET request is made to the specified endpoint using a URL that includes a
parameter. The following steps are executed:

1. **Arrange**: The initial setup is performed, including defining the request URL.
2. **Arrange URL**: The base URL (`GET_EXAMPLE`) is arranged for the request.
3. **Arrange PARAM**: A query parameter is added to the request using `arrangeKeyValue`, allowing
   for dynamic data retrieval.
4. **Act**: The request is executed with the `act()` method, followed by `actPerform()` to send
   the request.
5. **Assertions**: Several assertions are made to verify the response:
    - The response is asserted to be non-empty using `assertContentNotEmpty()`.
    - The HTTP status of the response is asserted to be `HttpStatus.OK`, ensuring the request was
      successful.
    - The response content size is asserted to match the expected number of items
      using `assertContentSize(2)`, confirming that the correct number of objects is returned.
    - The response content is asserted to match the expected list of `DemoDto` objects
      using `assertContentEquals`, confirming that the API returns the correct data structure.

This structured approach helps ensure that the endpoint behaves as expected and returns the correct
list of DTOs.

```java

@Test
void WHEN_call_endpoint_THEN_return_expected_list_dto() {

  get()
      .arrange()
      .arrangeUrl(GET_EXAMPLE)
      .arrangeParam()
      .arrangeKeyValue(PARAM_KEY_1, PARAM_VALUE_1)
      .act()
      .actPerform()
      .asserts()
      .assertStatus()
      .assertStatusIsOk()
      .assertContent()
      .assertContentNotEmpty()
      .assertContentSize(2)
      .assertContentEquals(DemoDto.class, EXPECTED_LIST);
}
```

---

### Test Case: Validate Response Status for File Upload

In this test case, a POST request is made to the specified endpoint to upload files. The following
steps are executed:

1. **Arrange**: The initial setup is performed, including defining the request URL and necessary
   parameters.
2. **Arrange URL**: The URL is arranged using `arrangeUrl`, which includes dynamic path
   variables (`PATH_VARIABLE_USER_ID_8`, `PATH_VARIABLE_PAGE_ID_3`) to target specific resources.
3. **Arrange PARAM**: A query parameter is added to the request using `arrangeKeyValue`,
   allowing
   for additional context in the request.
4. **Arrange HEAD**: An authorization token is included in the request headers
   using `arrangeAuth`,
   which is typically required for secured endpoints.
5. **Arrange BODY**: The files to be uploaded are arranged using `arrangeFiles`, enabling multiple
   file uploads in a single request.
6. **Action**: The request is executed with the `act()` method, followed by `actPerform()` to send
   the request.
7. **Assertions**: The response status is asserted to be `201`, indicating that the resource was
   created successfully.

This structured approach ensures that the endpoint correctly processes file uploads and returns the
expected status code.

```java

@Test
void GIVEN_files_WHEN_call_endpoint_THEN_return_expected_status_201() {

  post()
      .arrange()
      .arrangeUrl(POST_EXAMPLE, PATH_VARIABLE_USER_ID_8, PATH_VARIABLE_PAGE_ID_3)
      .arrangeParam()
      .arrangeKeyValue(PARAM_KEY_1, PARAM_VALUE_1)
      .arrangeHead()
      .arrangeAuth(TEST_TOKEN)
      .arrangeBody()
      .arrangeFiles(TEST_BODY_FILES)
      .act()
      .actPerform()
      .asserts()
      .assertStatus()
      .assertStatusIsCreated();
}
```

---

### Test Case: Update Resource with PUT and Validate Response Object

Step 1: Send the Request (GET)

1. **Arrange**: The URL for the update endpoint (`GET_DEMO`) is arranged
   using `arrangeUrl`.
2. **Arrange BODY**: The `DemoA` object, previously updated with a new name (`demoAUpdated`),
   is serialized into JSON using `arrangeJson` and set as the request body.
3. **Action**: The `PUT` request is executed using `act()` followed by `actPerform()` to send the
   updated resource to the endpoint.
4. **Answer**: The response is processed using `answer()`, and the content is deserialized into a
   `DemoA` object using `answerAsObject(DemoA.class)`. This allows the response data to be used in
   subsequent steps.

Step 2: Send the Modify DemoA (PUT)

1. **Arrange**: The initial setup is performed, including defining the request URL for the PUT
   endpoint.
2. **Arrange URL**: The URL is arranged using arrangeUrl, targeting the PUT_EXAMPLE endpoint.
3. **Arrange BODY**: The body is arranged using `arrangeJson(demoA)`, where the response from the
   previous request `(demoA)` is serialized and passed as the body of the PUT request.
4. **Action**: The request is executed with the `act()` method, followed by `actPerform()` to send
   the
   request.
5. **Assertions**: The response status is asserted to be 200 (or the expected status), confirming
   the successful update of the resource. Optionally, the content of the response can be validated
   to ensure it matches the expected result.

```java

@Test
void GIVEN_demoA_with_new_name_WHEN_update_THEN_return_expected_object() {

  var demoA = get()
      .arrange()
      .arrangeUrl(GET_DEMO)
      .act()
      .actPerform()
      .answer()
      .answerAsObject(DemoA.class);

  var demoAUpdated = demoA.withName(TEST_UPDATED);

  put()
      .arrange()
      .arrangeUrl(PUT_DEMO)
      .arrangeBody()
      .arrangeJson(demoAUpdated)
      .act()
      .actPerform()
      .asserts()
      .assertStatus()
      .assertStatusIsCreated()
      .assertContent()
      .assertContentEquals(DemoA.class, TEST_A1_UPDATED);
}
```

---

## License

This project is licensed under the Apache License, Version 2.0. See `LICENSE.txt` for more
information.

