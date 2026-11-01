package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.answer;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class AnswerTypeTest {

  @Nested
  class result {

    @Test
    void GIVEN_type_WHEN_create_THEN_return_type() {
      // Arrange
      var resultType = String.class;

      // Act
      var target = AnswerType.result(resultType);

      // Assert
      assertThat(target.type(), is(resultType));
    }

    @Test
    void GIVEN_null_WHEN_create_THEN_throw_domain_error() {
      // Arrange
      Class<String> resultType = null;

      // Act
      var exception =
          assertThrows(IllegalArgumentException.class, () -> AnswerType.result(resultType));

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
      var target = AnswerType.collectionElement(elementType);

      // Assert
      assertThat(target.type(), is(elementType));
    }

    @Test
    void GIVEN_null_WHEN_create_THEN_throw_domain_error() {
      // Act
      var exception =
          assertThrows(IllegalArgumentException.class, () -> AnswerType.collectionElement(null));

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
      var target = AnswerType.listElement(elementType);

      // Assert
      assertThat(target.type(), is(elementType));
    }

    @Test
    void GIVEN_null_WHEN_create_THEN_throw_domain_error() {
      // Act
      var exception =
          assertThrows(IllegalArgumentException.class, () -> AnswerType.listElement(null));

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
      var target = AnswerType.setElement(elementType);

      // Assert
      assertThat(target.type(), is(elementType));
    }

    @Test
    void GIVEN_null_WHEN_create_THEN_throw_domain_error() {
      // Act
      var exception =
          assertThrows(IllegalArgumentException.class, () -> AnswerType.setElement(null));

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
      var target = AnswerType.mapKey(keyType);

      // Assert
      assertThat(target.type(), is(keyType));
    }

    @Test
    void GIVEN_null_WHEN_create_THEN_throw_domain_error() {
      // Act
      var exception = assertThrows(IllegalArgumentException.class, () -> AnswerType.mapKey(null));

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
      var target = AnswerType.mapValue(valueType);

      // Assert
      assertThat(target.type(), is(valueType));
    }

    @Test
    void GIVEN_null_WHEN_create_THEN_throw_domain_error() {
      // Act
      var exception = assertThrows(IllegalArgumentException.class, () -> AnswerType.mapValue(null));

      // Assert
      assertThat(exception.getMessage(), is("Map value type must not be null"));
    }
  }
}
