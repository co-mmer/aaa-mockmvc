package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.string;

import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_A1_JSON;
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

class TestAssertStringImplTest extends TestAssertBase {

  private static final AssertValue<String, String> ASSERT_VALUE_A1 =
      AssertValue.expectedString(TEST_A1_JSON);

  private TestAssertStringImpl impl;

  @BeforeEach
  void setUp() {
    var context = TestContext.createContext(STEP_NAME);
    this.useContext(context);
    this.impl = new TestAssertStringImpl(context);
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
  void WHEN_hasLength_THEN_toHaveLength_is_called() {
    assertCall(
        () -> impl.hasLength(1),
        mock -> verify(mock, times(1)).toHaveLength(AssertValue.expectedLength(1)));
  }

  @Test
  void WHEN_isEqualTo_THEN_toEqual_is_called() {
    assertCall(
        () -> impl.isEqualTo(TEST_A1_JSON),
        mock -> verify(mock, times(1)).toEqual(ASSERT_VALUE_A1));
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
