package ej.test.aaamockmvc.request.arrange.delete.url;

import static ej.test.aaamockmvc.testdata.testutil.TestValue.TEST_PATH_VAR1;
import static ej.test.aaamockmvc.testdata.testutil.TestValue.TEST_URI;
import static ej.test.aaamockmvc.testdata.testutil.TestValue.TEST_URL;
import static ej.test.aaamockmvc.testdata.testutil.TestValue.VAR_STRING_1;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import ej.test.aaamockmvc.context.TestRequestContextBuilder;
import ej.test.aaamockmvc.request.act.TestActPerformImpl;
import ej.test.aaamockmvc.request.arrange.delete.head.TestArrangeDeleteHeadImpl;
import ej.test.aaamockmvc.request.arrange.delete.param.TestArrangeDeleteParamImpl;
import ej.test.aaamockmvc.request.arrange.utils.TestArrangeUrlUtils;
import ej.test.aaamockmvc.request.model.TestRequestDto;
import ej.test.aaamockmvc.request.model.TestRequestType;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

class TestArrangeDeleteImplTest {

  private TestRequestDto requestDto;
  private TestArrangeDeleteUrlImpl impl;
  private MockedStatic<TestArrangeUrlUtils> mockTestArrangeRequestUrl;

  @BeforeEach
  void setUp() {
    this.mockTestArrangeRequestUrl = Mockito.mockStatic(TestArrangeUrlUtils.class);

    this.requestDto = new TestRequestDto(TestRequestType.DELETE);
    var context = TestRequestContextBuilder.getInstance().withTestRequest(this.requestDto).build();

    this.impl = new TestArrangeDeleteUrlImpl(context);
  }

  @AfterEach
  void clean() {
    this.mockTestArrangeRequestUrl.close();
  }

  @Test
  void GIVEN_url_WHEN_arrangeUrl_THEN_setUri_is_called() {
    // Act
    this.impl.arrangeUrl(TEST_URL);

    // Assert
    this.mockTestArrangeRequestUrl.verify(
        () -> TestArrangeUrlUtils.setUri(this.requestDto.getUrl(), TEST_URL));
  }

  @Test
  void GIVEN_url_var1_and_vars_1_WHEN_arrangeUrl_THEN_setUri_is_called() {
    // Act
    this.impl.arrangeUrl(TEST_PATH_VAR1, VAR_STRING_1);

    // Assert
    this.mockTestArrangeRequestUrl.verify(
        () -> TestArrangeUrlUtils.setUri(this.requestDto.getUrl(), TEST_PATH_VAR1, VAR_STRING_1));
  }

  @Test
  void GIVEN_uri_WHEN_arrangeUri_THEN_setUri_is_called() {
    // Act
    this.impl.arrangeUri(TEST_URI);

    // Assert
    this.mockTestArrangeRequestUrl.verify(
        () -> TestArrangeUrlUtils.setUri(this.requestDto.getUrl(), TEST_URI));
  }

  @Test
  void WHEN_arrangeParam_THEN_expected_class() {
    // Act
    var arrangeParam = this.impl.arrangeParam();

    // Assert
    MatcherAssert.assertThat(arrangeParam.getClass(), is(TestArrangeDeleteParamImpl.class));
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
    assertThat(act.getClass(), is(TestActPerformImpl.class));
  }
}
