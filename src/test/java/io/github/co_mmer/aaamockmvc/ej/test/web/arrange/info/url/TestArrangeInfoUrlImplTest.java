package io.github.co_mmer.aaamockmvc.ej.test.web.arrange.info.url;

import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.TEST_PATH_VAR1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.TEST_URI;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.TEST_URL;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.VAR_STRING_1;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import io.github.co_mmer.aaamockmvc.ej.test.web.act.TestActImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.base.url.TestArrangeUrlUtils;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.info.head.TestArrangeInfoHeadImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.info.param.TestArrangeInfoParamImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.request.context.TestRequestContextBuilder;
import io.github.co_mmer.aaamockmvc.ej.test.web.request.model.TestRequestDto;
import io.github.co_mmer.aaamockmvc.ej.test.web.request.model.TestRequestType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

class TestArrangeInfoUrlImplTest {

  private TestRequestDto requestDto;
  private TestArrangeInfoUrlImpl impl;
  private MockedStatic<TestArrangeUrlUtils> mockTestArrangeUrlUtils;

  @BeforeEach
  void setUp() {
    this.mockTestArrangeUrlUtils = Mockito.mockStatic(TestArrangeUrlUtils.class);

    this.requestDto = new TestRequestDto(TestRequestType.HEAD);
    var context = TestRequestContextBuilder.getInstance().withTestRequest(this.requestDto).build();

    this.impl = new TestArrangeInfoUrlImpl(context);
  }

  @AfterEach
  void clean() {
    this.mockTestArrangeUrlUtils.close();
  }

  @Test
  void GIVEN_url_WHEN_arrangeUrl_THEN_setUri_is_called() {
    // Act
    this.impl.arrangeUrl(TEST_URL);

    // Assert
    this.mockTestArrangeUrlUtils.verify(
        () -> TestArrangeUrlUtils.setUri(this.requestDto.getUrl(), TEST_URL));
  }

  @Test
  void GIVEN_url_var1_and_vars_1_WHEN_arrangeUrl_THEN_setUri_is_called() {
    // Act
    this.impl.arrangeUrl(TEST_PATH_VAR1, VAR_STRING_1);

    // Assert
    this.mockTestArrangeUrlUtils.verify(
        () -> TestArrangeUrlUtils.setUri(this.requestDto.getUrl(), TEST_PATH_VAR1, VAR_STRING_1));
  }

  @Test
  void GIVEN_uri_WHEN_arrangeUri_THEN_setUri_is_called() {
    // Act
    this.impl.arrangeUri(TEST_URI);

    // Assert
    this.mockTestArrangeUrlUtils.verify(
        () -> TestArrangeUrlUtils.setUri(this.requestDto.getUrl(), TEST_URI));
  }

  @Test
  void WHEN_arrangeParam_THEN_expected_class() {
    // Act
    var arrangeParam = this.impl.arrangeParam();

    // Assert
    assertThat(arrangeParam.getClass(), is(TestArrangeInfoParamImpl.class));
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
    assertThat(act.getClass(), is(TestActImpl.class));
  }
}
