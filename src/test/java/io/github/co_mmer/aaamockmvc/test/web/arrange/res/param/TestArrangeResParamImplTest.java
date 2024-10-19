package io.github.co_mmer.aaamockmvc.test.web.arrange.res.param;

import static io.github.co_mmer.aaamockmvc.test.testdata.testutil.TestParameter.TEST_PARAM_KEY_1;
import static io.github.co_mmer.aaamockmvc.test.testdata.testutil.TestParameter.TEST_PARAM_KEY_VALUE_MAP_1_2;
import static io.github.co_mmer.aaamockmvc.test.testdata.testutil.TestParameter.TEST_PARAM_VALUE_1;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import io.github.co_mmer.aaamockmvc.test.web.act.TestActImpl;
import io.github.co_mmer.aaamockmvc.test.web.arrange.base.body.TestArrangeBodyUtils;
import io.github.co_mmer.aaamockmvc.test.web.arrange.base.url.TestArrangeUrlUtils;
import io.github.co_mmer.aaamockmvc.test.web.arrange.res.body.TestArrangeResBodyImpl;
import io.github.co_mmer.aaamockmvc.test.web.arrange.res.head.TestArrangeResHeadImpl;
import io.github.co_mmer.aaamockmvc.test.web.request.context.TestRequestContextBuilder;
import io.github.co_mmer.aaamockmvc.test.web.request.model.TestRequestDto;
import io.github.co_mmer.aaamockmvc.test.web.request.model.TestRequestType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

class TestArrangeResParamImplTest {

  private TestRequestDto dto;
  private TestArrangeResParamImpl impl;
  private MockedStatic<TestArrangeUrlUtils> mockTestArrangeUrlUtils;
  private MockedStatic<TestArrangeBodyUtils> mockTestArrangeBodyUtils;

  @BeforeEach
  void setUp() {
    this.mockTestArrangeUrlUtils = Mockito.mockStatic(TestArrangeUrlUtils.class);
    this.mockTestArrangeBodyUtils = Mockito.mockStatic(TestArrangeBodyUtils.class);

    this.dto = new TestRequestDto(TestRequestType.POST);
    var context = TestRequestContextBuilder.getInstance().withTestRequest(this.dto).build();

    this.impl = new TestArrangeResParamImpl(context);
  }

  @AfterEach
  void clean() {
    this.mockTestArrangeUrlUtils.close();
    this.mockTestArrangeBodyUtils.close();
  }

  @Test
  void GIVEN_param_WHEN_arrangeParam_THEN_addParam_is_called() {
    // Act
    this.impl.arrangeKeyValue(TEST_PARAM_KEY_1, TEST_PARAM_VALUE_1);

    // Assert
    this.mockTestArrangeUrlUtils.verify(
        () ->
            TestArrangeUrlUtils.addParam(this.dto.getUrl(), TEST_PARAM_KEY_1, TEST_PARAM_VALUE_1));
  }

  @Test
  void GIVEN_params_WHEN_arrangeParam_THEN_setParam_is_called() {
    // Act
    this.impl.arrangeKeyValue(TEST_PARAM_KEY_VALUE_MAP_1_2);

    // Assert
    this.mockTestArrangeUrlUtils.verify(
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
    assertThat(act.getClass(), is(TestActImpl.class));
  }
}
