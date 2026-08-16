package io.github.co_mmer.aaamockmvc.ej.test.web.internal.arrange.methods.delete.header;

import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.HEADER_KEY_1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.HEADER_MAP_1_2;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.HEADER_VALUE_1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.TEST_AUTH_KEY;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.TEST_AUTH_VALUE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_PDF;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestAAAContext;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.request.RequestHeaders;
import io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestContext;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

class TestArrangeDeleteHeaderImplTest {

  private TestAAAContext context;
  private TestArrangeDeleteHeaderImpl impl;

  @BeforeEach
  void setUp() {
    this.context = TestContext.createContext();
    this.impl = new TestArrangeDeleteHeaderImpl(context);
  }

  private RequestHeaders getHeaders() {
    return context.getArrangeBuilder().headers();
  }

  @Nested
  class constructor {

    @Test
    @SuppressWarnings("ConstantConditions")
    void GIVEN_null_WHEN_call_constructor_THEN_throw_NullPointerException() {
      assertThrows(NullPointerException.class, () -> new TestArrangeDeleteHeaderImpl(null));
    }
  }

  @Nested
  class accept {

    @Test
    @SuppressWarnings("ConstantConditions")
    void GIVEN_null_WHEN_accept_THEN_throw_Exception() {
      assertThrows(IllegalArgumentException.class, () -> impl.accept((MediaType) null));
    }

    @Test
    void GIVEN_type_WHEN_accept_THEN_header_contain_type() {
      // Act
      impl.accept(APPLICATION_JSON);

      // Assert
      assertThat(getHeaders().values(), hasEntry("Accept", List.of(APPLICATION_JSON.toString())));
    }

    @Test
    void GIVEN_types_WHEN_accept_THEN_header_contain_types() {
      // Act
      impl.accept(APPLICATION_JSON, APPLICATION_PDF);

      // Assert
      assertThat(
          getHeaders().values(),
          hasEntry("Accept", List.of(APPLICATION_JSON.toString(), APPLICATION_PDF.toString())));
    }
  }

  @Nested
  class auth {

    @Test
    void GIVEN_token_WHEN_auth_THEN_header_contains_token() {
      // Act
      impl.auth(TEST_AUTH_VALUE);

      // Assert
      assertThat(getHeaders().values(), hasEntry(TEST_AUTH_KEY, List.of(TEST_AUTH_VALUE)));
    }
  }

  @Nested
  class add {

    @Test
    void GIVEN_key_value_WHEN_add_THEN_header_contain_entry() {
      // Act
      impl.add(HEADER_KEY_1, HEADER_VALUE_1);

      // Assert
      assertThat(getHeaders().values(), hasEntry(HEADER_KEY_1, List.of(HEADER_VALUE_1)));
    }
  }

  @Nested
  class set {

    @Test
    @SuppressWarnings("ConstantConditions")
    void GIVEN_null_WHEN_set_THEN_throw_NullPointerException() {
      // Act & Arrange
      assertThrows(NullPointerException.class, () -> impl.set(null));
    }

    @Test
    void GIVEN_map_WHEN_set_THEN_header_is_expected_map() {
      // Act
      impl.set(HEADER_MAP_1_2);

      // Assert
      assertThat(getHeaders().values(), is(HEADER_MAP_1_2));
    }
  }
}
