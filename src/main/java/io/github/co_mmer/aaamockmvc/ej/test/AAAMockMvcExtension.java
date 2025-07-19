package io.github.co_mmer.aaamockmvc.ej.test;

public final class AAAMockMvcExtension
    implements org.junit.jupiter.api.extension.AfterEachCallback {

  @Override
  public void afterEach(org.junit.jupiter.api.extension.ExtensionContext context) {
    AAAMockMvc.clearContext();
  }
}
