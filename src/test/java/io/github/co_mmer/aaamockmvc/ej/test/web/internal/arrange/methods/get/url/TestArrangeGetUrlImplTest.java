package io.github.co_mmer.aaamockmvc.ej.test.web.internal.arrange.methods.get.url;

import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.arrange.methods.core.TestArrangeUrlSetter.addQuery;
import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.arrange.methods.core.TestArrangeUrlSetter.setUri;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.BASE_URI;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.BASE_URL;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.PATH_VARIABLE_USER_ID;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.PATH_WITH_USER_ID;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.QUERY_KEY_SEARCH;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.QUERY_MAP_SEARCH_PAGE;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.QUERY_VALUE_SEARCH_TERM;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.springframework.http.HttpMethod.GET;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.arrange.methods.core.TestArrangeUrlSetter;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.arrange.methods.get.header.TestArrangeGetHeaderImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestAAAContext;
import io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestContext;
import java.net.URI;
import java.util.stream.Stream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

class TestArrangeGetUrlImplTest {

  private TestAAAContext context;
  private TestArrangeGetUrlImpl impl;
  private MockedStatic<TestArrangeUrlSetter> mockTestArrangeUrlUtils;

  @BeforeEach
  void setUp() {
    this.context = TestContext.createContext();
    this.mockTestArrangeUrlUtils = Mockito.mockStatic(TestArrangeUrlSetter.class);
    this.impl = new TestArrangeGetUrlImpl(this.context, BASE_URL);
  }

  @AfterEach
  void clean() {
    this.mockTestArrangeUrlUtils.close();
  }

  @Nested
  class constructor {

    @ParameterizedTest()
    @MethodSource("provideNullOnContextUrl")
    @SuppressWarnings("ConstantConditions")
    void GIVEN_provideNullOnContextUrl_WHEN_call_constructor_THEN_throw_Exception(
        TestAAAContext context, String url) {

      assertThrows(NullPointerException.class, () -> new TestArrangeGetUrlImpl(context, url));
    }

    private static Stream<Arguments> provideNullOnContextUrl() {
      return Stream.of(
          Arguments.of(null, null),
          Arguments.of(mock(TestAAAContext.class), null),
          Arguments.of(null, BASE_URL));
    }

    @Test
    void GIVEN_url_WHEN_arrangeUrl_THEN_setUriIsCalled() {
      // Assert
      mockTestArrangeUrlUtils.verify(
          () -> setUri(context.getArrangeResult().getUrl(), GET, BASE_URL));
    }

    @Test
    void GIVEN_pathWithVariable_WHEN_call_constructor_THEN_setUriIsCalled() {
      // Act
      new TestArrangeGetUrlImpl(context, PATH_WITH_USER_ID, PATH_VARIABLE_USER_ID);

      // Assert
      mockTestArrangeUrlUtils.verify(
          () ->
              setUri(
                  context.getArrangeResult().getUrl(),
                  GET,
                  PATH_WITH_USER_ID,
                  PATH_VARIABLE_USER_ID));
    }

    @ParameterizedTest()
    @MethodSource("provideNullOnContextUri")
    @SuppressWarnings("ConstantConditions")
    void GIVEN_provideNullOnContextUri_WHEN_call_constructor_THEN_throw_Exception(
        TestAAAContext context, URI uri) {

      assertThrows(NullPointerException.class, () -> new TestArrangeGetUrlImpl(context, uri));
    }

    private static Stream<Arguments> provideNullOnContextUri() {
      return Stream.of(
          Arguments.of(null, null),
          Arguments.of(mock(TestAAAContext.class), null),
          Arguments.of(null, mock(URI.class)));
    }

    @Test
    void GIVEN_uri_WHEN_call_constructor_THEN_setUriIsCalled() {
      // Act
      new TestArrangeGetUrlImpl(context, BASE_URI);

      // Assert
      mockTestArrangeUrlUtils.verify(
          () -> setUri(context.getArrangeResult().getUrl(), GET, BASE_URI));
    }
  }

  @Nested
  class query {

    @Test
    @SuppressWarnings("ConstantConditions")
    void GIVEN_nullAsKey_WHEN_query_THEN_throw_Exception() {
      assertThrows(NullPointerException.class, () -> impl.query(null, QUERY_VALUE_SEARCH_TERM));
    }

    @Test
    @SuppressWarnings("ConstantConditions")
    void GIVEN_keyValue_WHEN_query_THEN_addQueryIsCalled() {
      // Act
      impl.query(QUERY_KEY_SEARCH, QUERY_VALUE_SEARCH_TERM);

      // Assert
      mockTestArrangeUrlUtils.verify(
          () ->
              addQuery(
                  context.getArrangeResult().getUrl(), QUERY_KEY_SEARCH, QUERY_VALUE_SEARCH_TERM));
    }

    @Test
    @SuppressWarnings("ConstantConditions")
    void GIVEN_nullAsMap_WHEN_query_THEN_throw_Exception() {
      assertThrows(NullPointerException.class, () -> impl.query(null));
    }

    @Test
    void GIVEN_map_WHEN_query_THEN_addQueryIsCalled() {
      // Act
      impl.query(QUERY_MAP_SEARCH_PAGE);

      // Assert
      mockTestArrangeUrlUtils.verify(
          () -> addQuery(context.getArrangeResult().getUrl(), QUERY_MAP_SEARCH_PAGE));
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
