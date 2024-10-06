package ej.test.aaamockmvc.request.arrange.res.body;

import static ej.test.aaamockmvc.testdata.testutil.TestBody.TEST_BODY_JSON;
import static ej.test.aaamockmvc.testdata.testutil.TestBody.TEST_BODY_XML;
import static ej.test.aaamockmvc.testdata.testutil.TestFiles.TEST_FILE_1;
import static ej.test.aaamockmvc.testdata.testutil.TestFiles.TEST_FILE_1_2;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_XML;

import ej.test.aaamockmvc.context.TestRequestContextBuilder;
import ej.test.aaamockmvc.request.act.TestActPerformImpl;
import ej.test.aaamockmvc.request.arrange.utils.TestArrangeRequestBody;
import ej.test.aaamockmvc.request.model.TestRequestDto;
import ej.test.aaamockmvc.request.model.TestRequestType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

class TestArrangeResBodyImplTest {

  private TestRequestDto dto;
  private TestArrangeResBodyImpl impl;
  private MockedStatic<TestArrangeRequestBody> mockTestArrangeRequestBody;

  @BeforeEach
  void setUp() {
    this.mockTestArrangeRequestBody = Mockito.mockStatic(TestArrangeRequestBody.class);

    this.dto = new TestRequestDto(TestRequestType.POST);
    var context = TestRequestContextBuilder.getInstance().withTestRequest(this.dto).build();

    this.impl = new TestArrangeResBodyImpl(context);
  }

  @AfterEach
  void clean() {
    this.mockTestArrangeRequestBody.close();
  }

  @Test
  void GIVEN_json_WHEN_arrangeJson_THEN_setContent_is_called() {
    // Act
    this.impl.arrangeJson(TEST_BODY_JSON);

    // Assert
    this.mockTestArrangeRequestBody.verify(
        () ->
            TestArrangeRequestBody.setContent(
                this.dto.getBody(), TEST_BODY_JSON, APPLICATION_JSON));
  }

  @Test
  void GIVEN_xml_WHEN_arrangeContent_THEN_setContent_is_called() {
    // Act
    this.impl.arrangeContent(TEST_BODY_XML, APPLICATION_XML);

    // Assert
    this.mockTestArrangeRequestBody.verify(
        () ->
            TestArrangeRequestBody.setContent(this.dto.getBody(), TEST_BODY_XML, APPLICATION_XML));
  }

  @Test
  void GIVEN_file_WHEN_arrangeFile_THEN_addFile_is_called() {
    // Act
    this.impl.arrangeFile(TEST_FILE_1);

    // Assert
    this.mockTestArrangeRequestBody.verify(
        () -> TestArrangeRequestBody.addFile(this.dto.getBody(), TEST_FILE_1));
  }

  @Test
  void GIVEN_files_WHEN_arrangeFiles_THEN_addFile_is_called() {
    // Act
    this.impl.arrangeFiles(TEST_FILE_1_2);

    // Assert
    this.mockTestArrangeRequestBody.verify(
        () -> TestArrangeRequestBody.addFiles(this.dto.getBody(), TEST_FILE_1_2));
  }

  @Test
  void WHEN_act_THEN_expected_class() {
    // Act
    var act = this.impl.act();

    // Assert
    assertThat(act.getClass(), is(TestActPerformImpl.class));
  }
}
