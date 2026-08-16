package io.github.co_mmer.aaamockmvc.ej.testdata.testutil;

import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_A1_JSON;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.mockmvc.model.RequestBuilder;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestAAAContext;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestActResult2;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestEnvironment;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestStepDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.test.web.servlet.MockMvc;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestContext {

  public static TestAAAContext createContext() {
    var context = new TestAAAContext(createTestEnvironment());
    context.setRequestBuilder(createRequestBuilder());
    return context;
  }

  public static TestAAAContext createContext(String stepName) {
    var context = new TestAAAContext(createTestEnvironment());
    context.setStep(new TestStepDto(stepName));
    context.setRequestBuilder(createRequestBuilder());
    return context;
  }

  private static TestEnvironment createTestEnvironment() {
    var mvc = mock(MockMvc.class);
    var objectMapper = new ObjectMapper();
    return new TestEnvironment(mvc, objectMapper);
  }

  public static TestAAAContext mockContext() {
    var context = new TestAAAContext(createTestEnvironment());
    context.setRequestBuilder(createRequestBuilder());

    var actResult = mock(TestActResult2.class);
    context.setActResult2(actResult);
    return context;
  }

  public static TestAAAContext mockContext(String stepName) {
    var context = new TestAAAContext(createTestEnvironment());
    context.setStep(new TestStepDto(stepName));

    var actResult = mock(TestActResult2.class);
    when(actResult.contentAsString()).thenReturn(TEST_A1_JSON);
    context.setActResult2(actResult);
    return context;
  }

  private static RequestBuilder createRequestBuilder() {
    var requestBuilder = new RequestBuilder();
    requestBuilder.method(
        io.github.co_mmer.aaamockmvc.ej.test.web.internal.mockmvc.model.HttpMethod.GET);
    return requestBuilder;
  }
}
