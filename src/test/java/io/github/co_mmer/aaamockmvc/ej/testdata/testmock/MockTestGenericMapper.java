package io.github.co_mmer.aaamockmvc.ej.testdata.testmock;

import static org.mockito.Mockito.mockStatic;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.mapper.TestGenericMapper;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.mapper.exception.TestGenericMapperException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MockTestGenericMapper {

  private static final TestGenericMapperException ERROR =
      new TestGenericMapperException(new Throwable("error"));

  public static MockedStatic<TestGenericMapper> mockParseWithClass() {
    var mockTestGenericMapper = mockStatic(TestGenericMapper.class);
    mockTestGenericMapper
        .when(
            () ->
                TestGenericMapper.parse(
                    Mockito.any(ObjectMapper.class), Mockito.anyString(), Mockito.<Class<?>>any()))
        .thenThrow(ERROR);

    return mockTestGenericMapper;
  }

  public static MockedStatic<TestGenericMapper> mockParseListWithClass() {
    var mockTestGenericMapper = mockStatic(TestGenericMapper.class);
    mockTestGenericMapper
        .when(
            () ->
                TestGenericMapper.parseList(
                    Mockito.any(ObjectMapper.class), Mockito.anyString(), Mockito.<Class<?>>any()))
        .thenThrow(ERROR);

    return mockTestGenericMapper;
  }

  public static MockedStatic<TestGenericMapper> mockParseSetWithClass() {
    var mockTestGenericMapper = mockStatic(TestGenericMapper.class);
    mockTestGenericMapper
        .when(
            () ->
                TestGenericMapper.parseSet(
                    Mockito.any(ObjectMapper.class), Mockito.anyString(), Mockito.<Class<?>>any()))
        .thenThrow(ERROR);

    return mockTestGenericMapper;
  }

  public static MockedStatic<TestGenericMapper> mockParseMapWithClass() {
    var mockTestGenericMapper = mockStatic(TestGenericMapper.class);
    mockTestGenericMapper
        .when(
            () ->
                TestGenericMapper.parseMap(
                    Mockito.any(ObjectMapper.class),
                    Mockito.anyString(),
                    Mockito.<Class<?>>any(),
                    Mockito.<Class<?>>any()))
        .thenThrow(ERROR);

    return mockTestGenericMapper;
  }
}
