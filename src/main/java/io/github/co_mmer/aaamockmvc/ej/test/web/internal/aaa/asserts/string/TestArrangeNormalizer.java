package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.string;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.metadata.Since;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@Since("1.3.0")
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestArrangeNormalizer {

  @Since("1.3.0")
  public static String normalizeObject(Object actual) {
    return normalizeObject(actual, Form.NFC);
  }

  @Since("1.4.0")
  public static String normalizeObject(Object actual, Form form) {
    return Normalizer.normalize(actual.toString(), form);
  }

  private static String handlingCharSequence(CharSequence cs) {
    return cs.toString();
  }

  @Since("1.4.0")
  public static List<String> normalizeCollection(Collection<?> actual) {
    return normalizeCollection(actual, Form.NFC);
  }

  @Since("1.4.0")
  public static List<String> normalizeCollection(Collection<?> actual, Form form) {
    return actual.stream().map(element -> normalizeObject(element, form)).toList();
  }

  @Since("1.3.0")
  public static Map<String, String> normalizeMap(Map<?, ?> actual) {
    return normalizeMap(actual, Form.NFC);
  }

  @Since("1.3.0")
  public static Map<String, String> normalizeMap(Map<?, ?> actual, Form form) {
    var result = new LinkedHashMap<String, String>();
    var originalKeys = new HashMap<String, Object>();

    for (var entry : actual.entrySet()) {
      var originalKey = entry.getKey();
      var normalizedKey = normalizeNullable(originalKey, form);

      if (originalKeys.containsKey(normalizedKey)) {
        throw keyCollision(form, originalKeys.get(normalizedKey), originalKey, normalizedKey);
      }

      originalKeys.put(normalizedKey, originalKey);
      result.put(normalizedKey, normalizeNullable(entry.getValue(), form));
    }

    return result;
  }

  private static String normalizeNullable(Object value, Form form) {
    return value == null ? null : normalizeObject(value, form);
  }

  private static IllegalArgumentException keyCollision(
      Form form, Object firstKey, Object secondKey, String normalizedKey) {

    var message =
        """
            Key collision after %s normalization: %s and %s both become %s
            """
            .formatted(form, describe(firstKey), describe(secondKey), describe(normalizedKey))
            .strip();

    return new IllegalArgumentException(message);
  }

  private static String describe(Object value) {
    var text = String.valueOf(value);
    return "\"%s\" [%s]".formatted(text, codePointsOf(text));
  }

  private static String codePointsOf(String value) {
    return value.codePoints().mapToObj("U+%04X"::formatted).collect(Collectors.joining(" "));
  }
}
