package io.github.co_mmer.aaamockmvc.ej.testdata.testmock;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestJsonProcessingExceptionWrapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MockObjectMapper {

  public static ObjectMapper throwOnWriteValueAsString() throws Exception {
    var mockObjectMapper = mock(ObjectMapper.class);
    when(mockObjectMapper.writeValueAsString(any()))
        .thenThrow(new TestJsonProcessingExceptionWrapper());
    return mockObjectMapper;
  }
}
