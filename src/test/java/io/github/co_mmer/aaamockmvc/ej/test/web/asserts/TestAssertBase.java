package io.github.co_mmer.aaamockmvc.ej.test.web.asserts;

import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestMockHttpServletResponse.mockGetContentAsSByteException;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestMockHttpServletResponse.mockGetContentAsStringException;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.TEST_HEAD_KEY_1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.TEST_HEAD_VALUE_1;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

public abstract class TestAssertBase {

  protected ResultActions actions;
  protected MockHttpServletResponse response;

  protected void initMockServer() {
    this.actions = mock(ResultActions.class);
    this.response = new MockHttpServletResponse();

    var mvcResult = mock(MvcResult.class);
    when(mvcResult.getResponse()).thenReturn(this.response);
    when(this.actions.andReturn()).thenReturn(mvcResult);
  }

  protected void useServerWithStringException() throws Exception {
    prepareServerResponse(mockGetContentAsStringException());
  }

  protected void useServerWithByteException() {
    prepareServerResponse(mockGetContentAsSByteException());
  }

  private void prepareServerResponse(MvcResult mvcResult) {
    when(this.actions.andReturn()).thenReturn(mvcResult);
  }

  protected void useServerWithResponse(String json) throws Exception {
    this.response.getWriter().write(json);
  }

  protected void useResultAssertionError() throws Exception {
    when(this.actions.andExpect(any())).thenThrow(new AssertionError());
  }

  protected void useResultException() throws Exception {
    when(this.actions.andExpect(any())).thenThrow(new Exception());
  }

  protected void useHeader() {
    this.response.setHeader(TEST_HEAD_KEY_1, TEST_HEAD_VALUE_1);
  }
}
