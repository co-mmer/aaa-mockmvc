package ej.test.aaamockmvc;

import static ej.test.aaamockmvc.testdata.testutil.TestDataMockMvc.MOCK_MVC;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import ej.test.aaamockmvc.request.TestRequestDelete;
import ej.test.aaamockmvc.request.TestRequestGet;
import ej.test.aaamockmvc.request.TestRequestHead;
import ej.test.aaamockmvc.request.TestRequestOption;
import ej.test.aaamockmvc.request.TestRequestPatch;
import ej.test.aaamockmvc.request.TestRequestPost;
import ej.test.aaamockmvc.request.TestRequestPut;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.context.WebApplicationContext;

class AAAMockMvcTest {

  private AAAMockMvc aaaMockMvc;
  private ConfigurableApplicationContext context;

  @BeforeEach
  public void setUp() {
    this.context = SpringApplication.run(Application.class);
    var webApplicationContext = (WebApplicationContext) context;
    this.aaaMockMvc = new AAAMockMvc(webApplicationContext);
  }

  @AfterEach
  public void clean() {
    this.context.close();
  }

  @Test
  void WHEN_get_THEN_return_expected_class() {
    // Act
    var get = this.aaaMockMvc.get();

    // Assert
    assertThat(get.getClass(), is(TestRequestGet.class));
  }

  @Test
  void WHEN_post_THEN_return_expected_class() {
    // Act
    var post = this.aaaMockMvc.post();

    // Assert
    assertThat(post.getClass(), is(TestRequestPost.class));
  }

  @Test
  void WHEN_put_THEN_return_expected_class() {
    // Act
    var put = this.aaaMockMvc.put();

    // Assert
    assertThat(put.getClass(), is(TestRequestPut.class));
  }

  @Test
  void WHEN_patch_THEN_return_expected_class() {
    // Act
    var patch = this.aaaMockMvc.patch();

    // Assert
    assertThat(patch.getClass(), is(TestRequestPatch.class));
  }

  @Test
  void WHEN_delete_THEN_return_expected_class() {
    // Act
    var delete = this.aaaMockMvc.delete();

    // Assert
    assertThat(delete.getClass(), is(TestRequestDelete.class));
  }

  @Test
  void WHEN_option_THEN_return_expected_class() {
    // Act
    var option = this.aaaMockMvc.options();

    // Assert
    assertThat(option.getClass(), is(TestRequestOption.class));
  }

  @Test
  void WHEN_head_THEN_return_expected_class() {
    // Act
    var head = this.aaaMockMvc.head();

    // Assert
    assertThat(head.getClass(), is(TestRequestHead.class));
  }

  @Test
  void WHEN_call_constructor_mockMvc_THEN_return_not_null() {
    // Act
    var aaaMockMvc = new AAAMockMvc(MOCK_MVC);

    // Assert
    assertThat(aaaMockMvc, is(notNullValue()));
  }
}
