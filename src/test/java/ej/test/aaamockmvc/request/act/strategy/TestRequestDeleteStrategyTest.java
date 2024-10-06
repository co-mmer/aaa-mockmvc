package ej.test.aaamockmvc.request.act.strategy;

import static ej.test.aaamockmvc.testdata.testutil.TestDataRequestDto.TEST_REQUEST_DTO;

import ej.test.aaamockmvc.testdata.testsetup.TestRequestStrategySetup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestRequestDeleteStrategyTest {

  private TestRequestStrategySetup setup;

  private TestRequestDeleteStrategy strategy;

  @BeforeEach
  void setUp() {
    this.strategy = new TestRequestDeleteStrategy();
    this.setup = new TestRequestStrategySetup();
  }

  @Test
  void WHEN_apply_THEN_TestRequestBuilder_is_called() {
    // Arrange
    this.setup.mockTestRequestBuilderDelete(TEST_REQUEST_DTO);

    // Act
    this.strategy.apply(TEST_REQUEST_DTO);

    // Assert
    this.setup.verifyTestRequestBuilderDelete(TEST_REQUEST_DTO);
  }

  @Test
  void WHEN_apply_THEN_TestComponentHead_apply_is_called() {
    // Arrange
    this.setup.mockTestRequestBuilderDelete(TEST_REQUEST_DTO);

    // Act
    this.strategy.apply(TEST_REQUEST_DTO);

    // Assert
    this.setup.verifyTestComponentHeadInteraction(TEST_REQUEST_DTO);
  }
}
