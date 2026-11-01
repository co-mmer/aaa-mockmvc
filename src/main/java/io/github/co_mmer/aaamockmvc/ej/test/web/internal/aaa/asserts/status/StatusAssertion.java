package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.status;

import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.AAAAssertDetail.failure;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.step.TestStepDto;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.metadata.Since;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

public final class StatusAssertion {
  private final StatusValue actual;
  private final TestStepDto step;

  @Since("2.1.0")
  public StatusAssertion(@NonNull StatusValue actual) {
    this(actual, null);
  }

  @Since("2.1.0")
  public StatusAssertion(@NonNull StatusValue actual, @Nullable TestStepDto step) {
    this.actual = actual;
    this.step = step;
  }

  @Since("2.1.0")
  public void is(@NonNull StatusValue expected) {
    if (this.actual.value() != expected.value()) {
      throw failure(
          this.step,
          "The HTTP response status must equal the expected status code.",
          expected.value(),
          this.actual.value());
    }
  }

  @Since("2.1.0")
  public void isInRange(@NonNull StatusRange expected) {
    if (this.actual.value() < expected.minimum() || this.actual.value() > expected.maximum()) {
      throw failure(
          this.step,
          "The HTTP response status must be within the expected range, including both bounds.",
          "[%d, %d]".formatted(expected.minimum(), expected.maximum()),
          this.actual.value());
    }
  }

  @Since("2.1.0")
  public void isOk() {
    if (this.actual.value() != 200) {
      throw failure(
          this.step, "The HTTP response status must be 200 (OK).", 200, this.actual.value());
    }
  }

  @Since("2.1.0")
  public void isCreated() {
    if (this.actual.value() != 201) {
      throw failure(
          this.step, "The HTTP response status must be 201 (Created).", 201, this.actual.value());
    }
  }

  @Since("2.1.0")
  public void isAccepted() {
    if (this.actual.value() != 202) {
      throw failure(
          this.step, "The HTTP response status must be 202 (Accepted).", 202, this.actual.value());
    }
  }

  @Since("2.1.0")
  public void isNotFound() {
    if (this.actual.value() != 404) {
      throw failure(
          this.step, "The HTTP response status must be 404 (Not Found).", 404, this.actual.value());
    }
  }

  @Since("2.1.0")
  public void isForbidden() {
    if (this.actual.value() != 403) {
      throw failure(
          this.step, "The HTTP response status must be 403 (Forbidden).", 403, this.actual.value());
    }
  }

  @Since("2.1.0")
  public void isUnauthorized() {
    if (this.actual.value() != 401) {
      throw failure(
          this.step,
          "The HTTP response status must be 401 (Unauthorized).",
          401,
          this.actual.value());
    }
  }

  @Since("2.1.0")
  public void is2xxSuccessful() {
    var range = StatusRange.SUCCESSFUL;
    if (this.actual.value() < range.minimum() || this.actual.value() > range.maximum()) {
      throw failure(
          this.step,
          "The HTTP response status must indicate success within the expected inclusive range.",
          "[%d, %d]".formatted(range.minimum(), range.maximum()),
          this.actual.value());
    }
  }

  @Since("2.1.0")
  public void is3xxRedirect() {
    var range = StatusRange.REDIRECT;
    if (this.actual.value() < range.minimum() || this.actual.value() > range.maximum()) {
      throw failure(
          this.step,
          "The HTTP response status must indicate a redirection within the expected inclusive range.",
          "[%d, %d]".formatted(range.minimum(), range.maximum()),
          this.actual.value());
    }
  }

  @Since("2.1.0")
  public void is4xxClientError() {
    var range = StatusRange.CLIENT_ERROR;
    if (this.actual.value() < range.minimum() || this.actual.value() > range.maximum()) {
      throw failure(
          this.step,
          "The HTTP response status must indicate a client error within the expected inclusive range.",
          "[%d, %d]".formatted(range.minimum(), range.maximum()),
          this.actual.value());
    }
  }

  @Since("2.1.0")
  public void is5xxServerError() {
    var range = StatusRange.SERVER_ERROR;
    if (this.actual.value() < range.minimum() || this.actual.value() > range.maximum()) {
      throw failure(
          this.step,
          "The HTTP response status must indicate a server error within the expected inclusive range.",
          "[%d, %d]".formatted(range.minimum(), range.maximum()),
          this.actual.value());
    }
  }
}
