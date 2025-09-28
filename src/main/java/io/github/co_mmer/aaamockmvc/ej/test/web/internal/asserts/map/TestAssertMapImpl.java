package io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.map;

import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.match.TestAssert.assertThat;
import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.string.TestArrangeNormalizer.normalizeMap;
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
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.annotation.Since;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.head.TestAssertHeadImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestAAAContext;
import java.util.Map;
import lombok.NonNull;

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
    assertThat(this.context.getStep(), normalizeMap(actual), is(normalizeMap(expectedMap)));
    return this;
  }

  @Override
  public TestAssertHead headers() {
    return new TestAssertHeadImpl(this.context);
  }
}
