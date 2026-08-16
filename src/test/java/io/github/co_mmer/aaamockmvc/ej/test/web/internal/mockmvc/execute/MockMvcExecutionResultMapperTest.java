package io.github.co_mmer.aaamockmvc.ej.test.web.internal.mockmvc.execute;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MvcResult;

class MockMvcExecutionResultMapperTest {

  private static final int ANY_STATUS = 201;
  private static final String ANY_CONTENT_AS_STRING = "response content";
  private static final byte[] ANY_CONTENT_AS_BYTES = new byte[]{1, 2, 3};

  private static final Map<String, List<String>> ANY_HEADERS =
      Map.of(
          "X-First-Header",
          List.of("first value", "second value"),
          "X-Second-Header",
          List.of("third value"));

  private MvcResult source;
  private MockHttpServletResponse response;

  @BeforeEach
  void setUp() {
    this.source = mock(MvcResult.class);
    this.response = mock(MockHttpServletResponse.class);
    when(this.source.getResponse()).thenReturn(this.response);
  }

  @Test
  void GIVEN_readable_response_WHEN_map_THEN_return_response_values() {
    // Arrange
    givenReadableResponse();

    // Act
    var result = MockMvcExecutionResultMapper.map(this.source);

    // Assert
    assertThat(result.status(), is(ANY_STATUS));
    assertThat(result.contentAsString(), is(ANY_CONTENT_AS_STRING));
    assertThat(result.contentAsBytes(), is(ANY_CONTENT_AS_BYTES));
    assertThat(result.headers(), is(ANY_HEADERS));
  }

  @Test
  void GIVEN_unreadable_response_content_WHEN_map_THEN_throw_execution_exception() {
    // Arrange
    givenUnreadableResponseContent();

    // Act
    var exception =
        assertThrows(
            MockMvcExecutionException.class,
            () -> MockMvcExecutionResultMapper.map(this.source));

    // Assert
    assertThat(
        exception.getMessage(),
        is("Could not read response content as String"));
  }

  @SneakyThrows
  private void givenReadableResponse() {
    when(this.response.getStatus()).thenReturn(ANY_STATUS);
    when(this.response.getContentAsString()).thenReturn(ANY_CONTENT_AS_STRING);
    when(this.response.getContentAsByteArray()).thenReturn(ANY_CONTENT_AS_BYTES);
    when(this.response.getHeaderNames()).thenReturn(ANY_HEADERS.keySet());

    ANY_HEADERS.forEach(
        (name, values) -> when(this.response.getHeaders(name)).thenReturn(values));
  }

  @SneakyThrows
  private void givenUnreadableResponseContent() {
    when(this.response.getContentAsString())
        .thenThrow(new UnsupportedEncodingException());
  }
}