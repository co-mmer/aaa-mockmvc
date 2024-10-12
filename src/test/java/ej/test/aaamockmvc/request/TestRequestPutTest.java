package ej.test.aaamockmvc.request;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.verify;

import ej.test.aaamockmvc.request.model.TestRequestDto;
import ej.test.aaamockmvc.request.model.TestRequestType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;

class TestRequestPutTest extends TestRequestBase {

  @InjectMocks private TestRequestPut testRequestPut;

  @Test
  void WHEN_arrange_THEN_TestRequestContext_is_called_with_mockMvc() {
    // Act
    this.testRequestPut.arrange();

    // Assert
    verify(this.mockContextBuilder).withTestRequestConfig(this.config);
  }

  @Test
  void WHEN_arrange_THEN_TestRequestContext_is_called_with_dto() {
    // Act
    this.testRequestPut.arrange();

    // Assert
    var mockCaptor = ArgumentCaptor.forClass(TestRequestDto.class);
    verify(this.mockContextBuilder).withTestRequest(mockCaptor.capture());

    var capturedTestRequestDTO = mockCaptor.getValue();
    assertThat(capturedTestRequestDTO, is(notNullValue()));
    assertThat(capturedTestRequestDTO.getType(), Matchers.is(TestRequestType.PUT));
  }
}
