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

  public static final TestObject1Dto TEST_OBJECT_1_DTO = new TestObject1Dto(1, "test");
  public static final TestObject1Dto TEST_OBJECT_2_DTO = new TestObject1Dto(2, "test");
  public static final TestObject1Dto TEST_OBJECT_3_DTO = new TestObject1Dto(3, "test");
  public static final List<TestObject1Dto> TEST_LIST_1_DTO =
      List.of(TEST_OBJECT_1_DTO, TEST_OBJECT_2_DTO);
  public static final List<TestObject1Dto> TEST_LIST_1_3_DTO =
      List.of(TEST_OBJECT_1_DTO, TEST_OBJECT_3_DTO);
  public static final List<TestObject1Dto> TEST_LIST_3_1_DTO =
      List.of(TEST_OBJECT_3_DTO, TEST_OBJECT_1_DTO);
  public static final Set<TestObject1Dto> TEST_SET_1_DTO =
      Set.of(TEST_OBJECT_1_DTO, TEST_OBJECT_2_DTO);
  public static final Map<Boolean, TestObject1Dto> TEST_MAP_1_DTO =
      Map.of(TRUE, TEST_OBJECT_1_DTO, FALSE, TEST_OBJECT_2_DTO);

  public static final Map<Boolean, TestObject1Dto> TEST_OBJECTS_MAP_2_DTO =
      Map.of(TRUE, TEST_OBJECT_1_DTO, FALSE, TEST_OBJECT_3_DTO);
  public static final String TEST_OBJECT_1_JSON = mapToString(TEST_OBJECT_1_DTO);
  public static final String TEST_LIST_1_JSON = mapToString(TEST_LIST_1_DTO);
  public static final String TEST_LIST_1_3_JSON = mapToString(TEST_LIST_1_3_DTO);

  public static final String TEST_SET_1_JSON = mapToString(TEST_SET_1_DTO);
  public static final String TEST_MAP_1_JSON = mapToString(TEST_MAP_1_DTO);
  public static final String TEST_OBJECTS_MAP_2_JSON = mapToString(TEST_OBJECTS_MAP_2_DTO);

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
