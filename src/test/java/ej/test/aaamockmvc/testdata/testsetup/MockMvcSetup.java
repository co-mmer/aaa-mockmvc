package ej.test.aaamockmvc.testdata.testsetup;

import static ej.test.aaamockmvc.testdata.testutil.TestBody.TEST_BODY_JSON;
import static ej.test.aaamockmvc.testdata.testutil.TestHeader.TEST_HEADER_KEY_1;
import static ej.test.aaamockmvc.testdata.testutil.TestHeader.TEST_HEADER_VALUE_1;
import static ej.test.aaamockmvc.testdata.testutil.TestValue.TEST_BYTE;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.UnsupportedEncodingException;
import lombok.Getter;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

@Getter
public final class MockMvcSetup {

  private final MockHttpServletResponse mockMockHttpServletResponse;
  private final MvcResult mockMvcResult;
  private final ResultActions mockResultActions;
  private final MockMvc mvc;

  public MockMvcSetup(MockMvc mvc) {
    this.mvc = mvc;
    this.mockMockHttpServletResponse = mock(MockHttpServletResponse.class);
    this.mockMvcResult = mock(MvcResult.class);
    this.mockResultActions = mock(ResultActions.class);
  }

  public void mockGetHeader() throws Exception {
    when(this.mockMockHttpServletResponse.getHeader(TEST_HEADER_KEY_1))
        .thenReturn(TEST_HEADER_VALUE_1);
    when(this.mockMvcResult.getResponse()).thenReturn(this.mockMockHttpServletResponse);
    when(this.mockResultActions.andReturn()).thenReturn(this.mockMvcResult);
    when(this.mvc.perform(any())).thenReturn(this.mockResultActions);
  }

  public void mockGetContentAsString() throws Exception {
    when(this.mockMockHttpServletResponse.getContentAsString()).thenReturn(TEST_BODY_JSON);
    when(this.mockMvcResult.getResponse()).thenReturn(this.mockMockHttpServletResponse);
    when(this.mockResultActions.andReturn()).thenReturn(this.mockMvcResult);
    when(this.mvc.perform(any())).thenReturn(this.mockResultActions);
  }

  public void mockThrowGetContentAsString() throws Exception {
    when(this.mockMockHttpServletResponse.getContentAsString())
        .thenThrow(UnsupportedEncodingException.class);
    when(this.mockMvcResult.getResponse()).thenReturn(this.mockMockHttpServletResponse);
    when(this.mockResultActions.andReturn()).thenReturn(this.mockMvcResult);
    when(this.mvc.perform(any())).thenReturn(this.mockResultActions);
  }

  public void mockGetContentAsByteArray() throws Exception {
    when(this.mockMockHttpServletResponse.getContentAsByteArray()).thenReturn(TEST_BYTE);
    when(this.mockMvcResult.getResponse()).thenReturn(this.mockMockHttpServletResponse);
    when(this.mockResultActions.andReturn()).thenReturn(this.mockMvcResult);
    when(this.mvc.perform(any())).thenReturn(this.mockResultActions);
  }

  public void verifyGetContentAsString() throws Exception {
    verify(this.mockMockHttpServletResponse).getContentAsString();
  }

  public void verifyGetHeader() {
    verify(this.mockMockHttpServletResponse).getHeader(TEST_HEADER_KEY_1);
  }

  public void verifyGetContentAsByteArray() {
    verify(this.mockMockHttpServletResponse).getContentAsByteArray();
  }
}
