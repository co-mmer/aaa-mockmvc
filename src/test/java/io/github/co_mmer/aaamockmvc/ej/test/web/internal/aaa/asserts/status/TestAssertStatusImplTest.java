package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.status;

import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.STEP_NAME;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.mockito.Mockito.mockConstruction;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.http.HttpStatus.OK;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.TestAssertBase;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.content.TestAssertContentImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.head.TestAssertHeadImpl;
import io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestContext;
import java.util.function.Consumer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

class TestAssertStatusImplTest extends TestAssertBase {

  private TestAssertStatusImpl impl;
  private MockedConstruction<StatusAssertion> mockedAssertion;

  @BeforeEach
  void setUp() {
    var context = TestContext.createContext(STEP_NAME);
    useContext(context);
    this.mockedAssertion = mockConstruction(StatusAssertion.class);
    this.impl = new TestAssertStatusImpl(context);
  }

  @AfterEach
  void tearDown() {
    this.mockedAssertion.close();
  }

  private void assertCall(Runnable actCall, Consumer<StatusAssertion> verifyCall) {
    actCall.run();

    verifyCall.accept(this.mockedAssertion.constructed().get(0));
  }

  @Test
  void WHEN_is_HttpStatus_THEN_is_is_called() {
    assertCall(() -> this.impl.is(OK), mock -> verify(mock, times(1)).is(StatusValue.of(OK)));
  }

  @Test
  void WHEN_is_statusCode_THEN_is_is_called() {
    assertCall(() -> this.impl.is(200), mock -> verify(mock, times(1)).is(new StatusValue(200)));
  }

  @Test
  void WHEN_isOk_THEN_isOk_is_called() {
    assertCall(() -> this.impl.isOk(), mock -> verify(mock, times(1)).isOk());
  }

  @Test
  void WHEN_isCreated_THEN_isCreated_is_called() {
    assertCall(() -> this.impl.isCreated(), mock -> verify(mock, times(1)).isCreated());
  }

  @Test
  void WHEN_isAccepted_THEN_isAccepted_is_called() {
    assertCall(() -> this.impl.isAccepted(), mock -> verify(mock, times(1)).isAccepted());
  }

  @Test
  void WHEN_isNotFound_THEN_isNotFound_is_called() {
    assertCall(() -> this.impl.isNotFound(), mock -> verify(mock, times(1)).isNotFound());
  }

  @Test
  void WHEN_isForbidden_THEN_isForbidden_is_called() {
    assertCall(() -> this.impl.isForbidden(), mock -> verify(mock, times(1)).isForbidden());
  }

  @Test
  void WHEN_isUnauthorized_THEN_isUnauthorized_is_called() {
    assertCall(() -> this.impl.isUnauthorized(), mock -> verify(mock, times(1)).isUnauthorized());
  }

  @Test
  void WHEN_is2xxSuccessful_THEN_is2xxSuccessful_is_called() {
    assertCall(() -> this.impl.is2xxSuccessful(), mock -> verify(mock, times(1)).is2xxSuccessful());
  }

  @Test
  void WHEN_is3xxRedirect_THEN_is3xxRedirect_is_called() {
    assertCall(() -> this.impl.is3xxRedirect(), mock -> verify(mock, times(1)).is3xxRedirect());
  }

  @Test
  void WHEN_is4xxClientError_THEN_is4xxClientError_is_called() {
    assertCall(
        () -> this.impl.is4xxClientError(), mock -> verify(mock, times(1)).is4xxClientError());
  }

  @Test
  void WHEN_is5xxServerError_THEN_is5xxServerError_is_called() {
    assertCall(
        () -> this.impl.is5xxServerError(), mock -> verify(mock, times(1)).is5xxServerError());
  }

  @Test
  void WHEN_isInRange_THEN_isInRange_is_called() {
    assertCall(
        () -> this.impl.isInRange(100, 200),
        mock -> verify(mock, times(1)).isInRange(new StatusRange(100, 200)));
  }

  @Test
  void WHEN_content_THEN_return_TestAssertContentImpl() {
    // Act
    var content = this.impl.content();

    // Assert
    assertThat(content, instanceOf(TestAssertContentImpl.class));
  }

  @Test
  void WHEN_headers_THEN_return_TestAssertHeadImpl() {
    // Act
    var headers = this.impl.headers();

    // Assert
    assertThat(headers, instanceOf(TestAssertHeadImpl.class));
  }
}
