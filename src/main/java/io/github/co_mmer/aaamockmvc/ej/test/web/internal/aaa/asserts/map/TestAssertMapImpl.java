package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.map;

import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.head.TestAssertHead;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.map.TestAssert1Map;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.map.TestAssert2Map;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.map.TestAssert3Map;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.map.TestAssertLMap;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.head.TestAssertHeadImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.context.TestAAAContext;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.metadata.Since;
import java.util.Map;
import org.springframework.lang.NonNull;

@Since("1.4.0")
public final class TestAssertMapImpl<K, V>
    implements TestAssert1Map<K, V>, TestAssert2Map<K, V>, TestAssert3Map<K, V>, TestAssertLMap {

  private final TestAAAContext context;
  private final MapAssertion<K, V> assertion;

  @Since("2.1.0")
  public TestAssertMapImpl(@NonNull TestAAAContext context, @NonNull MapValue<K, V> actual) {
    this.context = context;
    this.assertion = new MapAssertion<>(actual, context.getStep());
  }

  @Override
  @NonNull
  public TestAssert2Map<K, V> isNotEmpty() {
    this.assertion.isNotEmpty();
    return this;
  }

  @Override
  @NonNull
  public TestAssertLMap isEmpty() {
    this.assertion.isEmpty();
    return this;
  }

  @Override
  @NonNull
  public TestAssert3Map<K, V> hasSize(int expectedSize) {
    this.assertion.hasSize(expectedSize);
    return this;
  }

  @Override
  @NonNull
  public TestAssertLMap isEqualTo(@NonNull Map<K, V> expectedMap) {
    this.assertion.isEqualTo(new MapValue<>(expectedMap));
    return this;
  }

  @Override
  @NonNull
  public TestAssertHead headers() {
    return new TestAssertHeadImpl(this.context);
  }
}
