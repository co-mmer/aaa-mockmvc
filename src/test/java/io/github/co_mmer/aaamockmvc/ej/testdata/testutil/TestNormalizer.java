package io.github.co_mmer.aaamockmvc.ej.testdata.testutil;

import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestNormalizer {

  public static final String NOT_NORMALIZED_CAFE = "Café";
  public static final String NORMALIZED_CAFE = Normalizer.normalize(NOT_NORMALIZED_CAFE, Form.NFC);
  public static final List<String> NOT_NORMALIZED_LIST = List.of("Café", "Naïve", "Résumé");
  public static final List<String> NORMALIZED_CAFE_LIST =
      NOT_NORMALIZED_LIST.stream().map(element -> Normalizer.normalize(element, Form.NFC)).toList();

  public static final Map<String, String> NOT_NORMALIZED_CAFE_MAP =
      Map.of("Key1", "Café", "Key2", "Résumé");
  public static final Map<String, String> NORMALIZED_CAFE_MAP =
      NOT_NORMALIZED_CAFE_MAP.entrySet().stream()
          .collect(
              Collectors.toMap(
                  entry -> Normalizer.normalize(entry.getKey(), Form.NFC),
                  entry -> Normalizer.normalize(entry.getValue(), Form.NFC)));

  public static final Map<Object, Object> COLLISION_CHARSEQ_FIRST_NONCHARSEQ_SECOND;
  public static final Map<Object, Object> COLLISION_NONCHARSEQ_FIRST_CHARSEQ_SECOND;

  static {
    var map1 = new LinkedHashMap<>();
    map1.put("Cafe\u0301", "v1");
    map1.put(
        new Object() {
          @Override
          public String toString() {
            return "Caf\u00E9";
          }
        },
        "v2");

    COLLISION_CHARSEQ_FIRST_NONCHARSEQ_SECOND = Map.copyOf(map1);

    var map2 = new LinkedHashMap<>();
    map2.put(
        new Object() {
          @Override
          public String toString() {
            return "Cafe\u0301";
          } // e + combining accent
        },
        "v1");
    map2.put("Caf\u00E9", "v2");
    COLLISION_NONCHARSEQ_FIRST_CHARSEQ_SECOND = Map.copyOf(map2);
  }
}
