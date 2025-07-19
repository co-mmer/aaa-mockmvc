package io.github.co_mmer.aaamockmvc.ej.test.web.internal.arrange.base.url;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.annotation.Since;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.request.TestRequestUrlDto;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.http.HttpMethod;

@Since("1.0.0")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestArrangeUrlUtils {

  @Since("1.0.0")
  public static void setUri(
      @NonNull TestRequestUrlDto destination,
      @NonNull HttpMethod method,
      @NonNull String path,
      @NonNull Object... variable) {

    var checkedVariable = filterSupported(variable);
    var uri = TestRequestUriMapper.mapTo(path, checkedVariable);
    setUri(destination, method, uri);
  }

  private static List<Object> filterSupported(Object... variable) {
    return Arrays.stream(variable).filter(TestArrangeUrlUtils::isSupported).toList();
  }

  private static boolean isSupported(Object object) {
    return object instanceof String
        || object instanceof Character
        || object instanceof Integer
        || object instanceof Double
        || object instanceof Float
        || object instanceof Boolean;
  }

  @Since("1.0.0")
  public static void setUri(
      @NonNull TestRequestUrlDto destination, @NonNull HttpMethod method, @NonNull URI uri) {
    destination.setMethod(method);
    destination.setUri(uri);
  }

  @Since("1.0.0")
  public static void addQuery(@NonNull TestRequestUrlDto destination, String key, String value) {
    destination.getQuery().put(key, value);
  }

  @Since("1.0.0")
  public static void addQuery(
      @NonNull TestRequestUrlDto destination, @NonNull Map<String, String> params) {

    destination.getQuery().putAll(params);
  }
}
