package io.github.co_mmer.aaamockmvc.ej.scenario.info.data;

import io.github.co_mmer.aaamockmvc.ej.test.web.act.TestAct1;
import io.github.co_mmer.aaamockmvc.ej.test.web.request.model.TestRequestDto;
import lombok.Getter;

@Getter
public enum ScenarioDataInfo {
  USE_CASE_01(ScenarioDataActInfo.HEAD_ARRANGE__URL, ScenarioDataRequestInfo.HEAD_REQUEST__URI),
  USE_CASE_02(
      ScenarioDataActInfo.HEAD_ARRANGE__URL_VARIABLE,
      ScenarioDataRequestInfo.HEAD_REQUEST__URI_VARIABLE),
  USE_CASE_03(
      ScenarioDataActInfo.HEAD_ARRANGE__URL_VARIABLES,
      ScenarioDataRequestInfo.HEAD_REQUEST__URI_VARIABLES),

  USE_CASE_04(
      ScenarioDataActInfo.HEAD_ARRANGE__PARAM_KEY_VALUE_1,
      ScenarioDataRequestInfo.HEAD_REQUEST__PARAM_KEY_VALUE_1),
  USE_CASE_05(
      ScenarioDataActInfo.HEAD_ARRANGE__PARAM_KEY_VALUE_2,
      ScenarioDataRequestInfo.HEAD_REQUEST__PARAM_KEY_VALUE_2),
  USE_CASE_06(
      ScenarioDataActInfo.HEAD_ARRANGE__PARAM_KEY_VALUE_MAP,
      ScenarioDataRequestInfo.HEAD_REQUEST__PARAM_KEY_VALUE_MAP),

  USE_CASE_07(
      ScenarioDataActInfo.HEAD_ARRANGE__HEAD_ACCEPT_1,
      ScenarioDataRequestInfo.HEAD_REQUEST__HEAD_ACCEPT_1),
  USE_CASE_08(
      ScenarioDataActInfo.HEAD_ARRANGE__HEAD_ACCEPT_2,
      ScenarioDataRequestInfo.HEAD_REQUEST__HEAD_ACCEPT_2),
  USE_CASE_09(
      ScenarioDataActInfo.HEAD_ARRANGE__HEAD_AUTH, ScenarioDataRequestInfo.HEAD_REQUEST__HEAD_AUTH),
  USE_CASE_10(
      ScenarioDataActInfo.HEAD_ARRANGE__HEAD_CONTENT_TYPE_1,
      ScenarioDataRequestInfo.HEAD_REQUEST__HEAD_CONTENT_TYPE_1),
  USE_CASE_11(
      ScenarioDataActInfo.HEAD_ARRANGE__HEAD_CONTENT_TYPE_2,
      ScenarioDataRequestInfo.HEAD_REQUEST__HEAD_CONTENT_TYPE_2),
  USE_CASE_12(
      ScenarioDataActInfo.HEAD_ARRANGE__HEAD_KEY_VALUE_1,
      ScenarioDataRequestInfo.HEAD_REQUEST__HEAD_KEY_VALUE_1),
  USE_CASE_13(
      ScenarioDataActInfo.HEAD_ARRANGE__HEAD_KEY_VALUE_2,
      ScenarioDataRequestInfo.HEAD_REQUEST__HEAD_KEY_VALUE_2),
  USE_CASE_14(
      ScenarioDataActInfo.HEAD_ARRANGE__HEAD_KEY_VALUE_MAP,
      ScenarioDataRequestInfo.HEAD_REQUEST__HEAD_KEY_VALUE_MAP),

  USE_CASE_15(
      ScenarioDataActInfo.HEAD_ARRANGE__HEAD_ACCEPT_1_AUTH,
      ScenarioDataRequestInfo.HEAD_REQUEST__HEAD_ACCEPT_1_AUTH),
  USE_CASE_16(
      ScenarioDataActInfo.HEAD_ARRANGE__HEAD_ACCEPT_1_CONTENT_TYPE_1,
      ScenarioDataRequestInfo.HEAD_REQUEST__HEAD_ACCEPT_1_CONTENT_TYPE_1),
  USE_CASE_17(
      ScenarioDataActInfo.HEAD_ARRANGE__HEAD_ACCEPT_1_KEY_VALUE_1,
      ScenarioDataRequestInfo.HEAD_REQUEST__HEAD_ACCEPT_1_KEY_VALUE_1),
  USE_CASE_18(
      ScenarioDataActInfo.HEAD_ARRANGE__HEAD_ACCEPT_1_KEY_VALUE_2,
      ScenarioDataRequestInfo.HEAD_REQUEST__HEAD_ACCEPT_1_KEY_VALUE_2),
  USE_CASE_19(
      ScenarioDataActInfo.HEAD_ARRANGE__HEAD_ACCEPT_1_KEY_VALUE_MAP,
      ScenarioDataRequestInfo.HEAD_REQUEST__HEAD_ACCEPT_1_KEY_VALUE_MAP),

  USE_CASE_20(
      ScenarioDataActInfo.HEAD_ARRANGE__HEAD_AUT_CONTENT_TYPE_1,
      ScenarioDataRequestInfo.HEAD_REQUEST__HEAD_AUT_CONTENT_TYPE_1),
  USE_CASE_21(
      ScenarioDataActInfo.HEAD_ARRANGE__HEAD_AUT_KEY_VALUE_1,
      ScenarioDataRequestInfo.HEAD_REQUEST__HEAD_AUTH_KEY_VALUE_1),
  USE_CASE_22(
      ScenarioDataActInfo.HEAD_ARRANGE__HEAD_AUT_KEY_VALUE_2,
      ScenarioDataRequestInfo.HEAD_REQUEST__HEAD_AUTH_KEY_VALUE_2),
  USE_CASE_23(
      ScenarioDataActInfo.HEAD_ARRANGE__HEAD_AUT_KEY_VALUE_MAP,
      ScenarioDataRequestInfo.HEAD_REQUEST__HEAD_AUT_KEY_VALUE_MAP),

  USE_CASE_24(
      ScenarioDataActInfo.HEAD_ARRANGE__HEAD_CONTENT_TYPE_1_KEY_VALUE_1,
      ScenarioDataRequestInfo.HEAD_REQUEST__HEAD_CONTENT_TYPE_1_KEY_VALUE_1),
  USE_CASE_25(
      ScenarioDataActInfo.HEAD_ARRANGE__HEAD_CONTENT_TYPE_1_KEY_VALUE_2,
      ScenarioDataRequestInfo.HEAD_REQUEST__HEAD_CONTENT_TYPE_1_KEY_VALUE_2),
  USE_CASE_26(
      ScenarioDataActInfo.HEAD_ARRANGE__HEAD_CONTENT_TYPE_1_KEY_VALUE_MAP,
      ScenarioDataRequestInfo.HEAD_REQUEST__HEAD_CONTENT_TYPE_1_KEY_VALUE_MAP);

  private final TestAct1 act;
  private final TestRequestDto requestDto;

  ScenarioDataInfo(TestAct1 act, TestRequestDto requestDto) {
    this.act = act;
    this.requestDto = requestDto;
  }
}
