package ej.aaamockmvc.test.web.arrange.base.url;

import static ej.aaamockmvc.test.testdata.testutil.TestParameter.TEST_PARAM_KEY_0;
import static ej.aaamockmvc.test.testdata.testutil.TestParameter.TEST_PARAM_KEY_1;
import static ej.aaamockmvc.test.testdata.testutil.TestParameter.TEST_PARAM_KEY_2;
import static ej.aaamockmvc.test.testdata.testutil.TestParameter.TEST_PARAM_KEY_VALUE_MAP_1_2;
import static ej.aaamockmvc.test.testdata.testutil.TestParameter.TEST_PARAM_VALUE_0;
import static ej.aaamockmvc.test.testdata.testutil.TestParameter.TEST_PARAM_VALUE_1;
import static ej.aaamockmvc.test.testdata.testutil.TestParameter.TEST_PARAM_VALUE_2;
import static ej.aaamockmvc.test.testdata.testutil.TestValue.TEST_PATH;
import static ej.aaamockmvc.test.testdata.testutil.TestValue.TEST_PATH_VAR1;
import static ej.aaamockmvc.test.testdata.testutil.TestValue.TEST_PATH_VARIABLE_2;
import static ej.aaamockmvc.test.testdata.testutil.TestValue.TEST_PATH_VARIABLE_3;
import static ej.aaamockmvc.test.testdata.testutil.TestValue.TEST_PATH_VARIABLE_6;
import static ej.aaamockmvc.test.testdata.testutil.TestValue.TEST_URI;
import static ej.aaamockmvc.test.testdata.testutil.TestValue.VAR_BOOLEAN_TRUE;
import static ej.aaamockmvc.test.testdata.testutil.TestValue.VAR_CHAR_1;
import static ej.aaamockmvc.test.testdata.testutil.TestValue.VAR_DOUBLE_1;
import static ej.aaamockmvc.test.testdata.testutil.TestValue.VAR_FLOAT_1;
import static ej.aaamockmvc.test.testdata.testutil.TestValue.VAR_INT_1;
import static ej.aaamockmvc.test.testdata.testutil.TestValue.VAR_STRING_1;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

import ej.aaamockmvc.test.web.request.model.TestRequestUrlDto;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

class TestArrangeUrlUtilsTest {

  @Test
  void GIVEN_path_WHEN_setUri_THEN_getUri_returned_expected_value() {
    // Arrange
    var urlDto = new TestRequestUrlDto();

    // Act
    TestArrangeUrlUtils.setUri(urlDto, TEST_PATH);

    // Assert
    assertThat(urlDto.getUri(), is(TEST_URI));
  }

  @Test
  void GIVEN_path_var1_and_vars_1_WHEN_setUri_THEN_getUri_returned_expected_value() {
    // Arrange
    var urlDto = new TestRequestUrlDto();

    // Act
    TestArrangeUrlUtils.setUri(urlDto, TEST_PATH_VAR1, VAR_STRING_1);

    // Assert
    assertThat(urlDto.getUri().toString(), is(TEST_PATH + "/" + VAR_STRING_1));
  }

  @SuppressWarnings("all")
  @Test
  void GIVEN_path_var3_and_vars_only_unsupported_WHEN_setUri_THEN_throw_IllegalArgumentException() {
    // Arrange
    var urlDto = new TestRequestUrlDto();

    // Assert
    assertThrows(
        IllegalArgumentException.class,

        // Act
        () -> TestArrangeUrlUtils.setUri(urlDto, TEST_PATH_VARIABLE_3, List.of(1), Map.of("k", 1)));
  }

  @Test
  void
      GIVEN_path_var2_and_vars_2_unsupported_and_2_supported_WHEN_setUri_THEN_getUri_returned_expected_value() {
    // Arrange
    var urlDto = new TestRequestUrlDto();

    // Act
    TestArrangeUrlUtils.setUri(
        urlDto, TEST_PATH_VARIABLE_2, List.of(1), Map.of("k", 1), VAR_INT_1, VAR_STRING_1);

    // Assert
    var uri = urlDto.getUri().toString();
    assertThat(uri, is(TEST_PATH + "/" + VAR_INT_1 + "/" + VAR_STRING_1));
  }

  @Test
  void GIVEN_path_var6_and_vars_only_supported_WHEN_setUri_THEN_getUri_returned_expected_value() {
    // Arrange
    var urlDto = new TestRequestUrlDto();

    // Act
    TestArrangeUrlUtils.setUri(
        urlDto,
        TEST_PATH_VARIABLE_6,
        VAR_STRING_1,
        VAR_CHAR_1,
        VAR_INT_1,
        VAR_DOUBLE_1,
        VAR_FLOAT_1,
        VAR_BOOLEAN_TRUE);

    // Assert
    var uri = urlDto.getUri().toString();
    assertThat(
        uri,
        is(
            TEST_PATH
                + "/"
                + VAR_STRING_1
                + "/"
                + VAR_CHAR_1
                + "/"
                + VAR_INT_1
                + "/"
                + VAR_DOUBLE_1
                + "/"
                + VAR_FLOAT_1
                + "/"
                + VAR_BOOLEAN_TRUE));
  }

  @Test
  void GIVEN_key_value_WHEN_addParam_THEN_getParam_returned_expected_value() {
    // Arrange
    var testRequestUrlDto = new TestRequestUrlDto();

    // Act
    TestArrangeUrlUtils.addParam(testRequestUrlDto, TEST_PARAM_KEY_1, TEST_PARAM_VALUE_1);

    // Assert
    assertThat(testRequestUrlDto.getParam().size(), is(1));
    assertThat(testRequestUrlDto.getParam().get(TEST_PARAM_KEY_1), is(TEST_PARAM_VALUE_1));
  }

  @Test
  void GIVEN_key_value_map_WHEN_addParam_THEN_getParam_returned_expected_value() {
    // Arrange
    var testRequestUrlDto = new TestRequestUrlDto();

    // Act
    TestArrangeUrlUtils.addParam(testRequestUrlDto, TEST_PARAM_KEY_VALUE_MAP_1_2);

    // Assert
    assertThat(testRequestUrlDto.getParam(), is(TEST_PARAM_KEY_VALUE_MAP_1_2));
  }

  @Test
  void
      GIVEN_addParam_key_0_WHEN_addParam_with_map_key_1_2_THEN_getParam_returned_expected_values() {
    // Arrange
    var testRequestUrlDto = new TestRequestUrlDto();
    TestArrangeUrlUtils.addParam(testRequestUrlDto, TEST_PARAM_KEY_0, TEST_PARAM_VALUE_0);

    // Act
    TestArrangeUrlUtils.addParam(testRequestUrlDto, TEST_PARAM_KEY_VALUE_MAP_1_2);

    // Assert
    assertThat(testRequestUrlDto.getParam().size(), is(3));
    assertThat(testRequestUrlDto.getParam().get(TEST_PARAM_KEY_0), is(TEST_PARAM_VALUE_0));
    assertThat(testRequestUrlDto.getParam().get(TEST_PARAM_KEY_1), is(TEST_PARAM_VALUE_1));
    assertThat(testRequestUrlDto.getParam().get(TEST_PARAM_KEY_2), is(TEST_PARAM_VALUE_2));
  }
}
