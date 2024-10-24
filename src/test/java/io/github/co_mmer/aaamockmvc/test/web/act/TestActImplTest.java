package io.github.co_mmer.aaamockmvc.test.web.act;

import static io.github.co_mmer.aaamockmvc.testdata.testutil.TestBody.TEST_BODY_JSON;
import static io.github.co_mmer.aaamockmvc.testdata.testutil.TestDataRequestDto.TEST_REQUEST_DTO;
import static io.github.co_mmer.aaamockmvc.testdata.testutil.TestHeader.TEST_HEADER_KEY_1;
import static io.github.co_mmer.aaamockmvc.testdata.testutil.TestHeader.TEST_HEADER_VALUE_1;
import static io.github.co_mmer.aaamockmvc.testdata.testutil.TestValue.TEST_BYTE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.co_mmer.aaamockmvc.test.web.act.exception.TestActException;
import io.github.co_mmer.aaamockmvc.test.web.act.strategy.TestRequestBaseStrategy;
import io.github.co_mmer.aaamockmvc.test.web.act.strategy.TestRequestStrategyFactory;
import io.github.co_mmer.aaamockmvc.test.web.asserts.TestAssertImpl;
import io.github.co_mmer.aaamockmvc.test.web.request.context.TestRequestBean;
import io.github.co_mmer.aaamockmvc.test.web.request.context.TestRequestContext;
import io.github.co_mmer.aaamockmvc.testdata.MockTestRequestStrategyFactory;
import io.github.co_mmer.aaamockmvc.testdata.testsetup.MockMvcSetup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

class TestActImplTest {

  private MockMvc mvc;
  private MockMvcSetup setup;
  private MockHttpServletRequestBuilder mockRequestBuilder;
  private TestActImpl testAct;
  private TestRequestContext testRequestContext;

  @BeforeEach
  void setUp() {
    this.mvc = mock(MockMvc.class);
    this.setup = new MockMvcSetup(this.mvc);
    var testRequestConfig = new TestRequestBean(this.mvc, new ObjectMapper());
    this.mockRequestBuilder = mock(MockHttpServletRequestBuilder.class);
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
  void WHEN_resultActions_THEN_mvc_perform_is_called() throws Exception {
    // Arrange
    var mockTestRequestStrategy = mock(TestRequestBaseStrategy.class);
    var mockTestRequestStrategyFactory =
        MockTestRequestStrategyFactory.mockTestRequestStrategyFactory(mockTestRequestStrategy);

    when(mockTestRequestStrategy.apply(TEST_REQUEST_DTO)).thenReturn(this.mockRequestBuilder);

    this.testAct.actPerform();

    // Act
    this.testAct.resultActions();

    // Assert
    verify(this.mvc).perform(this.mockRequestBuilder);
    mockTestRequestStrategyFactory.close();
  }

  @Test
  void GIVEN_exception_WHEN_resultActions_THEN_throw_TestActException() throws Exception {
    // Arrange
    when(this.mvc.perform(any())).thenThrow(Exception.class);

    // Act
    var exception = assertThrows(Exception.class, () -> this.testAct.resultActions());

    // Assert
    assertThat(exception, is(notNullValue()));
    assertThat(exception.getClass(), is(TestActException.class));
  }

  @Test
  void WHEN_resultVoid_THEN_mvc_perform_is_called() throws Exception {
    // Arrange
    var mockTestRequestStrategy = mock(TestRequestBaseStrategy.class);
    var mockTestRequestStrategyFactory =
        MockTestRequestStrategyFactory.mockTestRequestStrategyFactory(mockTestRequestStrategy);

    when(mockTestRequestStrategy.apply(TEST_REQUEST_DTO)).thenReturn(this.mockRequestBuilder);
    this.testAct.actPerform();

    // Act
    this.testAct.resultVoid();

    // Assert
    verify(this.mvc).perform(this.mockRequestBuilder);
    mockTestRequestStrategyFactory.close();
  }

  @Test
  void GIVEN_exception_WHEN_resultVoid_THEN_throw_TestActException() throws Exception {
    // Arrange
    when(this.mvc.perform(any())).thenThrow(Exception.class);

    // Act
    var exception = assertThrows(Exception.class, () -> this.testAct.resultVoid());

    // Assert
    assertThat(exception, is(notNullValue()));
    assertThat(exception.getClass(), is(TestActException.class));
  }

  @Test
  void WHEN_resultAsString_THEN_mvc_getContentAsString_is_called() throws Exception {
    // Arrange
    this.setup.mockGetContentAsString();
    this.testAct = new TestActImpl(this.testRequestContext);

    // Act
    this.testAct.resultAsString();

    // Assert
    this.setup.verifyGetContentAsString();
  }

  @Test
  void WHEN_resultAsString_THEN_return_expected_value() throws Exception {
    // Arrange
    this.setup.mockGetContentAsString();
    this.testAct = new TestActImpl(this.testRequestContext);

    // Act
    var result = this.testAct.resultAsString();

    // Assert
    assertThat(result, is(TEST_BODY_JSON));
  }

  @Test
  void GIVEN_exception_WHEN_resultAsString_THEN_throw_TestActException() throws Exception {
    // Arrange
    this.setup.mockThrowGetContentAsString();
    this.testAct = new TestActImpl(this.testRequestContext);

    // Act
    var exception = assertThrows(Exception.class, () -> this.testAct.resultAsString());

    // Assert
    assertThat(exception, is(notNullValue()));
    assertThat(exception.getClass(), is(TestActException.class));
  }

  @Test
  void WHEN_resultAsByte_THEN_mvc_getContentAsByteArray_is_called() throws Exception {
    // Arrange
    this.setup.mockGetContentAsByteArray();
    this.testAct = new TestActImpl(this.testRequestContext);

    // Act
    this.testAct.resultAsByte();

    // Assert
    this.setup.verifyGetContentAsByteArray();
  }

  @Test
  void WHEN_resultAsByte_THEN_return_expected_value() throws Exception {
    // Arrange
    this.setup.mockGetContentAsByteArray();
    this.testAct = new TestActImpl(this.testRequestContext);

    // Act
    var result = this.testAct.resultAsByte();

    // Assert
    assertThat(result, is(TEST_BYTE));
  }

  @Test
  void WHEN_resultHeader_THEN_mvc_getContentAsByteArray_is_called() throws Exception {
    // Arrange
    this.setup.mockGetHeader();
    this.testAct = new TestActImpl(this.testRequestContext);

    // Act
    this.testAct.resultHeader(TEST_HEADER_KEY_1);

    // Assert
    this.setup.verifyGetHeader();
  }

  @Test
  void WHEN_resultHeader_THEN_return_expected_value() throws Exception {
    // Arrange
    this.setup.mockGetHeader();
    this.testAct = new TestActImpl(this.testRequestContext);

    // Act
    var result = this.testAct.resultHeader(TEST_HEADER_KEY_1);

    // Assert
    assertThat(result, is(TEST_HEADER_VALUE_1));
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
}
