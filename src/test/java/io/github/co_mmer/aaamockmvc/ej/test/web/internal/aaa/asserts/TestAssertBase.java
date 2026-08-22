package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.act.model.TestActResult;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.context.TestAAAContext;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import lombok.NonNull;
import org.springframework.http.HttpStatus;

public abstract class TestAssertBase {

  private TestAAAContext context;

  public TestAAAContext getContext() {
    return this.context;
  }

  protected void useContext(TestAAAContext context) {
    this.context = context;
  }

  protected void useActResult(int status) {
    this.context.setActResult(new TestActResult(status, Collections.emptyMap(), null, null));
  }

  protected void useActResult(HttpStatus status) {
    this.useActResult(status.value());
  }

  protected void useActResult(byte[] content) {
    this.context.setActResult(
        new TestActResult(-1, Collections.emptyMap(), content, new String(content)));
  }

  protected void useActResult(@NonNull String content) {
    var actResult = new TestActResult(-1, Collections.emptyMap(), content.getBytes(), content);
    this.context.setActResult(actResult);
  }

  protected void useActResult(String key, String... value) {
    this.context.setActResult(new TestActResult(-1, Map.of(key, Arrays.asList(value)), null, null));
  }

  protected <T> void useAssertResult(T actual) {
    this.context.setAssertOperand(AssertOperand.clazz(actual));
  }
}
