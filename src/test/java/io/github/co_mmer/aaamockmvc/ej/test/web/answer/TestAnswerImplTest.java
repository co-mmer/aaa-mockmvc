package io.github.co_mmer.aaamockmvc.ej.test.web.answer;

import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestDataRequestDto.TEST_REQUEST_DTO;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestHeader.TEST_HEADER_KEY_1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestHeader.TEST_HEADER_VALUE_1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.A1;
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
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.co_mmer.aaamockmvc.ej.test.web.act.strategy.TestRequestBaseStrategy;
import io.github.co_mmer.aaamockmvc.ej.test.web.answer.exception.TestAnswerException;
import io.github.co_mmer.aaamockmvc.ej.test.web.request.context.TestRequestBean;
import io.github.co_mmer.aaamockmvc.ej.test.web.request.context.TestRequestContext;
import io.github.co_mmer.aaamockmvc.ej.testdata.MockTestRequestStrategyFactory;
import io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObjectSimple;
import java.io.UnsupportedEncodingException;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

class TestAnswerImplTest {

  private TestAnswer testAnswer;
  private ResultActions mockResultActions;
  private TestRequestBean testRequestBean;
  private MockHttpServletRequestBuilder mockRequestBuilder;

  @BeforeEach
  @SneakyThrows
  void setUp() {
    var mvc = mock(MockMvc.class);
    this.mockRequestBuilder = mock(MockHttpServletRequestBuilder.class);
    this.testRequestBean = new TestRequestBean(mvc, new ObjectMapper());
    this.mockResultActions = mock(ResultActions.class);
    when(mvc.perform(this.mockRequestBuilder)).thenReturn(this.mockResultActions);

    var testRequestContext = new TestRequestContext(TEST_REQUEST_DTO, testRequestBean);
    this.testAnswer = new TestAnswerImpl(testRequestContext, this.mockRequestBuilder);
  }

  private void useContentAsString(String content) throws Exception {
    var mockMvcResult = mock(MvcResult.class);
    var mockMockHttpServletResponse = mock(MockHttpServletResponse.class);
    when(mockMockHttpServletResponse.getContentAsString()).thenReturn(content);

    when(mockMvcResult.getResponse()).thenReturn(mockMockHttpServletResponse);
    when(mockResultActions.andReturn()).thenReturn(mockMvcResult);
  }

  private void useContentAsByte() {
    var mockMvcResult = mock(MvcResult.class);
    var mockMockHttpServletResponse = mock(MockHttpServletResponse.class);
    when(mockMockHttpServletResponse.getContentAsByteArray()).thenReturn(TEST_BYTE);

    when(mockMvcResult.getResponse()).thenReturn(mockMockHttpServletResponse);
    when(mockResultActions.andReturn()).thenReturn(mockMvcResult);
  }

  private void useContentAsStringException() throws Exception {
    var mockMvcResult = mock(MvcResult.class);
    var mockMockHttpServletResponse = mock(MockHttpServletResponse.class);
    when(mockMockHttpServletResponse.getContentAsString())
        .thenThrow(new UnsupportedEncodingException("test"));

    when(mockMvcResult.getResponse()).thenReturn(mockMockHttpServletResponse);
    when(mockResultActions.andReturn()).thenReturn(mockMvcResult);
  }

  private void useHeader() {
    var mockMvcResult = mock(MvcResult.class);
    var mockMockHttpServletResponse = mock(MockHttpServletResponse.class);
    when(mockMockHttpServletResponse.getHeader(TEST_HEADER_KEY_1)).thenReturn(TEST_HEADER_VALUE_1);
    when(mockMvcResult.getResponse()).thenReturn(mockMockHttpServletResponse);
    when(mockResultActions.andReturn()).thenReturn(mockMvcResult);
  }

  @Nested
  class answerAsResultActions {

    @Test
    @SneakyThrows
    void WHEN_answerAsResultActions_THEN_return_resultActions() {
      // Act
      var resultActions = testAnswer.answerAsResultActions();

      // Assert
      assertThat(resultActions, is(mockResultActions));
    }
  }

