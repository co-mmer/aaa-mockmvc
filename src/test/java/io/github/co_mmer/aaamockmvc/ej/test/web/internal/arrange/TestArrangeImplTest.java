package io.github.co_mmer.aaamockmvc.ej.test.web.internal.arrange;

import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.TEST_PATH_VAR1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.TEST_URI_VAR1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.VAR_STRING_1;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.HEAD;
import static org.springframework.http.HttpMethod.OPTIONS;
import static org.springframework.http.HttpMethod.PATCH;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;

import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.TestArrange;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestAAAContext;
import io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class TestArrangeImplTest {

  private TestAAAContext context;
  private TestArrange impl;

  @BeforeEach
  void setUp() {
    this.context = TestContext.mockContext();
    this.impl = new TestArrangeImpl(this.context);
  }

  @Nested
  class Get {

    @Test
    void GIVEN_url_variable_WHEN_get_THEN_context_contain_url_variable() {
      // Act
      impl.get(TEST_PATH_VAR1, VAR_STRING_1);

      // Assert
      assertThat(context.getArrangeResult().getUrl().getMethod(), is(GET));
      assertThat(context.getArrangeResult().getUrl().getUri(), is(TEST_URI_VAR1));
    }

    @Test
    void GIVEN_uri_WHEN_get_THEN_context_contain_url_variable() {
      // Act
      impl.get(TEST_URI_VAR1);

      // Assert
      assertThat(context.getArrangeResult().getUrl().getMethod(), is(GET));
      assertThat(context.getArrangeResult().getUrl().getUri(), is(TEST_URI_VAR1));
    }

    @Test
    @SuppressWarnings("ConstantConditions")
    void GIVEN_null_String_url_WHEN_get_THEN_throwException() {
      // Act && Assert
      assertThrows(NullPointerException.class, () -> impl.get(null, VAR_STRING_1));
    }

    @Test
    @SuppressWarnings("ConstantConditions")
    void GIVEN_null_URI_WHEN_get_THEN_throwException() {
      // Act && Assert
      assertThrows(NullPointerException.class, () -> impl.get(null));
    }
  }

  @Nested
  class Delete {

    @Test
    void GIVEN_url_variable_WHEN_delete_THEN_context_contain_url_variable() {
      // Act
      impl.delete(TEST_PATH_VAR1, VAR_STRING_1);

      // Assert
      assertThat(context.getArrangeResult().getUrl().getMethod(), is(DELETE));
      assertThat(context.getArrangeResult().getUrl().getUri(), is(TEST_URI_VAR1));
    }

    @Test
    void GIVEN_uri_WHEN_delete_THEN_context_contain_url_variable() {
      // Act
      impl.delete(TEST_URI_VAR1);

      // Assert
      assertThat(context.getArrangeResult().getUrl().getMethod(), is(DELETE));
      assertThat(context.getArrangeResult().getUrl().getUri(), is(TEST_URI_VAR1));
    }

    @Test
    @SuppressWarnings("ConstantConditions")
    void GIVEN_null_String_url_WHEN_delete_THEN_throwException() {
      // Act && Assert
      assertThrows(NullPointerException.class, () -> impl.delete(null, VAR_STRING_1));
    }

    @Test
    @SuppressWarnings("ConstantConditions")
    void GIVEN_null_URI_WHEN_delete_THEN_throwException() {
      // Act && Assert
      assertThrows(NullPointerException.class, () -> impl.delete(null));
    }
  }

  @Nested
  class Options {

    @Test
    void GIVEN_url_variable_WHEN_options_THEN_context_contain_url_variable() {
      // Act
      impl.options(TEST_PATH_VAR1, VAR_STRING_1);

      // Assert
      assertThat(context.getArrangeResult().getUrl().getMethod(), is(OPTIONS));
      assertThat(context.getArrangeResult().getUrl().getUri(), is(TEST_URI_VAR1));
    }

    @Test
    void GIVEN_uri_WHEN_options_THEN_context_contain_url_variable() {
      // Act
      impl.options(TEST_URI_VAR1);

      // Assert
      assertThat(context.getArrangeResult().getUrl().getMethod(), is(OPTIONS));
      assertThat(context.getArrangeResult().getUrl().getUri(), is(TEST_URI_VAR1));
    }

    @Test
    @SuppressWarnings("ConstantConditions")
    void GIVEN_null_String_url_WHEN_options_THEN_throwException() {
      // Act && Assert
      assertThrows(NullPointerException.class, () -> impl.options(null, VAR_STRING_1));
    }

    @Test
    @SuppressWarnings("ConstantConditions")
    void GIVEN_null_URI_WHEN_options_THEN_throwException() {
      // Act && Assert
      assertThrows(NullPointerException.class, () -> impl.options(null));
    }
  }

  @Nested
  class Head {

    @Test
    void GIVEN_url_variable_WHEN_head_THEN_context_contain_url_variable() {
      // Act
      impl.head(TEST_PATH_VAR1, VAR_STRING_1);

      // Assert
      assertThat(context.getArrangeResult().getUrl().getMethod(), is(HEAD));
      assertThat(context.getArrangeResult().getUrl().getUri(), is(TEST_URI_VAR1));
    }

    @Test
    void GIVEN_uri_WHEN_head_THEN_context_contain_url_variable() {
      // Act
      impl.head(TEST_URI_VAR1);

      // Assert
      assertThat(context.getArrangeResult().getUrl().getMethod(), is(HEAD));
      assertThat(context.getArrangeResult().getUrl().getUri(), is(TEST_URI_VAR1));
    }

    @Test
    @SuppressWarnings("ConstantConditions")
    void GIVEN_null_String_url_WHEN_head_THEN_throwException() {
      // Act && Assert
      assertThrows(NullPointerException.class, () -> impl.head(null, VAR_STRING_1));
    }

    @Test
    @SuppressWarnings("ConstantConditions")
    void GIVEN_null_URI_WHEN_head_THEN_throwException() {
      // Act && Assert
      assertThrows(NullPointerException.class, () -> impl.head(null));
    }
  }

  @Nested
  class Post {

    @Test
    void GIVEN_url_variable_WHEN_post_THEN_context_contain_url_variable() {
      // Act
      impl.post(TEST_PATH_VAR1, VAR_STRING_1);

      // Assert
      assertThat(context.getArrangeResult().getUrl().getMethod(), is(POST));
      assertThat(context.getArrangeResult().getUrl().getUri(), is(TEST_URI_VAR1));
    }

    @Test
    void GIVEN_uri_WHEN_post_THEN_context_contain_url_variable() {
      // Act
      impl.post(TEST_URI_VAR1);

      // Assert
      assertThat(context.getArrangeResult().getUrl().getMethod(), is(POST));
      assertThat(context.getArrangeResult().getUrl().getUri(), is(TEST_URI_VAR1));
    }

    @Test
    @SuppressWarnings("ConstantConditions")
    void GIVEN_null_String_url_WHEN_post_THEN_throwException() {
      // Act && Assert
      assertThrows(NullPointerException.class, () -> impl.post(null, VAR_STRING_1));
    }

    @Test
    @SuppressWarnings("ConstantConditions")
    void GIVEN_null_URI_WHEN_post_THEN_throwException() {
      // Act && Assert
      assertThrows(NullPointerException.class, () -> impl.post(null));
    }
  }

  @Nested
  class Put {

    @Test
    void GIVEN_url_variable_WHEN_put_THEN_context_contain_url_variable() {
      // Act
      impl.put(TEST_PATH_VAR1, VAR_STRING_1);

      // Assert
      assertThat(context.getArrangeResult().getUrl().getMethod(), is(PUT));
      assertThat(context.getArrangeResult().getUrl().getUri(), is(TEST_URI_VAR1));
    }

    @Test
    void GIVEN_uri_WHEN_put_THEN_context_contain_url_variable() {
      // Act
      impl.put(TEST_URI_VAR1);

      // Assert
      assertThat(context.getArrangeResult().getUrl().getMethod(), is(PUT));
      assertThat(context.getArrangeResult().getUrl().getUri(), is(TEST_URI_VAR1));
    }

    @Test
    @SuppressWarnings("ConstantConditions")
    void GIVEN_null_String_url_WHEN_put_THEN_throwException() {
      // Act && Assert
      assertThrows(NullPointerException.class, () -> impl.put(null, VAR_STRING_1));
    }

    @Test
    @SuppressWarnings("ConstantConditions")
    void GIVEN_null_URI_WHEN_put_THEN_throwException() {
      // Act && Assert
      assertThrows(NullPointerException.class, () -> impl.put(null));
    }
  }

  @Nested
  class Patch {

    @Test
    void GIVEN_url_variable_WHEN_patch_THEN_context_contain_url_variable() {
      // Act
      impl.patch(TEST_PATH_VAR1, VAR_STRING_1);

      // Assert
      assertThat(context.getArrangeResult().getUrl().getMethod(), is(PATCH));
      assertThat(context.getArrangeResult().getUrl().getUri(), is(TEST_URI_VAR1));
    }

    @Test
    void GIVEN_uri_WHEN_patch_THEN_context_contain_url_variable() {
      // Act
      impl.patch(TEST_URI_VAR1);

      // Assert
      assertThat(context.getArrangeResult().getUrl().getMethod(), is(PATCH));
      assertThat(context.getArrangeResult().getUrl().getUri(), is(TEST_URI_VAR1));
    }

    @Test
    @SuppressWarnings("ConstantConditions")
    void GIVEN_null_String_url_WHEN_patch_THEN_throwException() {
      // Act && Assert
      assertThrows(NullPointerException.class, () -> impl.patch(null, VAR_STRING_1));
    }

    @Test
    @SuppressWarnings("ConstantConditions")
    void GIVEN_null_URI_WHEN_patch_THEN_throwException() {
      // Act && Assert
      assertThrows(NullPointerException.class, () -> impl.patch(null));
    }
  }
}
