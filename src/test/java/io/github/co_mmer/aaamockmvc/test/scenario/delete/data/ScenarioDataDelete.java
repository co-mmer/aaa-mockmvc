package io.github.co_mmer.aaamockmvc.test.scenario.delete.data;

import io.github.co_mmer.aaamockmvc.test.web.act.TestAct1;
import io.github.co_mmer.aaamockmvc.test.web.request.model.TestRequestDto;
import lombok.Getter;

@Getter
public enum ScenarioDataDelete {
  USE_CASE_01(ScenarioDataActDelete.DEL_ARRANGE__URL, ScenarioDataRequestDelete.DEL_REQUEST__URI),
  USE_CASE_02(
      ScenarioDataActDelete.DEL_ARRANGE__URL_VARIABLE,
      ScenarioDataRequestDelete.DEL_REQUEST__URI_VARIABLE),
  USE_CASE_03(
      ScenarioDataActDelete.DEL_ARRANGE__URL_VARIABLES,
      ScenarioDataRequestDelete.DEL_REQUEST__URI_VARIABLES),

  USE_CASE_04(
      ScenarioDataActDelete.DEL_ARRANGE__PARAM_KEY_VALUE_1,
      ScenarioDataRequestDelete.DEL_REQUEST__PARAM_KEY_VALUE_1),
  USE_CASE_05(
      ScenarioDataActDelete.DEL_ARRANGE__PARAM_KEY_VALUE_2,
      ScenarioDataRequestDelete.DEL_REQUEST__PARAM_KEY_VALUE_2),
  USE_CASE_06(
      ScenarioDataActDelete.DEL_ARRANGE__PARAM_KEY_VALUE_MAP,
      ScenarioDataRequestDelete.DEL_REQUEST__PARAM_KEY_VALUE_MAP),

  USE_CASE_07(
      ScenarioDataActDelete.DEL_ARRANGE__HEAD_ACCEPT_1,
      ScenarioDataRequestDelete.DEL_REQUEST__HEAD_ACCEPT_1),
  USE_CASE_08(
      ScenarioDataActDelete.DEL_ARRANGE__HEAD_ACCEPT_2,
      ScenarioDataRequestDelete.DEL_REQUEST__HEAD_ACCEPT_2),
  USE_CASE_09(
      ScenarioDataActDelete.DEL_ARRANGE__HEAD_AUTH,
      ScenarioDataRequestDelete.DEL_REQUEST__HEAD_AUTH),
  USE_CASE_10(
      ScenarioDataActDelete.DEL_ARRANGE__HEAD_CONTENT_TYPE_1,
      ScenarioDataRequestDelete.DEL_REQUEST__HEAD_CONTENT_TYPE_1),
  USE_CASE_11(
      ScenarioDataActDelete.DEL_ARRANGE__HEAD_CONTENT_TYPE_2,
      ScenarioDataRequestDelete.DEL_REQUEST__HEAD_CONTENT_TYPE_2),
  USE_CASE_12(
      ScenarioDataActDelete.DEL_ARRANGE__HEAD_KEY_VALUE_1,
      ScenarioDataRequestDelete.DEL_REQUEST__HEAD_KEY_VALUE_1),
  USE_CASE_13(
      ScenarioDataActDelete.DEL_ARRANGE__HEAD_KEY_VALUE_2,
      ScenarioDataRequestDelete.DEL_REQUEST__HEAD_KEY_VALUE_2),
  USE_CASE_14(
      ScenarioDataActDelete.DEL_ARRANGE__HEAD_KEY_VALUE_MAP,
      ScenarioDataRequestDelete.DEL_REQUEST__HEAD_KEY_VALUE_MAP),

  USE_CASE_15(
      ScenarioDataActDelete.DEL_ARRANGE__HEAD_ACCEPT_1_AUTH,
      ScenarioDataRequestDelete.DEL_REQUEST__HEAD_ACCEPT_1_AUTH),
  USE_CASE_16(
      ScenarioDataActDelete.DEL_ARRANGE__HEAD_ACCEPT_1_CONTENT_TYPE_1,
      ScenarioDataRequestDelete.DEL_REQUEST__HEAD_ACCEPT_1_CONTENT_TYPE_1),
  USE_CASE_17(
      ScenarioDataActDelete.DEL_ARRANGE__HEAD_ACCEPT_1_KEY_VALUE_1,
      ScenarioDataRequestDelete.DEL_REQUEST__HEAD_ACCEPT_1_KEY_VALUE_1),
  USE_CASE_18(
      ScenarioDataActDelete.DEL_ARRANGE__HEAD_ACCEPT_1_KEY_VALUE_2,
      ScenarioDataRequestDelete.DEL_REQUEST__HEAD_ACCEPT_1_KEY_VALUE_2),
  USE_CASE_19(
      ScenarioDataActDelete.DEL_ARRANGE__HEAD_ACCEPT_1_KEY_VALUE_MAP,
      ScenarioDataRequestDelete.DEL_REQUEST__HEAD_ACCEPT_1_KEY_VALUE_MAP),

  USE_CASE_20(
      ScenarioDataActDelete.DEL_ARRANGE__HEAD_AUT_CONTENT_TYPE_1,
      ScenarioDataRequestDelete.DEL_REQUEST__HEAD_AUT_CONTENT_TYPE_1),
  USE_CASE_21(
      ScenarioDataActDelete.DEL_ARRANGE__HEAD_AUT_KEY_VALUE_1,
      ScenarioDataRequestDelete.DEL_REQUEST__HEAD_AUTH_KEY_VALUE_1),
  USE_CASE_22(
      ScenarioDataActDelete.DEL_ARRANGE__HEAD_AUT_KEY_VALUE_2,
      ScenarioDataRequestDelete.DEL_REQUEST__HEAD_AUTH_KEY_VALUE_2),
  USE_CASE_23(
      ScenarioDataActDelete.DEL_ARRANGE__HEAD_AUT_KEY_VALUE_MAP,
      ScenarioDataRequestDelete.DEL_REQUEST__HEAD_AUT_KEY_VALUE_MAP),

  USE_CASE_24(
      ScenarioDataActDelete.DEL_ARRANGE__HEAD_CONTENT_TYPE_KEY_VALUE_1,
      ScenarioDataRequestDelete.DEL_REQUEST__HEAD_CONTENT_TYPE_KEY_VALUE_1),
  USE_CASE_25(
      ScenarioDataActDelete.DEL_ARRANGE__HEAD_CONTENT_TYPE_KEY_VALUE_2,
      ScenarioDataRequestDelete.DEL_REQUEST__HEAD_CONTENT_TYPE_KEY_VALUE_2),
  USE_CASE_26(
      ScenarioDataActDelete.DEL_ARRANGE__HEAD_CONTENT_TYPE_KEY_VALUE_MAP,
      ScenarioDataRequestDelete.DEL_REQUEST__HEAD_CONTENT_TYPE_KEY_VALUE_MAP);

  private final TestAct1 act;
  private final TestRequestDto requestDto;

  ScenarioDataDelete(TestAct1 act, TestRequestDto requestDto) {
    this.act = act;
    this.requestDto = requestDto;
  }
}
