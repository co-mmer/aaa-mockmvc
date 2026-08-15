package io.github.co_mmer.aaamockmvc.ej.test.web.internal.mockmvc.result;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.mockmvc.execute.MockMvcExecutionException;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.mockmvc.execute.MockMvcExecutionResult;
import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MvcResult;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class MockMvcResultMapper {

  public static MockMvcExecutionResult map(MvcResult source) {
    var response = source.getResponse();
    return new MockMvcExecutionResult(
        response.getStatus(),
        getContentAsString(response),
        response.getContentAsByteArray(),
        mapHeaders(response));
  }

  private static String getContentAsString(MockHttpServletResponse response) {
    try {
      return response.getContentAsString();
    } catch (UnsupportedEncodingException e) {
      throw new MockMvcExecutionException("Could not read response content as String", e);
    }
  }

  private static Map<String, List<String>> mapHeaders(MockHttpServletResponse response) {
    Map<String, List<String>> headers = new LinkedHashMap<>();

    for (String name : response.getHeaderNames()) {
      headers.put(name, List.copyOf(response.getHeaders(name)));
    }

    return Map.copyOf(headers);
  }
}
