package io.github.co_mmer.aaamockmvc.ej.test.web.scenario;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestAAAContext;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestEnvironment;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.scenario.section.TestStepImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.scenario.step.TestStep;
import java.util.function.Consumer;

public final class ScenarioFlowImpl implements ScenarioFlow {

  private final String flowName; // aktuell nur informativ
  private final TestEnvironment environment;

  public ScenarioFlowImpl(String flowName, TestEnvironment environment) {
    this.flowName = (flowName == null || flowName.isBlank()) ? "<unnamed flow>" : flowName;
    this.environment = environment;
  }

  @Override
  public <R> R section(String name, Consumer<TestStep> block) {
    var context = new TestAAAContext(this.environment);
    var section = new TestStepImpl(context);
    block.accept(section);

    var answerResult = context.getAnswerResult();
    if (answerResult == null) {
      return null;
    }

    var actualContent = answerResult.actualContent();
    @SuppressWarnings("unchecked")
    R result = (R) actualContent;
    return result;
  }
}
