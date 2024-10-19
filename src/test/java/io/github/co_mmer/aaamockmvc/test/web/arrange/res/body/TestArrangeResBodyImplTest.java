package io.github.co_mmer.aaamockmvc.test.web.arrange.res.body;

import static io.github.co_mmer.aaamockmvc.test.testdata.testutil.TestBody.TEST_BODY_JSON;
import static io.github.co_mmer.aaamockmvc.test.testdata.testutil.TestBody.TEST_BODY_XML;
import static io.github.co_mmer.aaamockmvc.test.testdata.testutil.TestFiles.TEST_FILE_1;
import static io.github.co_mmer.aaamockmvc.test.testdata.testutil.TestFiles.TEST_FILE_1_2;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_XML;

import io.github.co_mmer.aaamockmvc.test.web.act.TestActImpl;
import io.github.co_mmer.aaamockmvc.test.web.arrange.base.body.TestArrangeBodyUtils;
import io.github.co_mmer.aaamockmvc.test.web.request.context.TestRequestContextBuilder;
import io.github.co_mmer.aaamockmvc.test.web.request.model.TestRequestDto;
import io.github.co_mmer.aaamockmvc.test.web.request.model.TestRequestType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

class TestArrangeResBodyImplTest {

  private TestRequestDto dto;
  private TestArrangeResBodyImpl impl;
  private MockedStatic<TestArrangeBodyUtils> mockTestArrangeBodyUtils;

  @BeforeEach
  void setUp() {
    this.mockTestArrangeBodyUtils = Mockito.mockStatic(TestArrangeBodyUtils.class);

    this.dto = new TestRequestDto(TestRequestType.POST);
    var context = TestRequestContextBuilder.getInstance().withTestRequest(this.dto).build();

    this.impl = new TestArrangeResBodyImpl(context);
  }

  @AfterEach
  void clean() {
    this.mockTestArrangeBodyUtils.close();
  }

  @Test
  void GIVEN_json_WHEN_arrangeJson_THEN_setContent_is_called() {
    // Act
    this.impl.arrangeJson(TEST_BODY_JSON);

    // Assert
    this.mockTestArrangeBodyUtils.verify(
        () ->
            TestArrangeBodyUtils.setContent(this.dto.getBody(), TEST_BODY_JSON, APPLICATION_JSON));
  }

  @Test
  void GIVEN_xml_WHEN_arrangeContent_THEN_setContent_is_called() {
    // Act
    this.impl.arrangeContent(TEST_BODY_XML, APPLICATION_XML);

    // Assert
    this.mockTestArrangeBodyUtils.verify(
        () -> TestArrangeBodyUtils.setContent(this.dto.getBody(), TEST_BODY_XML, APPLICATION_XML));
  }

  @Test
  void GIVEN_file_WHEN_arrangeFile_THEN_addFile_is_called() {
    // Act
    this.impl.arrangeFile(TEST_FILE_1);

    // Assert
    this.mockTestArrangeBodyUtils.verify(
        () -> TestArrangeBodyUtils.addFile(this.dto.getBody(), TEST_FILE_1));
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
