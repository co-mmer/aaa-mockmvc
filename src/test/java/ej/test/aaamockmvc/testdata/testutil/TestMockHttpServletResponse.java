package ej.test.aaamockmvc.testdata.testutil;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MvcResult;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestMockHttpServletResponse {

  public static MvcResult mockGetContentAsStringException() throws Exception {
    var mockMvcResult = mock(MvcResult.class);
    var mockResponse = mock(MockHttpServletResponse.class);
    when(mockResponse.getContentAsString()).thenThrow(new RuntimeException());
    when(mockMvcResult.getResponse()).thenReturn(mockResponse);
    return mockMvcResult;
  }

  public static MvcResult mockGetContentAsSByteException() {
    var mockMvcResult = mock(MvcResult.class);
    var mockResponse = mock(MockHttpServletResponse.class);
    when(mockResponse.getContentAsByteArray()).thenThrow(new RuntimeException());
    when(mockMvcResult.getResponse()).thenReturn(mockResponse);
    return mockMvcResult;
  }
}
