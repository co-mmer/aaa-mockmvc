package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.arrange;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.sameInstance;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.HttpMethod;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.RequestPath;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.body.EmptyBody;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.body.TextBody;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class TestArrangeBuilderTest {

  private static final String ANY_PATH = "/customers/42";
  private static final String ANY_BODY = "request body";

  private TestArrangeBuilder builder;

  @BeforeEach
  void setUp() {
    this.builder = new TestArrangeBuilder();
  }

  @Nested
  class SetMethod {

    @Test
    void GIVEN_method_WHEN_method_THEN_store_method_and_return_builder() {
      // Act
      var result = builder.method(HttpMethod.GET);

      // Assert
      assertThat(result, is(sameInstance(builder)));
      assertThat(builder.build().method(), is(HttpMethod.GET));
    }
  }

  @Nested
  class AccessPath {

    @Test
    void GIVEN_new_builder_WHEN_path_THEN_create_path() {
      // Act
      var result = builder.path();

      // Assert
      assertThat(result, is(instanceOf(RequestPath.class)));
    }

    @Test
    void GIVEN_existing_path_WHEN_path_THEN_return_same_path() {
      // Arrange
      var path = builder.path();

      // Act
      var result = builder.path();

      // Assert
      assertThat(result, is(sameInstance(path)));
    }
  }

  @Nested
  class AccessQuery {

    @Test
    void GIVEN_new_builder_WHEN_query_THEN_return_empty_query() {
      // Act
      var result = builder.query();

      // Assert
      assertThat(result.values().isEmpty(), is(true));
    }

    @Test
    void GIVEN_existing_query_WHEN_query_THEN_return_same_query() {
      // Arrange
      var query = builder.query();

      // Act
      var result = builder.query();

      // Assert
      assertThat(result, is(sameInstance(query)));
    }
  }

  @Nested
  class AccessHeaders {

    @Test
    void GIVEN_new_builder_WHEN_headers_THEN_return_empty_headers() {
      // Act
      var result = builder.headers();

      // Assert
      assertThat(result.values().isEmpty(), is(true));
    }

    @Test
    void GIVEN_existing_headers_WHEN_headers_THEN_return_same_headers() {
      // Arrange
      var headers = builder.headers();

      // Act
      var result = builder.headers();

      // Assert
      assertThat(result, is(sameInstance(headers)));
    }
  }

  @Nested
  class SetBody {

    @Test
    void GIVEN_new_builder_WHEN_body_THEN_return_empty_body() {
      // Act
      var result = builder.body();

      // Assert
      assertThat(result, is(instanceOf(EmptyBody.class)));
    }

    @Test
    void GIVEN_body_WHEN_body_THEN_store_and_return_body() {
      // Arrange
      var body = new TextBody(ANY_BODY);

      // Act
      var result = builder.body(body);

      // Assert
      assertThat(result, is(sameInstance(body)));
      assertThat(builder.body(), is(sameInstance(body)));
    }
  }

  @Nested
  class BuildRequest {

    @Test
    void GIVEN_configured_builder_WHEN_build_THEN_return_request() {
      // Arrange
      var path = builder.path();
      path.setValue(ANY_PATH);

      var query = builder.query();
      var headers = builder.headers();
      var body = new TextBody(ANY_BODY);

      builder.method(HttpMethod.POST);
      builder.body(body);

      // Act
      var result = builder.build();

      // Assert
      assertThat(result.method(), is(HttpMethod.POST));
      assertThat(result.path(), is(sameInstance(path)));
      assertThat(result.query(), is(sameInstance(query)));
      assertThat(result.headers(), is(sameInstance(headers)));
      assertThat(result.body(), is(sameInstance(body)));
    }

    @Test
    void GIVEN_no_optional_values_WHEN_build_THEN_use_empty_objects() {
      // Arrange
      var path = builder.path();
      path.setValue(ANY_PATH);

      builder.method(HttpMethod.GET);

      // Act
      var result = builder.build();

      // Assert
      assertThat(result.query().values().isEmpty(), is(true));
      assertThat(result.headers().values().isEmpty(), is(true));
      assertThat(result.body(), is(instanceOf(EmptyBody.class)));
    }
  }
}
