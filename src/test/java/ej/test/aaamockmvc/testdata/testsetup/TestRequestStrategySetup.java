package ej.test.aaamockmvc.testdata.testsetup;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

import ej.test.aaamockmvc.request.act.strategy.builder.TestRequestBuilder;
import ej.test.aaamockmvc.request.act.strategy.component.TestComponentBody;
import ej.test.aaamockmvc.request.act.strategy.component.TestComponentHead;
import ej.test.aaamockmvc.request.model.TestRequestDto;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;

public final class TestRequestStrategySetup {

  private final MockHttpServletRequestBuilder mockRequestBuilder;
  private final MockMultipartHttpServletRequestBuilder mockMultipartRequestBuilder;

  private final MockedStatic<TestRequestBuilder> mockTestRequestUrlBuilder;
  private final MockedStatic<TestComponentHead> mockTestComponentHead;
  private final MockedStatic<TestComponentBody> mockTestComponentBody;

  public TestRequestStrategySetup() {
    this.mockRequestBuilder = Mockito.mock(MockHttpServletRequestBuilder.class);
    this.mockMultipartRequestBuilder = Mockito.mock(MockMultipartHttpServletRequestBuilder.class);

    this.mockTestRequestUrlBuilder = Mockito.mockStatic(TestRequestBuilder.class);
    this.mockTestComponentHead = Mockito.mockStatic(TestComponentHead.class);
    this.mockTestComponentBody = Mockito.mockStatic(TestComponentBody.class);
  }

  public void mockTestRequestBuilderGet(TestRequestDto requestDto) {
    this.mockTestRequestUrlBuilder
        .when(() -> TestRequestBuilder.get(requestDto.getUrl().getUri()))
        .thenReturn(this.mockRequestBuilder);
  }

  public void mockTestRequestBuilderDelete(TestRequestDto requestDto) {
    this.mockTestRequestUrlBuilder
        .when(() -> TestRequestBuilder.delete(requestDto.getUrl().getUri()))
        .thenReturn(this.mockRequestBuilder);
  }

  public void mockTestRequestBuilderHead(TestRequestDto requestDto) {
    this.mockTestRequestUrlBuilder
        .when(() -> TestRequestBuilder.head(requestDto.getUrl().getUri()))
        .thenReturn(this.mockRequestBuilder);
  }

  public void mockTestRequestBuilderOptions(TestRequestDto requestDto) {
    this.mockTestRequestUrlBuilder
        .when(() -> TestRequestBuilder.options(requestDto.getUrl().getUri()))
        .thenReturn(this.mockRequestBuilder);
  }

  public void mockTestRequestBuilderPatch(TestRequestDto requestDto) {
    this.mockTestRequestUrlBuilder
        .when(() -> TestRequestBuilder.patch(requestDto.getUrl().getUri()))
        .thenReturn(this.mockRequestBuilder);
  }

  public void mockTestRequestBuilderPatchMultipart(TestRequestDto requestDto) {
    this.mockTestRequestUrlBuilder
        .when(() -> TestRequestBuilder.patchMultipart(requestDto.getUrl().getUri()))
        .thenReturn(this.mockMultipartRequestBuilder);
  }

  public void mockTestRequestBuilderPost(TestRequestDto requestDto) {
    this.mockTestRequestUrlBuilder
        .when(() -> TestRequestBuilder.post(requestDto.getUrl().getUri()))
        .thenReturn(this.mockRequestBuilder);
  }

  public void mockTestRequestBuilderPostMultipart(TestRequestDto requestDto) {
    this.mockTestRequestUrlBuilder
        .when(() -> TestRequestBuilder.postMultipart(requestDto.getUrl().getUri()))
        .thenReturn(this.mockMultipartRequestBuilder);
  }

  public void mockTestRequestBuilderPut(TestRequestDto requestDto) {
    this.mockTestRequestUrlBuilder
        .when(() -> TestRequestBuilder.put(requestDto.getUrl().getUri()))
        .thenReturn(this.mockRequestBuilder);
  }

