package io.github.co_mmer.aaamockmvc.ej.test.web.internal.act.strategy;

import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.QUERY_KEY_1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.QUERY_KEY_2;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.QUERY_VALUE_1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.QUERY_VALUE_2;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.TEST_HEAD_KEY_1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.TEST_HEAD_VALUE_1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.TEST_URI;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_XML;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestArrangeResult;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

class TestRequestDeleteStrategyTest {

  @Test
  void WHEN_apply_THEN_buildsRequest_withUrlQueryAndHeaders() {
    // Arrange
    var context = createTestArrangeResult();
    var strategy = new TestRequestDeleteStrategy();

    // Act
    MockHttpServletRequestBuilder builder = strategy.apply(context);
    var request = builder.buildRequest(new MockServletContext());

    // Assert
    assertThat(request.getMethod(), is(DELETE.toString()));
    assertThat(request.getRequestURI(), is(TEST_URI.toString()));

    assertThat(request.getParameter(QUERY_KEY_1), is(QUERY_VALUE_1));
    assertThat(request.getParameter(QUERY_KEY_2), is(QUERY_VALUE_2));

    assertThat(request.getHeader("Accept"), is("application/json, application/xml"));
    assertThat(request.getHeader("Content-Type"), is("application/json"));
    assertThat(request.getHeader(TEST_HEAD_KEY_1), is(TEST_HEAD_VALUE_1));
  }

  private static TestArrangeResult createTestArrangeResult() {
    var context = new TestArrangeResult();

    context.getUrl().setMethod(DELETE);
    context.getUrl().setUri(TEST_URI);
    context.getUrl().getQuery().put(QUERY_KEY_1, QUERY_VALUE_1);
    context.getUrl().getQuery().put(QUERY_KEY_2, QUERY_VALUE_2);

    context.getHead().setAccepts(List.of(APPLICATION_JSON, APPLICATION_XML));
    context.getHead().setContentType(APPLICATION_JSON);
    context.getHead().setKeyValue(Map.of(TEST_HEAD_KEY_1, List.of(TEST_HEAD_VALUE_1)));
    return context;
  }
}
