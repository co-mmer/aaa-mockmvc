package io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.string;

import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestNormalizer.NORMALIZED_CAFE;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestNormalizer.NORMALIZED_CAFE_LIST;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestNormalizer.NORMALIZED_CAFE_MAP;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestNormalizer.NOT_NORMALIZED_CAFE;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestNormalizer.NOT_NORMALIZED_CAFE_MAP;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestNormalizer.NOT_NORMALIZED_LIST;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.HashMap;
import java.util.LinkedHashMap;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

class TestArrangeNormalizerTest {

  @Test
  void GIVEN_null_WHEN_normalizeObject_THEN_throw_NullPointerException() {
    assertThrows(NullPointerException.class, () -> TestArrangeNormalizer.normalizeObject(null));
  }

  @Test
  void GIVEN_word_WHEN_normalizeObject_THEN_return_expected_object() {
    // Act
    var result = TestArrangeNormalizer.normalizeObject(NOT_NORMALIZED_CAFE);

    // Assert
    assertThat(result, is(NORMALIZED_CAFE));
  }

  @Test
  void GIVEN_null_WHEN_normalizeCollection_THEN_throw_NullPointerException() {
    assertThrows(NullPointerException.class, () -> TestArrangeNormalizer.normalizeCollection(null));
  }

  @Test
  void GIVEN_words_WHEN_normalizeCollection_THEN_return_expected_list() {
    // Act
    var result = TestArrangeNormalizer.normalizeCollection(NOT_NORMALIZED_LIST);

    // Assert
    assertThat(result, is(NORMALIZED_CAFE_LIST));
  }

  @Test
  @SneakyThrows
  void GIVEN_words_WHEN_normalizeMap_THEN_return_expected_map() {
    // Act
    var result = TestArrangeNormalizer.normalizeMap(NOT_NORMALIZED_CAFE_MAP);

    // Assert
    assertThat(result, is(NORMALIZED_CAFE_MAP));
  }

  @Test
  @SneakyThrows
  void GIVEN_mapWithNullKey_WHEN_normalizeMap_THEN_keepNullKey_and_normalizeNonNullValues() {
    // Arrange
    var input = new LinkedHashMap<String, String>();
    input.put(null, "Café");
    input.put("Key", "Résumé");

    // Act
    var result = TestArrangeNormalizer.normalizeMap(input);

    // Assert
    assertThat(result.size(), is(2));
    assertThat(result.get(null), is(Normalizer.normalize("Café", Form.NFC)));
    assertThat(result.get("Key"), is(Normalizer.normalize("Résumé", Form.NFC)));
  }

  @Test
  @SneakyThrows
  void GIVEN_mapWithNullValue_WHEN_normalizeMap_THEN_keepNullValue() {
    // Arrange
    var input = new HashMap<String, String>();
    input.put("Key", null);

    // Act
    var result = TestArrangeNormalizer.normalizeMap(input);

    // Assert
    assertThat(result.size(), is(1));
    assertThat(result.get("Key"), is((String) null));
  }

  @Test
  @SuppressWarnings("all")
  void GIVEN_mapWithCollidingKeys_WHEN_normalizeMap_THEN_throw_TestArrangeNormalizerException() {
    var input = new HashMap<String, String>();
    input.put("Cafe\u0301", "value1");
    input.put("Caf\u00E9", "value2");

    // Act
    var ex =
        assertThrows(
            TestArrangeNormalizerException.class, () -> TestArrangeNormalizer.normalizeMap(input));

    // Assert
    assertThat(
        ex.getMessage(),
        is(
            "Key collision after normalization: originals \"Café\" [U+0043 U+0061 U+0066 U+0065 U+0301] vs \"Café\" [U+0043 U+0061 U+0066 U+00E9]"));
  }
}
