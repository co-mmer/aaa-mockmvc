package io.github.co_mmer.aaamockmvc.ej.test.web.internal.answer;

import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.utils.StringUtils.EMPTY;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testmock.MockTestGenericMapper.mockParseCollectionWithClass;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testmock.MockTestGenericMapper.mockParseListWithClass;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testmock.MockTestGenericMapper.mockParseMapWithClass;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testmock.MockTestGenericMapper.mockParseSetWithClass;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testmock.MockTestGenericMapper.mockParseWithClass;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.A1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_A1_JSON;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_BIGDECIMAL;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_BIGINTEGER;
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
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_LONG;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_MAP_A1_A2;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_MAP_A1_A2_JSON;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_SET_A1_A2;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_SET_A1_A2_JSON;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import io.github.co_mmer.aaamockmvc.ej.test.web.answer.TestAnswer;
import io.github.co_mmer.aaamockmvc.ej.test.web.answer.exception.TestAnswerException;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.TestAssertBase;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.mapper.exception.TestGenericMapperException;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestAAAContext;
import io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestContext;
import io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObjectSimple;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class TestAnswerImplTest extends TestAssertBase {

  private TestAAAContext context;
  private TestAnswer testAnswer;

  @BeforeEach
  @SneakyThrows
  void setUp() {
    this.context = TestContext.mockContext();
    this.useContext(context);
    this.testAnswer = new TestAnswerImpl(context);
  }

  @Nested
  class asNumber {

    @Test
    void GIVEN_1_JSON_WHEN_asNumber_THEN_return_1() {
      // Arrange
      useActResult(TEST_INTEGER_JSON);

      // Act
      var result = testAnswer.asNumber();

      // Assert
      assertThat(result, is(TEST_INTEGER));
      assertThat(context.getAnswerResult().actualContent(), is(TEST_INTEGER));
    }

    @Test
    @SuppressWarnings("all")
    void GIVEN_empty_WHEN_asNumber_THEN_return_null() {
      // Arrange
      useActResult(EMPTY);

      // Act
      var result = testAnswer.asNumber();

      // Assert
      assertThat(result, is(nullValue()));
    }

    @Test
    void GIVEN_throws_WHEN_asNumber_THEN_throw_TestAnswerException() {
      // Arrange
      var mockTestGenericMapper = mockParseWithClass();

      // Act
      var ex = assertThrows(TestAnswerException.class, () -> testAnswer.asNumber());

      // Assert
      assertThat(ex.getMessage(), containsString("asNumber"));
      assertThat(ex.getMessage(), containsString("Number"));
      assertThat(ex.getCause(), instanceOf(TestGenericMapperException.class));
      mockTestGenericMapper.close();
    }
  }

  @Nested
  class asBigDecimal {

    @Test
    void GIVEN_1_JSON_WHEN_asBigDecimal_THEN_return_1() {
      // Arrange
      useActResult(TEST_INTEGER_JSON);

      // Act
      var result = testAnswer.asBigDecimal();

      // Assert
      assertThat(result, is(TEST_BIGDECIMAL));
      assertThat(context.getAnswerResult().actualContent(), is(TEST_BIGDECIMAL));
    }

    @Test
    @SuppressWarnings("all")
    void GIVEN_empty_WHEN_asBigDecimal_THEN_return_null() {
      // Arrange
      useActResult(EMPTY);

      // Act
      var result = testAnswer.asBigDecimal();

      // Assert
      assertThat(result, is(nullValue()));
    }

    @Test
    void GIVEN_throws_WHEN_asBigDecimal_THEN_throw_TestAnswerException() {
      // Arrange
      var mockTestGenericMapper = mockParseWithClass();

      // Act
      var ex = assertThrows(TestAnswerException.class, () -> testAnswer.asBigDecimal());

      // Assert
      assertThat(ex.getMessage(), containsString("asBigDecimal"));
      assertThat(ex.getMessage(), containsString("BigDecimal"));
      assertThat(ex.getCause(), instanceOf(TestGenericMapperException.class));
      mockTestGenericMapper.close();
    }
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
      assertThat(context.getAnswerResult().actualContent(), is(TEST_INTEGER));
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
    void GIVEN_throws_WHEN_asInteger_THEN_throw_TestAnswerException() {
      // Arrange
      var mockTestGenericMapper = mockParseWithClass();

      // Act
      var ex = assertThrows(TestAnswerException.class, () -> testAnswer.asInteger());

      // Assert
      assertThat(ex.getMessage(), containsString("asInteger"));
      assertThat(ex.getMessage(), containsString("Integer"));
      assertThat(ex.getCause(), instanceOf(TestGenericMapperException.class));
      mockTestGenericMapper.close();
    }
  }

  @Nested
  class asLong {

    @Test
    void GIVEN_1_JSON_WHEN_asLong_THEN_return_1() {
      // Arrange
      useActResult(TEST_INTEGER_JSON);

      // Act
      var result = testAnswer.asLong();

      // Assert
      assertThat(result, is(TEST_LONG));
      assertThat(context.getAnswerResult().actualContent(), is(TEST_LONG));
    }

    @Test
    @SuppressWarnings("all")
    void GIVEN_empty_WHEN_asLong_THEN_return_null() {
      // Arrange
      useActResult(EMPTY);

      // Act
      var result = testAnswer.asLong();

      // Assert
      assertThat(result, is(nullValue()));
    }

    @Test
    void GIVEN_throws_WHEN_asLong_THEN_throw_TestAnswerException() {
      // Arrange
      var mockTestGenericMapper = mockParseWithClass();

      // Act
      var ex = assertThrows(TestAnswerException.class, () -> testAnswer.asLong());

      // Assert
      assertThat(ex.getMessage(), containsString("asLong"));
      assertThat(ex.getMessage(), containsString("Long"));
      assertThat(ex.getCause(), instanceOf(TestGenericMapperException.class));
      mockTestGenericMapper.close();
    }
  }

  @Nested
  class asBigInteger {

    @Test
    void GIVEN_1_JSON_WHEN_asBigInteger_THEN_return_1() {
      // Arrange
      useActResult(TEST_INTEGER_JSON);

      // Act
      var result = testAnswer.asBigInteger();

      // Assert
      assertThat(result, is(TEST_BIGINTEGER));
      assertThat(context.getAnswerResult().actualContent(), is(TEST_BIGINTEGER));
    }

    @Test
    @SuppressWarnings("all")
    void GIVEN_empty_WHEN_asBigInteger_THEN_return_null() {
      // Arrange
      useActResult(EMPTY);

      // Act
      var result = testAnswer.asBigInteger();

      // Assert
      assertThat(result, is(nullValue()));
    }

    @Test
    void GIVEN_throws_WHEN_asBigInteger_THEN_throw_TestAnswerException() {
      // Arrange
      var mockTestGenericMapper = mockParseWithClass();

      // Act
      var ex = assertThrows(TestAnswerException.class, () -> testAnswer.asBigInteger());

      // Assert
      assertThat(ex.getMessage(), containsString("asBigInteger"));
      assertThat(ex.getMessage(), containsString("BigInteger"));
      assertThat(ex.getCause(), instanceOf(TestGenericMapperException.class));
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
      assertThat(context.getAnswerResult().actualContent(), is(TEST_FLOAT));
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
    void GIVEN_throws_WHEN_asFloat_THEN_throw_TestAnswerException() {
      // Arrange
      var mockTestGenericMapper = mockParseWithClass();

      // Act
      var ex = assertThrows(TestAnswerException.class, () -> testAnswer.asFloat());

      // Assert
      assertThat(ex.getMessage(), containsString("asFloat"));
      assertThat(ex.getMessage(), containsString("Float"));
      assertThat(ex.getCause(), instanceOf(TestGenericMapperException.class));
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
      assertThat(context.getAnswerResult().actualContent(), is(TEST_DOUBLE));
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
    void GIVEN_throws_WHEN_asDouble_THEN_throw_TestAnswerException() {
      // Arrange
      var mockTestGenericMapper = mockParseWithClass();

      // Act
      var ex = assertThrows(TestAnswerException.class, () -> testAnswer.asDouble());

      // Assert
      assertThat(ex.getMessage(), containsString("asDouble"));
      assertThat(ex.getMessage(), containsString("Double"));
      assertThat(ex.getCause(), instanceOf(TestGenericMapperException.class));
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
      assertThat(context.getAnswerResult().actualContent(), is(TEST_BOOLEAN));
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
    void GIVEN_throws_WHEN_asBoolean_THEN_throw_TestAnswerException() {
      // Arrange
      var mockTestGenericMapper = mockParseWithClass();

      // Act
      var ex = assertThrows(TestAnswerException.class, () -> testAnswer.asBoolean());

      // Assert
      assertThat(ex.getMessage(), containsString("asBoolean"));
      assertThat(ex.getMessage(), containsString("Boolean"));
      assertThat(ex.getCause(), instanceOf(TestGenericMapperException.class));
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
      assertThat(context.getAnswerResult().actualContent(), is(TEST_A1_JSON));
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
      assertThat(result, is(TEST_A1_JSON.getBytes()));
      assertThat(context.getAnswerResult().actualContent(), is(TEST_A1_JSON.getBytes()));
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
    void GIVEN_throws_WHEN_asObject_THEN_throw_TestAnswerException() {
      // Arrange
      var mockTestGenericMapper = mockParseWithClass();

      // Act
      var ex =
          assertThrows(
              TestAnswerException.class, () -> testAnswer.asObject(TestObjectSimple.class));

      // Assert
      assertThat(ex.getMessage(), containsString("asObject"));
      assertThat(ex.getMessage(), containsString("TestObjectSimple"));
      assertThat(ex.getCause(), instanceOf(TestGenericMapperException.class));
      mockTestGenericMapper.close();
    }
  }

  @Nested
  class asCollection {

    @Test
    @SuppressWarnings("ConstantConditions")
    void GIVEN_null_WHEN_asCollection_THEN_throw_NullPointerException() {
      // Act & Arrange
      assertThrows(NullPointerException.class, () -> testAnswer.asCollection(null));
    }

    @Test
    void GIVEN_A1_A2_WHEN_asCollection_THEN_return_A1_A2() {
      // Arrange
      useActResult(TEST_LIST_A1_A2_JSON);

      // Act
      var result = testAnswer.asCollection(TestObjectSimple.class);

      // Assert
      assertThat(result, is(TEST_LIST_A1_A2));
      assertThat(context.getAnswerResult().actualContent(), is(TEST_LIST_A1_A2));
    }

    @Test
    void GIVEN_null_WHEN_asCollection_THEN_return_null() {
      // Arrange
      useActResult(EMPTY);

      // Act
      var result = testAnswer.asCollection(TestObjectSimple.class);

      // Assert
      assertThat(result, is(nullValue()));
    }

    @Test
    void GIVEN_throws_WHEN_asCollection_THEN_throwTestAnswerException() {
      // Arrange
      var mockTestGenericMapper = mockParseCollectionWithClass();

      // Act
      var ex =
          assertThrows(
              TestAnswerException.class, () -> testAnswer.asCollection(TestObjectSimple.class));

      // Assert
      assertThat(ex.getMessage(), containsString("asCollection"));
      assertThat(ex.getMessage(), containsString("TestObjectSimple"));
      assertThat(ex.getCause(), instanceOf(TestGenericMapperException.class));
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
      assertThat(context.getAnswerResult().actualContent(), is(TEST_LIST_A1_A2));
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
    void GIVEN_throws_WHEN_asList_THEN_throwTestAnswerException() {
      // Arrange
      var mockTestGenericMapper = mockParseListWithClass();

      // Act
      var ex =
          assertThrows(TestAnswerException.class, () -> testAnswer.asList(TestObjectSimple.class));

      // Assert
      assertThat(ex.getMessage(), containsString("asList"));
      assertThat(ex.getMessage(), containsString("TestObjectSimple"));
      assertThat(ex.getCause(), instanceOf(TestGenericMapperException.class));
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
      assertThat(context.getAnswerResult().actualContent(), is(TEST_SET_A1_A2));
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
    void GIVEN_throws_WHEN_asSet_THEN_throwTestAnswerException() {
      // Arrange
      var mockTestGenericMapper = mockParseSetWithClass();

      // Act
      var ex =
          assertThrows(TestAnswerException.class, () -> testAnswer.asSet(TestObjectSimple.class));

      // Assert
      assertThat(ex.getMessage(), containsString("asSet"));
      assertThat(ex.getMessage(), containsString("TestObjectSimple"));
      assertThat(ex.getCause(), instanceOf(TestGenericMapperException.class));
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
      assertThat(context.getAnswerResult().actualContent(), is(TEST_MAP_A1_A2));
    }

    @Test
    void GIVEN_null_WHEN_asMap_THEN_return_null() {
      // Arrange
      useActResult(EMPTY);

      // Act
      var result = testAnswer.asMap(Integer.class, TestObjectSimple.class);

      // Assert
      assertThat(result, is(nullValue()));
    }

    @Test
    void GIVEN_throws_WHEN_asMap_THEN_throw_TestAnswerException() {
      // Arrange
      var mockTestGenericMapper = mockParseMapWithClass();

      // Act
      var ex =
          assertThrows(
              TestAnswerException.class,
              () -> testAnswer.asMap(Integer.class, TestObjectSimple.class));

      // Assert
      assertThat(ex.getMessage(), containsString("asMap"));
      assertThat(ex.getMessage(), containsString("TestObjectSimple"));
      assertThat(ex.getMessage(), containsString("Integer"));
      assertThat(ex.getCause(), instanceOf(TestGenericMapperException.class));
      mockTestGenericMapper.close();
    }
  }
}
