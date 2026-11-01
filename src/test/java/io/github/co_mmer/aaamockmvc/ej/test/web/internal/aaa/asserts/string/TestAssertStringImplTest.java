package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.string;

import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_A1_JSON;
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
import java.util.function.Consumer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

class TestAssertStringImplTest extends TestAssertBase {

  private static final StringValue ASSERT_VALUE_A1 = new StringValue(TEST_A1_JSON);

  private TestAssertStringImpl impl;
  private MockedConstruction<StringAssertion> mockedAssertion;

  @BeforeEach
  void setUp() {
    var context = TestContext.createContext(STEP_NAME);
    useContext(context);
    this.mockedAssertion = mockConstruction(StringAssertion.class);
    this.impl = new TestAssertStringImpl(context, new StringValue(TEST_A1_JSON));
  }

  @AfterEach
  void tearDown() {
    this.mockedAssertion.close();
  }

  private void assertCall(Runnable actCall, Consumer<StringAssertion> verifyCall) {
    actCall.run();

    verifyCall.accept(this.mockedAssertion.constructed().get(0));
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
  void WHEN_hasLength_THEN_hasLength_is_called() {
    assertCall(() -> this.impl.hasLength(1), mock -> verify(mock, times(1)).hasLength(1));
  }

  @Test
  void WHEN_isEqualTo_THEN_isEqualTo_is_called() {
    assertCall(
        () -> this.impl.isEqualTo(TEST_A1_JSON),
        mock ->
            verify(mock, times(1))
                .isEqualTo(argThat(value -> value.value().equals(ASSERT_VALUE_A1.value()))));
  }

  @Test
  void WHEN_headers_THEN_return_TestAssertHeadImpl() {
    // Act
    var headers = this.impl.headers();

    // Assert
    assertThat(headers, instanceOf(TestAssertHeadImpl.class));
  }
}
