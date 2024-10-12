package ej.test.aaamockmvc.testdata.testutil;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestObject {

  public static final TestObjectDto TEST_OBJECT_1_DTO = new TestObjectDto(1, "test");
  public static final TestObjectDto TEST_OBJECT_2_DTO = new TestObjectDto(2, "test");
  public static final TestObjectDto TEST_OBJECT_3_DTO = new TestObjectDto(3, "test");
  public static final List<TestObjectDto> TEST_OBJECTS_1_DTO =
      List.of(TEST_OBJECT_1_DTO, TEST_OBJECT_2_DTO);
  public static final List<TestObjectDto> TEST_OBJECTS_2_DTO =
      List.of(TEST_OBJECT_1_DTO, TEST_OBJECT_3_DTO);
  public static final Set<TestObjectDto> TEST_OBJECTS_SET_1_DTO =
      Set.of(TEST_OBJECT_1_DTO, TEST_OBJECT_2_DTO);
  public static final Set<TestObjectDto> TEST_OBJECTS_SET_2_DTO =
      Set.of(TEST_OBJECT_1_DTO, TEST_OBJECT_3_DTO);
  public static final Map<Boolean, TestObjectDto> TEST_OBJECTS_MAP_1_DTO =
      Map.of(TRUE, TEST_OBJECT_1_DTO, FALSE, TEST_OBJECT_2_DTO);
  public static final Map<Boolean, TestObjectDto> TEST_OBJECTS_MAP_2_DTO =
      Map.of(TRUE, TEST_OBJECT_1_DTO, FALSE, TEST_OBJECT_3_DTO);
  public static final String TEST_OBJECT_1_JSON = mapToString(TEST_OBJECT_1_DTO);
  public static final String TEST_OBJECTS_1_JSON = mapToString(TEST_OBJECTS_1_DTO);
  public static final String TEST_OBJECTS_SET_1_JSON = mapToString(TEST_OBJECTS_SET_1_DTO);
  public static final String TEST_OBJECTS_MAP_1_JSON = mapToString(TEST_OBJECTS_MAP_1_DTO);

  private static String mapToString(Object value) {
    try {
      return new ObjectMapper().writeValueAsString(value);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }
}
