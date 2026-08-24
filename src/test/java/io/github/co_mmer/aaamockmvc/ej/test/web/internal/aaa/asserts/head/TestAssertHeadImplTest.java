package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.head;

import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.HEADER_KEY_AUTH;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.HEADER_KEY_CONTENT_TYPE;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.HEADER_VALUE_JSON;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.HEADER_VALUE_TOKEN;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.STEP_NAME;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.AAAAssert;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.TestAssertBase;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.model.AssertOperand;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.model.AssertValue;
import io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestContext;
import java.util.function.Consumer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestAssertHeadImplTest extends TestAssertBase {

  private static final String[] EXPECTED_HEADER_VALUES = {HEADER_VALUE_TOKEN, HEADER_VALUE_JSON};

  private TestAssertHeadImpl impl;

  @BeforeEach
  void setUp() {
    var context = TestContext.createContext(STEP_NAME);
    this.useContext(context);
    this.useActResult(HEADER_KEY_AUTH, HEADER_VALUE_TOKEN);
    this.impl = new TestAssertHeadImpl(context);
  }

  @SuppressWarnings("all")
  private void assertCall(Runnable actCall, Consumer<AAAAssert<?, ?>> verifyCall) {
    var mockInstance = mock(AAAAssert.class);
    var mockClass = mockStatic(AAAAssert.class);
    mockClass.when(() -> AAAAssert.expect(any(), any())).thenReturn(mockInstance);

    actCall.run();

    mockClass.verify(
        () -> AAAAssert.expect(eq(getContext().getStep()), any(AssertOperand.class)), times(1));

    verifyCall.accept(mockInstance);
    mockClass.close();
  }

  @Test
  void WHEN_containsKey_THEN_toContainKey_is_called() {
    assertCall(
        () -> impl.containsKey(HEADER_KEY_AUTH),
        mock ->
            verify(mock, times(1)).toContainKey(AssertValue.expectedHeaderName(HEADER_KEY_AUTH)));
  }

  @Test
  void WHEN_doesNotContainKey_THEN_notToContainKey_is_called() {
    assertCall(
        () -> impl.doesNotContainKey(HEADER_KEY_CONTENT_TYPE),
        mock ->
            verify(mock, times(1))
                .notToContainKey(AssertValue.unexpectedHeaderName(HEADER_KEY_CONTENT_TYPE)));
  }

  @Test
  void WHEN_containsEntry_THEN_toContainEntry_is_called() {
    assertCall(
        () -> impl.containsEntry(HEADER_KEY_AUTH, HEADER_VALUE_TOKEN),
        mock ->
            verify(mock, times(1))
                .toContainEntry(
                    AssertValue.expectedHeaderName(HEADER_KEY_AUTH),
                    AssertValue.expectedHeaderValue(HEADER_VALUE_TOKEN)));
  }

  @Test
  void WHEN_containsEntryExactly_THEN_toContainEntryExactly_is_called() {
    assertCall(
        () -> impl.containsEntryExactly(HEADER_KEY_AUTH, EXPECTED_HEADER_VALUES),
        mock ->
            verify(mock, times(1))
                .toContainEntryExactly(
                    AssertValue.expectedHeaderName(HEADER_KEY_AUTH),
                    AssertValue.expectedHeaderValues(EXPECTED_HEADER_VALUES)));
  }
}