  @Nested
  class answerAsString {

    @Test
    @SneakyThrows
    void WHEN_answerAsString_THEN_return_expected_string() {
      // Arrange
      useContentAsString(TEST_A1_JSON);

      // Act
      var result = testAnswer.answerAsString();

      // Assert
      assertThat(result, is(TEST_A1_JSON));
    }

    @Test
    @SneakyThrows
    void GIVEN_null_WHEN_answerAsString_THEN_return_nullValue() {
      // Arrange
      useContentAsString(null);

      // Act
      var result = testAnswer.answerAsString();

      // Assert
      assertThat(result, is(nullValue()));
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_answerAsString_THEN_throw_TestAnswerException() {
      // Arrange
      useContentAsStringException();

      // Act & Assert
      assertThrows(TestAnswerException.class, () -> testAnswer.answerAsString());
    }
  }

  @Nested
  class answerAsByte {

    @Test
    @SneakyThrows
    void WHEN_answerAsByte_THEN_return_expected_byte() {
      // Arrange
      useContentAsByte();

      // Act
      var resultByte = testAnswer.answerAsByte();

      // Assert
      assertThat(resultByte, is(TEST_BYTE));
    }
  }

  @Nested
  class answerAsObject {

    @Test
    @SneakyThrows
    void WHEN_answerAsObject_THEN_return_expected_object() {
      // Arrange
      useContentAsString(TEST_A1_JSON);

      // Act
      var result = testAnswer.answerAsObject(TestObjectSimple.class);

      // Assert
      assertThat(result, is(A1));
    }

    @Test
    @SneakyThrows
    void GIVEN_null_WHEN_answerAsObject_THEN_return_nullValue() {
      // Arrange
      useContentAsString(null);

      // Act
      var result = testAnswer.answerAsObject(TestObjectSimple.class);

      // Assert
      assertThat(result, is(nullValue()));
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_answerAsObject_THEN_throw_TestAnswerException() {
      // Arrange
      useContentAsStringException();

      // Act & Assert
      assertThrows(
          TestAnswerException.class, () -> testAnswer.answerAsObject(TestObjectSimple.class));
    }

    @Test
    @SneakyThrows
    @SuppressWarnings("all")
    void GIVEN_null_as_resultType_WHEN_answerAsObject_THEN_throw_TestAnswerException() {
      // Act & Assert
      assertThrows(NullPointerException.class, () -> testAnswer.answerAsObject(null));
    }
  }

  @Nested
  class answerAsList {

    @Test
    @SneakyThrows
    void WHEN_answerAsList_THEN_return_expected_object() {
      // Arrange
      useContentAsString(TEST_LIST_A1_A2_JSON);

      // Act
      var result = testAnswer.answerAsList(TestObjectSimple.class);

      // Assert
      assertThat(result, is(TEST_LIST_A1_A2));
    }

    @Test
    @SneakyThrows
    void GIVEN_null_WHEN_answerAsList_THEN_return_nullValue() {
      // Arrange
      useContentAsString(null);

      // Act
      var result = testAnswer.answerAsList(TestObjectSimple.class);

      // Assert
      assertThat(result, is(nullValue()));
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_answerAsList_THEN_throw_TestAnswerException() {
      // Arrange
      useContentAsStringException();

      // Act & Assert
      assertThrows(
          TestAnswerException.class, () -> testAnswer.answerAsList(TestObjectSimple.class));
    }

    @Test
    @SneakyThrows
    @SuppressWarnings("all")
    void GIVEN_null_as_resultType_WHEN_answerAsList_THEN_throw_TestAnswerException() {
      // Act & Assert
      assertThrows(NullPointerException.class, () -> testAnswer.answerAsList(null));
    }
  }

  @Nested
  class answerAsSet {

