package ej.test.aaamockmvc.request;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

import ej.test.aaamockmvc.context.TestRequestConfig;
import ej.test.aaamockmvc.context.TestRequestContext;
import ej.test.aaamockmvc.context.TestRequestContextBuilder;
import ej.test.aaamockmvc.request.model.TestRequestDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public abstract class TestRequestBase {

  @Mock protected TestRequestConfig config;
  @Mock protected TestRequestContextBuilder mockContextBuilder;
  @Mock protected TestRequestContext mockContext;
  protected MockedStatic<TestRequestContextBuilder> mockedStatic;

  @BeforeEach
  void setUp() {
    when(this.mockContextBuilder.withTestRequestConfig(any(TestRequestConfig.class)))
        .thenReturn(this.mockContextBuilder);
    when(this.mockContextBuilder.withTestRequest(any(TestRequestDto.class)))
        .thenReturn(this.mockContextBuilder);
    when(this.mockContextBuilder.build()).thenReturn(this.mockContext);

    this.mockedStatic = mockStatic(TestRequestContextBuilder.class);
    this.mockedStatic
        .when(TestRequestContextBuilder::getInstance)
        .thenReturn(this.mockContextBuilder);
  }

  @AfterEach
  void clean() {
    this.mockedStatic.close();
  }
}
