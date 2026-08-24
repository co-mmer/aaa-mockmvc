# <img src="./images/aaa-mockmvc-icon-27.png" align="left"/> Release

## [2.1.0]

Version **2.1.0** strengthens the AAA-MockMvc DSL with consistent validation and clearer, more
actionable failure messages.

Invalid request configurations, response target types, and assertion inputs are now rejected close
to where they are supplied with descriptive `IllegalArgumentException` messages. Each message
identifies the affected DSL argument and, when multiple values or conditions are supplied, includes
its one-based position.

Assertion failures now use a consistent structure that separates the expected result, the observed
response value, and the reason for the mismatch. The public assertion DSL, response mapping
behavior, and assertion semantics remain unchanged.

### 🌿 Highlights

- **Consistent DSL validation** — Invalid arguments are rejected close to where they are supplied.
- **Actionable validation messages** — Errors identify the affected argument and, where applicable,
  its position.
- **Predictable exception behavior** — Null arguments in the affected APIs now
  produce `IllegalArgumentException` instead of Lombok-generated `NullPointerException`.
- **Structured assertion failures** — Failures consistently report `Expected`, `Actual`,
  and `Reason`.
- **Leaner dependency footprint** — Hamcrest is no longer used internally and has been removed as a
  framework dependency.
- **Stable public DSL** — Existing assertion calls and response mapping semantics remain unchanged.

### ✨ New Features

#### Request validation

Request validation now covers:

- Paths and URI syntax.
- Path variable values and supported types.
- Query parameter names and values.
- Header names and values, including protection against carriage-return and line-feed characters.
- Duplicate header names that differ only in letter casing.
- Accepted media types and content types.
- Text and multipart request bodies.

#### Response target validation

Response target types are now validated for:

- `answer().asObject()`
- `answer().asCollection()`
- `answer().asList()`
- `answer().asSet()`
- `answer().asMap()`

Missing result, element, map-key, and map-value types now produce precise validation messages.

#### Assertion validation

Assertion validation now covers:

- Expected values for booleans, strings, byte arrays, objects, collections, and maps.
- Expected HTTP statuses.
- Expected result classes and collection element classes.
- Map key and value classes.
- Expected and unexpected collection elements.
- Expected header names and values.
- Predicate arguments supplied to `matchAll()`, `matchAny()`, and `matchNone()`.

When one of multiple elements, header values, or match conditions is `null`, the validation message
identifies it by its one-based position.

### 🧹 Improvements

#### Structured assertion failures

Assertion failures are now easier to scan, understand, and compare while debugging tests. Existing
assertion calls remain unchanged.

Failures produced by the DSL now follow a consistent structure:

```text
Expected: <expected result>
Actual: <actual response value>
Reason: <why the assertion failed>
```

For example, asserting an expected object when the endpoint returns an empty body produces:

```text
Expected: SimpleObject[id=1, name=A]
Actual:   null
Reason:   The response body was absent.
```

Each field has a clear purpose:

- `Expected` describes the asserted outcome.
- `Actual` shows the observed response value.
- `Reason` explains the mismatch in context.

#### Dependency cleanup

Hamcrest is no longer used internally and has been removed as a framework dependency. This does not
change the public assertion DSL. Projects that use Hamcrest directly should declare it explicitly
rather than relying on AAA-MockMvc to provide it transitively.

### ☂️ Fixes

- Corrected `Accept` header handling for multiple media types. Accept values are now represented
  consistently as separate HTTP header values. As a result, the raw representation may change
  from `application/json, application/pdf` to `application/json,application/pdf`, while preserving
  the same HTTP semantics.

---

## [2.0.1]

### 🧹 Improvements

* This release updates core dependencies to maintain framework compatibility and stability.
* No code or behavior changes were made.

----

## [2.0.0]

Version **2.0.0** marks a major step forward for the AAA-MockMvc framework.  
The testing experience has been reimagined — more fluent, predictable, and closer to real-world
developer workflows.  
This release refines the **Arrange → Act → Assert/Answer** flow into a more natural rhythm, with
clear semantics, stronger assertions, and a new **step runner** for structured scenarios.

### 🌿 Highlights

- **A gentler AAA flow** — Build once in Arrange, execute once in Act, and verify confidently in
  Assert/Answer.  
  Tests are now more declarative, stable, and easy to reason about.

    ```java
    
    @SpringBootTest
    class MyTest extends AAAMockMvcTestSupport {
    
      @Test
      void GIVEN_newUser_WHEN_createUser_THEN_userStatusIsPending() {
    
        arrange()
            .post("/users")
            .body()
            .json(new User("Napoleon"));
    
        act()
            .perform();
    
        asserts()
            .status()
            .isCreated()
            .content().asClass(UserResponse.class)
            .isNotNull()
            .matchAll(r -> r.status().equals("pending"));
      }
    }
    
    ```


- **Step Runner** — Compose multiple AAA blocks into named steps within a single test.  
  Keeps tests organized while reflecting real-world test flows.

    ```java
    
    @SpringBootTest
    class MyTest extends AAAMockMvcTestSupport {
    
      @Test
      void GIVEN_newUser_WHEN_receiveNewUser_THEN_returnStatusOk() {
    
        step("Create user", () -> {
            arrange().post("/users").body().json(new User("Napoleon"));
            act().perform();
            asserts().status().isCreated();
        });
            
        step("Retrieve user", () -> {
            arrange().get("/users/Napoleon");
            act().perform();
            asserts().status().isOk();
        });
      }
    }
    
    ```

### ✨ New Features

- **Support Boolean**
    - Added fluent assertion support for boolean payloads via `content().asBoolean()`.
    - Responses can also be read directly using `answer().asBoolean()`.

