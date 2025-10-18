package io.github.co_mmer.aaamockmvc.ej.test.web.internal.arrange.methods.core;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.annotation.Since;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.request.TestRequestHeadDto;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.MediaType;

@Since("1.0.0")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestArrangeHeaderSetter {

  @Since("1.0.0")
  public static void setAccepts(TestRequestHeadDto destination, MediaType... accepts) {
    TestArrangeValidator.nonNullAccepts(accepts);
    destination.setAccepts(Arrays.stream(accepts).toList());
  }

  @Since("1.0.0")
  public static void setContentType(TestRequestHeadDto destination, MediaType contentType) {
    destination.setContentType(contentType);
  }

  @Since("1.0.0")
  public static void addKeyValue(TestRequestHeadDto destination, String key, Object value) {
    destination.getKeyValue().computeIfAbsent(key, k -> new ArrayList<>()).add(value);
  }

  @Since("1.0.0")
  public static void addKeyValue(TestRequestHeadDto destination, Map<String, List<Object>> map) {
    destination.setKeyValue(map);
  }
}
