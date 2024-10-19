package ej.aaamockmvc;

import static ej.aaamockmvc.test.testdata.testutil.TestDataMockMvc.MOCK_MVC;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import com.fasterxml.jackson.databind.ObjectMapper;
import ej.aaamockmvc.test.AAAMockMvc;
import ej.aaamockmvc.test.web.request.TestRequestDelete;
import ej.aaamockmvc.test.web.request.TestRequestGet;
import ej.aaamockmvc.test.web.request.TestRequestHead;
import ej.aaamockmvc.test.web.request.TestRequestOption;
import ej.aaamockmvc.test.web.request.TestRequestPatch;
import ej.aaamockmvc.test.web.request.TestRequestPost;
import ej.aaamockmvc.test.web.request.TestRequestPut;
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
  void WHEN_call_constructor_context_THEN_return_not_null() {
    // Act
    var aaaMockMvc = new AAAMockMvc((WebApplicationContext) this.context);

    // Assert
    assertThat(aaaMockMvc, is(notNullValue()));
  }

  @Test
  void WHEN_call_constructor_context_ObjectMapper_THEN_return_not_null() {
    // Act
    var aaaMockMvc = new AAAMockMvc((WebApplicationContext) this.context, new ObjectMapper());

    // Assert
    assertThat(aaaMockMvc, is(notNullValue()));
  }

  @Test
  void WHEN_call_constructor_mockMvc_THEN_return_not_null() {
    // Act
    var aaaMockMvc = new AAAMockMvc(MOCK_MVC);

    // Assert
    assertThat(aaaMockMvc, is(notNullValue()));
  }

  @Test
  void WHEN_call_constructor_mockMvc_ObjectMapper_THEN_return_not_null() {
    // Act
    var aaaMockMvc = new AAAMockMvc(MOCK_MVC, new ObjectMapper());

    // Assert
    assertThat(aaaMockMvc, is(notNullValue()));
  }
}
