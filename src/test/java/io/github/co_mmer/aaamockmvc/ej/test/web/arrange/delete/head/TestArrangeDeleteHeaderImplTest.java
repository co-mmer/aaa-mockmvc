package io.github.co_mmer.aaamockmvc.ej.test.web.arrange.delete.head;

import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestHeader.TEST_AUTH_KEY;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestHeader.TEST_AUTH_VALUE;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestHeader.TEST_HEADER_KEY_1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestHeader.TEST_HEADER_MAP_1_2;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestHeader.TEST_HEADER_VALUE_1;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_PDF;

import io.github.co_mmer.aaamockmvc.ej.test.web.act.TestActImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.base.head.TestArrangeHeadUtils;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.base.validation.TestArrangeValidator;
import io.github.co_mmer.aaamockmvc.ej.test.web.request.context.TestRequestContextBuilder;
import io.github.co_mmer.aaamockmvc.ej.test.web.request.model.TestRequestDto;
import io.github.co_mmer.aaamockmvc.ej.test.web.request.model.TestRequestType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.http.MediaType;

class TestArrangeDeleteHeaderImplTest {

  private TestRequestDto dto;
  private TestArrangeDeleteHeadImpl impl;
  private MockedStatic<TestArrangeHeadUtils> mockTestArrangeRequestHead;

  @BeforeEach
  void setUp() {
    this.mockTestArrangeRequestHead = Mockito.mockStatic(TestArrangeHeadUtils.class);

    this.dto = new TestRequestDto(TestRequestType.DELETE);
    var context = TestRequestContextBuilder.getInstance().withTestRequest(this.dto).build();

    this.impl = new TestArrangeDeleteHeadImpl(context);
  }

  @AfterEach
  void clean() {
    this.mockTestArrangeRequestHead.close();
  }

  @Test
  @SuppressWarnings("ConstantConditions")
  void GIVEN_null_WHEN_call_constructor_THEN_throw_NullPointerException() {
    assertThrows(NullPointerException.class, () -> new TestArrangeDeleteHeadImpl(null));
  }

  @Test
  @SuppressWarnings("ConstantConditions")
  void GIVEN_null_WHEN_arrangeAccept_THEN_throw_IllegalArgumentException() {
    assertThrows(IllegalArgumentException.class, () -> this.impl.arrangeAccept((MediaType) null));
  }

  @Test
  void WHEN_arrangeAccept_THEN_TestArrangeValidator_is_called() {
    // Arrange
    var mockTestArrangeValidator = Mockito.mockStatic(TestArrangeValidator.class);

    // Act
    this.impl.arrangeAccept(APPLICATION_JSON);

    // Assert
    mockTestArrangeValidator.verify(() -> TestArrangeValidator.nonNullAccepts(APPLICATION_JSON));
    mockTestArrangeValidator.close();
  }

  @Test
  void GIVEN_type_WHEN_arrangeAccept_THEN_setAccepts_is_called() {
    // Act
    this.impl.arrangeAccept(APPLICATION_JSON);

    // Assert
    this.mockTestArrangeRequestHead.verify(
        () -> TestArrangeHeadUtils.setAccepts(this.dto.getHead(), APPLICATION_JSON));
  }

  @Test
  void GIVEN_types_WHEN_arrangeAccept_THEN_setAccepts_is_called() {
    // Act
    this.impl.arrangeAccept(APPLICATION_JSON, APPLICATION_PDF);

    // Assert
    this.mockTestArrangeRequestHead.verify(
        () ->
            TestArrangeHeadUtils.setAccepts(this.dto.getHead(), APPLICATION_JSON, APPLICATION_PDF));
  }

  @Test
  void GIVEN_auth_value_WHEN_arrangeAuth_THEN_addKeyValue_is_called() {
    // Act
    this.impl.arrangeAuth(TEST_AUTH_VALUE);

    // Assert
    this.mockTestArrangeRequestHead.verify(
        () -> TestArrangeHeadUtils.addKeyValue(this.dto.getHead(), TEST_AUTH_KEY, TEST_AUTH_VALUE));
  }

  @Test
  @SuppressWarnings("ConstantConditions")
  void GIVEN_null_WHEN_arrangeContentType_THEN_throw_IllegalArgumentException() {
    assertThrows(
        IllegalArgumentException.class, () -> this.impl.arrangeContentType((MediaType) null));
  }

  @Test
  void WHEN_arrangeContentType_THEN_TestArrangeValidator_is_called() {
    // Arrange
    var mockTestArrangeValidator = Mockito.mockStatic(TestArrangeValidator.class);

    // Act
    this.impl.arrangeContentType(APPLICATION_JSON);

    // Assert
    mockTestArrangeValidator.verify(
        () -> TestArrangeValidator.nonNullContentTypes(APPLICATION_JSON));
    mockTestArrangeValidator.close();
  }

  @Test
  void GIVEN_type_WHEN_arrangeContentType_THEN_setContentTypes_is_called() {
    // Act
    this.impl.arrangeContentType(APPLICATION_JSON);

    // Assert
    this.mockTestArrangeRequestHead.verify(
        () -> TestArrangeHeadUtils.setContentTypes(this.dto.getHead(), APPLICATION_JSON));
  }

  @Test
  void GIVEN_types_WHEN_arrangeContentType_THEN_setContentTypes_is_called() {
    // Act
    this.impl.arrangeContentType(APPLICATION_JSON, APPLICATION_PDF);

    // Assert
    this.mockTestArrangeRequestHead.verify(
        () ->
            TestArrangeHeadUtils.setContentTypes(
                this.dto.getHead(), APPLICATION_JSON, APPLICATION_PDF));
  }

  @Test
  void GIVEN_header_WHEN_arrangeKeyValue_THEN_addKeyValue_is_called() {
    // Act
    this.impl.arrangeKeyValue(TEST_HEADER_KEY_1, TEST_HEADER_VALUE_1);

    // Assert
    this.mockTestArrangeRequestHead.verify(
        () ->
            TestArrangeHeadUtils.addKeyValue(
                this.dto.getHead(), TEST_HEADER_KEY_1, TEST_HEADER_VALUE_1));
  }

  @Test
  void GIVEN_headers_WHEN_arrangeKeyValue_THEN_setKeyValue_is_called() {
    // Act
    this.impl.arrangeKeyValue(TEST_HEADER_MAP_1_2);

    // Assert
    this.mockTestArrangeRequestHead.verify(
        () -> TestArrangeHeadUtils.addKeyValue(this.dto.getHead(), TEST_HEADER_MAP_1_2));
  }

  @Test
  void WHEN_act_THEN_expected_class() {
    // Act
    var act = this.impl.act();

    // Assert
    assertThat(act.getClass(), is(TestActImpl.class));
  }
}
