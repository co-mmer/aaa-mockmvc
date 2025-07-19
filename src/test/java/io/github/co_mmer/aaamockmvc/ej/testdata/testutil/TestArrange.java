package io.github.co_mmer.aaamockmvc.ej.testdata.testutil;

import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestFiles.FILE_1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestFiles.FILE_2;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.QUERY_KEY_1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.QUERY_KEY_2;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.QUERY_VALUE_1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.QUERY_VALUE_2;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.TEST_HEAD_KEY_1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.TEST_HEAD_VALUE_1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.TEST_URI;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_XML;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestArrangeResult;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class TestArrange {

  public static final TestArrangeResult ARRANGE_PATCH =
      TestArrangeResultBuilder.builder()
          .withMethod(HttpMethod.PATCH)
          .withUri(TEST_URI)
          .withQueryParam(QUERY_KEY_1, QUERY_VALUE_1)
          .withQueryParam(QUERY_KEY_2, QUERY_VALUE_2)
          .withAccept(APPLICATION_JSON)
          .withAccept(APPLICATION_XML)
          .withContentType(APPLICATION_JSON)
          .withHeader(TEST_HEAD_KEY_1, TEST_HEAD_VALUE_1)
          .build();

  public static final TestArrangeResult ARRANGE_PATCH_FILES =
      TestArrangeResultBuilder.builder()
          .withMethod(HttpMethod.PATCH)
          .withUri(TEST_URI)
          .withQueryParam(QUERY_KEY_1, QUERY_VALUE_1)
          .withQueryParam(QUERY_KEY_2, QUERY_VALUE_2)
          .withAccept(APPLICATION_JSON)
          .withAccept(APPLICATION_XML)
          .withHeader(TEST_HEAD_KEY_1, TEST_HEAD_VALUE_1)
          .withFile(FILE_1)
          .withFile(FILE_2)
          .build();

  public static final TestArrangeResult ARRANGE_POST =
      TestArrangeResultBuilder.builder()
          .withMethod(HttpMethod.POST)
          .withUri(TEST_URI)
          .withQueryParam(QUERY_KEY_1, QUERY_VALUE_1)
          .withQueryParam(QUERY_KEY_2, QUERY_VALUE_2)
          .withAccept(APPLICATION_JSON)
          .withAccept(APPLICATION_XML)
          .withContentType(APPLICATION_JSON)
          .withHeader(TEST_HEAD_KEY_1, TEST_HEAD_VALUE_1)
          .build();

  public static final TestArrangeResult ARRANGE_POST_FILES =
      TestArrangeResultBuilder.builder()
          .withMethod(HttpMethod.PUT)
          .withUri(TEST_URI)
          .withQueryParam(QUERY_KEY_1, QUERY_VALUE_1)
          .withQueryParam(QUERY_KEY_2, QUERY_VALUE_2)
          .withAccept(APPLICATION_JSON)
          .withAccept(APPLICATION_XML)
          .withHeader(TEST_HEAD_KEY_1, TEST_HEAD_VALUE_1)
          .withFile(FILE_1)
          .withFile(FILE_2)
          .build();
  public static final TestArrangeResult ARRANGE_PUT =
      TestArrangeResultBuilder.builder()
          .withMethod(HttpMethod.POST)
          .withUri(TEST_URI)
          .withQueryParam(QUERY_KEY_1, QUERY_VALUE_1)
          .withQueryParam(QUERY_KEY_2, QUERY_VALUE_2)
          .withAccept(APPLICATION_JSON)
          .withAccept(APPLICATION_XML)
          .withContentType(APPLICATION_JSON)
          .withHeader(TEST_HEAD_KEY_1, TEST_HEAD_VALUE_1)
          .build();

  public static final TestArrangeResult ARRANGE_PUT_FILES =
      TestArrangeResultBuilder.builder()
          .withMethod(HttpMethod.PUT)
          .withUri(TEST_URI)
          .withQueryParam(QUERY_KEY_1, QUERY_VALUE_1)
          .withQueryParam(QUERY_KEY_2, QUERY_VALUE_2)
          .withAccept(APPLICATION_JSON)
          .withAccept(APPLICATION_XML)
          .withHeader(TEST_HEAD_KEY_1, TEST_HEAD_VALUE_1)
          .withFile(FILE_1)
          .withFile(FILE_2)
          .build();
}
