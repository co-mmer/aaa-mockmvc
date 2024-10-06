package ej.test.aaamockmvc;

import static org.hamcrest.Matchers.is;

import ej.test.aaamockmvc.request.TestRequestDelete;
import ej.test.aaamockmvc.request.TestRequestGet;
import ej.test.aaamockmvc.request.TestRequestHead;
import ej.test.aaamockmvc.request.TestRequestOption;
import ej.test.aaamockmvc.request.TestRequestPatch;
import ej.test.aaamockmvc.request.TestRequestPost;
import ej.test.aaamockmvc.request.TestRequestPut;
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
    var get = this.wrapper.get();

    // Assert
    MatcherAssert.assertThat(get.getClass(), is(TestRequestGet.class));
  }

  @Test
  void WHEN_post_THEN_return_expected_class() {
    // Act
    var post = this.wrapper.post();

    // Assert
    MatcherAssert.assertThat(post.getClass(), is(TestRequestPost.class));
  }

  @Test
  void WHEN_put_THEN_return_expected_class() {
    // Act
    var put = this.wrapper.put();

    // Assert
    MatcherAssert.assertThat(put.getClass(), is(TestRequestPut.class));
  }

  @Test
  void WHEN_patch_THEN_return_expected_class() {
    // Act
    var patch = this.wrapper.patch();

    // Assert
    MatcherAssert.assertThat(patch.getClass(), is(TestRequestPatch.class));
  }

  @Test
  void WHEN_delete_THEN_return_expected_class() {
    // Act
    var delete = this.wrapper.delete();

    // Assert
    MatcherAssert.assertThat(delete.getClass(), is(TestRequestDelete.class));
  }

  @Test
  void WHEN_options_THEN_return_expected_class() {
    // Act
    var options = this.wrapper.options();

    // Assert
    MatcherAssert.assertThat(options.getClass(), is(TestRequestOption.class));
  }

  @Test
  void WHEN_head_THEN_return_expected_class() {
    // Act
    var head = this.wrapper.head();

    // Assert
    MatcherAssert.assertThat(head.getClass(), is(TestRequestHead.class));
  }

  static class AAAMockMvcAbstractWrapper extends AAAMockMvcAbstract {

    public AAAMockMvcAbstractWrapper(AAAMockMvc aaaMockMvc) {
      super(aaaMockMvc);
    }
  }
}
