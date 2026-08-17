package io.github.co_mmer.aaamockmvc.ej.test.web.internal.request;

import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.RequestValidation.requireNonBlank;
import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.RequestValidation.requireNonEmpty;
import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.RequestValidation.requireNonNull;
import static org.springframework.http.HttpHeaders.ACCEPT;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.metadata.Since;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import lombok.EqualsAndHashCode;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedCaseInsensitiveMap;

@Since("2.0.2")
@EqualsAndHashCode
public final class RequestHeaders {

  private static final String NULL_HEADERS_MESSAGE = "Headers must not be null";
  private static final String NULL_HEADER_NAME_MESSAGE = "Header name must not be null";
  private static final String BLANK_HEADER_NAME_MESSAGE = "Header name must not be blank";
  private static final String INVALID_HEADER_NAME_MESSAGE =
      "Header name '%s' contains invalid characters";
  private static final String DUPLICATE_HEADER_NAME_MESSAGE =
      "Header '%s' is defined more than once using different casing";
  private static final String EMPTY_HEADER_VALUES_MESSAGE =
      "Header '%s' must contain at least one value";
  private static final String NULL_HEADER_VALUE_MESSAGE = "Header '%s' must not have a null value";
  private static final String NULL_HEADER_VALUE_AT_POSITION_MESSAGE =
      "Header '%s' value at position %d must not be null";
  private static final String INVALID_HEADER_VALUE_MESSAGE =
      "Header '%s' value must not contain carriage return or line feed characters";
  private static final String NULL_AUTHORIZATION_TOKEN_MESSAGE =
      "Authorization token must not be null";
  private static final String BLANK_AUTHORIZATION_TOKEN_MESSAGE =
      "Authorization token must not be blank";
  private static final String NULL_ACCEPTED_MEDIA_TYPES_MESSAGE =
      "Accepted media types must not be null";
  private static final String EMPTY_ACCEPTED_MEDIA_TYPES_MESSAGE =
      "Accept header must contain at least one media type";
  private static final String NULL_ACCEPTED_MEDIA_TYPE_MESSAGE =
      "Accepted media type at position %d must not be null";

  private static final String NULL_CONTENT_TYPE_MESSAGE = "Content type must not be null";
  private static final String BLANK_CONTENT_TYPE_MESSAGE = "Content type must not be blank";
  private static final String INVALID_CONTENT_TYPE_MESSAGE =
      "Content type '%s' is not a valid media type";

  private static final Pattern VALID_HEADER_NAME = Pattern.compile("[!#$%&'*+\\-.^_`|~0-9A-Za-z]+");

  private Map<String, List<String>> values = new LinkedCaseInsensitiveMap<>();

  @Since("2.0.2")
  public void set(Map<String, ? extends List<?>> headers) {
    requireNonNull(headers, NULL_HEADERS_MESSAGE);
    var checkedHeaders = new LinkedCaseInsensitiveMap<List<String>>();

    headers.forEach(
        (name, headerValues) -> {
          var checkedValues = validateValues(name, headerValues);

          if (checkedHeaders.containsKey(name)) {
            throw new IllegalArgumentException(DUPLICATE_HEADER_NAME_MESSAGE.formatted(name));
          }

          checkedHeaders.put(name, checkedValues);
        });

    this.values = checkedHeaders;
  }

  @Since("2.0.2")
  public Map<String, List<String>> values() {
    var copy = new LinkedCaseInsensitiveMap<List<String>>();
    values.forEach((name, headerValues) -> copy.put(name, List.copyOf(headerValues)));
    return Collections.unmodifiableMap(copy);
  }

  @Since("2.0.2")
  public RequestHeaders auth(String token) {
    requireNonNull(token, NULL_AUTHORIZATION_TOKEN_MESSAGE);
    requireNonEmpty(token, BLANK_AUTHORIZATION_TOKEN_MESSAGE);

    validateValue(AUTHORIZATION, token);
    replace(AUTHORIZATION, List.of(token));
    return this;
  }

  @Since("2.0.2")
  public RequestHeaders accept(MediaType... types) {
    requireNonNull(types, NULL_ACCEPTED_MEDIA_TYPES_MESSAGE);
    requireNonEmpty(EMPTY_ACCEPTED_MEDIA_TYPES_MESSAGE, types);

    for (var i = 0; i < types.length; i++) {
      requireNonNull(types[i], NULL_ACCEPTED_MEDIA_TYPE_MESSAGE.formatted(i + 1));
    }

    replace(ACCEPT, Arrays.stream(types).map(MediaType::toString).toList());
    return this;
  }

  @Since("2.0.2")
  public RequestHeaders contentType(MediaType mediaType) {
    requireNonNull(mediaType, NULL_CONTENT_TYPE_MESSAGE);
    replace(CONTENT_TYPE, List.of(mediaType.toString()));
    return this;
  }

  @Since("2.0.2")
  public RequestHeaders contentType(String mediaType) {
    requireNonNull(mediaType, NULL_CONTENT_TYPE_MESSAGE);
    requireNonEmpty(mediaType, BLANK_CONTENT_TYPE_MESSAGE);

    try {
      var checkedMediaType = MediaType.parseMediaType(mediaType);
      replace(CONTENT_TYPE, List.of(checkedMediaType.toString()));
      return this;
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException(INVALID_CONTENT_TYPE_MESSAGE.formatted(mediaType), e);
    }
  }

  @Since("2.0.2")
  public void add(String name, String value) {
    validateName(name);
    validateValue(name, value);
    values.computeIfAbsent(name, ignored -> new ArrayList<>()).add(value);
  }

  private static List<String> validateValues(String name, List<?> headerValues) {
    validateName(name);
    requireNonBlank(headerValues, EMPTY_HEADER_VALUES_MESSAGE.formatted(name));

    var checkedValues = new ArrayList<String>();

    for (var index = 0; index < headerValues.size(); index++) {
      var value = headerValues.get(index);
      requireNonNull(value, NULL_HEADER_VALUE_AT_POSITION_MESSAGE.formatted(name, index + 1));

      var checkedValue = String.valueOf(value);
      validateValue(name, checkedValue);
      checkedValues.add(checkedValue);
    }

    return checkedValues;
  }

  private static void validateName(String name) {
    requireNonNull(name, NULL_HEADER_NAME_MESSAGE);
    requireNonEmpty(name, BLANK_HEADER_NAME_MESSAGE);

    if (!VALID_HEADER_NAME.matcher(name).matches()) {
      throw new IllegalArgumentException(INVALID_HEADER_NAME_MESSAGE.formatted(name));
    }
  }

  private static void validateValue(String name, String value) {
    requireNonNull(value, NULL_HEADER_VALUE_MESSAGE.formatted(name));

    if (value.indexOf('\r') >= 0 || value.indexOf('\n') >= 0) {
      throw new IllegalArgumentException(INVALID_HEADER_VALUE_MESSAGE.formatted(name));
    }
  }

  private void replace(String name, List<String> headerValues) {
    values.put(name, new ArrayList<>(headerValues));
  }
}
