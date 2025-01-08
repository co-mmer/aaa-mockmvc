package io.github.co_mmer.aaamockmvc.ej.test.web.arrange.res.body;

import static io.github.co_mmer.aaamockmvc.ej.test.web.mapper.TestGenericMapper.mapToString;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestBody.TEST_BODY_JSON;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestBody.TEST_BODY_XML;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestFiles.TEST_FILE_1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestFiles.TEST_FILE_1_2;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.A1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_A1_JSON;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_REQUEST_BEAN;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockStatic;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_XML;

import io.github.co_mmer.aaamockmvc.ej.test.web.act.TestActImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.base.body.TestArrangeBodyUtils;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.exception.TestArrangeException;
import io.github.co_mmer.aaamockmvc.ej.test.web.mapper.TestGenericMapper;
import io.github.co_mmer.aaamockmvc.ej.test.web.mapper.exception.TestGenericMapperException;
import io.github.co_mmer.aaamockmvc.ej.test.web.request.context.TestRequestContextBuilder;
import io.github.co_mmer.aaamockmvc.ej.test.web.request.model.TestRequestDto;
import io.github.co_mmer.aaamockmvc.ej.test.web.request.model.TestRequestType;
import java.util.stream.Stream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.MockedStatic;
import org.springframework.http.MediaType;

class TestArrangeResBodyImplTest {

  private TestRequestDto dto;
  private TestArrangeResBodyImpl impl;
  private MockedStatic<TestArrangeBodyUtils> mockTestArrangeBodyUtils;

  @BeforeEach
  void setUp() {
    this.mockTestArrangeBodyUtils = mockStatic(TestArrangeBodyUtils.class);

    this.dto = new TestRequestDto(TestRequestType.POST);
    var context =
        TestRequestContextBuilder.getInstance()
            .withTestRequestBean(TEST_REQUEST_BEAN)
            .withTestRequest(this.dto)
            .build();

    this.impl = new TestArrangeResBodyImpl(context);
  }

  @AfterEach
  void clean() {
    this.mockTestArrangeBodyUtils.close();
  }

  @Test
  @SuppressWarnings("ConstantConditions")
  void GIVEN_null_WHEN_call_constructor_THEN_throw_NullPointerException() {
    assertThrows(NullPointerException.class, () -> new TestArrangeResBodyImpl(null));
  }

  @Test
  @SuppressWarnings("ConstantConditions")
  void GIVEN_null_WHEN_setContent_THEN_throw_NullPointerException() {
    assertThrows(NullPointerException.class, () -> new TestArrangeResBodyImpl(null));
  }

  @ParameterizedTest()
  @MethodSource("provideNullParametersArrangeContent")
  @SuppressWarnings("ConstantConditions")
  void GIVEN_null_WHEN_arrangeContent_THEN_throw_NullPointerException(String raw, MediaType type) {
    assertThrows(NullPointerException.class, () -> this.impl.arrangeContent(raw, type));
  }

  private static Stream<Arguments> provideNullParametersArrangeContent() {
    return Stream.of(
        Arguments.of(null, MediaType.APPLICATION_JSON),
        Arguments.of(TEST_BODY_XML, null),
        Arguments.of(null, null));
  }

  @Test
  void GIVEN_xml_WHEN_arrangeContent_THEN_setContent_is_called() {
    // Act
    this.impl.arrangeContent(TEST_BODY_XML, APPLICATION_XML);

    // Assert
    this.mockTestArrangeBodyUtils.verify(
        () -> TestArrangeBodyUtils.setContent(this.dto.getBody(), TEST_BODY_XML, APPLICATION_XML));
  }

  @Test()
  @SuppressWarnings("ConstantConditions")
  void GIVEN_null_WHEN_arrangeJson_THEN_throw_NullPointerException() {
    assertThrows(NullPointerException.class, () -> this.impl.arrangeJson(null));
  }

  @Test
  void GIVEN_json_WHEN_arrangeJson_THEN_addFile_is_setContent() {
    // Act
    this.impl.arrangeJson(TEST_BODY_JSON);

    // Assert
    this.mockTestArrangeBodyUtils.verify(
        () ->
            TestArrangeBodyUtils.setContent(this.dto.getBody(), TEST_BODY_JSON, APPLICATION_JSON));
  }

  @Test()
  @SuppressWarnings("ConstantConditions")
  void GIVEN_null_as_T_WHEN_arrangeJson_THEN_throw_NullPointerException() {
    assertThrows(NullPointerException.class, () -> this.impl.arrangeJson((Object) null));
  }

  @Test()
  void GIVEN_T_exception_WHEN_arrangeJson_THEN_throw_TestArrangeException() {
    // Arrange
    var mockTestGenericMapper = mockStatic(TestGenericMapper.class);
    mockTestGenericMapper
        .when(() -> mapToString(any(), any()))
        .thenThrow(new TestGenericMapperException(new Throwable()));

    // Assert
    assertThrows(
        TestArrangeException.class,
        ()

        // Act
        -> this.impl.arrangeJson(A1));

    mockTestGenericMapper.close();
  }

  @Test
  void GIVEN_T_WHEN_arrangeJson_THEN_addFile_is_setContent() throws Exception {
    // Act
    this.impl.arrangeJson(A1);

    // Assert
    this.mockTestArrangeBodyUtils.verify(
        () -> TestArrangeBodyUtils.setContent(this.dto.getBody(), TEST_A1_JSON, APPLICATION_JSON));
  }

  @Test()
  @SuppressWarnings("ConstantConditions")
  void GIVEN_null_WHEN_arrangeFile_THEN_throw_NullPointerException() {
    assertThrows(NullPointerException.class, () -> this.impl.arrangeFile(null));
  }

  @Test
  void GIVEN_file_WHEN_arrangeFile_THEN_addFile_is_called() {
    // Act
    this.impl.arrangeFile(TEST_FILE_1);

    // Assert
    this.mockTestArrangeBodyUtils.verify(
        () -> TestArrangeBodyUtils.addFile(this.dto.getBody(), TEST_FILE_1));
  }

  @Test()
  @SuppressWarnings("ConstantConditions")
  void GIVEN_null_WHEN_arrangeFiles_THEN_throw_NullPointerException() {
    assertThrows(NullPointerException.class, () -> this.impl.arrangeFiles(null));
  }

  @Test
  void GIVEN_files_WHEN_arrangeFiles_THEN_addFile_is_called() {
    // Act
    this.impl.arrangeFiles(TEST_FILE_1_2);

    // Assert
    this.mockTestArrangeBodyUtils.verify(
        () -> TestArrangeBodyUtils.addFiles(this.dto.getBody(), TEST_FILE_1_2));
  }

  @Test
  void WHEN_act_THEN_expected_class() {
    // Act
    var act = this.impl.act();

    // Assert
    assertThat(act.getClass(), is(TestActImpl.class));
  }
}
