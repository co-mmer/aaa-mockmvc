package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.status;

import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.content.TestAssertContent;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.head.TestAssertHead;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.status.TestAssert1Status;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.status.TestAssert2Status;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.content.TestAssertContentImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.head.TestAssertHeadImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.context.TestAAAContext;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.metadata.Since;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;

@Since("1.1.0")
public final class TestAssertStatusImpl implements TestAssert1Status, TestAssert2Status {

  private final TestAAAContext context;
  private final StatusAssertion assertion;

  @Since("2.1.0")
  public TestAssertStatusImpl(@NonNull TestAAAContext context) {
    this.context = context;
    this.assertion =
        new StatusAssertion(new StatusValue(context.getActResult().status()), context.getStep());
  }

  @Override
  @NonNull
  public TestAssert2Status is(@NonNull HttpStatus status) {
    this.assertion.is(StatusValue.of(status));
    return this;
  }

  @Override
  @NonNull
  public TestAssert2Status is(int status) {
    this.assertion.is(new StatusValue(status));
    return this;
  }

  @Override
  @NonNull
  public TestAssert2Status isOk() {
    this.assertion.isOk();
    return this;
  }

  @Override
  @NonNull
  public TestAssert2Status isCreated() {
    this.assertion.isCreated();
    return this;
  }

  @Override
  @NonNull
  public TestAssert2Status isAccepted() {
    this.assertion.isAccepted();
    return this;
  }

  @Override
  @NonNull
  public TestAssert2Status isNotFound() {
    this.assertion.isNotFound();
    return this;
  }

  @Override
  @NonNull
  public TestAssert2Status is2xxSuccessful() {
    this.assertion.is2xxSuccessful();
    return this;
  }

  @Override
  @NonNull
  public TestAssert2Status is3xxRedirect() {
    this.assertion.is3xxRedirect();
    return this;
  }

  @Override
  @NonNull
  public TestAssert2Status is4xxClientError() {
    this.assertion.is4xxClientError();
    return this;
  }

  @Override
  @NonNull
  public TestAssert2Status is5xxServerError() {
    this.assertion.is5xxServerError();
    return this;
  }

  @Override
  @NonNull
  public TestAssert2Status isForbidden() {
    this.assertion.isForbidden();
    return this;
  }

  @Override
  @NonNull
  public TestAssert2Status isUnauthorized() {
    this.assertion.isUnauthorized();
    return this;
  }

  @Override
  @NonNull
  public TestAssert2Status isInRange(int minStatusCode, int maxStatusCode) {
    this.assertion.isInRange(new StatusRange(minStatusCode, maxStatusCode));
    return this;
  }

  @Override
  @NonNull
  public TestAssertContent content() {
    return new TestAssertContentImpl(this.context);
  }

  @Override
  @NonNull
  public TestAssertHead headers() {
    return new TestAssertHeadImpl(this.context);
  }
}
