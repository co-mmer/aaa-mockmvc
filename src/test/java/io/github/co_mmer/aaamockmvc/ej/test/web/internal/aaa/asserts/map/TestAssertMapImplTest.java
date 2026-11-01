package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.map;

import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_MAP_A1_A2;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.STEP_NAME;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.mockConstruction;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.TestAssertBase;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.head.TestAssertHeadImpl;
import io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestContext;
import io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObjectSimple;
import java.util.function.Consumer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

class TestAssertMapImplTest extends TestAssertBase {

  private static final MapValue<Integer, TestObjectSimple> ASSERT_VALUE_A1_A2 =
      new MapValue<>(TEST_MAP_A1_A2);

  private TestAssertMapImpl<Integer, TestObjectSimple> impl;
  private MockedConstruction<?> mockedAssertion;

  @BeforeEach
  void setUp() {
    var context = TestContext.createContext(STEP_NAME);
    useContext(context);
    this.mockedAssertion = mockConstruction(MapAssertion.class);
    this.impl = new TestAssertMapImpl<>(context, new MapValue<>(TEST_MAP_A1_A2));
  }

  @AfterEach
  void tearDown() {
    this.mockedAssertion.close();
  }

  @SuppressWarnings("unchecked")
  private void assertCall(
      Runnable actCall, Consumer<MapAssertion<Integer, TestObjectSimple>> verifyCall) {
    actCall.run();

    verifyCall.accept(
        (MapAssertion<Integer, TestObjectSimple>) this.mockedAssertion.constructed().get(0));
  }

  @Test
  void WHEN_isNotEmpty_THEN_isNotEmpty_is_called() {
    assertCall(() -> this.impl.isNotEmpty(), mock -> verify(mock, times(1)).isNotEmpty());
  }

  @Test
  void WHEN_isEmpty_THEN_isEmpty_is_called() {
    assertCall(() -> this.impl.isEmpty(), mock -> verify(mock, times(1)).isEmpty());
  }

  @Test
  void WHEN_hasSize_THEN_hasSize_is_called() {
    assertCall(() -> this.impl.hasSize(2), mock -> verify(mock, times(1)).hasSize(2));
  }

  @Test
  void WHEN_isEqualTo_THEN_isEqualTo_is_called() {
    assertCall(
        () -> this.impl.isEqualTo(TEST_MAP_A1_A2),
        mock ->
            verify(mock, times(1))
                .isEqualTo(argThat(value -> value.values().equals(ASSERT_VALUE_A1_A2.values()))));
  }

  @Test
  void WHEN_headers_THEN_return_TestAssertHeadImpl() {
    // Act
    var headers = this.impl.headers();

    // Assert
    assertThat(headers, instanceOf(TestAssertHeadImpl.class));
  }
}