  public void mockTestRequestBuilderPutMultipart(TestRequestDto requestDto) {
    this.mockTestRequestUrlBuilder
        .when(() -> TestRequestBuilder.putMultipart(requestDto.getUrl().getUri()))
        .thenReturn(this.mockMultipartRequestBuilder);
  }

  public void verifyTestComponentHeadInteraction(TestRequestDto requestDto) {
    this.mockTestComponentHead.verify(
        () -> TestComponentHead.apply(any(), eq(requestDto.getHead())));
    close();
  }

  public void verifyTestRequestBuilderGet(TestRequestDto requestDto) {
    this.mockTestRequestUrlBuilder.verify(
        () -> TestRequestBuilder.get(requestDto.getUrl().getUri()));
    close();
  }

  public void verifyTestRequestBuilderDelete(TestRequestDto requestDto) {
    this.mockTestRequestUrlBuilder.verify(
        () -> TestRequestBuilder.delete(requestDto.getUrl().getUri()));
    close();
  }

  public void verifyTestRequestBuilderHead(TestRequestDto requestDto) {
    this.mockTestRequestUrlBuilder.verify(
        () -> TestRequestBuilder.head(requestDto.getUrl().getUri()));
    close();
  }

  public void verifyTestRequestBuilderOptions(TestRequestDto requestDto) {
    this.mockTestRequestUrlBuilder.verify(
        () -> TestRequestBuilder.options(requestDto.getUrl().getUri()));
    close();
  }

  public void verifyTestRequestBuilderPatch(TestRequestDto requestDto) {
    this.mockTestRequestUrlBuilder.verify(
        () -> TestRequestBuilder.patch(requestDto.getUrl().getUri()));
    this.mockTestRequestUrlBuilder.verifyNoMoreInteractions();
    close();
  }

  public void verifyTestRequestBuilderPatchMultipart(TestRequestDto requestDto) {
    this.mockTestRequestUrlBuilder.verify(
        () -> TestRequestBuilder.patchMultipart(requestDto.getUrl().getUri()));
    this.mockTestRequestUrlBuilder.verifyNoMoreInteractions();
    close();
  }

  public void verifyTestRequestBuilderPost(TestRequestDto requestDto) {
    this.mockTestRequestUrlBuilder.verify(
        () -> TestRequestBuilder.post(requestDto.getUrl().getUri()));
    this.mockTestRequestUrlBuilder.verifyNoMoreInteractions();
    close();
  }

  public void verifyTestRequestBuilderPostMultipart(TestRequestDto requestDto) {
    this.mockTestRequestUrlBuilder.verify(
        () -> TestRequestBuilder.postMultipart(requestDto.getUrl().getUri()));
    this.mockTestRequestUrlBuilder.verifyNoMoreInteractions();
    close();
  }

  public void verifyTestRequestBuilderPut(TestRequestDto requestDto) {
    this.mockTestRequestUrlBuilder.verify(
        () -> TestRequestBuilder.put(requestDto.getUrl().getUri()));
    this.mockTestRequestUrlBuilder.verifyNoMoreInteractions();
    close();
  }

  public void verifyTestRequestBuilderPutMultipart(TestRequestDto requestDto) {
    this.mockTestRequestUrlBuilder.verify(
        () -> TestRequestBuilder.putMultipart(requestDto.getUrl().getUri()));
    this.mockTestRequestUrlBuilder.verifyNoMoreInteractions();
    close();
  }

  public void verifyTestComponentBodyApply(TestRequestDto requestDto) {
    this.mockTestComponentBody.verify(
        () -> TestComponentBody.apply(this.mockRequestBuilder, requestDto.getBody()));
    close();
  }

  public void verifyTestComponentBodyMultipartApply(TestRequestDto requestDto) {
    this.mockTestComponentBody.verify(
        () -> TestComponentBody.apply(this.mockMultipartRequestBuilder, requestDto.getBody()));
    close();
  }

  public void close() {
    this.mockTestRequestUrlBuilder.close();
    this.mockTestComponentHead.close();
    this.mockTestComponentBody.close();
  }
}
