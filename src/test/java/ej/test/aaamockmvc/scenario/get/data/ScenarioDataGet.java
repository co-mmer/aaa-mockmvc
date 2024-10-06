package ej.test.aaamockmvc.scenario.get.data;

import static ej.test.aaamockmvc.scenario.get.data.ScenarioDataActGet.GET_ARRANGE__HEAD_ACCEPT_1;
import static ej.test.aaamockmvc.scenario.get.data.ScenarioDataActGet.GET_ARRANGE__HEAD_ACCEPT_1_AUTH;
import static ej.test.aaamockmvc.scenario.get.data.ScenarioDataActGet.GET_ARRANGE__HEAD_ACCEPT_1_CONTENT_TYPE_1;
import static ej.test.aaamockmvc.scenario.get.data.ScenarioDataActGet.GET_ARRANGE__HEAD_ACCEPT_1_KEY_VALUE_1;
import static ej.test.aaamockmvc.scenario.get.data.ScenarioDataActGet.GET_ARRANGE__HEAD_ACCEPT_1_KEY_VALUE_2;
import static ej.test.aaamockmvc.scenario.get.data.ScenarioDataActGet.GET_ARRANGE__HEAD_ACCEPT_1_KEY_VALUE_MAP;
import static ej.test.aaamockmvc.scenario.get.data.ScenarioDataActGet.GET_ARRANGE__HEAD_ACCEPT_2;
import static ej.test.aaamockmvc.scenario.get.data.ScenarioDataActGet.GET_ARRANGE__HEAD_AUTH;
import static ej.test.aaamockmvc.scenario.get.data.ScenarioDataActGet.GET_ARRANGE__HEAD_AUT_CONTENT_TYPE_1;
import static ej.test.aaamockmvc.scenario.get.data.ScenarioDataActGet.GET_ARRANGE__HEAD_AUT_KEY_VALUE_1;
import static ej.test.aaamockmvc.scenario.get.data.ScenarioDataActGet.GET_ARRANGE__HEAD_AUT_KEY_VALUE_2;
import static ej.test.aaamockmvc.scenario.get.data.ScenarioDataActGet.GET_ARRANGE__HEAD_AUT_KEY_VALUE_MAP;
import static ej.test.aaamockmvc.scenario.get.data.ScenarioDataActGet.GET_ARRANGE__HEAD_CONTENT_TYPE_1;
import static ej.test.aaamockmvc.scenario.get.data.ScenarioDataActGet.GET_ARRANGE__HEAD_CONTENT_TYPE_2;
import static ej.test.aaamockmvc.scenario.get.data.ScenarioDataActGet.GET_ARRANGE__HEAD_CONTENT_TYPE_KEY_VALUE_1;
import static ej.test.aaamockmvc.scenario.get.data.ScenarioDataActGet.GET_ARRANGE__HEAD_CONTENT_TYPE_KEY_VALUE_2;
import static ej.test.aaamockmvc.scenario.get.data.ScenarioDataActGet.GET_ARRANGE__HEAD_CONTENT_TYPE_KEY_VALUE_MAP;
import static ej.test.aaamockmvc.scenario.get.data.ScenarioDataActGet.GET_ARRANGE__HEAD_KEY_VALUE_1;
import static ej.test.aaamockmvc.scenario.get.data.ScenarioDataActGet.GET_ARRANGE__HEAD_KEY_VALUE_2;
import static ej.test.aaamockmvc.scenario.get.data.ScenarioDataActGet.GET_ARRANGE__HEAD_KEY_VALUE_MAP;
import static ej.test.aaamockmvc.scenario.get.data.ScenarioDataActGet.GET_ARRANGE__PARAM_KEY_VALUE_1;
import static ej.test.aaamockmvc.scenario.get.data.ScenarioDataActGet.GET_ARRANGE__PARAM_KEY_VALUE_2;
import static ej.test.aaamockmvc.scenario.get.data.ScenarioDataActGet.GET_ARRANGE__PARAM_KEY_VALUE_MAP;
import static ej.test.aaamockmvc.scenario.get.data.ScenarioDataActGet.GET_ARRANGE__URL;
import static ej.test.aaamockmvc.scenario.get.data.ScenarioDataActGet.GET_ARRANGE__URL_VARIABLE;
import static ej.test.aaamockmvc.scenario.get.data.ScenarioDataActGet.GET_ARRANGE__URL_VARIABLES;
import static ej.test.aaamockmvc.scenario.get.data.ScenarioDataRequestGet.GET_REQUEST__HEAD_ACCEPT_1;
import static ej.test.aaamockmvc.scenario.get.data.ScenarioDataRequestGet.GET_REQUEST__HEAD_ACCEPT_1_AUTH;
import static ej.test.aaamockmvc.scenario.get.data.ScenarioDataRequestGet.GET_REQUEST__HEAD_ACCEPT_1_CONTENT_TYPE_1;
import static ej.test.aaamockmvc.scenario.get.data.ScenarioDataRequestGet.GET_REQUEST__HEAD_ACCEPT_1_KEY_VALUE_1;
import static ej.test.aaamockmvc.scenario.get.data.ScenarioDataRequestGet.GET_REQUEST__HEAD_ACCEPT_1_KEY_VALUE_2;
import static ej.test.aaamockmvc.scenario.get.data.ScenarioDataRequestGet.GET_REQUEST__HEAD_ACCEPT_1_KEY_VALUE_MAP;
import static ej.test.aaamockmvc.scenario.get.data.ScenarioDataRequestGet.GET_REQUEST__HEAD_ACCEPT_2;
import static ej.test.aaamockmvc.scenario.get.data.ScenarioDataRequestGet.GET_REQUEST__HEAD_AUTH;
import static ej.test.aaamockmvc.scenario.get.data.ScenarioDataRequestGet.GET_REQUEST__HEAD_AUTH_KEY_VALUE_1;
import static ej.test.aaamockmvc.scenario.get.data.ScenarioDataRequestGet.GET_REQUEST__HEAD_AUTH_KEY_VALUE_2;
import static ej.test.aaamockmvc.scenario.get.data.ScenarioDataRequestGet.GET_REQUEST__HEAD_AUT_CONTENT_TYPE_1;
import static ej.test.aaamockmvc.scenario.get.data.ScenarioDataRequestGet.GET_REQUEST__HEAD_AUT_KEY_VALUE_MAP;
import static ej.test.aaamockmvc.scenario.get.data.ScenarioDataRequestGet.GET_REQUEST__HEAD_CONTENT_TYPE_1;
import static ej.test.aaamockmvc.scenario.get.data.ScenarioDataRequestGet.GET_REQUEST__HEAD_CONTENT_TYPE_2;
import static ej.test.aaamockmvc.scenario.get.data.ScenarioDataRequestGet.GET_REQUEST__HEAD_CONTENT_TYPE_KEY_VALUE_1;
import static ej.test.aaamockmvc.scenario.get.data.ScenarioDataRequestGet.GET_REQUEST__HEAD_CONTENT_TYPE_KEY_VALUE_2;
import static ej.test.aaamockmvc.scenario.get.data.ScenarioDataRequestGet.GET_REQUEST__HEAD_CONTENT_TYPE_KEY_VALUE_MAP;
import static ej.test.aaamockmvc.scenario.get.data.ScenarioDataRequestGet.GET_REQUEST__HEAD_KEY_VALUE_1;
import static ej.test.aaamockmvc.scenario.get.data.ScenarioDataRequestGet.GET_REQUEST__HEAD_KEY_VALUE_2;
import static ej.test.aaamockmvc.scenario.get.data.ScenarioDataRequestGet.GET_REQUEST__HEAD_KEY_VALUE_MAP;
import static ej.test.aaamockmvc.scenario.get.data.ScenarioDataRequestGet.GET_REQUEST__PARAM_KEY_VALUE_1;
import static ej.test.aaamockmvc.scenario.get.data.ScenarioDataRequestGet.GET_REQUEST__PARAM_KEY_VALUE_2;
import static ej.test.aaamockmvc.scenario.get.data.ScenarioDataRequestGet.GET_REQUEST__PARAM_KEY_VALUE_MAP;
import static ej.test.aaamockmvc.scenario.get.data.ScenarioDataRequestGet.GET_REQUEST__URI;
import static ej.test.aaamockmvc.scenario.get.data.ScenarioDataRequestGet.GET_REQUEST__URI_VARIABLE;
import static ej.test.aaamockmvc.scenario.get.data.ScenarioDataRequestGet.GET_REQUEST__URI_VARIABLES;

