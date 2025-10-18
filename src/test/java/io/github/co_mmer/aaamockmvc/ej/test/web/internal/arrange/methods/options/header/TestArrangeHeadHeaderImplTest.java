package io.github.co_mmer.aaamockmvc.ej.test.web.internal.arrange.methods.options.header;

import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.arrange.methods.core.TestArrangeHeaderSetter.addKeyValue;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.HEADER_KEY_1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.HEADER_MAP_1_2;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.HEADER_VALUE_1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.TEST_AUTH_KEY;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.TEST_AUTH_VALUE;
import static org.junit.jupiter.api.Assertions.assertThrows;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.arrange.methods.core.TestArrangeHeaderSetter;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestAAAContext;
import io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestContext;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

class TestArrangeHeadHeaderImplTest {

  private TestAAAContext context;
  private TestArrangeOptionsHeaderImpl impl;
  private MockedStatic<TestArrangeHeaderSetter> mockTestArrangeHeadUtils;

  @BeforeEach
  void setUp() {
    this.context = TestContext.createContext();
    this.mockTestArrangeHeadUtils = Mockito.mockStatic(TestArrangeHeaderSetter.class);
    this.impl = new TestArrangeOptionsHeaderImpl(context);
  }

  @AfterEach
  void clean() {
    this.mockTestArrangeHeadUtils.close();
  }

  @Nested
  class constructor {

    @Test
    @SuppressWarnings("ConstantConditions")
    void GIVEN_null_WHEN_call_constructor_THEN_throw_NullPointerException() {
      assertThrows(NullPointerException.class, () -> new TestArrangeOptionsHeaderImpl(null));
    }
  }

  @Nested
  class auth {

    @Test
    void GIVEN_token_WHEN_auth_THEN_addKeyValue_isCalled() {
      // Act
      impl.auth(TEST_AUTH_VALUE);

      // Assert
      mockTestArrangeHeadUtils.verify(
          () -> addKeyValue(context.getArrangeResult().getHead(), TEST_AUTH_KEY, TEST_AUTH_VALUE));
    }
  }

  @Nested
  class add {

    @Test
    void GIVEN_key_value_WHEN_add_THEN_addKeyValue_isCalled() {
      // Act
      impl.add(HEADER_KEY_1, HEADER_VALUE_1);

      // Assert
      mockTestArrangeHeadUtils.verify(
          () -> addKeyValue(context.getArrangeResult().getHead(), HEADER_KEY_1, HEADER_VALUE_1));
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
    void GIVEN_map_WHEN_set_THEN_addKeyValue_isCalled() {
      // Act
      impl.set(HEADER_MAP_1_2);

      // Assert
      mockTestArrangeHeadUtils.verify(
          () -> addKeyValue(context.getArrangeResult().getHead(), HEADER_MAP_1_2));
    }
  }
}
