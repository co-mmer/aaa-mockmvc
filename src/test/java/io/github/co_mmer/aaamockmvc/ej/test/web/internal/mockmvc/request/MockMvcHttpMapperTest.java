package io.github.co_mmer.aaamockmvc.ej.test.web.internal.mockmvc.request;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.HttpMethod;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class MockMvcHttpMethodMapperTest {

  @ParameterizedTest(name = "{0} maps to {1}")
  @MethodSource("supportedHttpMethods")
  void GIVEN_http_method_WHEN_mapTo_THEN_return_corresponding_spring_http_method(
      HttpMethod source, org.springframework.http.HttpMethod expected) {
    // Act
    var result = MockMvcHttpMapper.mapTo(source);

    // Assert
    assertThat(result, is(expected));
  }

  private static Stream<Arguments> supportedHttpMethods() {
    return Stream.of(
        arguments(HttpMethod.GET, org.springframework.http.HttpMethod.GET),
        arguments(HttpMethod.HEAD, org.springframework.http.HttpMethod.HEAD),
        arguments(HttpMethod.POST, org.springframework.http.HttpMethod.POST),
        arguments(HttpMethod.PUT, org.springframework.http.HttpMethod.PUT),
        arguments(HttpMethod.PATCH, org.springframework.http.HttpMethod.PATCH),
        arguments(HttpMethod.DELETE, org.springframework.http.HttpMethod.DELETE),
        arguments(HttpMethod.OPTIONS, org.springframework.http.HttpMethod.OPTIONS),
        arguments(HttpMethod.TRACE, org.springframework.http.HttpMethod.TRACE));
  }
}
