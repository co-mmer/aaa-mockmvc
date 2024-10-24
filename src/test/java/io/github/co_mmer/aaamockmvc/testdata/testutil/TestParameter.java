package io.github.co_mmer.aaamockmvc.testdata.testutil;

import java.util.Map;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestParameter {

  public static final String TEST_PARAM_KEY_0 = TestValue.KEY_0;
  public static final String TEST_PARAM_KEY_1 = TestValue.KEY_1;
  public static final String TEST_PARAM_KEY_2 = TestValue.KEY_2;

  public static final String TEST_PARAM_VALUE_0 = TestValue.VALUE_0;
  public static final String TEST_PARAM_VALUE_1 = TestValue.VALUE_1;
  public static final String TEST_PARAM_VALUE_2 = TestValue.VALUE_2;

  public static final Map<String, String> TEST_PARAM_KEY_VALUE_MAP_1_2 =
      Map.of(TEST_PARAM_KEY_1, TEST_PARAM_VALUE_1, TEST_PARAM_KEY_2, TEST_PARAM_VALUE_2);
}
