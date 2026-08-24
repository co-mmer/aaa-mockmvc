package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.map;

import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.head.TestAssertHead;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.map.TestAssert1Map;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.map.TestAssert2Map;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.map.TestAssert3Map;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.map.TestAssertLMap;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.AAAAssert;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.head.TestAssertHeadImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.model.AssertValue;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.context.TestAAAContext;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.metadata.Since;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;

@Since("1.4.0")
@RequiredArgsConstructor
public final class TestAssertMapImpl<K, V>
    implements TestAssert1Map<K, V>, TestAssert2Map<K, V>, TestAssert3Map<K, V>, TestAssertLMap {

  private final TestAAAContext context;

  @Override
  public TestAssert2Map<K, V> isNotEmpty() {
    AAAAssert.expect(this.context.getStep(), this.context.getAssertOperand()).notToBeEmpty();
    return this;
  }

  @Override
  public TestAssertLMap isEmpty() {
    AAAAssert.expect(this.context.getStep(), this.context.getAssertOperand()).toBeEmpty();
    return this;
  }

  @Override
  public TestAssert3Map<K, V> hasSize(int expectedSize) {
    AAAAssert.expect(this.context.getStep(), this.context.getAssertOperand())
        .toHaveSize(AssertValue.expectedSize(expectedSize));

    return this;
  }

  @Override
  public TestAssertLMap isEqualTo(@NonNull Map<K, V> expectedMap) {
    AAAAssert.expect(this.context.getStep(), this.context.getAssertOperand())
        .toEqual(AssertValue.expectedMap(expectedMap));
    return this;
  }

  @Override
  public TestAssertHead headers() {
    return new TestAssertHeadImpl(this.context);
  }
}
