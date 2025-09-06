package io.github.co_mmer.aaamockmvc.ej.test.web.internal.answer;

import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.utils.StringUtils.EMPTY;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testmock.MockTestGenericMapper.mockParseListWithClass;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testmock.MockTestGenericMapper.mockParseMapWithClass;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testmock.MockTestGenericMapper.mockParseSetWithClass;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testmock.MockTestGenericMapper.mockParseWithClass;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.A1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_A1_JSON;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_BOOLEAN;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_BOOLEAN_JSON;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_DOUBLE;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_DOUBLE_JSON;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_FLOAT;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_FLOAT_JSON;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_INTEGER;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_INTEGER_JSON;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_LIST_A1_A2;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_LIST_A1_A2_JSON;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_MAP_A1_A2;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_MAP_A1_A2_JSON;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_SET_A1_A2;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_SET_A1_A2_JSON;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import io.github.co_mmer.aaamockmvc.ej.test.web.answer.TestAnswer;
import io.github.co_mmer.aaamockmvc.ej.test.web.answer.exception.TestAnswerRuntimeException;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.TestAssertBase;
import io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestContext;
import io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObjectSimple;
import lombok.SneakyThrows;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class TestAnswerImplTest extends TestAssertBase {

  private TestAnswer testAnswer;

  @BeforeEach
  @SneakyThrows
  void setUp() {
    var context = TestContext.mockContext();
    this.useContext(context);
    this.testAnswer = new TestAnswerImpl(context);
  }

  @Nested
  class asInteger {


    @Test
    void GIVEN_1_JSON_WHEN_asInteger_THEN_return_1() {
      // Arrange
      useActResult(TEST_INTEGER_JSON);

      // Act
      var result = testAnswer.asInteger();

      // Assert
      assertThat(result, is(TEST_INTEGER));
    }

    @Test
    @SuppressWarnings("all")
    void GIVEN_empty_WHEN_asInteger_THEN_return_null() {
      // Arrange
      useActResult(EMPTY);

      // Act
      var result = testAnswer.asInteger();

      // Assert
      assertThat(result, is(nullValue()));
    }

    @Test
    void GIVEN_throws_WHEN_asInteger_THEN_throw_TestAnswerRuntimeException() {
      // Arrange
      var mockTestGenericMapper = mockParseWithClass();

      // Act && Assert
      assertThrows(TestAnswerRuntimeException.class, () -> testAnswer.asInteger());
      mockTestGenericMapper.close();
    }
  }

  @Nested
  class asFloat {


    @Test
    void GIVEN_1_JSON_WHEN_asFloat_THEN_return_1() {
      // Arrange
      useActResult(TEST_FLOAT_JSON);

      // Act
      var result = testAnswer.asFloat();

      // Assert
      assertThat(result, is(TEST_FLOAT));
    }

    @Test
    @SuppressWarnings("all")
    void GIVEN_empty_WHEN_asFloat_THEN_return_null() {
      // Arrange
      useActResult(EMPTY);

      // Act
      var result = testAnswer.asFloat();

      // Assert
      assertThat(result, is(nullValue()));
    }

    @Test
    void GIVEN_throws_WHEN_asFloat_THEN_throw_TestAnswerRuntimeException() {
      // Arrange
      var mockTestGenericMapper = mockParseWithClass();

      // Act && Assert
      assertThrows(TestAnswerRuntimeException.class, () -> testAnswer.asFloat());
      mockTestGenericMapper.close();
    }
  }

  @Nested
  class asDouble {


    @Test
    void GIVEN_1_JSON_WHEN_asDouble_THEN_return_1() {
      // Arrange
      useActResult(TEST_DOUBLE_JSON);

      // Act
      var result = testAnswer.asDouble();

      // Assert
      assertThat(result, is(TEST_DOUBLE));
    }

    @Test
    @SuppressWarnings("all")
    void GIVEN_empty_WHEN_asDouble_THEN_return_null() {
      // Arrange
      useActResult(EMPTY);

      // Act
      var result = testAnswer.asDouble();

      // Assert
      assertThat(result, is(nullValue()));
    }

    @Test
    void GIVEN_throws_WHEN_asDouble_THEN_throw_TestAnswerRuntimeException() {
      // Arrange
      var mockTestGenericMapper = mockParseWithClass();

      // Act && Assert
      assertThrows(TestAnswerRuntimeException.class, () -> testAnswer.asDouble());
      mockTestGenericMapper.close();
    }
  }

  @Nested
  class asBoolean {


    @Test
    void GIVEN_True_JSON_WHEN_asBoolean_THEN_return_1() {
      // Arrange
      useActResult(TEST_BOOLEAN_JSON);

      // Act
      var result = testAnswer.asBoolean();

      // Assert
      assertThat(result, is(TEST_BOOLEAN));
    }

    @Test
    @SuppressWarnings("all")
    void GIVEN_empty_WHEN_asBoolean_THEN_return_null() {
      // Arrange
      useActResult(EMPTY);

      // Act
      var result = testAnswer.asBoolean();

      // Assert
      assertThat(result, is(nullValue()));
    }

    @Test
    void GIVEN_throws_WHEN_asBoolean_THEN_throw_TestAnswerRuntimeException() {
      // Arrange
      var mockTestGenericMapper = mockParseWithClass();

      // Act && Assert
      assertThrows(TestAnswerRuntimeException.class, () -> testAnswer.asBoolean());
      mockTestGenericMapper.close();
    }
  }

  @Nested
  class asString {

    @Test
    void GIVEN_A1JSON_WHEN_asString_THEN_return_A1JSON() {
      // Arrange
      useActResult(TEST_A1_JSON);

      // Act
      var result = testAnswer.asString();

      // Assert
      assertThat(result, is(TEST_A1_JSON));
    }

    @Test
    @SuppressWarnings("all")
    void GIVEN_empty_WHEN_asString_THEN_return_empty() {
      // Arrange
      useActResult(EMPTY);

      // Act
      var result = testAnswer.asString();

      // Assert
      assertThat(result, is(EMPTY));
    }
  }

  @Nested
  class asByte {

    @Test
    void GIVEN_A1JSON_WHEN_asByte_THEN_return_A1JSON_bytes() {
      // Arrange
      useActResult(TEST_A1_JSON.getBytes());

      // Act
      var result = testAnswer.asByte();

      // Assert
      MatcherAssert.assertThat(result, is(TEST_A1_JSON.getBytes()));
    }
  }

  @Nested
  class asObject {

    @Test
    @SuppressWarnings("ConstantConditions")
    void GIVEN_null_WHEN_asObject_THEN_throw_NullPointerException() {
      // Act & Arrange
      assertThrows(NullPointerException.class, () -> testAnswer.asObject(null));
    }

    @Test
    void GIVEN_A1_WHEN_asObject_THEN_return_A1() {
      // Arrange
      useActResult(TEST_A1_JSON);

      // Act
      var result = testAnswer.asObject(TestObjectSimple.class);

      // Assert
      assertThat(result, is(A1));
    }

    @Test
    @SuppressWarnings("all")
    void GIVEN_emptyl_WHEN_asObject_THEN_return_null() {
      // Arrange
      useActResult(EMPTY);

      // Act
      var result = testAnswer.asObject(TestObjectSimple.class);

      // Assert
      assertThat(result, is(nullValue()));
    }

    @Test
    void GIVEN_throws_WHEN_asObject_THEN_throw_TestAnswerRuntimeException() {
      // Arrange
      var mockTestGenericMapper = mockParseWithClass();

      // Act && Assert
      assertThrows(
          TestAnswerRuntimeException.class, () -> testAnswer.asObject(TestObjectSimple.class));
      mockTestGenericMapper.close();
    }
  }

  @Nested
  class asList {

    @Test
    @SuppressWarnings("ConstantConditions")
    void GIVEN_null_WHEN_asList_THEN_throw_NullPointerException() {
      // Act & Arrange
      assertThrows(NullPointerException.class, () -> testAnswer.asList(null));
    }

    @Test
    void GIVEN_A1_A2_WHEN_asList_THEN_return_A1_A2() {
      // Arrange
      useActResult(TEST_LIST_A1_A2_JSON);

      // Act
      var result = testAnswer.asList(TestObjectSimple.class);

      // Assert
      assertThat(result, is(TEST_LIST_A1_A2));
    }

    @Test
    void GIVEN_null_WHEN_asList_THEN_return_null() {
      // Arrange
      useActResult(EMPTY);

      // Act
      var result = testAnswer.asList(TestObjectSimple.class);

      // Assert
      assertThat(result, is(nullValue()));
    }

    @Test
    void GIVEN_throws_WHEN_asList_THEN_throwTestAnswerRuntimeException() {
      // Arrange
      var mockTestGenericMapper = mockParseListWithClass();

      // Act && Assert
      assertThrows(
          TestAnswerRuntimeException.class, () -> testAnswer.asList(TestObjectSimple.class));
      mockTestGenericMapper.close();
    }
  }

  @Nested
  class asSet {

    @Test
    @SuppressWarnings("ConstantConditions")
    void GIVEN_null_WHEN_asSet_THEN_throw_NullPointerException() {
      // Act & Arrange
      assertThrows(NullPointerException.class, () -> testAnswer.asSet(null));
    }

    @Test
    void GIVEN_A1_A2_WHEN_asSet_THEN_return_A1_A2() {
      // Arrange
      useActResult(TEST_SET_A1_A2_JSON);

      // Act
      var result = testAnswer.asSet(TestObjectSimple.class);

      // Assert
      assertThat(result, is(TEST_SET_A1_A2));
    }

    @Test
    void GIVEN_null_WHEN_asSet_THEN_return_null() {
      // Arrange
      useActResult(EMPTY);

      // Act
      var result = testAnswer.asSet(TestObjectSimple.class);

      // Assert
      assertThat(result, is(nullValue()));
    }

    @Test
    void GIVEN_throws_WHEN_asSet_THEN_throwTestAnswerRuntimeException() {
      // Arrange
      var mockTestGenericMapper = mockParseSetWithClass();

      // Act && Assert
      assertThrows(
          TestAnswerRuntimeException.class, () -> testAnswer.asSet(TestObjectSimple.class));
      mockTestGenericMapper.close();
    }
  }

  @Nested
  class asMap {

    @Test
    @SuppressWarnings("ConstantConditions")
    void GIVEN_null_WHEN_asMap_THEN_throw_NullPointerException() {
      // Act & Arrange
      assertThrows(NullPointerException.class, () -> testAnswer.asMap(null, null));
    }

    @Test
    @SneakyThrows
    void GIVEN_A1_A2_JSON_WHEN_asMap_THEN_return_A1_A2() {
      // Arrange
      useActResult(TEST_MAP_A1_A2_JSON);

      // Act
      var result = testAnswer.asMap(Integer.class, TestObjectSimple.class);

      // Assert
      assertThat(result, is(TEST_MAP_A1_A2));
    }

    @Test
    @SneakyThrows
    void GIVEN_null_WHEN_asMap_THEN_return_null() {
      // Arrange
      useActResult(EMPTY);

      // Act
      var result = testAnswer.asMap(Integer.class, TestObjectSimple.class);

      // Assert
      assertThat(result, is(nullValue()));
    }

    @Test
    void GIVEN_throws_WHEN_asMap_THEN_throwTestAnswerRuntimeException() {
      // Arrange
      var mockTestGenericMapper = mockParseMapWithClass();

      // Act && Assert
      assertThrows(
          TestAnswerRuntimeException.class,
          () -> testAnswer.asMap(Integer.class, TestObjectSimple.class));
      mockTestGenericMapper.close();
    }
  }
}
