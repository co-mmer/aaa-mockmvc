package io.github.co_mmer.aaamockmvc.ej.testdata.testutil;

import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.TEST_DESERIALIZE;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;

public class TestObject1Deserializer extends JsonDeserializer<TestObject1Dto> {

  @Override
  public TestObject1Dto deserialize(JsonParser p, DeserializationContext context)
      throws IOException {

    var node = p.getCodec().readTree(p);
    var id = node.get("id").toString();
    return new TestObject1Dto(Integer.parseInt(id), TEST_DESERIALIZE);
  }
}
