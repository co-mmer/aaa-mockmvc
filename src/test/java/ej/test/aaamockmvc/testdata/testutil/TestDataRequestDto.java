package ej.test.aaamockmvc.testdata.testutil;

import static ej.test.aaamockmvc.testdata.testutil.TestFiles.TEST_FILE_1;
import static ej.test.aaamockmvc.testdata.testutil.TestParameter.TEST_PARAM_KEY_VALUE_MAP_1_2;
import static ej.test.aaamockmvc.testdata.testutil.TestValue.TEST_URI;

import ej.test.aaamockmvc.request.model.TestRequestDto;
import ej.test.aaamockmvc.request.model.TestRequestType;
import java.util.Collections;
import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.mock.web.MockMultipartFile;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestDataRequestDto {

  public static final TestRequestDto TEST_REQUEST_DTO = createTestRequestDto();
  public static final TestRequestDto TEST_REQUEST_DTO_WITH_FILE_EMPTY =
      createTestRequestDto(Collections.emptyList());
  public static final TestRequestDto TEST_REQUEST_DTO_WITH_FILE_1 =
      createTestRequestDto(List.of(TEST_FILE_1));

  private static TestRequestDto createTestRequestDto() {
    var testRequestDto = new TestRequestDto(TestRequestType.GET);
    testRequestDto.getUrl().setUri(TEST_URI);
    testRequestDto.getUrl().setParam(TEST_PARAM_KEY_VALUE_MAP_1_2);
    return testRequestDto;
  }

  private static TestRequestDto createTestRequestDto(List<MockMultipartFile> files) {
    var testRequestDto = new TestRequestDto(TestRequestType.GET);
    testRequestDto.getUrl().setUri(TEST_URI);
    testRequestDto.getUrl().setParam(TEST_PARAM_KEY_VALUE_MAP_1_2);
    testRequestDto.getBody().setFiles(files);
    return testRequestDto;
  }
}
