package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.clazz;

import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.A;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.A1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.STEP_NAME;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.mockConstruction;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.TestAssertBase;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.head.TestAssertHeadImpl;
import io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestContext;
import io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObjectSimple;
import java.util.function.Consumer;
import java.util.function.Predicate;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

class TestAssertClassImplTest extends TestAssertBase {

  private static final Predicate<TestObjectSimple> PREDICATE_A =
      element -> element.name().equals(A);

  private static final ObjectValue<TestObjectSimple> ASSERT_VALUE_A1 = ObjectValue.expected(A1);

  private TestAssertClassImpl<TestObjectSimple> impl;
  private MockedConstruction<?> mockedAssertion;

  @BeforeEach
  void setUp() {
    var context = TestContext.createContext(STEP_NAME);
    this.useContext(context);
    this.mockedAssertion = mockConstruction(ObjectAssertion.class);
    this.impl = new TestAssertClassImpl<>(context, new ObjectValue<>(A1));
  }

  @AfterEach
  void tearDown() {
    this.mockedAssertion.close();
  }

  @SuppressWarnings("unchecked")
  private void assertCall(
      Runnable actCall, Consumer<ObjectAssertion<TestObjectSimple>> verifyCall) {
    actCall.run();

    verifyCall.accept(
        (ObjectAssertion<TestObjectSimple>) this.mockedAssertion.constructed().get(0));
  }

  @Test
  void WHEN_isNotNull_THEN_isNotNull_is_called() {
    assertCall(() -> this.impl.isNotNull(), mock -> verify(mock, times(1)).isNotNull());
  }

  @Test
  void WHEN_isNull_THEN_isNull_is_called() {
    assertCall(() -> this.impl.isNull(), mock -> verify(mock, times(1)).isNull());
  }

  @Test
  void WHEN_isEqualTo_THEN_isEqualTo_is_called() {
    assertCall(
        () -> this.impl.isEqualTo(A1),
        mock ->
            verify(mock, times(1))
                .isEqualTo(argThat(value -> value.value().equals(ASSERT_VALUE_A1.value()))));
  }

  @Test
  void WHEN_matchAll_THEN_matchAll_is_called() {
    assertCall(
        () -> this.impl.matchAll(PREDICATE_A), mock -> verify(mock, times(1)).matchAll(any()));
  }

  @Test
  void WHEN_matchAll_2_THEN_matchAll_is_called() {
    assertCall(
        () -> this.impl.matchAll(PREDICATE_A, PREDICATE_A),
        mock -> verify(mock, times(1)).matchAll(any()));
  }

  @Test
  void WHEN_matchAll_3_THEN_matchAll_is_called() {
    assertCall(
        () -> this.impl.matchAll(PREDICATE_A, PREDICATE_A, PREDICATE_A),
        mock -> verify(mock, times(1)).matchAll(any()));
  }

  @Test
  void WHEN_matchAll_varargs_THEN_matchAll_is_called() {
    assertCall(
        () -> this.impl.matchAll(PREDICATE_A, PREDICATE_A, PREDICATE_A, PREDICATE_A),
        mock -> verify(mock, times(1)).matchAll(any()));
  }

  @Test
  void WHEN_matchAny_THEN_matchAny_is_called() {
    assertCall(
        () -> this.impl.matchAny(PREDICATE_A), mock -> verify(mock, times(1)).matchAny(any()));
  }

  @Test
  void WHEN_matchAny_2_THEN_matchAny_is_called() {
    assertCall(
        () -> this.impl.matchAny(PREDICATE_A, PREDICATE_A),
        mock -> verify(mock, times(1)).matchAny(any()));
  }

  @Test
  void WHEN_matchAny_3_THEN_matchAny_is_called() {
    assertCall(
        () -> this.impl.matchAny(PREDICATE_A, PREDICATE_A, PREDICATE_A),
        mock -> verify(mock, times(1)).matchAny(any()));
  }

  @Test
  void WHEN_matchAny_varargs_THEN_matchAny_is_called() {
    assertCall(
        () -> this.impl.matchAny(PREDICATE_A, PREDICATE_A, PREDICATE_A, PREDICATE_A),
        mock -> verify(mock, times(1)).matchAny(any()));
  }

  @Test
  void WHEN_matchNone_THEN_matchNone_is_called() {
    assertCall(
        () -> this.impl.matchNone(PREDICATE_A), mock -> verify(mock, times(1)).matchNone(any()));
  }

  @Test
  void WHEN_matchNone_2_THEN_matchNone_is_called() {
    assertCall(
        () -> this.impl.matchNone(PREDICATE_A, PREDICATE_A),
        mock -> verify(mock, times(1)).matchNone(any()));
  }

  @Test
  void WHEN_matchNone_3_THEN_matchNone_is_called() {
    assertCall(
        () -> this.impl.matchNone(PREDICATE_A, PREDICATE_A, PREDICATE_A),
        mock -> verify(mock, times(1)).matchNone(any()));
  }

  @Test
  void WHEN_matchNone_varargs_THEN_matchNone_is_called() {
    assertCall(
        () -> this.impl.matchNone(PREDICATE_A, PREDICATE_A, PREDICATE_A, PREDICATE_A),
        mock -> verify(mock, times(1)).matchNone(any()));
  }

  @Test
  void WHEN_headers_THEN_return_TestAssertHeadImpl() {
    // Act
    var headers = this.impl.headers();

    // Assert
    assertThat(headers, instanceOf(TestAssertHeadImpl.class));
  }
}
