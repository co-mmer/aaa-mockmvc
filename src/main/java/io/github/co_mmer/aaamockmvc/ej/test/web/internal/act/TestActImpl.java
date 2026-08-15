package io.github.co_mmer.aaamockmvc.ej.test.web.internal.act;

import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.act.error.TestActFailedErrorFormatter.createMessage;

import io.github.co_mmer.aaamockmvc.ej.test.web.act.TestAct;
import io.github.co_mmer.aaamockmvc.ej.test.web.act.error.TestActFailedError;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.act.strategy.TestRequestStrategyFactory;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.act.validator.TestActPreconditionsValidator;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.annotation.Since;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.mockmvc.execute.MockMvcExecutionResult;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.mockmvc.execute.MockMvcExecutor;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.mockmvc.model.d.RequestDescription;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestAAAContext;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestActResult2;
import org.springframework.test.web.servlet.ResultActions;

@Since("1.0.0")
public final class TestActImpl implements TestAct {

  private final TestAAAContext context;

  @Since("2.0.0")
  public TestActImpl(TestAAAContext context) {
    this.context = context;
  }

  @Override
  public TestAct perform() {
    /*    var resultActions = performRequest();
    var actResult = TestActResultMapper.mapTo(resultActions);
    this.context.setActResult(actResult);*/

    var result = performRequest2();
    var resultAct =
        new TestActResult2(
            result.status(), result.headers(), result.contentAsBytes(), result.contentAsString());
    this.context.setActResult2(resultAct);

    return this;
  }

  @Deprecated
  private ResultActions performRequest() {
    var result = this.context.getArrangeResult();
    TestActPreconditionsValidator.verifyPerform(this.context);

    var strategy = TestRequestStrategyFactory.resolve(result.getUrl().getMethod());
    var requestBuilder = strategy.apply(result);

    try {
      return this.context.getEnvironment().mvc().perform(requestBuilder);
    } catch (Exception e) {
      throw new TestActFailedError(createMessage(this.context.getStep(), result, e));
    }
  }

  private MockMvcExecutionResult performRequest2() {
    var request = this.context.getRequestBuilder().build();

    try {
      var executor = new MockMvcExecutor(this.context.getEnvironment().mvc());
      return executor.execute(request);
    } catch (Exception e) {
      var description = RequestDescription.describe(request);
      throw new TestActFailedError(createMessage(this.context.getStep(), description, e));
    }
  }
}