import ej.test.aaamockmvc.request.act.TestAct1Perform;
import ej.test.aaamockmvc.request.model.TestRequestDto;
import lombok.Getter;

@Getter
public enum ScenarioDataGet {
  USE_CASE_01(GET_ARRANGE__URL, GET_REQUEST__URI),
  USE_CASE_02(GET_ARRANGE__URL_VARIABLE, GET_REQUEST__URI_VARIABLE),
  USE_CASE_03(GET_ARRANGE__URL_VARIABLES, GET_REQUEST__URI_VARIABLES),

  USE_CASE_04(GET_ARRANGE__PARAM_KEY_VALUE_1, GET_REQUEST__PARAM_KEY_VALUE_1),
  USE_CASE_05(GET_ARRANGE__PARAM_KEY_VALUE_2, GET_REQUEST__PARAM_KEY_VALUE_2),
  USE_CASE_06(GET_ARRANGE__PARAM_KEY_VALUE_MAP, GET_REQUEST__PARAM_KEY_VALUE_MAP),

  USE_CASE_07(GET_ARRANGE__HEAD_ACCEPT_1, GET_REQUEST__HEAD_ACCEPT_1),
  USE_CASE_08(GET_ARRANGE__HEAD_ACCEPT_2, GET_REQUEST__HEAD_ACCEPT_2),
  USE_CASE_09(GET_ARRANGE__HEAD_AUTH, GET_REQUEST__HEAD_AUTH),
  USE_CASE_10(GET_ARRANGE__HEAD_CONTENT_TYPE_1, GET_REQUEST__HEAD_CONTENT_TYPE_1),
  USE_CASE_11(GET_ARRANGE__HEAD_CONTENT_TYPE_2, GET_REQUEST__HEAD_CONTENT_TYPE_2),
  USE_CASE_12(GET_ARRANGE__HEAD_KEY_VALUE_1, GET_REQUEST__HEAD_KEY_VALUE_1),
  USE_CASE_13(GET_ARRANGE__HEAD_KEY_VALUE_2, GET_REQUEST__HEAD_KEY_VALUE_2),
  USE_CASE_14(GET_ARRANGE__HEAD_KEY_VALUE_MAP, GET_REQUEST__HEAD_KEY_VALUE_MAP),