    @Test
    @SneakyThrows
    void WHEN_answerAsSet_THEN_return_expected_object() {
      // Arrange
      useContentAsString(TEST_SET_A1_A2_JSON);

      // Act
      var result = testAnswer.answerAsSet(TestObjectSimple.class);

      // Assert
      assertThat(result, is(TEST_SET_A1_A2));
    }

    @Test
    @SneakyThrows
    void GIVEN_null_WHEN_answerAsSet_THEN_return_nullValue() {
      // Arrange
      useContentAsString(null);

      // Act
      var result = testAnswer.answerAsSet(TestObjectSimple.class);

      // Assert
      assertThat(result, is(nullValue()));
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_answerAsSet_THEN_throw_TestAnswerException() {
      // Arrange
      useContentAsStringException();

      // Act & Assert
      assertThrows(TestAnswerException.class, () -> testAnswer.answerAsSet(TestObjectSimple.class));
    }

    @Test
    @SneakyThrows
    @SuppressWarnings("all")
    void GIVEN_null_as_resultType_WHEN_answerAsSet_THEN_throw_TestAnswerException() {
      // Act & Assert
      assertThrows(NullPointerException.class, () -> testAnswer.answerAsSet(null));
    }
  }

  @Nested
  class answerAsMap {

    @Test
    @SneakyThrows
    void WHEN_answerAsMap_THEN_return_expected_object() {
      // Arrange
      useContentAsString(TEST_MAP_A1_A2_JSON);

      // Act
      var result = testAnswer.answerAsMap(Integer.class, TestObjectSimple.class);

      // Assert
      assertThat(result, is(TEST_MAP_A1_A2));
    }

    @Test
    @SneakyThrows
    void GIVEN_null_WHEN_answerAsMap_THEN_return_nullValue() {
      // Arrange
      useContentAsString(null);

      // Act
      var result = testAnswer.answerAsMap(Integer.class, TestObjectSimple.class);

      // Assert
      assertThat(result, is(nullValue()));
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_answerAsMap_THEN_throw_TestAnswerException() {
      // Arrange
      useContentAsStringException();

      // Act & Assert
      assertThrows(
          TestAnswerException.class,
          () -> testAnswer.answerAsMap(Integer.class, TestObjectSimple.class));
    }

    @Test
    @SneakyThrows
    @SuppressWarnings("all")
    void GIVEN_null_as_keyType_WHEN_answerAsMap_THEN_throw_TestAnswerException() {
      // Act & Assert
      assertThrows(
          NullPointerException.class, () -> testAnswer.answerAsMap(null, TestObjectSimple.class));
    }

    @Test
    @SneakyThrows
    @SuppressWarnings("all")
    void GIVEN_null_as_valueType_WHEN_answerAsMap_THEN_throw_TestAnswerException() {
      // Act & Assert
      assertThrows(NullPointerException.class, () -> testAnswer.answerAsMap(Integer.class, null));
    }
  }

  @Nested
  class answerHeader {

    @Test
    @SneakyThrows
    void WHEN_answerHeader_THEN_return_expected_header_value() {
      // Arrange
      useHeader();

      // Act
      var resultHeader = testAnswer.answerHeader(TEST_HEADER_KEY_1);

      // Assert
      assertThat(resultHeader, is(TEST_HEADER_VALUE_1));
    }
  }

  @Nested
  @SuppressWarnings("all")
  class answerAsVoid {

    @Test
    @SneakyThrows
    void WHEN_answerAsVoid_THEN_verifyNoInteractions() {
      // Arrange
      var mockTestRequestStrategy = Mockito.mock(TestRequestBaseStrategy.class);
      var mockTestRequestStrategyFactory =
          MockTestRequestStrategyFactory.mockTestRequestStrategyFactory(mockTestRequestStrategy);

      when(mockTestRequestStrategy.apply(TEST_REQUEST_DTO)).thenReturn(mockRequestBuilder);

      // Act
      testAnswer.answerVoid();

      // Assert
      verify(testRequestBean.mvc()).perform(mockRequestBuilder);
      mockTestRequestStrategyFactory.close();
    }
  }
}
