package io.github.co_mmer.aaamockmvc.ej.testdata.testutil;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.request.MultipartBody;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.request.MultipartPart;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
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
    return new MockMultipartFile(name, name, "text/plain", "".getBytes());
  }

  // ---

  public static final MultipartBody MULTIPART_FILE_1_2 = createFile(TEST_FILE_1, TEST_FILE_2);

  private static MultipartBody createFile(MockMultipartFile... part) {
    var b = new MultipartBody();
    Arrays.stream(part).forEach(d -> b.add(createMultipartPart(d)));
    return b;
  }

  @SneakyThrows
  private static MultipartPart createMultipartPart(MockMultipartFile file) {
    return new MultipartPart(
        file.getName(), file.getOriginalFilename(), file.getContentType(), file.getBytes());
  }
}
