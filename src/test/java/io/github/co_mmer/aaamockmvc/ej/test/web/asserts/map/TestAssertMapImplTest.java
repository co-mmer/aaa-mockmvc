package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.map;

import static io.github.co_mmer.aaamockmvc.ej.test.web.asserts.content.TestArrangeNormalizer.normalizeMap;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_MAP_1_DTO;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_MAP_1_JSON;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_OBJECTS_MAP_2_DTO;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_SET_1_JSON;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.content.TestArrangeNormalizer;
import io.github.co_mmer.aaamockmvc.ej.testdata.testmock.MockTestGenericMapper;
import io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject1Dto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

class TestAssertMapImplTest {

  private ResultActions actions;
  private MockHttpServletResponse response;
  private TestAssertMapImpl testAssert;

  @BeforeEach
  void setUp() {
    this.actions = mock(ResultActions.class);
    var mvcResult = mock(MvcResult.class);
    this.response = new MockHttpServletResponse();

    when(mvcResult.getResponse()).thenReturn(this.response);
    when(this.actions.andReturn()).thenReturn(mvcResult);

    this.testAssert = new TestAssertMapImpl(this.actions, new ObjectMapper());
  }

  private void mockResponse(String json) throws Exception {
    this.response.getWriter().write(json);
  }

  @Test
  void GIVEN_expected_map_WHEN_assertMapEquals_THEN_assert_is_true() throws Exception {
    // Arrange
    mockResponse(TEST_MAP_1_JSON);

    // Act & Assert
    this.testAssert.assertMapEquals(Boolean.class, TestObject1Dto.class, TEST_MAP_1_DTO);
  }

  @Test
  void GIVEN_unexpected_map_WHEN_assertMapEquals_THEN_assert_is_false() throws Exception {
    // Arrange
    mockResponse(TEST_SET_1_JSON);

    // Act & Assert
    assertThrows(
        AssertionError.class,
        () ->
            this.testAssert.assertMapEquals(
                Boolean.class, TestObject1Dto.class, TEST_OBJECTS_MAP_2_DTO));
  }

  @Test
  void GIVEN_exception_map_WHEN_assertMapEquals_THEN_assert_is_false() {
    // Arrange
    var mockTestGenericMapper = MockTestGenericMapper.mapToMapThrowException();

    // Act & Assert
    assertThrows(
        AssertionFailedError.class,
        () -> this.testAssert.assertMapEquals(Boolean.class, TestObject1Dto.class, TEST_MAP_1_DTO));

    mockTestGenericMapper.close();
  }

  @Test
  void GIVEN_map_WHEN_assertMapEquals_THEN_normalizeMap_is_called() throws Exception {
    // Arrange
    var mockTestArrangeNormalizer = mockStatic(TestArrangeNormalizer.class);
    mockResponse(TEST_MAP_1_JSON);

    // Act
    this.testAssert.assertMapEquals(Boolean.class, TestObject1Dto.class, TEST_MAP_1_DTO);

    // Assert
    mockTestArrangeNormalizer.verify(() -> normalizeMap(any()), times(2));
    mockTestArrangeNormalizer.close();
  }
}
