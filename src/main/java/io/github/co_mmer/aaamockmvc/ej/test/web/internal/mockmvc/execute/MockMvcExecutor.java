package io.github.co_mmer.aaamockmvc.ej.test.web.internal.mockmvc.execute;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.mockmvc.request.MockMvcRequestMapper;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.mockmvc.result.MockMvcResultMapper;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestArrangeResult;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.test.web.servlet.MockMvc;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class MockMvcExecutor {

  public static MockMvcExecutionResult execute(MockMvc mockMvc, TestArrangeResult request) {
    try {
      var requestBuilder = MockMvcRequestMapper.map(request);
      var mvcResult = mockMvc.perform(requestBuilder).andReturn();
      return MockMvcResultMapper.map(mvcResult);
    } catch (Exception e) {
      throw new MockMvcExecutionException(e.getMessage());
    }
  }
}
