package ej.aaamockmvc.test.web.arrange.delete.head;

import static ej.aaamockmvc.test.testdata.testutil.TestHeader.TEST_AUTH_KEY;
import static ej.aaamockmvc.test.testdata.testutil.TestHeader.TEST_AUTH_VALUE;
import static ej.aaamockmvc.test.testdata.testutil.TestHeader.TEST_HEADER_KEY_1;
import static ej.aaamockmvc.test.testdata.testutil.TestHeader.TEST_HEADER_MAP_1_2;
import static ej.aaamockmvc.test.testdata.testutil.TestHeader.TEST_HEADER_VALUE_1;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_PDF;

import ej.aaamockmvc.test.web.act.TestActImpl;
import ej.aaamockmvc.test.web.arrange.base.head.TestArrangeHeadUtils;
import ej.aaamockmvc.test.web.request.context.TestRequestContextBuilder;
import ej.aaamockmvc.test.web.request.model.TestRequestDto;
import ej.aaamockmvc.test.web.request.model.TestRequestType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

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
  void WHEN_act_THEN_expected_class() {
    // Act
    var act = this.impl.act();

    // Assert
    assertThat(act.getClass(), is(TestActImpl.class));
  }
}
