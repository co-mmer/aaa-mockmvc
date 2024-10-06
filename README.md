# <img src="./images/aaa-mockmvc-icon.png" align="left" style="margin-top: 0x;"/>

# AAA-MockMVC

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

### 1. Create a Configuration Class

First, a configuration class must be created that provides a `AAAMockMvc` bean. This bean will
be
used to interact with the MVC testing framework.

```java

@Configuration
public class AAAMockMvcConfig {

  @Bean
  public AAAMockMvc aaaMockMvc(WebApplicationContext context) {
    return new AAAMockMvc(context);
  }
}
```

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

### Example 1: Status Check

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

### Example 2: Content Check

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

### Example 3: Status and Content Check

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

### Example 4: Complex Request with Parameters and Headers

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

### Example 5: Complex Request with Parameters, Headers and Body

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

### Example 6: Custom Assertions After actPerform()

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