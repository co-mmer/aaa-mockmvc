package io.github.co_mmer.aaamockmvc.ej.test.web.internal.scenario;

import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.fluent.FluentMatchers.allowsExactly;
import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.fluent.FluentMatchers.transition;

import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.bool.TestAssert1Boolean;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.bool.TestAssert2Boolean;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.bool.TestAssert3Boolean;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.bytes.TestAssert1Byte;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.bytes.TestAssert2Byte;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.bytes.TestAssertLByte;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.clazz.TestAssert1Class;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.clazz.TestAssert2Class;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.clazz.TestAssert3Class;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.clazz.TestAssert4Class;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.clazz.TestAssertLClass;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.collection.TestAssert1Collection;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.collection.TestAssert2Collection;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.collection.TestAssert3Collection;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.collection.TestAssert4Collection;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.collection.TestAssert5Collection;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.collection.TestAssertLCollection;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.map.TestAssert1Map;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.map.TestAssert2Map;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.map.TestAssert3Map;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.map.TestAssertLMap;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.number.TestAssert1Number;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.number.TestAssert2Number;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.number.TestAssert3Number;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.number.TestAssert4Number;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.number.TestAssert5Number;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.number.TestAssert7Number;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.number.TestAssertLNumber;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.string.TestAssert1String;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.string.TestAssert2String;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.string.TestAssertLString;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.fluent.FluentAssert;
import org.junit.jupiter.api.Test;

class AssertFluentEdgesTest {

  @Test
  void single_transitions_number() {
    FluentAssert.assertThat(
        transition(TestAssert1Number.class, "isNull", TestAssertLNumber.class),
        allowsExactly("headers"));

    FluentAssert.assertThat(
        transition(TestAssert1Number.class, "isNotNull", TestAssert2Number.class),
        allowsExactly(
            "isPositive",
            "isNonPositive",
            "isNegative",
            "isNonNegative",
            "isZero",
            "isNotEqualTo",
            "isEqualTo",
            "isEven",
            "isOdd",
            "headers"));

    FluentAssert.assertThat(
        transition(TestAssert1Number.class, "isPositive", TestAssert3Number.class),
        allowsExactly("isNotEqualTo", "isEqualTo", "isEven", "isOdd", "headers"));

    FluentAssert.assertThat(
        transition(TestAssert1Number.class, "isNonPositive", TestAssert4Number.class),
        allowsExactly("isNegative", "isNotEqualTo", "isEqualTo", "isEven", "isOdd", "headers"));

    FluentAssert.assertThat(
        transition(TestAssert1Number.class, "isNegative", TestAssert3Number.class),
        allowsExactly("isNotEqualTo", "isEqualTo", "isEven", "isOdd", "headers"));

    FluentAssert.assertThat(
        transition(TestAssert1Number.class, "isNonNegative", TestAssert5Number.class),
        allowsExactly("isPositive", "isNotEqualTo", "isEqualTo", "isEven", "isOdd", "headers"));

    FluentAssert.assertThat(
        transition(TestAssert1Number.class, "isZero", TestAssertLNumber.class),
        allowsExactly("headers"));

    FluentAssert.assertThat(
        transition(TestAssert1Number.class, "isNotEqualTo", TestAssertLNumber.class),
        allowsExactly("headers"));

    FluentAssert.assertThat(
        transition(TestAssert1Number.class, "isEqualTo", TestAssertLNumber.class),
        allowsExactly("headers"));

    FluentAssert.assertThat(
        transition(TestAssert1Number.class, "isEven", TestAssert7Number.class),
        allowsExactly(
            "isPositive",
            "isNonPositive",
            "isNegative",
            "isNonNegative",
            "isNotEqualTo",
            "isEqualTo",
            "headers"));

    FluentAssert.assertThat(
        transition(TestAssert1Number.class, "isOdd", TestAssert7Number.class),
        allowsExactly(
            "isPositive",
            "isNonPositive",
            "isNegative",
            "isNonNegative",
            "isNotEqualTo",
            "isEqualTo",
            "headers"));
  }

  @Test
  void single_transitions_boolean() {
    FluentAssert.assertThat(
        transition(TestAssert1Boolean.class, "isNull", TestAssert3Boolean.class),
        allowsExactly("headers"));

    FluentAssert.assertThat(
        transition(TestAssert1Boolean.class, "isNotNull", TestAssert2Boolean.class),
        allowsExactly("isTrue", "isFalse", "headers"));

    FluentAssert.assertThat(
        transition(TestAssert1Boolean.class, "isTrue", TestAssert3Boolean.class),
        allowsExactly("headers"));

    FluentAssert.assertThat(
        transition(TestAssert1Boolean.class, "isFalse", TestAssert3Boolean.class),
        allowsExactly("headers"));

    FluentAssert.assertThat(
        transition(TestAssert1Boolean.class, "isEqualTo", TestAssert3Boolean.class),
        allowsExactly("headers"));
  }

