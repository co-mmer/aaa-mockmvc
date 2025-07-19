package io.github.co_mmer.aaamockmvc.ej.test.web.internal.act.strategy;

import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestArrange.ARRANGE_POST;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestArrange.ARRANGE_POST_FILES;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.QUERY_KEY_1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.QUERY_KEY_2;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.QUERY_VALUE_1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.QUERY_VALUE_2;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.TEST_HEAD_KEY_1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.TEST_HEAD_VALUE_1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.TEST_URI;
import static javax.swing.text.html.FormSubmitEvent.MethodType.POST;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartHttpServletRequest;
import org.springframework.mock.web.MockServletContext;

class TestRequestPostStrategyTest {

  @Test
  void WHEN_apply_THEN_buildsRequest_withUrlQueryAndHeaders() {
    // Arrange
    var strategy = new TestRequestPostStrategy();

    // Act
    var builder = strategy.apply(ARRANGE_POST);
    var request = builder.buildRequest(new MockServletContext());

    // Assert
    assertThat(request.getMethod(), is(POST.toString()));
    assertThat(request.getRequestURI(), is(TEST_URI.toString()));

    assertThat(request.getParameter(QUERY_KEY_1), is(QUERY_VALUE_1));
    assertThat(request.getParameter(QUERY_KEY_2), is(QUERY_VALUE_2));

    assertThat(request.getHeader("Accept"), is("application/json, application/xml"));
    assertThat(request.getHeader("Content-Type"), is("application/json"));
    assertThat(request.getHeader(TEST_HEAD_KEY_1), is(TEST_HEAD_VALUE_1));
  }

  @Test
  void WHEN_apply_withFiles_THEN_buildsMultipartPatch_withFilesAndHeaders() {
    // Arrange
    var strategy = new TestRequestPostStrategy();

    // Act
    var builder = strategy.apply(ARRANGE_POST_FILES);
    var request = builder.buildRequest(new MockServletContext());

    assertThat(request.getRequestURI(), is(TEST_URI.toString()));

    assertThat(request.getParameter(QUERY_KEY_1), is(QUERY_VALUE_1));
    assertThat(request.getParameter(QUERY_KEY_2), is(QUERY_VALUE_2));

    assertThat(request, instanceOf(MockMultipartHttpServletRequest.class));
    var multipart = (MockMultipartHttpServletRequest) request;

    assertThat(multipart.getFiles("files"), hasSize(2));
    assertThat(request.getContentType(), startsWith("multipart/"));
    assertThat(request.getHeader("Accept"), is("application/json, application/xml"));
    assertThat(request.getHeader("Content-Type"), is(MULTIPART_FORM_DATA.toString()));
    assertThat(request.getHeader(TEST_HEAD_KEY_1), is(TEST_HEAD_VALUE_1));
  }
}
