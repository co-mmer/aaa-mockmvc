package ej.aaamockmvc.test.scenario.res.data;

import static ej.aaamockmvc.test.testdata.testutil.TestBody.TEST_BODY_JSON;
import static ej.aaamockmvc.test.testdata.testutil.TestBody.TEST_BODY_XML;
import static ej.aaamockmvc.test.testdata.testutil.TestFiles.TEST_FILE_1;
import static ej.aaamockmvc.test.testdata.testutil.TestFiles.TEST_FILE_2;
import static ej.aaamockmvc.test.testdata.testutil.TestFiles.TEST_FILE_3;
import static ej.aaamockmvc.test.testdata.testutil.TestFiles.TEST_FILE_4;
import static ej.aaamockmvc.test.testdata.testutil.TestHeader.TEST_AUTH_KEY;
import static ej.aaamockmvc.test.testdata.testutil.TestHeader.TEST_AUTH_VALUE;
import static ej.aaamockmvc.test.testdata.testutil.TestHeader.TEST_HEADER_KEY_1;
import static ej.aaamockmvc.test.testdata.testutil.TestHeader.TEST_HEADER_KEY_2;
import static ej.aaamockmvc.test.testdata.testutil.TestHeader.TEST_HEADER_MAP_1_2;
import static ej.aaamockmvc.test.testdata.testutil.TestHeader.TEST_HEADER_VALUE_1;
import static ej.aaamockmvc.test.testdata.testutil.TestHeader.TEST_HEADER_VALUE_2;
import static ej.aaamockmvc.test.testdata.testutil.TestParameter.TEST_PARAM_KEY_1;
import static ej.aaamockmvc.test.testdata.testutil.TestParameter.TEST_PARAM_KEY_2;
import static ej.aaamockmvc.test.testdata.testutil.TestParameter.TEST_PARAM_KEY_VALUE_MAP_1_2;
import static ej.aaamockmvc.test.testdata.testutil.TestParameter.TEST_PARAM_VALUE_1;
import static ej.aaamockmvc.test.testdata.testutil.TestParameter.TEST_PARAM_VALUE_2;
import static ej.aaamockmvc.test.testdata.testutil.TestValue.TEST_URI;
import static ej.aaamockmvc.test.testdata.testutil.TestValue.TEST_URI_VAR_1;
import static ej.aaamockmvc.test.testdata.testutil.TestValue.TEST_URI_VAR_2;
import static ej.aaamockmvc.test.web.request.model.TestRequestType.POST;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_PDF;
import static org.springframework.http.MediaType.APPLICATION_XML;

