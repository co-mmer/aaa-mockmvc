package io.github.co_mmer.aaamockmvc.testdata.testutil;

import static io.github.co_mmer.aaamockmvc.testdata.testutil.TestBody.TEST_BODY_JSON;
import static io.github.co_mmer.aaamockmvc.testdata.testutil.TestFiles.TEST_FILE_1;
import static io.github.co_mmer.aaamockmvc.testdata.testutil.TestFiles.TEST_FILE_2;
import static org.springframework.http.MediaType.APPLICATION_JSON;

import io.github.co_mmer.aaamockmvc.test.web.request.model.TestRequestBodyDto;
import java.util.Collections;
import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestDataRequestBodyDto {

  public static final TestRequestBodyDto TEST_REQUEST_BODY_CONTENT_NULL =
      createRequestBodyDto(null, APPLICATION_JSON);
  public static final TestRequestBodyDto TEST_REQUEST_BODY_CONTENT =
      createRequestBodyDto(TEST_BODY_JSON, APPLICATION_JSON);
  public static final TestRequestBodyDto TEST_REQUEST_BODY_FILE_NULL = createRequestBodyDto(null);
  public static final TestRequestBodyDto TEST_REQUEST_BODY_FILE_EMPTY =
      createRequestBodyDto(Collections.emptyList());
  public static final TestRequestBodyDto TEST_REQUEST_BODY_FILE_1 =
      createRequestBodyDto(List.of(TEST_FILE_1));
  public static final TestRequestBodyDto TEST_REQUEST_BODY_FILE_2 =
      createRequestBodyDto(List.of(TEST_FILE_1, TEST_FILE_2));

  public static TestRequestBodyDto createRequestBodyDto(String content, MediaType contentType) {
    var requestBodyDto = new TestRequestBodyDto();
    requestBodyDto.setContent(content);
    requestBodyDto.setContentType(contentType);
    return requestBodyDto;
  }

  private static TestRequestBodyDto createRequestBodyDto(List<MockMultipartFile> files) {
    var requestBodyDto = new TestRequestBodyDto();
    requestBodyDto.setFiles(files);
    return requestBodyDto;
  }
}
