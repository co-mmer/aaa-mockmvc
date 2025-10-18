package io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.status;

import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.STEP_NAME;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.status.TestAssert1Status;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.status.TestAssert2Status;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.TestAssertBase;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.content.TestAssertContentImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.head.TestAssertHeadImpl;
import io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestContext;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.http.HttpStatus;

@SuppressWarnings("java:S2699")
class TestAssertStatusImplTest extends TestAssertBase {

  private TestAssert1Status testAssert1;
  private TestAssert2Status testAssert2;

  @BeforeEach
  void setUp() {
    var context = TestContext.createContext(STEP_NAME);
    this.useContext(context);
    this.testAssert1 = new TestAssertStatusImpl(context);
    this.testAssert2 = new TestAssertStatusImpl(context);
  }

  @Nested
  class constructor {

    @Test
    @SuppressWarnings("ConstantConditions")
    void GIVEN_null_WHEN_call_constructor_THEN_throw_Exception() {
      assertThrows(NullPointerException.class, () -> new TestAssertStatusImpl(null));
    }
  }

  @Nested
  class is {

    @Test
    @SuppressWarnings("ConstantConditions")
    void GIVEN_null_WHEN_is_THEN_throw_Exception() {
      assertThrows(NullPointerException.class, () -> testAssert1.is(null));
    }

    @Test
    void GIVEN_ok_WHEN_isOk_THEN_success() {
      // Arrange
      useActResult(OK);

      // Act & Assert
      testAssert1.is(OK);
    }

    @Test
    void GIVEN_noContent_WHEN_isOk_THEN_failed() {
      // Arrange
      useActResult(NO_CONTENT);

      // Act & Assert
      var ex = assertThrows(AssertionError.class, () -> testAssert1.is(OK));
      assertThat(ex.getMessage(), containsString(STEP_NAME));
    }

    @Test
    void GIVEN_ok_WHEN_is200_THEN_success() {
      // Arrange
      useActResult(OK);

      // Act & Assert
      testAssert1.is(200);
    }

    @Test
    void GIVEN_noContent_WHEN_is200_THEN_failed() {
      // Arrange
      useActResult(NO_CONTENT);

      // Act & Assert
      var ex = assertThrows(AssertionError.class, () -> testAssert1.is(200));
      assertThat(ex.getMessage(), containsString(STEP_NAME));
    }
  }

  @Nested
  class status {

    @ParameterizedTest
    @MethodSource("useCaseHttpStatus")
    void GIVEN_expected_HttpStatus_WHEN_assert_THEN_success(HttpStatus status) {
      // Arrange
      useActResult(status);

      // Act & Assert
      switch (status) {
        case OK:
          testAssert1.isOk();
          break;
        case CREATED:
          testAssert1.isCreated();
          break;
        case ACCEPTED:
          testAssert1.isAccepted();
          break;
        case NOT_FOUND:
          testAssert1.isNotFound();
          break;
        case FORBIDDEN:
          testAssert1.isForbidden();
          break;
        case UNAUTHORIZED:
          testAssert1.isUnauthorized();
          break;
        default:
          Assertions.fail();
      }
    }

    @ParameterizedTest
    @MethodSource("useCaseHttpStatus")
    @SuppressWarnings("java:S5778")
    void GIVEN_unexpected_HttpStatus_WHEN_assert_THEN_failed(HttpStatus status) {
      // Arrange
      useActResult(1);

      // Act & Assert
      var ex =
          assertThrows(
              AssertionError.class,
              () -> {
                switch (status) {
                  case OK:
                    testAssert1.isOk();
                    break;
                  case CREATED:
                    testAssert1.isCreated();
                    break;
                  case ACCEPTED:
                    testAssert1.isAccepted();
                    break;
                  case NOT_FOUND:
                    testAssert1.isNotFound();
                    break;
                  case FORBIDDEN:
                    testAssert1.isForbidden();
                    break;
                  case UNAUTHORIZED:
                    testAssert1.isUnauthorized();
                    break;
                  default:
                    Assertions.fail();
                }
              });
      assertThat(ex.getMessage(), containsString(STEP_NAME));
    }

    private static Stream<HttpStatus> useCaseHttpStatus() {
      return Stream.of(OK, CREATED, ACCEPTED, NOT_FOUND, FORBIDDEN, UNAUTHORIZED);
    }
  }

  @Nested
  class is2xxSuccessful {

