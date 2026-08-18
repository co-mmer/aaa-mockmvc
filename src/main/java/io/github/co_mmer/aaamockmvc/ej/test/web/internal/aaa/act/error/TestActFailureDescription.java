package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.act.error;

import static java.util.stream.Collectors.joining;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.step.TestStepDescription;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.step.TestStepDto;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.metadata.Since;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.Request;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.description.RequestDescription;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.utils.StringUtils;
import java.util.stream.Stream;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@Since("2.0.2")
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class TestActFailureDescription {

  @Since("2.0.2")
  public static String describe(TestStepDto step, Request request, Throwable cause) {

    return Stream.of(
            TestStepDescription.describe(step),
            "ACT failed:",
            RequestDescription.describe(request),
            "Cause: " + describeCause(cause))
        .filter(StringUtils::isNotBlank)
        .collect(joining(System.lineSeparator()));
  }

  private static String describeCause(Throwable cause) {
    var message = cause.getMessage();
    return StringUtils.isBlank(message) ? "<no message>" : message;
  }
}
