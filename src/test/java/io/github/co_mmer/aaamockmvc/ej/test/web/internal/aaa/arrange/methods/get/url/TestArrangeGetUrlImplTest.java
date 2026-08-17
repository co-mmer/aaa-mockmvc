package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.arrange.methods.get.url;

import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.BASE_PATH;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.BASE_URI;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.BASE_URL;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.PATH_WITH_USER_ID;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.QUERY_KEY_SEARCH;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.QUERY_MAP_LIST_SEARCH_PAGE;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.QUERY_MAP_SEARCH_PAGE;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.QUERY_VALUE_SEARCH_TERM;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.arrange.methods.delete.url.TestArrangeDeleteUrlImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.arrange.methods.get.header.TestArrangeGetHeaderImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.context.TestAAAContext;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.RequestPath;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.RequestQuery;
import io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestContext;
import java.net.URI;
import java.util.List;
import java.util.stream.Stream;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class TestArrangeGetUrlImplTest {

  private TestAAAContext context;
  private TestArrangeGetUrlImpl impl;

  @BeforeEach
  void setUp() {
    this.context = TestContext.createContext();
    this.impl = new TestArrangeGetUrlImpl(this.context, BASE_URL);
  }

  private RequestPath getPath() {
    return context.getArrangeBuilder().path();
  }

  private RequestQuery getQuery() {
    return context.getArrangeBuilder().query();
  }

  @Nested
  class constructor {

    @ParameterizedTest()
    @MethodSource("provideNullOnContextUrl")
    @SuppressWarnings("ConstantConditions")
    void GIVEN_provideNullOnContextUrl_WHEN_call_constructor_THEN_throw_Exception(
        TestAAAContext context, String url) {

      assertThrows(NullPointerException.class, () -> new TestArrangeDeleteUrlImpl(context, url));
    }

    private static Stream<Arguments> provideNullOnContextUrl() {
      return Stream.of(
          Arguments.of(null, null),
          Arguments.of(mock(TestAAAContext.class), null),
          Arguments.of(null, BASE_URL));
    }

    @Test
    @SneakyThrows
    void GIVEN_pathWithVariable_WHEN_call_constructor_THEN_return_expected_path() {
      // Act
      new TestArrangeDeleteUrlImpl(context, PATH_WITH_USER_ID, 1);

      // Assert
      assertThat(getPath().value(), is(new URI(BASE_URI + "/" + 1)));
    }

    @ParameterizedTest()
    @MethodSource("provideNullOnContextUri")
    @SuppressWarnings("ConstantConditions")
    void GIVEN_provideNullOnContextUri_WHEN_call_constructor_THEN_throw_Exception(
        TestAAAContext context, URI uri) {

      assertThrows(NullPointerException.class, () -> new TestArrangeDeleteUrlImpl(context, uri));
    }

    private static Stream<Arguments> provideNullOnContextUri() {
      return Stream.of(
          Arguments.of(null, null),
          Arguments.of(mock(TestAAAContext.class), null),
          Arguments.of(null, mock(URI.class)));
    }

    @Test
    @SneakyThrows
    void GIVEN_uri_WHEN_call_constructor_THEN_return_expected_path() {
      // Act
      new TestArrangeDeleteUrlImpl(context, BASE_URI);

      // Assert
      assertThat(getPath().value(), is(BASE_URI));
    }

    @Test
    void GIVEN_url_WHEN_call_constructor_THEN_return_expected_path() {
      new TestArrangeDeleteUrlImpl(context, BASE_PATH);

      // Assert
      assertThat(getPath().value(), is(BASE_URI));
    }
  }

  @Nested
  class query {

    @Test
    @SuppressWarnings("ConstantConditions")
    void GIVEN_keyValue_WHEN_query_THEN_return_expected_query() {
      // Act
      impl.query(QUERY_KEY_SEARCH, QUERY_VALUE_SEARCH_TERM);

      // Assert
      assertThat(getQuery().values(), hasEntry(QUERY_KEY_SEARCH, List.of(QUERY_VALUE_SEARCH_TERM)));
    }

    @Test
    void GIVEN_map_WHEN_query_THEN_return_expected_query() {
      // Act
      impl.query(QUERY_MAP_SEARCH_PAGE);

      // Assert
      assertThat(getQuery().values(), is(QUERY_MAP_LIST_SEARCH_PAGE));
    }
  }

  @Nested
  class headers {

    @Test
    void WHEN_headers_THEN_returnExpectedClass() {
      // Act
      var headers = impl.headers();

      // Assert
      assertThat(headers.getClass(), is(TestArrangeGetHeaderImpl.class));
    }
  }
}