  @Test
  void single_transitions_byte() {
    FluentAssert.assertThat(
        transition(TestAssert1Byte.class, "isNotEmpty", TestAssert2Byte.class),
        allowsExactly("isEqualTo", "headers"));

    FluentAssert.assertThat(
        transition(TestAssert1Byte.class, "isEmpty", TestAssertLByte.class),
        allowsExactly("headers"));

    FluentAssert.assertThat(
        transition(TestAssert1Byte.class, "hasLength", TestAssert2Byte.class),
        allowsExactly("isEqualTo", "headers"));

    FluentAssert.assertThat(
        transition(TestAssert1Byte.class, "isEqualTo", TestAssertLByte.class),
        allowsExactly("headers"));
  }

  @Test
  void single_transitions_class() {
    FluentAssert.assertThat(
        transition(TestAssert1Class.class, "isNotNull", TestAssert2Class.class),
        allowsExactly("isEqualTo", "matchAll", "matchAny", "matchNone", "headers"));

    FluentAssert.assertThat(
        transition(TestAssert1Class.class, "isNull", TestAssertLClass.class),
        allowsExactly("headers"));

    FluentAssert.assertThat(
        transition(TestAssert1Class.class, "isEqualTo", TestAssertLClass.class),
        allowsExactly("headers"));

    FluentAssert.assertThat(
        transition(TestAssert1Class.class, "matchAll", TestAssert3Class.class),
        allowsExactly("matchAny", "matchNone", "headers"));

    FluentAssert.assertThat(
        transition(TestAssert1Class.class, "matchAny", TestAssert4Class.class),
        allowsExactly("matchNone", "headers"));

    FluentAssert.assertThat(
        transition(TestAssert1Class.class, "matchNone", TestAssertLClass.class),
        allowsExactly("headers"));
  }

  @Test
  void single_transitions_collection() {
    FluentAssert.assertThat(
        transition(TestAssert1Collection.class, "isNotEmpty", TestAssert2Collection.class),
        allowsExactly(
            "contains",
            "containsAnyOrder",
            "notContains",
            "isEqualTo",
            "matchAll",
            "matchAny",
            "matchNone",
            "headers"));

    FluentAssert.assertThat(
        transition(TestAssert1Collection.class, "isEmpty", TestAssertLCollection.class),
        allowsExactly("headers"));

    FluentAssert.assertThat(
        transition(TestAssert1Collection.class, "isEqualTo", TestAssertLCollection.class),
        allowsExactly("headers"));

    FluentAssert.assertThat(
        transition(TestAssert1Collection.class, "hasSize", TestAssert2Collection.class),
        allowsExactly(
            "contains",
            "containsAnyOrder",
            "notContains",
            "isEqualTo",
            "matchAll",
            "matchAny",
            "matchNone",
            "headers"));

    FluentAssert.assertThat(
        transition(TestAssert1Collection.class, "contains", TestAssert3Collection.class),
        allowsExactly("matchAll", "matchAny", "matchNone", "headers"));

    FluentAssert.assertThat(
        transition(TestAssert1Collection.class, "containsAnyOrder", TestAssert3Collection.class),
        allowsExactly("matchAll", "matchAny", "matchNone", "headers"));

    FluentAssert.assertThat(
        transition(TestAssert1Collection.class, "notContains", TestAssert3Collection.class),
        allowsExactly("matchAll", "matchAny", "matchNone", "headers"));

    FluentAssert.assertThat(
        transition(TestAssert1Collection.class, "matchAll", TestAssert4Collection.class),
        allowsExactly("matchAny", "matchNone", "headers"));

    FluentAssert.assertThat(
        transition(TestAssert1Collection.class, "matchAny", TestAssert5Collection.class),
        allowsExactly("matchNone", "headers"));

    FluentAssert.assertThat(
        transition(TestAssert1Collection.class, "matchNone", TestAssertLCollection.class),
        allowsExactly("headers"));
  }

  @Test
  void single_transitions_map() {
    FluentAssert.assertThat(
        transition(TestAssert1Map.class, "isNotEmpty", TestAssert2Map.class),
        allowsExactly("isEqualTo", "headers"));

    FluentAssert.assertThat(
        transition(TestAssert1Map.class, "isEmpty", TestAssertLMap.class),
        allowsExactly("headers"));

    FluentAssert.assertThat(
        transition(TestAssert1Map.class, "hasSize", TestAssert3Map.class),
        allowsExactly("isEqualTo", "headers"));

    FluentAssert.assertThat(
        transition(TestAssert1Map.class, "isEqualTo", TestAssertLMap.class),
        allowsExactly("headers"));
  }

  @Test
  void single_transitions_string() {
    FluentAssert.assertThat(
        transition(TestAssert1String.class, "isNotEmpty", TestAssert2String.class),
        allowsExactly("isEqualTo", "headers"));

    FluentAssert.assertThat(
        transition(TestAssert1String.class, "isEmpty", TestAssertLString.class),
        allowsExactly("headers"));

    FluentAssert.assertThat(
        transition(TestAssert1String.class, "hasLength", TestAssert2String.class),
        allowsExactly("isEqualTo", "headers"));

    FluentAssert.assertThat(
        transition(TestAssert1String.class, "isEqualTo", TestAssertLString.class),
        allowsExactly("headers"));
  }
}
