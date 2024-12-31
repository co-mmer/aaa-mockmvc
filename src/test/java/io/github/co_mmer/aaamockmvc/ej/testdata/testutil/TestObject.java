package io.github.co_mmer.aaamockmvc.ej.testdata.testutil;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.co_mmer.aaamockmvc.ej.test.web.request.context.TestRequestBean;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestObject {

  public static final TestObjectA TEST_A1 = new TestObjectA(1, "test");
  public static final TestObjectA TEST_A2 = new TestObjectA(2, "test");
  public static final TestObjectA TEST_A3 = new TestObjectA(3, "test");
  public static final List<TestObjectA> TEST_LIST_A1 =
      List.of(TEST_A1);
  public static final List<TestObjectA> TEST_LIST_A3 =
      List.of(TEST_A3);
  public static final List<TestObjectA> TEST_LIST_A1_A2 =
      List.of(TEST_A1, TEST_A2);
  public static final List<TestObjectA> TEST_LIST_A1_A3 =
      List.of(TEST_A1, TEST_A3);
  public static final List<TestObjectA> TEST_LIST_A3_A1 =
      List.of(TEST_A3, TEST_A1);
  public static final Set<TestObjectA> TEST_SET_A1_A2 =
      Set.of(TEST_A1, TEST_A2);
  public static final Map<Boolean, TestObjectA> TEST_MAP_A1_A2 =
      Map.of(TRUE, TEST_A1, FALSE, TEST_A2);

  public static final Map<Boolean, TestObjectA> TEST_MAP_A1_A3 =
      Map.of(TRUE, TEST_A1, FALSE, TEST_A3);
  public static final String TEST_A1_JSON = mapToString(TEST_A1);
  public static final String TEST_LIST_A1_A2_JSON = mapToString(TEST_LIST_A1_A2);
  public static final String TEST_LIST_A1_A3_JSON = mapToString(TEST_LIST_A1_A3);

  public static final String TEST_SET_A1_A2_JSON = mapToString(TEST_SET_A1_A2);
  public static final String TEST_MAP_A1_A2_JSON = mapToString(TEST_MAP_A1_A2);
  public static final String TEST_MAP_A1_A3_JSON = mapToString(TEST_MAP_A1_A3);

  public static final TestRequestBean TEST_REQUEST_BEAN =
      new TestRequestBean(TestDataMockMvc.MOCK_MVC, new ObjectMapper());

  private static String mapToString(Object value) {
    try {
      return new ObjectMapper().writeValueAsString(value);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }
}
