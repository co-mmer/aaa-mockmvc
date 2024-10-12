package ej.test.aaamockmvc.testdata.testutil;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;

public class TestObjectDeserializer extends JsonDeserializer<TestObjectDto> {

  @Override
  public TestObjectDto deserialize(JsonParser p, DeserializationContext context)
      throws IOException {

    var node = p.getCodec().readTree(p);
    var id = node.get("id").toString();
    return new TestObjectDto(Integer.parseInt(id), "deserialize");
  }
}
