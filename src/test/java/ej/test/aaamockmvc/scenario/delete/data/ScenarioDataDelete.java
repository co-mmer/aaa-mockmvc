package ej.test.aaamockmvc.scenario.delete.data;

import static ej.test.aaamockmvc.scenario.delete.data.ScenarioDataActDelete.DEL_ARRANGE__HEAD_ACCEPT_1;
import static ej.test.aaamockmvc.scenario.delete.data.ScenarioDataActDelete.DEL_ARRANGE__HEAD_ACCEPT_1_AUTH;
import static ej.test.aaamockmvc.scenario.delete.data.ScenarioDataActDelete.DEL_ARRANGE__HEAD_ACCEPT_1_CONTENT_TYPE_1;
import static ej.test.aaamockmvc.scenario.delete.data.ScenarioDataActDelete.DEL_ARRANGE__HEAD_ACCEPT_1_KEY_VALUE_1;
import static ej.test.aaamockmvc.scenario.delete.data.ScenarioDataActDelete.DEL_ARRANGE__HEAD_ACCEPT_1_KEY_VALUE_2;
import static ej.test.aaamockmvc.scenario.delete.data.ScenarioDataActDelete.DEL_ARRANGE__HEAD_ACCEPT_1_KEY_VALUE_MAP;
import static ej.test.aaamockmvc.scenario.delete.data.ScenarioDataActDelete.DEL_ARRANGE__HEAD_ACCEPT_2;
import static ej.test.aaamockmvc.scenario.delete.data.ScenarioDataActDelete.DEL_ARRANGE__HEAD_AUTH;
import static ej.test.aaamockmvc.scenario.delete.data.ScenarioDataActDelete.DEL_ARRANGE__HEAD_AUT_CONTENT_TYPE_1;
import static ej.test.aaamockmvc.scenario.delete.data.ScenarioDataActDelete.DEL_ARRANGE__HEAD_AUT_KEY_VALUE_1;
import static ej.test.aaamockmvc.scenario.delete.data.ScenarioDataActDelete.DEL_ARRANGE__HEAD_AUT_KEY_VALUE_2;
import static ej.test.aaamockmvc.scenario.delete.data.ScenarioDataActDelete.DEL_ARRANGE__HEAD_AUT_KEY_VALUE_MAP;
import static ej.test.aaamockmvc.scenario.delete.data.ScenarioDataActDelete.DEL_ARRANGE__HEAD_CONTENT_TYPE_1;
import static ej.test.aaamockmvc.scenario.delete.data.ScenarioDataActDelete.DEL_ARRANGE__HEAD_CONTENT_TYPE_2;
import static ej.test.aaamockmvc.scenario.delete.data.ScenarioDataActDelete.DEL_ARRANGE__HEAD_CONTENT_TYPE_KEY_VALUE_1;
import static ej.test.aaamockmvc.scenario.delete.data.ScenarioDataActDelete.DEL_ARRANGE__HEAD_CONTENT_TYPE_KEY_VALUE_2;
import static ej.test.aaamockmvc.scenario.delete.data.ScenarioDataActDelete.DEL_ARRANGE__HEAD_CONTENT_TYPE_KEY_VALUE_MAP;
import static ej.test.aaamockmvc.scenario.delete.data.ScenarioDataActDelete.DEL_ARRANGE__HEAD_KEY_VALUE_1;
import static ej.test.aaamockmvc.scenario.delete.data.ScenarioDataActDelete.DEL_ARRANGE__HEAD_KEY_VALUE_2;
import static ej.test.aaamockmvc.scenario.delete.data.ScenarioDataActDelete.DEL_ARRANGE__HEAD_KEY_VALUE_MAP;
import static ej.test.aaamockmvc.scenario.delete.data.ScenarioDataActDelete.DEL_ARRANGE__PARAM_KEY_VALUE_1;
import static ej.test.aaamockmvc.scenario.delete.data.ScenarioDataActDelete.DEL_ARRANGE__PARAM_KEY_VALUE_2;
import static ej.test.aaamockmvc.scenario.delete.data.ScenarioDataActDelete.DEL_ARRANGE__PARAM_KEY_VALUE_MAP;
import static ej.test.aaamockmvc.scenario.delete.data.ScenarioDataActDelete.DEL_ARRANGE__URL;
import static ej.test.aaamockmvc.scenario.delete.data.ScenarioDataActDelete.DEL_ARRANGE__URL_VARIABLE;
import static ej.test.aaamockmvc.scenario.delete.data.ScenarioDataActDelete.DEL_ARRANGE__URL_VARIABLES;
import static ej.test.aaamockmvc.scenario.delete.data.ScenarioDataRequestDelete.DEL_REQUEST__HEAD_ACCEPT_1;
import static ej.test.aaamockmvc.scenario.delete.data.ScenarioDataRequestDelete.DEL_REQUEST__HEAD_ACCEPT_1_AUTH;
import static ej.test.aaamockmvc.scenario.delete.data.ScenarioDataRequestDelete.DEL_REQUEST__HEAD_ACCEPT_1_CONTENT_TYPE_1;
import static ej.test.aaamockmvc.scenario.delete.data.ScenarioDataRequestDelete.DEL_REQUEST__HEAD_ACCEPT_1_KEY_VALUE_1;
import static ej.test.aaamockmvc.scenario.delete.data.ScenarioDataRequestDelete.DEL_REQUEST__HEAD_ACCEPT_1_KEY_VALUE_2;
import static ej.test.aaamockmvc.scenario.delete.data.ScenarioDataRequestDelete.DEL_REQUEST__HEAD_ACCEPT_1_KEY_VALUE_MAP;
import static ej.test.aaamockmvc.scenario.delete.data.ScenarioDataRequestDelete.DEL_REQUEST__HEAD_ACCEPT_2;
import static ej.test.aaamockmvc.scenario.delete.data.ScenarioDataRequestDelete.DEL_REQUEST__HEAD_AUTH;
import static ej.test.aaamockmvc.scenario.delete.data.ScenarioDataRequestDelete.DEL_REQUEST__HEAD_AUTH_KEY_VALUE_1;
import static ej.test.aaamockmvc.scenario.delete.data.ScenarioDataRequestDelete.DEL_REQUEST__HEAD_AUTH_KEY_VALUE_2;
import static ej.test.aaamockmvc.scenario.delete.data.ScenarioDataRequestDelete.DEL_REQUEST__HEAD_AUT_CONTENT_TYPE_1;
import static ej.test.aaamockmvc.scenario.delete.data.ScenarioDataRequestDelete.DEL_REQUEST__HEAD_AUT_KEY_VALUE_MAP;
import static ej.test.aaamockmvc.scenario.delete.data.ScenarioDataRequestDelete.DEL_REQUEST__HEAD_CONTENT_TYPE_1;
import static ej.test.aaamockmvc.scenario.delete.data.ScenarioDataRequestDelete.DEL_REQUEST__HEAD_CONTENT_TYPE_2;
import static ej.test.aaamockmvc.scenario.delete.data.ScenarioDataRequestDelete.DEL_REQUEST__HEAD_CONTENT_TYPE_KEY_VALUE_1;
import static ej.test.aaamockmvc.scenario.delete.data.ScenarioDataRequestDelete.DEL_REQUEST__HEAD_CONTENT_TYPE_KEY_VALUE_2;
import static ej.test.aaamockmvc.scenario.delete.data.ScenarioDataRequestDelete.DEL_REQUEST__HEAD_CONTENT_TYPE_KEY_VALUE_MAP;
import static ej.test.aaamockmvc.scenario.delete.data.ScenarioDataRequestDelete.DEL_REQUEST__HEAD_KEY_VALUE_1;
import static ej.test.aaamockmvc.scenario.delete.data.ScenarioDataRequestDelete.DEL_REQUEST__HEAD_KEY_VALUE_2;
import static ej.test.aaamockmvc.scenario.delete.data.ScenarioDataRequestDelete.DEL_REQUEST__HEAD_KEY_VALUE_MAP;
import static ej.test.aaamockmvc.scenario.delete.data.ScenarioDataRequestDelete.DEL_REQUEST__PARAM_KEY_VALUE_1;
import static ej.test.aaamockmvc.scenario.delete.data.ScenarioDataRequestDelete.DEL_REQUEST__PARAM_KEY_VALUE_2;
import static ej.test.aaamockmvc.scenario.delete.data.ScenarioDataRequestDelete.DEL_REQUEST__PARAM_KEY_VALUE_MAP;
import static ej.test.aaamockmvc.scenario.delete.data.ScenarioDataRequestDelete.DEL_REQUEST__URI;
import static ej.test.aaamockmvc.scenario.delete.data.ScenarioDataRequestDelete.DEL_REQUEST__URI_VARIABLE;
import static ej.test.aaamockmvc.scenario.delete.data.ScenarioDataRequestDelete.DEL_REQUEST__URI_VARIABLES;

