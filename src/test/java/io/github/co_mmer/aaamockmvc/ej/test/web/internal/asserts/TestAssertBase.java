package io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts;

import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestMockHttpServletResponse.mockGetContentAsSByteException;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestMockHttpServletResponse.mockGetContentAsStringException;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.TEST_HEAD_KEY_1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.TEST_HEAD_VALUE_1;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestAAAContext;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestActResult;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestAnswerResult;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestAssertResult;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

public abstract class TestAssertBase {

  protected ResultActions actions;
  protected MockHttpServletResponse response;
  private TestAAAContext context;

  // todo aufräumen
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
    when(this.actions.andExpect(any())).thenThrow(new AssertionError("test"));
  }

  protected void useResultException() throws Exception {
    when(this.actions.andExpect(any())).thenThrow(new Exception("test"));
  }

  protected void useHeader() {
    this.response.setHeader(TEST_HEAD_KEY_1, TEST_HEAD_VALUE_1);
  }

  // ---

  protected void useContext(TestAAAContext context) {
    this.context = context;
  }

  protected void useActResult(int status) {
    this.context.setActResult(new TestActResult(status, Collections.emptyMap(), null, UTF_8));
  }

  protected void useActResult(HttpStatus status) {
    this.useActResult(status.value());
  }

  protected void useActResult(byte[] content) {
    this.context.setActResult(new TestActResult(-1, Collections.emptyMap(), content, UTF_8));
  }

  protected void useActResult(@NonNull String content) {
    var actResult = new TestActResult(-1, Collections.emptyMap(), content.getBytes(), UTF_8);
    this.context.setActResult(actResult);
  }

  protected void useActResult(String key, String... value) {
    this.context.setActResult(
        new TestActResult(-1, Map.of(key, Arrays.asList(value)), null, UTF_8));
  }

  protected <T> void useAssertResult(T actual) {
    this.context.setAssertResult(new TestAssertResult<>(actual));
  }

  protected <T> void useAnswerResult(T actual) {
    this.context.setAnswerResult(new TestAnswerResult<>(actual));
  }
}
