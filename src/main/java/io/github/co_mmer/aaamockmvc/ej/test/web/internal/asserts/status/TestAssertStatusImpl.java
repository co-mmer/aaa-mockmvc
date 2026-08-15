package io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.status;

import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.match.TestAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.content.TestAssertContent;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.head.TestAssertHead;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.status.TestAssert1Status;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.status.TestAssert2Status;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.annotation.Since;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.content.TestAssertContentImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.head.TestAssertHeadImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestAAAContext;
import lombok.NonNull;
import org.hamcrest.Matchers;
import org.springframework.http.HttpStatus;

@Since("1.1.0")
public final class TestAssertStatusImpl implements TestAssert1Status, TestAssert2Status {

  private record Status(int min, int max) {}

  private static final Status SUCCESSFUL = new Status(200, 299);
  private static final Status REDIRECT = new Status(300, 399);
  private static final Status CLIENT = new Status(400, 499);
  private static final Status SERVER = new Status(500, 599);
  private final TestAAAContext context;

  @Since("2.0.0")
  public TestAssertStatusImpl(@NonNull TestAAAContext context) {
    this.context = context;
  }

  @Override
  public TestAssert2Status is(@NonNull HttpStatus status) {
    assertThat(
        this.context.getStep(), this.context.getActResult2().status(), Matchers.is(status.value()));
    return this;
  }

  @Override
  public TestAssert2Status is(int status) {
    assertThat(this.context.getStep(), this.context.getActResult2().status(), Matchers.is(status));
    return this;
  }

  @Override
  public TestAssert2Status isOk() {
    return this.is(HttpStatus.OK);
  }

  @Override
  public TestAssert2Status isCreated() {
    return this.is(HttpStatus.CREATED);
  }

  @Override
  public TestAssert2Status isAccepted() {
    return this.is(HttpStatus.ACCEPTED);
  }

  @Override
  public TestAssert2Status isNotFound() {
    return this.is(HttpStatus.NOT_FOUND);
  }

  @Override
  public TestAssert2Status is2xxSuccessful() {
    return this.isInRange(SUCCESSFUL.min, SUCCESSFUL.max);
  }

  @Override
  public TestAssert2Status is3xxRedirect() {
    return this.isInRange(REDIRECT.min, REDIRECT.max);
  }

  @Override
  public TestAssert2Status is4xxClientError() {
    return this.isInRange(CLIENT.min, CLIENT.max);
  }

  @Override
  public TestAssert2Status is5xxServerError() {
    return this.isInRange(SERVER.min, SERVER.max);
  }

  @Override
  public TestAssert2Status isForbidden() {
    return this.is(HttpStatus.FORBIDDEN);
  }

  @Override
  public TestAssert2Status isUnauthorized() {
    return this.is(HttpStatus.UNAUTHORIZED);
  }

  @Override
  public TestAssert2Status isInRange(int minStatusCode, int maxStatusCode) {
    var status = this.context.getActResult2().status();
    assertThat(this.context.getStep(), status, Matchers.is(greaterThanOrEqualTo(minStatusCode)));
    assertThat(this.context.getStep(), status, Matchers.is(lessThanOrEqualTo(maxStatusCode)));
    return this;
  }

  @Override
  public TestAssertContent content() {
    return new TestAssertContentImpl(this.context);
  }

  @Override
  public TestAssertHead headers() {
    return new TestAssertHeadImpl(this.context);
  }
}
