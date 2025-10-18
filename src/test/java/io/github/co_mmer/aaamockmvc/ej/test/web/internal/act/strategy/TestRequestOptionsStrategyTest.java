package io.github.co_mmer.aaamockmvc.ej.test.web.internal.act.strategy;

import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.BASE_URI;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.HEADER_KEY_AUTH;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.HEADER_VALUE_TOKEN;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.QUERY_KEY_PAGE;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.QUERY_KEY_SEARCH;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.QUERY_VALUE_PAGE_NUMBER;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.QUERY_VALUE_SEARCH_TERM;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.http.HttpMethod.OPTIONS;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_XML;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestArrangeResult;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

class TestRequestOptionsStrategyTest {

  @Test
  void WHEN_apply_THEN_buildsRequest_withUrlQueryAndHeaders() {
    // Arrange
    var context = createTestArrangeResult();
    var strategy = new TestRequestOptionsStrategy();

    // Act
    MockHttpServletRequestBuilder builder = strategy.apply(context);
    var request = builder.buildRequest(new MockServletContext());

    // Assert
    assertThat(request.getMethod(), is(OPTIONS.toString()));
    assertThat(request.getRequestURI(), is(BASE_URI.toString()));

    assertThat(request.getParameter(QUERY_KEY_SEARCH), is(QUERY_VALUE_SEARCH_TERM));
    assertThat(request.getParameter(QUERY_KEY_PAGE), is(QUERY_VALUE_PAGE_NUMBER));

    assertThat(request.getHeader("Accept"), is("application/json, application/xml"));
    assertThat(request.getHeader("Content-Type"), is("application/json"));
    assertThat(request.getHeader(HEADER_KEY_AUTH), is(HEADER_VALUE_TOKEN));
  }

  private static TestArrangeResult createTestArrangeResult() {
    var context = new TestArrangeResult();

    context.getUrl().setMethod(OPTIONS);
    context.getUrl().setUri(BASE_URI);
    context.getUrl().getQuery().put(QUERY_KEY_SEARCH, QUERY_VALUE_SEARCH_TERM);
    context.getUrl().getQuery().put(QUERY_KEY_PAGE, QUERY_VALUE_PAGE_NUMBER);

    context.getHead().setAccepts(List.of(APPLICATION_JSON, APPLICATION_XML));
    context.getHead().setContentType(APPLICATION_JSON);
    context.getHead().setKeyValue(Map.of(HEADER_KEY_AUTH, List.of(HEADER_VALUE_TOKEN)));
    return context;
  }
}
