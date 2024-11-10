# <img src="./images/aaa-mockmvc-icon.png" align="left" style="margin-top: 35px;"/>

# Changelog

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

- Content Assertion Methods **`assertContentEquals)`**:
    - Enhanced to normalize both the actual and expected response content using Unicode
      Normalization Form C (NFC).
    - This ensures consistent text representation across Unicode formats, improving reliability when
      comparing text-based content with potential Unicode variations.

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


