package io.github.co_mmer.aaamockmvc.ej.test.web.arrange;

import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.delete.url.TestArrange1DeleteUrl;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.get.url.TestArrange1GetUrl;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.head.url.TestArrange1HeadUrl;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.options.url.TestArrange1OptionsUrl;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.res.url.TestArrange1ResUrl;
import java.net.URI;
import org.springframework.lang.NonNull;

/**
 * Arrange phase entry point for defining the HTTP request.
 *
 * <p><b>What it does:</b> Starts the request specification by choosing the HTTP method and target
 * URL/URI. No network I/O is performed here; this phase only builds the request that will be
 * executed later by {@code actPerform().perform()}.
 *
 * <p><b>Typical usage (AAA):</b>
 *
 * <pre>{@code
 * arrange()
 *   .get("/api/users/{id}", 42)
 *   .query("verbose", "true")
 *   .headers()
 *   .accept(MediaType.APPLICATION_JSON);
 *
 * actPerform()
 *  .perform();
 *
 * asserts()
 *  .status()
 *  .isOk();
 * }</pre>
 *
 * <p><b>Preconditions:</b> This is part of the arrange phase; {@code actPerform().perform()} has
 * not yet been executed.
 *
 * @since 2.0.0
 */
public interface TestArrange {

  /**
   * Begins a GET request using a URL pattern with optional URI template variables.
   *
   * <p>Template variables are applied if the URL contains placeholders (e.g. {@code
   * "/users/{id}"}).
   *
   * @param url the target URL pattern; must not be {@code null}
   * @param variable zero or more URI template variables applied in order
   * @return the next step in the arrange chain, exposing only arrange-appropriate methods based on
   *     the current state.
   * @since 2.0.0
   */
  TestArrange1GetUrl get(@NonNull String url, Object... variable);

  /**
   * Begins a GET request using a concrete {@link URI}.
   *
   * @param uri the target URI; must not be {@code null}
   * @return the next step in the arrange chain, exposing only arrange-appropriate methods based on
   *     the current state.
   * @since 2.0.0
   */
  TestArrange1GetUrl get(@NonNull URI uri);

  /**
   * Begins a DELETE request using a URL pattern with optional URI template variables.
   *
   * @param url the target URL pattern; must not be {@code null}
   * @param variable zero or more URI template variables applied in order
   * @return the next step in the arrange chain, exposing only arrange-appropriate methods based on
   *     the current state.
   * @since 2.0.0
   */
  TestArrange1DeleteUrl delete(@NonNull String url, Object... variable);

  /**
   * Begins a DELETE request using a concrete {@link URI}.
   *
   * @param uri the target URI; must not be {@code null}
   * @return the next step in the arrange chain, exposing only arrange-appropriate methods based on
   *     the current state.
   * @since 2.0.0
   */
  TestArrange1DeleteUrl delete(@NonNull URI uri);

  /**
   * Begins an OPTIONS request using a URL pattern with optional URI template variables.
   *
   * @param url the target URL pattern; must not be {@code null}
   * @param variable zero or more URI template variables applied in order
   * @return the next step in the arrange chain, exposing only arrange-appropriate methods based on
   *     the current state.
   * @since 2.0.0
   */
  TestArrange1OptionsUrl options(@NonNull String url, Object... variable);

  /**
   * Begins an OPTIONS request using a concrete {@link URI}.
   *
   * @param uri the target URI; must not be {@code null}
   * @return the next step in the arrange chain, exposing only arrange-appropriate methods based on
   *     the current state.
   * @since 2.0.0
   */
  TestArrange1OptionsUrl options(@NonNull URI uri);

  /**
   * Begins a HEAD request using a URL pattern with optional URI template variables.
   *
   * @param url the target URL pattern; must not be {@code null}
   * @param variable zero or more URI template variables applied in order
   * @return the next step in the arrange chain, exposing only arrange-appropriate methods based on
   *     the current state.
   * @since 2.0.0
   */
  TestArrange1HeadUrl head(@NonNull String url, Object... variable);

  /**
   * Begins a HEAD request using a concrete {@link URI}.
   *
   * @param uri the target URI; must not be {@code null}
   * @return the next step in the arrange chain, exposing only arrange-appropriate methods based on
   *     the current state.
   * @since 2.0.0
   */
  TestArrange1HeadUrl head(@NonNull URI uri);

  /**
   * Begins a POST request using a URL pattern with optional URI template variables.
   *
   * <p>Subsequent steps allow configuring headers and a request body.
   *
   * @param url the target URL pattern; must not be {@code null}
   * @param variable zero or more URI template variables applied in order
   * @return the next step in the arrange chain, exposing only arrange-appropriate methods based on
   *     the current state.
   * @since 2.0.0
   */
  TestArrange1ResUrl post(@NonNull String url, Object... variable);

  /**
   * Begins a POST request using a concrete {@link URI}.
   *
   * <p>Subsequent steps allow configuring headers and a request body.
   *
   * @param uri the target URI; must not be {@code null}
   * @return the next step in the arrange chain, exposing only arrange-appropriate methods based on
   *     the current state.
   * @since 2.0.0
   */
  TestArrange1ResUrl post(@NonNull URI uri);

  /**
   * Begins a PUT request using a URL pattern with optional URI template variables.
   *
   * <p>Subsequent steps allow configuring headers and a request body.
   *
   * @param url the target URL pattern; must not be {@code null}
   * @param variable zero or more URI template variables applied in order
   * @return the next step in the arrange chain, exposing only arrange-appropriate methods based on
   *     the current state.
   * @since 2.0.0
   */
  TestArrange1ResUrl put(@NonNull String url, Object... variable);

  /**
   * Begins a PUT request using a concrete {@link URI}.
   *
   * <p>Subsequent steps allow configuring headers and a request body.
   *
   * @param uri the target URI; must not be {@code null}
   * @return the next step in the arrange chain, exposing only arrange-appropriate methods based on
   *     the current state.
   * @since 2.0.0
   */
  TestArrange1ResUrl put(@NonNull URI uri);

  /**
   * Begins a PATCH request using a URL pattern with optional URI template variables.
   *
   * <p>Subsequent steps allow configuring headers and a request body.
   *
   * @param url the target URL pattern; must not be {@code null}
   * @param variable zero or more URI template variables applied in order
   * @return the next step in the arrange chain, exposing only arrange-appropriate methods based on
   *     the current state.
   * @since 2.0.0
   */
  TestArrange1ResUrl patch(@NonNull String url, Object... variable);

  /**
   * Begins a PATCH request using a concrete {@link URI}.
   *
   * <p>Subsequent steps allow configuring headers and a request body.
   *
   * @param uri the target URI; must not be {@code null}
   * @return the next step in the arrange chain, exposing only arrange-appropriate methods based on
   *     the current state.
   * @since 2.0.0
   */
  TestArrange1ResUrl patch(@NonNull URI uri);
}
