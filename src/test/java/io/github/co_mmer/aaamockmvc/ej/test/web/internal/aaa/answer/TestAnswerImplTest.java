package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.answer;

import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.utils.StringUtils.EMPTY;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testmock.MockTestGenericMapper.mockParseCollectionWithClass;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testmock.MockTestGenericMapper.mockParseListWithClass;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testmock.MockTestGenericMapper.mockParseMapWithClass;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testmock.MockTestGenericMapper.mockParseSetWithClass;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testmock.MockTestGenericMapper.mockParseWithClass;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.A1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_A1_JSON;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_BOOLEAN;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_BOOLEAN_JSON;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_LIST_A1_A2;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_LIST_A1_A2_JSON;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_MAP_A1_A2;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_MAP_A1_A2_JSON;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_SET_A1_A2;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_SET_A1_A2_JSON;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

import io.github.co_mmer.aaamockmvc.ej.test.web.answer.TestAnswer;
import io.github.co_mmer.aaamockmvc.ej.test.web.answer.exception.TestAnswerFailed;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.TestAssertBase;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.context.TestAAAContext;
import io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestContext;
import io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObjectSimple;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class TestAnswerImplTest extends TestAssertBase {

  private static final String TESTING = "Testing";
  private TestAAAContext context;
  private TestAnswer impl;

  @BeforeEach
  @SneakyThrows
  void setUp() {
    this.context = TestContext.mockContext(TESTING);
    this.useContext(context);
    this.impl = new TestAnswerImpl(context);
  }

  @Nested
  class asBoolean {

    @Test
    void GIVEN_True_JSON_WHEN_asBoolean_THEN_return_1() {
      // Arrange
      useActResult(TEST_BOOLEAN_JSON);

      // Act
      var result = impl.asBoolean();

      // Assert
      assertThat(result, is(TEST_BOOLEAN));
      assertThat(context.getAnswerResult().actualContent(), is(TEST_BOOLEAN));
    }

    @Test
    void GIVEN_empty_WHEN_asBoolean_THEN_TestAnswerFailed_is_thrown() {
      // Arrange
      useActResult(EMPTY);

      // Act & Assert
      assertThrows(TestAnswerFailed.class, () -> impl.asBoolean());
    }

    @Test
    void GIVEN_throws_WHEN_asBoolean_THEN_throw_TestAnswerFailed() {
      // Arrange
      var mockTestGenericMapper = mockParseWithClass();

      // Act
      var ex = assertThrows(TestAnswerFailed.class, () -> impl.asBoolean());

      // Assert
      assertThat(
          ex.getMessage(),
          is(
              "Step 'Testing' ⇒ 'answer().asBoolean()' — Reason: Response body '{\"id\":1,\"name\":\"A\"}' cannot be mapped to Boolean"));
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
      var result = impl.asString();

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
      var result = impl.asString();

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
      var result = impl.asByte();

      // Assert
      assertThat(result, is(TEST_A1_JSON.getBytes()));
      assertThat(context.getAnswerResult().actualContent(), is(TEST_A1_JSON.getBytes()));
    }
  }

  @Nested
  class asObject {

    @Test
    @SuppressWarnings("ConstantConditions")
    void GIVEN_null_WHEN_asObject_THEN_throw_IllegalArgumentException() {
      // Act & Arrange
      assertThrows(IllegalArgumentException.class, () -> impl.asObject(null));
    }

    @Test
    void GIVEN_A1_WHEN_asObject_THEN_return_A1() {
      // Arrange
      useActResult(TEST_A1_JSON);

      // Act
      var result = impl.asObject(TestObjectSimple.class);

      // Assert
      assertThat(result, is(A1));
    }

    @Test
    void GIVEN_empty_WHEN_asObject_THEN_TestAnswerFailed_is_thrown() {
      // Arrange
      useActResult(EMPTY);

      // Act & Assert
      assertThrows(TestAnswerFailed.class, () -> impl.asObject(TestObjectSimple.class));
    }

    @Test
    void GIVEN_throws_WHEN_asObject_THEN_throw_TestAnswerFailed() {
      // Arrange
      var mockTestGenericMapper = mockParseWithClass();

      // Act
      var ex = assertThrows(TestAnswerFailed.class, () -> impl.asObject(TestObjectSimple.class));

      // Assert
      assertThat(
          ex.getMessage(),
          is(
              "Step 'Testing' ⇒ 'answer().asObject()' — Reason: Response body '{\"id\":1,\"name\":\"A\"}' cannot be mapped to TestObjectSimple"));
      mockTestGenericMapper.close();
    }
  }

  @Nested
  class asCollection {

    @Test
    @SuppressWarnings("ConstantConditions")
    void GIVEN_null_WHEN_asCollection_THEN_throw_IllegalArgumentException() {
      // Act & Arrange
      assertThrows(IllegalArgumentException.class, () -> impl.asCollection(null));
    }

    @Test
    void GIVEN_A1_A2_WHEN_asCollection_THEN_return_A1_A2() {
      // Arrange
      useActResult(TEST_LIST_A1_A2_JSON);

      // Act
      var result = impl.asCollection(TestObjectSimple.class);

      // Assert
      assertThat(result, is(TEST_LIST_A1_A2));
      assertThat(context.getAnswerResult().actualContent(), is(TEST_LIST_A1_A2));
    }

    @Test
    void GIVEN_empty_WHEN_asCollection_THEN_TestAnswerFailed_is_thrown() {
      // Arrange
      useActResult(EMPTY);

      // Act & Assert
      assertThrows(TestAnswerFailed.class, () -> impl.asCollection(TestObjectSimple.class));
    }

    @Test
    void GIVEN_throws_WHEN_asCollection_THEN_throw_TestAnswerFailed() {
      // Arrange
      var mockTestGenericMapper = mockParseCollectionWithClass();

      // Act
      var ex =
          assertThrows(TestAnswerFailed.class, () -> impl.asCollection(TestObjectSimple.class));

      // Assert
      assertThat(
          ex.getMessage(),
          is(
              "Step 'Testing' ⇒ 'answer().asCollection()' — Reason: Response body '{\"id\":1,\"name\":\"A\"}' cannot be mapped to TestObjectSimple"));
      mockTestGenericMapper.close();
    }
  }

  @Nested
  class asList {

    @Test
    @SuppressWarnings("ConstantConditions")
    void GIVEN_null_WHEN_asList_THEN_throw_IllegalArgumentException() {
      // Act & Arrange
      assertThrows(IllegalArgumentException.class, () -> impl.asList(null));
    }

    @Test
    void GIVEN_A1_A2_WHEN_asList_THEN_return_A1_A2() {
      // Arrange
      useActResult(TEST_LIST_A1_A2_JSON);

      // Act
      var result = impl.asList(TestObjectSimple.class);

      // Assert
      assertThat(result, is(TEST_LIST_A1_A2));
      assertThat(context.getAnswerResult().actualContent(), is(TEST_LIST_A1_A2));
    }

    @Test
    void GIVEN_empty_WHEN_asList_THEN_TestAnswerFailed_is_thrown() {
      // Arrange
      useActResult(EMPTY);

      // Act & Assert
      assertThrows(TestAnswerFailed.class, () -> impl.asList(TestObjectSimple.class));
    }

    @Test
    void GIVEN_throws_WHEN_asList_THEN_throw_TestAnswerFailed() {
      // Arrange
      var mockTestGenericMapper = mockParseListWithClass();

      // Act
      var ex = assertThrows(TestAnswerFailed.class, () -> impl.asList(TestObjectSimple.class));

      // Assert
      assertThat(
          ex.getMessage(),
          is(
              "Step 'Testing' ⇒ 'answer().asList()' — Reason: Response body '{\"id\":1,\"name\":\"A\"}' cannot be mapped to TestObjectSimple"));
      mockTestGenericMapper.close();
    }
  }

  @Nested
  class asSet {

    @Test
    @SuppressWarnings("ConstantConditions")
    void GIVEN_null_WHEN_asSet_THEN_throw_IllegalArgumentException() {
      // Act & Arrange
      assertThrows(IllegalArgumentException.class, () -> impl.asSet(null));
    }

    @Test
    void GIVEN_A1_A2_WHEN_asSet_THEN_return_A1_A2() {
      // Arrange
      useActResult(TEST_SET_A1_A2_JSON);

      // Act
      var result = impl.asSet(TestObjectSimple.class);

      // Assert
      assertThat(result, is(TEST_SET_A1_A2));
      assertThat(context.getAnswerResult().actualContent(), is(TEST_SET_A1_A2));
    }

    @Test
    void GIVEN_empty_WHEN_asSet_THEN_TestAnswerFailed_is_thrown() {
      // Arrange
      useActResult(EMPTY);

      // Act & Assert
      assertThrows(TestAnswerFailed.class, () -> impl.asSet(TestObjectSimple.class));
    }

    @Test
    void GIVEN_throws_WHEN_asSet_THEN_throw_TestAnswerFailed() {
      // Arrange
      var mockTestGenericMapper = mockParseSetWithClass();

      // Act
      var ex = assertThrows(TestAnswerFailed.class, () -> impl.asSet(TestObjectSimple.class));

      // Assert
      assertThat(
          ex.getMessage(),
          containsString(
              "Step 'Testing' ⇒ 'answer().asSet()' — Reason: Response body '{\"id\":1,\"name\":\"A\"}' cannot be mapped to TestObjectSimple"));
      mockTestGenericMapper.close();
    }
  }

  @Nested
  class asMap {

    @Test
    @SuppressWarnings("ConstantConditions")
    void GIVEN_null_WHEN_asMap_THEN_throw_IllegalArgumentException() {
      // Act & Arrange
      assertThrows(IllegalArgumentException.class, () -> impl.asMap(null, null));
    }

    @Test
    @SuppressWarnings("ConstantConditions")
    void GIVEN_keyIsNull_WHEN_asMap_THEN_throw_IllegalArgumentException() {
      // Act & Arrange
      assertThrows(IllegalArgumentException.class, () -> impl.asMap(null, TestObjectSimple.class));
    }

    @Test
    @SuppressWarnings("ConstantConditions")
    void GIVEN_valueIsNull_WHEN_asMap_THEN_throw_IllegalArgumentException() {
      // Act & Arrange
      assertThrows(IllegalArgumentException.class, () -> impl.asMap(Integer.class, null));
    }

    @Test
    @SneakyThrows
    void GIVEN_A1_A2_JSON_WHEN_asMap_THEN_return_A1_A2() {
      // Arrange
      useActResult(TEST_MAP_A1_A2_JSON);

      // Act
      var result = impl.asMap(Integer.class, TestObjectSimple.class);

      // Assert
      assertThat(result, is(TEST_MAP_A1_A2));
      assertThat(context.getAnswerResult().actualContent(), is(TEST_MAP_A1_A2));
    }

    @Test
    void GIVEN_empty_WHEN_asMap_THEN_TestAnswerFailed_is_thrown() {
      // Arrange
      useActResult(EMPTY);

      // Act & Assert
      assertThrows(TestAnswerFailed.class, () -> impl.asMap(Integer.class, TestObjectSimple.class));
    }

    @Test
    void GIVEN_throws_WHEN_asMap_THEN_throw_TestAnswerFailed() {
      // Arrange
      var mockTestGenericMapper = mockParseMapWithClass();

      // Act
      var ex =
          assertThrows(
              TestAnswerFailed.class, () -> impl.asMap(Integer.class, TestObjectSimple.class));

      // Assert
      assertThat(
          ex.getMessage(),
          containsString("Step 'Testing' ⇒ 'answer().asMap()' — Reason: Response body "));
      assertThat(
          ex.getMessage(), containsString("cannot be mapped to Map<Integer, TestObjectSimple>"));
      mockTestGenericMapper.close();
    }
  }
}
