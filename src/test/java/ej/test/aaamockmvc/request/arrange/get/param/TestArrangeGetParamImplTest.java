package ej.test.aaamockmvc.request.arrange.get.param;

import static ej.test.aaamockmvc.testdata.testutil.TestParameter.TEST_PARAM_KEY_1;
import static ej.test.aaamockmvc.testdata.testutil.TestParameter.TEST_PARAM_KEY_VALUE_MAP_1_2;
import static ej.test.aaamockmvc.testdata.testutil.TestParameter.TEST_PARAM_VALUE_1;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import ej.test.aaamockmvc.context.TestRequestContextBuilder;
import ej.test.aaamockmvc.request.act.TestActPerformImpl;
import ej.test.aaamockmvc.request.arrange.get.head.TestArrangeGetHeadImpl;
import ej.test.aaamockmvc.request.arrange.utils.TestArrangeUrlUtils;
import ej.test.aaamockmvc.request.model.TestRequestDto;
import ej.test.aaamockmvc.request.model.TestRequestType;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

class TestArrangeGetParamImplTest {

  private TestRequestDto requestDto;
  private TestArrangeGetParamImpl impl;
  private MockedStatic<TestArrangeUrlUtils> mockTestArrangeRequestUrl;

  @BeforeEach
  void setUp() {
    this.mockTestArrangeRequestUrl = Mockito.mockStatic(TestArrangeUrlUtils.class);

    this.requestDto = new TestRequestDto(TestRequestType.GET);
    var context = TestRequestContextBuilder.getInstance().withTestRequest(this.requestDto).build();

    this.impl = new TestArrangeGetParamImpl(context);
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
            TestArrangeUrlUtils.addParam(
                this.requestDto.getUrl(), TEST_PARAM_KEY_1, TEST_PARAM_VALUE_1));
  }

  @Test
  void GIVEN_key_value_map_WHEN_arrangeKeyValue_THEN_addParam_is_called() {
    // Act
    this.impl.arrangeKeyValue(TEST_PARAM_KEY_VALUE_MAP_1_2);

    // Assert
    this.mockTestArrangeRequestUrl.verify(
        () -> TestArrangeUrlUtils.addParam(this.requestDto.getUrl(), TEST_PARAM_KEY_VALUE_MAP_1_2));
  }

  @Test
  void WHEN_arrangeHead_THEN_expected_class() {
    // Act
    var arrangeHead = this.impl.arrangeHead();

    // Assert
    assertThat(arrangeHead.getClass(), is(TestArrangeGetHeadImpl.class));
  }

  @Test
  void WHEN_act_expected_class() {
    // Act
    var act = this.impl.act();

    // Assert
    MatcherAssert.assertThat(act.getClass(), is(TestActPerformImpl.class));
  }
}
