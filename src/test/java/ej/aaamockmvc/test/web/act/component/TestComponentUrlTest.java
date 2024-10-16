package ej.aaamockmvc.test.web.act.component;

import static ej.aaamockmvc.test.testdata.testutil.TestDataRequestUrlDto.TEST_REQUEST_URL_PARAM;
import static ej.aaamockmvc.test.testdata.testutil.TestDataRequestUrlDto.TEST_REQUEST_URL_PARAM_EMPTY;
import static ej.aaamockmvc.test.testdata.testutil.TestDataRequestUrlDto.TEST_REQUEST_URL_PARAM_NULL;
import static ej.aaamockmvc.test.testdata.testutil.TestParameter.TEST_PARAM_KEY_1;
import static ej.aaamockmvc.test.testdata.testutil.TestParameter.TEST_PARAM_VALUE_1;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import ej.aaamockmvc.test.web.act.strategy.component.TestComponentUrl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

class TestComponentUrlTest {

  private MockHttpServletRequestBuilder mockRequestBuilder;

  @BeforeEach
  void setUp() {
    this.mockRequestBuilder = mock(MockHttpServletRequestBuilder.class);
  }

  @Test
  void GIVEN_param_null_WHEN_apply_THEN_verifyNoInteractions() {
    // Act
    TestComponentUrl.apply(this.mockRequestBuilder, TEST_REQUEST_URL_PARAM_NULL);

    // Assert
    verifyNoInteractions(this.mockRequestBuilder);
  }

  @Test
  void GIVEN_param_empty_WHEN_apply_THEN_verifyNoInteractions() {
    // Act
    TestComponentUrl.apply(this.mockRequestBuilder, TEST_REQUEST_URL_PARAM_EMPTY);

    // Assert
    verifyNoInteractions(this.mockRequestBuilder);
  }

  @Test
  void GIVEN_param_1_WHEN_apply_THEN_verify_param() {
    // Act
    TestComponentUrl.apply(this.mockRequestBuilder, TEST_REQUEST_URL_PARAM);

    // Assert
    verify(this.mockRequestBuilder).param(TEST_PARAM_KEY_1, TEST_PARAM_VALUE_1);
    verifyNoMoreInteractions(this.mockRequestBuilder);
  }
}
