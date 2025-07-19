package io.github.co_mmer.aaamockmvc.ej.test.web.internal.arrange.methods.core;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.annotation.Since;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.arrange.base.validation.TestArrangeValidator;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.request.TestRequestHeadDto;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.http.MediaType;

@Since("1.0.0")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestArrangeHeadUtils {

  @Since("1.0.0")
  public static void setAccepts(
      @NonNull TestRequestHeadDto destination, @NonNull MediaType... accepts) {

    TestArrangeValidator.nonNullAccepts(accepts);
    destination.setAccepts(Arrays.stream(accepts).toList());
  }

  @Since("1.0.0")
  public static void setContentTypes(
      @NonNull TestRequestHeadDto destination, @NonNull MediaType contentType) {

    destination.setContentType(contentType);
  }

  @Since("1.0.0")
  public static void addKeyValue(
      @NonNull TestRequestHeadDto destination, String key, Object value) {

    destination.getKeyValue().computeIfAbsent(key, k -> new ArrayList<>()).add(value);
  }

  @Since("1.0.0")
  public static void addKeyValue(
      @NonNull TestRequestHeadDto destination, @NonNull Map<String, List<Object>> keyValue) {

    destination.setKeyValue(keyValue);
  }
}
