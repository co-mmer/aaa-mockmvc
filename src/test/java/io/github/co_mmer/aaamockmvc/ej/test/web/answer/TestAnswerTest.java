package io.github.co_mmer.aaamockmvc.ej.test.web.answer;

import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestBody.TEST_BODY_JSON;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestDataRequestDto.TEST_REQUEST_DTO;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestHeader.TEST_HEADER_KEY_1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestHeader.TEST_HEADER_VALUE_1;
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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

class TestAnswerTest {

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
  void GIVEN_exception_WHEN_answerVoid_THEN_throw_TestResultException() throws Exception {
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
