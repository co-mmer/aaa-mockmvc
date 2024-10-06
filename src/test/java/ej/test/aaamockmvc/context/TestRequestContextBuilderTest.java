package ej.test.aaamockmvc.context;

import static ej.test.aaamockmvc.testdata.testutil.TestDataRequestDto.TEST_REQUEST_DTO;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;

class TestRequestContextBuilderTest {

  @Test
  void GIVEN_mvc_request_WHEN_build_THEN_return_expected_context() {
    // Arrange
    var mockMvc = Mockito.mock(MockMvc.class);

    // Act
    var context =
        TestRequestContextBuilder.getInstance()
            .withMockMvc(mockMvc)
            .withTestRequest(TEST_REQUEST_DTO)
            .build();

    // Assert
    assertThat(context.mvc(), is(mockMvc));
    assertThat(context.request(), Matchers.is(TEST_REQUEST_DTO));
  }
}
