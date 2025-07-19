package io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.res.url;

import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.res.body.TestArrange1ResBody;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.res.header.TestArrange1ResHead;

/**
 * POST/PUT/PATCH arrange step for defining the request URL and query parameters.
 *
 * <p><b>What it does:</b> Adds query parameters to the configured POST/PUT/PATCH URL and
 * transitions to further arrange steps (e.g., headers, body). No network I/O is performed; this
 * only mutates the request specification for the subsequent {@code actPerform().perform()}.
 *
 * <p><b>Typical usage (AAA):</b>
 *
 * <pre>{@code
 * arrange()
 *   .post("/api/items") //.put("/api/items") .patch("/api/items")
 *   .query("page", "1")
 *   .headers()
 *   .add("X-Trace-Id", "1234")
 *   .headers() // .body()
 *   ...
 * }</pre>
 *
 * <p><b>Preconditions:</b> This is part of the arrange phase; {@code actPerform().perform()} has
 * not yet been executed.
 *
 * @since 1.0.0
 */
public interface TestArrange3ResUrl {

  /**
   * Switches to the headers arrange step for the same request specification.
   *
   * @return the next step in the arrange chain, exposing only arrange-appropriate methods based on
   *     the current state.
   * @since 2.0.0
   */
  TestArrange1ResHead headers();

  /**
   * Switches to the body arrange step for the same request specification.
   *
   * @return the next step in the arrange chain, exposing only arrange-appropriate methods based on
   *     the current state.
   * @since 2.0.0
   */
  TestArrange1ResBody body();
}
