package io.github.co_mmer.aaamockmvc.ej.test.web.scenario;

import io.github.co_mmer.aaamockmvc.ej.test.web.scenario.step.TestStep;
import java.util.function.Consumer;

public interface ScenarioFlow {

  <R> R section(String name, Consumer<TestStep> block);
}
