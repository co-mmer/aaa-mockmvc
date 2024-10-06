package ej.test.aaamockmvc.testdata;

import static org.mockito.ArgumentMatchers.any;

import ej.test.aaamockmvc.request.act.strategy.TestRequestBaseStrategy;
import ej.test.aaamockmvc.request.act.strategy.TestRequestStrategyFactory;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MockTestRequestStrategyFactory {

  public static MockedStatic<TestRequestStrategyFactory> mockTestRequestStrategyFactory() {
    var mockTestRequestStrategyFactory = Mockito.mockStatic(TestRequestStrategyFactory.class);
    var mockTestRequestBaseStrategy = Mockito.mock(TestRequestBaseStrategy.class);
    mockTestRequestStrategyFactory
        .when(() -> TestRequestStrategyFactory.resolve(any()))
        .thenReturn(mockTestRequestBaseStrategy);
    return mockTestRequestStrategyFactory;
  }

  public static MockedStatic<TestRequestStrategyFactory> mockTestRequestStrategyFactory(
      TestRequestBaseStrategy strategy) {

    var mockTestRequestStrategyFactory = Mockito.mockStatic(TestRequestStrategyFactory.class);
    mockTestRequestStrategyFactory
        .when(() -> TestRequestStrategyFactory.resolve(any()))
        .thenReturn(strategy);
    return mockTestRequestStrategyFactory;
  }
}
