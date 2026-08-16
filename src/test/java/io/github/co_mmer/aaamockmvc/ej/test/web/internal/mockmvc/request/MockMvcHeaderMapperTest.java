package io.github.co_mmer.aaamockmvc.ej.test.web.internal.mockmvc.request;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

class MockMvcHeaderMapperTest {

  private static final String ANY_FIRST_HEADER_NAME = "X-First-Header";
  private static final String ANY_FIRST_HEADER_VALUE = "first value";
  private static final String ANY_SECOND_HEADER_VALUE = "second value";

  private static final String ANY_SECOND_HEADER_NAME = "X-Second-Header";
  private static final String ANY_THIRD_HEADER_VALUE = "third value";

  private static final Map<String, List<String>> ANY_HEADERS =
      Map.of(
          ANY_FIRST_HEADER_NAME,
          List.of(ANY_FIRST_HEADER_VALUE, ANY_SECOND_HEADER_VALUE),
          ANY_SECOND_HEADER_NAME,
          List.of(ANY_THIRD_HEADER_VALUE));

  @Test
  void GIVEN_headers_WHEN_apply_THEN_call_header_with_each_value() {
    // Arrange
    var builder = mock(MockHttpServletRequestBuilder.class);

    // Act
    MockMvcHeaderMapper.apply(builder, ANY_HEADERS);

    // Assert
    verify(builder).header(ANY_FIRST_HEADER_NAME, ANY_FIRST_HEADER_VALUE);
    verify(builder).header(ANY_FIRST_HEADER_NAME, ANY_SECOND_HEADER_VALUE);
    verify(builder).header(ANY_SECOND_HEADER_NAME, ANY_THIRD_HEADER_VALUE);
    verifyNoMoreInteractions(builder);
  }

  @Test
  void GIVEN_null_headers_WHEN_apply_THEN_do_not_interact_with_builder() {
    // Arrange
    var builder = mock(MockHttpServletRequestBuilder.class);

    // Act
    MockMvcHeaderMapper.apply(builder, null);

    // Assert
    verifyNoInteractions(builder);
  }

  @Test
  void GIVEN_empty_headers_WHEN_apply_THEN_do_not_interact_with_builder() {
    // Arrange
    var builder = mock(MockHttpServletRequestBuilder.class);

    // Act
    MockMvcHeaderMapper.apply(builder, Map.of());

    // Assert
    verifyNoInteractions(builder);
  }

  @Test
  void GIVEN_headers_without_values_WHEN_apply_THEN_do_not_interact_with_builder() {
    // Arrange
    var builder = mock(MockHttpServletRequestBuilder.class);
    var headers = new HashMap<String, List<String>>();
    headers.put("X-Null-Values", null);
    headers.put("X-Empty-Values", List.of());

    // Act
    MockMvcHeaderMapper.apply(builder, headers);

    // Assert
    verifyNoInteractions(builder);
  }
}
