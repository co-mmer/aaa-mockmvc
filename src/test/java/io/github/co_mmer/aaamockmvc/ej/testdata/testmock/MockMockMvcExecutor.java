package io.github.co_mmer.aaamockmvc.ej.testdata.testmock;

import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_A1_JSON;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.mockmvc.execute.MockMvcExecutionResult;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.mockmvc.execute.MockMvcExecutor;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.Request;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MockMockMvcExecutor {

  public static MockedStatic<MockMvcExecutor> mockExecutor() {
    var mockResult = mockResult();

    var executor = Mockito.mockStatic(MockMvcExecutor.class);
    executor
        .when(() -> MockMvcExecutor.execute(any(MockMvc.class), any(Request.class)))
        .thenReturn(mockResult);

    return executor;
  }

  private static MockMvcExecutionResult mockResult() {
    var result = Mockito.mock(MockMvcExecutionResult.class);
    when(result.status()).thenReturn(200);
    when(result.contentAsString()).thenReturn(TEST_A1_JSON);
    return result;
  }
}
