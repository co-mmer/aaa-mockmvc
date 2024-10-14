package ej.aaamockmvc.test.web.request.context;

import static ej.aaamockmvc.test.testdata.testutil.TestDataRequestDto.TEST_REQUEST_DTO;
import static ej.aaamockmvc.test.testdata.testutil.TestObject.TEST_REQUEST_CONFIG;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

class TestRequestContextBuilderTest {

  @Test
  void GIVEN_mvc_request_WHEN_build_THEN_return_expected_context() {
    // Act
    var context =
        TestRequestContextBuilder.getInstance()
            .withTestRequestBean(TEST_REQUEST_CONFIG)
            .withTestRequest(TEST_REQUEST_DTO)
            .build();

    // Assert
    assertThat(context.bean(), is(TEST_REQUEST_CONFIG));
    assertThat(context.request(), Matchers.is(TEST_REQUEST_DTO));
  }
}
