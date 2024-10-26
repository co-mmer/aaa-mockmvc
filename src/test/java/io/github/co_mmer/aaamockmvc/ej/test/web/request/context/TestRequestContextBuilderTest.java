package io.github.co_mmer.aaamockmvc.ej.test.web.request.context;

import static org.hamcrest.MatcherAssert.assertThat;

import io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestDataRequestDto;
import io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

class TestRequestContextBuilderTest {

  @Test
  void GIVEN_mvc_request_WHEN_build_THEN_return_expected_context() {
    // Act
    var context =
        TestRequestContextBuilder.getInstance()
            .withTestRequestBean(TestObject.TEST_REQUEST_CONFIG)
            .withTestRequest(TestDataRequestDto.TEST_REQUEST_DTO)
            .build();

    // Assert
    assertThat(context.bean(), Matchers.is(TestObject.TEST_REQUEST_CONFIG));
    assertThat(context.request(), Matchers.is(TestDataRequestDto.TEST_REQUEST_DTO));
  }
}
