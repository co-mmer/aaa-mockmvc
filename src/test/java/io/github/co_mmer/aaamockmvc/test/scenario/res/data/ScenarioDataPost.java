package io.github.co_mmer.aaamockmvc.test.scenario.res.data;

import io.github.co_mmer.aaamockmvc.test.web.act.TestAct1;
import io.github.co_mmer.aaamockmvc.test.web.request.model.TestRequestDto;
import lombok.Getter;

@Getter
public enum ScenarioDataPost {
  USE_CASE_01(ScenarioDataActPost.POST_ARRANGE__URL, ScenarioDataRequestPost.POST_REQUEST__PATH),
  USE_CASE_02(
      ScenarioDataActPost.POST_ARRANGE__URL_VARIABLE,
      ScenarioDataRequestPost.POST_REQUEST__URI_VARIABLE),
  USE_CASE_03(
      ScenarioDataActPost.POST_ARRANGE__URL_VARIABLES,
      ScenarioDataRequestPost.POST_REQUEST__URI_VARIABLES),

  USE_CASE_04(
      ScenarioDataActPost.POST_ARRANGE__PARAM_KEY_VALUE_1,
      ScenarioDataRequestPost.POST_REQUEST__PARAM_KEY_VALUE_1),
  USE_CASE_05(
      ScenarioDataActPost.POST_ARRANGE__PARAM_KEY_VALUE_2,
      ScenarioDataRequestPost.POST_REQUEST__PARAM_KEY_VALUE_2),
  USE_CASE_06(
      ScenarioDataActPost.POST_ARRANGE__PARAM_KEY_VALUE_MAP,
      ScenarioDataRequestPost.POST_REQUEST__PARAM_KEY_VALUE_MAP),

  USE_CASE_07(
      ScenarioDataActPost.POST_ARRANGE__HEAD_ACCEPT_1,
      ScenarioDataRequestPost.POST_REQUEST__HEAD_ACCEPT_1),
  USE_CASE_08(
      ScenarioDataActPost.POST_ARRANGE__HEAD_ACCEPT_2,
      ScenarioDataRequestPost.POST_REQUEST__HEAD_ACCEPT_2),
  USE_CASE_09(
      ScenarioDataActPost.POST_ARRANGE__HEAD_AUTH, ScenarioDataRequestPost.POST_REQUEST__HEAD_AUTH),
  USE_CASE_10(
      ScenarioDataActPost.POST_ARRANGE__HEAD_CONTENT_TYPE_1,
      ScenarioDataRequestPost.POST_REQUEST__HEAD_CONTENT_TYPE_1),
  USE_CASE_11(
      ScenarioDataActPost.POST_ARRANGE__HEAD_CONTENT_TYPE_2,
      ScenarioDataRequestPost.POST_REQUEST__HEAD_CONTENT_TYPE_2),
  USE_CASE_12(
      ScenarioDataActPost.POST_ARRANGE__HEAD_KEY_VALUE_1,
      ScenarioDataRequestPost.POST_REQUEST__HEAD_KEY_VALUE_1),
  USE_CASE_13(
      ScenarioDataActPost.POST_ARRANGE__HEAD_KEY_VALUE_2,
      ScenarioDataRequestPost.POST_REQUEST__HEAD_KEY_VALUE_2),
  USE_CASE_14(
      ScenarioDataActPost.POST_ARRANGE__HEAD_KEY_VALUE_MAP,
      ScenarioDataRequestPost.POST_REQUEST__HEAD_KEY_VALUE_MAP),

  USE_CASE_15(
      ScenarioDataActPost.POST_ARRANGE__BODY_BINARY_FILE_1,
      ScenarioDataRequestPost.POST_REQUEST__BODY_BINARY_FILE_1),
  USE_CASE_16(
      ScenarioDataActPost.POST_ARRANGE__BODY_BINARY_FILE_2,
      ScenarioDataRequestPost.POST_REQUEST__BODY_BINARY_FILE_2),
  USE_CASE_17(
      ScenarioDataActPost.POST_ARRANGE__BODY_BINARY_FILES,
      ScenarioDataRequestPost.POST_REQUEST__BODY_BINARY_FILES),

