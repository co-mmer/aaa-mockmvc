package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.status;

import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.InvalidAssertionException;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.metadata.Since;

public record StatusRange(int minimum, int maximum) {
  public static final StatusRange SUCCESSFUL = new StatusRange(200, 299);
  public static final StatusRange REDIRECT = new StatusRange(300, 399);
  public static final StatusRange CLIENT_ERROR = new StatusRange(400, 499);
  public static final StatusRange SERVER_ERROR = new StatusRange(500, 599);

  @Since("2.1.0")
  public StatusRange {
    if (minimum > maximum) {
      throw new InvalidAssertionException(
          "isInRange() requires a minimum that is not greater than the maximum.");
    }
  }
}
