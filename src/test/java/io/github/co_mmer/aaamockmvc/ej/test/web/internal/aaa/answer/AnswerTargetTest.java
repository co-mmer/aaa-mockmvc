package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.answer;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class AnswerTargetTest {

  @Nested
  class result {

    @Test
    void GIVEN_type_WHEN_create_THEN_return_type() {
      // Arrange
      var resultType = String.class;

      // Act
      var target = AnswerTarget.result(resultType);

      // Assert
      assertThat(target.type(), is(resultType));
    }

    @Test
    void GIVEN_null_WHEN_create_THEN_throw_domain_error() {
      // Arrange
      Class<String> resultType = null;

      // Act
      var exception =
          assertThrows(IllegalArgumentException.class, () -> AnswerTarget.result(resultType));

      // Assert
      assertThat(exception.getMessage(), is("Result type must not be null"));
    }
  }

  @Nested
  class collectionElement {

    @Test
    void GIVEN_type_WHEN_create_THEN_return_type() {
      // Arrange
      var elementType = String.class;

      // Act
      var target = AnswerTarget.collectionElement(elementType);

      // Assert
      assertThat(target.type(), is(elementType));
    }

    @Test
    void GIVEN_null_WHEN_create_THEN_throw_domain_error() {
      // Arrange
      Class<String> elementType = null;

      // Act
      var exception =
          assertThrows(
              IllegalArgumentException.class, () -> AnswerTarget.collectionElement(elementType));

      // Assert
      assertThat(exception.getMessage(), is("Collection element type must not be null"));
    }
  }

  @Nested
  class listElement {

    @Test
    void GIVEN_type_WHEN_create_THEN_return_type() {
      // Arrange
      var elementType = String.class;

      // Act
      var target = AnswerTarget.listElement(elementType);

      // Assert
      assertThat(target.type(), is(elementType));
    }

    @Test
    void GIVEN_null_WHEN_create_THEN_throw_domain_error() {
      // Arrange
      Class<String> elementType = null;

      // Act
      var exception =
          assertThrows(IllegalArgumentException.class, () -> AnswerTarget.listElement(elementType));

      // Assert
      assertThat(exception.getMessage(), is("List element type must not be null"));
    }
  }

  @Nested
  class setElement {

    @Test
    void GIVEN_type_WHEN_create_THEN_return_type() {
      // Arrange
      var elementType = String.class;

      // Act
      var target = AnswerTarget.setElement(elementType);

      // Assert
      assertThat(target.type(), is(elementType));
    }

    @Test
    void GIVEN_null_WHEN_create_THEN_throw_domain_error() {
      // Arrange
      Class<String> elementType = null;

      // Act
      var exception =
          assertThrows(IllegalArgumentException.class, () -> AnswerTarget.setElement(elementType));

      // Assert
      assertThat(exception.getMessage(), is("Set element type must not be null"));
    }
  }

  @Nested
  class mapKey {

    @Test
    void GIVEN_type_WHEN_create_THEN_return_type() {
      // Arrange
      var keyType = String.class;

      // Act
      var target = AnswerTarget.mapKey(keyType);

      // Assert
      assertThat(target.type(), is(keyType));
    }

    @Test
    void GIVEN_null_WHEN_create_THEN_throw_domain_error() {
      // Arrange
      Class<String> keyType = null;

      // Act
      var exception =
          assertThrows(IllegalArgumentException.class, () -> AnswerTarget.mapKey(keyType));

      // Assert
      assertThat(exception.getMessage(), is("Map key type must not be null"));
    }
  }

  @Nested
  class mapValue {

    @Test
    void GIVEN_type_WHEN_create_THEN_return_type() {
      // Arrange
      var valueType = Integer.class;

      // Act
      var target = AnswerTarget.mapValue(valueType);

      // Assert
      assertThat(target.type(), is(valueType));
    }

    @Test
    void GIVEN_null_WHEN_create_THEN_throw_domain_error() {
      // Arrange
      Class<Integer> valueType = null;

      // Act
      var exception =
          assertThrows(IllegalArgumentException.class, () -> AnswerTarget.mapValue(valueType));

      // Assert
      assertThat(exception.getMessage(), is("Map value type must not be null"));
    }
  }

  @Nested
  class valueObject {

    @Test
    void GIVEN_same_type_WHEN_compare_THEN_equal() {
      // Arrange
      var first = AnswerTarget.result(String.class);
      var second = AnswerTarget.result(String.class);

      // Act
      var equal = first.equals(second);
      var sameHashCode = first.hashCode() == second.hashCode();

      // Assert
      assertThat(equal, is(true));
      assertThat(sameHashCode, is(true));
    }

    @Test
    void GIVEN_different_type_WHEN_compare_THEN_not_equal() {
      // Arrange
      var first = AnswerTarget.result(String.class);
      var second = AnswerTarget.result(Integer.class);

      // Act
      var equal = first.equals(second);

      // Assert
      assertThat(equal, is(false));
      assertThat(first, is(not(second)));
    }

    @Test
    void GIVEN_type_WHEN_toString_THEN_return_type_name() {
      // Arrange
      var target = AnswerTarget.result(String.class);

      // Act
      var result = target.toString();

      // Assert
      assertThat(result, is(String.class.getTypeName()));
    }
  }
}
