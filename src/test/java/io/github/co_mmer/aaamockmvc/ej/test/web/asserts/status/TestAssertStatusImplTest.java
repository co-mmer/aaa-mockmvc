package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.status;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.TestAssertBase;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.bytes.TestAssertByteImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.clazz.TestAssertClassImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.collection.TestAssertCollectionImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.custom.TestAssertCustomImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.head.TestAssertHeadImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.map.TestAssertMapImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.string.TestAssertStringImpl;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.http.HttpStatus;

class TestAssertStatusImplTest extends TestAssertBase {

  private ObjectMapper objectMapper;
  private TestAssert1Status testAssert1;
  private TestAssert2Status testAssert2;

  @BeforeEach
  void setUp() {
    initMockServer();

    this.objectMapper = new ObjectMapper();
    this.testAssert1 = new TestAssertStatusImpl(this.actions, this.objectMapper);
    this.testAssert2 = new TestAssertStatusImpl(this.actions, this.objectMapper);
  }

  @Nested
  class constructor {

    @SuppressWarnings("ConstantConditions")
    @Test
    void GIVEN_null_null_WHEN_call_constructor_THEN_throw_NullPointerException() {
      //  Assert
      assertThrows(
          NullPointerException.class,

          // Act
          () -> new TestAssertStatusImpl(null, null));
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void GIVEN_objectMapper_null_WHEN_call_constructor_THEN_throw_NullPointerException() {
      //  Assert
      assertThrows(
          NullPointerException.class,

          // Act
          () -> new TestAssertStatusImpl(actions, null));
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void GIVEN_actions_null_WHEN_call_constructor_THEN_throw_NullPointerException() {
      //  Assert
      assertThrows(
          NullPointerException.class,

          // Act
          () -> new TestAssertStatusImpl(null, objectMapper));
    }
  }

  @Nested
  class assertStatus {

    @SuppressWarnings("ConstantConditions")
    @Test
    void GIVEN_null_WHEN_assertStatus_THEN_throw_NullPointerException() {
      //  Assert
      assertThrows(
          NullPointerException.class,

          // Act
          () -> testAssert1.assertStatus(null));
    }

    @Test
    void GIVEN_expected_HttpStatus_WHEN_assertStatus_THEN_assert_is_true() {
      // Arrange
      response.setStatus(OK.value());

      // Act & Assert
      testAssert1.assertStatus(OK);
    }

    @Test
    void GIVEN_unexpected_HttpStatus_WHEN_assertStatus_THEN_assert_is_false() {
      // Arrange
      response.setStatus(NO_CONTENT.value());

      // Act & Assert
      assertThrows(AssertionError.class, () -> testAssert1.assertStatus(OK));
    }

    @Test
    void GIVEN_expected_status_WHEN_assertStatus_THEN_assert_is_true() {
      // Arrange
      response.setStatus(OK.value());

      // Act & Assert
      testAssert1.assertStatus(200);
    }

    @Test
    void GIVEN_unexpected_status_WHEN_assertStatus_THEN_assert_is_false() {
      // Arrange
      response.setStatus(NO_CONTENT.value());

      // Act & Assert
      assertThrows(AssertionError.class, () -> testAssert1.assertStatus(200));
    }
  }

  @Nested
  class assertStatusIs {

    @ParameterizedTest
    @MethodSource("useCaseHttpStatus")
    void GIVEN_expected_HttpStatus_WHEN_assert_THEN_assert_is_true(HttpStatus status) {
      // Arrange
      response.setStatus(status.value());

      // Act & Assert
      switch (status) {
        case OK:
          testAssert1.assertStatusIsOk();
          break;
        case CREATED:
          testAssert1.assertStatusIsCreated();
          break;
        case ACCEPTED:
          testAssert1.assertStatusIsAccepted();
          break;
        case NOT_FOUND:
          testAssert1.assertStatusIsNotFound();
          break;
        case FORBIDDEN:
          testAssert1.assertStatusIsAccessForbidden();
          break;
        case UNAUTHORIZED:
          testAssert1.assertStatusIsAccessUnauthorized();
          break;
        default:
          Assertions.fail();
      }
    }

    @SuppressWarnings("all")
    @ParameterizedTest
    @MethodSource("useCaseHttpStatus")
    void GIVEN_unexpected_HttpStatus_WHEN_assert_THEN_assert_is_false(HttpStatus status) {
      // Arrange
      response.setStatus(1);

      // Act & Assert
      assertThrows(
          AssertionError.class,
          () -> {
            switch (status) {
              case OK:
                testAssert1.assertStatusIsOk();
                break;
              case CREATED:
                testAssert1.assertStatusIsCreated();
                break;
              case ACCEPTED:
                testAssert1.assertStatusIsAccepted();
                break;
              case NOT_FOUND:
                testAssert1.assertStatusIsNotFound();
                break;
              case FORBIDDEN:
                testAssert1.assertStatusIsAccessForbidden();
                break;
              case UNAUTHORIZED:
                testAssert1.assertStatusIsAccessUnauthorized();
                break;
              default:
                Assertions.fail();
            }
          });
    }

    private static Stream<HttpStatus> useCaseHttpStatus() {
      return Stream.of(OK, CREATED, ACCEPTED, NOT_FOUND, FORBIDDEN, UNAUTHORIZED);
    }
  }

  @Nested
  class assertStatusIsClientError {

    @Test
    void GIVEN_400_499_WHEN_assertStatusIsClientError_THEN_assert_is_true() {
      // Arrange
      for (var statusCode = 400; statusCode <= 499; statusCode++) {
        response.setStatus(statusCode);

        // Act & Assert
        testAssert1.assertStatusIsClientError();
      }
    }

    @ParameterizedTest
    @ValueSource(ints = {399, 500})
    void GIVEN_unexpected_HttpStatus_WHEN_assertStatusIsClientError_THEN_assert_is_false(
        int unexpectedStatusCodes) {
      // Arrange
      response.setStatus(unexpectedStatusCodes);

      // Act & Assert
      assertThrows(AssertionError.class, () -> testAssert1.assertStatusIsClientError());
    }
  }

  @Nested
  class assertStatusIsServerError {

    @Test
    void GIVEN_500_599_WHEN_assertStatusIsServerError_THEN_assert_is_true() {
      // Arrange
      for (var statusCode = 500; statusCode <= 599; statusCode++) {
        response.setStatus(statusCode);

        // Act & Assert
        testAssert1.assertStatusIsServerError();
      }
    }

    @ParameterizedTest
    @ValueSource(ints = {499, 600})
    void GIVEN_unexpected_HttpStatus_WHEN_assertStatusIsServerError_THEN_assert_is_false(
        int unexpectedStatusCodes) {
      // Arrange
      response.setStatus(unexpectedStatusCodes);

      // Act & Assert
      assertThrows(AssertionError.class, () -> testAssert1.assertStatusIsServerError());
    }
  }

  @Nested
  class assertStatusIsRedirect {

    @Test
    void GIVEN_300_399_WHEN_assertStatusIsRedirect_THEN_assert_is_true() {
      // Arrange
      for (var statusCode = 300; statusCode <= 399; statusCode++) {
        response.setStatus(statusCode);

        // Act & Assert
        testAssert1.assertStatusIsRedirect();
      }
    }

    @ParameterizedTest
    @ValueSource(ints = {299, 400})
    void GIVEN_unexpected_HttpStatus_WHEN_assertStatusIsRedirect_THEN_assert_is_false(
        int unexpectedStatusCodes) {
      // Arrange
      response.setStatus(unexpectedStatusCodes);

      // Act & Assert
      assertThrows(AssertionError.class, () -> testAssert1.assertStatusIsRedirect());
    }
  }

  @Nested
  class assertStatusInRange {

    @Test
    void GIVEN_300_399_WHEN_assertStatusInRange_THEN_assert_is_true() {
      // Arrange
      for (var statusCode = 100; statusCode <= 200; statusCode++) {
        response.setStatus(statusCode);

        // Act & Assert
        testAssert1.assertStatusInRange(100, 200);
      }
    }

    @ParameterizedTest
    @ValueSource(ints = {99, 201})
    void GIVEN_unexpected_HttpStatus_WHEN_assertStatusInRange_THEN_assert_is_false(
        int unexpectedStatusCodes) {
      // Arrange
      response.setStatus(unexpectedStatusCodes);

      // Act & Assert
      assertThrows(AssertionError.class, () -> testAssert1.assertStatusInRange(100, 200));
    }
  }

  @Nested
  class nextSteps {

    @Test
    void WHEN_assertContent_THEN_return_expected_class() {
      // Act
      var assertContent = testAssert2.assertContent();

      // Assert
      assertThat(assertContent.getClass(), is(TestAssertStringImpl.class));
    }

    @Test
    void WHEN_assertClass_THEN_return_expected_class() {
      // Act
      var assertClass = testAssert2.assertClass();

      // Assert
      assertThat(assertClass.getClass(), is(TestAssertClassImpl.class));
    }

    @Test
    void WHEN_assertByte_THEN_return_expected_class() {
      // Act
      var assertByte = testAssert2.assertByte();

      // Assert
      assertThat(assertByte.getClass(), is(TestAssertByteImpl.class));
    }

    @Test
    void WHEN_assertCollection_THEN_return_expected_class() {
      // Act
      var assertCollection = testAssert2.assertCollection();

      // Assert
      assertThat(assertCollection.getClass(), is(TestAssertCollectionImpl.class));
    }

    @Test
    void WHEN_assertMap_THEN_return_expected_class() {
      // Act
      var assertMap = testAssert2.assertMap();

      // Assert
      assertThat(assertMap.getClass(), is(TestAssertMapImpl.class));
    }

    @Test
    void WHEN_assertHead_THEN_return_expected_class() {
      // Arrange
      useHeader();

      // Act
      var assertHead = testAssert2.assertHead();

      // Assert
      assertThat(assertHead.getClass(), is(TestAssertHeadImpl.class));
    }

    @Test
    void WHEN_assertCustom_THEN_return_expected_class() {
      // Act
      var assertCustom = testAssert2.assertCustom();

      // Assert
      assertThat(assertCustom.getClass(), is(TestAssertCustomImpl.class));
    }
  }
}
