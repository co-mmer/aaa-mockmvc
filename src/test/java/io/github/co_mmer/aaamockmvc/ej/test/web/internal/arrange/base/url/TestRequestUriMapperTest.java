package io.github.co_mmer.aaamockmvc.ej.test.web.internal.arrange.base.url;

import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.BASE_PATH;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.PATH_VARIABLE_USER_ID;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.PATH_WITH_USER_ID;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.PATH_WITH_USER_ORDER_AND_PRODUCT;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.request.TestRequestBodyDto;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

class TestRequestUriMapperTest {

  @SuppressWarnings("all")
  @Test
  void GIVEN_null_null_WHEN_mapTo_THEN_throw_NullPointerException() {
    //  Assert
    assertThrows(
        NullPointerException.class,

        // Act
        () -> TestRequestUriMapper.mapTo(null, null));
  }

  @SuppressWarnings("all")
  @Test
  void GIVEN_url_null_WHEN_mapTo_THEN_throw_NullPointerException() {
    //  Assert
    assertThrows(
        NullPointerException.class,

        // Act
        () -> TestRequestUriMapper.mapTo(null, List.of(PATH_VARIABLE_USER_ID)));
  }

  @SuppressWarnings("all")
  @Test
  void GIVEN_path_variables_null_WHEN_mapTo_THEN_throw_NullPointerException() {
    // Arrange
    var testRequestBodyDto = new TestRequestBodyDto();

    //  Assert
    assertThrows(
        NullPointerException.class,

        // Act
        () -> TestRequestUriMapper.mapTo(BASE_PATH, null));
  }

  @Test
  void GIVEN_path_variables_empty_WHEN_mapTo_THEN_returned_expected_value() {
    // Act
    var uri = TestRequestUriMapper.mapTo(BASE_PATH, Collections.emptyList());

    // Assert
    assertThat(uri.toString(), is(BASE_PATH));
  }

  @Test
  void GIVEN_path_var1_variable1_WHEN_mapTo_THEN_returned_expected_value() {
    // Act
    var uri = TestRequestUriMapper.mapTo(PATH_WITH_USER_ID, List.of(PATH_VARIABLE_USER_ID));

    // Assert
    assertThat(uri.toString(), is(BASE_PATH + "/" + PATH_VARIABLE_USER_ID));
  }

  @Test
  void GIVEN_path_variable1_WHEN_mapTo_THEN_returned_expected_value() {
    // Act
    var uri = TestRequestUriMapper.mapTo(BASE_PATH, List.of(PATH_VARIABLE_USER_ID));

    // Assert
    assertThat(uri.toString(), is(BASE_PATH));
  }

  @Test
  void GIVEN_path_var3_variable1_WHEN_mapTo_THEN_throw_IllegalArgumentException() {
    // Arrange
    List<Object> vars = List.of(PATH_VARIABLE_USER_ID);

    // Assert
    assertThrows(
        IllegalArgumentException.class,

        // Act
        () -> TestRequestUriMapper.mapTo(PATH_WITH_USER_ORDER_AND_PRODUCT, vars));
  }

  @Test
  void GIVEN_path_var3_variables_empty_WHEN_mapTo_THEN_throw_IllegalArgumentException() {
    // Arrange
    List<Object> vars = Collections.emptyList();

    // Assert
    assertThrows(
        IllegalArgumentException.class,

        // Act
        () -> TestRequestUriMapper.mapTo(PATH_WITH_USER_ORDER_AND_PRODUCT, vars));
  }
}
