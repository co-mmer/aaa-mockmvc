package io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.string;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.annotation.Since;
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
    final String s = (actual instanceof CharSequence cs) ? cs.toString() : actual.toString();
    return Normalizer.normalize(s, form);
  }

  @Since("1.4.0")
  public static List<String> normalizeCollection(Collection<?> actual) {
    return normalizeCollection(actual, Form.NFC);
  }

  @Since("1.4.0")
  public static List<String> normalizeCollection(Collection<?> actual, Form form) {
    return actual.stream().map(elem -> normalizeObject(elem, form)).toList();
  }

  @Since("1.3.0")
  public static Map<String, String> normalizeMap(Map<?, ?> actual)
      throws TestArrangeNormalizerException {
    return normalizeMap(actual, Form.NFC);
  }

  @Since("1.3.0")
  public static Map<String, String> normalizeMap(Map<?, ?> actual, Form form)
      throws TestArrangeNormalizerException {
    var result = new LinkedHashMap<String, String>();
    var firstOriginalKeyByNormalized = new HashMap<String, Object>();

    for (var e : actual.entrySet()) {
      var key = e.getKey();
      var normalizedKey = getNormalized(form, key);

      var value = e.getValue();
      var normalizedValue = getNormalized(form, value);

      var firstOriginal = firstOriginalKeyByNormalized.putIfAbsent(normalizedKey, key);
      if (firstOriginal != null) {
        var left = describeOriginal(firstOriginal);
        var right = describeOriginal(key);
        throw new TestArrangeNormalizerException(
            "Key collision after normalization: originals " + left + " vs " + right);
      }

      result.put(normalizedKey, normalizedValue);
    }
    return result;
  }

  private static String getNormalized(Form form, Object object) {
    return (object == null) ? null : normalizeObject(object, form);
  }

  private static String describeOriginal(Object o) {
    var s = (o instanceof CharSequence cs) ? cs.toString() : String.valueOf(o);
    return "\"" + s + "\" [" + codePointsOf(s) + "]";
  }

  private static String codePointsOf(String s) {
    return s.codePoints()
        .mapToObj(cp -> String.format("U+%04X", cp))
        .collect(Collectors.joining(" "));
  }
}
