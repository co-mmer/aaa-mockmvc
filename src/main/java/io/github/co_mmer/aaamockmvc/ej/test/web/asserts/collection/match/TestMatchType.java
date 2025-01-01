package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.collection.match;

import lombok.Getter;

@Getter
public enum TestMatchType {
  ALL("The actual collection does not match the expected condition."),
  ANY("At least one element should match the condition."),
  NONE("No element should match the condition, but some did.");

  private final String reason;

  TestMatchType(String reason) {
    this.reason = reason;
  }
}
