package io.github.co_mmer.aaamockmvc.ej.testdata.testutil;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.request.TestRequestUrlDto;
import java.util.Collections;
import java.util.Map;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestDataRequestUrlDto {

  public static final TestRequestUrlDto TEST_REQUEST_URL_QUERY_NULL = createTestRequestUrlDto(null);
  public static final TestRequestUrlDto TEST_REQUEST_URL_QUERY_EMPTY =
      createTestRequestUrlDto(Collections.emptyMap());

  public static final TestRequestUrlDto TEST_REQUEST_URL_QUERY1 =
      createTestRequestUrlDto(
          Map.of(TestParameter.TEST_QUERY_KEY_1, TestParameter.TEST_QUERY_VALUE_1));

  private static TestRequestUrlDto createTestRequestUrlDto(Map<String, String> param) {
    var requestHeadDto = new TestRequestUrlDto();
    requestHeadDto.setUri(TestValue.TEST_URI);
    requestHeadDto.setQuery(param);
    return requestHeadDto;
  }
}
