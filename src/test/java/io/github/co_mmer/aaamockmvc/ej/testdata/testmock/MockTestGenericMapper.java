package io.github.co_mmer.aaamockmvc.ej.testdata.testmock;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockStatic;

import io.github.co_mmer.aaamockmvc.ej.test.web.mapper.TestGenericMapper;
import io.github.co_mmer.aaamockmvc.ej.test.web.mapper.exception.TestGenericMapperException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.mockito.MockedStatic;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MockTestGenericMapper {

  private static final TestGenericMapperException ERROR =
      new TestGenericMapperException(new Throwable("error"));

  public static MockedStatic<TestGenericMapper> mapToListThrowException() {
    var mockTestGenericMapper = mockStatic(TestGenericMapper.class);
    mockTestGenericMapper
        .when(() -> TestGenericMapper.mapToList(any(), any(), any()))
        .thenThrow(ERROR);

    return mockTestGenericMapper;
  }

  public static MockedStatic<TestGenericMapper> mapToMapThrowException() {
    var mockTestGenericMapper = mockStatic(TestGenericMapper.class);
    mockTestGenericMapper
        .when(() -> TestGenericMapper.mapToMap(any(), any(), any(), any()))
        .thenThrow(ERROR);

    return mockTestGenericMapper;
  }
}
