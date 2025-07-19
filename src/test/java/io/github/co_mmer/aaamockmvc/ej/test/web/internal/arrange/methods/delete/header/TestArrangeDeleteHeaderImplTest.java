package io.github.co_mmer.aaamockmvc.ej.test.web.internal.arrange.methods.delete.header;

import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.arrange.methods.core.TestArrangeHeadUtils.addKeyValue;
import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.arrange.methods.core.TestArrangeHeadUtils.setAccepts;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestHeader.TEST_AUTH_KEY;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestHeader.TEST_AUTH_VALUE;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestHeader.TEST_HEADER_KEY_1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestHeader.TEST_HEADER_MAP_1_2;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestHeader.TEST_HEADER_VALUE_1;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_PDF;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.arrange.base.validation.TestArrangeValidator;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.arrange.methods.core.TestArrangeHeadUtils;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestAAAContext;
import io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestContext;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.http.MediaType;

class TestArrangeDeleteHeaderImplTest {

  private TestAAAContext context;
  private TestArrangeDeleteHeaderImpl impl;
  private MockedStatic<TestArrangeHeadUtils> mockTestArrangeHeadUtils;

  @BeforeEach
  void setUp() {
    this.context = TestContext.createContext();
    this.mockTestArrangeHeadUtils = Mockito.mockStatic(TestArrangeHeadUtils.class);
    this.impl = new TestArrangeDeleteHeaderImpl(context);
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
      assertThrows(NullPointerException.class, () -> new TestArrangeDeleteHeaderImpl(null));
    }
  }

  @Nested
  class accept {

    @Test
    @SuppressWarnings("ConstantConditions")
    void GIVEN_null_WHEN_accept_THEN_throwException() {
      assertThrows(IllegalArgumentException.class, () -> impl.accept((MediaType) null));
    }

    @Test
    void WHEN_accept_THEN_TestArrangeValidatorIsCalled() {
      // Arrange
      var mockTestArrangeValidator = Mockito.mockStatic(TestArrangeValidator.class);

      // Act
      impl.accept(APPLICATION_JSON);

      // Assert
      mockTestArrangeValidator.verify(() -> TestArrangeValidator.nonNullAccepts(APPLICATION_JSON));
      mockTestArrangeValidator.close();
    }

    @Test
    void GIVEN_type_WHEN_accept_THEN_setAcceptsIsCalled() {
      // Act
      impl.accept(APPLICATION_JSON);

      // Assert
      mockTestArrangeHeadUtils.verify(
          () -> setAccepts(context.getArrangeResult().getHead(), APPLICATION_JSON));
    }

    @Test
    void GIVEN_types_WHEN_accept_THEN_setAcceptsIsCalled() {
      // Act
      impl.accept(APPLICATION_JSON, APPLICATION_PDF);

      // Assert
      mockTestArrangeHeadUtils.verify(
          () ->
              setAccepts(context.getArrangeResult().getHead(), APPLICATION_JSON, APPLICATION_PDF));
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
