package io.github.co_mmer.aaamockmvc.ej.testdata.testutil;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestArrangeResult;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestArrangeResultBuilder {

  private HttpMethod method;
  private URI uri;
  private final Map<String, String> query = new HashMap<>();

  private final List<MediaType> accepts = new ArrayList<>();
  private MediaType contentType;
  private final Map<String, List<Object>> headers = new HashMap<>();

  private String bodyContent;
  private MediaType bodyContentType;
  private final List<MockMultipartFile> files = new ArrayList<>();

  public static TestArrangeResultBuilder builder() {
    return new TestArrangeResultBuilder();
  }

  // ==== URL ====
  public TestArrangeResultBuilder withMethod(HttpMethod method) {
    this.method = method;
    return this;
  }

  public TestArrangeResultBuilder withUri(URI uri) {
    this.uri = uri;
    return this;
  }

  public TestArrangeResultBuilder withQueryParam(String key, String value) {
    this.query.put(key, value);
    return this;
  }

  // ==== Header ====
  public TestArrangeResultBuilder withAccept(MediaType mediaType) {
    this.accepts.add(mediaType);
    return this;
  }

  public TestArrangeResultBuilder withAccepts(List<MediaType> mediaTypes) {
    this.accepts.addAll(mediaTypes);
    return this;
  }

  public TestArrangeResultBuilder withContentType(MediaType contentType) {
    this.contentType = contentType;
    return this;
  }

  public TestArrangeResultBuilder withHeader(String key, Object... values) {
    this.headers.put(key, Arrays.asList(values));
    return this;
  }

  // ==== Body ====
  public TestArrangeResultBuilder withBodyContent(String content, MediaType contentType) {
    this.bodyContent = content;
    this.bodyContentType = contentType;
    return this;
  }

  public TestArrangeResultBuilder withFile(MockMultipartFile file) {
    this.files.add(file);
    return this;
  }

  public TestArrangeResultBuilder withFiles(List<MockMultipartFile> files) {
    this.files.addAll(files);
    return this;
  }

  public TestArrangeResult build() {
    var result = new TestArrangeResult();

    // URL
    result.getUrl().setMethod(method);
    result.getUrl().setUri(uri);
    result.getUrl().getQuery().putAll(query);

    // Header
    result.getHead().getAccepts().addAll(accepts);
    result.getHead().setContentType(contentType);
    result.getHead().getKeyValue().putAll(headers);

    // Body
    result.getBody().setContent(bodyContent);
    result.getBody().setContentType(bodyContentType);
    result.getBody().getFiles().addAll(files);

    return result;
  }
}
