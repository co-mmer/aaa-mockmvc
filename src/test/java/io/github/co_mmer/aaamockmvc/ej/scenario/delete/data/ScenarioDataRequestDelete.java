package io.github.co_mmer.aaamockmvc.ej.scenario.delete.data;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_XML;

import io.github.co_mmer.aaamockmvc.ej.test.web.request.model.TestRequestDto;
import io.github.co_mmer.aaamockmvc.ej.test.web.request.model.TestRequestType;
import io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestHeader;
import io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestParameter;
import io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestRequestDtoBuilder;
import io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue;
import java.util.Map;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ScenarioDataRequestDelete {

  public static final TestRequestDto DEL_REQUEST__URI =
      TestRequestDtoBuilder.builder()
          .withRequestType(TestRequestType.DELETE)
          .withUri(TestValue.TEST_URI)
          .build();

  public static final TestRequestDto DEL_REQUEST__URI_VARIABLE =
      TestRequestDtoBuilder.builder()
          .withRequestType(TestRequestType.DELETE)
          .withUri(TestValue.TEST_URI_VAR_1)
          .build();

  public static final TestRequestDto DEL_REQUEST__URI_VARIABLES =
      TestRequestDtoBuilder.builder()
          .withRequestType(TestRequestType.DELETE)
          .withUri(TestValue.TEST_URI_VAR_2)
          .build();

  public static final TestRequestDto DEL_REQUEST__PARAM_KEY_VALUE_1 =
      TestRequestDtoBuilder.builder()
          .withRequestType(TestRequestType.DELETE)
          .withUri(TestValue.TEST_URI)
          .withParamKeyValue(TestParameter.TEST_PARAM_KEY_1, TestParameter.TEST_PARAM_VALUE_1)
          .build();

  public static final TestRequestDto DEL_REQUEST__PARAM_KEY_VALUE_2 =
      TestRequestDtoBuilder.builder()
          .withRequestType(TestRequestType.DELETE)
          .withUri(TestValue.TEST_URI)
          .withParamKeyValue(TestParameter.TEST_PARAM_KEY_1, TestParameter.TEST_PARAM_VALUE_1)
          .withParamKeyValue(TestParameter.TEST_PARAM_KEY_2, TestParameter.TEST_PARAM_VALUE_2)
          .build();

  public static final TestRequestDto DEL_REQUEST__PARAM_KEY_VALUE_MAP =
      TestRequestDtoBuilder.builder()
          .withRequestType(TestRequestType.DELETE)
          .withUri(TestValue.TEST_URI)
          .withParam(TestParameter.TEST_PARAM_KEY_VALUE_MAP_1_2)
          .build();

  public static final TestRequestDto DEL_REQUEST__HEAD_AUTH =
      TestRequestDtoBuilder.builder()
          .withRequestType(TestRequestType.DELETE)
          .withUri(TestValue.TEST_URI)
          .withHeadKeyValue(TestHeader.TEST_AUTH_KEY, TestHeader.TEST_AUTH_VALUE)
          .build();

  public static final TestRequestDto DEL_REQUEST__HEAD_ACCEPT_1 =
      TestRequestDtoBuilder.builder()
          .withRequestType(TestRequestType.DELETE)
          .withUri(TestValue.TEST_URI)
          .withHeadAccept(APPLICATION_JSON)
          .build();

  public static final TestRequestDto DEL_REQUEST__HEAD_ACCEPT_2 =
      TestRequestDtoBuilder.builder()
          .withRequestType(TestRequestType.DELETE)
          .withUri(TestValue.TEST_URI)
          .withHeadAccept(APPLICATION_XML)
          .withHeadAccept(APPLICATION_JSON)
          .build();

  public static final TestRequestDto DEL_REQUEST__HEAD_KEY_VALUE_1 =
      TestRequestDtoBuilder.builder()
          .withRequestType(TestRequestType.DELETE)
          .withUri(TestValue.TEST_URI)
          .withHeadKeyValue(Map.of(TestHeader.TEST_HEADER_KEY_1, TestHeader.TEST_HEADER_VALUE_1))
          .build();

  public static final TestRequestDto DEL_REQUEST__HEAD_KEY_VALUE_2 =
      TestRequestDtoBuilder.builder()
          .withRequestType(TestRequestType.DELETE)
          .withUri(TestValue.TEST_URI)
          .withHeadKeyValue(TestHeader.TEST_HEADER_KEY_1, TestHeader.TEST_HEADER_VALUE_1)
          .withHeadKeyValue(TestHeader.TEST_HEADER_KEY_2, TestHeader.TEST_HEADER_VALUE_2)
          .build();

  public static final TestRequestDto DEL_REQUEST__HEAD_KEY_VALUE_MAP =
      TestRequestDtoBuilder.builder()
          .withRequestType(TestRequestType.DELETE)
          .withUri(TestValue.TEST_URI)
          .withHeadKeyValue(TestHeader.TEST_HEADER_MAP_1_2)
          .build();

  public static final TestRequestDto DEL_REQUEST__HEAD_ACCEPT_1_AUTH =
      TestRequestDtoBuilder.builder()
          .withRequestType(TestRequestType.DELETE)
          .withUri(TestValue.TEST_URI)
          .withHeadAccept(APPLICATION_JSON)
          .withHeadKeyValue(TestHeader.TEST_AUTH_KEY, TestHeader.TEST_AUTH_VALUE)
          .build();

  public static final TestRequestDto DEL_REQUEST__HEAD_ACCEPT_1_KEY_VALUE_1 =
      TestRequestDtoBuilder.builder()
          .withRequestType(TestRequestType.DELETE)
          .withUri(TestValue.TEST_URI)
          .withHeadAccept(APPLICATION_JSON)
          .withHeadKeyValue(TestHeader.TEST_HEADER_KEY_1, TestHeader.TEST_HEADER_VALUE_1)
          .build();

  public static final TestRequestDto DEL_REQUEST__HEAD_ACCEPT_1_KEY_VALUE_2 =
      TestRequestDtoBuilder.builder()
          .withRequestType(TestRequestType.DELETE)
          .withUri(TestValue.TEST_URI)
          .withHeadAccept(APPLICATION_JSON)
          .withHeadKeyValue(TestHeader.TEST_HEADER_KEY_1, TestHeader.TEST_HEADER_VALUE_1)
          .withHeadKeyValue(TestHeader.TEST_HEADER_KEY_2, TestHeader.TEST_HEADER_VALUE_2)
          .build();

  public static final TestRequestDto DEL_REQUEST__HEAD_AUTH_KEY_VALUE_1 =
      TestRequestDtoBuilder.builder()
          .withRequestType(TestRequestType.DELETE)
          .withUri(TestValue.TEST_URI)
          .withHeadKeyValue(TestHeader.TEST_AUTH_KEY, TestHeader.TEST_AUTH_VALUE)
          .withHeadKeyValue(TestHeader.TEST_HEADER_KEY_1, TestHeader.TEST_HEADER_VALUE_1)
          .build();

  public static final TestRequestDto DEL_REQUEST__HEAD_AUTH_KEY_VALUE_2 =
      TestRequestDtoBuilder.builder()
          .withRequestType(TestRequestType.DELETE)
          .withUri(TestValue.TEST_URI)
          .withHeadKeyValue(TestHeader.TEST_AUTH_KEY, TestHeader.TEST_AUTH_VALUE)
          .withHeadKeyValue(TestHeader.TEST_HEADER_KEY_1, TestHeader.TEST_HEADER_VALUE_1)
          .withHeadKeyValue(TestHeader.TEST_HEADER_KEY_2, TestHeader.TEST_HEADER_VALUE_2)
          .build();

  public static final TestRequestDto DEL_REQUEST__HEAD_AUT_KEY_VALUE_MAP =
      DEL_REQUEST__HEAD_AUTH_KEY_VALUE_2;
  public static final TestRequestDto DEL_REQUEST__HEAD_ACCEPT_1_KEY_VALUE_MAP =
      DEL_REQUEST__HEAD_ACCEPT_1_KEY_VALUE_2;
}
