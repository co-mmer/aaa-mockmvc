package ej.test.aaamockmvc.request.act.strategy;

import static ej.test.aaamockmvc.testdata.testutil.TestDataRequestDto.TEST_REQUEST_DTO;
import static ej.test.aaamockmvc.testdata.testutil.TestDataRequestDto.TEST_REQUEST_DTO_WITH_FILE_1;
import static ej.test.aaamockmvc.testdata.testutil.TestDataRequestDto.TEST_REQUEST_DTO_WITH_FILE_EMPTY;

import ej.test.aaamockmvc.testdata.testsetup.TestRequestStrategySetup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestRequestPatchStrategyTest {

  private TestRequestStrategySetup setup;

  private TestRequestPatchStrategy strategy;

  @BeforeEach
  void setUp() {
    this.strategy = new TestRequestPatchStrategy();
    this.setup = new TestRequestStrategySetup();
  }

  @Test
  void GIVEN_file_empty_WHEN_apply_THEN_TestRequestBuilder_patch_is_called() {
    // Arrange
    this.setup.mockTestRequestBuilderPatch(TEST_REQUEST_DTO_WITH_FILE_EMPTY);

    // Act
    this.strategy.apply(TEST_REQUEST_DTO_WITH_FILE_EMPTY);

    // Assert
    this.setup.verifyTestRequestBuilderPatch(TEST_REQUEST_DTO_WITH_FILE_EMPTY);
  }

  @Test
  void GIVEN_file_empty_WHEN_apply_THEN_TestComponentBody_apply_is_called() {
    // Arrange
    this.setup.mockTestRequestBuilderPatch(TEST_REQUEST_DTO_WITH_FILE_EMPTY);

    // Act
    this.strategy.apply(TEST_REQUEST_DTO_WITH_FILE_EMPTY);

    // Assert
    this.setup.verifyTestComponentBodyApply(TEST_REQUEST_DTO_WITH_FILE_EMPTY);
  }

  @Test
  void GIVEN_file_1_WHEN_apply_THEN_TestRequestBuilder_patchMultipart_is_called() {
    // Arrange
    this.setup.mockTestRequestBuilderPatchMultipart(TEST_REQUEST_DTO_WITH_FILE_1);

    // Act
    this.strategy.apply(TEST_REQUEST_DTO_WITH_FILE_1);

    // Assert
    this.setup.verifyTestRequestBuilderPatchMultipart(TEST_REQUEST_DTO_WITH_FILE_1);
  }

  @Test
  void GIVEN_file_1_WHEN_apply_THEN_TestComponentBody_apply_is_called() {
    // Arrange
    this.setup.mockTestRequestBuilderPatchMultipart(TEST_REQUEST_DTO_WITH_FILE_1);

    // Act
    this.strategy.apply(TEST_REQUEST_DTO_WITH_FILE_1);

    // Assert
    this.setup.verifyTestComponentBodyMultipartApply(TEST_REQUEST_DTO_WITH_FILE_1);
  }

  @Test
  void WHEN_apply_THEN_TestComponentHead_apply_is_called() {
    // Arrange
    this.setup.mockTestRequestBuilderPatch(TEST_REQUEST_DTO);

    // Act
    this.strategy.apply(TEST_REQUEST_DTO);

    // Assert
    this.setup.verifyTestComponentHeadInteraction(TEST_REQUEST_DTO);
  }
}
