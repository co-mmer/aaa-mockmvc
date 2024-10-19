package ej.aaamockmvc.test.web.act.strategy;

import static ej.aaamockmvc.test.testdata.testutil.TestDataRequestDto.TEST_REQUEST_DTO;

import ej.aaamockmvc.test.testdata.testsetup.TestRequestStrategySetup;
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
