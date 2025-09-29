package io.github.co_mmer.aaamockmvc.ej.test.web.internal.arrange.methods.core;

import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.arrange.methods.core.TestArrangeHeaderSetter.addKeyValue;
import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.arrange.methods.core.TestArrangeHeaderSetter.setAccepts;
import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.arrange.methods.core.TestArrangeHeaderSetter.setContentType;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.HEADER_KEY_0;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.HEADER_KEY_1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.HEADER_KEY_2;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.HEADER_MAP_1_2;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.HEADER_VALUE_0;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.HEADER_VALUE_1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.HEADER_VALUE_2;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_XML;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.request.TestRequestHeadDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class TestArrangeHeadSetterTest {

  private TestRequestHeadDto headDto;

  @BeforeEach
  void setUp() {
    this.headDto = new TestRequestHeadDto();
  }

  @Nested
  class setAccepts {

    @Test
    void GIVEN_types_null_WHEN_setAccepts_THEN_throw_IllegalArgumentException() {
      // Act
      var ex = assertThrows(IllegalArgumentException.class, () -> setAccepts(headDto, null, null));

      // Assert
      assertThat(ex.getMessage(), is("Accepts must not contain null values"));
    }

    @Test
    void GIVEN_type_WHEN_setAccepts_THEN_getAccepts_returned_expected_value() {
      // Act
      setAccepts(headDto, APPLICATION_JSON);

      // Assert
      assertThat(headDto.getAccepts(), contains(APPLICATION_JSON));
    }

    @Test
    void GIVEN_types_WHEN_setAccepts_THEN_getAccepts_returned_expected_values() {
      // Act
      setAccepts(headDto, APPLICATION_JSON, APPLICATION_XML);

      // Assert
      assertThat(headDto.getAccepts(), contains(APPLICATION_JSON, APPLICATION_XML));
    }
  }

  @Nested
  class setContentTypes {

    @Test
    void GIVEN_type_WHEN_setContentType_THEN_getContentType_returned_expected_value() {
      // Act
      setContentType(headDto, APPLICATION_JSON);

      // Assert
      assertThat(headDto.getContentType(), is(APPLICATION_JSON));
    }
  }

  @Nested
  class addKeyValue {

    @Test
    void GIVEN_key_value_1_WHEN_addKeyValue_THEN_getKeyValue_returned_expected_value() {
      // Act
      addKeyValue(headDto, HEADER_KEY_1, HEADER_VALUE_1);

      // Assert
      assertThat(headDto.getKeyValue().size(), is(1));
      assertThat(headDto.getKeyValue().get(HEADER_KEY_1), contains(HEADER_VALUE_1));
    }

    @Test
    void GIVEN_key_value_2_WHEN_addKeyValue_THEN_getKeyValue_returned_expected_value() {
      // Act
      addKeyValue(headDto, HEADER_KEY_1, HEADER_VALUE_1);
      addKeyValue(headDto, HEADER_KEY_2, HEADER_VALUE_2);

      // Assert
      assertThat(headDto.getKeyValue().size(), is(2));
      assertThat(headDto.getKeyValue().get(HEADER_KEY_1), contains(HEADER_VALUE_1));
      assertThat(headDto.getKeyValue().get(HEADER_KEY_2), contains(HEADER_VALUE_2));
    }

    @Test
    void GIVEN_key_value_map_WHEN_addKeyValue_THEN_getKeyValue_returned_expected_value() {
      // Act
      addKeyValue(headDto, HEADER_MAP_1_2);

      // Assert
      assertThat(headDto.getKeyValue(), is(HEADER_MAP_1_2));
    }

    @Test
    void
        GIVEN_addKeyValue_key_0_WHEN_addKeyValue_with_map_key_1_2_THEN_getKeyValue_returned_expected_values() {
      // Arrange
      addKeyValue(headDto, HEADER_KEY_0, HEADER_VALUE_0);

      // Act
      addKeyValue(headDto, HEADER_MAP_1_2);

      // Assert
      assertThat(headDto.getKeyValue(), is(HEADER_MAP_1_2));
    }
  }
}
