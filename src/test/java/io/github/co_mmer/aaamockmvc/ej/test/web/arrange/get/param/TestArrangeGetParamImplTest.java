package io.github.co_mmer.aaamockmvc.ej.test.web.arrange.get.param;

import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestParameter.TEST_PARAM_KEY_1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestParameter.TEST_PARAM_KEY_VALUE_MAP_1_2;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestParameter.TEST_PARAM_VALUE_1;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

import io.github.co_mmer.aaamockmvc.ej.test.web.act.TestActImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.base.url.TestArrangeUrlUtils;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.get.head.TestArrangeGetHeadImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.request.context.TestRequestContextBuilder;
import io.github.co_mmer.aaamockmvc.ej.test.web.request.model.TestRequestDto;
import io.github.co_mmer.aaamockmvc.ej.test.web.request.model.TestRequestType;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

class TestArrangeGetParamImplTest {

  private TestRequestDto requestDto;
  private TestArrangeGetParamImpl impl;
  private MockedStatic<TestArrangeUrlUtils> mockTestArrangeUrlUtils;

  @BeforeEach
  void setUp() {
    this.mockTestArrangeUrlUtils = Mockito.mockStatic(TestArrangeUrlUtils.class);

    this.requestDto = new TestRequestDto(TestRequestType.GET);
    var context = TestRequestContextBuilder.getInstance().withTestRequest(this.requestDto).build();

    this.impl = new TestArrangeGetParamImpl(context);
  }

  @AfterEach
  void clean() {
    this.mockTestArrangeUrlUtils.close();
  }

  @Test
  @SuppressWarnings("ConstantConditions")
  void GIVEN_null_WHEN_call_constructor_THEN_throw_NullPointerException() {
    assertThrows(NullPointerException.class, () -> new TestArrangeGetParamImpl(null));
  }

  @Test
  void GIVEN_key_value_WHEN_arrangeKeyValue_THEN_addParam_is_called() {
    // Act
    this.impl.arrangeKeyValue(TEST_PARAM_KEY_1, TEST_PARAM_VALUE_1);

    // Assert
    this.mockTestArrangeUrlUtils.verify(
        () ->
            TestArrangeUrlUtils.addParam(
                this.requestDto.getUrl(), TEST_PARAM_KEY_1, TEST_PARAM_VALUE_1));
  }

  @Test
  @SuppressWarnings("ConstantConditions")
  void GIVEN_map_null_WHEN_arrangeKeyValue_THEN_throw_NullPointerException() {
    assertThrows(NullPointerException.class, () -> this.impl.arrangeKeyValue(null));
  }

  @Test
  void GIVEN_key_value_map_WHEN_arrangeKeyValue_THEN_addParam_is_called() {
    // Act
    this.impl.arrangeKeyValue(TEST_PARAM_KEY_VALUE_MAP_1_2);

    // Assert
    this.mockTestArrangeUrlUtils.verify(
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
    MatcherAssert.assertThat(act.getClass(), is(TestActImpl.class));
  }
}
