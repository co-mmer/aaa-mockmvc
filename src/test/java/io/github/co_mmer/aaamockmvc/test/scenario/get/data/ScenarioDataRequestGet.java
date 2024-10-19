package io.github.co_mmer.aaamockmvc.test.scenario.get.data;

import static io.github.co_mmer.aaamockmvc.test.testdata.testutil.TestHeader.TEST_AUTH_KEY;
import static io.github.co_mmer.aaamockmvc.test.testdata.testutil.TestHeader.TEST_AUTH_VALUE;
import static io.github.co_mmer.aaamockmvc.test.testdata.testutil.TestHeader.TEST_HEADER_KEY_1;
import static io.github.co_mmer.aaamockmvc.test.testdata.testutil.TestHeader.TEST_HEADER_KEY_2;
import static io.github.co_mmer.aaamockmvc.test.testdata.testutil.TestHeader.TEST_HEADER_MAP_1_2;
import static io.github.co_mmer.aaamockmvc.test.testdata.testutil.TestHeader.TEST_HEADER_VALUE_1;
import static io.github.co_mmer.aaamockmvc.test.testdata.testutil.TestHeader.TEST_HEADER_VALUE_2;
import static io.github.co_mmer.aaamockmvc.test.testdata.testutil.TestParameter.TEST_PARAM_KEY_1;
import static io.github.co_mmer.aaamockmvc.test.testdata.testutil.TestParameter.TEST_PARAM_KEY_2;
import static io.github.co_mmer.aaamockmvc.test.testdata.testutil.TestParameter.TEST_PARAM_KEY_VALUE_MAP_1_2;
import static io.github.co_mmer.aaamockmvc.test.testdata.testutil.TestParameter.TEST_PARAM_VALUE_1;
import static io.github.co_mmer.aaamockmvc.test.testdata.testutil.TestParameter.TEST_PARAM_VALUE_2;
import static io.github.co_mmer.aaamockmvc.test.testdata.testutil.TestValue.TEST_URI;
import static io.github.co_mmer.aaamockmvc.test.testdata.testutil.TestValue.TEST_URI_VAR_1;
import static io.github.co_mmer.aaamockmvc.test.testdata.testutil.TestValue.TEST_URI_VAR_2;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_XML;

