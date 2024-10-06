package ej.test.aaamockmvc.request;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

import ej.test.aaamockmvc.context.TestRequestContext;
import ej.test.aaamockmvc.context.TestRequestContextBuilder;
import ej.test.aaamockmvc.request.model.TestRequestDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(MockitoExtension.class)
public abstract class TestRequestBase {

  @Mock protected MockMvc mockMvc;
  @Mock protected TestRequestContextBuilder mockContextBuilder;
  @Mock protected TestRequestContext mockContext;
  protected MockedStatic<TestRequestContextBuilder> mockedStatic;

  @BeforeEach
  void setUp() {
    when(this.mockContextBuilder.withMockMvc(any(MockMvc.class)))
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
