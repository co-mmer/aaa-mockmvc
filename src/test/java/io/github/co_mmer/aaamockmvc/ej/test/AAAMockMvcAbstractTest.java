package io.github.co_mmer.aaamockmvc.ej.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import io.github.co_mmer.aaamockmvc.ej.test.web.request.TestRequestDelete;
import io.github.co_mmer.aaamockmvc.ej.test.web.request.TestRequestGet;
import io.github.co_mmer.aaamockmvc.ej.test.web.request.TestRequestHead;
import io.github.co_mmer.aaamockmvc.ej.test.web.request.TestRequestOption;
import io.github.co_mmer.aaamockmvc.ej.test.web.request.TestRequestPatch;
import io.github.co_mmer.aaamockmvc.ej.test.web.request.TestRequestPost;
import io.github.co_mmer.aaamockmvc.ej.test.web.request.TestRequestPut;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest(classes = WebApplicationContext.class)
class AAAMockMvcAbstractTest extends AAAMockMvcAbstract {

  @Test
  void WHEN_get_THEN_return_expected_class() {
    // Act
    var get = get();

    // Assert
    assertThat(get.getClass(), is(TestRequestGet.class));
  }

  @Test
  void WHEN_post_THEN_return_expected_class() {
    // Act
    var post = post();

    // Assert
    assertThat(post.getClass(), is(TestRequestPost.class));
  }

  @Test
  void WHEN_put_THEN_return_expected_class() {
    // Act
    var put = put();

    // Assert
    assertThat(put.getClass(), is(TestRequestPut.class));
  }

  @Test
  void WHEN_patch_THEN_return_expected_class() {
    // Act
    var patch = patch();

    // Assert
    assertThat(patch.getClass(), is(TestRequestPatch.class));
  }

  @Test
  void WHEN_delete_THEN_return_expected_class() {
    // Act
    var delete = delete();

    // Assert
    assertThat(delete.getClass(), is(TestRequestDelete.class));
  }

  @Test
  void WHEN_options_THEN_return_expected_class() {
    // Act
    var options = options();

    // Assert
    assertThat(options.getClass(), is(TestRequestOption.class));
  }

  @Test
  void WHEN_head_THEN_return_expected_class() {
    // Act
    var head = head();

    // Assert
    assertThat(head.getClass(), is(TestRequestHead.class));
  }
}