    @Test
    void GIVEN_200_299_WHEN_is2xxSuccessful_THEN_success() {
      // Arrange
      for (var statusCode = 200; statusCode <= 299; statusCode++) {
        useActResult(statusCode);

        // Act & Assert
        testAssert1.is2xxSuccessful();
      }
    }

    @ParameterizedTest
    @ValueSource(ints = {199, 301})
    void GIVEN_199_300_WHEN_is2xxSuccessful_THEN_failed(int unexpectedStatusCodes) {
      // Arrange
      useActResult(unexpectedStatusCodes);

      // Act & Assert
      var ex = assertThrows(AssertionError.class, () -> testAssert1.is2xxSuccessful());
      assertThat(ex.getMessage(), containsString(STEP_NAME));
    }
  }

  @Nested
  class is3xxRedirect {

    @Test
    void GIVEN_300_399_WHEN_is3xxRedirect_THEN_success() {
      // Arrange
      for (var statusCode = 300; statusCode <= 399; statusCode++) {
        useActResult(statusCode);

        // Act & Assert
        testAssert1.is3xxRedirect();
      }
    }

    @ParameterizedTest
    @ValueSource(ints = {299, 400})
    void GIVEN_299_400_HttpStatus_WHEN_is3xxRedirect_THEN_failed(int unexpectedStatusCodes) {
      // Arrange
      useActResult(unexpectedStatusCodes);

      // Act & Assert
      var ex = assertThrows(AssertionError.class, () -> testAssert1.is3xxRedirect());
      assertThat(ex.getMessage(), containsString(STEP_NAME));
    }
  }

  @Nested
  class is4xxClientError {

    @Test
    void GIVEN_400_499_WHEN_is4xxClientError_THEN_success() {
      // Arrange
      for (var statusCode = 400; statusCode <= 499; statusCode++) {
        useActResult(statusCode);

        // Act & Assert
        testAssert1.is4xxClientError();
      }
    }

    @ParameterizedTest
    @ValueSource(ints = {399, 500})
    void GIVEN_399_500_WHEN_is4xxClientError_THEN_failed(int unexpectedStatusCodes) {
      // Arrange
      useActResult(unexpectedStatusCodes);

      // Act & Assert
      var ex = assertThrows(AssertionError.class, () -> testAssert1.is4xxClientError());
      assertThat(ex.getMessage(), containsString(STEP_NAME));
    }
  }

  @Nested
  class is5xxServerError {

    @Test
    void GIVEN_500_599_WHEN_is5xxServerError_THEN_success() {
      // Arrange
      for (var statusCode = 500; statusCode <= 599; statusCode++) {
        useActResult(statusCode);

        // Act & Assert
        testAssert1.is5xxServerError();
      }
    }

    @ParameterizedTest
    @ValueSource(ints = {499, 600})
    void GIVEN_499_600_WHEN_is5xxServerError_THEN_failed(int unexpectedStatusCodes) {
      // Arrange
      useActResult(unexpectedStatusCodes);

      // Act & Assert
      var ex = assertThrows(AssertionError.class, () -> testAssert1.is5xxServerError());
      assertThat(ex.getMessage(), containsString(STEP_NAME));
    }
  }

  @Nested
  class isInRange {

    @Test
    void GIVEN_300_399_WHEN_isInRange_THEN_success() {
      // Arrange
      for (var statusCode = 100; statusCode <= 200; statusCode++) {
        useActResult(statusCode);

        // Act & Assert
        testAssert1.isInRange(100, 200);
      }
    }

    @ParameterizedTest
    @ValueSource(ints = {99, 201})
    void GIVEN_99_201_WHEN_isInRange100_200_THEN_failed(int unexpectedStatusCodes) {
      // Arrange
      useActResult(unexpectedStatusCodes);

      // Act & Assert
      var ex = assertThrows(AssertionError.class, () -> testAssert1.isInRange(100, 200));
      assertThat(ex.getMessage(), containsString(STEP_NAME));
    }
  }

  @Nested
  class nextSteps {

    @Test
    void WHEN_content_THEN_return_expected_class() {
      // Act
      var content = testAssert2.content();

      // Assert
      assertThat(content, instanceOf(TestAssertContentImpl.class));
    }

    @Test
    void WHEN_headers_THEN_return_expected_class() {
      // Act
      var headers = testAssert2.headers();

      // Assert
      assertThat(headers, instanceOf(TestAssertHeadImpl.class));
    }
  }
}
