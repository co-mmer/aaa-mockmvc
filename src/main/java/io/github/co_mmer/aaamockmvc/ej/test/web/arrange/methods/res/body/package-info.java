/**
 * Arrange-phase for configuring the request body on POST, PUT, and PATCH requests.
 *
 * <p><b>Scope:</b> Provides a fluent API to define the payload of a request as raw text, JSON
 * (string or serialized POJO), or multipart files. This step mutates only the request
 * specification; no network I/O is performed until {@code act().perform()} is invoked.
 *
 * <p><b>General rules (apply to all methods in this package):</b>
 *
 * <ul>
 *   <li>All validations throw {@link java.lang.NullPointerException} for {@code null} arguments.
 *   <li>For {@code raw(...)}: if the {@link org.springframework.http.MediaType} specifies a
 *       charset, it is used to encode the string; otherwise UTF-8 is applied.
 *   <li>For {@code json(...)}: if no {@code Content-Type} header has been set, it defaults to
 *       {@code application/json;charset=UTF-8}.
 *   <li>For {@code json(Object)}: serialization failures result in {@link
 *       io.github.co_mmer.aaamockmvc.ej.test.web.arrange.exception.TestArrangeException}.
 *   <li>For multipart methods: the request is switched to {@code multipart/form-data}; boundaries
 *       and encoding are handled automatically.
 * </ul>
 *
 * <p><b>Typical usage (AAA):</b>
 *
 * <pre>{@code
 * arrange()
 *   .post("/api/items")   // or .put("/api/items") or .patch("/api/items")
 *   .headers()
 *   .contentType(MediaType.APPLICATION_JSON)
 *   .body()
 *   .json(new Item("A"));   // or .json("{\"name\":\"A\"}") / .raw("payload", TEXT_PLAIN)
 *
 * arrange()
 *   .post("/api/upload")
 *   .headers()
 *   .body()
 *   .file(new MockMultipartFile("file", "test.txt",
 *       MediaType.TEXT_PLAIN_VALUE, "Hello".getBytes()));
 * }</pre>
 *
 * <p><b>Entry point:</b> The public API surface is {@link
 * io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.res.body.TestArrange1ResBody}.
 *
 * @since 1.0.0
 */
package io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.res.body;
