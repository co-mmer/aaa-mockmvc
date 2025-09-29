package io.github.co_mmer.aaamockmvc.ej.test.web.internal.arrange.methods.res.body;

import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.arrange.methods.core.TestArrangeBodySetter.addFile;
import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.arrange.methods.core.TestArrangeBodySetter.addFiles;
import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.arrange.methods.core.TestArrangeBodySetter.setContent;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestBody.TEST_BODY_JSON;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestBody.TEST_BODY_XML;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestFiles.TEST_FILE_1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestFiles.TEST_FILE_1_2;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.A1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_A1_JSON;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockStatic;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_XML;

import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.exception.TestArrangeException;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.arrange.methods.core.TestArrangeBodySetter;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.mapper.TestGenericMapper;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.mapper.exception.TestGenericMapperException;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestAAAContext;
import io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestContext;
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
import org.springframework.http.MediaType;

class TestArrangeResBodyImplTest {

  private TestAAAContext context;
  private TestArrangeResBodyImpl impl;
  private MockedStatic<TestArrangeBodySetter> mockTestArrangeBodyUtils;

  @BeforeEach
  void setUp() {
    this.context = TestContext.createContext();
    this.mockTestArrangeBodyUtils = Mockito.mockStatic(TestArrangeBodySetter.class);
    this.impl = new TestArrangeResBodyImpl(this.context);
  }

  @AfterEach
  void clean() {
    this.mockTestArrangeBodyUtils.close();
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
    void GIVEN_xml_WHEN_raw_THEN_setContentIsCalled() {
      // Act
      impl.raw(TEST_BODY_XML, APPLICATION_XML);

      // Assert
      mockTestArrangeBodyUtils.verify(
          () -> setContent(context.getArrangeResult().getBody(), TEST_BODY_XML, APPLICATION_XML));
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
    void GIVEN_json_WHEN_json_THEN_setContentIsCalled() {
      // Act
      impl.json(TEST_BODY_JSON);

      // Assert
      mockTestArrangeBodyUtils.verify(
          () -> setContent(context.getArrangeResult().getBody(), TEST_BODY_JSON, APPLICATION_JSON));
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
    void GIVEN_T_WHEN_json_THEN_setContentIsCalled() {
      // Act
      impl.json(A1);

      // Assert
      mockTestArrangeBodyUtils.verify(
          () -> setContent(context.getArrangeResult().getBody(), TEST_A1_JSON, APPLICATION_JSON));
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
    void GIVEN_file1_WHEN_file_THEN_addFileIsCalled() {
      // Act
      impl.file(TEST_FILE_1);

      // Assert
      mockTestArrangeBodyUtils.verify(
          () -> addFile(context.getArrangeResult().getBody(), TEST_FILE_1));
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
    void GIVEN_files_WHEN_files_THEN_addFileIsCalled() {
      // Act
      impl.files(TEST_FILE_1_2);

      // Assert
      mockTestArrangeBodyUtils.verify(
          () -> addFiles(context.getArrangeResult().getBody(), TEST_FILE_1_2));
    }
  }
}
