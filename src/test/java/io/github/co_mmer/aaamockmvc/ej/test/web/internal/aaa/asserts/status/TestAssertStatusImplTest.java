package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.status;

import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.STEP_NAME;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.AAAAssert;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.model.AssertOperand;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.model.AssertValue;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.TestAssertBase;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.content.TestAssertContentImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.head.TestAssertHeadImpl;
import io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestContext;
import java.util.function.Consumer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class TestAssertStatusImplTest extends TestAssertBase {

  private TestAssertStatusImpl impl;

  @BeforeEach
  void setUp() {
    var context = TestContext.createContext(STEP_NAME);
    this.useContext(context);
    this.useActResult(OK);
    this.impl = new TestAssertStatusImpl(context);
  }

  private void assertCall(Runnable actCall, Consumer<AAAAssert<?, ?>> verifyCall) {
    var mockInstance = Mockito.mock(AAAAssert.class);
    var mockClass = Mockito.mockStatic(AAAAssert.class);
    mockClass.when(() -> AAAAssert.expect(any(), any())).thenReturn(mockInstance);

    actCall.run();

    mockClass.verify(
        () -> AAAAssert.expect(eq(getContext().getStep()), any(AssertOperand.class)),
        Mockito.times(1));

    verifyCall.accept(mockInstance);
    mockClass.close();
  }

  @Test
  void WHEN_is_HttpStatus_THEN_toHaveStatus_is_called() {
    assertCall(
        () -> impl.is(OK),
        mock -> verify(mock, times(1)).toHaveStatus(AssertValue.expectedStatus(OK)));
  }

  @Test
  void WHEN_is_statusCode_THEN_toHaveStatus_is_called() {
    assertCall(
        () -> impl.is(200),
        mock -> verify(mock, times(1)).toHaveStatus(AssertValue.expectedStatus(200)));
  }

  @Test
  void WHEN_isOk_THEN_toHaveStatus_is_called() {
    assertCall(
        () -> impl.isOk(),
        mock -> verify(mock, times(1)).toHaveStatus(AssertValue.expectedStatus(OK)));
  }

  @Test
  void WHEN_isCreated_THEN_toHaveStatus_is_called() {
    assertCall(
        () -> impl.isCreated(),
        mock -> verify(mock, times(1)).toHaveStatus(AssertValue.expectedStatus(CREATED)));
  }

  @Test
  void WHEN_isAccepted_THEN_toHaveStatus_is_called() {
    assertCall(
        () -> impl.isAccepted(),
        mock -> verify(mock, times(1)).toHaveStatus(AssertValue.expectedStatus(ACCEPTED)));
  }

  @Test
  void WHEN_isNotFound_THEN_toHaveStatus_is_called() {
    assertCall(
        () -> impl.isNotFound(),
        mock -> verify(mock, times(1)).toHaveStatus(AssertValue.expectedStatus(NOT_FOUND)));
  }

  @Test
  void WHEN_isForbidden_THEN_toHaveStatus_is_called() {
    assertCall(
        () -> impl.isForbidden(),
        mock -> verify(mock, times(1)).toHaveStatus(AssertValue.expectedStatus(FORBIDDEN)));
  }

  @Test
  void WHEN_isUnauthorized_THEN_toHaveStatus_is_called() {
    assertCall(
        () -> impl.isUnauthorized(),
        mock -> verify(mock, times(1)).toHaveStatus(AssertValue.expectedStatus(UNAUTHORIZED)));
  }

  @Test
  void WHEN_is2xxSuccessful_THEN_toBeInRange_is_called() {
    assertCall(
        () -> impl.is2xxSuccessful(),
        mock ->
            verify(mock, times(1))
                .toBeInRange(AssertValue.expectedStatus(200), AssertValue.expectedStatus(299)));
  }

  @Test
  void WHEN_is3xxRedirect_THEN_toBeInRange_is_called() {
    assertCall(
        () -> impl.is3xxRedirect(),
        mock ->
            verify(mock, times(1))
                .toBeInRange(AssertValue.expectedStatus(300), AssertValue.expectedStatus(399)));
  }

  @Test
  void WHEN_is4xxClientError_THEN_toBeInRange_is_called() {
    assertCall(
        () -> impl.is4xxClientError(),
        mock ->
            verify(mock, times(1))
                .toBeInRange(AssertValue.expectedStatus(400), AssertValue.expectedStatus(499)));
  }

  @Test
  void WHEN_is5xxServerError_THEN_toBeInRange_is_called() {
    assertCall(
        () -> impl.is5xxServerError(),
        mock ->
            verify(mock, times(1))
                .toBeInRange(AssertValue.expectedStatus(500), AssertValue.expectedStatus(599)));
  }

  @Test
  void WHEN_isInRange_THEN_toBeInRange_is_called() {
    assertCall(
        () -> impl.isInRange(100, 200),
        mock ->
            verify(mock, times(1))
                .toBeInRange(AssertValue.expectedStatus(100), AssertValue.expectedStatus(200)));
  }

  @Nested
  class nextSteps {

    @Test
    void content() {
      // Act
      var content = impl.content();

      // Assert
      assertThat(content, instanceOf(TestAssertContentImpl.class));
    }

    @Test
    void headers() {
      // Act
      var headers = impl.headers();

      // Assert
      assertThat(headers, instanceOf(TestAssertHeadImpl.class));
    }
  }
}
