package ej.aaamockmvc.test.web.arrange.base.url;

import java.net.URI;
import java.util.List;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Utility class for mapping URL templates to fully expanded URIs with path variables.
 *
 * <p>This class provides methods to convert a URL template and a list of path variables into a
 * complete and valid {@code URI}. The path variables are expanded into the template, and the
 * resulting URI is encoded to ensure it conforms to URL standards.
 *
 * <p>The constructor is private, and the class is designed to be used statically.
 *
 * @since 1.0.0
 */
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestRequestUriMapper {

  /**
   * Maps a URL template to a fully expanded and encoded {@code URI}.
   *
   * <p>This method takes a URL template and a list of path variables, expanding the variables into
   * the placeholders in the URL template, and returns a fully encoded {@code URI}.
   *
   * @param url the URL template to be expanded (must not be {@code null})
   * @param variables the list of variables to substitute into the URL template (must not be {@code
   *     null})
   * @return a fully expanded and encoded {@code URI}
   * @throws NullPointerException if the {@code url} or {@code variables} is {@code null}
   * @throws IllegalArgumentException if there are not enough variables provided to fully expand the
   *     {@code url} template
   * @since 1.0.0
   */
  public static URI mapTo(@NonNull String url, @NonNull List<Object> variables) {
    return UriComponentsBuilder.fromUriString(url)
        .buildAndExpand(variables.toArray())
        .encode()
        .toUri();
  }
}
