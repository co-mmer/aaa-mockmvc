package io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestAAAContext;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestActResult2;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestAssertResult;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import lombok.NonNull;
import org.springframework.http.HttpStatus;

public abstract class TestAssertBase {

  private TestAAAContext context;

  protected void useContext(TestAAAContext context) {
    this.context = context;
  }

  protected void useActResult(int status) {
    this.context.setActResult2(new TestActResult2(status, Collections.emptyMap(), null, null));
  }

  protected void useActResult(HttpStatus status) {
    this.useActResult(status.value());
  }

  protected void useActResult(byte[] content) {
    this.context.setActResult2(
        new TestActResult2(-1, Collections.emptyMap(), content, new String(content)));
  }

  protected void useActResult(@NonNull String content) {
    var actResult = new TestActResult2(-1, Collections.emptyMap(), content.getBytes(), content);
    this.context.setActResult2(actResult);
  }

  protected void useActResult(String key, String... value) {
    this.context.setActResult2(
        new TestActResult2(-1, Map.of(key, Arrays.asList(value)), null, null));
  }

  protected <T> void useAssertResult(T actual) {
    this.context.setAssertResult(new TestAssertResult<>(actual));
  }
}
