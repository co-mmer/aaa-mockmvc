package io.github.co_mmer.aaamockmvc.ej.scenario.get.data;

import static io.github.co_mmer.aaamockmvc.ej.scenario.get.data.ScenarioDataRequestGet.GET_REQUEST__HEAD_ACCEPT_1;
import static io.github.co_mmer.aaamockmvc.ej.scenario.get.data.ScenarioDataRequestGet.GET_REQUEST__HEAD_ACCEPT_1_AUTH;
import static io.github.co_mmer.aaamockmvc.ej.scenario.get.data.ScenarioDataRequestGet.GET_REQUEST__HEAD_ACCEPT_1_KEY_VALUE_1;
import static io.github.co_mmer.aaamockmvc.ej.scenario.get.data.ScenarioDataRequestGet.GET_REQUEST__HEAD_ACCEPT_1_KEY_VALUE_2;
import static io.github.co_mmer.aaamockmvc.ej.scenario.get.data.ScenarioDataRequestGet.GET_REQUEST__HEAD_ACCEPT_1_KEY_VALUE_MAP;
import static io.github.co_mmer.aaamockmvc.ej.scenario.get.data.ScenarioDataRequestGet.GET_REQUEST__HEAD_ACCEPT_2;
import static io.github.co_mmer.aaamockmvc.ej.scenario.get.data.ScenarioDataRequestGet.GET_REQUEST__HEAD_AUTH;
import static io.github.co_mmer.aaamockmvc.ej.scenario.get.data.ScenarioDataRequestGet.GET_REQUEST__HEAD_AUTH_KEY_VALUE_1;
import static io.github.co_mmer.aaamockmvc.ej.scenario.get.data.ScenarioDataRequestGet.GET_REQUEST__HEAD_AUTH_KEY_VALUE_2;
import static io.github.co_mmer.aaamockmvc.ej.scenario.get.data.ScenarioDataRequestGet.GET_REQUEST__HEAD_AUT_KEY_VALUE_MAP;
import static io.github.co_mmer.aaamockmvc.ej.scenario.get.data.ScenarioDataRequestGet.GET_REQUEST__HEAD_KEY_VALUE_1;
import static io.github.co_mmer.aaamockmvc.ej.scenario.get.data.ScenarioDataRequestGet.GET_REQUEST__HEAD_KEY_VALUE_2;
import static io.github.co_mmer.aaamockmvc.ej.scenario.get.data.ScenarioDataRequestGet.GET_REQUEST__HEAD_KEY_VALUE_MAP;
import static io.github.co_mmer.aaamockmvc.ej.scenario.get.data.ScenarioDataRequestGet.GET_REQUEST__PARAM_KEY_VALUE_1;
import static io.github.co_mmer.aaamockmvc.ej.scenario.get.data.ScenarioDataRequestGet.GET_REQUEST__PARAM_KEY_VALUE_2;
import static io.github.co_mmer.aaamockmvc.ej.scenario.get.data.ScenarioDataRequestGet.GET_REQUEST__PARAM_KEY_VALUE_MAP;
import static io.github.co_mmer.aaamockmvc.ej.scenario.get.data.ScenarioDataRequestGet.GET_REQUEST__URI;
import static io.github.co_mmer.aaamockmvc.ej.scenario.get.data.ScenarioDataRequestGet.GET_REQUEST__URI_VARIABLE;
import static io.github.co_mmer.aaamockmvc.ej.scenario.get.data.ScenarioDataRequestGet.GET_REQUEST__URI_VARIABLES;

import io.github.co_mmer.aaamockmvc.ej.test.web.act.TestAct1;
import io.github.co_mmer.aaamockmvc.ej.test.web.request.model.TestRequestDto;
import lombok.Getter;

@Getter
public enum ScenarioDataGet {
  USE_CASE_01(ScenarioDataActGet.GET_ARRANGE__URL, GET_REQUEST__URI),
  USE_CASE_02(ScenarioDataActGet.GET_ARRANGE__URL_VARIABLE, GET_REQUEST__URI_VARIABLE),
  USE_CASE_03(ScenarioDataActGet.GET_ARRANGE__URL_VARIABLES, GET_REQUEST__URI_VARIABLES),

  USE_CASE_04(ScenarioDataActGet.GET_ARRANGE__PARAM_KEY_VALUE_1, GET_REQUEST__PARAM_KEY_VALUE_1),
  USE_CASE_05(ScenarioDataActGet.GET_ARRANGE__PARAM_KEY_VALUE_2, GET_REQUEST__PARAM_KEY_VALUE_2),
  USE_CASE_06(
      ScenarioDataActGet.GET_ARRANGE__PARAM_KEY_VALUE_MAP, GET_REQUEST__PARAM_KEY_VALUE_MAP),

  USE_CASE_07(ScenarioDataActGet.GET_ARRANGE__HEAD_ACCEPT_1, GET_REQUEST__HEAD_ACCEPT_1),
  USE_CASE_08(ScenarioDataActGet.GET_ARRANGE__HEAD_ACCEPT_2, GET_REQUEST__HEAD_ACCEPT_2),
  USE_CASE_09(ScenarioDataActGet.GET_ARRANGE__HEAD_AUTH, GET_REQUEST__HEAD_AUTH),
  USE_CASE_12(ScenarioDataActGet.GET_ARRANGE__HEAD_KEY_VALUE_1, GET_REQUEST__HEAD_KEY_VALUE_1),
  USE_CASE_13(ScenarioDataActGet.GET_ARRANGE__HEAD_KEY_VALUE_2, GET_REQUEST__HEAD_KEY_VALUE_2),
  USE_CASE_14(ScenarioDataActGet.GET_ARRANGE__HEAD_KEY_VALUE_MAP, GET_REQUEST__HEAD_KEY_VALUE_MAP),

  USE_CASE_15(ScenarioDataActGet.GET_ARRANGE__HEAD_ACCEPT_1_AUTH, GET_REQUEST__HEAD_ACCEPT_1_AUTH),
  USE_CASE_17(
      ScenarioDataActGet.GET_ARRANGE__HEAD_ACCEPT_1_KEY_VALUE_1,
      GET_REQUEST__HEAD_ACCEPT_1_KEY_VALUE_1),
  USE_CASE_18(
      ScenarioDataActGet.GET_ARRANGE__HEAD_ACCEPT_1_KEY_VALUE_2,
      GET_REQUEST__HEAD_ACCEPT_1_KEY_VALUE_2),
  USE_CASE_19(
      ScenarioDataActGet.GET_ARRANGE__HEAD_ACCEPT_1_KEY_VALUE_MAP,
      GET_REQUEST__HEAD_ACCEPT_1_KEY_VALUE_MAP),
  USE_CASE_21(
      ScenarioDataActGet.GET_ARRANGE__HEAD_AUT_KEY_VALUE_1, GET_REQUEST__HEAD_AUTH_KEY_VALUE_1),
  USE_CASE_22(
      ScenarioDataActGet.GET_ARRANGE__HEAD_AUT_KEY_VALUE_2, GET_REQUEST__HEAD_AUTH_KEY_VALUE_2),
  USE_CASE_23(
      ScenarioDataActGet.GET_ARRANGE__HEAD_AUT_KEY_VALUE_MAP, GET_REQUEST__HEAD_AUT_KEY_VALUE_MAP);

  private final TestAct1 act;
  private final TestRequestDto requestDto;

  ScenarioDataGet(TestAct1 act, TestRequestDto requestDto) {
    this.act = act;
    this.requestDto = requestDto;
  }
}
