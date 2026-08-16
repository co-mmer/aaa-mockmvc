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

class MockMvcQueryMapperTest {

  private static final String ANY_FIRST_PARAMETER_NAME = "first";
  private static final List<String> ANY_FIRST_PARAMETER_VALUES =
      List.of("first value", "second value");

  private static final String ANY_SECOND_PARAMETER_NAME = "second";
  private static final List<String> ANY_SECOND_PARAMETER_VALUES = List.of("third value");

  private static final Map<String, List<String>> ANY_2_QUERY_PARAMETERS =
      Map.of(
          ANY_FIRST_PARAMETER_NAME,
          ANY_FIRST_PARAMETER_VALUES,
          ANY_SECOND_PARAMETER_NAME,
          ANY_SECOND_PARAMETER_VALUES);

  @Test
  void GIVEN_query_parameters_WHEN_apply_THEN_call_queryParam_with_each_parameter() {
    // Arrange
    var builder = mock(MockHttpServletRequestBuilder.class);

    // Act
    MockMvcQueryMapper.apply(builder, ANY_2_QUERY_PARAMETERS);

    // Assert
    verify(builder)
        .queryParam(ANY_FIRST_PARAMETER_NAME, ANY_FIRST_PARAMETER_VALUES.toArray(new String[0]));

    verify(builder)
        .queryParam(ANY_SECOND_PARAMETER_NAME, ANY_SECOND_PARAMETER_VALUES.toArray(new String[0]));

    verifyNoMoreInteractions(builder);
  }

  @Test
  void GIVEN_null_query_parameters_WHEN_apply_THEN_do_not_interact_with_builder() {
    // Arrange
    var builder = mock(MockHttpServletRequestBuilder.class);

    // Act
    MockMvcQueryMapper.apply(builder, null);

    // Assert
    verifyNoInteractions(builder);
  }

  @Test
  void GIVEN_empty_query_parameters_WHEN_apply_THEN_do_not_interact_with_builder() {
    // Arrange
    var builder = mock(MockHttpServletRequestBuilder.class);

    // Act
    MockMvcQueryMapper.apply(builder, Map.of());

    // Assert
    verifyNoInteractions(builder);
  }

  @Test
  void GIVEN_query_parameters_without_values_WHEN_apply_THEN_call_queryParam_without_values() {
    // Arrange
    var builder = mock(MockHttpServletRequestBuilder.class);
    var queryParameters = new HashMap<String, List<String>>();
    queryParameters.put("parameter-with-null-values", null);
    queryParameters.put("parameter-with-empty-values", List.of());

    // Act
    MockMvcQueryMapper.apply(builder, queryParameters);

    // Assert
    verify(builder).queryParam("parameter-with-null-values");
    verify(builder).queryParam("parameter-with-empty-values");
    verifyNoMoreInteractions(builder);
  }
}
