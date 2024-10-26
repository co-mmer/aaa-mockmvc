package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.custom;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;

class TestAssertCustomImplTest {

  private ResultActions actions;
  private TestAssertCustom testAssert;

  @BeforeEach
  void setUp() {
    this.actions = mock(ResultActions.class);
    var mvcResult = mock(MvcResult.class);
    var response = new MockHttpServletResponse();

    when(mvcResult.getResponse()).thenReturn(response);
    when(this.actions.andReturn()).thenReturn(mvcResult);

    this.testAssert = new TestAssertCustomImpl(this.actions);
  }

  @SuppressWarnings("ConstantConditions")
  @Test
  void GIVEN_null_WHEN_call_constructor_THEN_throw_NullPointerException() {
    //  Assert
    assertThrows(
        NullPointerException.class,

        // Act
        () -> new TestAssertCustomImpl(null));
  }

  @Test
  void GIVEN_exception_WHEN_assertCustomResultMatcher_THEN_throw_exception() throws Exception {

    // Arrange
    var mockStatusMatcher = mock(ResultMatcher.class);
    doThrow(new RuntimeException()).when(this.actions).andExpect(mockStatusMatcher);

    // Assert
    assertThrows(
        AssertionFailedError.class,

        // Act
        () -> this.testAssert.assertCustomResultMatcher(mockStatusMatcher));
  }

  @SuppressWarnings("ConstantConditions")
  @Test
  void GIVEN_null_WHEN_assertCustomResultMatcher_THEN_throw_NullPointerException() {
    //  Assert
    assertThrows(
        NullPointerException.class,

        // Act
        () -> this.testAssert.assertCustomResultMatcher(null));
  }

  @Test
  void GIVEN_matcher_WHEN_assertCustomResultMatcher_THEN_andExpect_is_called() throws Exception {
    // Arrange
    var mockStatusMatcher = mock(ResultMatcher.class);

    // Act
    this.testAssert.assertCustomResultMatcher(mockStatusMatcher);

    // Assert
    verify(this.actions).andExpect(mockStatusMatcher);
  }
}
