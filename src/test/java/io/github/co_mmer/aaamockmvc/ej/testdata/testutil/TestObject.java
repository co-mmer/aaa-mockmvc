package io.github.co_mmer.aaamockmvc.ej.testdata.testutil;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.co_mmer.aaamockmvc.ej.test.web.request.context.TestRequestBean;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestObject {

  public static final String A = "A";
  public static final String B = "B";
  public static final String NEW = "NEW";
  public static final String CLOSE = "Close";

  public static final int ID1 = 1;
  public static final int ID2 = 2;
  public static final int ID3 = 3;
  public static final int ID4 = 4;
  public static final String TEST_EMPTY_BYTE = Arrays.toString(new byte[0]);

  public static final TestObjectSimple A1 = new TestObjectSimple(ID1, A);
  public static final TestObjectSimple A2 = new TestObjectSimple(ID2, A);
  public static final TestObjectSimple A3 = new TestObjectSimple(ID3, A);
  public static final TestObjectSimple A4 = new TestObjectSimple(ID4, A);
  public static final TestObjectMatch B1 = new TestObjectMatch(ID1, B, NEW);
  public static final TestObjectMatch B2 = new TestObjectMatch(ID2, B, NEW);

  public static final List<TestObjectMatch> TEST_LIST_B1NEW_B2NEW = List.of(B1, B2);

  public static final List<TestObjectSimple> TEST_LIST_A1 = List.of(A1);
  public static final List<TestObjectSimple> TEST_LIST_A3 = List.of(A3);
  public static final List<TestObjectSimple> TEST_LIST_A1_A2 = List.of(A1, A2);

  public static final List<TestObjectSimple> TEST_LIST_A1_A3 = List.of(A1, A3);
  public static final List<TestObjectSimple> TEST_LIST_A3_A1 = List.of(A3, A1);
  public static final Set<TestObjectSimple> TEST_SET_A1_A2 = Set.of(A1, A2);

  public static final Map<Integer, TestObjectSimple> TEST_MAP_A1_A2 = Map.of(ID1, A1, ID2, A2);
  public static final Map<Boolean, TestObjectSimple> TEST_MAP_A1_A3 = Map.of(TRUE, A1, FALSE, A3);

  public static final String TEST_A1_JSON = mapToString(A1);
  public static final String TEST_A2_JSON = mapToString(A2);
  public static final String TEST_B1NEW_JSON = mapToString(B1);

  public static final String TEST_LIST_A1_A2_JSON = mapToString(TEST_LIST_A1_A2);
  public static final String TEST_LIST_A1_A3_JSON = mapToString(TEST_LIST_A1_A3);
  public static final String TEST_LIST_B1NEW_B2NEW_JSON = mapToString(TEST_LIST_B1NEW_B2NEW);

  public static final String TEST_SET_A1_A2_JSON = mapToString(TEST_SET_A1_A2);
  public static final String TEST_MAP_A1_A2_JSON = mapToString(TEST_MAP_A1_A2);
  public static final String TEST_MAP_A1_A3_JSON = mapToString(TEST_MAP_A1_A3);
  public static final String TEST_MAP_EMPTY_JSON = mapToString(Collections.emptyMap());

  public static final TestRequestBean TEST_REQUEST_BEAN =
      new TestRequestBean(TestDataMockMvc.MOCK_MVC, new ObjectMapper());

  @SneakyThrows
  private static String mapToString(Object value) {
    return new ObjectMapper().writeValueAsString(value);
  }
}
