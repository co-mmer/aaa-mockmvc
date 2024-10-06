package ej.test.aaamockmvc.testdata.testutil;

import static ej.test.aaamockmvc.testdata.testutil.TestParameter.TEST_PARAM_KEY_1;
import static ej.test.aaamockmvc.testdata.testutil.TestParameter.TEST_PARAM_VALUE_1;
import static ej.test.aaamockmvc.testdata.testutil.TestValue.TEST_URI;

import ej.test.aaamockmvc.request.model.TestRequestUrlDto;
import java.util.Collections;
import java.util.Map;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestDataRequestUrlDto {

  public static final TestRequestUrlDto TEST_REQUEST_URL_PARAM_NULL = createTestRequestUrlDto(null);
  public static final TestRequestUrlDto TEST_REQUEST_URL_PARAM_EMPTY =
      createTestRequestUrlDto(Collections.emptyMap());

  public static final TestRequestUrlDto TEST_REQUEST_URL_PARAM =
      createTestRequestUrlDto(Map.of(TEST_PARAM_KEY_1, TEST_PARAM_VALUE_1));

  private static TestRequestUrlDto createTestRequestUrlDto(Map<String, String> param) {
    var requestHeadDto = new TestRequestUrlDto();
    requestHeadDto.setUri(TEST_URI);
    requestHeadDto.setParam(param);
    return requestHeadDto;
  }
}
