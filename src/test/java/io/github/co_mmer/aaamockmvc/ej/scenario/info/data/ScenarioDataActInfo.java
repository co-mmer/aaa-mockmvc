package io.github.co_mmer.aaamockmvc.ej.scenario.info.data;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_XML;

import io.github.co_mmer.aaamockmvc.ej.test.web.act.TestAct1;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.info.head.TestArrange1InfoHead;
import io.github.co_mmer.aaamockmvc.ej.test.web.request.TestRequestHead;
import io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestHeader;
import io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject;
import io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestParameter;
import io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ScenarioDataActInfo {

  private static final TestRequestHead HEAD = new TestRequestHead(TestObject.TEST_REQUEST_BEAN);

  public static final TestAct1 HEAD_ARRANGE__URL =
      HEAD.arrange().arrangeUrl(TestValue.TEST_PATH).act();

  public static final TestAct1 HEAD_ARRANGE__URL_VARIABLE =
      HEAD.arrange().arrangeUrl(TestValue.TEST_PATH_VAR_1, TestValue.VAR_STRING_1).act();

  public static final TestAct1 HEAD_ARRANGE__URL_VARIABLES =
      HEAD.arrange()
          .arrangeUrl(TestValue.TEST_PATH_VAR_2, TestValue.VAR_STRING_1, TestValue.VAR_STRING_2)
          .act();

  public static final TestAct1 HEAD_ARRANGE__PARAM_KEY_VALUE_1 =
      HEAD.arrange()
          .arrangeUrl(TestValue.TEST_PATH)
          .arrangeParam()
          .arrangeKeyValue(TestParameter.TEST_PARAM_KEY_1, TestParameter.TEST_PARAM_VALUE_1)
          .act();

  public static final TestAct1 HEAD_ARRANGE__PARAM_KEY_VALUE_2 =
      HEAD.arrange()
          .arrangeUrl(TestValue.TEST_PATH)
          .arrangeParam()
          .arrangeKeyValue(TestParameter.TEST_PARAM_KEY_1, TestParameter.TEST_PARAM_VALUE_1)
          .arrangeKeyValue(TestParameter.TEST_PARAM_KEY_2, TestParameter.TEST_PARAM_VALUE_2)
          .act();

  public static final TestAct1 HEAD_ARRANGE__PARAM_KEY_VALUE_MAP =
      HEAD.arrange()
          .arrangeUrl(TestValue.TEST_PATH)
          .arrangeParam()
          .arrangeKeyValue(TestParameter.TEST_PARAM_KEY_VALUE_MAP_1_2)
          .act();

  public static final TestAct1 HEAD_ARRANGE__HEAD_AUTH =
      HEAD.arrange()
          .arrangeUrl(TestValue.TEST_PATH)
          .arrangeHead()
          .arrangeAuth(TestHeader.TEST_AUTH_VALUE)
          .act();

  public static final TestAct1 HEAD_ARRANGE__HEAD_ACCEPT_1 =
      HEAD.arrange()
          .arrangeUrl(TestValue.TEST_PATH)
          .arrangeHead()
          .arrangeAccept(APPLICATION_JSON)
          .act();

  public static final TestAct1 HEAD_ARRANGE__HEAD_ACCEPT_2 =
      HEAD.arrange()
          .arrangeUrl(TestValue.TEST_PATH)
          .arrangeHead()
          .arrangeAccept(APPLICATION_XML, APPLICATION_JSON)
          .act();

  public static final TestAct1 HEAD_ARRANGE__HEAD_KEY_VALUE_1 =
      HEAD.arrange()
          .arrangeUrl(TestValue.TEST_PATH)
          .arrangeHead()
          .arrangeKeyValue(TestHeader.TEST_HEADER_KEY_1, TestHeader.TEST_HEADER_VALUE_1)
          .act();

  public static final TestAct1 HEAD_ARRANGE__HEAD_KEY_VALUE_2 =
      HEAD.arrange()
          .arrangeUrl(TestValue.TEST_PATH)
          .arrangeHead()
          .arrangeKeyValue(TestHeader.TEST_HEADER_KEY_1, TestHeader.TEST_HEADER_VALUE_1)
          .arrangeKeyValue(TestHeader.TEST_HEADER_KEY_2, TestHeader.TEST_HEADER_VALUE_2)
          .act();

  public static final TestAct1 HEAD_ARRANGE__HEAD_KEY_VALUE_MAP =
      HEAD.arrange()
          .arrangeUrl(TestValue.TEST_PATH)
          .arrangeHead()
          .arrangeKeyValue(TestHeader.TEST_HEADER_MAP_1_2)
          .act();

  public static final TestAct1 HEAD_ARRANGE__HEAD_ACCEPT_1_AUTH =
      HEAD.arrange()
          .arrangeUrl(TestValue.TEST_PATH)
          .arrangeHead()
          .arrangeAccept(APPLICATION_JSON)
          .arrangeAuth(TestHeader.TEST_AUTH_VALUE)
          .act();

  public static final TestAct1 HEAD_ARRANGE__HEAD_ACCEPT_1_KEY_VALUE_1 =
      HEAD.arrange()
          .arrangeUrl(TestValue.TEST_PATH)
          .arrangeHead()
          .arrangeAccept(APPLICATION_JSON)
          .arrangeKeyValue(TestHeader.TEST_HEADER_KEY_1, TestHeader.TEST_HEADER_VALUE_1)
          .act();

  public static final TestAct1 HEAD_ARRANGE__HEAD_ACCEPT_1_KEY_VALUE_2 =
      HEAD.arrange()
          .arrangeUrl(TestValue.TEST_PATH)
          .arrangeHead()
          .arrangeAccept(APPLICATION_JSON)
          .arrangeKeyValue(TestHeader.TEST_HEADER_KEY_1, TestHeader.TEST_HEADER_VALUE_1)
          .arrangeKeyValue(TestHeader.TEST_HEADER_KEY_2, TestHeader.TEST_HEADER_VALUE_2)
          .act();

  public static final TestAct1 HEAD_ARRANGE__HEAD_ACCEPT_1_KEY_VALUE_MAP =
      HEAD.arrange()
          .arrangeUrl(TestValue.TEST_PATH)
          .arrangeHead()
          .arrangeAccept(APPLICATION_JSON)
          .arrangeKeyValue(TestHeader.TEST_HEADER_MAP_1_2)
          .act();

  public static final TestAct1 HEAD_ARRANGE__HEAD_AUT_KEY_VALUE_1 =
      HEAD.arrange()
          .arrangeUrl(TestValue.TEST_PATH)
          .arrangeHead()
          .arrangeAuth(TestHeader.TEST_AUTH_VALUE)
          .arrangeKeyValue(TestHeader.TEST_HEADER_KEY_1, TestHeader.TEST_HEADER_VALUE_1)
          .act();

  public static final TestAct1 HEAD_ARRANGE__HEAD_AUT_KEY_VALUE_2 =
      HEAD.arrange()
          .arrangeUrl(TestValue.TEST_PATH)
          .arrangeHead()
          .arrangeAuth(TestHeader.TEST_AUTH_VALUE)
          .arrangeKeyValue(TestHeader.TEST_HEADER_KEY_1, TestHeader.TEST_HEADER_VALUE_1)
          .arrangeKeyValue(TestHeader.TEST_HEADER_KEY_2, TestHeader.TEST_HEADER_VALUE_2)
          .act();

  public static final TestAct1 HEAD_ARRANGE__HEAD_AUT_KEY_VALUE_MAP =
      HEAD.arrange()
          .arrangeUrl(TestValue.TEST_PATH)
          .arrangeHead()
          .arrangeAuth(TestHeader.TEST_AUTH_VALUE)
          .arrangeKeyValue(TestHeader.TEST_HEADER_MAP_1_2)
          .act();

  public static final TestArrange1InfoHead HEAD_ARRANGE__PARAM_KEY_VALUE_1__HEAD =
      HEAD.arrange()
          .arrangeUrl(TestValue.TEST_PATH)
          .arrangeParam()
          .arrangeKeyValue(TestParameter.TEST_PARAM_KEY_1, TestParameter.TEST_PARAM_VALUE_1)
          .arrangeHead();

  public static final TestArrange1InfoHead HEAD_ARRANGE__PARAM_KEY_VALUE_2__HEAD =
      HEAD.arrange()
          .arrangeUrl(TestValue.TEST_PATH)
          .arrangeParam()
          .arrangeKeyValue(TestParameter.TEST_PARAM_KEY_1, TestParameter.TEST_PARAM_VALUE_1)
          .arrangeKeyValue(TestParameter.TEST_PARAM_KEY_2, TestParameter.TEST_PARAM_VALUE_2)
          .arrangeHead();

  public static final TestArrange1InfoHead HEAD_ARRANGE__PARAM_KEY_VALUE_MAP__HEAD =
      HEAD.arrange()
          .arrangeUrl(TestValue.TEST_PATH)
          .arrangeParam()
          .arrangeKeyValue(TestParameter.TEST_PARAM_KEY_VALUE_MAP_1_2)
          .arrangeHead();
}
