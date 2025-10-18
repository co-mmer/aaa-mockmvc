package io.github.co_mmer.aaamockmvc.ej.test.web.internal.act.strategy;

import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestArrange.ARRANGE_PATCH;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestArrange.ARRANGE_PATCH_FILES;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.BASE_URI;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.HEADER_KEY_AUTH;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.HEADER_VALUE_TOKEN;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.QUERY_KEY_PAGE;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.QUERY_KEY_SEARCH;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.QUERY_VALUE_PAGE_NUMBER;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.QUERY_VALUE_SEARCH_TERM;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;
import static org.springframework.http.HttpMethod.PATCH;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartHttpServletRequest;
import org.springframework.mock.web.MockServletContext;

class TestRequestPatchStrategyTest {

  @Test
  void WHEN_apply_THEN_buildsRequest_withUrlQueryAndHeaders() {
    // Arrange
    var strategy = new TestRequestPatchStrategy();

    // Act
    var builder = strategy.apply(ARRANGE_PATCH);
    var request = builder.buildRequest(new MockServletContext());

    // Assert
    assertThat(request.getMethod(), is(PATCH.toString()));
    assertThat(request.getRequestURI(), is(BASE_URI.toString()));

    assertThat(request.getParameter(QUERY_KEY_SEARCH), is(QUERY_VALUE_SEARCH_TERM));
    assertThat(request.getParameter(QUERY_KEY_PAGE), is(QUERY_VALUE_PAGE_NUMBER));

    assertThat(request.getHeader("Accept"), is("application/json, application/xml"));
    assertThat(request.getHeader("Content-Type"), is("application/json"));
    assertThat(request.getHeader(HEADER_KEY_AUTH), is(HEADER_VALUE_TOKEN));
  }

  @Test
  void WHEN_apply_withFiles_THEN_buildsMultipartPatch_withFilesAndHeaders() {
    // Arrange
    var strategy = new TestRequestPatchStrategy();

    // Act
    var builder = strategy.apply(ARRANGE_PATCH_FILES);
    var request = builder.buildRequest(new MockServletContext());

    assertThat(request.getRequestURI(), is(BASE_URI.toString()));

    assertThat(request.getParameter(QUERY_KEY_SEARCH), is(QUERY_VALUE_SEARCH_TERM));
    assertThat(request.getParameter(QUERY_KEY_PAGE), is(QUERY_VALUE_PAGE_NUMBER));

    assertThat(request, instanceOf(MockMultipartHttpServletRequest.class));
    var multipart = (MockMultipartHttpServletRequest) request;

    assertThat(multipart.getFiles("files"), hasSize(2));
    assertThat(request.getContentType(), startsWith("multipart/"));
    assertThat(request.getHeader("Accept"), is("application/json, application/xml"));
    assertThat(request.getHeader("Content-Type"), is(MULTIPART_FORM_DATA.toString()));
    assertThat(request.getHeader(HEADER_KEY_AUTH), is(HEADER_VALUE_TOKEN));
  }
}
