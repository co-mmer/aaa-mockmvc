package ej.aaamockmvc.test.web.request;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.verify;

import ej.aaamockmvc.test.web.request.model.TestRequestDto;
import ej.aaamockmvc.test.web.request.model.TestRequestType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;

class TestRequestGetTest extends TestRequestBase {

  @InjectMocks private TestRequestGet testRequestGet;

  @Test
  void WHEN_arrange_THEN_TestRequestContextBuilder_is_called_with_bean() {
    // Act
    this.testRequestGet.arrange();

    // Assert
    verify(this.mockContextBuilder).withTestRequestBean(this.bean);
  }

  @Test
  void WHEN_arrange_THEN_TestRequestContext_is_called_with_dto() {
    // Act
    this.testRequestGet.arrange();

    // Assert
    var mockCaptor = ArgumentCaptor.forClass(TestRequestDto.class);
    verify(this.mockContextBuilder).withTestRequest(mockCaptor.capture());

    var capturedTestRequestDTO = mockCaptor.getValue();
    assertThat(capturedTestRequestDTO, is(notNullValue()));
    assertThat(capturedTestRequestDTO.getType(), Matchers.is(TestRequestType.GET));
  }
}
