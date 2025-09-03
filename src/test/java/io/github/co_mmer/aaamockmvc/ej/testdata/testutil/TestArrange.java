package io.github.co_mmer.aaamockmvc.ej.testdata.testutil;

import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestFiles.FILE_1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestFiles.FILE_2;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.BASE_URI;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.HEADER_KEY_AUTH;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.HEADER_VALUE_TOKEN;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.QUERY_KEY_PAGE;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.QUERY_KEY_SEARCH;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.QUERY_VALUE_PAGE_NUMBER;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.QUERY_VALUE_SEARCH_TERM;
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
          .withUri(BASE_URI)
          .withQueryParam(QUERY_KEY_SEARCH, QUERY_VALUE_SEARCH_TERM)
          .withQueryParam(QUERY_KEY_PAGE, QUERY_VALUE_PAGE_NUMBER)
          .withAccept(APPLICATION_JSON)
          .withAccept(APPLICATION_XML)
          .withContentType(APPLICATION_JSON)
          .withHeader(HEADER_KEY_AUTH, HEADER_VALUE_TOKEN)
          .build();

  public static final TestArrangeResult ARRANGE_PATCH_FILES =
      TestArrangeResultBuilder.builder()
          .withMethod(HttpMethod.PATCH)
          .withUri(BASE_URI)
          .withQueryParam(QUERY_KEY_SEARCH, QUERY_VALUE_SEARCH_TERM)
          .withQueryParam(QUERY_KEY_PAGE, QUERY_VALUE_PAGE_NUMBER)
          .withAccept(APPLICATION_JSON)
          .withAccept(APPLICATION_XML)
          .withHeader(HEADER_KEY_AUTH, HEADER_VALUE_TOKEN)
          .withFile(FILE_1)
          .withFile(FILE_2)
          .build();

  public static final TestArrangeResult ARRANGE_POST =
      TestArrangeResultBuilder.builder()
          .withMethod(HttpMethod.POST)
          .withUri(BASE_URI)
          .withQueryParam(QUERY_KEY_SEARCH, QUERY_VALUE_SEARCH_TERM)
          .withQueryParam(QUERY_KEY_PAGE, QUERY_VALUE_PAGE_NUMBER)
          .withAccept(APPLICATION_JSON)
          .withAccept(APPLICATION_XML)
          .withContentType(APPLICATION_JSON)
          .withHeader(HEADER_KEY_AUTH, HEADER_VALUE_TOKEN)
          .build();

  public static final TestArrangeResult ARRANGE_POST_FILES =
      TestArrangeResultBuilder.builder()
          .withMethod(HttpMethod.PUT)
          .withUri(BASE_URI)
          .withQueryParam(QUERY_KEY_SEARCH, QUERY_VALUE_SEARCH_TERM)
          .withQueryParam(QUERY_KEY_PAGE, QUERY_VALUE_PAGE_NUMBER)
          .withAccept(APPLICATION_JSON)
          .withAccept(APPLICATION_XML)
          .withHeader(HEADER_KEY_AUTH, HEADER_VALUE_TOKEN)
          .withFile(FILE_1)
          .withFile(FILE_2)
          .build();
  public static final TestArrangeResult ARRANGE_PUT =
      TestArrangeResultBuilder.builder()
          .withMethod(HttpMethod.POST)
          .withUri(BASE_URI)
          .withQueryParam(QUERY_KEY_SEARCH, QUERY_VALUE_SEARCH_TERM)
          .withQueryParam(QUERY_KEY_PAGE, QUERY_VALUE_PAGE_NUMBER)
          .withAccept(APPLICATION_JSON)
          .withAccept(APPLICATION_XML)
          .withContentType(APPLICATION_JSON)
          .withHeader(HEADER_KEY_AUTH, HEADER_VALUE_TOKEN)
          .build();

  public static final TestArrangeResult ARRANGE_PUT_FILES =
      TestArrangeResultBuilder.builder()
          .withMethod(HttpMethod.PUT)
          .withUri(BASE_URI)
          .withQueryParam(QUERY_KEY_SEARCH, QUERY_VALUE_SEARCH_TERM)
          .withQueryParam(QUERY_KEY_PAGE, QUERY_VALUE_PAGE_NUMBER)
          .withAccept(APPLICATION_JSON)
          .withAccept(APPLICATION_XML)
          .withHeader(HEADER_KEY_AUTH, HEADER_VALUE_TOKEN)
          .withFile(FILE_1)
          .withFile(FILE_2)
          .build();
}
