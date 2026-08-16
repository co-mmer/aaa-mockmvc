package io.github.co_mmer.aaamockmvc.ej.test.web.internal.act;

import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.act.error.TestActFailedErrorFormatter.createMessage;

import io.github.co_mmer.aaamockmvc.ej.test.web.act.TestAct;
import io.github.co_mmer.aaamockmvc.ej.test.web.act.error.TestActFailedError;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.annotation.Since;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.mockmvc.execute.MockMvcExecutionResult;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.mockmvc.execute.MockMvcExecutor;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.mockmvc.model.d.RequestDescription;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestAAAContext;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestActResult;

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
    try {
      var request = this.context.getArrangeBuilder().build();
      return MockMvcExecutor.execute(this.context.getEnvironment().mvc(), request);
    } catch (Exception e) {
      var request = this.context.getArrangeBuilder().build();
      var description = RequestDescription.describe(request);
      throw new TestActFailedError(createMessage(this.context.getStep(), description, e)); // todo
    }
  }
}
