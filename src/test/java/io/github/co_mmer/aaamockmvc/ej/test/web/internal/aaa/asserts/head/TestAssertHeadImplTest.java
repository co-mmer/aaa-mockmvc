package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.head;

import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.HEADER_KEY_AUTH;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.HEADER_KEY_CONTENT_TYPE;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.HEADER_VALUE_JSON;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.HEADER_VALUE_TOKEN;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.STEP_NAME;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.mockConstruction;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.TestAssertBase;
import io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestContext;
import java.util.Arrays;
import java.util.function.Consumer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

class TestAssertHeadImplTest extends TestAssertBase {

  private static final String[] EXPECTED_HEADER_VALUES = {HEADER_VALUE_TOKEN, HEADER_VALUE_JSON};

  private TestAssertHeadImpl impl;
  private MockedConstruction<HeaderAssertion> mockedAssertion;

  @BeforeEach
  void setUp() {
    var context = TestContext.createContext(STEP_NAME);
    useContext(context);
    useActResult(HEADER_KEY_AUTH, HEADER_VALUE_TOKEN);
    this.mockedAssertion = mockConstruction(HeaderAssertion.class);
    this.impl = new TestAssertHeadImpl(context);
  }

  @AfterEach
  void tearDown() {
    this.mockedAssertion.close();
  }

  private void assertCall(Runnable actCall, Consumer<HeaderAssertion> verifyCall) {
    actCall.run();

    verifyCall.accept(this.mockedAssertion.constructed().get(0));
  }

  @Test
  void WHEN_containsKey_THEN_containsKey_is_called() {
    assertCall(
        () -> this.impl.containsKey(HEADER_KEY_AUTH),
        mock ->
            verify(mock, times(1))
                .containsKey(argThat(value -> value.value().equals(HEADER_KEY_AUTH))));
  }

  @Test
  void WHEN_doesNotContainKey_THEN_doesNotContainKey_is_called() {
    assertCall(
        () -> this.impl.doesNotContainKey(HEADER_KEY_CONTENT_TYPE),
        mock ->
            verify(mock, times(1))
                .doesNotContainKey(
                    argThat(value -> value.value().equals(HEADER_KEY_CONTENT_TYPE))));
  }

  @Test
  void WHEN_containsEntry_THEN_containsEntry_is_called() {
    assertCall(
        () -> this.impl.containsEntry(HEADER_KEY_AUTH, HEADER_VALUE_TOKEN),
        mock ->
            verify(mock, times(1))
                .containsEntry(
                    argThat(value -> value.value().equals(HEADER_KEY_AUTH)),
                    argThat(value -> value.value().equals(HEADER_VALUE_TOKEN))));
  }

  @Test
  void WHEN_containsEntryExactly_THEN_containsEntryExactly_is_called() {
    assertCall(
        () -> this.impl.containsEntryExactly(HEADER_KEY_AUTH, EXPECTED_HEADER_VALUES),
        mock ->
            verify(mock, times(1))
                .containsEntryExactly(
                    argThat(value -> value.value().equals(HEADER_KEY_AUTH)),
                    argThat(
                        value -> value.values().equals(Arrays.asList(EXPECTED_HEADER_VALUES)))));
  }
}