import ej.test.aaamockmvc.request.act.TestAct1Perform;
import ej.test.aaamockmvc.request.model.TestRequestDto;
import lombok.Getter;

@Getter
public enum ScenarioDataDelete {
  USE_CASE_01(DEL_ARRANGE__URL, DEL_REQUEST__URI),
  USE_CASE_02(DEL_ARRANGE__URL_VARIABLE, DEL_REQUEST__URI_VARIABLE),
  USE_CASE_03(DEL_ARRANGE__URL_VARIABLES, DEL_REQUEST__URI_VARIABLES),

  USE_CASE_04(DEL_ARRANGE__PARAM_KEY_VALUE_1, DEL_REQUEST__PARAM_KEY_VALUE_1),
  USE_CASE_05(DEL_ARRANGE__PARAM_KEY_VALUE_2, DEL_REQUEST__PARAM_KEY_VALUE_2),
  USE_CASE_06(DEL_ARRANGE__PARAM_KEY_VALUE_MAP, DEL_REQUEST__PARAM_KEY_VALUE_MAP),

  USE_CASE_07(DEL_ARRANGE__HEAD_ACCEPT_1, DEL_REQUEST__HEAD_ACCEPT_1),
  USE_CASE_08(DEL_ARRANGE__HEAD_ACCEPT_2, DEL_REQUEST__HEAD_ACCEPT_2),
  USE_CASE_09(DEL_ARRANGE__HEAD_AUTH, DEL_REQUEST__HEAD_AUTH),
  USE_CASE_10(DEL_ARRANGE__HEAD_CONTENT_TYPE_1, DEL_REQUEST__HEAD_CONTENT_TYPE_1),
  USE_CASE_11(DEL_ARRANGE__HEAD_CONTENT_TYPE_2, DEL_REQUEST__HEAD_CONTENT_TYPE_2),
  USE_CASE_12(DEL_ARRANGE__HEAD_KEY_VALUE_1, DEL_REQUEST__HEAD_KEY_VALUE_1),
  USE_CASE_13(DEL_ARRANGE__HEAD_KEY_VALUE_2, DEL_REQUEST__HEAD_KEY_VALUE_2),
  USE_CASE_14(DEL_ARRANGE__HEAD_KEY_VALUE_MAP, DEL_REQUEST__HEAD_KEY_VALUE_MAP),

