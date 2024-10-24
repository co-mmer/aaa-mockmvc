package io.github.co_mmer.aaamockmvc.testdata.testutil;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_XML;

import io.github.co_mmer.aaamockmvc.test.web.request.model.TestRequestHeadDto;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.MediaType;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestDataRequestHeadDto {

  public static final TestRequestHeadDto TEST_REQUEST_HEAD_KEY_VALUE_NULL =
      createRequestHeadDto(null, null, null);
  public static final TestRequestHeadDto TEST_REQUEST_HEAD_KEY_VALUE_EMPTY =
      createRequestHeadDto(Collections.emptyMap(), null, null);
  public static final TestRequestHeadDto TEST_REQUEST_HEAD_KEY_VALUE_1 =
      createRequestHeadDto(
          Map.of(TestHeader.TEST_HEADER_KEY_1, TestHeader.TEST_HEADER_VALUE_1), null, null);
  public static final TestRequestHeadDto TEST_REQUEST_HEAD_KEY_VALUE_2 =
      createRequestHeadDto(TestHeader.TEST_HEADER_MAP_1_2, null, null);
  public static final TestRequestHeadDto TEST_REQUEST_HEAD_ACCEPT_NULL =
      createRequestHeadDto(null, null, null);
  public static final TestRequestHeadDto TEST_REQUEST_HEAD_ACCEPT_EMPTY =
      createRequestHeadDto(null, Collections.emptyList(), null);
  public static final TestRequestHeadDto TEST_REQUEST_HEAD_ACCEPT_1 =
      createRequestHeadDto(null, List.of(APPLICATION_JSON), null);
  public static final TestRequestHeadDto TEST_REQUEST_HEAD_ACCEPT_2 =
      createRequestHeadDto(null, List.of(APPLICATION_JSON, APPLICATION_XML), null);
  public static final TestRequestHeadDto TEST_REQUEST_HEAD_CONTENT_TYPE_NULL =
      createRequestHeadDto(null, null, null);
  public static final TestRequestHeadDto TEST_REQUEST_HEAD_CONTENT_TYPE_EMPTY =
      createRequestHeadDto(null, null, Collections.emptyList());
  public static final TestRequestHeadDto TEST_REQUEST_HEAD_CONTENT_TYPE_1 =
      createRequestHeadDto(null, null, List.of(APPLICATION_JSON));
  public static final TestRequestHeadDto TEST_REQUEST_HEAD_CONTENT_TYPE_2 =
      createRequestHeadDto(null, null, List.of(APPLICATION_JSON, APPLICATION_XML));

  private static TestRequestHeadDto createRequestHeadDto(
      Map<String, Object> keyValue, List<MediaType> accepts, List<MediaType> contentTypes) {

    var requestHeadDto = new TestRequestHeadDto();
    requestHeadDto.setKeyValue(keyValue);
    requestHeadDto.setAccepts(accepts);
    requestHeadDto.setContentTypes(contentTypes);
    return requestHeadDto;
  }
}
