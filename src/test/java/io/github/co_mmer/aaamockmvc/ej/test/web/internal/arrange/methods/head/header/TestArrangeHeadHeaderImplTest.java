package io.github.co_mmer.aaamockmvc.ej.test.web.internal.arrange.methods.head.header;

import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.HEADER_KEY_1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.HEADER_MAP_1_2;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.HEADER_VALUE_1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.TEST_AUTH_KEY;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.TEST_AUTH_VALUE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.arrange.methods.head.header.TestArrangeHeadHeaderImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.context.TestAAAContext;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.RequestHeaders;
import io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestContext;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class TestArrangeHeadHeaderImplTest {

  private TestAAAContext context;
  private TestArrangeHeadHeaderImpl impl;

  @BeforeEach
  void setUp() {
    this.context = TestContext.createContext();
    this.impl = new TestArrangeHeadHeaderImpl(context);
  }

  private RequestHeaders getHeaders() {
    return context.getArrangeBuilder().headers();
  }

  @Nested
  class constructor {

    @Test
    @SuppressWarnings("ConstantConditions")
    void GIVEN_null_WHEN_call_constructor_THEN_throw_NullPointerException() {
      assertThrows(NullPointerException.class, () -> new TestArrangeHeadHeaderImpl(null));
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
