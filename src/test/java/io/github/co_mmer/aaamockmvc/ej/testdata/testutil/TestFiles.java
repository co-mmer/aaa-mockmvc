package io.github.co_mmer.aaamockmvc.ej.testdata.testutil;

import java.nio.charset.StandardCharsets;
import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestFiles {

  public static final MockMultipartFile FILE_2 =
      new MockMultipartFile(
          "files",
          "b.json",
          MediaType.APPLICATION_JSON_VALUE,
          "{\"k\":1}".getBytes(StandardCharsets.UTF_8));
  public static final MockMultipartFile FILE_1 =
      new MockMultipartFile(
          "files", "a.txt", MediaType.TEXT_PLAIN_VALUE, "alpha".getBytes(StandardCharsets.UTF_8));

  public static final MockMultipartFile TEST_FILE_1 = createFile("test1");
  public static final MockMultipartFile TEST_FILE_2 = createFile("test2");
  public static final MockMultipartFile TEST_FILE_3 = createFile("test3");
  public static final MockMultipartFile TEST_FILE_4 = createFile("test4");

  public static final List<MockMultipartFile> TEST_FILE_1_2 = List.of(TEST_FILE_1, TEST_FILE_2);
  public static final List<MockMultipartFile> TEST_FILE_3_4 = List.of(TEST_FILE_3, TEST_FILE_4);

  private static MockMultipartFile createFile(String name) {
    return new MockMultipartFile(name, new byte[0]);
  }
}
