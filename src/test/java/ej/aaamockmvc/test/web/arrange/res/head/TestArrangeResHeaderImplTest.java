package ej.aaamockmvc.test.web.arrange.res.head;

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
import ej.aaamockmvc.test.web.arrange.res.body.TestArrangeResBodyImpl;
import ej.aaamockmvc.test.web.request.context.TestRequestContextBuilder;
import ej.aaamockmvc.test.web.request.model.TestRequestDto;
import ej.aaamockmvc.test.web.request.model.TestRequestType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

class TestArrangeResHeaderImplTest {

  private TestRequestDto dto;
  private TestArrangeResHeadImpl impl;
  private MockedStatic<TestArrangeHeadUtils> mockTestArrangeHeadUtils;

  @BeforeEach
  void setUp() {
    this.mockTestArrangeHeadUtils = Mockito.mockStatic(TestArrangeHeadUtils.class);

    this.dto = new TestRequestDto(TestRequestType.POST);
    var context = TestRequestContextBuilder.getInstance().withTestRequest(this.dto).build();

    this.impl = new TestArrangeResHeadImpl(context);
  }

  @AfterEach
  void clean() {
    this.mockTestArrangeHeadUtils.close();
  }

  @Test
  void GIVEN_type_WHEN_arrangeAccept_THEN_setAccepts_is_called() {
    // Act
    this.impl.arrangeAccept(APPLICATION_JSON);

    // Assert
    this.mockTestArrangeHeadUtils.verify(
        () -> TestArrangeHeadUtils.setAccepts(this.dto.getHead(), APPLICATION_JSON));
  }

  @Test
  void GIVEN_types_WHEN_arrangeAccept_THEN_setAccepts_is_called() {
    // Act
    this.impl.arrangeAccept(APPLICATION_JSON, APPLICATION_PDF);

    // Assert
    this.mockTestArrangeHeadUtils.verify(
        () ->
            TestArrangeHeadUtils.setAccepts(this.dto.getHead(), APPLICATION_JSON, APPLICATION_PDF));
  }

  @Test
  void GIVEN_auth_value_WHEN_arrangeAuth_THEN_addKeyValue_is_called() {
    // Act
    this.impl.arrangeAuth(TEST_AUTH_VALUE);

    // Assert
    this.mockTestArrangeHeadUtils.verify(
        () -> TestArrangeHeadUtils.addKeyValue(this.dto.getHead(), TEST_AUTH_KEY, TEST_AUTH_VALUE));
  }

  @Test
  void GIVEN_type_WHEN_arrangeContentType_THEN_setContentTypes_is_called() {
    // Act
    this.impl.arrangeContentType(APPLICATION_JSON);

    // Assert
    this.mockTestArrangeHeadUtils.verify(
        () -> TestArrangeHeadUtils.setContentTypes(this.dto.getHead(), APPLICATION_JSON));
  }

  @Test
  void GIVEN_types_WHEN_arrangeContentType_THEN_setContentTypes_is_called() {
    // Act
    this.impl.arrangeContentType(APPLICATION_JSON, APPLICATION_PDF);

    // Assert
    this.mockTestArrangeHeadUtils.verify(
        () ->
            TestArrangeHeadUtils.setContentTypes(
                this.dto.getHead(), APPLICATION_JSON, APPLICATION_PDF));
  }

  @Test
  void GIVEN_key_value_WHEN_arrangeKeyValue_THEN_addKeyValue_is_called() {
    // Act
    this.impl.arrangeKeyValue(TEST_HEADER_KEY_1, TEST_HEADER_VALUE_1);

    // Assert
    this.mockTestArrangeHeadUtils.verify(
        () ->
            TestArrangeHeadUtils.addKeyValue(
                this.dto.getHead(), TEST_HEADER_KEY_1, TEST_HEADER_VALUE_1));
  }

  @Test
  void GIVEN_key_value_map_WHEN_arrangeKeyValue_THEN_addKeyValue_is_called() {
    // Act
    this.impl.arrangeKeyValue(TEST_HEADER_MAP_1_2);

    // Assert
    this.mockTestArrangeHeadUtils.verify(
        () -> TestArrangeHeadUtils.addKeyValue(this.dto.getHead(), TEST_HEADER_MAP_1_2));
  }

  @Test
  void WHEN_arrangeBody_THEN_expected_class() {
    // Act
    var arrangeBody = this.impl.arrangeBody();

    // Assert
    assertThat(arrangeBody.getClass(), is(TestArrangeResBodyImpl.class));
  }

  @Test
  void WHEN_act_THEN_expected_class() {
    // Act
    var act = this.impl.act();

    // Assert
    assertThat(act.getClass(), is(TestActImpl.class));
  }
}
