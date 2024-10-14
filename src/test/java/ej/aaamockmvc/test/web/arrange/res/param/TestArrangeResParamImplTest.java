package ej.aaamockmvc.test.request.arrange.res.param;

import static ej.aaamockmvc.test.testdata.testutil.TestParameter.TEST_PARAM_KEY_1;
import static ej.aaamockmvc.test.testdata.testutil.TestParameter.TEST_PARAM_KEY_VALUE_MAP_1_2;
import static ej.aaamockmvc.test.testdata.testutil.TestParameter.TEST_PARAM_VALUE_1;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import ej.aaamockmvc.test.web.act.TestActPerformImpl;
import ej.aaamockmvc.test.web.arrange.res.body.TestArrangeResBodyImpl;
import ej.aaamockmvc.test.web.arrange.res.head.TestArrangeResHeadImpl;
import ej.aaamockmvc.test.web.arrange.res.param.TestArrangeResParamImpl;
import ej.aaamockmvc.test.web.arrange.utils.TestArrangeRequestBody;
import ej.aaamockmvc.test.web.arrange.utils.TestArrangeUrlUtils;
import ej.aaamockmvc.test.web.request.context.TestRequestContextBuilder;
import ej.aaamockmvc.test.web.request.model.TestRequestDto;
import ej.aaamockmvc.test.web.request.model.TestRequestType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

class TestArrangeResParamImplTest {

  private TestRequestDto dto;
  private TestArrangeResParamImpl impl;
  private MockedStatic<TestArrangeUrlUtils> mockTestArrangeRequestUrl;
  private MockedStatic<TestArrangeRequestBody> mockTestArrangeRequestBody;

  @BeforeEach
  void setUp() {
    this.mockTestArrangeRequestUrl = Mockito.mockStatic(TestArrangeUrlUtils.class);
    this.mockTestArrangeRequestBody = Mockito.mockStatic(TestArrangeRequestBody.class);

    this.dto = new TestRequestDto(TestRequestType.POST);
    var context = TestRequestContextBuilder.getInstance().withTestRequest(this.dto).build();

    this.impl = new TestArrangeResParamImpl(context);
  }

  @AfterEach
  void clean() {
    this.mockTestArrangeRequestUrl.close();
    this.mockTestArrangeRequestBody.close();
  }

  @Test
  void GIVEN_param_WHEN_arrangeParam_THEN_addParam_is_called() {
    // Act
    this.impl.arrangeKeyValue(TEST_PARAM_KEY_1, TEST_PARAM_VALUE_1);

    // Assert
    this.mockTestArrangeRequestUrl.verify(
        () ->
            TestArrangeUrlUtils.addParam(this.dto.getUrl(), TEST_PARAM_KEY_1, TEST_PARAM_VALUE_1));
  }

  @Test
  void GIVEN_params_WHEN_arrangeParam_THEN_setParam_is_called() {
    // Act
    this.impl.arrangeKeyValue(TEST_PARAM_KEY_VALUE_MAP_1_2);

    // Assert
    this.mockTestArrangeRequestUrl.verify(
        () -> TestArrangeUrlUtils.addParam(this.dto.getUrl(), TEST_PARAM_KEY_VALUE_MAP_1_2));
  }

  @Test
  void WHEN_arrangeHead_THEN_expected_class() {
    // Act
    var arrangeHead = this.impl.arrangeHead();

    // Assert
    assertThat(arrangeHead.getClass(), is(TestArrangeResHeadImpl.class));
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
    assertThat(act.getClass(), is(TestActPerformImpl.class));
  }
}