  USE_CASE_15(GET_ARRANGE__HEAD_ACCEPT_1_AUTH, GET_REQUEST__HEAD_ACCEPT_1_AUTH),
  USE_CASE_16(GET_ARRANGE__HEAD_ACCEPT_1_CONTENT_TYPE_1, GET_REQUEST__HEAD_ACCEPT_1_CONTENT_TYPE_1),
  USE_CASE_17(GET_ARRANGE__HEAD_ACCEPT_1_KEY_VALUE_1, GET_REQUEST__HEAD_ACCEPT_1_KEY_VALUE_1),
  USE_CASE_18(GET_ARRANGE__HEAD_ACCEPT_1_KEY_VALUE_2, GET_REQUEST__HEAD_ACCEPT_1_KEY_VALUE_2),
  USE_CASE_19(GET_ARRANGE__HEAD_ACCEPT_1_KEY_VALUE_MAP, GET_REQUEST__HEAD_ACCEPT_1_KEY_VALUE_MAP),

  USE_CASE_20(GET_ARRANGE__HEAD_AUT_CONTENT_TYPE_1, GET_REQUEST__HEAD_AUT_CONTENT_TYPE_1),
  USE_CASE_21(GET_ARRANGE__HEAD_AUT_KEY_VALUE_1, GET_REQUEST__HEAD_AUTH_KEY_VALUE_1),
  USE_CASE_22(GET_ARRANGE__HEAD_AUT_KEY_VALUE_2, GET_REQUEST__HEAD_AUTH_KEY_VALUE_2),
  USE_CASE_23(GET_ARRANGE__HEAD_AUT_KEY_VALUE_MAP, GET_REQUEST__HEAD_AUT_KEY_VALUE_MAP),

  USE_CASE_24(
      GET_ARRANGE__HEAD_CONTENT_TYPE_KEY_VALUE_1, GET_REQUEST__HEAD_CONTENT_TYPE_KEY_VALUE_1),
  USE_CASE_25(
      GET_ARRANGE__HEAD_CONTENT_TYPE_KEY_VALUE_2, GET_REQUEST__HEAD_CONTENT_TYPE_KEY_VALUE_2),
  USE_CASE_26(
      GET_ARRANGE__HEAD_CONTENT_TYPE_KEY_VALUE_MAP, GET_REQUEST__HEAD_CONTENT_TYPE_KEY_VALUE_MAP);

  private final TestAct1Perform act;
  private final TestRequestDto requestDto;

  ScenarioDataGet(TestAct1Perform act, TestRequestDto requestDto) {
    this.act = act;
    this.requestDto = requestDto;
  }
}
