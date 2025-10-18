package io.github.co_mmer.aaamockmvc.ej.test.web.internal.arrange.methods.core;

import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.arrange.methods.core.TestArrangeUrlSetter.addQuery;
import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.arrange.methods.core.TestArrangeUrlSetter.setUri;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.BASE_PATH;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.BASE_URI;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.PATH_VARIABLE_USER_ID;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.PATH_WITH_USER_AND_ORDER;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.PATH_WITH_USER_ID;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.PATH_WITH_USER_ORDER_AND_PRODUCT;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.PATH_WITH_USER_ORDER_PRODUCT_CATEGORY_SESSION_REQUEST;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.QUERY_KEY_2;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.QUERY_KEY_PAGE;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.QUERY_KEY_SEARCH;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.QUERY_MAP_1_2;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.QUERY_MAP_SEARCH_PAGE;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.QUERY_VALUE_2;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.QUERY_VALUE_PAGE_NUMBER;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.QUERY_VALUE_SEARCH_TERM;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.SAMPLE_BOOLEAN_TRUE;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.SAMPLE_CHAR_1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.SAMPLE_DOUBLE_1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.SAMPLE_FLOAT_1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.SAMPLE_INT_1;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.http.HttpMethod.GET;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.request.TestRequestUrlDto;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

class TestArrangeUrlSetterTest {

  private TestRequestUrlDto urlDto;

  @BeforeEach
  void setUp() {
    this.urlDto = new TestRequestUrlDto();
  }

  @Nested
  class SetUri {

    @Test
    void GIVEN_path_WHEN_setUri_THEN_getUri_returned_expected_value() {
      // Act
      setUri(urlDto, GET, BASE_PATH);

      // Assert
      assertThat(urlDto.getUri(), is(BASE_URI));
    }

    @Test
    void GIVEN_path_var1_and_vars_1_WHEN_setUri_THEN_getUri_returned_expected_value() {
      // Act
      setUri(urlDto, GET, PATH_WITH_USER_ID, PATH_VARIABLE_USER_ID);

      // Assert
      assertThat(urlDto.getUri().toString(), is(BASE_PATH + "/" + PATH_VARIABLE_USER_ID));
    }

    @Test
    void
        GIVEN_path_var3_and_vars_only_unsupported_WHEN_setUri_THEN_throw_IllegalArgumentException() {
      // Arrange
      Executable executable =
          () -> setUri(urlDto, GET, PATH_WITH_USER_ORDER_AND_PRODUCT, List.of(1), Map.of("k", 1));

      // Act && Assert
      assertThrows(IllegalArgumentException.class, executable);
    }

    @Test
    void
        GIVEN_path_var2_and_vars_2_unsupported_and_2_supported_WHEN_setUri_THEN_getUri_returned_expected_value() {
      // Act
      setUri(
          urlDto,
          GET,
          PATH_WITH_USER_AND_ORDER,
          List.of(1),
          Map.of("k", 1),
          SAMPLE_INT_1,
          PATH_VARIABLE_USER_ID);

      // Assert
      var uri = urlDto.getUri().toString();
      assertThat(uri, is(BASE_PATH + "/" + SAMPLE_INT_1 + "/" + PATH_VARIABLE_USER_ID));
    }

    @Test
    void GIVEN_path_var6_and_vars_only_supported_WHEN_setUri_THEN_getUri_returned_expected_value() {
      // Act
      setUri(
          urlDto,
          GET,
          PATH_WITH_USER_ORDER_PRODUCT_CATEGORY_SESSION_REQUEST,
          PATH_VARIABLE_USER_ID,
          SAMPLE_CHAR_1,
          SAMPLE_INT_1,
          SAMPLE_DOUBLE_1,
          SAMPLE_FLOAT_1,
          SAMPLE_BOOLEAN_TRUE);

      // Assert
      var uri = urlDto.getUri().toString();
      assertThat(
          uri,
          is(
              BASE_PATH
                  + "/"
                  + PATH_VARIABLE_USER_ID
                  + "/"
                  + SAMPLE_CHAR_1
                  + "/"
                  + SAMPLE_INT_1
                  + "/"
                  + SAMPLE_DOUBLE_1
                  + "/"
                  + SAMPLE_FLOAT_1
                  + "/"
                  + SAMPLE_BOOLEAN_TRUE));
    }
  }

  @Nested
  class AddQuery {

    @Test
    void GIVEN_key_value_WHEN_addQuery_THEN_getQuery_returned_expected_value() {
      // Act
      addQuery(urlDto, QUERY_KEY_SEARCH, QUERY_VALUE_SEARCH_TERM);

      // Assert
      assertThat(urlDto.getQuery().size(), is(1));
      assertThat(urlDto.getQuery().get(QUERY_KEY_SEARCH), is(QUERY_VALUE_SEARCH_TERM));
    }

    @Test
    void GIVEN_key_value_map_WHEN_addQuery_THEN_getQuery_returned_expected_value() {
      // Act
      addQuery(urlDto, QUERY_MAP_SEARCH_PAGE);

      // Assert
      assertThat(urlDto.getQuery(), is(QUERY_MAP_SEARCH_PAGE));
    }

    @Test
    void
        GIVEN_addQuery_key_0_WHEN_addQuery_with_map_key_1_2_THEN_getQuery_returned_expected_values() {
      // Arrange
      addQuery(urlDto, QUERY_KEY_SEARCH, QUERY_VALUE_SEARCH_TERM);

      // Act
      addQuery(urlDto, QUERY_MAP_1_2);

      // Assert
      assertThat(urlDto.getQuery().size(), is(3));
      assertThat(urlDto.getQuery().get(QUERY_KEY_SEARCH), is(QUERY_VALUE_SEARCH_TERM));
      assertThat(urlDto.getQuery().get(QUERY_KEY_PAGE), is(QUERY_VALUE_PAGE_NUMBER));
      assertThat(urlDto.getQuery().get(QUERY_KEY_2), is(QUERY_VALUE_2));
    }
  }
}
