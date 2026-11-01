package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.act;

import io.github.co_mmer.aaamockmvc.ej.test.web.act.TestAct;
import io.github.co_mmer.aaamockmvc.ej.test.web.act.error.TestActFailedError;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.act.error.TestActFailureDescription;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.act.model.TestActResult;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.context.TestAAAContext;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.metadata.Since;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.mockmvc.execute.MockMvcExecutionResult;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.mockmvc.execute.MockMvcExecutor;

@Since("1.0.0")
public final class TestActImpl implements TestAct {

  private final TestAAAContext context;

  @Since("2.0.0")
  public TestActImpl(TestAAAContext context) {
    this.context = context;
  }

  @Override
  public TestAct perform() {
    var result = performRequest();
    var resultAct =
        new TestActResult(
            result.status(), result.headers(), result.contentAsBytes(), result.contentAsString());
    this.context.setActResult(resultAct);

    return this;
  }

  private MockMvcExecutionResult performRequest() {
    var request = this.context.getArrangeBuilder().build();

    try {
      return MockMvcExecutor.execute(this.context.getEnvironment().mvc(), request);
    } catch (Exception cause) {
      var message = TestActFailureDescription.describe(this.context.getStep(), request, cause);
      throw new TestActFailedError(message);
    }
  }
}
