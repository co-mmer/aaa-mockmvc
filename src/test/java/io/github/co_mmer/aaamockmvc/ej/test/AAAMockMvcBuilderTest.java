package io.github.co_mmer.aaamockmvc.ej.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

class AAAMockMvcBuilderTest {

  private final MockMvc mockMvc = mock(MockMvc.class);
  private final WebApplicationContext wac = webApplicationContext();
  private final ObjectMapper objectMapper = new ObjectMapper();

  private WebApplicationContext webApplicationContext() {
    var context = new AnnotationConfigWebApplicationContext();
    context.setServletContext(new MockServletContext());
    context.refresh();
    return context;
  }

  @Test
  void GIVEN_mockMvc_objectMapper_WHEN_build_THEN_return_not_null() {
    // Act
    var result =
        AAAMockMvcBuilder.builder()
            .withMockMvc(this.mockMvc)
            .withObjectMapper(this.objectMapper)
            .build();

    // Assert
    assertThat(result, is(notNullValue()));
  }

  @Test
  void GIVEN_only_mockMvc_WHEN_build_THEN_return_not_null() {
    // Act
    var result = AAAMockMvcBuilder.builder().withMockMvc(this.mockMvc).build();

    // Assert
    assertThat(result, is(notNullValue()));
  }

  @Test
  void GIVEN_wac_objectMapper_WHEN_build_THEN_return_not_null() {
    // Act
    AAAMockMvc result =
        AAAMockMvcBuilder.builder()
            .withWebApplicationContext(this.wac)
            .withObjectMapper(this.objectMapper)
            .build();

    // Assert
    assertThat(result, is(notNullValue()));
  }

  @Test
  void GIVEN_only_wac_WHEN_build_THEN_return_not_null() {
    // Act
    var result = AAAMockMvcBuilder.builder().withWebApplicationContext(this.wac).build();

    // Assert
    assertThat(result, is(notNullValue()));
  }

  @Test
  void GIVEN_only_objectMapper_WHEN_build_THEN_throw_Exception() {
    // Act
    var exception =
        assertThrows(
            IllegalStateException.class,
            () -> AAAMockMvcBuilder.builder().withObjectMapper(this.objectMapper).build());

    // Assert
    assertThat(exception.getMessage(), containsString("Neither WebApplicationContext nor MockMvc"));
  }

  @Test
  void GIVEN_nothing_WHEN_build_THEN_throw_Exception() {
    // Act
    var exception =
        assertThrows(IllegalStateException.class, () -> AAAMockMvcBuilder.builder().build());

    // Assert
    assertThat(exception.getMessage(), containsString("Neither WebApplicationContext nor MockMvc"));
  }
}
