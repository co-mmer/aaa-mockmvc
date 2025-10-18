package io.github.co_mmer.aaamockmvc.ej.test.web.internal.act.strategy;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.annotation.Since;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestArrangeResult;
import lombok.NonNull;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

@Since("1.0.0")
public interface TestRequestStrategy {

  @Since("2.0.0")
  MockHttpServletRequestBuilder apply(@NonNull TestArrangeResult request);
}
