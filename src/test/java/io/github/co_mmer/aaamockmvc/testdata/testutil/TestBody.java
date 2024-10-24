package io.github.co_mmer.aaamockmvc.testdata.testutil;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestBody {

  public static final String TEST_BODY_JSON = "jsonBody";
  public static final String TEST_BODY_XML =
      "<ExampleRequest>" + "<Name>John Doe</Name>" + "<Age>30</Age>" + "</ExampleRequest>";
}
