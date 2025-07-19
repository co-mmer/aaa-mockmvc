package io.github.co_mmer.aaamockmvc.ej.testdata.testutil;

import java.net.URI;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestValue {

  public static final String QUERY_KEY_1 = "q";
  public static final String QUERY_KEY_2 = "page";
  public static final String QUERY_VALUE_1 = "search";
  public static final String QUERY_VALUE_2 = "2";

  public static final String KEY_0 = "key_0";
  public static final String KEY_1 = "key_1";
  public static final String KEY_2 = "key_2";
  public static final String VALUE_0 = "value_0";
  public static final String VALUE_1 = "value_1";
  public static final String VALUE_2 = "value_2";

  public static final String VAR_STRING_1 = "var1";
  public static final String VAR_STRING_2 = "var2";
  public static final String VAR_STRING_3 = "var3";
  public static final String VAR_STRING_4 = "var4";
  public static final String VAR_STRING_5 = "var5";
  public static final String VAR_STRING_6 = "var6";
  public static final String TEST_PATH = "/test";
  public static final String TEST_URL = "test";
  public static final URI TEST_URI = createUri();

  public static final char VAR_CHAR_1 = '1';
  public static final int VAR_INT_1 = 1;
  public static final double VAR_DOUBLE_1 = 1D;
  public static final float VAR_FLOAT_1 = 1F;
  public static final boolean VAR_BOOLEAN_TRUE = true;

  public static final String TEST_PATH_VAR1 = "/test/{" + VAR_STRING_1 + "}";
  public static final URI TEST_URI_VAR1 = createUriQuery("/test/var1");

  public static final String TEST_PATH_VARIABLE_2 =
      "/test/{" + VAR_STRING_1 + "}/{" + VAR_STRING_2 + "}";
  public static final String TEST_PATH_VARIABLE_3 =
      "/test/{" + VAR_STRING_1 + "}/{" + VAR_STRING_2 + "}/{" + VAR_STRING_3 + "}";

  public static final String TEST_PATH_VARIABLE_6 =
      "/test/"
          + "{"
          + VAR_STRING_1
          + "}"
          + "/"
          + "{"
          + VAR_STRING_2
          + "}/"
          + "{"
          + VAR_STRING_3
          + "}/"
          + "{"
          + VAR_STRING_4
          + "}/"
          + "{"
          + VAR_STRING_5
          + "}/"
          + "{"
          + VAR_STRING_6
          + "}";

  public static final byte[] TEST_BYTE = new byte[0];

  public static final String TEST_HEAD_KEY_1 = "key1";
  public static final String TEST_HEAD_KEY_2 = "key2";

  public static final String TEST_HEAD_VALUE_1 = "value1";
  public static final String TEST_HEAD_VALUE_2 = "value2";
  public static final String TEST_HEAD_VALUE_3 = "value3";

  @SneakyThrows
  private static URI createUri() {
    return new URI(TEST_PATH);
  }

  @SneakyThrows
  private static URI createUriQuery(String uri) {
    return new URI(uri);
  }
}
