package ej.test.aaamockmvc.request.asserts;

import static ej.test.aaamockmvc.testdata.testutil.TestValue.TEST_HEAD_KEY_1;
import static ej.test.aaamockmvc.testdata.testutil.TestValue.TEST_HEAD_VALUE_1;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import ej.test.aaamockmvc.request.asserts.content.TestAssertContentImpl;
import ej.test.aaamockmvc.request.asserts.head.TestAssertHeadImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;

class TestAssertImplTest {

  private ResultActions actions;
  private MockHttpServletResponse response;
  private TestAssertImpl testAssert;

  @BeforeEach
  void setUp() {
    this.actions = mock(ResultActions.class);
    var mvcResult = mock(MvcResult.class);
    this.response = new MockHttpServletResponse();

    when(mvcResult.getResponse()).thenReturn(this.response);
    when(actions.andReturn()).thenReturn(mvcResult);

    this.testAssert = new TestAssertImpl(actions, new ObjectMapper());
  }

  @Test
  void GIVEN_expected_HttpStatus_WHEN_assertStatus_THEN_assert_is_true() {
    // Arrange
    this.response.setStatus(HttpStatus.OK.value());

    // Act & Assert
    this.testAssert.assertStatus(HttpStatus.OK);
  }

  @Test
  void GIVEN_unexpected_HttpStatus_WHEN_assertStatus_THEN_assert_is_false() {
    // Arrange
    this.response.setStatus(HttpStatus.NO_CONTENT.value());

    // Act & Assert
    assertThrows(AssertionError.class, () -> this.testAssert.assertStatus(HttpStatus.OK));
  }

  @Test
  void GIVEN_expected_status_WHEN_assertStatus_THEN_assert_is_true() {
    // Arrange
    this.response.setStatus(HttpStatus.OK.value());

    // Act & Assert
    this.testAssert.assertStatus(200);
  }

  @Test
  void GIVEN_unexpected_status_WHEN_assertStatus_THEN_assert_is_false() {
    // Arrange
    this.response.setStatus(HttpStatus.NO_CONTENT.value());

    // Act & Assert
    assertThrows(AssertionError.class, () -> this.testAssert.assertStatus(200));
  }

  @Test
  void GIVEN_exception_WHEN_assertMatcher_THEN_throw_TestAssertException() throws Exception {
    // Arrange
    var mockStatusMatcher = mock(ResultMatcher.class);
    doThrow(new RuntimeException()).when(this.actions).andExpect(mockStatusMatcher);

    // Act
    assertThrows(
        AssertionFailedError.class, () -> this.testAssert.assertByResultMatcher(mockStatusMatcher));

    // Assert
    verify(this.actions).andExpect(mockStatusMatcher);
  }

  @Test
  void GIVEN_matcher_WHEN_assertByResultMatcher_THEN_andExpect_is_called() throws Exception {
    // Arrange
    var mockStatusMatcher = mock(ResultMatcher.class);

    // Act
    this.testAssert.assertByResultMatcher(mockStatusMatcher);

    // Assert
    verify(this.actions).andExpect(mockStatusMatcher);
  }

  @Test
  void WHEN_assertHead_THEN_return_expected_class() {
    // Arrange
    this.response.setHeader(TEST_HEAD_KEY_1, TEST_HEAD_VALUE_1);

    // Act
    var assertHead = this.testAssert.assertHead();

    // Assert
    assertThat(assertHead.getClass(), is(TestAssertHeadImpl.class));
  }

  @Test
  void WHEN_assertContent_THEN_return_expected_class() {
    // Arrange
    this.response.setHeader(TEST_HEAD_KEY_1, TEST_HEAD_VALUE_1);

    // Act
    var assertContent = this.testAssert.assertContent();

    // Assert
    assertThat(assertContent.getClass(), is(TestAssertContentImpl.class));
  }
}
