package io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.res.body;

import java.util.List;
import lombok.NonNull;
import org.springframework.mock.web.MockMultipartFile;

/**
 * POST/PUT/PATCH arrange step for defining the request body.
 *
 * <p><b>What it does:</b> Configures the payload for the pending request as raw text, JSON (string
 * or serialized POJO), or multipart files. This step only mutates the request specification; no
 * network I/O is performed until {@code actPerform().perform()}.
 *
 * <p><b>Typical usage (AAA):</b>
 *
 * <pre>{@code
 * arrange()
 *   .post("/api/items") //.put("/api/items") .patch("/api/items")
 *   .headers()
 *   .contentType(MediaType.APPLICATION_JSON)
 *   .body()
 *   .json(new Item("A")); // or .json("{\"name\":\"A\"}") / .raw("payload", TEXT_PLAIN)
 *
 * }</pre>
 *
 * <p><b>Preconditions:</b> This is part of the arrange phase; {@code actPerform().perform()} has
 * not yet been executed.
 *
 * @since 1.0.0
 */
public interface TestArrange2ResBody {

  /**
   * Arranges a single file as the body (multipart/form-data).
   *
   * <p>Switches the request to multipart; boundaries and encoding are handled by the underlying
   * client.
   *
   * @param file the file to include; must not be {@code null}
   * @return the next step in the arrange chain, exposing only arrange-appropriate methods based on
   *     the current state.
   * @throws NullPointerException if {@code file} is {@code null}
   * @since 1.0.0
   */
  TestArrange2ResBody file(@NonNull MockMultipartFile file);

  /**
   * Arranges multiple files as the body (multipart/form-data).
   *
   * <p>Switches the request to multipart and adds all provided files as parts.
   *
   * @param files the files to include; must not be {@code null}
   * @return the next step in the arrange chain, exposing only arrange-appropriate methods based on
   *     the current state.
   * @since 2.0.0
   */
  TestArrange3ResBody files(@NonNull List<MockMultipartFile> files);
}