import io.github.co_mmer.aaamockmvc.test.testdata.testutil.TestRequestDtoBuilder;
import io.github.co_mmer.aaamockmvc.test.web.request.model.TestRequestDto;
import io.github.co_mmer.aaamockmvc.test.web.request.model.TestRequestType;
import java.util.Map;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ScenarioDataRequestGet {

  public static final TestRequestDto GET_REQUEST__URI =
      TestRequestDtoBuilder.builder()
          .withRequestType(TestRequestType.GET)
          .withUri(TEST_URI)
          .build();

  public static final TestRequestDto GET_REQUEST__URI_VARIABLE =
      TestRequestDtoBuilder.builder()
          .withRequestType(TestRequestType.GET)
          .withUri(TEST_URI_VAR_1)
          .build();

  public static final TestRequestDto GET_REQUEST__URI_VARIABLES =
      TestRequestDtoBuilder.builder()
          .withRequestType(TestRequestType.GET)
          .withUri(TEST_URI_VAR_2)
          .build();

  public static final TestRequestDto GET_REQUEST__PARAM_KEY_VALUE_1 =
      TestRequestDtoBuilder.builder()
          .withRequestType(TestRequestType.GET)
          .withUri(TEST_URI)
          .withParamKeyValue(TEST_PARAM_KEY_1, TEST_PARAM_VALUE_1)
          .build();

  public static final TestRequestDto GET_REQUEST__PARAM_KEY_VALUE_2 =
      TestRequestDtoBuilder.builder()
          .withRequestType(TestRequestType.GET)
          .withUri(TEST_URI)
          .withParamKeyValue(TEST_PARAM_KEY_1, TEST_PARAM_VALUE_1)
          .withParamKeyValue(TEST_PARAM_KEY_2, TEST_PARAM_VALUE_2)
          .build();

  public static final TestRequestDto GET_REQUEST__PARAM_KEY_VALUE_MAP =
      TestRequestDtoBuilder.builder()
          .withRequestType(TestRequestType.GET)
          .withUri(TEST_URI)
          .withParam(TEST_PARAM_KEY_VALUE_MAP_1_2)
          .build();

  public static final TestRequestDto GET_REQUEST__HEAD_KEY_VALUE_1 =
      TestRequestDtoBuilder.builder()
          .withRequestType(TestRequestType.GET)
          .withUri(TEST_URI)
          .withHeadKeyValue(Map.of(TEST_HEADER_KEY_1, TEST_HEADER_VALUE_1))
          .build();

  public static final TestRequestDto GET_REQUEST__HEAD_KEY_VALUE_2 =
      TestRequestDtoBuilder.builder()
          .withRequestType(TestRequestType.GET)
          .withUri(TEST_URI)
          .withHeadKeyValue(TEST_HEADER_KEY_1, TEST_HEADER_VALUE_1)
          .withHeadKeyValue(TEST_HEADER_KEY_2, TEST_HEADER_VALUE_2)
          .build();

  public static final TestRequestDto GET_REQUEST__HEAD_KEY_VALUE_MAP =
      TestRequestDtoBuilder.builder()
          .withRequestType(TestRequestType.GET)
          .withUri(TEST_URI)
          .withHeadKeyValue(TEST_HEADER_MAP_1_2)
          .build();

  public static final TestRequestDto GET_REQUEST__HEAD_AUTH =
      TestRequestDtoBuilder.builder()
          .withRequestType(TestRequestType.GET)
          .withUri(TEST_URI)
          .withHeadKeyValue(TEST_AUTH_KEY, TEST_AUTH_VALUE)
          .build();

  public static final TestRequestDto GET_REQUEST__HEAD_ACCEPT_1 =
      TestRequestDtoBuilder.builder()
          .withRequestType(TestRequestType.GET)
          .withUri(TEST_URI)
          .withHeadAccept(APPLICATION_JSON)
          .build();

  public static final TestRequestDto GET_REQUEST__HEAD_ACCEPT_2 =
      TestRequestDtoBuilder.builder()
          .withRequestType(TestRequestType.GET)
          .withUri(TEST_URI)
          .withHeadAccept(APPLICATION_XML)
          .withHeadAccept(APPLICATION_JSON)
          .build();

  public static final TestRequestDto GET_REQUEST__HEAD_CONTENT_TYPE_1 =
      TestRequestDtoBuilder.builder()
          .withRequestType(TestRequestType.GET)
          .withUri(TEST_URI)
          .withHeadContentType(APPLICATION_JSON)
          .build();

  public static final TestRequestDto GET_REQUEST__HEAD_CONTENT_TYPE_2 =
      TestRequestDtoBuilder.builder()
          .withRequestType(TestRequestType.GET)
          .withUri(TEST_URI)
          .withHeadContentType(APPLICATION_JSON)
          .withHeadContentType(APPLICATION_XML)
          .build();

  public static final TestRequestDto GET_REQUEST__HEAD_ACCEPT_1_AUTH =
      TestRequestDtoBuilder.builder()
          .withRequestType(TestRequestType.GET)
          .withUri(TEST_URI)
          .withHeadAccept(APPLICATION_JSON)
          .withHeadKeyValue(TEST_AUTH_KEY, TEST_AUTH_VALUE)
          .build();

  public static final TestRequestDto GET_REQUEST__HEAD_ACCEPT_1_CONTENT_TYPE_1 =
      TestRequestDtoBuilder.builder()
          .withRequestType(TestRequestType.GET)
          .withUri(TEST_URI)
          .withHeadAccept(APPLICATION_XML)
          .withHeadContentType(APPLICATION_JSON)
          .build();

  public static final TestRequestDto GET_REQUEST__HEAD_ACCEPT_1_KEY_VALUE_1 =
      TestRequestDtoBuilder.builder()
          .withRequestType(TestRequestType.GET)
          .withUri(TEST_URI)
          .withHeadAccept(APPLICATION_JSON)
          .withHeadKeyValue(TEST_HEADER_KEY_1, TEST_HEADER_VALUE_1)
          .build();

  public static final TestRequestDto GET_REQUEST__HEAD_ACCEPT_1_KEY_VALUE_2 =
      TestRequestDtoBuilder.builder()
          .withRequestType(TestRequestType.GET)
          .withUri(TEST_URI)
          .withHeadAccept(APPLICATION_JSON)
          .withHeadKeyValue(TEST_HEADER_KEY_1, TEST_HEADER_VALUE_1)
          .withHeadKeyValue(TEST_HEADER_KEY_2, TEST_HEADER_VALUE_2)
          .build();

  public static final TestRequestDto GET_REQUEST__HEAD_ACCEPT_1_KEY_VALUE_MAP =
      GET_REQUEST__HEAD_ACCEPT_1_KEY_VALUE_2;

  public static final TestRequestDto GET_REQUEST__HEAD_AUT_CONTENT_TYPE_1 =
      TestRequestDtoBuilder.builder()
          .withRequestType(TestRequestType.GET)
          .withUri(TEST_URI)
          .withHeadKeyValue(TEST_AUTH_KEY, TEST_AUTH_VALUE)
          .withHeadContentType(APPLICATION_JSON)
          .build();
  public static final TestRequestDto GET_REQUEST__HEAD_AUTH_KEY_VALUE_1 =
      TestRequestDtoBuilder.builder()
          .withRequestType(TestRequestType.GET)
          .withUri(TEST_URI)
          .withHeadKeyValue(TEST_AUTH_KEY, TEST_AUTH_VALUE)
          .withHeadKeyValue(TEST_HEADER_KEY_1, TEST_HEADER_VALUE_1)
          .build();

  public static final TestRequestDto GET_REQUEST__HEAD_AUTH_KEY_VALUE_2 =
      TestRequestDtoBuilder.builder()
          .withRequestType(TestRequestType.GET)
          .withUri(TEST_URI)
          .withHeadKeyValue(TEST_AUTH_KEY, TEST_AUTH_VALUE)
          .withHeadKeyValue(TEST_HEADER_KEY_1, TEST_HEADER_VALUE_1)
          .withHeadKeyValue(TEST_HEADER_KEY_2, TEST_HEADER_VALUE_2)
          .build();

  public static final TestRequestDto GET_REQUEST__HEAD_AUT_KEY_VALUE_MAP =
      GET_REQUEST__HEAD_AUTH_KEY_VALUE_2;
  public static final TestRequestDto GET_REQUEST__HEAD_CONTENT_TYPE_KEY_VALUE_1 =
      TestRequestDtoBuilder.builder()
          .withRequestType(TestRequestType.GET)
          .withUri(TEST_URI)
          .withHeadContentType(APPLICATION_XML)
          .withHeadKeyValue(TEST_HEADER_KEY_1, TEST_HEADER_VALUE_1)
          .build();

  public static final TestRequestDto GET_REQUEST__HEAD_CONTENT_TYPE_KEY_VALUE_2 =
      TestRequestDtoBuilder.builder()
          .withRequestType(TestRequestType.GET)
          .withUri(TEST_URI)
          .withHeadContentType(APPLICATION_XML)
          .withHeadKeyValue(TEST_HEADER_KEY_1, TEST_HEADER_VALUE_1)
          .withHeadKeyValue(TEST_HEADER_KEY_2, TEST_HEADER_VALUE_2)
          .build();

  public static final TestRequestDto GET_REQUEST__HEAD_CONTENT_TYPE_KEY_VALUE_MAP =
      GET_REQUEST__HEAD_CONTENT_TYPE_KEY_VALUE_2;
}
