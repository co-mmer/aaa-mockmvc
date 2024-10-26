package io.github.co_mmer.aaamockmvc.ej.testdata.testutil;

import java.util.Map;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestHeader {

  public static final String TEST_HEADER_KEY_0 = TestValue.KEY_0;
  public static final String TEST_HEADER_KEY_1 = TestValue.KEY_1;
  public static final String TEST_HEADER_KEY_2 = TestValue.KEY_2;

  public static final String TEST_HEADER_VALUE_0 = TestValue.VALUE_0;
  public static final String TEST_HEADER_VALUE_1 = TestValue.VALUE_1;
  public static final String TEST_HEADER_VALUE_2 = TestValue.VALUE_2;

  public static final Map<String, Object> TEST_HEADER_MAP_1_2 =
      Map.of(TEST_HEADER_KEY_1, TEST_HEADER_VALUE_1, TEST_HEADER_KEY_2, TEST_HEADER_VALUE_2);

  public static final String TEST_AUTH_KEY = "Authorization";
  public static final String TEST_AUTH_VALUE = "test";
}
