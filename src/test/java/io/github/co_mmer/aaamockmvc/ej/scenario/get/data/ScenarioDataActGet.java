package io.github.co_mmer.aaamockmvc.ej.scenario.get.data;

import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestHeader.TEST_AUTH_VALUE;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestHeader.TEST_HEADER_KEY_1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestHeader.TEST_HEADER_KEY_2;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestHeader.TEST_HEADER_MAP_1_2;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestHeader.TEST_HEADER_VALUE_1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestHeader.TEST_HEADER_VALUE_2;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_REQUEST_BEAN;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestParameter.TEST_PARAM_KEY_1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestParameter.TEST_PARAM_KEY_2;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestParameter.TEST_PARAM_KEY_VALUE_MAP_1_2;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestParameter.TEST_PARAM_VALUE_1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestParameter.TEST_PARAM_VALUE_2;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.TEST_PATH;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.TEST_PATH_VAR_1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.TEST_PATH_VAR_2;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.VAR_STRING_1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.VAR_STRING_2;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_XML;

import io.github.co_mmer.aaamockmvc.ej.test.web.act.TestAct1;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.get.head.TestArrange1GetHead;
import io.github.co_mmer.aaamockmvc.ej.test.web.request.TestRequestGet;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ScenarioDataActGet {

  private static final TestRequestGet GET = new TestRequestGet(TEST_REQUEST_BEAN);

  public static final TestAct1 GET_ARRANGE__URL = GET.arrange().arrangeUrl(TEST_PATH).act();

  public static final TestAct1 GET_ARRANGE__URL_VARIABLE =
      GET.arrange().arrangeUrl(TEST_PATH_VAR_1, VAR_STRING_1).act();

  public static final TestAct1 GET_ARRANGE__URL_VARIABLES =
      GET.arrange().arrangeUrl(TEST_PATH_VAR_2, VAR_STRING_1, VAR_STRING_2).act();

  public static final TestAct1 GET_ARRANGE__PARAM_KEY_VALUE_1 =
      GET.arrange()
          .arrangeUrl(TEST_PATH)
          .arrangeParam()
          .arrangeKeyValue(TEST_PARAM_KEY_1, TEST_PARAM_VALUE_1)
          .act();

  public static final TestAct1 GET_ARRANGE__PARAM_KEY_VALUE_2 =
      GET.arrange()
          .arrangeUrl(TEST_PATH)
          .arrangeParam()
          .arrangeKeyValue(TEST_PARAM_KEY_1, TEST_PARAM_VALUE_1)
          .arrangeKeyValue(TEST_PARAM_KEY_2, TEST_PARAM_VALUE_2)
          .act();

  public static final TestAct1 GET_ARRANGE__PARAM_KEY_VALUE_MAP =
      GET.arrange()
          .arrangeUrl(TEST_PATH)
          .arrangeParam()
          .arrangeKeyValue(TEST_PARAM_KEY_VALUE_MAP_1_2)
          .act();

  public static final TestAct1 GET_ARRANGE__HEAD_KEY_VALUE_1 =
      GET.arrange()
          .arrangeUrl(TEST_PATH)
          .arrangeHead()
          .arrangeKeyValue(TEST_HEADER_KEY_1, TEST_HEADER_VALUE_1)
          .act();

  public static final TestAct1 GET_ARRANGE__HEAD_KEY_VALUE_2 =
      GET.arrange()
          .arrangeUrl(TEST_PATH)
          .arrangeHead()
          .arrangeKeyValue(TEST_HEADER_KEY_1, TEST_HEADER_VALUE_1)
          .arrangeKeyValue(TEST_HEADER_KEY_2, TEST_HEADER_VALUE_2)
          .act();

  public static final TestAct1 GET_ARRANGE__HEAD_KEY_VALUE_MAP =
      GET.arrange().arrangeUrl(TEST_PATH).arrangeHead().arrangeKeyValue(TEST_HEADER_MAP_1_2).act();

  public static final TestAct1 GET_ARRANGE__HEAD_AUTH =
      GET.arrange().arrangeUrl(TEST_PATH).arrangeHead().arrangeAuth(TEST_AUTH_VALUE).act();

  public static final TestAct1 GET_ARRANGE__HEAD_ACCEPT_1 =
      GET.arrange().arrangeUrl(TEST_PATH).arrangeHead().arrangeAccept(APPLICATION_JSON).act();

  public static final TestAct1 GET_ARRANGE__HEAD_ACCEPT_2 =
      GET.arrange()
          .arrangeUrl(TEST_PATH)
          .arrangeHead()
          .arrangeAccept(APPLICATION_XML, APPLICATION_JSON)
          .act();

  public static final TestAct1 GET_ARRANGE__HEAD_ACCEPT_1_AUTH =
      GET.arrange()
          .arrangeUrl(TEST_PATH)
          .arrangeHead()
          .arrangeAccept(APPLICATION_JSON)
          .arrangeAuth(TEST_AUTH_VALUE)
          .act();

  public static final TestAct1 GET_ARRANGE__HEAD_ACCEPT_1_KEY_VALUE_1 =
      GET.arrange()
          .arrangeUrl(TEST_PATH)
          .arrangeHead()
          .arrangeAccept(APPLICATION_JSON)
          .arrangeKeyValue(TEST_HEADER_KEY_1, TEST_HEADER_VALUE_1)
          .act();

  public static final TestAct1 GET_ARRANGE__HEAD_ACCEPT_1_KEY_VALUE_2 =
      GET.arrange()
          .arrangeUrl(TEST_PATH)
          .arrangeHead()
          .arrangeAccept(APPLICATION_JSON)
          .arrangeKeyValue(TEST_HEADER_KEY_1, TEST_HEADER_VALUE_1)
          .arrangeKeyValue(TEST_HEADER_KEY_2, TEST_HEADER_VALUE_2)
          .act();

  public static final TestAct1 GET_ARRANGE__HEAD_ACCEPT_1_KEY_VALUE_MAP =
      GET.arrange()
          .arrangeUrl(TEST_PATH)
          .arrangeHead()
          .arrangeAccept(APPLICATION_JSON)
          .arrangeKeyValue(TEST_HEADER_MAP_1_2)
          .act();

  public static final TestAct1 GET_ARRANGE__HEAD_AUT_KEY_VALUE_1 =
      GET.arrange()
          .arrangeUrl(TEST_PATH)
          .arrangeHead()
          .arrangeAuth(TEST_AUTH_VALUE)
          .arrangeKeyValue(TEST_HEADER_KEY_1, TEST_HEADER_VALUE_1)
          .act();

  public static final TestAct1 GET_ARRANGE__HEAD_AUT_KEY_VALUE_2 =
      GET.arrange()
          .arrangeUrl(TEST_PATH)
          .arrangeHead()
          .arrangeAuth(TEST_AUTH_VALUE)
          .arrangeKeyValue(TEST_HEADER_KEY_1, TEST_HEADER_VALUE_1)
          .arrangeKeyValue(TEST_HEADER_KEY_2, TEST_HEADER_VALUE_2)
          .act();

  public static final TestAct1 GET_ARRANGE__HEAD_AUT_KEY_VALUE_MAP =
      GET.arrange()
          .arrangeUrl(TEST_PATH)
          .arrangeHead()
          .arrangeAuth(TEST_AUTH_VALUE)
          .arrangeKeyValue(TEST_HEADER_MAP_1_2)
          .act();

  public static final TestArrange1GetHead GET_ARRANGE__PARAM_KEY_VALUE_1__HEAD =
      GET.arrange()
          .arrangeUrl(TEST_PATH)
          .arrangeParam()
          .arrangeKeyValue(TEST_PARAM_KEY_1, TEST_PARAM_VALUE_1)
          .arrangeHead();

  public static final TestArrange1GetHead GET_ARRANGE__PARAM_KEY_VALUE_2__HEAD =
      GET.arrange()
          .arrangeUrl(TEST_PATH)
          .arrangeParam()
          .arrangeKeyValue(TEST_PARAM_KEY_1, TEST_PARAM_VALUE_1)
          .arrangeKeyValue(TEST_PARAM_KEY_2, TEST_PARAM_VALUE_2)
          .arrangeHead();

  public static final TestArrange1GetHead GET_ARRANGE__PARAM_KEY_VALUE_MAP__HEAD =
      GET.arrange()
          .arrangeUrl(TEST_PATH)
          .arrangeParam()
          .arrangeKeyValue(TEST_PARAM_KEY_VALUE_MAP_1_2)
          .arrangeHead();
}
