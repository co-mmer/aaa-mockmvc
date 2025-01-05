package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.content;

import static io.github.co_mmer.aaamockmvc.ej.test.web.asserts.content.TestArrangeNormalizer.normalizeObject;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.A1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.A2;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_A1_JSON;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.TestAssertBase;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.head.TestAssertHeadImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.mapper.TestGenericMapper;
import io.github.co_mmer.aaamockmvc.ej.test.web.mapper.exception.TestGenericMapperException;
import io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject1;
import java.util.stream.Stream;
import lombok.SneakyThrows;
import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.opentest4j.AssertionFailedError;
import org.springframework.test.web.servlet.ResultActions;

class TestAssertContentImplTest extends TestAssertBase {

  private static final String EXPECTED_CONTENT = "expected content";
  private static final String ACTUAL_CONTENT = "actual content";
  private TestAssertContentImpl testAssertContent;

  @BeforeEach
  void setUp() {
    initMockServer();
    this.testAssertContent = new TestAssertContentImpl(this.actions, new ObjectMapper());
  }

  @Nested
  class constructor {

    @ParameterizedTest()
    @MethodSource("provideNullParameters")
    @SuppressWarnings("ConstantConditions")
    void GIVEN_provideNullParameters_WHEN_call_constructor_THEN_throw_NullPointerException(
        ResultActions actions, ObjectMapper objectMapper) {

      assertThrows(
          NullPointerException.class, () -> new TestAssertContentImpl(actions, objectMapper));
    }

    private static Stream<Arguments> provideNullParameters() {
      return Stream.of(
          Arguments.of(null, new ObjectMapper()),
          Arguments.of(mock(ResultActions.class), null),
          Arguments.of(null, null));
    }
  }

  @Nested
  class assertContentNotEmpty {

    @Test
    @SneakyThrows
    void GIVEN_expected_WHEN_assertContentNotEmpty_THEN_assert_true() {
      // Arrange
      useServerWithResponse(EXPECTED_CONTENT);

      // Act & Assert
      testAssertContent.assertContentNotEmpty();
    }

    @Test
    @SneakyThrows
    void GIVEN_unexpected_WHEN_assertContentNotEmpty_THEN_assert_false() {
      // Arrange
      useServerWithResponse(Strings.EMPTY);

      // Act & Assert
      assertThrows(AssertionError.class, testAssertContent::assertContentNotEmpty);
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertContentNotEmpty_THEN_assert_false() {
      // Arrange
      useServerWithStringException();
      var testAssertContentException = new TestAssertContentImpl(actions, new ObjectMapper());

      // Act & Assert
      assertThrows(AssertionError.class, testAssertContentException::assertContentNotEmpty);
    }
  }

  @Nested
  class assertContentEmpty {

    @Test
    @SneakyThrows
    void GIVEN_expected_WHEN_assertContentEmpty_THEN_assert_true() {
      // Arrange
      useServerWithResponse(Strings.EMPTY);

      // Act & Assert
      testAssertContent.assertContentEmpty();
    }

    @Test
    @SneakyThrows
    void GIVEN_unexpected_WHEN_assertContentEmpty_THEN_return_assert_false() {
      // Arrange
      useServerWithResponse(ACTUAL_CONTENT);

      // Act & Assert
      assertThrows(AssertionError.class, testAssertContent::assertContentEmpty);
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertContentEmpty_THEN_assert_false() {
      // Arrange
      useServerWithStringException();
      var testAssertContentException = new TestAssertContentImpl(actions, new ObjectMapper());

      // Act & Assert
      assertThrows(AssertionError.class, testAssertContentException::assertContentEmpty);
    }
  }

  @Nested
  class assertContentEquals {

    @Test
    @SneakyThrows
    void GIVEN_expected_WHEN_assertContentEquals_THEN_assert_true() {
      // Arrange
      useServerWithResponse(EXPECTED_CONTENT);

      // Act & Assert
      testAssertContent.assertContentEquals(EXPECTED_CONTENT);
    }

