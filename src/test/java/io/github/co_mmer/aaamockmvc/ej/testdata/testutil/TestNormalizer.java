package io.github.co_mmer.aaamockmvc.ej.testdata.testutil;

import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestNormalizer {

  public static final String WORD_OBJECT = "Café";
  public static final String WORD_OBJECT_NORMALIZED = Normalizer.normalize(WORD_OBJECT, Form.NFC);
  public static final List<String> WORD_LIST = List.of("Café", "Naïve", "Résumé");
  public static final List<String> WORD_LIST_NORMALIZED =
      WORD_LIST.stream().map(element -> Normalizer.normalize(element, Form.NFC)).toList();
  public static final Set<String> WORD_SET = Set.of("Café", "Naïve", "Résumé");
  public static final Set<String> WORD_SET_NORMALIZED =
      WORD_SET.stream()
          .map(element -> Normalizer.normalize(element, Form.NFC))
          .collect(Collectors.toSet());

  public static final Map<String, String> WORD_MAP = Map.of("Key1", "Café", "Key2", "Résumé");
  public static final Map<String, String> WORD_MAP_NORMALIZED =
      WORD_MAP.entrySet().stream()
          .collect(
              Collectors.toMap(
                  entry -> Normalizer.normalize(entry.getKey(), Form.NFC),
                  entry -> Normalizer.normalize(entry.getValue(), Form.NFC)));
}
