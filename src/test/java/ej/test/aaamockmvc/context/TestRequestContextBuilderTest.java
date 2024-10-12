package ej.test.aaamockmvc.context;

import static ej.test.aaamockmvc.testdata.testutil.TestDataRequestDto.TEST_REQUEST_DTO;
import static ej.test.aaamockmvc.testdata.testutil.TestObject.TEST_REQUEST_CONFIG;
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
            .withTestRequestConfig(TEST_REQUEST_CONFIG)
            .withTestRequest(TEST_REQUEST_DTO)
            .build();

    // Assert
    assertThat(context.config(), is(TEST_REQUEST_CONFIG));
    assertThat(context.request(), Matchers.is(TEST_REQUEST_DTO));
  }
}
