package io.github.co_mmer.aaamockmvc.ej.test.web.act.strategy.builder;

import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.TEST_URI;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

import io.github.co_mmer.aaamockmvc.ej.test.web.request.model.TestRequestType;
import jakarta.servlet.ServletContext;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.request.RequestPostProcessor;

class TestRequestBuilderTest {

  private static final ServletContext SERVLET_CONTEXT = null;

  @Test
  @SuppressWarnings("ConstantConditions")
  void GIVEN_null_WHEN_get_THEN_throw_NullPointerException() {
    assertThrows(NullPointerException.class, () -> TestRequestBuilder.get(null));
  }

  @Test
  @SuppressWarnings("ConstantConditions")
  void GIVEN_url_WHEN_get_THEN_return_expected_request() {
    // Act
    var requestBuilder = TestRequestBuilder.get(TEST_URI);

    // Assert
    var request = requestBuilder.buildRequest(SERVLET_CONTEXT);
    assertThat(request.getMethod(), is(TestRequestType.GET.toString()));
  }

  @Test
  @SuppressWarnings("all")
  void GIVEN_null_WHEN_post_THEN_throw_NullPointerException() {
    assertThrows(NullPointerException.class, () -> TestRequestBuilder.post(null));
  }

  @Test
  @SuppressWarnings("ConstantConditions")
  void GIVEN_url_WHEN_post_THEN_return_expected_request() {
    // Act
    var requestBuilder = TestRequestBuilder.post(TEST_URI);

    // Assert
    var request = requestBuilder.buildRequest(SERVLET_CONTEXT);
    assertThat(request.getMethod(), is(TestRequestType.POST.toString()));
  }

  @Test
  @SuppressWarnings("ConstantConditions")
  void GIVEN_null_WHEN_postMultipart_THEN_throw_NullPointerException() {
    assertThrows(NullPointerException.class, () -> TestRequestBuilder.postMultipart(null));
  }

  @Test
  @SuppressWarnings("ConstantConditions")
  void GIVEN_url_WHEN_postMultipart_THEN_return_expected_request() {
    // Act
    var requestBuilder = TestRequestBuilder.postMultipart(TEST_URI);

    // Assert
    var request = requestBuilder.buildRequest(SERVLET_CONTEXT);
    assertThat(request.getMethod(), is(TestRequestType.POST.toString()));
  }

  @Test
  @SuppressWarnings("ConstantConditions")
  void GIVEN_null_WHEN_put_THEN_throw_NullPointerException() {
    assertThrows(NullPointerException.class, () -> TestRequestBuilder.put(null));
  }

  @Test
  @SuppressWarnings("ConstantConditions")
  void GIVEN_url_WHEN_put_THEN_return_expected_request() {
    // Act
    var requestBuilder = TestRequestBuilder.put(TEST_URI);

    // Assert
    var request = requestBuilder.buildRequest(SERVLET_CONTEXT);
    assertThat(request.getMethod(), is(TestRequestType.PUT.toString()));
  }

  @Test
  @SuppressWarnings("ConstantConditions")
  void GIVEN_null_WHEN_putMultipart_THEN_throw_NullPointerException() {
    assertThrows(NullPointerException.class, () -> TestRequestBuilder.putMultipart(null));
  }

  @Test
  void GIVEN_url_WHEN_putMultipart_THEN_return_expected_request() {
    // Arrange
    var mockRequestPostProcessor = Mockito.mock(RequestPostProcessor.class);
    var mockTestRequestBuilderUtils = Mockito.mockStatic(TestRequestBuilderUtils.class);
    mockTestRequestBuilderUtils
        .when(() -> TestRequestBuilderUtils.setMethod(HttpMethod.PUT))
        .thenReturn(mockRequestPostProcessor);

    var mockMultiPartBuilder = Mockito.mock(MockMultipartHttpServletRequestBuilder.class);

    var mockRequestBuilders = Mockito.mockStatic(MockMvcRequestBuilders.class);
    mockRequestBuilders
        .when(() -> MockMvcRequestBuilders.multipart(TEST_URI))
        .thenReturn(mockMultiPartBuilder);

    // Act
    TestRequestBuilder.putMultipart(TEST_URI);

    // Assert
    Mockito.verify(mockMultiPartBuilder).with(mockRequestPostProcessor);
    mockTestRequestBuilderUtils.close();
    mockRequestBuilders.close();
  }

