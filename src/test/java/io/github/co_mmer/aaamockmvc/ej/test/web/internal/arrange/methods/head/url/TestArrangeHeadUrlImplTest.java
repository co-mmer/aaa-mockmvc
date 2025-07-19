package io.github.co_mmer.aaamockmvc.ej.test.web.internal.arrange.methods.head.url;

import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.arrange.base.url.TestArrangeUrlUtils.addQuery;
import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.arrange.base.url.TestArrangeUrlUtils.setUri;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestParameter.TEST_PARAM_KEY_VALUE_MAP_1_2;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestParameter.TEST_QUERY_KEY_1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestParameter.TEST_QUERY_VALUE_1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.TEST_PATH_VAR1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.TEST_URI;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.TEST_URL;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.VAR_STRING_1;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.springframework.http.HttpMethod.HEAD;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.arrange.base.url.TestArrangeUrlUtils;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.arrange.methods.head.header.TestArrangeHeadHeaderImpl;
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

class TestArrangeHeadUrlImplTest {

  private TestAAAContext context;
  private TestArrangeHeadUrlImpl impl;
  private MockedStatic<TestArrangeUrlUtils> mockTestArrangeUrlUtils;

  @BeforeEach
  void setUp() {
    this.context = TestContext.createContext();
    this.mockTestArrangeUrlUtils = Mockito.mockStatic(TestArrangeUrlUtils.class);
    this.impl = new TestArrangeHeadUrlImpl(this.context, TEST_URL);
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
    void GIVEN_provideNullOnContextUrl_WHEN_call_constructor_THEN_throwException(
        TestAAAContext context, String url) {

      assertThrows(NullPointerException.class, () -> new TestArrangeHeadUrlImpl(context, url));
    }

    private static Stream<Arguments> provideNullOnContextUrl() {
      return Stream.of(
          Arguments.of(null, null),
          Arguments.of(mock(TestAAAContext.class), null),
          Arguments.of(null, TEST_URL));
    }

    @Test
    void GIVEN_url_WHEN_arrangeUrl_THEN_setUriIsCalled() {
      // Assert
      mockTestArrangeUrlUtils.verify(
          () -> setUri(context.getArrangeResult().getUrl(), HEAD, TEST_URL));
    }

    @Test
    void GIVEN_pathWithVariable_WHEN_call_constructor_THEN_setUriIsCalled() {
      // Act
      new TestArrangeHeadUrlImpl(context, TEST_PATH_VAR1, VAR_STRING_1);

      // Assert
      mockTestArrangeUrlUtils.verify(
          () -> setUri(context.getArrangeResult().getUrl(), HEAD, TEST_PATH_VAR1, VAR_STRING_1));
    }

    @ParameterizedTest()
    @MethodSource("provideNullOnContextUri")
    @SuppressWarnings("ConstantConditions")
    void GIVEN_provideNullOnContextUri_WHEN_call_constructor_THEN_throwException(
        TestAAAContext context, URI uri) {

      assertThrows(NullPointerException.class, () -> new TestArrangeHeadUrlImpl(context, uri));
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
      new TestArrangeHeadUrlImpl(context, TEST_URI);

      // Assert
      mockTestArrangeUrlUtils.verify(
          () -> setUri(context.getArrangeResult().getUrl(), HEAD, TEST_URI));
    }
  }

  @Nested
  class query {

    @Test
    @SuppressWarnings("ConstantConditions")
    void GIVEN_nullAsKey_WHEN_query_THEN_throwException() {
      assertThrows(NullPointerException.class, () -> impl.query(null, TEST_QUERY_VALUE_1));
    }

    @Test
    @SuppressWarnings("ConstantConditions")
    void GIVEN_keyValue_WHEN_query_THEN_addQueryIsCalled() {
      // Act
      impl.query(TEST_QUERY_KEY_1, TEST_QUERY_VALUE_1);

      // Assert
      mockTestArrangeUrlUtils.verify(
          () ->
              addQuery(context.getArrangeResult().getUrl(), TEST_QUERY_KEY_1, TEST_QUERY_VALUE_1));
    }

    @Test
    @SuppressWarnings("ConstantConditions")
    void GIVEN_nullAsMap_WHEN_query_THEN_throwException() {
      assertThrows(NullPointerException.class, () -> impl.query(null));
    }

    @Test
    void GIVEN_map_WHEN_query_THEN_addQueryIsCalled() {
      // Act
      impl.query(TEST_PARAM_KEY_VALUE_MAP_1_2);

      // Assert
      mockTestArrangeUrlUtils.verify(
          () -> addQuery(context.getArrangeResult().getUrl(), TEST_PARAM_KEY_VALUE_MAP_1_2));
    }
  }

  @Nested
  class headers {

    @Test
    void WHEN_headers_THEN_returnExpectedClass() {
      // Act
      var headers = impl.headers();

      // Assert
      assertThat(headers.getClass(), is(TestArrangeHeadHeaderImpl.class));
    }
  }
}
