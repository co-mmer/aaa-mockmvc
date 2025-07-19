package io.github.co_mmer.aaamockmvc.ej.test.web.internal.arrange.methods.res.body;

import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.arrange.base.body.TestArrangeBodyUtils.setContent;
import static org.springframework.http.MediaType.APPLICATION_JSON;

import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.exception.TestArrangeException;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.res.body.TestArrange1ResBody;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.res.body.TestArrange2ResBody;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.res.body.TestArrange3ResBody;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.annotation.Since;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.arrange.base.TestArrangeBaseAbstract;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.arrange.base.body.TestArrangeBodyUtils;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.mapper.TestGenericMapper;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestAAAContext;
import java.util.List;
import lombok.NonNull;
import org.springframework.http.MediaType;
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
    TestArrangeBodyUtils.setContent(super.getBody(), raw, type);
  }

  @Override
  public void json(@NonNull String json) {
    TestArrangeBodyUtils.setContent(super.getBody(), json, APPLICATION_JSON);
  }

  @Override
  public <T> void json(@NonNull T content) {
    try {
      var json = TestGenericMapper.toJson(super.getEnvironment().objectMapper(), content);
      setContent(getBody(), json, APPLICATION_JSON);
    } catch (Exception e) {
      throw new TestArrangeException(e);
    }
  }

  @Override
  public TestArrange2ResBody file(@NonNull MockMultipartFile file) {
    TestArrangeBodyUtils.addFile(super.getBody(), file);
    return this;
  }

  @Override
  public TestArrange3ResBody files(@NonNull List<MockMultipartFile> files) {
    TestArrangeBodyUtils.addFiles(super.getBody(), files);
    return this;
  }
}