  @Test
  @SuppressWarnings("ConstantConditions")
  void GIVEN_null_WHEN_patch_THEN_throw_NullPointerException() {
    assertThrows(NullPointerException.class, () -> TestRequestBuilder.patch(null));
  }

  @Test
  @SuppressWarnings("ConstantConditions")
  void GIVEN_url_WHEN_patch_THEN_return_expected_request() {
    // Act
    var requestBuilder = TestRequestBuilder.patch(TEST_URI);

    // Assert
    var request = requestBuilder.buildRequest(SERVLET_CONTEXT);
    assertThat(request.getMethod(), is(TestRequestType.PATCH.toString()));
  }

  @Test
  @SuppressWarnings("ConstantConditions")
  void GIVEN_null_WHEN_patchMultipart_THEN_throw_NullPointerException() {
    assertThrows(NullPointerException.class, () -> TestRequestBuilder.patchMultipart(null));
  }

  @Test
  void GIVEN_url_WHEN_patchMultipart_THEN_return_expected_request() {
    // Arrange
    var mockRequestPostProcessor = Mockito.mock(RequestPostProcessor.class);
    var mockTestRequestBuilderUtils = Mockito.mockStatic(TestRequestBuilderUtils.class);
    mockTestRequestBuilderUtils
        .when(() -> TestRequestBuilderUtils.setMethod(HttpMethod.PATCH))
        .thenReturn(mockRequestPostProcessor);

    var mockMultiPartBuilder = Mockito.mock(MockMultipartHttpServletRequestBuilder.class);

    var mockRequestBuilders = Mockito.mockStatic(MockMvcRequestBuilders.class);
    mockRequestBuilders
        .when(() -> MockMvcRequestBuilders.multipart(TEST_URI))
        .thenReturn(mockMultiPartBuilder);

    // Act
    TestRequestBuilder.patchMultipart(TEST_URI);

    // Assert
    Mockito.verify(mockMultiPartBuilder).with(mockRequestPostProcessor);
    mockTestRequestBuilderUtils.close();
    mockRequestBuilders.close();
  }

  @Test
  @SuppressWarnings("ConstantConditions")
  void GIVEN_null_WHEN_delete_THEN_throw_NullPointerException() {
    assertThrows(NullPointerException.class, () -> TestRequestBuilder.delete(null));
  }

  @Test
  @SuppressWarnings("ConstantConditions")
  void GIVEN_url_WHEN_delete_THEN_return_expected_request() {
    // Act
    var requestBuilder = TestRequestBuilder.delete(TEST_URI);

    // Assert
    var request = requestBuilder.buildRequest(SERVLET_CONTEXT);
    assertThat(request.getMethod(), is(TestRequestType.DELETE.toString()));
  }

  @Test
  @SuppressWarnings("ConstantConditions")
  void GIVEN_null_WHEN_head_THEN_throw_NullPointerException() {
    assertThrows(NullPointerException.class, () -> TestRequestBuilder.head(null));
  }

  @Test
  @SuppressWarnings("ConstantConditions")
  void GIVEN_url_WHEN_head_THEN_return_expected_request() {
    // Act
    var requestBuilder = TestRequestBuilder.head(TEST_URI);

    // Assert
    var request = requestBuilder.buildRequest(SERVLET_CONTEXT);
    assertThat(request.getMethod(), is(TestRequestType.HEAD.toString()));
  }

  @Test
  @SuppressWarnings("ConstantConditions")
  void GIVEN_null_WHEN_options_THEN_throw_NullPointerException() {
    assertThrows(NullPointerException.class, () -> TestRequestBuilder.options(null));
  }

  @Test
  @SuppressWarnings("ConstantConditions")
  void GIVEN_url_WHEN_options_THEN_return_expected_request() {
    // Act
    var requestBuilder = TestRequestBuilder.options(TEST_URI);

    // Assert
    var request = requestBuilder.buildRequest(SERVLET_CONTEXT);
    assertThat(request.getMethod(), is(TestRequestType.OPTIONS.toString()));
  }
}
