package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.map;

import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.match.TestAssert.assertThat;
import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.string.TestArrangeNormalizer.normalizeMap;
import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.utils.StringUtils.EMPTY;
import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.utils.StringUtils.EMPTY_OBJECT;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.head.TestAssertHead;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.map.TestAssert1Map;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.map.TestAssert2Map;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.map.TestAssert3Map;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.map.TestAssertLMap;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.head.TestAssertHeadImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.match.TestAssertReason;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.string.TestArrangeNormalizerException;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.context.TestAAAContext;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.metadata.Since;
import java.util.Map;
import lombok.NonNull;
import org.opentest4j.AssertionFailedError;

@Since("1.4.0")
public final class TestAssertMapImpl<K, V>
    implements TestAssert1Map<K, V>, TestAssert2Map<K, V>, TestAssert3Map<K, V>, TestAssertLMap {

  private final TestAAAContext context;

  @Since("2.0.0")
  public TestAssertMapImpl(@NonNull TestAAAContext context) {
    this.context = context;
  }

  @Override
  public TestAssert2Map<K, V> isNotEmpty() {
    var actual = this.context.getActResult().contentAsString();
    assertThat(this.context.getStep(), actual, not(anyOf(is(EMPTY), is(EMPTY_OBJECT))));
    return this;
  }

  @Override
  public TestAssertLMap isEmpty() {
    var actual = this.context.getActResult().contentAsString();
    assertThat(this.context.getStep(), actual, anyOf(is(EMPTY), is(EMPTY_OBJECT)));
    return this;
  }

  @Override
  public TestAssert3Map<K, V> hasSize(int expectedSize) {
    var actual = (Map<?, ?>) this.context.getAssertResult().actualContent();
    assertThat(this.context.getStep(), actual.size(), is(expectedSize));
    return this;
  }

  @Override
  public TestAssertLMap isEqualTo(@NonNull Map<K, V> expectedMap) {
    @SuppressWarnings("unchecked")
    var actual = (Map<K, V>) this.context.getAssertResult().actualContent();

    try {
      assertThat(this.context.getStep(), normalizeMap(actual), is(normalizeMap(expectedMap)));
    } catch (TestArrangeNormalizerException e) {
      var reason = TestAssertReason.reasonOf(this.context.getStep(), e.getMessage());
      throw new AssertionFailedError(reason);
    }
    return this;
  }

  @Override
  public TestAssertHead headers() {
    return new TestAssertHeadImpl(this.context);
  }
}
