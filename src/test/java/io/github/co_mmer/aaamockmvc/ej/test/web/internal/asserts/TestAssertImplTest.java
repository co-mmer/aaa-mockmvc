package io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.TestAssertImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.content.TestAssertContentImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.head.TestAssertHeadImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.status.TestAssertStatusImpl;
import io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestAssertImplTest extends TestAssertBase {

  private TestAssertImpl testAssert;

  @BeforeEach
  void setUp() {
    var context = TestContext.createContext();
    this.useContext(context);
    this.testAssert = new TestAssertImpl(context);
  }

  @Test
  @SuppressWarnings("ConstantConditions")
  void GIVEN_null_WHEN_call_constructor_THEN_throw_Exception() {
    assertThrows(NullPointerException.class, () -> new TestAssertImpl(null));
  }

  @Test
  void WHEN_status_THEN_return_expected_class() {
    // Act
    var status = this.testAssert.status();

    // Assert
    assertThat(status.getClass(), is(TestAssertStatusImpl.class));
  }

  @Test
  void WHEN_content_THEN_return_expected_class() {
    // Act
    var content = this.testAssert.content();

    // Assert
    assertThat(content.getClass(), is(TestAssertContentImpl.class));
  }

  @Test
  void WHEN_headers_THEN_return_expected_class() {
    // Act
    var headers = this.testAssert.headers();

    // Assert
    assertThat(headers.getClass(), is(TestAssertHeadImpl.class));
  }
}
