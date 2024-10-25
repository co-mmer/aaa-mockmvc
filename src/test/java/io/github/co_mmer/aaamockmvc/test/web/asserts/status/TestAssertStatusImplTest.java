package io.github.co_mmer.aaamockmvc.test.web.asserts.status;

import static io.github.co_mmer.aaamockmvc.testdata.testutil.TestValue.TEST_HEAD_KEY_1;
import static io.github.co_mmer.aaamockmvc.testdata.testutil.TestValue.TEST_HEAD_VALUE_1;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.co_mmer.aaamockmvc.test.web.asserts.content.TestAssertContentImpl;
import io.github.co_mmer.aaamockmvc.test.web.asserts.custom.TestAssertCustomImpl;
import io.github.co_mmer.aaamockmvc.test.web.asserts.head.TestAssertHeadImpl;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

class TestAssertStatusImplTest {

  private ResultActions actions;
  private ObjectMapper objectMapper;
  private MockHttpServletResponse response;
  private TestAssert1Status testAssert1;
  private TestAssert2Status testAssert2;

  @BeforeEach
  void setUp() {
    this.actions = mock(ResultActions.class);
    var mvcResult = mock(MvcResult.class);
    this.response = new MockHttpServletResponse();

    when(mvcResult.getResponse()).thenReturn(this.response);
    when(this.actions.andReturn()).thenReturn(mvcResult);

    this.objectMapper = new ObjectMapper();
    this.testAssert1 = new TestAssertStatusImpl(this.actions, this.objectMapper);
    this.testAssert2 = new TestAssertStatusImpl(this.actions, this.objectMapper);
  }

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
        () -> new TestAssertStatusImpl(this.actions, null));
  }

  @SuppressWarnings("ConstantConditions")
  @Test
  void GIVEN_actions_null_WHEN_call_constructor_THEN_throw_NullPointerException() {
    //  Assert
    assertThrows(
        NullPointerException.class,

        // Act
        () -> new TestAssertStatusImpl(null, this.objectMapper));
  }

  @SuppressWarnings("ConstantConditions")
  @Test
  void GIVEN_null_WHEN_assertStatus_THEN_throw_NullPointerException() {
    //  Assert
    assertThrows(
        NullPointerException.class,

        // Act
        () -> this.testAssert1.assertStatus(null));
  }

  @Test
  void GIVEN_expected_HttpStatus_WHEN_assertStatus_THEN_assert_is_true() {
    // Arrange
    this.response.setStatus(OK.value());

    // Act & Assert
    this.testAssert1.assertStatus(OK);
  }

  @Test
  void GIVEN_unexpected_HttpStatus_WHEN_assertStatus_THEN_assert_is_false() {
    // Arrange
    this.response.setStatus(NO_CONTENT.value());

    // Act & Assert
    assertThrows(AssertionError.class, () -> this.testAssert1.assertStatus(OK));
  }

  @Test
  void GIVEN_expected_status_WHEN_assertStatus_THEN_assert_is_true() {
    // Arrange
    this.response.setStatus(OK.value());

    // Act & Assert
    this.testAssert1.assertStatus(200);
  }

  @Test
  void GIVEN_unexpected_status_WHEN_assertStatus_THEN_assert_is_false() {
    // Arrange
    this.response.setStatus(NO_CONTENT.value());

    // Act & Assert
    assertThrows(AssertionError.class, () -> this.testAssert1.assertStatus(200));
  }

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
    this.response.setStatus(1);

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

  @Test
  void GIVEN_400_499_WHEN_assertStatusIsClientError_THEN_assert_is_true() {
    // Arrange
    for (var statusCode = 400; statusCode <= 499; statusCode++) {
      this.response.setStatus(statusCode);

      // Act & Assert
      this.testAssert1.assertStatusIsClientError();
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

  @Test
  void GIVEN_500_599_WHEN_assertStatusIsServerError_THEN_assert_is_true() {
    // Arrange
    for (var statusCode = 500; statusCode <= 599; statusCode++) {
      this.response.setStatus(statusCode);

      // Act & Assert
      this.testAssert1.assertStatusIsServerError();
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

  @Test
  void GIVEN_300_399_WHEN_assertStatusIsRedirect_THEN_assert_is_true() {
    // Arrange
    for (var statusCode = 300; statusCode <= 399; statusCode++) {
      this.response.setStatus(statusCode);

      // Act & Assert
      this.testAssert1.assertStatusIsRedirect();
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

  @Test
  void GIVEN_300_399_WHEN_assertStatusInRange_THEN_assert_is_true() {
    // Arrange
    for (var statusCode = 100; statusCode <= 200; statusCode++) {
      this.response.setStatus(statusCode);

      // Act & Assert
      this.testAssert1.assertStatusInRange(100, 200);
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

  @Test
  void GIVEN_assert2_WHEN_assertContent_THEN_return_expected_class() {
    // Arrange
    this.response.setHeader(TEST_HEAD_KEY_1, TEST_HEAD_VALUE_1);

    // Act
    var assertContent = this.testAssert2.assertContent();

    // Assert
    assertThat(assertContent.getClass(), is(TestAssertContentImpl.class));
  }

  @Test
  void GIVEN_assert2_WHEN_assertHead_THEN_return_expected_class() {
    // Arrange
    this.response.setHeader(TEST_HEAD_KEY_1, TEST_HEAD_VALUE_1);

    // Act
    var assertHead = this.testAssert2.assertHead();

    // Assert
    assertThat(assertHead.getClass(), is(TestAssertHeadImpl.class));
  }

  @Test
  void GIVEN_assert2_WHEN_assertCustom_THEN_return_expected_class() {
    // Arrange
    this.response.setHeader(TEST_HEAD_KEY_1, TEST_HEAD_VALUE_1);

    // Act
    var assertCustom = this.testAssert2.assertCustom();

    // Assert
    assertThat(assertCustom.getClass(), is(TestAssertCustomImpl.class));
  }
}
