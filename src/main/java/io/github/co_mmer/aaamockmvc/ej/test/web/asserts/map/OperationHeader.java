package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.map;

import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.head.TestAssertHead;

interface OperationHeader {

  /**
   * Switches to HTTP header assertions for the same response snapshot.
   *
   * <p>Use this to continue the assertion chain on headers (e.g. {@code containsKey}, {@code
   * containsEntry}). No additional I/O is performed; the headers captured during
   * {@code actPerform().perform()} are reused.
   *
   * @return the next step in the fluent assertion chain, exposing only arrange-appropriate methods
   * based on the current state.
   * @since 2.0.0
   */
  TestAssertHead headers();
}
