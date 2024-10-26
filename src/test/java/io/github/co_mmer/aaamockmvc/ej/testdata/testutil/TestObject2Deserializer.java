package io.github.co_mmer.aaamockmvc.ej.testdata.testutil;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;

public class TestObject2Deserializer extends JsonDeserializer<TestObject2Dto> {

  @Override
  public TestObject2Dto deserialize(JsonParser p, DeserializationContext context)
      throws IOException {

    var node = p.getCodec().readTree(p);
    var id = node.get("id").toString();
    return new TestObject2Dto(Integer.parseInt(id));
  }
}
