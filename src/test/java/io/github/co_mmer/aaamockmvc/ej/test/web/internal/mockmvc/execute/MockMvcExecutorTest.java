package io.github.co_mmer.aaamockmvc.ej.test.web.internal.mockmvc.execute;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.mockmvc.request.MockMvcRequestMapper;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.Request;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;

class MockMvcExecutorTest {

  private static final String ANY_EXCEPTION_MESSAGE = "execution failed";

  private MockMvc mockMvc;
  private Request request;
  private RequestBuilder requestBuilder;
  private ResultActions resultActions;
  private MvcResult mvcResult;
  private MockMvcExecutionResult expectedResult;

  private MockedStatic<MockMvcRequestMapper> requestMapper;
  private MockedStatic<MockMvcExecutionResultMapper> resultMapper;

  @BeforeEach
  void setUp() {
    this.mockMvc = mock(MockMvc.class);
    this.request = mock(Request.class);
    this.requestBuilder = mock(RequestBuilder.class);
    this.resultActions = mock(ResultActions.class);
    this.mvcResult = mock(MvcResult.class);
    this.expectedResult = mock(MockMvcExecutionResult.class);

    this.requestMapper = mockStatic(MockMvcRequestMapper.class);
    this.resultMapper = mockStatic(MockMvcExecutionResultMapper.class);
  }

  @AfterEach
  void tearDown() {
    this.resultMapper.close();
    this.requestMapper.close();
  }

  @Test
  @SneakyThrows
  void GIVEN_successful_execution_WHEN_execute_THEN_return_execution_result() {
    // Arrange
    givenSuccessfulExecution();

    // Act
    var result = MockMvcExecutor.execute(this.mockMvc, this.request);

    // Assert
    this.requestMapper.verify(() -> MockMvcRequestMapper.map(this.request));
    verify(this.mockMvc).perform(this.requestBuilder);
    verify(this.resultActions).andReturn();
    this.resultMapper.verify(() -> MockMvcExecutionResultMapper.map(this.mvcResult));
    assertThat(result, is(this.expectedResult));
  }

  @Test
  void GIVEN_failed_execution_WHEN_execute_THEN_throw_execution_exception() {
    // Arrange
    givenFailedExecution(ANY_EXCEPTION_MESSAGE);

    // Act
    var exception =
        assertThrows(
            MockMvcExecutionException.class,
            () -> MockMvcExecutor.execute(this.mockMvc, this.request));

    // Assert
    assertThat(exception.getMessage(), is(ANY_EXCEPTION_MESSAGE));
  }

  @SneakyThrows
  private void givenSuccessfulExecution() {
    this.requestMapper
        .when(() -> MockMvcRequestMapper.map(this.request))
        .thenReturn(this.requestBuilder);

    when(this.mockMvc.perform(this.requestBuilder)).thenReturn(this.resultActions);
    when(this.resultActions.andReturn()).thenReturn(this.mvcResult);

    this.resultMapper
        .when(() -> MockMvcExecutionResultMapper.map(this.mvcResult))
        .thenReturn(this.expectedResult);
  }

  @SneakyThrows
  private void givenFailedExecution(String message) {
    this.requestMapper
        .when(() -> MockMvcRequestMapper.map(this.request))
        .thenReturn(this.requestBuilder);

    when(this.mockMvc.perform(this.requestBuilder))
        .thenThrow(new Exception(message));
  }
}