package io.github.co_mmer.aaamockmvc.ej.test.web.asserts;

import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.TEST_HEAD_KEY_1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.TEST_HEAD_VALUE_1;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.bytes.TestAssertByteImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.clazz.TestAssertClassImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.collection.TestAssertCollectionImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.content.TestAssertContentImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.custom.TestAssertCustomImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.head.TestAssertHeadImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.map.TestAssertMapImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.status.TestAssertStatusImpl;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

class TestAssertImplTest {

  private MockHttpServletResponse response;
  private TestAssertImpl testAssert;

  @BeforeEach
  void setUp() {
    var actions = mock(ResultActions.class);
    var mvcResult = mock(MvcResult.class);
    this.response = new MockHttpServletResponse();

    when(mvcResult.getResponse()).thenReturn(this.response);
    when(actions.andReturn()).thenReturn(mvcResult);

    this.testAssert = new TestAssertImpl(actions, new ObjectMapper());
  }

  @ParameterizedTest
  @MethodSource("provideNull")
  @SuppressWarnings("ConstantConditions")
  void GIVEN_provideNull_WHEN_call_constructor__THEN_throw_NullPointerException(
      ResultActions resultActions, ObjectMapper objectMapper) {
    assertThrows(NullPointerException.class, () -> new TestAssertImpl(resultActions, objectMapper));
  }

  private static Stream<Arguments> provideNull() {
    return Stream.of(
        Arguments.of(null, mock(ObjectMapper.class)),
        Arguments.of(mock(ResultActions.class), null),
        Arguments.of(null, null));
  }

  @Test
  void WHEN_assertStatus_THEN_return_expected_class() {

    // Act
    var assertStatus = this.testAssert.assertStatus();

    // Assert
    assertThat(assertStatus.getClass(), is(TestAssertStatusImpl.class));
  }

  @Test
  void WHEN_assertContent_THEN_return_expected_class() {
    // Act
    var assertContent = this.testAssert.assertContent();

    // Assert
    assertThat(assertContent.getClass(), is(TestAssertContentImpl.class));
  }

  @Test
  void WHEN_assertClass_THEN_return_expected_class() {
    // Act
    var assertClass = this.testAssert.assertClass();

    // Assert
    assertThat(assertClass.getClass(), is(TestAssertClassImpl.class));
  }

  @Test
  void WHEN_assertByte_THEN_return_expected_class() {

    // Act
    var assertByte = this.testAssert.assertByte();

    // Assert
    assertThat(assertByte.getClass(), is(TestAssertByteImpl.class));
  }

  @Test
  void WHEN_assertCollection_THEN_return_expected_class() {
    // Act
    var assertCollection = this.testAssert.assertCollection();

    // Assert
    assertThat(assertCollection.getClass(), is(TestAssertCollectionImpl.class));
  }

  @Test
  void WHEN_assertMap_THEN_return_expected_class() {
    // Act
    var assertMap = this.testAssert.assertMap();

    // Assert
    assertThat(assertMap.getClass(), is(TestAssertMapImpl.class));
  }

  @Test
  void WHEN_assertCustom_THEN_return_expected_class() {
    // Act
    var assertCustom = this.testAssert.assertCustom();

    // Assert
    assertThat(assertCustom.getClass(), is(TestAssertCustomImpl.class));
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
}
