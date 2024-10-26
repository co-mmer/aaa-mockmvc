package io.github.co_mmer.aaamockmvc.ej.test.web.mapper;

import static io.github.co_mmer.aaamockmvc.ej.test.web.mapper.TestGenericMapperConfigurer.registerDeserializers;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_OBJECT_1_JSON;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.TEST_DESERIALIZE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject1Deserializer;
import io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject1Dto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestGenericMapperConfigurerTest {

  private ObjectMapper objectMapper;

  @BeforeEach
  void setUp() {
    this.objectMapper = new ObjectMapper();
  }

  @Test
  void
      GIVEN_JsonDeserializer_null_WHEN_registerDeserializers_THEN_return_getRegisteredModuleIds_size_0() {
    // Act
    var mapper = registerDeserializers(this.objectMapper, String.class, null);

    // Assert
    assertThat(mapper.getRegisteredModuleIds().size(), is(0));
  }

  @Test
  void
      GIVEN_JsonDeserialize_empty_WHEN_registerDeserializers_THEN_return_getRegisteredModuleIds_size_0() {
    // Act
    var mapper = registerDeserializers(this.objectMapper, String.class, new JsonDeserializer[0]);

    // Assert
    assertThat(mapper.getRegisteredModuleIds().size(), is(0));
  }

  @Test
  void GIVEN_JsonDeserialize_WHEN_registerDeserializers_THEN_return_deserialize() throws Exception {
    // Arrange
    JsonDeserializer<?>[] deserializers = {new TestObject1Deserializer()};

    // Act
    var mapper = registerDeserializers(this.objectMapper, TestObject1Dto.class, deserializers);

    // Assert
    var result = mapper.readValue(TEST_OBJECT_1_JSON, TestObject1Dto.class);
    assertThat(result.name(), is(TEST_DESERIALIZE));
  }
}
