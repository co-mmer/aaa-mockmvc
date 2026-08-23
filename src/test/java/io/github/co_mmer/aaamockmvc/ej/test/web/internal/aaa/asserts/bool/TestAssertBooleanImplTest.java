package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.bool;

import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.STEP_NAME;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.AAAAssert;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.AssertValue;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.TestAssertBase;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.head.TestAssertHeadImpl;
import io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestContext;
import java.util.function.Consumer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class TestAssertBooleanImplTest extends TestAssertBase {

  private static final AssertValue<Boolean, Boolean> ASSERT_VALUE_TRUE =
      AssertValue.expectedBoolean(Boolean.TRUE);

  private static final AssertValue<Boolean, Boolean> ASSERT_VALUE_FALSE =
      AssertValue.expectedBoolean(Boolean.FALSE);

  private TestAssertBooleanImpl impl;

  @BeforeEach
  void setUp() {
    var context = TestContext.createContext(STEP_NAME);
    this.useContext(context);
    this.impl = new TestAssertBooleanImpl(context);
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
  void WHEN_isNull_THEN_toBeAbsent_is_called() {
    assertCall(() -> impl.isNull(), mock -> verify(mock, times(1)).toBeAbsent());
  }

  @Test
  void WHEN_isNotNull_THEN_toBePresent_is_called() {
    assertCall(() -> impl.isNotNull(), mock -> verify(mock, times(1)).toBePresent());
  }

  @Test
  void WHEN_isEqualTo_THEN_toEqual_is_called() {
    assertCall(
        () -> impl.isEqualTo(Boolean.TRUE),
        mock -> verify(mock, times(1)).toEqual(ASSERT_VALUE_TRUE));
  }

  @Test
  void WHEN_isTrue_THEN_toEqual_is_called() {
    assertCall(() -> impl.isTrue(), mock -> verify(mock, times(1)).toEqual(ASSERT_VALUE_TRUE));
  }

  @Test
  void WHEN_isFalse_THEN_toEqual_is_called() {
    assertCall(() -> impl.isFalse(), mock -> verify(mock, times(1)).toEqual(ASSERT_VALUE_FALSE));
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
