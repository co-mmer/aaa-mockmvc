package io.github.co_mmer.aaamockmvc.testdata.testutil;

import io.github.co_mmer.aaamockmvc.test.web.request.model.TestRequestDto;
import io.github.co_mmer.aaamockmvc.test.web.request.model.TestRequestType;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

public final class TestRequestDtoBuilder {

  private TestRequestType type;
  private URI uri;
  private Map<String, String> param;
  private Map<String, Object> headerKeyValue;
  private final List<MediaType> headerAccepts;
  private final List<MediaType> headerContentTypes;

  private final List<MockMultipartFile> files;
  private String content;
  private MediaType contentType;

  private TestRequestDtoBuilder() {
    this.param = new HashMap<>();
    this.headerKeyValue = new HashMap<>();
    this.headerAccepts = new ArrayList<>();
    this.headerContentTypes = new ArrayList<>();

    this.files = new ArrayList<>();
  }

  public TestRequestDtoBuilder withRequestType(TestRequestType type) {
    this.type = type;
    return this;
  }

  public TestRequestDtoBuilder withUri(URI uri) {
    this.uri = uri;
    return this;
  }

  public TestRequestDtoBuilder withParam(Map<String, String> value) {
    this.param = value;
    return this;
  }

  public TestRequestDtoBuilder withParamKeyValue(String key, String value) {
    this.param.put(key, value);
    return this;
  }

  public TestRequestDtoBuilder withHeadKeyValue(String key, String value) {
    this.headerKeyValue.put(key, value);
    return this;
  }

  public TestRequestDtoBuilder withHeadKeyValue(Map<String, Object> value) {
    this.headerKeyValue = value;
    return this;
  }

  public TestRequestDtoBuilder withHeadAccept(MediaType type) {
    this.headerAccepts.add(type);
    return this;
  }

  public TestRequestDtoBuilder withHeadContentType(MediaType type) {
    this.headerContentTypes.add(type);
    return this;
  }

  public TestRequestDtoBuilder withFile(MockMultipartFile file) {
    this.files.add(file);
    return this;
  }

  public TestRequestDtoBuilder withContent(String content) {
    this.content = content;
    return this;
  }

  public TestRequestDtoBuilder withContentType(MediaType contentType) {
    this.contentType = contentType;
    return this;
  }

  public TestRequestDto build() {
    var requestDto = new TestRequestDto(this.type);
    requestDto.getUrl().setUri(this.uri);
    requestDto.getUrl().setParam(this.param);
    requestDto.getHead().setKeyValue(this.headerKeyValue);
    requestDto.getHead().setAccepts(this.headerAccepts);
    requestDto.getHead().setContentTypes(this.headerContentTypes);
    requestDto.getBody().setFiles(this.files);
    requestDto.getBody().setContent(this.content);
    requestDto.getBody().setContentType(this.contentType);
    return requestDto;
  }

  public static TestRequestDtoBuilder builder() {
    return new TestRequestDtoBuilder();
  }
}
