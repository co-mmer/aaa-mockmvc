package io.github.co_mmer.aaamockmvc.ej.test.web.internal.arrange.methods.head.header;

import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.arrange.methods.core.TestArrangeHeadUtils.addKeyValue;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestHeader.TEST_AUTH_KEY;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestHeader.TEST_AUTH_VALUE;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestHeader.TEST_HEADER_KEY_1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestHeader.TEST_HEADER_MAP_1_2;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestHeader.TEST_HEADER_VALUE_1;
import static org.junit.jupiter.api.Assertions.assertThrows;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.arrange.methods.core.TestArrangeHeadUtils;
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
  private TestArrangeHeadHeaderImpl impl;
  private MockedStatic<TestArrangeHeadUtils> mockTestArrangeHeadUtils;

  @BeforeEach
  void setUp() {
    this.context = TestContext.createContext();
    this.mockTestArrangeHeadUtils = Mockito.mockStatic(TestArrangeHeadUtils.class);
    this.impl = new TestArrangeHeadHeaderImpl(context);
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
      assertThrows(NullPointerException.class, () -> new TestArrangeHeadHeaderImpl(null));
    }
  }

  @Nested
  class auth {

    @Test
    void GIVEN_token_WHEN_auth_THEN_addKeyValueIsCalled() {
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
    void GIVEN_key_value_WHEN_add_THEN_addKeyValueIsCalled() {
      // Act
      impl.add(TEST_HEADER_KEY_1, TEST_HEADER_VALUE_1);

      // Assert
      mockTestArrangeHeadUtils.verify(
          () ->
              addKeyValue(
                  context.getArrangeResult().getHead(), TEST_HEADER_KEY_1, TEST_HEADER_VALUE_1));
    }

    @Test
    void GIVEN_map_WHEN_set_THEN_addKeyValueIsCalled() {
      // Act
      impl.set(TEST_HEADER_MAP_1_2);

      // Assert
      mockTestArrangeHeadUtils.verify(
          () -> addKeyValue(context.getArrangeResult().getHead(), TEST_HEADER_MAP_1_2));
    }
  }
}