    @Test
    @SneakyThrows
    void GIVEN_unexpected_WHEN_assertContentEquals_THEN_assert_false() {
      // Arrange
      useServerWithResponse(ACTUAL_CONTENT);

      // Act & Assert
      assertThrows(
          AssertionError.class, () -> testAssertContent.assertContentEquals(EXPECTED_CONTENT));
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertContentEquals_THEN_assert_false() {
      // Arrange
      useServerWithStringException();
      var testAssertContentException = new TestAssertContentImpl(actions, new ObjectMapper());

      // Act & Assert
      assertThrows(
          AssertionFailedError.class,
          () -> testAssertContentException.assertContentEquals(EXPECTED_CONTENT));
    }

    @Test
    @SneakyThrows
    void GIVEN_string_WHEN_assertContentEquals_THEN_normalizeObject_is_called() {
      // Arrange
      var mockTestArrangeNormalizer = mockStatic(TestArrangeNormalizer.class);
      useServerWithResponse(ACTUAL_CONTENT);

      // Act
      testAssertContent.assertContentEquals(EXPECTED_CONTENT);

      // Assert
      mockTestArrangeNormalizer.verify(() -> normalizeObject(any()), times(2));
      mockTestArrangeNormalizer.close();
    }

    @Test
    @SneakyThrows
    void GIVEN_expected_object_WHEN_assertContentEquals_THEN_assert_is_true() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      testAssertContent.assertContentEquals(TestObject1.class, A1);
    }

    @Test
    @SneakyThrows
    void GIVEN_unexpected_object_WHEN_assertContentEquals_THEN_assert_is_false() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      assertThrows(
          AssertionError.class, () -> testAssertContent.assertContentEquals(TestObject1.class, A2));
    }

    @Test
    void GIVEN_exception_object_WHEN_assertContentEquals_THEN_assert_is_false() {
      // Arrange
      var mockTestGenericMapper = mockStatic(TestGenericMapper.class);
      mockTestGenericMapper
          .when(() -> TestGenericMapper.mapTo(any(), any(), any()))
          .thenThrow(new TestGenericMapperException(new Throwable("error")));

      // Act & Assert
      assertThrows(
          AssertionFailedError.class,
          () -> testAssertContent.assertContentEquals(TestObject1.class, A1));

      mockTestGenericMapper.close();
    }

    @Test
    @SneakyThrows
    void GIVEN_object_WHEN_assertContentEquals_THEN_normalizeObject_is_called() {
      // Arrange
      var mockTestArrangeNormalizer = mockStatic(TestArrangeNormalizer.class);
      useServerWithResponse(TEST_A1_JSON);

      // Act
      testAssertContent.assertContentEquals(TestObject1.class, A1);

      // Assert
      mockTestArrangeNormalizer.verify(() -> normalizeObject(any()), times(2));
      mockTestArrangeNormalizer.close();
    }
  }

  @Nested
  class assertContentLength {

    @Test
    @SneakyThrows
    void GIVEN_expected_WHEN_assertContentLength_THEN_assert_true() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      testAssertContent.assertContentLength(TEST_A1_JSON.length());
    }

    @Test
    @SneakyThrows
    void GIVEN_unexpected_WHEN_assertContentLength_THEN_assert_false() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      assertThrows(AssertionError.class, () -> testAssertContent.assertContentLength(10));
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertContentLength_THEN_assert_false() {
      // Arrange
      useServerWithStringException();
      var testAssertContentException = new TestAssertContentImpl(actions, new ObjectMapper());

      // Act & Assert
      assertThrows(
          AssertionFailedError.class, () -> testAssertContentException.assertContentLength(50));
    }

    @Test
    @SneakyThrows
    void GIVEN_string_WHEN_assertContentEquals_THEN_normalizeObject_is_called() {
      // Arrange
      var mockTestArrangeNormalizer = mockStatic(TestArrangeNormalizer.class);
      useServerWithResponse(ACTUAL_CONTENT);

      // Act
      testAssertContent.assertContentEquals(EXPECTED_CONTENT);

      // Assert
      mockTestArrangeNormalizer.verify(() -> normalizeObject(any()), times(2));
      mockTestArrangeNormalizer.close();
    }

