package io.github.co_mmer.aaamockmvc;

import static org.hamcrest.Matchers.is;

import io.github.co_mmer.aaamockmvc.test.AAAMockMvc;
import io.github.co_mmer.aaamockmvc.test.testdata.AAAMockMvcAbstractWrapper;
import io.github.co_mmer.aaamockmvc.test.web.request.TestRequestDelete;
import io.github.co_mmer.aaamockmvc.test.web.request.TestRequestGet;
import io.github.co_mmer.aaamockmvc.test.web.request.TestRequestHead;
import io.github.co_mmer.aaamockmvc.test.web.request.TestRequestOption;
import io.github.co_mmer.aaamockmvc.test.web.request.TestRequestPatch;
import io.github.co_mmer.aaamockmvc.test.web.request.TestRequestPost;
import io.github.co_mmer.aaamockmvc.test.web.request.TestRequestPut;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.context.WebApplicationContext;

class AAAMockMvcAbstractTest {

  private AAAMockMvcAbstractWrapper wrapper;
  private ConfigurableApplicationContext context;

  @BeforeEach
  public void setUp() {
    this.context = SpringApplication.run(Application.class);
    var webApplicationContext = (WebApplicationContext) context;
    var aaaMockMvc = new AAAMockMvc(webApplicationContext);
    this.wrapper = new AAAMockMvcAbstractWrapper(aaaMockMvc);
  }

  @AfterEach
  public void clean() {
    this.context.close();
  }

  @Test
  void WHEN_get_THEN_return_expected_class() {
    // Act
    var get = this.wrapper.wrapGet();

    // Assert
    MatcherAssert.assertThat(get.getClass(), is(TestRequestGet.class));
  }

  @Test
  void WHEN_post_THEN_return_expected_class() {
    // Act
    var post = this.wrapper.wrapPost();

    // Assert
    MatcherAssert.assertThat(post.getClass(), is(TestRequestPost.class));
  }

  @Test
  void WHEN_put_THEN_return_expected_class() {
    // Act
    var put = this.wrapper.wrapPut();

    // Assert
    MatcherAssert.assertThat(put.getClass(), is(TestRequestPut.class));
  }

  @Test
  void WHEN_patch_THEN_return_expected_class() {
    // Act
    var patch = this.wrapper.wrapPatch();

    // Assert
    MatcherAssert.assertThat(patch.getClass(), is(TestRequestPatch.class));
  }

  @Test
  void WHEN_delete_THEN_return_expected_class() {
    // Act
    var delete = this.wrapper.wrapDelete();

    // Assert
    MatcherAssert.assertThat(delete.getClass(), is(TestRequestDelete.class));
  }

  @Test
  void WHEN_options_THEN_return_expected_class() {
    // Act
    var options = this.wrapper.wrapOptions();

    // Assert
    MatcherAssert.assertThat(options.getClass(), is(TestRequestOption.class));
  }

  @Test
  void WHEN_head_THEN_return_expected_class() {
    // Act
    var head = this.wrapper.wrapHead();

    // Assert
    MatcherAssert.assertThat(head.getClass(), is(TestRequestHead.class));
  }
}
