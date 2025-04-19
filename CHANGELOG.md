# <img src="./images/aaa-mockmvc-icon.png" align="left" style="margin-top: 35px;"/>

# Changelog

## [1.4.1]

### Upgraded

- lombok 1.18.34 → 1.18.38
- spring-boot 3.4.0 → 3.4.4

### Improved

- reduced number of transitive dependencies

### Fixed

- `answerAsObject(Class<T> expectedClass)` now returns `null` instead of throwing
  a `MismatchedInputException` when the HTTP response is `null`.
- `answerAsList(Class<T> elementType)` now returns `null` instead of throwing
  a `MismatchedInputException` when the HTTP response is `null`.
- `answerAsSet(Class<T> elementType)` now returns `null` instead of throwing
  a `MismatchedInputException` when the HTTP response is `null`.
- `answerAsMap(Class<K> keyType, Class<V> valueType)` now returns `null` instead of throwing
  a `MismatchedInputException` when the HTTP response is `null`.

## [1.4.0]

### Added

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

### Upgraded

- lombok 1.18.34 → 1.18.36
- spring-boot-starter-web 3.4.0 → 3.4.1
- spring-boot-starter-test 3.4.0 → 3.4.1
- mockito-core 5.14.2 → 5.15.2

---

## [1.3.0]

### Added

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

### Improved

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

### Improved

- This release improves general test coverage across the framework, enhancing reliability and
  stability in core functionalities.

---

## [1.2.0]

### Added

- **TestAnswer**: Introduced for accessing the results of HTTP requests.
    - New method `answer()` in TestAct for retrieving the result of the executed request.

---

## [1.1.0]

### Added

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

- Initial release of the **AAA-MockMvc**