### 🧹 Improvements

- **Short, memorable method names** — Consistent naming like `isOk`, `isCreated`, `asList`,
  and `containsEntryExactly` improves discoverability and reduces noise.
- **Sharper snapshot semantics** — `act().perform()` now executes only once; assertions and answers
  reuse the same cached response.
- **Better error experience** — Human-readable failure messages with contextual guidance and
  annotated step names.
- **Seamless Spring/JUnit integration** — Lightweight extension automatically resets state between
  tests.
- **Refined documentation and JavaDoc** — Practical examples and precise contracts make onboarding
  faster.

---

## [1.5.0]

This release improves integration testing support by introducing a dedicated base class and
automatic configuration for Spring-based projects.  
It simplifies setup, reduces boilerplate, and provides a smoother developer experience when writing
integration tests.

### ✨ New Features

- **`AAAMockMvcAbstract`**
    - New base class for writing Spring integration tests.
    - Provides convenient access to HTTP request builders such as `get()`, `post()`, and `put()`.
    - Internally injects a fully configured `AAAMockMvc` instance via Spring Dependency Injection.

- **`AAAMockMvcConfig`**
    - Adds automatic configuration using Spring’s `ObjectProvider` mechanism.
    - Automatically detects and reuses existing project beans (`MockMvc`, `ObjectMapper`, etc.).

### 🧹 Improvements

- Updated and clarified documentation for the Arrange section, offering better guidance on test
  setup patterns.

---

## [1.4.2]

### 🧹 Improvements

* This release updates core dependencies to maintain framework compatibility and stability.
* No code or behavior changes were made.

---

## [1.4.1]

This maintenance release focuses on dependency updates, cleaner runtime behavior, and improved error
handling for null responses.

### 🧹 Improvements

* This release updates core dependencies to maintain framework compatibility and stability.

### ☂️ Fixes

- Methods in the `answerAs*()` family now return `null` instead of throwing
  a `MismatchedInputException`  
  when the HTTP response body is empty. This makes tests more predictable and reduces unnecessary
  exception noise.

---

## [1.4.0]

This release introduces a wide range of new assertion utilities, allowing developers to validate
classes, collections, maps, and byte arrays directly in HTTP response tests.  
These new methods make test assertions more expressive, readable, and type-safe.

### ✨ New Features

- **Class Assertions**
    - New assertion methods to verify that the returned class is empty, not empty, or matches
      expected types and predicates.
    - Enables concise validation of class-level properties in API responses.

- **Collection Assertions**
    - Rich set of methods to check for collection size, emptiness, content equality, and containment
      in any order.
    - Supports validating elements against multiple predicates for greater flexibility.

- **Map Assertions**
    - Methods for checking map size, emptiness, and exact key–value pair matches.
    - Simplifies verification of structured JSON responses deserialized as maps.

- **Byte Array Assertions**
    - New utilities to validate binary responses (e.g., file downloads or encoded payloads).
    - Includes assertions for emptiness, exact length, and equality.

---

## [1.3.0]

This release improves how HTTP responses are read and validated in the **Answer** section of the AAA
flow.  
It introduces new deserialization utilities and more reliable text comparisons, helping developers
assert complex responses with less boilerplate.

### ✨ New Features

- **Answer Enhancements**
    - Added new methods to easily deserialize HTTP responses into various Java types:
        - `answerAsObject(T resultType)` — map response body to a single object.
        - `answerAsList(Class<T> elementType)` — convert response to a list.
        - `answerAsSet(Class<T> elementType)` — convert response to a set.
        - `answerAsMap(Class<K>, Class<V>)` — convert response to a map.
    - These methods simplify working with JSON responses and reduce manual parsing.

- **Arrange Enhancements**
    - Added `arrangeJson(T content)` for setting JSON request bodies in PUT, POST, and PATCH
      operations.
    - Improves readability and consistency when arranging HTTP requests.

### 🧹 Improvements

- **Unicode Normalization in Assertions**
    - `assertContentEquals()` now normalizes text using Unicode NFC form before comparison.
    - Ensures consistent test results even when different text encodings are used.

---

## [1.2.1]

### 🧹 Improvements

- Enhanced overall test coverage across the framework.
- Increased reliability and stability in core functionalities.

---

## [1.2.0]

This release introduces the **TestAnswer** component, extending the AAA flow with a dedicated layer
for accessing HTTP responses.

### ✨ New Features

- **TestAnswer**
    - Added `answer()` method in `TestAct` to retrieve and process the result of executed HTTP
      requests.

---

## [1.1.0]

This release introduces new assertion capabilities, expanding the AAA-MockMvc framework’s ability to
validate HTTP responses precisely and clearly.

### ✨ New Features

- **Custom Assertions**
    - Added `assertCustom()` in `TestAssert` for flexible, user-defined assertions.
- **Status Assertions**
    - Added `assertStatus()` for validating HTTP response codes.
    - Includes predefined methods for common status checks:
        - `isOk()`, `isCreated()`, `isAccepted()`
        - `isNotFound()`, `isClientError()`, `isServerError()`
        - `isRedirect()`, `isAccessForbidden()`, `isAccessUnauthorized()`
        - `isInRange(min, max)`

---

## [1.0.0]

### 🌱 Initial Release

- Initial release of the **AAA-MockMvc**

### ✨ New Features

- Introduced the **AAA-MockMvc** testing framework.
- Provides a clean and expressive flow: **Arrange → Act → Assert/Answer**.
- Enables simplified and readable integration testing for Spring Boot applications.
- Offers a fluent API for building and asserting HTTP requests and responses.


