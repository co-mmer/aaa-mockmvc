package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.status;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.InvalidAssertionException;
import org.junit.jupiter.api.Test;

class StatusRangeTest {

  private static final String INVALID_RANGE_MESSAGE =
      "isInRange() requires a minimum that is not greater than the maximum.";

  @Test
  void GIVEN_valid_bounds_WHEN_constructor_THEN_bounds_are_preserved() {
    // Act
    var result = new StatusRange(200, 299);

    // Assert
    assertThat(result.minimum(), is(200));
    assertThat(result.maximum(), is(299));
  }

  @Test
  void GIVEN_equal_bounds_WHEN_constructor_THEN_single_status_range_is_valid() {
    // Act
    var result = new StatusRange(200, 200);

    // Assert
    assertThat(result.minimum(), is(200));
    assertThat(result.maximum(), is(200));
  }

  @Test
  void GIVEN_minimum_above_maximum_WHEN_constructor_THEN_throw_domain_error() {
    // Act
    var exception = assertThrows(InvalidAssertionException.class, () -> new StatusRange(201, 200));

    // Assert
    assertThat(exception.getMessage(), is(INVALID_RANGE_MESSAGE));
  }

  @Test
  void GIVEN_successful_range_THEN_expected_bounds_are_defined() {
    assertThat(StatusRange.SUCCESSFUL.minimum(), is(200));
    assertThat(StatusRange.SUCCESSFUL.maximum(), is(299));
  }

  @Test
  void GIVEN_redirect_range_THEN_expected_bounds_are_defined() {
    assertThat(StatusRange.REDIRECT.minimum(), is(300));
    assertThat(StatusRange.REDIRECT.maximum(), is(399));
  }

  @Test
  void GIVEN_client_error_range_THEN_expected_bounds_are_defined() {
    assertThat(StatusRange.CLIENT_ERROR.minimum(), is(400));
    assertThat(StatusRange.CLIENT_ERROR.maximum(), is(499));
  }

  @Test
  void GIVEN_server_error_range_THEN_expected_bounds_are_defined() {
    assertThat(StatusRange.SERVER_ERROR.minimum(), is(500));
    assertThat(StatusRange.SERVER_ERROR.maximum(), is(599));
  }
}
