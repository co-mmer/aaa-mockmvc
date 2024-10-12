<table border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td><img src="./images/aaa-mockmvc-icon.png" alt="icon" style="vertical-align: middle;"/></td>
    <td><h1>AAA-MockMvc</h1></td>
  </tr>
</table>

## Overview

This project provides a framework for structuring unit tests following the AAA (Arrange, Act,
Assert) pattern with a focus on clarity and readability. The library offers a fluent API that guides
the developer through each phase, ensuring that only contextually relevant methods are available at
each step. This design choice enhances the ease of writing, maintaining, and understanding tests.
The key goal is to provide a clean separation of concerns within the test, allowing developers to
express their test logic fluently and naturally.

___

## Description

In the provided library, every test follows the AAA structure using the following three phases:

1. **Arrange**: Set up the necessary conditions for the test (e.g., define URL, parameters,
   headers).
2. **Act**: Perform the operation (e.g., make the API request).
3. **Assert**: Validate the result (e.g., check HTTP status, response content).

___

## Guide

To write tests using this framework, certain configurations are necessary. Below are the steps
required to set up your testing environment effectively.

## 1. Configuration

To configure `AAAMockMvc` for your tests, there are four main options available. Each configuration
provides flexibility to use `AAAMockMvc` in the test setup to interact with the MVC testing
framework, depending on the specific project requirements.

### Option A: WebApplicationContext with Default ObjectMapper

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

### Option B: WebApplicationContext with Custom ObjectMapper

In this option, both the `WebApplicationContext` and a custom `ObjectMapper` can be passed
to `AAAMockMvc`.

#### Steps:

1. Define a configuration class.
2. Create a ObjectMapper bean with custom configuration.
3. Pass the custom ObjectMapper instance to the AAAMockMvc bean

```java

import com.fasterxml.jackson.databind.ObjectMapper;

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

### Option C: Custom MockMvc and Default ObjectMapper

This option allows for configuring `AAAMockMvc` with a custom `MockMvc` instance.
The framework will use a default ObjectMapper (`new ObjectMapper()`).

#### Steps:

1. Define a configuration class.
2. Create a MockMvc bean with custom configuration.
3. Pass the custom MockMvc instance to the AAAMockMvc bean

```java

import com.fasterxml.jackson.databind.ObjectMapper;

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

### Option D: Custom MockMvc and Custom ObjectMapper

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

### Conclusion

All options provide the necessary configuration to use `AAAMockMvc` in tests:

- **Option A** is ideal for simple projects that use the default `WebApplicationContext` and rely on
  the default `ObjectMapper`.
- **Option B** provides flexibility by allowing the use of a custom `ObjectMapper` with the
  default `WebApplicationContext`.
- **Option C** is suited for projects requiring a custom `MockMvc` setup, while still using the
  default `ObjectMapper`.
- **Option D** offers full customization, allowing both a custom `MockMvc` and a
  custom `ObjectMapper`, for projects with advanced configuration needs.

---

### 2. Using AAAMockMvc in Tests

There are two options for utilizing the AAAMockMvc in test classes:

#### Option A: Directly Autowiring AAAMockMvc

AAAMockMvc can be directly autowired into the test class. This method allows the API methods to
be
used directly in the tests.

```java

@WebMvcTest

public class ControllerTest {

  @Autowired
  private AAAMockMvc aaaMockMvc;

  @Test
  void WHEN_calling_endpoint_THEN_return_expected_status() throws Exception {
    aaaMockMvc.get()
        .arrange()
        .arrangeUrl(GET_EXAMPLE)
        .act()
        .actPerform()
        .assertResult()
        .assertMatcher(status().isOk());
  }
}
```

#### Option B: Extending AAAMockMvcAbstract

Alternatively, extending the abstract class `AAAMockMvcAbstract` is another option, which
provides
all
necessary methods like get(), post(), etc. This approach is useful for encapsulating common test
behaviors and reducing boilerplate code in test classes.

```java

@WebMvcTest
public class ControllerTest extends AAAMockMvcAbstract {

  @Test
  void WHEN_calling_endpoint_THEN_return_expected_content() throws Exception {
    get()
        .arrange()
        .arrangeUrl(GET_EXMAPLE)
        .act()
        .actPerform()
        .assertResponse()
        .assertContent("expectedResponse");
  }
}
```

___

## Examples

The following examples illustrate the usage of the fluent API for arranging, acting, and asserting
in test cases.

### Example 1: Status asserts

```java

@Test
void WHEN_call_endpoint_THEN_return_status_200() throws Exception {
  get()
      .arrange()
      .arrangeUrl(GET_EXAMPLE)
      .act()
      .actPerform()
      .asserts()
      .assertStatus(200);
}
```

