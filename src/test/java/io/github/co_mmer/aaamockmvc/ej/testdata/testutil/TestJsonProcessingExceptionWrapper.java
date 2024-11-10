package io.github.co_mmer.aaamockmvc.ej.testdata.testutil;

import com.fasterxml.jackson.core.JsonProcessingException;

public class TestJsonProcessingExceptionWrapper extends JsonProcessingException {

  public TestJsonProcessingExceptionWrapper() {
    super("test");
  }
}
