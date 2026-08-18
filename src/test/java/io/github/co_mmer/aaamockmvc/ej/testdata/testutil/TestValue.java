package io.github.co_mmer.aaamockmvc.ej.testdata.testutil;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.step.TestStepDto;
import java.net.URI;
import java.util.List;
import java.util.Map;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestValue {

  public static final String QUERY_KEY_SEARCH = "q";
  public static final String QUERY_KEY_PAGE = "page";
  public static final String QUERY_VALUE_SEARCH_TERM = "search";
  public static final String QUERY_VALUE_PAGE_NUMBER = "2";

  public static final Map<String, String> QUERY_MAP_SEARCH_PAGE =
      Map.of(QUERY_KEY_SEARCH, QUERY_VALUE_SEARCH_TERM, QUERY_KEY_PAGE, QUERY_VALUE_PAGE_NUMBER);

  public static final Map<String, List<String>> QUERY_MAP_LIST_SEARCH_PAGE =
      Map.of(
          QUERY_KEY_SEARCH,
          List.of(QUERY_VALUE_SEARCH_TERM),
          QUERY_KEY_PAGE,
          List.of(QUERY_VALUE_PAGE_NUMBER));

  public static final String HEADER_KEY_1 = "key_1";
  public static final String HEADER_KEY_2 = "key_2";
  public static final String HEADER_VALUE_1 = "value_1";
  public static final String HEADER_VALUE_2 = "value_2";
  public static final Map<String, List<Object>> HEADER_MAP_1_2 =
      Map.of(HEADER_KEY_1, List.of(HEADER_VALUE_1), HEADER_KEY_2, List.of(HEADER_VALUE_2));

  public static final String TEST_AUTH_KEY = "Authorization";
  public static final String TEST_AUTH_VALUE = "test";

  public static final String PATH_VARIABLE_USER_ID = "var1";

  public static final String BASE_PATH = "/test";
  public static final String BASE_URL = "test";
  public static final URI BASE_URI = createUri();

  public static final String PATH_WITH_USER_ID = "/test/{" + PATH_VARIABLE_USER_ID + "}";
  public static final URI URI_WITH_USER_ID = createUriQuery();

  public static final String HEADER_KEY_AUTH = "key1";
  public static final String HEADER_KEY_CONTENT_TYPE = "key2";

  public static final String HEADER_VALUE_TOKEN = "value1";
  public static final String HEADER_VALUE_JSON = "value2";
  public static final String STEP_NAME = "Create User";
  public static final TestStepDto STEP = new TestStepDto(STEP_NAME);

  @SneakyThrows
  private static URI createUri() {
    return new URI(BASE_PATH);
  }

  @SneakyThrows
  private static URI createUriQuery() {
    return new URI("/test/var1");
  }
}
