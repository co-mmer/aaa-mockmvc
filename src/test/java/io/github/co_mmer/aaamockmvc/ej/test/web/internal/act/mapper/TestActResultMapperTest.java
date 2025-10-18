package io.github.co_mmer.aaamockmvc.ej.test.web.internal.act.mapper;

import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.EMPTY_BYTE_ARRAY;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.HEADER_KEY_AUTH;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.HEADER_KEY_CONTENT_TYPE;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.HEADER_VALUE_JSON;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.HEADER_VALUE_TOKEN;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.HEADER_VALUE_XML;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.nio.charset.StandardCharsets;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

class TestActResultMapperTest {

  private static final ResultActions ACT_UTF16 = actions(respUtf16());
  private static final ResultActions ACT_CHARSET_NULL = actions(respCharsetNull());
  private static final ResultActions ACT_CHARSET_INVALID = actions(respCharsetInvalid());
  private static final ResultActions ACT_HEADERS = actions(respHeaders());
  public static final String CONTENT = "y";

  @Test
  void GIVEN_utf16_WHEN_mapTo_THEN_maps_all_fields() {
    // Act
    var result = TestActResultMapper.mapTo(ACT_UTF16);

    // Assert
    assertThat(result.status(), is(201));
    assertThat(result.charset().name(), is("UTF-16"));
    assertThat(result.contentAsString(), is("hi"));
  }

  @Test
  void GIVEN_nullCharset_WHEN_mapTo_THEN_defaultUtf8() {
    // Act
    var result = TestActResultMapper.mapTo(ACT_CHARSET_NULL);

    // Assert
    assertThat(result.charset(), is(StandardCharsets.UTF_8));
    assertThat(result.contentAsBytes(), is(EMPTY_BYTE_ARRAY));
  }

  @Test
  void GIVEN_invalidCharset_WHEN_mapTo_THEN_fallbackUtf8() {
    // Act
    var result = TestActResultMapper.mapTo(ACT_CHARSET_INVALID);

    // Assert
    assertThat(result.charset(), is(StandardCharsets.UTF_8));
    assertThat(result.contentAsString(), is(CONTENT));
  }

  @Test
  void GIVEN_headers_WHEN_mapTo_THEN_preserves_values() {
    // Act
    var result = TestActResultMapper.mapTo(ACT_HEADERS);

    // Assert
    var headers = result.headers();
    assertThat(headers.get(HEADER_KEY_AUTH), contains(HEADER_VALUE_TOKEN, HEADER_VALUE_JSON));
    assertThat(headers.get(HEADER_KEY_CONTENT_TYPE), contains(HEADER_VALUE_XML));
  }

  @Test
  void GIVEN_content_WHEN_contentAsBytes_THEN_matches_content() {
    // Act
    var result = TestActResultMapper.mapTo(ACT_CHARSET_NULL);

    // Assert
    assertThat(result.contentAsBytes(), is(EMPTY_BYTE_ARRAY));
  }

  @SneakyThrows
  private static MockHttpServletResponse respUtf16() {
    var response = new MockHttpServletResponse();
    response.setStatus(201);
    response.setCharacterEncoding("UTF-16");
    response.getOutputStream().write("hi".getBytes(StandardCharsets.UTF_16));
    return response;
  }

  @SneakyThrows
  private static MockHttpServletResponse respCharsetNull() {
    var response = mock(MockHttpServletResponse.class);
    Mockito.when(response.getStatus()).thenReturn(200);
    Mockito.when(response.getCharacterEncoding()).thenReturn(null);
    Mockito.when(response.getContentAsByteArray()).thenReturn(EMPTY_BYTE_ARRAY);
    return response;
  }

  @SneakyThrows
  private static MockHttpServletResponse respCharsetInvalid() {
    var response = Mockito.spy(new MockHttpServletResponse());
    doReturn("BAD-CHARSET").when(response).getCharacterEncoding();
    response.setStatus(200);
    response.getOutputStream().write(CONTENT.getBytes(StandardCharsets.UTF_8));
    return response;
  }

  private static MockHttpServletResponse respHeaders() {
    var response = new MockHttpServletResponse();
    response.setStatus(204);
    response.addHeader(HEADER_KEY_AUTH, HEADER_VALUE_TOKEN);
    response.addHeader(HEADER_KEY_AUTH, HEADER_VALUE_JSON);
    response.addHeader(HEADER_KEY_CONTENT_TYPE, HEADER_VALUE_XML);
    return response;
  }

  @SneakyThrows
  private static ResultActions actions(MockHttpServletResponse response) {
    var actions = mock(ResultActions.class);
    var mvcResult = mock(MvcResult.class);
    when(actions.andReturn()).thenReturn(mvcResult);
    when(mvcResult.getResponse()).thenReturn(response);
    return actions;
  }
}
