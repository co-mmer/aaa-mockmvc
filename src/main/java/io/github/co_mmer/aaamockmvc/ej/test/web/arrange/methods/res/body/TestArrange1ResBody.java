package io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.res.body;

import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.exception.TestArrangeException;
import java.util.List;
import lombok.NonNull;
import org.springframework.http.MediaType;
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
public interface TestArrange1ResBody {

  /**
   * Sets the body to the given raw text with the provided {@link MediaType}.
   *
   * <p>If the media type declares a charset, it is used to encode the string; otherwise UTF-8 is
   * used.
   *
   * @param raw the raw textual payload; must not be {@code null}
   * @param type the content type to apply; must not be {@code null}
   * @since 2.0.0
   */
  void raw(@NonNull String raw, @NonNull MediaType type);

  /**
   * Sets the body to the given JSON string.
   *
   * <p>The string is forwarded as-is (no validation). If no {@code Content-Type} has been set, it
   * defaults to {@code application/json;charset=UTF-8}.
   *
   * @param json the JSON payload; must not be {@code null}
   * @since 2.0.0
   */
  void json(@NonNull String json);

  /**
   * Serializes the given object to JSON using the configured mapper and sets it as the body.
   *
   * <p>If no {@code Content-Type} has been set, it defaults to {@code
   * application/json;charset=UTF-8}.
   *
   * @param content the POJO to serialize; must not be {@code null}
   * @param <T> type of the content object
   * @throws TestArrangeException if serialization fails
   * @since 2.0.0
   */
  <T> void json(@NonNull T content) throws TestArrangeException;

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