    @Test
    @SneakyThrows
    void GIVEN_expected_object_WHEN_assertContentEquals_THEN_assert_is_true() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      testAssertContent.assertContentEquals(TestObject1.class, A1);
    }

    @Test
    @SneakyThrows
    void GIVEN_unexpected_object_WHEN_assertContentEquals_THEN_assert_is_false() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      assertThrows(
          AssertionError.class, () -> testAssertContent.assertContentEquals(TestObject1.class, A2));
    }

    @Test
    void GIVEN_exception_object_WHEN_assertContentEquals_THEN_assert_is_false() {
      // Arrange
      var mockTestGenericMapper = mockStatic(TestGenericMapper.class);
      mockTestGenericMapper
          .when(() -> TestGenericMapper.mapTo(any(), any(), any()))
          .thenThrow(new TestGenericMapperException(new Throwable("error")));

      // Act & Assert
      assertThrows(
          AssertionFailedError.class,
          () -> testAssertContent.assertContentEquals(TestObject1.class, A1));

      mockTestGenericMapper.close();
    }

    @Test
    @SneakyThrows
    void GIVEN_object_WHEN_assertContentEquals_THEN_normalizeObject_is_called() {
      // Arrange
      var mockTestArrangeNormalizer = mockStatic(TestArrangeNormalizer.class);
      useServerWithResponse(TEST_A1_JSON);

      // Act
      testAssertContent.assertContentEquals(TestObject1.class, A1);

      // Assert
      mockTestArrangeNormalizer.verify(() -> normalizeObject(any()), times(2));
      mockTestArrangeNormalizer.close();
    }
  }

  @Nested
  class nextStep {

    @Test
    void WHEN_assertHead_THEN_return_expected_class() {
      // Arrange
      useHeader();

      // Act
      var assertHead = testAssertContent.assertHead();

      // Assert
      assertThat(assertHead.getClass(), is(TestAssertHeadImpl.class));
    }
  }

  @Nested
  class combinationNotEmpty {

    @Test
    @SneakyThrows
    void notEmpty_equals_string() {
      // Arrange
      useServerWithResponse(EXPECTED_CONTENT);

      // Act & Assert
      testAssertContent.assertContentNotEmpty().assertContentEquals(EXPECTED_CONTENT);
    }

    @Test
    @SneakyThrows
    void notEmpty_equals_object() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      testAssertContent.assertContentNotEmpty().assertContentEquals(TestObject1.class, A1);
    }

    @Test
    @SneakyThrows
    void notEmpty_head() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      testAssertContent.assertContentNotEmpty().assertHead();
    }
  }

  @Nested
  class combinationEmpty {

    @Test
    @SneakyThrows
    void empty_head() {
      // Arrange
      useServerWithResponse(Strings.EMPTY);

      // Act & Assert
      testAssertContent.assertContentEmpty().assertHead();
    }
  }

  @Nested
  class combinationLength {

    @Test
    @SneakyThrows
    void length_equals_string() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      testAssertContent
          .assertContentLength(TEST_A1_JSON.length())
          .assertContentEquals(TEST_A1_JSON);
    }

    @Test
    @SneakyThrows
    void length_equals_object() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      testAssertContent
          .assertContentLength(TEST_A1_JSON.length())
          .assertContentEquals(TestObject1.class, A1);
    }

    @Test
    @SneakyThrows
    void length_head() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      testAssertContent.assertContentLength(TEST_A1_JSON.length()).assertHead();
    }
  }

  @Nested
  class combinationEquals {

    @Test
    @SneakyThrows
    void equals_string_head() {
      // Arrange
      useServerWithResponse(EXPECTED_CONTENT);

      // Act & Assert
      testAssertContent.assertContentEquals(EXPECTED_CONTENT).assertHead();
    }

    @Test
    @SneakyThrows
    void equals_object_head() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      testAssertContent.assertContentEquals(TestObject1.class, A1).assertHead();
    }
  }
}
