package ej.test.aaamockmvc.request.act.strategy;

import static ej.test.aaamockmvc.testdata.testutil.TestDataRequestDto.TEST_REQUEST_DTO;

import ej.test.aaamockmvc.testdata.testsetup.TestRequestStrategySetup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestRequestGetStrategyTest {

  private TestRequestStrategySetup setup;

  private TestRequestGetStrategy strategy;

  @BeforeEach
  void setUp() {
    this.strategy = new TestRequestGetStrategy();
    this.setup = new TestRequestStrategySetup();
  }

  @Test
  void WHEN_apply_THEN_TestRequestBuilder_is_called() {
    // Arrange
    this.setup.mockTestRequestBuilderGet(TEST_REQUEST_DTO);

    // Act
    this.strategy.apply(TEST_REQUEST_DTO);

    // Assert
    this.setup.verifyTestRequestBuilderGet(TEST_REQUEST_DTO);
  }

  @Test
  void WHEN_apply_THEN_TestComponentHead_apply_is_called() {
    // Arrange
    this.setup.mockTestRequestBuilderGet(TEST_REQUEST_DTO);

    // Act
    this.strategy.apply(TEST_REQUEST_DTO);

    // Assert
    this.setup.verifyTestComponentHeadInteraction(TEST_REQUEST_DTO);
  }
}
