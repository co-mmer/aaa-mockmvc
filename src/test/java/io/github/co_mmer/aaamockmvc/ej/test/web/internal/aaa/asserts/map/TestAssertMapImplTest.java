package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.map;

import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_MAP_A1_A2;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.STEP_NAME;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.AAAAssert;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.TestAssertBase;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.head.TestAssertHeadImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.model.AssertValue;
import io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestContext;
import io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObjectSimple;
import java.util.Map;
import java.util.function.Consumer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class TestAssertMapImplTest extends TestAssertBase {

  private static final AssertValue<Map<Integer, TestObjectSimple>, ?> ASSERT_VALUE_A1_A2 =
      AssertValue.expectedMap(TEST_MAP_A1_A2);

  private TestAssertMapImpl<Integer, TestObjectSimple> impl;

  @BeforeEach
  void setUp() {
    var context = TestContext.createContext(STEP_NAME);
    this.useContext(context);
    this.impl = new TestAssertMapImpl<>(context);
  }

  private void assertCall(Runnable actCall, Consumer<AAAAssert<?, ?>> verifyCall) {
    var mockInstance = Mockito.mock(AAAAssert.class);
    var mockClass = Mockito.mockStatic(AAAAssert.class);
    mockClass.when(() -> AAAAssert.expect(any(), any())).thenReturn(mockInstance);

    actCall.run();

    mockClass.verify(
        () -> AAAAssert.expect(getContext().getStep(), getContext().getAssertOperand()),
        Mockito.times(1));

    verifyCall.accept(mockInstance);
    mockClass.close();
  }

  @Test
  void WHEN_isNotEmpty_THEN_notToBeEmpty_is_called() {
    assertCall(() -> impl.isNotEmpty(), mock -> verify(mock, times(1)).notToBeEmpty());
  }

  @Test
  void WHEN_isEmpty_THEN_toBeEmpty_is_called() {
    assertCall(() -> impl.isEmpty(), mock -> verify(mock, times(1)).toBeEmpty());
  }

  @Test
  void WHEN_hasSize_THEN_toHaveSize_is_called() {
    assertCall(
        () -> impl.hasSize(2),
        mock -> verify(mock, times(1)).toHaveSize(AssertValue.expectedSize(2)));
  }

  @Test
  void WHEN_isEqualTo_THEN_toEqual_is_called() {
    assertCall(
        () -> impl.isEqualTo(TEST_MAP_A1_A2),
        mock -> verify(mock, times(1)).toEqual(ASSERT_VALUE_A1_A2));
  }

  @Nested
  class nextStep {

    @Test
    void headers() {
      // Act
      var headers = impl.headers();

      // Assert
      assertThat(headers, instanceOf(TestAssertHeadImpl.class));
    }
  }
}
