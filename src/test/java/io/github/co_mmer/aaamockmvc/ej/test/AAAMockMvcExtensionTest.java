package io.github.co_mmer.aaamockmvc.ej.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

class AAAMockMvcExtensionTest {

  private AAAMockMvcExtension extension;
  private ExtensionContext mockExtensionContext;
  private MockedStatic<AAAMockMvc> mockAAAMockMvc;

  @BeforeEach
  void setUp() {
    this.mockExtensionContext = Mockito.mock(ExtensionContext.class);
    this.mockAAAMockMvc = Mockito.mockStatic(AAAMockMvc.class);
    this.extension = new AAAMockMvcExtension();
  }

  @AfterEach
  void clean() {
    this.mockAAAMockMvc.close();
  }

  @Test
  void WHEN_beforeEach_THEN_clearContext_is_called() {
    // Act
    this.extension.beforeEach(this.mockExtensionContext);

    // Act
    this.mockAAAMockMvc.verify(AAAMockMvc::clearContext);
  }

  @Test
  void WHEN_afterEach_THEN_clearContext_is_called() {
    // Act
    this.extension.afterEach(this.mockExtensionContext);

    // Act
    this.mockAAAMockMvc.verify(AAAMockMvc::clearContext);
  }
}
