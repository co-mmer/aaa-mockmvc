package io.github.co_mmer.aaamockmvc.ej.test.web.act.strategy;

import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestDataRequestDto.TEST_REQUEST_DTO;

import io.github.co_mmer.aaamockmvc.ej.testdata.testsetup.TestRequestStrategySetup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestRequestOptionsStrategyTest {

  private TestRequestStrategySetup setup;

  private TestRequestOptionsStrategy strategy;

  @BeforeEach
  void setUp() {
    this.strategy = new TestRequestOptionsStrategy();
    this.setup = new TestRequestStrategySetup();
  }

  @Test
  void WHEN_apply_THEN_TestRequestBuilder_is_called() {
    // Arrange
    this.setup.mockTestRequestBuilderOptions(TEST_REQUEST_DTO);

    // Act
    this.strategy.apply(TEST_REQUEST_DTO);

    // Assert
    this.setup.verifyTestRequestBuilderOptions(TEST_REQUEST_DTO);
  }

  @Test
  void WHEN_apply_THEN_TestComponentHead_apply_is_called() {
    // Arrange
    this.setup.mockTestRequestBuilderOptions(TEST_REQUEST_DTO);

    // Act
    this.strategy.apply(TEST_REQUEST_DTO);

    // Assert
    this.setup.verifyTestComponentHeadInteraction(TEST_REQUEST_DTO);
  }
}
