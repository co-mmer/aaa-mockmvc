package ej.test.aaamockmvc.request.asserts;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ej.test.aaamockmvc.request.act.exception.TestAssertException;
import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;

class TestAssertImplTest {

  private static final String EXPECTED_CONTENT = "expected content";
  private static final String ACTUAL_CONTENT = "actual content";
  private ResultActions actions;
  private MockHttpServletResponse response;
  private TestAssertImpl testAssert;

  @BeforeEach
  void setUp() {
    this.actions = mock(ResultActions.class);
    var mvcResult = mock(MvcResult.class);
    this.response = new MockHttpServletResponse();

    when(mvcResult.getResponse()).thenReturn(this.response);
    when(this.actions.andReturn()).thenReturn(mvcResult);

    this.testAssert = new TestAssertImpl(this.actions);
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
  void GIVEN_expected_WHEN_assertStringContentIsNotEmpty_THEN_assert_true() throws Exception {

    // Arrange
    this.response.getWriter().write(EXPECTED_CONTENT);

    // Act & Assert
    this.testAssert.assertStringContentIsNotEmpty();
  }

  @Test
  void GIVEN_unexpected_WHEN_assertStringContentIsNotEmpty_THEN_assert_false() throws Exception {

    // Arrange
    this.response.getWriter().write(Strings.EMPTY);

    // Act & Assert
    assertThrows(AssertionError.class, this.testAssert::assertStringContentIsNotEmpty);
  }

  @Test
  void GIVEN_expected_WHEN_assertStringContentIsEmpty_THEN_assert_true() throws Exception {
    // Arrange
    this.response.getWriter().write(Strings.EMPTY);

    // Act & Assert
    this.testAssert.assertStringContentIsEmpty();
  }

  @Test
  void GIVEN_unexpected_WHEN_assertStringContentIsEmpty_THEN_return_assert_false()
      throws Exception {

    // Arrange
    this.response.getWriter().write(ACTUAL_CONTENT);

    // Act & Assert
    assertThrows(AssertionError.class, this.testAssert::assertStringContentIsEmpty);
  }

  @Test
  void GIVEN_expected_WHEN_assertStringContent_THEN_assert_true() throws Exception {
    // Arrange
    this.response.getWriter().write(EXPECTED_CONTENT);

    // Act & Assert
    this.testAssert.assertStringContent(EXPECTED_CONTENT);
  }

  @Test
  void GIVEN_unexpected_WHEN_assertStringContent_THEN_assert_false() throws Exception {
    // Arrange
    this.response.getWriter().write(ACTUAL_CONTENT);

    // Act & Assert
    assertThrows(AssertionError.class, () -> this.testAssert.assertStringContent(EXPECTED_CONTENT));
  }

  @Test
  void GIVEN_exception_WHEN_assertStringContent_THEN_assert_false() throws Exception {
    // Arrange
    var mockMvcResult = mock(MvcResult.class);
    var mockResponse = mock(MockHttpServletResponse.class);
    when(mockResponse.getContentAsString()).thenThrow(new RuntimeException());
    when(mockMvcResult.getResponse()).thenReturn(mockResponse);

    when(this.actions.andReturn()).thenReturn(mockMvcResult);
    var testAssert = new TestAssertImpl(this.actions);

    // Act & Assert
    assertThrows(TestAssertException.class, () -> testAssert.assertStringContent(EXPECTED_CONTENT));
  }

  @Test
  void GIVEN_expected_WHEN_assertByteContentIsNotEmpty_THEN_assert_true() throws Exception {

    // Arrange
    this.response.getWriter().write(ACTUAL_CONTENT);

    // Act & Assert
    this.testAssert.assertByteContentIsNotEmpty();
  }

  @Test
  void GIVEN_unexpected_WHEN_assertByteContentIsNotEmpty_THEN_assert_false() throws Exception {

    // Arrange
    this.response.getWriter().write(Strings.EMPTY);

    // Act & Assert
    assertThrows(AssertionError.class, this.testAssert::assertByteContentIsNotEmpty);
  }

  @Test
  void GIVEN_expected_WHEN_assertByteContentIsEmpty_THEN_assert_true() throws Exception {
    // Arrange
    this.response.getWriter().write(Strings.EMPTY);

    // Act & Assert
    this.testAssert.assertByteContentIsEmpty();
  }

  @Test
  void GIVEN_unexpected_WHEN_assertByteContentIsEmpty_THEN_return_assert_false() throws Exception {

    // Arrange
    this.response.getWriter().write(ACTUAL_CONTENT);

    // Act & Assert
    assertThrows(AssertionError.class, this.testAssert::assertByteContentIsEmpty);
  }

  @Test
  void GIVEN_expected_WHEN_assertByteContent_THEN_assert_true() throws Exception {
    // Arrange
    this.response.getWriter().write(EXPECTED_CONTENT);

    // Act & Assert
    this.testAssert.assertByteContent(EXPECTED_CONTENT.getBytes());
  }

  @Test
  void GIVEN_unexpected_WHEN_assertByteContent_THEN_assert_false() throws Exception {
    // Arrange
    this.response.getWriter().write(ACTUAL_CONTENT);

    // Act & Assert
    assertThrows(AssertionError.class, () -> this.testAssert.assertByteContent(new byte[1]));
  }

  @Test
  void GIVEN_exception_WHEN_assertByteContent_THEN_assert_false() {
    // Arrange
    var mockMvcResult = mock(MvcResult.class);
    var mockResponse = mock(MockHttpServletResponse.class);
    when(mockResponse.getContentAsByteArray()).thenThrow(new RuntimeException());
    when(mockMvcResult.getResponse()).thenReturn(mockResponse);

    when(this.actions.andReturn()).thenReturn(mockMvcResult);
    var testAssert = new TestAssertImpl(this.actions);

    // Act & Assert
    assertThrows(TestAssertException.class, () -> testAssert.assertByteContent(new byte[1]));
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
  void GIVEN_exception_WHEN_assertMatcher_THEN_throw_TestAssertException() throws Exception {
    // Arrange
    var mockStatusMatcher = mock(ResultMatcher.class);
    doThrow(new RuntimeException()).when(this.actions).andExpect(mockStatusMatcher);

    // Act
    assertThrows(
        TestAssertException.class, () -> this.testAssert.assertByResultMatcher(mockStatusMatcher));

    // Assert
    verify(this.actions).andExpect(mockStatusMatcher);
  }
}
