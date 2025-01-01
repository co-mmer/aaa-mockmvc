package io.github.co_mmer.aaamockmvc.ej.test.web.answer;

import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestBody.TEST_BODY_JSON;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestDataRequestDto.TEST_REQUEST_DTO;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestHeader.TEST_HEADER_KEY_1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestHeader.TEST_HEADER_VALUE_1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_A1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_A1_JSON;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_LIST_A1_A2;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_LIST_A1_A2_JSON;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_MAP_A1_A2;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_MAP_A1_A2_JSON;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_SET_A1_A2;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_SET_A1_A2_JSON;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.TEST_BYTE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.co_mmer.aaamockmvc.ej.test.web.act.strategy.TestRequestBaseStrategy;
import io.github.co_mmer.aaamockmvc.ej.test.web.answer.exception.TestAnswerException;
import io.github.co_mmer.aaamockmvc.ej.test.web.request.context.TestRequestBean;
import io.github.co_mmer.aaamockmvc.ej.test.web.request.context.TestRequestContext;
import io.github.co_mmer.aaamockmvc.ej.testdata.MockTestRequestStrategyFactory;
import io.github.co_mmer.aaamockmvc.ej.testdata.testsetup.MockMvcSetup;
import io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObjectA;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

class TestAnswerImplTest {

  private MockMvc mvc;
  private MockMvcSetup setup;
  private TestAnswer testAnswer;
  private MockHttpServletRequestBuilder mockRequestBuilder;
  private TestRequestContext testRequestContext;

  @BeforeEach
  void setUp() {
    this.mvc = mock(MockMvc.class);
    this.setup = new MockMvcSetup(this.mvc);
    var testRequestConfig = new TestRequestBean(this.mvc, new ObjectMapper());
    this.mockRequestBuilder = mock(MockHttpServletRequestBuilder.class);

    this.testRequestContext = new TestRequestContext(TEST_REQUEST_DTO, testRequestConfig);
    this.testAnswer = new TestAnswerImpl(this.testRequestContext, this.mockRequestBuilder);
  }

  @Test
  void GIVEN_exception_WHEN_answerAsResultActions_THEN_throw_exception() throws Exception {
    // Arrange
    when(this.mvc.perform(any())).thenThrow(Exception.class);

    // Act
    var exception = assertThrows(Exception.class, () -> this.testAnswer.answerAsResultActions());

    // Assert
    assertThat(exception.getClass(), is(TestAnswerException.class));
  }

  @Test
  void WHEN_answerVoid_THEN_mvc_perform_is_called() throws Exception {
    // Arrange
    var mockTestRequestStrategy = Mockito.mock(TestRequestBaseStrategy.class);
    var mockTestRequestStrategyFactory =
        MockTestRequestStrategyFactory.mockTestRequestStrategyFactory(mockTestRequestStrategy);

    when(mockTestRequestStrategy.apply(TEST_REQUEST_DTO)).thenReturn(this.mockRequestBuilder);

    // Act
    this.testAnswer.answerVoid();

    // Assert
    verify(this.mvc).perform(this.mockRequestBuilder);
    mockTestRequestStrategyFactory.close();
  }

  @Test
  void GIVEN_exception_WHEN_answerVoid_THEN_throw_TestAnswerException() throws Exception {
    // Arrange
    when(this.mvc.perform(any())).thenThrow(Exception.class);

    // Act
    var exception = assertThrows(Exception.class, () -> this.testAnswer.answerVoid());

    // Assert
    assertThat(exception.getClass(), is(TestAnswerException.class));
  }

  @Test
  void WHEN_answerAsString_THEN_mvc_getContentAsString_is_called() throws Exception {
    // Arrange
    this.setup.mockGetContentAsString();
    this.testAnswer = new TestAnswerImpl(this.testRequestContext, this.mockRequestBuilder);

    // Act
    this.testAnswer.answerAsString();

    // Assert
    this.setup.verifyGetContentAsString();
  }

  @Test
  void WHEN_answerAsString_THEN_return_expected_value() throws Exception {
    // Arrange
    this.setup.mockGetContentAsString();
    this.testAnswer = new TestAnswerImpl(this.testRequestContext, this.mockRequestBuilder);

    // Act
    var result = this.testAnswer.answerAsString();

    // Assert
    assertThat(result, is(TEST_BODY_JSON));
  }

  @Test
  void GIVEN_exception_WHEN_answerAsString_THEN_throw_exception() throws Exception {
    // Arrange
    this.setup.mockThrowGetContentAsString();
    this.testAnswer = new TestAnswerImpl(this.testRequestContext, this.mockRequestBuilder);

    // Act
    var exception = assertThrows(Exception.class, () -> this.testAnswer.answerAsString());

    // Assert
    assertThat(exception.getClass(), is(TestAnswerException.class));
  }

