package io.github.co_mmer.aaamockmvc.ej.test.web.arrange.base.url;

import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.TEST_PATH;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.TEST_PATH_VAR1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.TEST_PATH_VARIABLE_3;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.VAR_STRING_1;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

class TestRequestUriMapperTest {

  @Test
  void GIVEN_path_variables_empty_WHEN_mapTo_THEN_returned_expected_value() {
    // Act
    var uri = TestRequestUriMapper.mapTo(TEST_PATH, Collections.emptyList());

    // Assert
    assertThat(uri.toString(), is(TEST_PATH));
  }

  @Test
  void GIVEN_path_var1_variable_1_WHEN_mapTo_THEN_returned_expected_value() {
    // Act
    var uri = TestRequestUriMapper.mapTo(TEST_PATH_VAR1, List.of(VAR_STRING_1));

    // Assert
    assertThat(uri.toString(), is(TEST_PATH + "/" + VAR_STRING_1));
  }

  @Test
  void GIVEN_path_variable_1_WHEN_mapTo_THEN_returned_expected_value() {
    // Act
    var uri = TestRequestUriMapper.mapTo(TEST_PATH, List.of(VAR_STRING_1));

    // Assert
    assertThat(uri.toString(), is(TEST_PATH));
  }

  @Test
  void GIVEN_path_var3_variable_1_WHEN_mapTo_THEN_throw_IllegalArgumentException() {
    // Arrange
    List<Object> vars = List.of(VAR_STRING_1);

    // Assert
    assertThrows(
        IllegalArgumentException.class,

        // Act
        () -> TestRequestUriMapper.mapTo(TEST_PATH_VARIABLE_3, vars));
  }

  @Test
  void GIVEN_path_var3_variables_empty_WHEN_mapTo_THEN_throw_IllegalArgumentException() {
    // Arrange
    List<Object> vars = Collections.emptyList();

    // Assert
    assertThrows(
        IllegalArgumentException.class,

        // Act
        () -> TestRequestUriMapper.mapTo(TEST_PATH_VARIABLE_3, vars));
  }
}
