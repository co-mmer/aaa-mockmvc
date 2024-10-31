package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.head;

import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.TEST_HEAD_KEY_1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.TEST_HEAD_KEY_2;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.TEST_HEAD_VALUE_1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.TEST_HEAD_VALUE_2;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

class TestAssertHeadImplTest {

  private MockHttpServletResponse response;
  private TestAssertHead testAssert;

  @BeforeEach
  void setUp() {
    var actions = mock(ResultActions.class);
    var mvcResult = mock(MvcResult.class);
    this.response = new MockHttpServletResponse();
    when(mvcResult.getResponse()).thenReturn(this.response);
    when(actions.andReturn()).thenReturn(mvcResult);
    this.testAssert = new TestAssertHeadImpl(actions);
  }

  @Test
  @SuppressWarnings("ConstantConditions")
  void GIVEN_null_WHEN_call_constructor_THEN_throw_NullPointerException() {
    assertThrows(NullPointerException.class, () -> new TestAssertHeadImpl(null));
  }

  @Test
  @SuppressWarnings("ConstantConditions")
  void GIVEN_null_WHEN_assertHeadContains_THEN_throw_NullPointerException() {
    assertThrows(NullPointerException.class, () -> this.testAssert.assertHeadContains(null));
  }

  @Test
  void GIVEN_existing_header_WHEN_assertHeadContains_THEN_assert_is_true() {
    // Arrange
    this.response.setHeader(TEST_HEAD_KEY_1, TEST_HEAD_VALUE_1);

    // Act & Assert
    this.testAssert.assertHeadContains(TEST_HEAD_KEY_1);
  }

  @Test
  void GIVEN_not_existing_header_WHEN_assertHeadContains_THEN_assert_is_false() {
    // Arrange
    this.response.setHeader(TEST_HEAD_KEY_1, TEST_HEAD_VALUE_1);

    // Act & Assert
    assertThrows(AssertionError.class, () -> this.testAssert.assertHeadContains(TEST_HEAD_KEY_2));
  }

  @Test
  void GIVEN_not_existing_header_WHEN_assertHeadNotContains_THEN_assert_is_true() {
    // Arrange
    this.response.setHeader(TEST_HEAD_KEY_1, TEST_HEAD_VALUE_1);

    // Act & Assert
    this.testAssert.assertHeadNotContains(TEST_HEAD_KEY_2);
  }

  @Test
  void GIVEN_existing_header_WHEN_assertHeadNotContains_THEN_assert_is_false() {
    // Arrange
    this.response.setHeader(TEST_HEAD_KEY_1, TEST_HEAD_VALUE_1);

    // Act & Assert
    assertThrows(
        AssertionError.class, () -> this.testAssert.assertHeadNotContains(TEST_HEAD_KEY_1));
  }

  @Test
  @SuppressWarnings("ConstantConditions")
  void GIVEN_null_WHEN_assertHeadEquals_THEN_throw_NullPointerException() {
    assertThrows(NullPointerException.class,
        () -> this.testAssert.assertHeadEquals(null, TEST_HEAD_VALUE_1));
  }

  @Test
  void GIVEN_existing_header_WHEN_assertHeadEquals_THEN_assert_is_true() {
    // Arrange
    this.response.setHeader(TEST_HEAD_KEY_1, TEST_HEAD_VALUE_1);

    // Act & Assert
    this.testAssert.assertHeadEquals(TEST_HEAD_KEY_1, TEST_HEAD_VALUE_1);
  }

  @Test
  void GIVEN_not_expected_header_value_WHEN_assertHeadEquals_THEN_assert_is_false() {
    // Arrange
    this.response.setHeader(TEST_HEAD_KEY_1, TEST_HEAD_VALUE_1);

    // Assert
    assertThrows(
        AssertionError.class,

        // Act
        () -> this.testAssert.assertHeadEquals(TEST_HEAD_KEY_1, TEST_HEAD_VALUE_2));
  }

  @Test
  void GIVEN_not_expected_header_key_WHEN_assertHeadEquals_THEN_assert_is_false() {
    // Arrange
    this.response.setHeader(TEST_HEAD_KEY_1, TEST_HEAD_VALUE_1);

    // Assert
    assertThrows(
        AssertionError.class,

        // Act
        () -> this.testAssert.assertHeadEquals(TEST_HEAD_KEY_2, TEST_HEAD_VALUE_1));
  }
}