  USE_CASE_15(DEL_ARRANGE__HEAD_ACCEPT_1_AUTH, DEL_REQUEST__HEAD_ACCEPT_1_AUTH),
  USE_CASE_16(DEL_ARRANGE__HEAD_ACCEPT_1_CONTENT_TYPE_1, DEL_REQUEST__HEAD_ACCEPT_1_CONTENT_TYPE_1),
  USE_CASE_17(DEL_ARRANGE__HEAD_ACCEPT_1_KEY_VALUE_1, DEL_REQUEST__HEAD_ACCEPT_1_KEY_VALUE_1),
  USE_CASE_18(DEL_ARRANGE__HEAD_ACCEPT_1_KEY_VALUE_2, DEL_REQUEST__HEAD_ACCEPT_1_KEY_VALUE_2),
  USE_CASE_19(DEL_ARRANGE__HEAD_ACCEPT_1_KEY_VALUE_MAP, DEL_REQUEST__HEAD_ACCEPT_1_KEY_VALUE_MAP),

  USE_CASE_20(DEL_ARRANGE__HEAD_AUT_CONTENT_TYPE_1, DEL_REQUEST__HEAD_AUT_CONTENT_TYPE_1),
  USE_CASE_21(DEL_ARRANGE__HEAD_AUT_KEY_VALUE_1, DEL_REQUEST__HEAD_AUTH_KEY_VALUE_1),
  USE_CASE_22(DEL_ARRANGE__HEAD_AUT_KEY_VALUE_2, DEL_REQUEST__HEAD_AUTH_KEY_VALUE_2),
  USE_CASE_23(DEL_ARRANGE__HEAD_AUT_KEY_VALUE_MAP, DEL_REQUEST__HEAD_AUT_KEY_VALUE_MAP),

  USE_CASE_24(
      DEL_ARRANGE__HEAD_CONTENT_TYPE_KEY_VALUE_1, DEL_REQUEST__HEAD_CONTENT_TYPE_KEY_VALUE_1),
  USE_CASE_25(
      DEL_ARRANGE__HEAD_CONTENT_TYPE_KEY_VALUE_2, DEL_REQUEST__HEAD_CONTENT_TYPE_KEY_VALUE_2),
  USE_CASE_26(
      DEL_ARRANGE__HEAD_CONTENT_TYPE_KEY_VALUE_MAP, DEL_REQUEST__HEAD_CONTENT_TYPE_KEY_VALUE_MAP);

  private final TestAct1Perform act;
  private final TestRequestDto requestDto;

  ScenarioDataDelete(TestAct1Perform act, TestRequestDto requestDto) {
    this.act = act;
    this.requestDto = requestDto;
  }
}
