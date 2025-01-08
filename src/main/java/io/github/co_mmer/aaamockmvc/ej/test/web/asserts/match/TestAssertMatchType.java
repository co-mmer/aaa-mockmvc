package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.match;

import lombok.Getter;

@Getter
public enum TestAssertMatchType {
  ALL("The actual value does not match the expected condition."),
  ANY("At least one value should match the condition."),
  NONE("No value should match the condition, but some did.");

  private final String reason;

  TestAssertMatchType(String reason) {
    this.reason = reason;
  }
}
