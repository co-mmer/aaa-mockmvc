package io.github.co_mmer.aaamockmvc.ej.test.web.internal.mockmvc.execute;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.metadata.Since;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.mockmvc.request.MockMvcRequestMapper;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.Request;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.test.web.servlet.MockMvc;

@Since("2.1.0")
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class MockMvcExecutor {

  @Since("2.1.0")
  public static MockMvcExecutionResult execute(MockMvc mockMvc, Request request) {
    try {
      var requestBuilder = MockMvcRequestMapper.map(request);
      var mvcResult = mockMvc.perform(requestBuilder).andReturn();
      return MockMvcExecutionResultMapper.map(mvcResult);
    } catch (Exception e) {
      throw new MockMvcExecutionException(e.getMessage());
    }
  }
}
