package ej.aaamockmvc.test.web.asserts.mapper;

import static ej.aaamockmvc.test.testdata.testutil.TestObject.TEST_OBJECT_1_JSON;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import ej.aaamockmvc.test.testdata.testutil.TestObjectDeserializer;
import ej.aaamockmvc.test.testdata.testutil.TestObjectDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestAssertObjectMapperTest {

  private ObjectMapper objectMapper;

  @BeforeEach
  void setUp() {
    this.objectMapper = new ObjectMapper();
  }

  @Test
  void GIVEN_JsonDeserializer_null_WHEN_mapTo_THEN_return_getRegisteredModuleIds_size_0() {
    // Act
    var objectMapper = TestAssertObjectMapper.create(this.objectMapper, String.class, null);

    // Assert
    assertThat(objectMapper.getRegisteredModuleIds().size(), is(0));
  }

  @Test
  void GIVEN_JsonDeserialize_empty_WHEN_mapTo_THEN_return_getRegisteredModuleIds_size_0() {
    // Act
    var objectMapper =
        TestAssertObjectMapper.create(this.objectMapper, String.class, new JsonDeserializer[0]);

    // Assert
    assertThat(objectMapper.getRegisteredModuleIds().size(), is(0));
  }

  @Test
  void GIVEN_JsonDeserialize_WHEN_mapTo_THEN_return_deserialize() throws Exception {
    // Arrange
    JsonDeserializer<?>[] deserializers = {new TestObjectDeserializer()};

    // Act
    var objectMapper =
        TestAssertObjectMapper.create(this.objectMapper, TestObjectDto.class, deserializers);

    // Assert
    var result = objectMapper.readValue(TEST_OBJECT_1_JSON, TestObjectDto.class);
    assertThat(result.name(), is("deserialize"));
  }
}
