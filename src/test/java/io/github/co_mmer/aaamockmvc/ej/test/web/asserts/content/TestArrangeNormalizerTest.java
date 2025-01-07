package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.content;

import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestNormalizer.WORD_LIST;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestNormalizer.WORD_LIST_NORMALIZED;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestNormalizer.WORD_MAP;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestNormalizer.WORD_MAP_NORMALIZED;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestNormalizer.WORD_OBJECT;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestNormalizer.WORD_OBJECT_NORMALIZED;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

@SuppressWarnings("ConstantConditions")
class TestArrangeNormalizerTest {

  @Test
  void GIVEN_null_WHEN_normalizeObject_THEN_throw_NullPointerException() {
    assertThrows(NullPointerException.class, () -> TestArrangeNormalizer.normalizeObject(null));
  }

  @Test
  void GIVEN_word_WHEN_normalizeObject_THEN_return_expected_object() {
    // Act
    var result = TestArrangeNormalizer.normalizeObject(WORD_OBJECT);

    // Assert
    assertThat(result, is(WORD_OBJECT_NORMALIZED));
  }

  @Test
  void GIVEN_null_WHEN_normalizeCollection_THEN_throw_NullPointerException() {
    assertThrows(NullPointerException.class, () -> TestArrangeNormalizer.normalizeCollection(null));
  }

  @Test
  void GIVEN_words_WHEN_normalizeCollection_THEN_return_expected_list() {
    // Act
    var result = TestArrangeNormalizer.normalizeCollection(WORD_LIST);

    // Assert
    assertThat(result, is(WORD_LIST_NORMALIZED));
  }

  @Test
  void GIVEN_null_WHEN_normalizeAsObjects_THEN_throw_NullPointerException() {
    assertThrows(NullPointerException.class, () -> TestArrangeNormalizer.normalizeAsObjects(null));
  }

  @Test
  void GIVEN_words_WHEN_normalizeAsObjects_THEN_return_expected_list() {
    // Act
    var result = TestArrangeNormalizer.normalizeAsObjects(WORD_LIST);

    // Assert
    assertThat(result, is(WORD_LIST_NORMALIZED));
  }

  @Test
  void GIVEN_words_WHEN_normalizeMap_THEN_return_expected_map() {
    // Act
    var result = TestArrangeNormalizer.normalizeMap(WORD_MAP);

    // Assert
    assertThat(result, is(WORD_MAP_NORMALIZED));
  }
}
