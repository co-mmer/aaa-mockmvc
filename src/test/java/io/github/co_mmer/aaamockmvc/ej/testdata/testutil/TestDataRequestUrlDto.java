package io.github.co_mmer.aaamockmvc.ej.testdata.testutil;

import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.QUERY_KEY_SEARCH;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.QUERY_VALUE_SEARCH_TERM;

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
      createTestRequestUrlDto(Map.of(QUERY_KEY_SEARCH, QUERY_VALUE_SEARCH_TERM));

  private static TestRequestUrlDto createTestRequestUrlDto(Map<String, String> param) {
    var requestHeadDto = new TestRequestUrlDto();
    requestHeadDto.setUri(TestValue.BASE_URI);
    requestHeadDto.setQuery(param);
    return requestHeadDto;
  }
}