  @Test
  void WHEN_answerAsByte_THEN_mvc_getContentAsByteArray_is_called() throws Exception {
    // Arrange
    this.setup.mockGetContentAsByteArray();
    this.testAnswer = new TestAnswerImpl(this.testRequestContext, this.mockRequestBuilder);

    // Act
    this.testAnswer.answerAsByte();

    // Assert
    this.setup.verifyGetContentAsByteArray();
  }

  @Test
  void WHEN_answerAsByte_THEN_return_expected_value() throws Exception {
    // Arrange
    this.setup.mockGetContentAsByteArray();
    this.testAnswer = new TestAnswerImpl(this.testRequestContext, this.mockRequestBuilder);

    // Act
    var result = this.testAnswer.answerAsByte();

    // Assert
    assertThat(result, is(TEST_BYTE));
  }

  @Test
  @SuppressWarnings("ConstantConditions")
  void GIVEN_null_WHEN_answerAsObject_THEN_throw_NullPointerException() {
    assertThrows(NullPointerException.class, () -> this.testAnswer.answerAsObject(null));
  }

  @Test
  void GIVEN_exception_WHEN_answerAsObject_THEN_throw_TestAnswerException() throws Exception {
    // Arrange
    when(this.mvc.perform(any())).thenThrow(Exception.class);

    // Act
    var exception =
        assertThrows(Exception.class, () -> this.testAnswer.answerAsObject(TestObjectA.class));

    // Assert
    assertThat(exception.getClass(), is(TestAnswerException.class));
  }

  @Test
  void GIVEN_wrong_class_WHEN_answerAsObject_THEN_throw_Exception() throws Exception {
    // Arrange
    this.setup.mockGetContentAsString(TEST_A1_JSON);
    this.testAnswer = new TestAnswerImpl(this.testRequestContext, this.mockRequestBuilder);

    // Assert
    assertThrows(
        TestAnswerException.class,

        // Act
        () -> this.testAnswer.answerAsObject(String.class));
  }

  @Test
  void WHEN_answerAsObject_THEN_return_expected_object() throws Exception {
    // Arrange
    this.setup.mockGetContentAsString(TEST_A1_JSON);
    this.testAnswer = new TestAnswerImpl(this.testRequestContext, this.mockRequestBuilder);

    // Act
    var result = this.testAnswer.answerAsObject(TestObjectA.class);

    // Assert
    assertThat(result, is(TEST_A1));
  }

  @Test
  @SuppressWarnings("ConstantConditions")
  void GIVEN_null_WHEN_answerAsList_THEN_throw_NullPointerException() {
    assertThrows(NullPointerException.class, () -> this.testAnswer.answerAsList(null));
  }

  @Test
  void GIVEN_exception_WHEN_answerAsList_THEN_throw_TestAnswerException() throws Exception {
    // Arrange
    when(this.mvc.perform(any())).thenThrow(Exception.class);

    // Act
    var exception =
        assertThrows(Exception.class, () -> this.testAnswer.answerAsList(TestObjectA.class));

    // Assert
    assertThat(exception.getClass(), is(TestAnswerException.class));
  }

  @Test
  void GIVEN_wrong_class_WHEN_answerAsList_THEN_throw_Exception() throws Exception {
    // Arrange
    this.setup.mockGetContentAsString(TEST_LIST_A1_A2_JSON);
    this.testAnswer = new TestAnswerImpl(this.testRequestContext, this.mockRequestBuilder);

    // Assert
    assertThrows(
        TestAnswerException.class,

        // Act
        () -> this.testAnswer.answerAsList(String.class));
  }

  @Test
  void WHEN_answerAsList_THEN_return_expected_list() throws Exception {
    // Arrange
    this.setup.mockGetContentAsString(TEST_LIST_A1_A2_JSON);
    this.testAnswer = new TestAnswerImpl(this.testRequestContext, this.mockRequestBuilder);

    // Act
    var result = this.testAnswer.answerAsList(TestObjectA.class);

    // Assert
    assertThat(result, is(TEST_LIST_A1_A2));
  }

  @Test
  @SuppressWarnings("ConstantConditions")
  void GIVEN_null_WHEN_answerAsSet_THEN_throw_NullPointerException() {
    assertThrows(NullPointerException.class, () -> this.testAnswer.answerAsSet(null));
  }

