# <img src="./images/aaa-mockmvc-icon.png" align="left" style="margin-top: 35px;"/>

# Changelog

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

## [1.0.0]

- Initial release of the **AAA-MockMvc**


