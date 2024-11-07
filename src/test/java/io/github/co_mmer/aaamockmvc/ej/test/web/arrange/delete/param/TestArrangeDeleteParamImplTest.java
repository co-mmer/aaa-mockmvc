package io.github.co_mmer.aaamockmvc.ej.test.web.arrange.delete.param;

import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestParameter.TEST_PARAM_KEY_1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestParameter.TEST_PARAM_KEY_VALUE_MAP_1_2;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestParameter.TEST_PARAM_VALUE_1;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

import io.github.co_mmer.aaamockmvc.ej.test.web.act.TestActImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.base.url.TestArrangeUrlUtils;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.delete.head.TestArrangeDeleteHeadImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.request.context.TestRequestContextBuilder;
import io.github.co_mmer.aaamockmvc.ej.test.web.request.model.TestRequestDto;
import io.github.co_mmer.aaamockmvc.ej.test.web.request.model.TestRequestType;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

class TestArrangeDeleteParamImplTest {

  private TestRequestDto dto;
  private TestArrangeDeleteParamImpl impl;
  private MockedStatic<TestArrangeUrlUtils> mockTestArrangeUrlUtils;

  @BeforeEach
  void setUp() {
    this.mockTestArrangeUrlUtils = Mockito.mockStatic(TestArrangeUrlUtils.class);

    this.dto = new TestRequestDto(TestRequestType.DELETE);
    var context = TestRequestContextBuilder.getInstance().withTestRequest(this.dto).build();

    this.impl = new TestArrangeDeleteParamImpl(context);
  }

  @AfterEach
  void clean() {
    this.mockTestArrangeUrlUtils.close();
  }

  @Test
  @SuppressWarnings("ConstantConditions")
  void GIVEN_null_WHEN_call_constructor_THEN_throw_NullPointerException() {
    assertThrows(NullPointerException.class, () -> new TestArrangeDeleteParamImpl(null));
  }

  @Test
  void GIVEN_key_value_WHEN_arrangeKeyValue_THEN_addParam_is_called() {
    // Act
    this.impl.arrangeKeyValue(TEST_PARAM_KEY_1, TEST_PARAM_VALUE_1);

    // Assert
    this.mockTestArrangeUrlUtils.verify(
        () ->
            TestArrangeUrlUtils.addParam(this.dto.getUrl(), TEST_PARAM_KEY_1, TEST_PARAM_VALUE_1));
  }

  @Test
  @SuppressWarnings("ConstantConditions")
  void GIVEN_map_null_WHEN_arrangeKeyValue_THEN_throw_NullPointerException() {
    assertThrows(NullPointerException.class, () -> this.impl.arrangeKeyValue(null));
  }

  @Test
  void GIVEN_key_value_map_WHEN_arrangeKeyValue_THEN_setParam_is_called() {
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
    MatcherAssert.assertThat(arrangeHead.getClass(), is(TestArrangeDeleteHeadImpl.class));
  }

  @Test
  void WHEN_act_THEN_expected_class() {
    // Act
    var act = this.impl.act();

    // Assert
    assertThat(act.getClass(), is(TestActImpl.class));
  }
}
