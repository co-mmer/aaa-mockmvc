package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.status;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import org.junit.jupiter.api.Test;

class StatusValueTest {

  private static final String NULL_STATUS_MESSAGE = "Expected HTTP status must not be null.";

  @Test
  void GIVEN_status_code_WHEN_constructor_THEN_value_is_preserved() {
    // Act
    var result = new StatusValue(200);

    // Assert
    assertThat(result.value(), is(200));
  }

  @Test
  void GIVEN_zero_WHEN_constructor_THEN_value_is_preserved() {
    // Act
    var result = new StatusValue(0);

    // Assert
    assertThat(result.value(), is(0));
  }

  @Test
  void GIVEN_null_WHEN_of_THEN_throw_domain_error() {
    // Act
    var exception = assertThrows(IllegalArgumentException.class, () -> StatusValue.of(null));

    // Assert
    assertThat(exception.getMessage(), is(NULL_STATUS_MESSAGE));
  }

  @Test
  void GIVEN_ok_WHEN_of_THEN_status_code_is_preserved() {
    // Act
    var result = StatusValue.of(OK);

    // Assert
    assertThat(result.value(), is(200));
  }

  @Test
  void GIVEN_created_WHEN_of_THEN_status_code_is_preserved() {
    // Act
    var result = StatusValue.of(CREATED);

    // Assert
    assertThat(result.value(), is(201));
  }

  @Test
  void GIVEN_accepted_WHEN_of_THEN_status_code_is_preserved() {
    // Act
    var result = StatusValue.of(ACCEPTED);

    // Assert
    assertThat(result.value(), is(202));
  }

  @Test
  void GIVEN_unauthorized_WHEN_of_THEN_status_code_is_preserved() {
    // Act
    var result = StatusValue.of(UNAUTHORIZED);

    // Assert
    assertThat(result.value(), is(401));
  }

  @Test
  void GIVEN_forbidden_WHEN_of_THEN_status_code_is_preserved() {
    // Act
    var result = StatusValue.of(FORBIDDEN);

    // Assert
    assertThat(result.value(), is(403));
  }

  @Test
  void GIVEN_not_found_WHEN_of_THEN_status_code_is_preserved() {
    // Act
    var result = StatusValue.of(NOT_FOUND);

    // Assert
    assertThat(result.value(), is(404));
  }
}