import ej.aaamockmvc.test.testdata.testutil.TestRequestDtoBuilder;
import ej.aaamockmvc.test.web.request.model.TestRequestDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ScenarioDataRequestPost {

  public static final TestRequestDto POST_REQUEST__PATH =
      TestRequestDtoBuilder.builder().withRequestType(POST).withUri(TEST_URI).build();

  public static final TestRequestDto POST_REQUEST__URI_VARIABLE =
      TestRequestDtoBuilder.builder().withRequestType(POST).withUri(TEST_URI_VAR_1).build();

  public static final TestRequestDto POST_REQUEST__URI_VARIABLES =
      TestRequestDtoBuilder.builder().withRequestType(POST).withUri(TEST_URI_VAR_2).build();

  public static final TestRequestDto POST_REQUEST__PARAM_KEY_VALUE_1 =
      TestRequestDtoBuilder.builder()
          .withRequestType(POST)
          .withUri(TEST_URI)
          .withParamKeyValue(TEST_PARAM_KEY_1, TEST_PARAM_VALUE_1)
          .build();

  public static final TestRequestDto POST_REQUEST__PARAM_KEY_VALUE_2 =
      TestRequestDtoBuilder.builder()
          .withRequestType(POST)
          .withUri(TEST_URI)
          .withParamKeyValue(TEST_PARAM_KEY_1, TEST_PARAM_VALUE_1)
          .withParamKeyValue(TEST_PARAM_KEY_2, TEST_PARAM_VALUE_2)
          .build();

  public static final TestRequestDto POST_REQUEST__PARAM_KEY_VALUE_MAP =
      TestRequestDtoBuilder.builder()
          .withRequestType(POST)
          .withUri(TEST_URI)
          .withParam(TEST_PARAM_KEY_VALUE_MAP_1_2)
          .build();

  public static final TestRequestDto POST_REQUEST__HEAD_ACCEPT_1 =
      TestRequestDtoBuilder.builder()
          .withRequestType(POST)
          .withUri(TEST_URI)
          .withHeadAccept(APPLICATION_JSON)
          .build();

  public static final TestRequestDto POST_REQUEST__HEAD_ACCEPT_2 =
      TestRequestDtoBuilder.builder()
          .withRequestType(POST)
          .withUri(TEST_URI)
          .withHeadAccept(APPLICATION_JSON)
          .withHeadAccept(APPLICATION_PDF)
          .build();

  public static final TestRequestDto POST_REQUEST__HEAD_AUTH =
      TestRequestDtoBuilder.builder()
          .withRequestType(POST)
          .withUri(TEST_URI)
          .withHeadKeyValue(TEST_AUTH_KEY, TEST_AUTH_VALUE)
          .build();

  public static final TestRequestDto POST_REQUEST__HEAD_CONTENT_TYPE_1 =
      TestRequestDtoBuilder.builder()
          .withRequestType(POST)
          .withUri(TEST_URI)
          .withHeadContentType(APPLICATION_JSON)
          .build();

  public static final TestRequestDto POST_REQUEST__HEAD_CONTENT_TYPE_2 =
      TestRequestDtoBuilder.builder()
          .withRequestType(POST)
          .withUri(TEST_URI)
          .withHeadContentType(APPLICATION_JSON)
          .withHeadContentType(APPLICATION_PDF)
          .build();

  public static final TestRequestDto POST_REQUEST__HEAD_KEY_VALUE_1 =
      TestRequestDtoBuilder.builder()
          .withRequestType(POST)
          .withUri(TEST_URI)
          .withHeadKeyValue(TEST_HEADER_KEY_1, TEST_HEADER_VALUE_1)
          .build();

  public static final TestRequestDto POST_REQUEST__HEAD_KEY_VALUE_2 =
      TestRequestDtoBuilder.builder()
          .withRequestType(POST)
          .withUri(TEST_URI)
          .withHeadKeyValue(TEST_HEADER_KEY_1, TEST_HEADER_VALUE_1)
          .withHeadKeyValue(TEST_HEADER_KEY_2, TEST_HEADER_VALUE_2)
          .build();

  public static final TestRequestDto POST_REQUEST__HEAD_KEY_VALUE_MAP =
      POST_REQUEST__HEAD_KEY_VALUE_2;

  public static final TestRequestDto POST_REQUEST__BODY_BINARY_FILE_1 =
      TestRequestDtoBuilder.builder()
          .withRequestType(POST)
          .withUri(TEST_URI)
          .withFile(TEST_FILE_1)
          .build();

  public static final TestRequestDto POST_REQUEST__BODY_BINARY_FILE_2 =
      TestRequestDtoBuilder.builder()
          .withRequestType(POST)
          .withUri(TEST_URI)
          .withFile(TEST_FILE_1)
          .withFile(TEST_FILE_2)
          .build();

  public static final TestRequestDto POST_REQUEST__BODY_BINARY_FILES =
      POST_REQUEST__BODY_BINARY_FILE_2;

  public static final TestRequestDto POST_REQUEST__BODY_BINARY_FILE_1_FILES =
      TestRequestDtoBuilder.builder()
          .withRequestType(POST)
          .withUri(TEST_URI)
          .withFile(TEST_FILE_1)
          .withFile(TEST_FILE_2)
          .withFile(TEST_FILE_3)
          .build();

  public static final TestRequestDto POST_REQUEST__BODY_BINARY_FILE_2_FILES =
      TestRequestDtoBuilder.builder()
          .withRequestType(POST)
          .withUri(TEST_URI)
          .withFile(TEST_FILE_1)
          .withFile(TEST_FILE_2)
          .withFile(TEST_FILE_3)
          .withFile(TEST_FILE_4)
          .build();

  public static final TestRequestDto POST_REQUEST__BODY_BINARY_FILES_FILES =
      POST_REQUEST__BODY_BINARY_FILE_2_FILES;

  public static final TestRequestDto POST_REQUEST__BODY_JSON =
      TestRequestDtoBuilder.builder()
          .withRequestType(POST)
          .withUri(TEST_URI)
          .withContent(TEST_BODY_JSON)
          .withContentType(APPLICATION_JSON)
          .build();

  public static final TestRequestDto POST_REQUEST__BODY_CONTENT_JSON = POST_REQUEST__BODY_JSON;

  public static final TestRequestDto POST_REQUEST__BODY_CONTENT_XML =
      TestRequestDtoBuilder.builder()
          .withRequestType(POST)
          .withUri(TEST_URI)
          .withContent(TEST_BODY_XML)
          .withContentType(APPLICATION_XML)
          .build();

  public static final TestRequestDto POST_REQUEST__HEAD_ACCEPT_1_AUTH =
      TestRequestDtoBuilder.builder()
          .withRequestType(POST)
          .withUri(TEST_URI)
          .withHeadAccept(APPLICATION_JSON)
          .withHeadKeyValue(TEST_AUTH_KEY, TEST_AUTH_VALUE)
          .build();

  public static final TestRequestDto POST_REQUEST__HEAD_ACCEPT_1_CONTENT_TYPE_1 =
      TestRequestDtoBuilder.builder()
          .withRequestType(POST)
          .withUri(TEST_URI)
          .withHeadAccept(APPLICATION_JSON)
          .withHeadContentType(APPLICATION_XML)
          .build();

  public static final TestRequestDto POST_REQUEST__HEAD_ACCEPT_1_KEY_VALUE_1 =
      TestRequestDtoBuilder.builder()
          .withRequestType(POST)
          .withUri(TEST_URI)
          .withHeadAccept(APPLICATION_JSON)
          .withHeadKeyValue(TEST_HEADER_KEY_1, TEST_HEADER_VALUE_1)
          .build();

  public static final TestRequestDto POST_REQUEST__HEAD_ACCEPT_1_KEY_VALUE_2 =
      TestRequestDtoBuilder.builder()
          .withRequestType(POST)
          .withUri(TEST_URI)
          .withHeadAccept(APPLICATION_JSON)
          .withHeadKeyValue(TEST_HEADER_KEY_1, TEST_HEADER_VALUE_1)
          .withHeadKeyValue(TEST_HEADER_KEY_2, TEST_HEADER_VALUE_2)
          .build();

  public static final TestRequestDto POST_REQUEST__HEAD_ACCEPT_1_KEY_VALUE_MAP =
      POST_REQUEST__HEAD_ACCEPT_1_KEY_VALUE_2;

  public static final TestRequestDto POST_REQUEST__HEAD_AUT_CONTENT_TYPE_1 =
      TestRequestDtoBuilder.builder()
          .withRequestType(POST)
          .withUri(TEST_URI)
          .withHeadKeyValue(TEST_AUTH_KEY, TEST_AUTH_VALUE)
          .withHeadContentType(APPLICATION_JSON)
          .build();
  public static final TestRequestDto POST_REQUEST__HEAD_AUTH_KEY_VALUE_1 =
      TestRequestDtoBuilder.builder()
          .withRequestType(POST)
          .withUri(TEST_URI)
          .withHeadKeyValue(TEST_AUTH_KEY, TEST_AUTH_VALUE)
          .withHeadKeyValue(TEST_HEADER_KEY_1, TEST_HEADER_VALUE_1)
          .build();

  public static final TestRequestDto POST_REQUEST__HEAD_AUTH_KEY_VALUE_2 =
      TestRequestDtoBuilder.builder()
          .withRequestType(POST)
          .withUri(TEST_URI)
          .withHeadKeyValue(TEST_AUTH_KEY, TEST_AUTH_VALUE)
          .withHeadKeyValue(TEST_HEADER_KEY_1, TEST_HEADER_VALUE_1)
          .withHeadKeyValue(TEST_HEADER_KEY_2, TEST_HEADER_VALUE_2)
          .build();

  public static final TestRequestDto POST_REQUEST__HEAD_AUT_KEY_VALUE_MAP =
      POST_REQUEST__HEAD_AUTH_KEY_VALUE_2;

  public static final TestRequestDto POST_REQUEST__HEAD_CONTENT_TYPE_KEY_VALUE_1 =
      TestRequestDtoBuilder.builder()
          .withRequestType(POST)
          .withUri(TEST_URI)
          .withHeadContentType(APPLICATION_XML)
          .withHeadKeyValue(TEST_HEADER_KEY_1, TEST_HEADER_VALUE_1)
          .build();

  public static final TestRequestDto POST_REQUEST__HEAD_CONTENT_TYPE_KEY_VALUE_2 =
      TestRequestDtoBuilder.builder()
          .withRequestType(POST)
          .withUri(TEST_URI)
          .withHeadContentType(APPLICATION_XML)
          .withHeadKeyValue(TEST_HEADER_KEY_1, TEST_HEADER_VALUE_1)
          .withHeadKeyValue(TEST_HEADER_KEY_2, TEST_HEADER_VALUE_2)
          .build();

  public static final TestRequestDto POST_REQUEST__HEAD_CONTENT_TYPE_KEY_VALUE_MAP =
      TestRequestDtoBuilder.builder()
          .withRequestType(POST)
          .withUri(TEST_URI)
          .withHeadContentType(APPLICATION_XML)
          .withHeadKeyValue(TEST_HEADER_MAP_1_2)
          .build();
}
