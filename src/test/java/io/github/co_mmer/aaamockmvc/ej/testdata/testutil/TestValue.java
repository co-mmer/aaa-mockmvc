package io.github.co_mmer.aaamockmvc.ej.testdata.testutil;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestStepDto;
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
  public static final String QUERY_KEY_2 = TestValue.HEADER_KEY_2;
  public static final String QUERY_VALUE_2 = TestValue.HEADER_VALUE_2;

  public static final Map<String, String> QUERY_MAP_SEARCH_PAGE =
      Map.of(QUERY_KEY_SEARCH, QUERY_VALUE_SEARCH_TERM, QUERY_KEY_PAGE, QUERY_VALUE_PAGE_NUMBER);
  public static final Map<String, String> QUERY_MAP_1_2 =
      Map.of(QUERY_KEY_2, QUERY_VALUE_2, QUERY_KEY_PAGE, QUERY_VALUE_PAGE_NUMBER);

  public static final String HEADER_KEY_0 = "key_0";
  public static final String HEADER_KEY_1 = "key_1";
  public static final String HEADER_KEY_2 = "key_2";
  public static final String HEADER_VALUE_0 = "value_0";
  public static final String HEADER_VALUE_1 = "value_1";
  public static final String HEADER_VALUE_2 = "value_2";
  public static final Map<String, List<Object>> HEADER_MAP_1_2 =
      Map.of(HEADER_KEY_1, List.of(HEADER_VALUE_1), HEADER_KEY_2, List.of(HEADER_VALUE_2));

  public static final String TEST_AUTH_KEY = "Authorization";
  public static final String TEST_AUTH_VALUE = "test";

  public static final String PATH_VARIABLE_USER_ID = "var1";
  public static final String PATH_VARIABLE_ORDER_ID = "var2";
  public static final String PATH_VARIABLE_PRODUCT_ID = "var3";
  public static final String PATH_VARIABLE_CATEGORY_ID = "var4";
  public static final String PATH_VARIABLE_SESSION_ID = "var5";
  public static final String PATH_VARIABLE_REQUEST_ID = "var6";

  public static final String BASE_PATH = "/test";
  public static final String BASE_URL = "test";
  public static final URI BASE_URI = createUri();

  public static final char SAMPLE_CHAR_1 = '1';
  public static final int SAMPLE_INT_1 = 1;
  public static final double SAMPLE_DOUBLE_1 = 1D;
  public static final float SAMPLE_FLOAT_1 = 1F;
  public static final boolean SAMPLE_BOOLEAN_TRUE = true;

  public static final String PATH_WITH_USER_ID = "/test/{" + PATH_VARIABLE_USER_ID + "}";
  public static final URI URI_WITH_USER_ID = createUriQuery("/test/var1");

  public static final String PATH_WITH_USER_AND_ORDER =
      "/test/{" + PATH_VARIABLE_USER_ID + "}/{" + PATH_VARIABLE_ORDER_ID + "}";
  public static final String PATH_WITH_USER_ORDER_AND_PRODUCT =
      "/test/{"
          + PATH_VARIABLE_USER_ID
          + "}/{"
          + PATH_VARIABLE_ORDER_ID
          + "}/{"
          + PATH_VARIABLE_PRODUCT_ID
          + "}";

  public static final String PATH_WITH_USER_ORDER_PRODUCT_CATEGORY_SESSION_REQUEST =
      "/test/"
          + "{"
          + PATH_VARIABLE_USER_ID
          + "}"
          + "/"
          + "{"
          + PATH_VARIABLE_ORDER_ID
          + "}/"
          + "{"
          + PATH_VARIABLE_PRODUCT_ID
          + "}/"
          + "{"
          + PATH_VARIABLE_CATEGORY_ID
          + "}/"
          + "{"
          + PATH_VARIABLE_SESSION_ID
          + "}/"
          + "{"
          + PATH_VARIABLE_REQUEST_ID
          + "}";

  public static final byte[] EMPTY_BYTE_ARRAY = new byte[0];

  public static final String HEADER_KEY_AUTH = "key1";
  public static final String HEADER_KEY_CONTENT_TYPE = "key2";

  public static final String HEADER_VALUE_TOKEN = "value1";
  public static final String HEADER_VALUE_JSON = "value2";
  public static final String HEADER_VALUE_XML = "value3";
  public static final String STEP_NAME = "Create User";
  public static final TestStepDto STEP = new TestStepDto(STEP_NAME);

  @SneakyThrows
  private static URI createUri() {
    return new URI(BASE_PATH);
  }

  @SneakyThrows
  private static URI createUriQuery(String uri) {
    return new URI(uri);
  }
}
