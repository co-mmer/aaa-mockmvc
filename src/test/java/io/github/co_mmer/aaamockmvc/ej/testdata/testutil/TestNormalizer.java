package io.github.co_mmer.aaamockmvc.ej.testdata.testutil;

import java.text.Normalizer;
import java.text.Normalizer.Form;
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
}
