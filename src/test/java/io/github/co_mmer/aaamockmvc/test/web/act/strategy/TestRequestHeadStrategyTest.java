package io.github.co_mmer.aaamockmvc.test.web.act.strategy;

import static io.github.co_mmer.aaamockmvc.test.testdata.testutil.TestDataRequestDto.TEST_REQUEST_DTO;

import io.github.co_mmer.aaamockmvc.test.testdata.testsetup.TestRequestStrategySetup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestRequestHeadStrategyTest {

  private TestRequestStrategySetup setup;

  private TestRequestHeadStrategy strategy;

  @BeforeEach
  void setUp() {
    this.strategy = new TestRequestHeadStrategy();
    this.setup = new TestRequestStrategySetup();
  }

  @Test
  void WHEN_apply_THEN_TestRequestBuilder_is_called() {
    // Arrange
    this.setup.mockTestRequestBuilderHead(TEST_REQUEST_DTO);

    // Act
    this.strategy.apply(TEST_REQUEST_DTO);

    // Assert
    this.setup.verifyTestRequestBuilderHead(TEST_REQUEST_DTO);
  }

  @Test
  void WHEN_apply_THEN_TestComponentHead_apply_is_called() {
    // Arrange
    this.setup.mockTestRequestBuilderHead(TEST_REQUEST_DTO);

    // Act
    this.strategy.apply(TEST_REQUEST_DTO);

    // Assert
    this.setup.verifyTestComponentHeadInteraction(TEST_REQUEST_DTO);
  }
}
