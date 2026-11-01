package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.collection;

import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.A;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.A1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.A2;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_LIST_A1_A2;
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

class TestAssertCollectionImplTest extends TestAssertBase {

  private static final Predicate<TestObjectSimple> PREDICATE_A =
      element -> element.name().equals(A);

  private static final CollectionValue<TestObjectSimple> VALUE_A1_A2 =
      new CollectionValue<>(TEST_LIST_A1_A2);

  private TestAssertCollectionImpl<TestObjectSimple> impl;
  private MockedConstruction<?> mockedAssertion;

  @BeforeEach
  void setUp() {
    var context = TestContext.createContext(STEP_NAME);
    useContext(context);
    this.mockedAssertion = mockConstruction(CollectionAssertion.class);
    this.impl = new TestAssertCollectionImpl<>(context, new CollectionValue<>(TEST_LIST_A1_A2));
  }

  @AfterEach
  void tearDown() {
    this.mockedAssertion.close();
  }

  @SuppressWarnings("unchecked")
  private void assertCall(
      Runnable actCall, Consumer<CollectionAssertion<TestObjectSimple>> verifyCall) {
    actCall.run();

    verifyCall.accept(
        (CollectionAssertion<TestObjectSimple>) this.mockedAssertion.constructed().get(0));
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
    assertCall(() -> this.impl.hasSize(1), mock -> verify(mock, times(1)).hasSize(1));
  }

  @Test
  void WHEN_isEqualTo_THEN_isEqualTo_is_called() {
    assertCall(
        () -> this.impl.isEqualTo(TEST_LIST_A1_A2),
        mock ->
            verify(mock, times(1))
                .isEqualTo(argThat(value -> value.values().equals(VALUE_A1_A2.values()))));
  }

  @Test
  void WHEN_contains_THEN_contains_is_called() {
    assertCall(
        () -> this.impl.contains(TEST_LIST_A1_A2),
        mock ->
            verify(mock, times(1))
                .contains(argThat(value -> value.values().equals(VALUE_A1_A2.values()))));
  }

  @Test
  void WHEN_contains_varargs_THEN_contains_is_called() {
    assertCall(
        () -> this.impl.contains(A1, A2),
        mock ->
            verify(mock, times(1))
                .contains(argThat(value -> value.values().equals(VALUE_A1_A2.values()))));
  }

  @Test
  void WHEN_containsAnyOrder_THEN_containsAnyOrder_is_called() {
    assertCall(
        () -> this.impl.containsAnyOrder(TEST_LIST_A1_A2),
        mock ->
            verify(mock, times(1))
                .containsAnyOrder(argThat(value -> value.values().equals(VALUE_A1_A2.values()))));
  }

  @Test
  void WHEN_notContains_THEN_notContains_is_called() {
    assertCall(
        () -> this.impl.notContains(TEST_LIST_A1_A2),
        mock ->
            verify(mock, times(1))
                .notContains(argThat(value -> value.values().equals(VALUE_A1_A2.values()))));
  }

  @Test
  void WHEN_notContains_varargs_THEN_notContains_is_called() {
    assertCall(
        () -> this.impl.notContains(A1, A2),
        mock ->
            verify(mock, times(1))
                .notContains(argThat(value -> value.values().equals(VALUE_A1_A2.values()))));
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
