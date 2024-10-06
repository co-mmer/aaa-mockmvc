package ej.test.aaamockmvc.request.act.component;

import static ej.test.aaamockmvc.testdata.testutil.TestBody.TEST_BODY_JSON;
import static ej.test.aaamockmvc.testdata.testutil.TestDataRequestBodyDto.TEST_REQUEST_BODY_CONTENT_NULL;
import static ej.test.aaamockmvc.testdata.testutil.TestDataRequestBodyDto.TEST_REQUEST_BODY_FILE_1;
import static ej.test.aaamockmvc.testdata.testutil.TestDataRequestBodyDto.TEST_REQUEST_BODY_FILE_2;
import static ej.test.aaamockmvc.testdata.testutil.TestDataRequestBodyDto.TEST_REQUEST_BODY_FILE_EMPTY;
import static ej.test.aaamockmvc.testdata.testutil.TestDataRequestBodyDto.TEST_REQUEST_BODY_FILE_NULL;
import static ej.test.aaamockmvc.testdata.testutil.TestDataRequestBodyDto.createRequestBodyDto;
import static ej.test.aaamockmvc.testdata.testutil.TestFiles.TEST_FILE_1;
import static ej.test.aaamockmvc.testdata.testutil.TestFiles.TEST_FILE_2;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_PDF;

import ej.test.aaamockmvc.request.act.strategy.component.TestComponentBody;
import ej.test.aaamockmvc.request.model.TestRequestBodyDto;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;

class TestComponentBodyTest {

  private MockMultipartHttpServletRequestBuilder mockRequestBuilder;

  @BeforeEach
  void setUp() {
    this.mockRequestBuilder = mock(MockMultipartHttpServletRequestBuilder.class);
  }

  @Test
  void GIVEN_body_content_null_WHEN_apply_THEN_verifyNoInteractions() {
    // Act
    TestComponentBody.apply(this.mockRequestBuilder, TEST_REQUEST_BODY_CONTENT_NULL);

    // Assert
    verifyNoInteractions(this.mockRequestBuilder);
  }

  @ParameterizedTest
  @MethodSource("useBodyContent")
  void GIVEN_useBodyContent_WHEN_apply_THEN_verify_expected_methods(
      String content, MediaType contentType) {
    // Arrange
    var body = createRequestBodyDto(content, contentType);

    // Act
    TestComponentBody.apply(this.mockRequestBuilder, body);

    // Assert
    verify(this.mockRequestBuilder).content(content);
    verify(this.mockRequestBuilder).contentType(contentType);
    verifyNoMoreInteractions(this.mockRequestBuilder);
  }

  private static Stream<Arguments> useBodyContent() {
    return Stream.of(
        Arguments.of("", null),
        Arguments.of("", APPLICATION_PDF),
        Arguments.of(TEST_BODY_JSON, null),
        Arguments.of(TEST_BODY_JSON, APPLICATION_JSON));
  }

  @ParameterizedTest
  @MethodSource("useBodyFileBlank")
  void GIVEN_useBodyFileBlank_WHEN_apply_THEN_verifyNoInteractions(TestRequestBodyDto bodyDto) {
    // Act
    TestComponentBody.apply(this.mockRequestBuilder, bodyDto);

    // Assert
    verifyNoInteractions(this.mockRequestBuilder);
  }

  private static Stream<Arguments> useBodyFileBlank() {
    return Stream.of(
        Arguments.of(TEST_REQUEST_BODY_FILE_NULL), Arguments.of(TEST_REQUEST_BODY_FILE_EMPTY));
  }

  @Test
  void GIVEN_body_file_1_WHEN_apply_THEN_verify_file() {
    // Act
    TestComponentBody.apply(this.mockRequestBuilder, TEST_REQUEST_BODY_FILE_1);

    // Assert
    verify(this.mockRequestBuilder, times(1)).file(TEST_FILE_1);
  }

  @Test
  void GIVEN_body_file_2_WHEN_apply_THEN_verify_file() {
    // Act
    TestComponentBody.apply(this.mockRequestBuilder, TEST_REQUEST_BODY_FILE_2);

    // Assert
    verify(this.mockRequestBuilder, times(1)).file(TEST_FILE_1);
    verify(this.mockRequestBuilder, times(1)).file(TEST_FILE_2);
  }
}
