package ej.aaamockmvc.test.web.arrange.base.url;

import ej.aaamockmvc.test.web.request.model.TestRequestUrlDto;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * Utility class for arranging URLs in a test request.
 *
 * <p>This class provides static methods to configure the request path and path variables, as well
 * as adding parameters to the request URL. This class cannot be instantiated.
 *
 * @since 1.0.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestArrangeUrlUtils {

  /**
   * Sets the URI for the specified destination DTO by mapping the given path with the provided
   * variable arguments.
   *
   * <p>This method filters the provided variable arguments to ensure only supported types are used,
   * then maps the specified path to a URI using those variables. Finally, it sets the resulting URI
   * to the destination DTO.
   *
   * <p>Supported variable types include {@code String}, {@code Character}, {@code Integer}, {@code
   * Double}, {@code Float}, and {@code Boolean}. If any unsupported types are passed, they will be
   * filtered out.
   *
   * @param destination the destination DTO to which the URI will be set (must not be {@code null})
   * @param path the path template used to create the URI (must not be {@code null})
   * @param variable the optional variable arguments to substitute into the path template
   * @throws NullPointerException if any of the parameters {@code destination}, {@code path}, or any
   *     element in {@code variable} is {@code null}
   * @since 1.0.0
   */
  public static void setUri(
      @NonNull TestRequestUrlDto destination, @NonNull String path, @NonNull Object... variable) {

    var checkedVariable = filterSupported(variable);
    var uri = TestRequestUriMapper.mapTo(path, checkedVariable);
    setUri(destination, uri);
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

  /**
   * Sets the URI for the request URL.
   *
   * @param destination the destination DTO to which the URI will be set (must not be {@code null})
   * @param uri the {@link URI} to be assigned to the destination (must not be {@code null})
   * @throws NullPointerException if any of the parameters {@code destination}, {@code path}, or any
   *     element in {@code variable} is {@code null}
   * @since 1.0.0
   */
  public static void setUri(@NonNull TestRequestUrlDto destination, @NonNull URI uri) {
    destination.setUri(uri);
  }

  /**
   * Adds a parameter to the request URL.
   *
   * @param destination the destination {@code TestRequestUrlDto} to add the parameter to (must not
   *     be {@code null})
   * @param key the name of the parameter (must not be {@code null})
   * @param value the value of the parameter (must not be {@code null})
   * @throws NullPointerException if either {@code destination}, {@code key}, or {@code value} is
   *     {@code null}
   * @since 1.0.0
   */
  public static void addParam(@NonNull TestRequestUrlDto destination, String key, String value) {
    destination.getParam().put(key, value);
  }

  /**
   * Adds multiple parameters to the request URL.
   *
   * @param destination the destination {@code TestRequestUrlDto} to add parameters to (must not be
   *     {@code null})
   * @param params a map of parameter names and their corresponding values (must not be {@code
   *     null})
   * @throws NullPointerException if either {@code destination} or {@code params} is {@code null}
   * @since 1.0.0
   */
  public static void addParam(
      @NonNull TestRequestUrlDto destination, @NonNull Map<String, String> params) {

    destination.getParam().putAll(params);
  }
}
