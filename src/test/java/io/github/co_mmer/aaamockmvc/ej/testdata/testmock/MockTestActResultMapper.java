package io.github.co_mmer.aaamockmvc.ej.testdata.testmock;

import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_A1_JSON;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.act.mapper.TestActResultMapper;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestActResult;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MockTestActResultMapper {

  public static MockedStatic<TestActResultMapper> mockWithResult() {
    var mockResult = mockTestActResult();
    var mockTestActResultMapper = Mockito.mockStatic(TestActResultMapper.class);
    mockTestActResultMapper.when(() -> TestActResultMapper.mapTo(any())).thenReturn(mockResult);
    return mockTestActResultMapper;
  }

  private static TestActResult mockTestActResult() {
    var mockResult = Mockito.mock(TestActResult.class);
    when(mockResult.status()).thenReturn(200);
    when(mockResult.contentAsString()).thenReturn(TEST_A1_JSON);
    return mockResult;
  }
}
