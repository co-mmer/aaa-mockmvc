package io.github.co_mmer.aaamockmvc.ej.test.web.act;

import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestDataRequestDto.TEST_REQUEST_DTO;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.co_mmer.aaamockmvc.ej.test.web.act.exception.TestActException;
import io.github.co_mmer.aaamockmvc.ej.test.web.act.strategy.TestRequestBaseStrategy;
import io.github.co_mmer.aaamockmvc.ej.test.web.act.strategy.TestRequestStrategyFactory;
import io.github.co_mmer.aaamockmvc.ej.test.web.answer.TestAnswerImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.TestAssertImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.request.context.TestRequestBean;
import io.github.co_mmer.aaamockmvc.ej.test.web.request.context.TestRequestContext;
import io.github.co_mmer.aaamockmvc.ej.testdata.MockTestRequestStrategyFactory;
import io.github.co_mmer.aaamockmvc.ej.testdata.testsetup.MockMvcSetup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;

class TestActImplTest {

  private MockMvc mvc;
  private MockMvcSetup setup;
  private TestActImpl testAct;
  private TestRequestContext testRequestContext;

  @BeforeEach
  void setUp() {
    this.mvc = mock(MockMvc.class);
    this.setup = new MockMvcSetup(this.mvc);
    var testRequestConfig = new TestRequestBean(this.mvc, new ObjectMapper());

    this.testRequestContext = new TestRequestContext(TEST_REQUEST_DTO, testRequestConfig);
    this.testAct = new TestActImpl(this.testRequestContext);
  }

  @Test
  void WHEN_actPerform_THEN_TestRequestStrategyFactory_resolve_is_called() {
    // Arrange
    var mockTestRequestStrategyFactory =
        MockTestRequestStrategyFactory.mockTestRequestStrategyFactory();

    // Act
    this.testAct.actPerform();

    // Assert
    mockTestRequestStrategyFactory.verify(
        () -> TestRequestStrategyFactory.resolve(TEST_REQUEST_DTO.getType()));

    mockTestRequestStrategyFactory.close();
  }

  @Test
  void WHEN_actPerform_THEN_strategy_apply_is_called() {
    // Arrange
    var mockTestRequestStrategy = Mockito.mock(TestRequestBaseStrategy.class);
    var mockTestRequestStrategyFactory =
        MockTestRequestStrategyFactory.mockTestRequestStrategyFactory(mockTestRequestStrategy);

    // Act
    this.testAct.actPerform();

    // Assert
    verify(mockTestRequestStrategy).apply(TEST_REQUEST_DTO);
    mockTestRequestStrategyFactory.close();
  }

  @Test
  void WHEN_asserts_THEN_return_expected_class() throws Exception {
    // Arrange
    this.setup.mockGetHeader();
    this.testAct = new TestActImpl(this.testRequestContext);

    // Act
    var asserts = this.testAct.asserts();

    // Assert
    assertThat(asserts.getClass(), is(TestAssertImpl.class));
  }

  @Test
  void GIVEN_exception_WHEN_asserts_THEN_throw_exception() throws Exception {
    // Arrange
    this.setup.mockGetHeader();
    when(this.mvc.perform(any())).thenThrow(Exception.class);
    this.testAct = new TestActImpl(this.testRequestContext);

    // Act
    var exception = assertThrows(Exception.class, () -> this.testAct.asserts());

    // Assert
    assertThat(exception.getClass(), is(TestActException.class));
  }

  @Test
  void WHEN_answer_THEN_return_expected_class() throws Exception {
    // Arrange
    this.setup.mockGetHeader();
    this.testAct = new TestActImpl(this.testRequestContext);

    // Act
    var answer = this.testAct.answer();

    // Assert
    assertThat(answer.getClass(), is(TestAnswerImpl.class));
  }
}
