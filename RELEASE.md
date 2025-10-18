# <img src="./images/27px.png" align="left"/> Release

----

## [2.0.0]

Version 2.0.0 represents a major evolution of the AAA-MockMvc framework.  
It refines the core testing flow (Arrange → Act → Assert/Answer), improves error clarity, and
introduces a new step runner for building real-world test scenarios more intuitively.  
This release focuses on expressiveness, predictability, and a smoother developer experience.

### 🌿 Highlights

- **A gentler AAA flow** — Build once in Arrange, run once in Act, and read with confidence in
  Assert/Answer. Tests tell a clearer story and stay focused on intent.
- **Short, memorable method names** — Simplified, consistent naming (e.g., isOk,
  isCreated, asList, containsEntryExactly) for faster typing and scanning.
- **Step runner, made for real-world flows** — Name your steps, group multiple AAA blocks in one
  test,
  and (optionally) return the last captured answer from a step. Readability first.

### ✨ New Features

- **Step runner (step(...))**
    - Group multiple AAA blocks into named steps within a single test.
    - Each step runs in isolation and can return the last captured answer from its block.
    - Greatly improves readability and organization of complex test flows.
    - Example:

  ```java
    step("Add Napoleon", () -> {
        arrange().post(BASE + CREATE_USER).body().json(new User("Napoleon"));
        act().perform();
        asserts().status().isCreated();
    });

- **Support Boolean**
    - Added fluent assertion support for boolean payloads via `content().asBoolean()`.
    - Responses can also be read directly using `answer().asBoolean()`.

### 🧹 Improvements

- **Sharper Snapshot Semantics**
    - `act().perform()` executes exactly once; subsequent assertions read from the same cached
      response.  
      No hidden re-execution or I/O.

- **Error Hygiene**
    - Added clearer precondition validation with actionable messages, e.g.:
      > “Call `arrange().get|post|…` then `act().perform()` before using `asserts()` or `answer()`.”

- **Spring/JUnit Integration**
    - Lightweight JUnit 5 extension that auto-cleans state before and after tests.
    - Ensures clean test suites without manual resets.


- **Documentation**
    - Refined docs and JavaDoc with practical examples for faster onboarding.

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

- Updated and clarified documentation for the Arrange section,  
  offering better guidance on test setup patterns.

---

## [1.4.2]

### 🧹 Improvements

This release updates core dependencies to maintain framework compatibility and stability.  
No code or behavior changes were made.

---

## [1.4.1]

This maintenance release focuses on dependency updates, cleaner runtime behavior, and improved error
handling for null responses.

### 🧹 Improvements

- Reduced the number of transitive dependencies, resulting in a lighter and faster test runtime.

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


