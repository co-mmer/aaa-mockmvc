package io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.res.body;

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
public interface TestArrange3ResBody extends TestOperationFiles {}
