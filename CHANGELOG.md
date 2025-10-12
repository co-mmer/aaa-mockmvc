# <img src="./images/27px.png" align="left"/> Changelog

----

## [2.0.0]

### 🌿 Highlights

- **A gentler AAA flow** — Build once in Arrange, run once in Act, and read with confidence in
  Assert/Answer. Tests tell a clearer story and stay focused on intent.
- **Short, memorable method names** — Simplified, consistent naming (e.g., isOk,
  isCreated, asList, containsEntryExactly) for faster typing and scanning.
- **Step runner, made for real-world flows** — Name your steps, group multiple AAA blocks in one
  test,
  and (optionally) return the last captured answer from a step. Readability first.
- **Crisp snapshot semantics** — `act().perform()` executes exactly once; every assertion and answer
  reads
  from the same cached response. No surprises, no hidden I/O.
- **Failure messages that help, not hinder** — Human-friendly errors with concrete next actions,
  optionally annotated with the step name to pinpoint issues fast.
- **Seamless Spring/JUnit fit** — A lightweight JUnit 5 extension that auto-cleans state
  before/after
  tests, so suites stay tidy by default.
- **Polished docs & JavaDoc**  — Practical examples and precise contracts that make the framework
  feel familiar from the first read.

### ✨ New Features

- **Step runner (step(...))**
    - Group multiple AAA blocks in one test with named steps. Each step
      runs in an isolated context and can (optionally) return the value from the last answer() call
      inside the block.
  ```java
    step("Add Napoleon", () -> {
        arrange().post(BASE + CREATE_USER).body().json(new User("Napoleon"));
        act().perform();
        asserts().status().isCreated();
    });

- **Support Boolean**
    - Assert boolean payloads fluently after mapping via `content().asBoolean()`.
    - Read boolean values directly with `answer().asBoolean()`.

### 🧹 Improvements

- **Sharper snapshot semantics** — `act().perform()` executes exactly **once**; `asserts()`
  and `answer()` never trigger extra I/O.

- **Exception model:**
    - Act failures → `TestActFailedError` (wraps infrastructure/runtime problems, not HTTP status
      codes)
    - Assert failures → `AssertionsFailedError`
    - Answer mapping failures → `TestAnswerException`

- **Error hygiene:**
    - Precondition validators with precise guidance (e.g., “Call `arrange().get|post|…`
      then `act().perform()` before `asserts()`/`answer()`”).

### 📦 Dependency Upgrades

- spring-boot 3.5.0 → 3.5.6
- lombok 1.18.40 → 1.18.42
- maven-javadoc-plugin 3.10.1 → 3.11.3
- central-publishing-maven-plugin 0.6.0 → 0.9.0
- maven-gpg-plugin 3.2.7 → 3.2.8
- maven-surefire-plugin 3.2.5 → 3.5.4
- jacoco-maven-plugin 0.8.12 → 0.8.13
- spotless-maven-plugin 2.43.0 → 3.0.0
- maven-compiler-plugin 3.13.0 → 3.14.1

---

## [2.0.0-RC1]

### 🔭 Overview

- **Release Candidate for 2.0.0** — API considered stable; only bug fixes and documentation
  polish planned until GA.
- **A refined AAA flow** — Arrange, Act, Assert/Answer now tell a clearer story with a consistent,
  one-time execution model.
- **Concise, memorable method names** — faster to type, easier to scan.
- **Step runner** — structure complex test flows with named steps.

---

## [1.5.0]

### ✨ New Features

- Introduced `AAAMockMvcAbstract` as the recommended base class for writing integration tests.
    - Provides convenient access to HTTP test request builders (`get()`, `post()`, `put()`, etc.)
    - Internally injects a fully configured `AAAMockMvc` instance via Spring Dependency Injection.

- Automatic configuration via new `AAAMockMvcConfig`
    - Uses Spring’s `ObjectProvider` mechanism to detect and inject existing beans.
    - Automatically reuses project-defined `MockMvc` and/or `ObjectMapper` beans if present.

### 🧹 Improvements

- Revised and improved the arrange section of the documentation for greater clarity and guidance.

### 📦 Dependency Upgrades

- spring-boot 3.4.5 → 3.5.0

---

## [1.4.2]

### 📦 Dependency Upgrades

- spring-boot 3.4.4 → 3.4.5

---

## [1.4.1]

### 📦 Dependency Upgrades

- lombok 1.18.34 → 1.18.38
- spring-boot 3.4.0 → 3.4.4

### 🧹 Improvements

- reduced number of transitive dependencies

### ☂️ Fixed

- `answerAsObject(Class<T> expectedClass)` now returns `null` instead of throwing
  a `MismatchedInputException` when the HTTP response is `null`.
- `answerAsList(Class<T> elementType)` now returns `null` instead of throwing
  a `MismatchedInputException` when the HTTP response is `null`.
- `answerAsSet(Class<T> elementType)` now returns `null` instead of throwing
  a `MismatchedInputException` when the HTTP response is `null`.
- `answerAsMap(Class<K> keyType, Class<V> valueType)` now returns `null` instead of throwing
  a `MismatchedInputException` when the HTTP response is `null`.

---

## [1.4.0]

### ✨ New Features

- Class Assertion
    - **`assertClassNotEmpty()`**: Asserts that the class of the HTTP response is not empty.
    - **`assertClassEmpty()`**: Asserts that the class of the HTTP response is empty.
    - **`assertClassEquals(Class, Object)`**: Asserts that the class of the HTTP response matches
      the expected object.
    - **`assertClassMatchAll(Class, Predicate)`**: Asserts that the class matches all specified
      conditions.
    - **`assertClassMatchAll(Class, Predicate...)`**: Asserts that the class matches all specified
      conditions.
    - **`assertClassMatchAny(Class, Predicate)`**: Asserts that the class matches at least one of
      the specified conditions.
    - **`assertClassMatchAny(Class, Predicate...)`**: Asserts that the class matches at least one of
      the specified conditions.
    - **`assertClassMatchNone(Class, Predicate)`**: Asserts that the class matches none of the
      specified conditions.
    - **`assertClassMatchNone(Class, Predicate...)`**: Asserts that the class matches none of the
      specified conditions.


- Collection Assertion
    - **`assertCollectionNotEmpty()`**: Asserts that the collection in the HTTP response is not
      empty.
    - **`assertCollectionEmpty()`**: Asserts that the collection in the HTTP response is empty.
    - **`assertCollectionSize(int)`**: Asserts that the size of the collection in the HTTP response
      matches the given size.
    - **`assertCollectionEquals(Class, Collection)`**: Asserts that the content of the HTTP response
      matches the given collection of objects.
    - **`assertCollectionContains(Class, Collection)`**: Asserts that the collection in the HTTP
      response contains the expected elements.
    - **`assertCollectionContains(Class, Object...)`**: Asserts that the collection in the HTTP
      response
      contains the specified elements (varargs).
    - **`assertCollectionContainsAnyOrder(Class, Collection)`**: Asserts that the collection in the
      HTTP
      response matches the given collection of objects, ignoring order.
    - **`assertCollectionNotContains(Class, Collection)`**: Asserts that the collection in the HTTP
      response does not contain the specified elements.
    - **`assertCollectionNotContains(Class, Object...)`**: Asserts that the collection in the HTTP
      response does not contain the specified elements (varargs).
    - **`assertCollectionMatchAll(Class, Predicate)`**: Asserts that all elements in the collection
      in
      the HTTP response match the specified condition.
    - **`assertCollectionMatchAll(Class, Predicate...)`**: Asserts that all elements in the
      collection
      match the specified conditions.
    - **`assertCollectionMatchAny(Class, Predicate)`**: Asserts that at least one element in the
      collection in the HTTP response matches the specified condition.
    - **`assertCollectionMatchAny(Class, Predicate...)`**: Asserts that at least one element in the
      collection matches the specified conditions.
    - **`assertCollectionMatchNone(Class, Predicate)`**: Asserts that none of the elements in the
      collection in the HTTP response match the specified condition.
    - **`assertCollectionMatchNone(Class, Predicate...)`**: Asserts that none of the elements in the
      collection match any of the specified conditions.


- Map Assertion
    - **`assertMapNotEmpty()`**: Asserts that the map in the HTTP response is not empty.
    - **`assertMapEmpty()`**: Asserts that the map in the HTTP response is empty.
    - **`assertMapSize(int)`**: Asserts that the size of the map in the HTTP response matches the
      given size.
    - **`assertMapEquals(Class, Class, Map)`**: Asserts that the map in the HTTP response matches
      the expected map of key-value pairs.


- Byte Array Assertion
    - **`assertByteNotEmpty()`**: Asserts that the byte array content of the HTTP response is not
      empty.
    - **`assertByteEmpty()`**: Asserts that the byte array content of the HTTP response is empty.
    - **`assertByteLength(int)`**: Asserts that the length of the byte array content of the
      HTTP response matches the specified value.
    - **`assertByteEquals(byte[])`**: Asserts that the byte array content of the HTTP response
      matches
      the expected byte array.

### 📦 Dependency Upgrades

- lombok 1.18.34 → 1.18.36
- spring-boot-starter-web 3.4.0 → 3.4.1
- spring-boot-starter-test 3.4.0 → 3.4.1
- mockito-core 5.14.2 → 5.15.2

---

## [1.3.0]

### ✨ New Features

- Answer Section Enhancements
    - **`answerAsObject(T resultType)`**: Deserializes the HTTP response directly into an object
      of the specified type (T).
    - **`answerAsList(Class<T> elementType)`**: Deserializes the HTTP response into a set of the
      specified type (T).
    - **`answerAsSet(Class<T> elementType)`** Deserializes HTTP response content directly into
      a set of a specified class.
    - **`answerAsMap(Class<K> keyType, Class<V> valueType)`**:  Deserializes the HTTP response
      into a map with specified key and value types (K and V).


- Arrange Section Enhancements
    - **` arrangeJson(T content)`**: A new generic method for setting JSON content in the request
      body for PUT, POST, and PATCH requests.

### 🧹 Improvements

- Content Assertion Methods **`assertContentEquals()`**:
    - Enhanced to normalize both the actual and expected response content using Unicode
      Normalization Form C (NFC).
    - This ensures consistent text representation across Unicode formats, improving reliability when
      comparing text-based content with potential Unicode variations.
    - The following methods are affected:
        - `assertContentEquals(String expectedString)`
        - `assertContentEquals(Class<T> expectedClass, T expectedResponse)`,
        - `assertContentEquals(Class<T> expectedClass, List<T> expectedResponse)`
        - `assertContentEquals(Class<T> expectedClass, Set<T> expectedResponse)`
        - `assertContentEquals(Class<K> keyClass, Class<V> valueClass, Map<K, V> expectedResponse)`

---

## [1.2.1]

### 🧹 Improvements

- This release improves general test coverage across the framework, enhancing reliability and
  stability in core functionalities.

---

## [1.2.0]

### ✨ New Features

- **TestAnswer**: Introduced for accessing the results of HTTP requests.
    - New method `answer()` in TestAct for retrieving the result of the executed request.

---

## [1.1.0]

### ✨ New Features

- **TestAssertCustom**: Introduced for custom assertions.
    - New method `assertCustom()` in `TestAssert` for accessing custom assertions.

- **TestAssertStatus**: Added to validate HTTP status codes.
    - New method `assertStatus()` in `TestAssert` for accessing status assertions.
    - Implementation with various status assertion methods
        - `assertStatusIsOk()`
        - `assertStatusIsCreated()`
        - `assertStatusIsAccepted()`
        - `assertStatusIsNotFound()`
        - `assertStatusIsClientError()`
        - `assertStatusIsServerError()`
        - `assertStatusIsRedirect()`
        - `assertStatusIsAccessForbidden()`
        - `assertStatusIsAccessUnauthorized()`
        - `assertStatusInRange(int minStatusCode, int maxStatusCode);`

---

## [1.0.0]

### 🌱 Initial Release

- Initial release of the **AAA-MockMvc**


