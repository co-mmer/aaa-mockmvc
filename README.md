<table>
  <tr>
    <td><img src="./images/aaa-mockmvc-icon.png" alt="icon" style="vertical-align: middle;"/></td>
    <td><h1>AAA-MockMvc</h1></td>
  </tr>
</table>

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=co-mmer_aaa-mockmvc&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=co-mmer_aaa-mockmvc)
[![SQALE Rating](https://sonarcloud.io/api/project_badges/measure?project=co-mmer_aaa-mockmvc&metric=sqale_rating)](https://sonarcloud.io/summary/new_code?id=co-mmer_aaa-mockmvc)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=co-mmer_aaa-mockmvc&metric=security_rating)](https://sonarcloud.io/summary/new_code?id=co-mmer_aaa-mockmvc)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=co-mmer_aaa-mockmvc&metric=vulnerabilities)](https://sonarcloud.io/summary/new_code?id=co-mmer_aaa-mockmvc)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=co-mmer_aaa-mockmvc&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=co-mmer_aaa-mockmvc)

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
  <version>1.2.0</version>
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
  void WHEN_calling_endpoint_THEN_return_expected_status() throws Exception {
    aaaMockMvc.get()
        .arrange()
        .arrangeUrl(GET_SIMPLE)
        .act()
        .actPerform()
        .asserts()
        .assertStatus(HttpStatus.OK);
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
  void WHEN_calling_endpoint_THEN_return_expected_content() throws Exception {
    get()
        .arrange()
        .arrangeUrl(GET_SIMPLE)
        .act()
        .actPerform()
        .asserts()
        .assertStatus(HttpStatus.OK);
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

Optionally, the result of the request can be accessed using the `answer()` method, allowing for
further examination and validation of the response.

### Arrange Section

<details>
<summary>Arrange URL</summary>

### Arrange URL

In this example, the simplest form of a request is demonstrated, utilizing only the base URL.

```
 get()
      .arrange()
      .arrangeUrl(GET_EXAMPLE)
      .act()
      ...
```

---

### Arrange Path Variable

In this example, a URL with a path variable is utilized, which dynamically replaces a segment of the
URL to retrieve specific data.

```
 get()
      .arrange()
      .arrangeUrl(GET_EXAMPLE, PATH_VAR_8)
      .act()
      ...
```

---

### Arrange URI

In this example, when a complete URL is provided, the request is directed to that specific endpoint.

```
 get()
      .arrange()
      .arrangeUrl(GET_EXAMPLE_URI)
      .act()
      ...
```

---

### Arrange Parameter

In this example, a URL is enhanced with a query parameter, commonly used for search or filter
requests.

```
 get()
      .arrange()
      .arrangeUrl(GET_EXAMPLE)
      .arrangeParam()
      .arrangeKeyValue(PARAM_KEY_1, PARAM_VALUE_1)
      .act()
      ...
```

---

### Arrange Parameters

In this example, multiple parameters are appended to the URL, enabling a more detailed query.

```
 get()
      .arrange()
      .arrangeUrl(GET_EXAMPLE)
      .arrangeParam()
      .arrangeKeyValue(PARAM_KEY_1, PARAM_VALUE_1)
      .arrangeKeyValue(PARAM_KEY_2, PARAM_VALUE_2)
      .act()
      ...
```

---

### Arrange Parameters (Map)

In this example, an entire set of parameters is appended to the URL using a map, which simplifies
the handling of dynamic parameters.

```
 get()
      .arrange()
      .arrangeUrl(GET_EXAMPLE)
      .arrangeParam()
      .arrangeKeyValue(PARAM_MAP)
      .act()
      ...
```

---

</details>


<details>
<summary>Arrange HEAD</summary>

### Arrange Header Accept

In this example, the Accept header is set, indicating that the client
expects a response in JSON format.

```
 get()
      .arrange()
      .arrangeUrl(GET_EXAMPLE)
      .arrangeHead()
      .arrangeAccept(APPLICATION_JSON)
      .act()
      ...
```

---

### Arrange Header Auth

In this example, an authorization token is added to the headers, which is typically required for
secured endpoints.

```
 get()
      .arrange()
      .arrangeUrl(GET_EXAMPLE)
      .arrangeHead()
      .arrangeAuth(TOKEN)
      .act()
      ...
```

---

### Arrange Header Content-Type

In this example, the Content-Type header is defined, indicating the format of the data being sent (
if applicable).

```
 get()
      .arrange()
      .arrangeUrl(GET_EXAMPLE)
      .arrangeHead()
      .arrangeContentType(APPLICATION_JSON)
      .act()
      ...
```

---

### Arrange Header Key-Value

In this example, a single custom header is set, allowing for additional context or control over the
request.

```
 get()
      .arrange()
      .arrangeUrl(GET_EXAMPLE)
      .arrangeHead()
      .arrangeKeyValue(HEAD_X_CUSTOM_1, HEAD_X_CUSTOM_VALUE_1)
      .act()
      ...
```

---

### Arrange Header Key-Value (Multiple)

In this example, this example shows how to set multiple custom headers in a single request, which
can be necessary for more complex API interactions.

```
 get()
      .arrange()
      .arrangeUrl(GET_EXAMPLE)
      .arrangeHead()
      .arrangeKeyValue(HEAD_X_CUSTOM_1, HEAD_X_CUSTOM_VALUE_1)
      .arrangeKeyValue(HEAD_X_CUSTOM_2, HEAD_X_CUSTOM_VALUE_2)
      .act()
      ...
```

---

### Arrange Header Key-Value (Map)

In this example, using a map to set multiple custom headers allows for a cleaner approach when
numerous headers are required, making the code more maintainable.

```
 get()
      .arrange()
      .arrangeUrl(GET_EXAMPLE)
      .arrangeHead()
      .arrangeKeyValue(HEAD_X_CUSTOM_MAP)
      .act()
      ...
```

---

</details>


<details>
<summary>Arrange BODY</summary>

### Arrange Body File

In this example, one file are added to the request body.

```
 post()
      .arrange()
      .arrangeUrl(POST_EXAMPLE)
      .arrangeBody()
      .arrangeFile(FILE_1)
      .act()
      ...
```

---

### Arrange Body File (Multiple)

In this example, two files are added to the request body.

```
 post()
      .arrange()
      .arrangeUrl(POST_EXAMPLE)
      .arrangeBody()
      .arrangeFile(FILE_1)
      .arrangeFile(FILE_2)
      .act()
      ...
```

---

### Arrange Body File (List)

In this example, this example illustrates how to upload multiple files using a list. This approach
simplifies adding multiple files and enhances code readability.

```
 post()
      .arrange()
      .arrangeUrl(POST_EXAMPLE)
      .arrangeBody()
      .arrangeFiles(List.of(FILE_1, FILE_2))
      .act()
      ...
```

---

### Arrange Content Raw

In this example, raw content is sent in the body along with its media type, allowing for versatile
content submissions beyond standard formats.

```
 post()
      .arrange()
      .arrangeUrl(POST_EXAMPLE)
      .arrangeBody()
      .arrangeContent(RAW_CONTENT, RAW_MEDIATYPE)
      .act()
      ...
```

---

### Arrange Content Json

In this example, JSON data is sent in the request body, demonstrating how to transmit structured
data to the server.

```
 post()
      .arrange()
      .arrangeUrl(POST_EXAMPLE)
      .arrangeBody()
      .arrangeJson(JSON_1)
      .act()
      ...
```

</details>

### Assert Section

<details>
<summary>Assert Status</summary>

### Assert Status (Literal)

This example shows how to assert that the response returns the exact status code.

```
  get()
      ...
      .act()
      .actPerform()
      .asserts()
      .assertStatus()
      .assertStatus(200)
```

### Assert Status (Enum)

This example shows how to assert that the response returns the exact HttpStatus.

```
  get()
      ...
      .act()
      .actPerform()
      .asserts()
      .assertStatus()
      .assertStatus(HttpStatus.OK)
```

---

### Assert Status Is OK

This example shows how to assert that the response status is 200 OK.

```
  get()
      ...
      .act()
      .actPerform()
      .asserts()
      .assertStatus()
      .assertStatusIsOk()
```

---

### Assert Status Is Created

This example shows how to assert that the response status is 201 Created.

```
  get()
      ...
      .act()
      .actPerform()
      .asserts()
      .assertStatus()
      .assertStatusIsCreated()
```

---

### Assert Status Is Accepted

This example shows how to assert that the response status is 202 Accepted.

```
  get()
      ...
      .act()
      .actPerform()
      .asserts()
      .assertStatus()
      .assertStatusIsAccepted()
```

---

### Assert Status Is Not Found

This example shows how to assert that the response status is 404 Not Found.

```
  get()
      ...
      .act()
      .actPerform()
      .asserts()
      .assertStatus()
      .assertStatusIsNotFound()
```

---

### Assert Status Is Client Error

This example shows how to assert that the response status is within the range of client errors (
400-499).

```
  get()
      ...
      .act()
      .actPerform()
      .asserts()
      .assertStatus()
      .assertStatusIsClientError()
```

---

### Assert Status Is Server Error

This example shows how to assert that the response status is within the range of server errors (
500-599).

```
  get()
      ...
      .act()
      .actPerform()
      .asserts()
      .assertStatus()
      .assertStatusIsServerError()
```

---

### Assert Status Is Redirect

This example shows how to assert that the response status indicates a redirection (3xx).

```
  get()
      ...
      .act()
      .actPerform()
      .asserts()
      .assertStatus()
      .assertStatusIsRedirect()
```

---

### Assert Status Is Access Forbidden

This example shows how to assert that the response status is 403 Access Forbidden.

```
  get()
      ...
      .act()
      .actPerform()
      .asserts()
      .assertStatus()
      .assertStatusIsAccessForbidden()
```

---

### Assert Status Is Access Unauthorized

This example shows how to assert that the response status is 401 Access Unauthorized.

```
  get()
      ...
      .act()
      .actPerform()
      .asserts()
      .assertStatus()
      .assertStatusIsAccessUnauthorized()
```

---

### Assert Status In Range

This example shows how to assert that the response status is within a specific range.

```
  get()
      ...
      .act()
      .actPerform()
      .asserts()
      .assertStatus()
      .assertStatusInRange(400, 499)
```

---

</details> 

<details>
<summary>Assert Content</summary>

### Assert Content Not Empty

In this example, the **`assertContentNotEmpty`** method is used to assert that the response content
is not empty.

```
  get()
      ...
      .act()
      .actPerform()
      .asserts()
      .assertContent()
      .assertContentNotEmpty()
```

---

### Assert Content Empty

In this example, the **`assertContentEmpty`** method is used to assert that the response content
is empty.

```
  get()
      ...
      .act()
      .actPerform()
      .asserts()
      .assertContent()
      .assertContentEmpty()
```

---

### Assert Content Equals (Byte)

In this example, the **`assertContentEquals`** method is used to assert that the response content
matches an expected byte array.

```
  get()
      ...
      .act()
      .actPerform()
      .asserts()
      .assertContent()
      .assertContentEquals(EXPECTED_BYTE_ARRAY)
```

---

### Assert Content Equals (String)

In this example, the **`assertContentEquals`** method is used to assert that the response content
matches an expected string.

```
  get()
      ...
      .act()
      .actPerform()
      .asserts()
      .assertContent()
      .assertContentEquals(EXPECTED_STRING)
```

---

</details>



<details>
<summary>Assert Content (Collection) </summary>

### Assert Content Equals (List)

In this example, the **`assertContentEquals`** method is used to assert that the response content
matches an expected **`List`** of objects.

- **Class Specification**: Only the class of the objects contained in the list needs to be
  specified (in this case, `DemoDto.class`). This informs the framework about the type of object to
  expect.
- **Expected Objects**: Alongside the class, a list of expected objects (`EXPECTED_LIST`) is
  provided, which the response should match. This approach is particularly useful for validating
  collections of data returned by the API.

```
  // EXPECTED_LIST = List.of(TEST_DTO_1, TEST_DTO_2);
  
  get()
      ...
      .act()
      .actPerform()
      .asserts()
      .assertContent()
      .assertContentEquals(DemoDto.class, EXPECTED_LIST)
```

### Assert Content Equals (Set)

In this example, the **`assertContentEquals`** method is used to assert that the response content
matches an expected **`Set`** of objects.

- **Class Specification**: Only the class of the objects contained in the set needs to be
  specified (in this case, `DemoDto.class`). This informs the framework about the type of object to
  expect.
- **Expected Objects**: Alongside the class, a set of expected objects (`EXPECTED_SET`) is provided,
  which the response should match. This approach is particularly useful for validating collections
  of data returned by the API.

```
  // EXPECTED_SET = Set.of(TEST_DTO_1, TEST_DTO_2);
  
    get()
      ...
      .act()
      .actPerform()
      .asserts()
      .assertContent()
      .assertContentEquals(DemoDto.class, EXPECTED_SET)
```

### Assert Content Equals (Map)

In this example, the **`assertContentEquals`** method is utilized to assert that the response
content matches an expected **`map`** of objects.

- **Class Specifications**:
    - **Key Class**: The class of the keys in the map must be specified (in this
      case, `Boolean.class`).
    - **Value Class:** The class of the values in the map must be specified (in this case,
      DemoDto.class).
- **Expected Objects**: Alongside the class, a map of expected objects (`EXPECTED_MAP`) is
  provided, which the response should match. This approach is particularly useful for validating
  collections of data returned by the API.

```
  // EXPECTED_MAP = Map.of(TRUE, TEST_DTO_1, FALSE, TEST_DTO_2);
  
  get()
      ...
      .act()
      .actPerform()
      .asserts()
      .assertContent()
      .assertContentEquals(Boolean.class, DemoDto.class, EXPECTED_MAP);
```

---

### Assert Content Size

In this example, the **`assertContentSize`** method is used to assert that the response content size
matches an expected size.

```
  get()
      ...
      .act()
      .actPerform()
      .asserts()
      .assertContent()
      .assertContentSize(2)
```

---

</details> 

<details>
<summary>Assert Head</summary>

### Assert Head Contains

This example asserts that the response contains a specific header.

```
  head()
      ...
      .act()
      .actPerform()
      .asserts()
      .assertHead()
      .assertHeadContains(X_HEADER)
```

---

### Assert Head Not Contains

This example asserts that a specific header is not present in the response.

```
  head()
      ...
      .act()
      .actPerform()
      .asserts()
      .assertHead()
      .assertHeadNotContains(X_HEADER)
```

---

### Assert Head Equals

This example is used to assert that a specific header key has the expected value.

```
  head()
      ...
      .act()
      .actPerform()
      .asserts()
      .assertHead()
      .assertHeadEquals(X_HEAD_KEY, X_HEAD_VALUE)
```

--- 
</details>


<details>
<summary>Assert Custom</summary>

### Assert Result Matcher

In this example, the **`assertCustomResultMatcher`** method demonstrates how to use custom
assertions through the `ResultMatcher` interface.

In the provided code snippet, a custom assertion is made to check for the existence
of a cookie named `"sessionId"` in the response. The syntax `cookie().exists("sessionId")` is a
specific `ResultMatcher` that verifies if the specified cookie is present in the response.

```  
  get()
      ...
      .act()
      .actPerform()
      .asserts()
      .assertCustom()
      .assertCustomResultMatcher(cookie().exists("sessionId"))
```

</details>

### Answer Section

<details>
<summary>Answer ResultActions</summary>

### Retrieve Result Actions

This method retrieves the ResultActions from the executed HTTP request, allowing detailed
examination and validation of the response.

```  
 var answer = get()
                 ...
                 .act()
                 .actPerform()
                 .answer()
                 .answerAsResultActions();
```

---

</details>

<details>
<summary>Answer String</summary>

### Retrieve Response as String

This method retrieves the content of the HTTP response as a String.

```  
 var answer = get()
                 ...
                 .act()
                 .actPerform()
                 .answer()
                 .answerAsString();
```

---

</details>

<details>
<summary>Answer Byte</summary>

### Retrieve Response as Byte Array

This method retrieves the content of the HTTP response as a byte array.

```  
 var answer = get()
                 ...
                 .act()
                 .actPerform()
                 .answer()
                 .answerAsByte();
```

---

</details>

<details>
<summary>Answer Header</summary>

### Retrieve Specific Response Header

This method retrieves the value of a specific response header.

```  
 var answer = get()
                 ...
                 .act()
                 .actPerform()
                 .answer()
                 .answerHeader(KEY);
```

---

</details>


<details>
<summary>Answer Void</summary>

### Execute and Discard Response Content

This method retrieves the result of the HTTP request without returning any content. It is used when
the response is not needed.

```  
  get()
      ...
      .act()
      .actPerform()
      .answer()
      .answerVoid();
```

</details>

---

## Examples

### Test Case: Validate Response with Expected List of DTOs

In this test case, a GET request is made to the specified endpoint using a URL that includes a
parameter. The following steps are executed:

1. **Arrange**: The initial setup is performed, including defining the request URL.
2. **Arrange URL**: The base URL (`GET_EXAMPLE`) is arranged for the request.
3. **Arrange PARAM**: A query parameter is added to the request using `arrangeKeyValue`, allowing
   for dynamic data retrieval.
4. **Action**: The request is executed with the `act()` method, followed by `actPerform()` to send
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
void WHEN_call_endpoint_THEN_return_expected_list_dto() throws Exception {

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
void GIVEN_files_WHEN_call_endpoint_THEN_return_expected_status_201() throws Exception {

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

## License

This project is licensed under the Apache License, Version 2.0. See `LICENSE.txt` for more
information.

