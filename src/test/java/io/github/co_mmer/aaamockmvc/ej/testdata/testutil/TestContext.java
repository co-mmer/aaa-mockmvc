package io.github.co_mmer.aaamockmvc.ej.testdata.testutil;

import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_A1_JSON;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestAAAContext;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestActResult;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestArrangeResult;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestEnvironment;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestStepMetadata;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.servlet.MockMvc;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestContext {

  public static TestAAAContext createContext() {
    var context = new TestAAAContext(createTestEnvironment());
    context.setArrangeResult(createTestArrangeResult());
    return context;
  }

  private static TestEnvironment createTestEnvironment() {
    var mvc = mock(MockMvc.class);
    var objectMapper = new ObjectMapper();
    return new TestEnvironment(mvc, objectMapper);
  }

  public static TestAAAContext mockContext() {
    var context = new TestAAAContext(createTestEnvironment());
    context.setStep(new TestStepMetadata(null));
    context.setArrangeResult(createTestArrangeResult());

    var actResult = mock(TestActResult.class);
    when(actResult.contentAsString()).thenReturn(TEST_A1_JSON);
    context.setActResult(actResult);
    return context;
  }

  private static TestArrangeResult createTestArrangeResult() {
    var arrange = new TestArrangeResult();
    arrange.getUrl().setMethod(HttpMethod.GET);
    return arrange;
  }
}
