package io.github.co_mmer.aaamockmvc.ej.test.web.internal.arrange.methods.get.header;

import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.arrange.methods.core.TestArrangeHeaderSetter.addKeyValue;
import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.arrange.methods.core.TestArrangeHeaderSetter.setAccepts;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.HEADER_KEY_1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.HEADER_MAP_1_2;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.HEADER_VALUE_1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.TEST_AUTH_KEY;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.TEST_AUTH_VALUE;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_PDF;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.arrange.methods.core.TestArrangeHeaderSetter;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.arrange.methods.core.TestArrangeValidator;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestAAAContext;
import io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestContext;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.http.MediaType;

class TestArrangeGetHeaderImplTest {

  private TestAAAContext context;
  private TestArrangeGetHeaderImpl impl;
  private MockedStatic<TestArrangeHeaderSetter> mockTestArrangeHeadUtils;

  @BeforeEach
  void setUp() {
    this.context = TestContext.createContext();
    this.mockTestArrangeHeadUtils = Mockito.mockStatic(TestArrangeHeaderSetter.class);
    this.impl = new TestArrangeGetHeaderImpl(this.context);
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
      assertThrows(NullPointerException.class, () -> new TestArrangeGetHeaderImpl(null));
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
    void WHEN_accept_THEN_TestArrangeValidator_isCalled() {
      // Arrange
      var mockTestArrangeValidator = Mockito.mockStatic(TestArrangeValidator.class);

      // Act
      impl.accept(APPLICATION_JSON);

      // Assert
      mockTestArrangeValidator.verify(() -> TestArrangeValidator.nonNullAccepts(APPLICATION_JSON));
      mockTestArrangeValidator.close();
    }

    @Test
    void GIVEN_type_WHEN_accept_THEN_setAccepts_isCalled() {
      // Act
      impl.accept(APPLICATION_JSON);

      // Assert
      mockTestArrangeHeadUtils.verify(
          () -> setAccepts(context.getArrangeResult().getHead(), APPLICATION_JSON));
    }

    @Test
    void GIVEN_types_WHEN_accept_THEN_setAccepts_isCalled() {
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
