package io.github.co_mmer.aaamockmvc.ej.test.web.internal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.co_mmer.aaamockmvc.ej.test.AAAMockMvc;
import io.github.co_mmer.aaamockmvc.ej.test.AAAMockMvcConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@SuppressWarnings("unchecked")
class AAAMockMvcConfigTest {

  private WebApplicationContext mockWebApplicationContext;
  private ObjectProvider<MockMvc> mockMvcProvider;
  private ObjectProvider<ObjectMapper> mockObjectMapperProvider;
  private AAAMockMvcBuilder mockAAAMockMvcBuilder;
  private AAAMockMvc mockAAAMockMvc;
  private AAAMockMvcConfig config;

  @BeforeEach
  void setUp() {
    this.mockWebApplicationContext = mock(WebApplicationContext.class);
    this.mockMvcProvider = mock(ObjectProvider.class);
    when(this.mockMvcProvider.getIfAvailable()).thenReturn(null);

    this.mockObjectMapperProvider = mock(ObjectProvider.class);
    when(this.mockObjectMapperProvider.getIfAvailable()).thenReturn(null);

    this.mockAAAMockMvcBuilder = mock(AAAMockMvcBuilder.class);
    this.mockAAAMockMvc = mock(AAAMockMvc.class);
    this.config = new AAAMockMvcConfig();
  }

  @Test
  void WHEN_aaaMockMvc_called_THEN_builder_is_used() {
    // Arrange
    var mockStaticAAAMockMvcBuilder = createMockAAAMockMvcBuilder();

    // Act
    var result =
        this.config.aaaMockMvc(
            this.mockWebApplicationContext, this.mockMvcProvider, this.mockObjectMapperProvider);

    // Assert
    assertThat(result, is(this.mockAAAMockMvc));
    mockStaticAAAMockMvcBuilder.verify(AAAMockMvcBuilder::builder);
    verify(this.mockAAAMockMvcBuilder).withWebApplicationContext(this.mockWebApplicationContext);
    verify(this.mockAAAMockMvcBuilder).build();

    // Cleanup
    mockStaticAAAMockMvcBuilder.close();
  }

  private MockedStatic<AAAMockMvcBuilder> createMockAAAMockMvcBuilder() {
    var mockStaticAAAMockMvcBuilder = mockStatic(AAAMockMvcBuilder.class);
    mockStaticAAAMockMvcBuilder
        .when(AAAMockMvcBuilder::builder)
        .thenReturn(this.mockAAAMockMvcBuilder);

    when(this.mockAAAMockMvcBuilder.withWebApplicationContext(this.mockWebApplicationContext))
        .thenReturn(this.mockAAAMockMvcBuilder);
    when(this.mockAAAMockMvcBuilder.withMockMvc(null)).thenReturn(this.mockAAAMockMvcBuilder);
    when(this.mockAAAMockMvcBuilder.withObjectMapper(null)).thenReturn(this.mockAAAMockMvcBuilder);
    when(this.mockAAAMockMvcBuilder.build()).thenReturn(this.mockAAAMockMvc);
    return mockStaticAAAMockMvcBuilder;
  }
}
