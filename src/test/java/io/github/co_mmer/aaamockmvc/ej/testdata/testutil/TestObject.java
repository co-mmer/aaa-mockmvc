package io.github.co_mmer.aaamockmvc.ej.testdata.testutil;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.co_mmer.aaamockmvc.ej.test.web.request.context.TestRequestBean;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestObject {

  public static final String A = "A";
  public static final String B = "B";
  public static final String NEW = "NEW";
  public static final String CLOSE = "Close";

  public static final TestObject1 A1 = new TestObject1(1, A);
  public static final TestObject1 A2 = new TestObject1(2, A);
  public static final TestObject1 A3 = new TestObject1(3, A);
  public static final TestObject1 A4 = new TestObject1(4, A);
  public static final TestObject2 B1 = new TestObject2(1, B, NEW);
  public static final TestObject2 B2 = new TestObject2(2, B, NEW);
  public static final List<TestObject2> TEST_LIST_B1NEW_B2NEW = List.of(B1, B2);

  public static final List<TestObject1> TEST_LIST_A1 = List.of(A1);
  public static final List<TestObject1> TEST_LIST_A3 = List.of(A3);
  public static final List<TestObject1> TEST_LIST_A1_A2 = List.of(A1, A2);

  public static final List<TestObject1> TEST_LIST_A1_A3 = List.of(A1, A3);
  public static final List<TestObject1> TEST_LIST_A3_A1 = List.of(A3, A1);
  public static final Set<TestObject1> TEST_SET_A1_A2 = Set.of(A1, A2);
  public static final Map<Integer, TestObject1> TEST_MAP_A1_A2 =
      Map.of(1, A1, 2, A2);

  public static final Map<Boolean, TestObject1> TEST_MAP_A1_A3 =
      Map.of(TRUE, A1, FALSE, A3);
  public static final String TEST_A1_JSON = mapToString(A1);
  public static final String TEST_LIST_A1_A2_JSON = mapToString(TEST_LIST_A1_A2);
  public static final String TEST_LIST_A1_A3_JSON = mapToString(TEST_LIST_A1_A3);
  public static final String TEST_LIST_B1NEW_B2NEW_JSON = mapToString(TEST_LIST_B1NEW_B2NEW);

  public static final String TEST_SET_A1_A2_JSON = mapToString(TEST_SET_A1_A2);
  public static final String TEST_MAP_A1_A2_JSON = mapToString(TEST_MAP_A1_A2);
  public static final String TEST_MAP_A1_A3_JSON = mapToString(TEST_MAP_A1_A3);
  public static final String TEST_MAP_EMPTY_JSON = mapToString(Collections.emptyMap());

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
