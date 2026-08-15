package io.github.co_mmer.aaamockmvc.ej.test.web.internal.mockmvc.execute;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.mockmvc.model.Request;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.mockmvc.request.MockMvcRequestMapper;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.mockmvc.result.MockMvcResultMapper;
import java.util.Objects;
import org.springframework.test.web.servlet.MockMvc;

public final class MockMvcExecutor {

  private final MockMvc mockMvc;

  public MockMvcExecutor(MockMvc mockMvc) {
    this.mockMvc = Objects.requireNonNull(mockMvc, "mockMvc must not be null");
  }

  public MockMvcExecutionResult execute(Request request) {
    try {
      var requestBuilder = MockMvcRequestMapper.map(request);
      var mvcResult = mockMvc.perform(requestBuilder).andReturn();
      return MockMvcResultMapper.map(mvcResult);
    } catch (Exception e) {
      throw new MockMvcExecutionException(e.getMessage());
    }
  }
}