  @Test
  void GIVEN_exception_WHEN_answerAsSet_THEN_throw_TestAnswerException() throws Exception {
    // Arrange
    when(this.mvc.perform(any())).thenThrow(Exception.class);

    // Act
    var exception =
        assertThrows(Exception.class, () -> this.testAnswer.answerAsSet(TestObjectA.class));

    // Assert
    assertThat(exception.getClass(), is(TestAnswerException.class));
  }

  @Test
  void GIVEN_wrong_class_WHEN_answerAsSet_THEN_throw_Exception() throws Exception {
    // Arrange
    this.setup.mockGetContentAsString(TEST_LIST_A1_A2_JSON);
    this.testAnswer = new TestAnswerImpl(this.testRequestContext, this.mockRequestBuilder);

    // Assert
    assertThrows(
        TestAnswerException.class,

        // Act
        () -> this.testAnswer.answerAsSet(String.class));
  }

  @Test
  void WHEN_answerAsSet_THEN_return_expected_set() throws Exception {
    // Arrange
    this.setup.mockGetContentAsString(TEST_SET_A1_A2_JSON);
    this.testAnswer = new TestAnswerImpl(this.testRequestContext, this.mockRequestBuilder);

    // Act
    var result = this.testAnswer.answerAsSet(TestObjectA.class);

    // Assert
    assertThat(result, is(TEST_SET_A1_A2));
  }

  @ParameterizedTest
  @MethodSource("provideNullParameters")
  @SuppressWarnings("ConstantConditions")
  void GIVEN_provideNullParameters_WHEN_answerAsMap_THEN_throw_NullPointerException(
      Class<String> targetKeyClass, Class<String> targetValueClass) {

    assertThrows(
        NullPointerException.class,
        () -> this.testAnswer.answerAsMap(targetKeyClass, targetValueClass));
  }

  private static Stream<Arguments> provideNullParameters() {
    return Stream.of(
        Arguments.of(null, String.class),
        Arguments.of(String.class, null),
        Arguments.of(null, null));
  }

  @Test
  void GIVEN_exception_WHEN_answerAsMap_THEN_throw_TestAnswerException() throws Exception {
    // Arrange
    when(this.mvc.perform(any())).thenThrow(Exception.class);

    // Act
    var exception =
        assertThrows(
            Exception.class, () -> this.testAnswer.answerAsMap(Boolean.class, TestObjectA.class));

    // Assert
    assertThat(exception.getClass(), is(TestAnswerException.class));
  }

  @ParameterizedTest
  @MethodSource("wrongClasses")
  void GIVEN_wrongClasses_WHEN_answerAsMap_THEN_throw_Exception(
      Class<?> targetKeyClass, Class<?> targetValueClass) throws Exception {

    // Arrange
    this.setup.mockGetContentAsString(TEST_LIST_A1_A2_JSON);
    this.testAnswer = new TestAnswerImpl(this.testRequestContext, this.mockRequestBuilder);

    // Assert
    assertThrows(
        TestAnswerException.class,

        // Act
        () -> this.testAnswer.answerAsMap(targetKeyClass, targetValueClass));
  }

  private static Stream<Arguments> wrongClasses() {
    return Stream.of(
        Arguments.of(Boolean.class, String.class),
        Arguments.of(String.class, TestObjectA.class),
        Arguments.of(String.class, String.class));
  }

  @Test
  void WHEN_answerAsMap_THEN_return_expected_map() throws Exception {
    // Arrange
    this.setup.mockGetContentAsString(TEST_MAP_A1_A2_JSON);
    this.testAnswer = new TestAnswerImpl(this.testRequestContext, this.mockRequestBuilder);

    // Act
    var result = this.testAnswer.answerAsMap(Boolean.class, TestObjectA.class);

    // Assert
    assertThat(result, is(TEST_MAP_A1_A2));
  }

  @Test
  void WHEN_answerHeader_THEN_mvc_getContentAsByteArray_is_called() throws Exception {
    // Arrange
    this.setup.mockGetHeader();
    this.testAnswer = new TestAnswerImpl(this.testRequestContext, this.mockRequestBuilder);

    // Act
    this.testAnswer.answerHeader(TEST_HEADER_KEY_1);

    // Assert
    this.setup.verifyGetHeader();
  }

  @Test
  void WHEN_answerHeader_THEN_return_expected_value() throws Exception {
    // Arrange
    this.setup.mockGetHeader();
    this.testAnswer = new TestAnswerImpl(this.testRequestContext, this.mockRequestBuilder);

    // Act
    var result = this.testAnswer.answerHeader(TEST_HEADER_KEY_1);

    // Assert
    assertThat(result, is(TEST_HEADER_VALUE_1));
  }
}
