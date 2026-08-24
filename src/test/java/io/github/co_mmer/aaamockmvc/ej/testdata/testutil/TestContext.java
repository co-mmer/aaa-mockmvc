package io.github.co_mmer.aaamockmvc.ej.testdata.testutil;

import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_A1_JSON;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.act.model.TestActResult;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.arrange.TestArrangeBuilder;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.context.TestAAAContext;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.context.TestEnvironment;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.step.TestStepDto;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.HttpMethod;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.test.web.servlet.MockMvc;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestContext {

  public static TestAAAContext createContext() {
    var context = new TestAAAContext(createTestEnvironment());
    context.setArrangeBuilder(createRequestBuilder());
    return context;
  }

  public static TestAAAContext createContext(String stepName) {
    var context = new TestAAAContext(createTestEnvironment());
    context.setStep(new TestStepDto(stepName));
    context.setArrangeBuilder(createRequestBuilder());
    return context;
  }

  private static TestEnvironment createTestEnvironment() {
    var mvc = mock(MockMvc.class);
    var objectMapper = new ObjectMapper();
    return new TestEnvironment(mvc, objectMapper);
  }

  public static TestAAAContext mockContext() {
    var context = new TestAAAContext(createTestEnvironment());
    context.setArrangeBuilder(createRequestBuilder());

    var actResult = mock(TestActResult.class);
    context.setActResult(actResult);
    return context;
  }

  public static TestAAAContext mockContext(String stepName) {
    var context = new TestAAAContext(createTestEnvironment());
    context.setStep(new TestStepDto(stepName));

    var actResult = mock(TestActResult.class);
    when(actResult.contentAsString()).thenReturn(TEST_A1_JSON);
    context.setActResult(actResult);
    return context;
  }

  private static TestArrangeBuilder createRequestBuilder() {
    var requestBuilder = new TestArrangeBuilder();
    requestBuilder.method(HttpMethod.GET);
    return requestBuilder;
  }
}
