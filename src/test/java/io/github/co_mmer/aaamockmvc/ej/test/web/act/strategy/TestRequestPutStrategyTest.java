package io.github.co_mmer.aaamockmvc.ej.test.web.act.strategy;

import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestDataRequestDto.TEST_REQUEST_DTO;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestDataRequestDto.TEST_REQUEST_DTO_WITH_FILE_1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestDataRequestDto.TEST_REQUEST_DTO_WITH_FILE_EMPTY;

import io.github.co_mmer.aaamockmvc.ej.testdata.testsetup.TestRequestStrategySetup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestRequestPutStrategyTest {

  private TestRequestStrategySetup setup;

  private TestRequestPutStrategy strategy;

  @BeforeEach
  void setUp() {
    this.strategy = new TestRequestPutStrategy();
    this.setup = new TestRequestStrategySetup();
  }

  @Test
  void GIVEN_file_empty_WHEN_apply_THEN_TestRequestBuilder_put_is_called() {
    // Arrange
    this.setup.mockTestRequestBuilderPut(TEST_REQUEST_DTO_WITH_FILE_EMPTY);

    // Act
    this.strategy.apply(TEST_REQUEST_DTO_WITH_FILE_EMPTY);

    // Assert
    this.setup.verifyTestRequestBuilderPut(TEST_REQUEST_DTO_WITH_FILE_EMPTY);
  }

  @Test
  void GIVEN_file_empty_WHEN_apply_THEN_TestComponentBody_apply_is_called() {
    // Arrange
    this.setup.mockTestRequestBuilderPut(TEST_REQUEST_DTO_WITH_FILE_EMPTY);

    // Act
    this.strategy.apply(TEST_REQUEST_DTO_WITH_FILE_EMPTY);

    // Assert
    this.setup.verifyTestComponentBodyApply(TEST_REQUEST_DTO_WITH_FILE_EMPTY);
  }

  @Test
  void GIVEN_file_1_WHEN_apply_THEN_TestRequestBuilder_putMultipart_is_called() {
    // Arrange
    this.setup.mockTestRequestBuilderPutMultipart(TEST_REQUEST_DTO_WITH_FILE_1);

    // Act
    this.strategy.apply(TEST_REQUEST_DTO_WITH_FILE_1);

    // Assert
    this.setup.verifyTestRequestBuilderPutMultipart(TEST_REQUEST_DTO_WITH_FILE_1);
  }

  @Test
  void GIVEN_file_1_WHEN_apply_THEN_TestComponentBody_apply_is_called() {
    // Arrange
    this.setup.mockTestRequestBuilderPutMultipart(TEST_REQUEST_DTO_WITH_FILE_1);

    // Act
    this.strategy.apply(TEST_REQUEST_DTO_WITH_FILE_1);

    // Assert
    this.setup.verifyTestComponentBodyMultipartApply(TEST_REQUEST_DTO_WITH_FILE_1);
  }

  @Test
  void WHEN_apply_THEN_TestComponentHead_apply_is_called() {
    // Arrange
    this.setup.mockTestRequestBuilderPut(TEST_REQUEST_DTO);

    // Act
    this.strategy.apply(TEST_REQUEST_DTO);

    // Assert
    this.setup.verifyTestComponentHeadInteraction(TEST_REQUEST_DTO);
  }
}