```java

@Test
void WHEN_call_endpoint_THEN_return_status_ok() throws Exception {
  get()
      .arrange()
      .arrangeUrl(GET_EXAMPLE)
      .act()
      .actPerform()
      .asserts()
      .assertStatus(HttpStatus.OK);
}
```

### Example 2: String Content asserts

```java

@Test
void WHEN_call_endpoint_THEN_return_expected_content() throws Exception {
  get()
      .arrange()
      .arrangeUrl(GET_EXAMPLE)
      .act()
      .actPerform()
      .asserts()
      .assertStringContentIsNotEmpty()
      .assertStringContent("expectedContent");
}
```

### Example 3: Collection Content asserts

```java

@Test
void WHEN_call_endpoint_THEN_return_expected_content() throws Exception {
  // TEST_DTO_LIST = List.of(TEST_DTO_1, TEST_DTO_2);

  get()
      .arrange()
      .arrangeUrl(GET_EXAMPLE)
      .act()
      .actPerform()
      .asserts()
      .assertStatus(200)
      .assertEquals(DemoDto.class, TEST_DTO_LIST);
}
```

```java

@Test
void WHEN_call_endpoint_THEN_return_expected_content() throws Exception {
  // TEST_DTO_SET = Set.of(TEST_DTO_1, TEST_DTO_2);

  get()
      .arrange()
      .arrangeUrl(GET_EXAMPLE)
      .act()
      .actPerform()
      .asserts()
      .assertStatus(200)
      .assertEquals(DemoDto.class, TEST_DTO_SET);
}
```

```java

@Test
void WHEN_call_endpoint_THEN_return_expected_content() throws Exception {
  // TEST_DTO_MAP = Map<Boolean, DemoDto> TEST_DTO_MAP = Map.of(TRUE, TEST_DTO_1, FALSE, TEST_DTO_2);

  get()
      .arrange()
      .arrangeUrl(GET_EXAMPLE)
      .act()
      .actPerform()
      .asserts()
      .assertStatus(200)
      .assertEquals(Boolean.class, DemoDto.class, TEST_DTO_MAP);
}
```

### Example 4: Status and Content asserts

```java

@Test
void WHEN_call_endpoint_THEN_return_expected_status_content() throws Exception {
  get()
      .arrange()
      .arrangeUrl(GET_EXAMPLE)
      .act()
      .actPerform()
      .asserts()
      .assertStatus(200)
      .assertStringContentIsNotEmpty()
      .assertStringContent("expectedContent");
}
```

### Example 5: Complex Request with Parameters and Headers

```java

@Test
void WHEN_call_endpoint_THEN_return_expected_status_content() throws Exception {
  get()
      .arrange()
      .arrangeUrl(GET_EXAMPLE, VAR_ID_8)
      .arrangeParam()
      .arrangeKeyValue(PARAM_KEY_1, PARAM_VALUE_1)
      .arrangeHead()
      .arrangeKeyValue(HEAD_X_CUSTOM_1, HEAD_X_CUSTOM_VALUE_1)
      .act()
      .actPerform()
      .asserts()
      .assertStatus(200)
      .assertStringContentIsNotEmpty()
      .assertStringContent("expectedContent");
}
```

### Example 6: Complex Request with Parameters, Headers and Body

```java

@Test
void GIVEN_files_WHEN_call_endpoint_THEN_return_expected_status_201()
    throws Exception {

  post()
      .arrange()
      .arrangeUrl(POST_EXAMPLE)
      .arrangeParam()
      .arrangeKeyValue(PARAM_KEY_1, PARAM_VALUE_1)
      .arrangeHead()
      .arrangeKeyValue(HEAD_X_CUSTOM_1, HEAD_X_CUSTOM_VALUE_1)
      .arrangeBody()
      .arrangeFiles(TEST_BODY_FILES)
      .act()
      .actPerform()
      .asserts()
      .assertStatus(201);
}
```

### Example 7: Custom Assertions After actPerform()

It is possible, in addition to the provided assertion methods, to directly
access the `ResultActions` object after `actPerform()` to extract the response content (e.g.,
using `getContentAsString()`) and perform custom assertions. This provides the flexibility to
extend beyond the provided assertions if needed.

```java

@Test
void WHEN_call_endpoint_THEN_custom_assertions_on_result() throws Exception {
  get()
      .arrange()
      .arrangeUrl(GET_EXAMPLE)
      .act()
      .actPerform()
      .resultActions()

      // Assert
      .andExpect(status().isCreated())
      .andReturn();
}
```

```java

@Test
void WHEN_call_endpoint_THEN_custom_assertions_on_result() throws Exception {
  String result = get()
      .arrange()
      .arrangeUrl(GET_EXAMPLE)
      .act()
      .actPerform()
      .resultAsString(); // Perform the action and store the result

  // Custom assertions on the result
  assertTrue(result.contains("expectedContent"));
}
```