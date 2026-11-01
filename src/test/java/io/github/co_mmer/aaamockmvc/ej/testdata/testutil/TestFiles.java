package io.github.co_mmer.aaamockmvc.ej.testdata.testutil;

import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.mock.web.MockMultipartFile;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestFiles {

  public static final MockMultipartFile TEST_FILE_1 = createFile("test1");
  public static final MockMultipartFile TEST_FILE_2 = createFile("test2");
  public static final List<MockMultipartFile> TEST_FILE_1_2 = List.of(TEST_FILE_1, TEST_FILE_2);

  private static MockMultipartFile createFile(String name) {
    return new MockMultipartFile(name, name, "text/plain", "".getBytes());
  }
}
