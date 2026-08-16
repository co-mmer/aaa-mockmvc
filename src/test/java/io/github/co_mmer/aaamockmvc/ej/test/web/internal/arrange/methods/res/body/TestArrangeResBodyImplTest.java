package io.github.co_mmer.aaamockmvc.ej.test.web.internal.arrange.methods.res.body;

import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestBody.TEST_BODY_JSON;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestBody.TEST_BODY_XML;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestFiles.TEST_FILE_1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestFiles.TEST_FILE_1_2;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestFiles.TEST_FILE_2;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.A1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_A1_JSON;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockStatic;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_XML;

import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.exception.TestArrangeException;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.mapper.TestGenericMapper;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.mapper.exception.TestGenericMapperException;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestAAAContext;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.request.MultipartBody;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.request.RequestBody;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.request.RequestHeaders;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.request.TextBody;
import io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestContext;
import java.util.List;
import java.util.stream.Stream;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.http.MediaType;

class TestArrangeResBodyImplTest {

  private TestAAAContext context;
  private TestArrangeResBodyImpl impl;

  @BeforeEach
  void setUp() {
    this.context = TestContext.createContext();
    this.impl = new TestArrangeResBodyImpl(this.context);
  }

  private RequestHeaders getHeaders() {
    return context.getArrangeBuilder().headers();
  }

  private RequestBody getBody() {
    return context.getArrangeBuilder().body();
  }

  @Nested
  class constructor {

    @Test
    @SuppressWarnings("ConstantConditions")
    void GIVEN_null_WHEN_call_constructor_THEN_throw_Exception() {
      assertThrows(NullPointerException.class, () -> new TestArrangeResBodyImpl(null));
    }
  }

  @Nested
  class raw {

    @ParameterizedTest()
    @MethodSource("provideNullParametersRaw")
    @SuppressWarnings("ConstantConditions")
    void GIVEN_null_WHEN_arrangeContent_THEN_throw_Exception(String raw, MediaType type) {
      assertThrows(NullPointerException.class, () -> impl.raw(raw, type));
    }

    private static Stream<Arguments> provideNullParametersRaw() {
      return Stream.of(
          Arguments.of(null, APPLICATION_XML),
          Arguments.of(TEST_BODY_XML, null),
          Arguments.of(null, null));
    }

    @Test
    void GIVEN_xml_WHEN_raw_THEN_return_expected_body() {
      // Act
      impl.raw(TEST_BODY_XML, APPLICATION_XML);

      // Assert
      var body = (TextBody) getBody();
      assertThat(body.value(), is(TEST_BODY_XML));
      assertThat(
          getHeaders().values(), hasEntry("Content-Type", List.of(APPLICATION_XML.toString())));
    }
  }

  @Nested
  class json {

    @Test
    @SuppressWarnings("ConstantConditions")
    void GIVEN_null_WHEN_json_THEN_throw_Exception() {
      assertThrows(NullPointerException.class, () -> impl.json(null));
    }

    @Test
    void GIVEN_json_WHEN_json_THEN_return_expected_body() {
      // Act
      impl.json(TEST_BODY_JSON);

      // Assert
      var body = (TextBody) getBody();
      assertThat(body.value(), is(TEST_BODY_JSON));
      assertThat(
          getHeaders().values(), hasEntry("Content-Type", List.of(APPLICATION_JSON.toString())));
    }

    @Test()
    @SuppressWarnings("ConstantConditions")
    void GIVEN_null_as_T_WHEN_json_THEN_throw_Exception() {
      assertThrows(NullPointerException.class, () -> impl.json((Object) null));
    }

    @Test()
    void GIVEN_T_exception_WHEN_json_THEN_throw_Exception() {
      // Arrange
      var mockTestGenericMapper = mockStatic(TestGenericMapper.class);
      mockTestGenericMapper
          .when(() -> TestGenericMapper.toJson(any(), any()))
          .thenThrow(new TestGenericMapperException(new Throwable()));

      // Assert && Act
      assertThrows(TestArrangeException.class, () -> impl.json(A1));
      mockTestGenericMapper.close();
    }

    @Test
    void GIVEN_T_WHEN_json_THEN_return_expected_body() {
      // Act
      impl.json(A1);

      // Assert
      var body = (TextBody) getBody();
      assertThat(body.value(), is(TEST_A1_JSON));
      assertThat(
          getHeaders().values(), hasEntry("Content-Type", List.of(APPLICATION_JSON.toString())));
    }
  }

  @Nested
  class file {

    @Test()
    @SuppressWarnings("ConstantConditions")
    void GIVEN_null_WHEN_file_THEN_throw_Exception() {
      assertThrows(NullPointerException.class, () -> impl.file(null));
    }

    @Test
    @SneakyThrows
    void GIVEN_file1_WHEN_file_THEN_addFileIsCalled() {
      // Act
      impl.file(TEST_FILE_1);

      // Assert
      var body = (MultipartBody) getBody();
      assertThat(body.parts(), hasSize(1));

      var firstPart = body.parts().get(0);
      assertThat(firstPart.name(), is(TEST_FILE_1.getName()));
      assertThat(firstPart.filename(), is(TEST_FILE_1.getOriginalFilename()));
      assertThat(firstPart.content(), is(TEST_FILE_1.getBytes()));
      assertThat(firstPart.contentType(), is(TEST_FILE_1.getContentType()));
    }
  }

  @Nested
  class files {

    @Test()
    @SuppressWarnings("ConstantConditions")
    void GIVEN_null_WHEN_files_THEN_throw_Exception() {
      assertThrows(NullPointerException.class, () -> impl.files(null));
    }

    @Test
    @SneakyThrows
    void GIVEN_files_WHEN_files_THEN_addFileIsCalled() {
      // Act
      impl.files(TEST_FILE_1_2);

      // Assert
      var body = (MultipartBody) getBody();
      assertThat(body.parts(), hasSize(2));

      var firstPart = body.parts().get(0);
      assertThat(firstPart.name(), is(TEST_FILE_1.getName()));
      assertThat(firstPart.filename(), is(TEST_FILE_1.getOriginalFilename()));
      assertThat(firstPart.content(), is(TEST_FILE_1.getBytes()));
      assertThat(firstPart.contentType(), is(TEST_FILE_1.getContentType()));

      var secondPart = body.parts().get(1);
      assertThat(secondPart.name(), is(TEST_FILE_2.getName()));
      assertThat(secondPart.filename(), is(TEST_FILE_2.getOriginalFilename()));
      assertThat(secondPart.content(), is(TEST_FILE_1.getBytes()));
      assertThat(secondPart.contentType(), is(TEST_FILE_1.getContentType()));
    }
  }
}