  USE_CASE_18(
      ScenarioDataActPost.POST_ARRANGE__BODY_BINARY_FILE_1_FILES,
      ScenarioDataRequestPost.POST_REQUEST__BODY_BINARY_FILE_1_FILES),
  USE_CASE_19(
      ScenarioDataActPost.POST_ARRANGE__BODY_BINARY_FILE_2_FILES,
      ScenarioDataRequestPost.POST_REQUEST__BODY_BINARY_FILE_2_FILES),
  USE_CASE_20(
      ScenarioDataActPost.POST_ARRANGE__BODY_BINARY_FILES_FILES,
      ScenarioDataRequestPost.POST_REQUEST__BODY_BINARY_FILES_FILES),

  USE_CASE_21(
      ScenarioDataActPost.POST_ARRANGE__BODY_JSON, ScenarioDataRequestPost.POST_REQUEST__BODY_JSON),
  USE_CASE_22(
      ScenarioDataActPost.POST_ARRANGE__BODY_CONTENT_JSON,
      ScenarioDataRequestPost.POST_REQUEST__BODY_CONTENT_JSON),
  USE_CASE_23(
      ScenarioDataActPost.POST_ARRANGE__BODY_CONTENT_XML,
      ScenarioDataRequestPost.POST_REQUEST__BODY_CONTENT_XML),

  USE_CASE_24(
      ScenarioDataActPost.POST_ARRANGE__HEAD_ACCEPT_1_AUTH,
      ScenarioDataRequestPost.POST_REQUEST__HEAD_ACCEPT_1_AUTH),
  USE_CASE_25(
      ScenarioDataActPost.POST_ARRANGE__HEAD_ACCEPT_1_CONTENT_TYPE_1,
      ScenarioDataRequestPost.POST_REQUEST__HEAD_ACCEPT_1_CONTENT_TYPE_1),
  USE_CASE_26(
      ScenarioDataActPost.POST_ARRANGE__HEAD_ACCEPT_1_KEY_VALUE_1,
      ScenarioDataRequestPost.POST_REQUEST__HEAD_ACCEPT_1_KEY_VALUE_1),
  USE_CASE_27(
      ScenarioDataActPost.POST_ARRANGE__HEAD_ACCEPT_1_KEY_VALUE_2,
      ScenarioDataRequestPost.POST_REQUEST__HEAD_ACCEPT_1_KEY_VALUE_2),
  USE_CASE_28(
      ScenarioDataActPost.POST_ARRANGE__HEAD_ACCEPT_1_KEY_VALUE_MAP,
      ScenarioDataRequestPost.POST_REQUEST__HEAD_ACCEPT_1_KEY_VALUE_MAP),

  USE_CASE_29(
      ScenarioDataActPost.POST_ARRANGE__HEAD_AUT_CONTENT_TYPE_1,
      ScenarioDataRequestPost.POST_REQUEST__HEAD_AUT_CONTENT_TYPE_1),
  USE_CASE_30(
      ScenarioDataActPost.POST_ARRANGE__HEAD_AUT_KEY_VALUE_1,
      ScenarioDataRequestPost.POST_REQUEST__HEAD_AUTH_KEY_VALUE_1),
  USE_CASE_31(
      ScenarioDataActPost.POST_ARRANGE__HEAD_AUT_KEY_VALUE_2,
      ScenarioDataRequestPost.POST_REQUEST__HEAD_AUTH_KEY_VALUE_2),
  USE_CASE_32(
      ScenarioDataActPost.POST_ARRANGE__HEAD_AUT_KEY_VALUE_MAP,
      ScenarioDataRequestPost.POST_REQUEST__HEAD_AUT_KEY_VALUE_MAP),

  USE_CASE_33(
      ScenarioDataActPost.POST_ARRANGE__HEAD_CONTENT_TYPE_KEY_VALUE_1,
      ScenarioDataRequestPost.POST_REQUEST__HEAD_CONTENT_TYPE_KEY_VALUE_1),
  USE_CASE_34(
      ScenarioDataActPost.POST_ARRANGE__HEAD_CONTENT_TYPE_KEY_VALUE_2,
      ScenarioDataRequestPost.POST_REQUEST__HEAD_CONTENT_TYPE_KEY_VALUE_2),
  USE_CASE_35(
      ScenarioDataActPost.POST_ARRANGE__HEAD_CONTENT_TYPE_KEY_VALUE_MAP,
      ScenarioDataRequestPost.POST_REQUEST__HEAD_CONTENT_TYPE_KEY_VALUE_MAP);

  private final TestAct1 act;
  private final TestRequestDto requestDto;

  ScenarioDataPost(TestAct1 act, TestRequestDto requestDto) {
    this.act = act;
    this.requestDto = requestDto;
  }
}
