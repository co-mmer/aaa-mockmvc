package io.github.co_mmer.aaamockmvc.ej.test.web.internal.act;

import io.github.co_mmer.aaamockmvc.ej.test.web.act.TestAct;
import io.github.co_mmer.aaamockmvc.ej.test.web.act.error.TestActFailedError;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.act.error.TestActFailedErrorMessage;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.act.mapper.TestActResultMapper;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.act.strategy.TestRequestStrategyFactory;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.act.validator.TestActPreconditionsValidator;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.annotation.Since;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestAAAContext;
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

    var resultActions = performRequest();
    var actResult = TestActResultMapper.mapTo(resultActions);
    this.context.setActResult(actResult);
    return this;
  }

  private ResultActions performRequest() {
    var result = this.context.getArrangeResult();
    TestActPreconditionsValidator.verifyPerform(this.context);

    var strategy = TestRequestStrategyFactory.resolve(result.getUrl().getMethod());
    var requestBuilder = strategy.apply(result);

    try {
      return this.context.getEnvironment().mvc().perform(requestBuilder);
    } catch (Exception e) {
      var message = TestActFailedErrorMessage.build(result, e);
      throw new TestActFailedError(message, e);
    }
  }
}
