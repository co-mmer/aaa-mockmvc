package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.arrange.methods.res.body;

import static org.springframework.http.MediaType.APPLICATION_JSON;

import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.exception.TestArrangeException;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.res.body.TestArrange1ResBody;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.res.body.TestArrange2ResBody;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.res.body.TestArrange3ResBody;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.arrange.base.TestArrangeBaseAbstract;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.context.TestAAAContext;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.mapper.TestGenericMapper;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.metadata.Since;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.body.EmptyBody;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.body.MultipartBody;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.body.TextBody;
import java.io.IOException;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.mock.web.MockMultipartFile;

@Since("1.0.0")
public final class TestArrangeResBodyImpl extends TestArrangeBaseAbstract
    implements TestArrange1ResBody, TestArrange2ResBody, TestArrange3ResBody {

  @Since("2.0.0")
  public TestArrangeResBodyImpl(@NonNull TestAAAContext context) {
    super(context);
  }

  @Override
  public void raw(@NonNull String raw, @NonNull MediaType type) {
    this.context.getArrangeBuilder().body(new TextBody(raw));
    getRequestHeaders().contentType(type);
  }

  @Override
  public void json(@NonNull String json) {
    this.context.getArrangeBuilder().body(new TextBody(json));
    getRequestHeaders().contentType(APPLICATION_JSON);
  }

  @Override
  public <T> void json(@NonNull T content) {
    try {
      var json = TestGenericMapper.toJson(getEnvironment().objectMapper(), content);
      this.context.getArrangeBuilder().body(new TextBody(json));
      getRequestHeaders().contentType(APPLICATION_JSON);
    } catch (Exception e) {
      throw new TestArrangeException(e);
    }
  }

  @Override
  public TestArrange2ResBody file(@NonNull MockMultipartFile file) {
    try {
      var body = multipartBody();
      body.add(file.getName(), file.getOriginalFilename(), file.getContentType(), file.getBytes());
      this.context.getArrangeBuilder().body(body);
      return this;
    } catch (IOException e) {
      throw new TestArrangeException(e);
    }
  }

  // todo testinf
  @Override
  public TestArrange3ResBody files(@NonNull List<MockMultipartFile> files) {
    try {
      var body = multipartBody();
      for (var file : files) {
        body.add(
            file.getName(), file.getOriginalFilename(), file.getContentType(), file.getBytes());
      }
      this.context.getArrangeBuilder().body(body);
      return this;
    } catch (IOException e) {
      throw new TestArrangeException(e);
    }
  }

  // todo testinf
  private MultipartBody multipartBody() {
    var currentBody = getRequestBody();

    if (currentBody instanceof EmptyBody) {
      var body = new MultipartBody();
      this.context.getArrangeBuilder().body(body);
      return body;
    }

    if (currentBody instanceof MultipartBody multipartBody) {
      return multipartBody;
    }

    throw new IllegalStateException("Request body is already set and is not a MultipartBody");
  }
}
