package ej.aaamockmvc.test.request.arrange.info.param;

import static ej.aaamockmvc.test.testdata.testutil.TestParameter.TEST_PARAM_KEY_1;
import static ej.aaamockmvc.test.testdata.testutil.TestParameter.TEST_PARAM_KEY_VALUE_MAP_1_2;
import static ej.aaamockmvc.test.testdata.testutil.TestParameter.TEST_PARAM_VALUE_1;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import ej.aaamockmvc.test.web.act.TestActPerformImpl;
import ej.aaamockmvc.test.web.arrange.info.head.TestArrangeInfoHeadImpl;
import ej.aaamockmvc.test.web.arrange.info.param.TestArrangeInfoParamImpl;
import ej.aaamockmvc.test.web.arrange.utils.TestArrangeUrlUtils;
import ej.aaamockmvc.test.web.request.context.TestRequestContextBuilder;
import ej.aaamockmvc.test.web.request.model.TestRequestDto;
import ej.aaamockmvc.test.web.request.model.TestRequestType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

class TestArrangeInfoParamImplTest {

  private TestRequestDto dto;
  private TestArrangeInfoParamImpl impl;
  private MockedStatic<TestArrangeUrlUtils> mockTestArrangeRequestUrl;

  @BeforeEach
  void setUp() {
    this.mockTestArrangeRequestUrl = Mockito.mockStatic(TestArrangeUrlUtils.class);

    this.dto = new TestRequestDto(TestRequestType.HEAD);
    var context = TestRequestContextBuilder.getInstance().withTestRequest(this.dto).build();

    this.impl = new TestArrangeInfoParamImpl(context);
  }

  @AfterEach
  void clean() {
    this.mockTestArrangeRequestUrl.close();
  }

  @Test
  void GIVEN_key_value_WHEN_arrangeKeyValue_THEN_addParam_is_called() {
    // Act
    this.impl.arrangeKeyValue(TEST_PARAM_KEY_1, TEST_PARAM_VALUE_1);

    // Assert
    this.mockTestArrangeRequestUrl.verify(
        () ->
            TestArrangeUrlUtils.addParam(this.dto.getUrl(), TEST_PARAM_KEY_1, TEST_PARAM_VALUE_1));
  }

  @Test
  void GIVEN_key_value_map_WHEN_arrangeKeyValue_THEN_addParam_is_called() {
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
    assertThat(arrangeHead.getClass(), is(TestArrangeInfoHeadImpl.class));
  }

  @Test
  void WHEN_act_THEN_expected_class() {
    // Act
    var act = this.impl.act();

    // Assert
    assertThat(act.getClass(), is(TestActPerformImpl.class));
  }
}
