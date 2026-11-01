package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.bool;

import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.STEP_NAME;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.mockito.Mockito.mockConstruction;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.TestAssertBase;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.head.TestAssertHeadImpl;
import io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestContext;
import java.util.function.Consumer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

class TestAssertBooleanImplTest extends TestAssertBase {

  private static final BooleanValue VALUE_TRUE = BooleanValue.expected(Boolean.TRUE);

  private TestAssertBooleanImpl impl;
  private MockedConstruction<BooleanAssertion> mockedAssertion;

  @BeforeEach
  void setUp() {
    var context = TestContext.createContext(STEP_NAME);
    this.useContext(context);
    this.mockedAssertion = mockConstruction(BooleanAssertion.class);
    this.impl = new TestAssertBooleanImpl(context, new BooleanValue(Boolean.TRUE));
  }

  @AfterEach
  void tearDown() {
    this.mockedAssertion.close();
  }

  private void assertCall(Runnable actCall, Consumer<BooleanAssertion> verifyCall) {
    actCall.run();

    verifyCall.accept(this.mockedAssertion.constructed().get(0));
  }

  @Test
  void WHEN_isNull_THEN_isNull_is_called() {
    assertCall(() -> this.impl.isNull(), mock -> verify(mock, times(1)).isNull());
  }

  @Test
  void WHEN_isNotNull_THEN_isNotNull_is_called() {
    assertCall(() -> this.impl.isNotNull(), mock -> verify(mock, times(1)).isNotNull());
  }

  @Test
  void WHEN_isEqualTo_THEN_isEqualTo_is_called() {
    assertCall(
        () -> this.impl.isEqualTo(Boolean.TRUE),
        mock -> verify(mock, times(1)).isEqualTo(VALUE_TRUE));
  }

  @Test
  void WHEN_isTrue_THEN_isTrue_is_called() {
    assertCall(() -> this.impl.isTrue(), mock -> verify(mock, times(1)).isTrue());
  }

  @Test
  void WHEN_isFalse_THEN_isFalse_is_called() {
    assertCall(() -> this.impl.isFalse(), mock -> verify(mock, times(1)).isFalse());
  }

  @Test
  void WHEN_headers_THEN_return_TestAssertHeadImpl() {
    // Act
    var headers = this.impl.headers();

    // Assert
    assertThat(headers, instanceOf(TestAssertHeadImpl.class));
  }
}
