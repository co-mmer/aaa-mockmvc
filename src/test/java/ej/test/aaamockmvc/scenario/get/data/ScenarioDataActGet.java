package ej.test.aaamockmvc.scenario.get.data;

import static ej.test.aaamockmvc.testdata.testutil.TestDataMockMvc.MOCK_MVC;
import static ej.test.aaamockmvc.testdata.testutil.TestHeader.TEST_AUTH_VALUE;
import static ej.test.aaamockmvc.testdata.testutil.TestHeader.TEST_HEADER_KEY_1;
import static ej.test.aaamockmvc.testdata.testutil.TestHeader.TEST_HEADER_KEY_2;
import static ej.test.aaamockmvc.testdata.testutil.TestHeader.TEST_HEADER_MAP_1_2;
import static ej.test.aaamockmvc.testdata.testutil.TestHeader.TEST_HEADER_VALUE_1;
import static ej.test.aaamockmvc.testdata.testutil.TestHeader.TEST_HEADER_VALUE_2;
import static ej.test.aaamockmvc.testdata.testutil.TestParameter.TEST_PARAM_KEY_1;
import static ej.test.aaamockmvc.testdata.testutil.TestParameter.TEST_PARAM_KEY_2;
import static ej.test.aaamockmvc.testdata.testutil.TestParameter.TEST_PARAM_KEY_VALUE_MAP_1_2;
import static ej.test.aaamockmvc.testdata.testutil.TestParameter.TEST_PARAM_VALUE_1;
import static ej.test.aaamockmvc.testdata.testutil.TestParameter.TEST_PARAM_VALUE_2;
import static ej.test.aaamockmvc.testdata.testutil.TestValue.TEST_PATH;
import static ej.test.aaamockmvc.testdata.testutil.TestValue.TEST_PATH_VAR_1;
import static ej.test.aaamockmvc.testdata.testutil.TestValue.TEST_PATH_VAR_2;
import static ej.test.aaamockmvc.testdata.testutil.TestValue.VAR_STRING_1;
import static ej.test.aaamockmvc.testdata.testutil.TestValue.VAR_STRING_2;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_XML;

import ej.test.aaamockmvc.request.TestRequestGet;
import ej.test.aaamockmvc.request.act.TestAct1Perform;
import ej.test.aaamockmvc.request.arrange.get.head.TestArrange1GetHead;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ScenarioDataActGet {

  private static final TestRequestGet GET = new TestRequestGet(MOCK_MVC);

  public static final TestAct1Perform GET_ARRANGE__URL = GET.arrange().arrangeUrl(TEST_PATH).act();

  public static final TestAct1Perform GET_ARRANGE__URL_VARIABLE =
      GET.arrange().arrangeUrl(TEST_PATH_VAR_1, VAR_STRING_1).act();

  public static final TestAct1Perform GET_ARRANGE__URL_VARIABLES =
      GET.arrange().arrangeUrl(TEST_PATH_VAR_2, VAR_STRING_1, VAR_STRING_2).act();

  public static final TestAct1Perform GET_ARRANGE__PARAM_KEY_VALUE_1 =
      GET.arrange()
          .arrangeUrl(TEST_PATH)
          .arrangeParam()
          .arrangeKeyValue(TEST_PARAM_KEY_1, TEST_PARAM_VALUE_1)
          .act();

  public static final TestAct1Perform GET_ARRANGE__PARAM_KEY_VALUE_2 =
      GET.arrange()
          .arrangeUrl(TEST_PATH)
          .arrangeParam()
          .arrangeKeyValue(TEST_PARAM_KEY_1, TEST_PARAM_VALUE_1)
          .arrangeKeyValue(TEST_PARAM_KEY_2, TEST_PARAM_VALUE_2)
          .act();

  public static final TestAct1Perform GET_ARRANGE__PARAM_KEY_VALUE_MAP =
      GET.arrange()
          .arrangeUrl(TEST_PATH)
          .arrangeParam()
          .arrangeKeyValue(TEST_PARAM_KEY_VALUE_MAP_1_2)
          .act();

  public static final TestAct1Perform GET_ARRANGE__HEAD_KEY_VALUE_1 =
      GET.arrange()
          .arrangeUrl(TEST_PATH)
          .arrangeHead()
          .arrangeKeyValue(TEST_HEADER_KEY_1, TEST_HEADER_VALUE_1)
          .act();

  public static final TestAct1Perform GET_ARRANGE__HEAD_KEY_VALUE_2 =
      GET.arrange()
          .arrangeUrl(TEST_PATH)
          .arrangeHead()
          .arrangeKeyValue(TEST_HEADER_KEY_1, TEST_HEADER_VALUE_1)
          .arrangeKeyValue(TEST_HEADER_KEY_2, TEST_HEADER_VALUE_2)
          .act();

  public static final TestAct1Perform GET_ARRANGE__HEAD_KEY_VALUE_MAP =
      GET.arrange().arrangeUrl(TEST_PATH).arrangeHead().arrangeKeyValue(TEST_HEADER_MAP_1_2).act();

  public static final TestAct1Perform GET_ARRANGE__HEAD_AUTH =
      GET.arrange().arrangeUrl(TEST_PATH).arrangeHead().arrangeAuth(TEST_AUTH_VALUE).act();

  public static final TestAct1Perform GET_ARRANGE__HEAD_ACCEPT_1 =
      GET.arrange().arrangeUrl(TEST_PATH).arrangeHead().arrangeAccept(APPLICATION_JSON).act();

  public static final TestAct1Perform GET_ARRANGE__HEAD_ACCEPT_2 =
      GET.arrange()
          .arrangeUrl(TEST_PATH)
          .arrangeHead()
          .arrangeAccept(APPLICATION_XML, APPLICATION_JSON)
          .act();

  public static final TestAct1Perform GET_ARRANGE__HEAD_CONTENT_TYPE_1 =
      GET.arrange().arrangeUrl(TEST_PATH).arrangeHead().arrangeContentType(APPLICATION_JSON).act();

  public static final TestAct1Perform GET_ARRANGE__HEAD_CONTENT_TYPE_2 =
      GET.arrange()
          .arrangeUrl(TEST_PATH)
          .arrangeHead()
          .arrangeContentType(APPLICATION_JSON, APPLICATION_XML)
          .act();

  public static final TestAct1Perform GET_ARRANGE__HEAD_ACCEPT_1_AUTH =
      GET.arrange()
          .arrangeUrl(TEST_PATH)
          .arrangeHead()
          .arrangeAccept(APPLICATION_JSON)
          .arrangeAuth(TEST_AUTH_VALUE)
          .act();

  public static final TestAct1Perform GET_ARRANGE__HEAD_ACCEPT_1_CONTENT_TYPE_1 =
      GET.arrange()
          .arrangeUrl(TEST_PATH)
          .arrangeHead()
          .arrangeAccept(APPLICATION_XML)
          .arrangeContentType(APPLICATION_JSON)
          .act();

  public static final TestAct1Perform GET_ARRANGE__HEAD_ACCEPT_1_KEY_VALUE_1 =
      GET.arrange()
          .arrangeUrl(TEST_PATH)
          .arrangeHead()
          .arrangeAccept(APPLICATION_JSON)
          .arrangeKeyValue(TEST_HEADER_KEY_1, TEST_HEADER_VALUE_1)
          .act();

  public static final TestAct1Perform GET_ARRANGE__HEAD_ACCEPT_1_KEY_VALUE_2 =
      GET.arrange()
          .arrangeUrl(TEST_PATH)
          .arrangeHead()
          .arrangeAccept(APPLICATION_JSON)
          .arrangeKeyValue(TEST_HEADER_KEY_1, TEST_HEADER_VALUE_1)
          .arrangeKeyValue(TEST_HEADER_KEY_2, TEST_HEADER_VALUE_2)
          .act();

  public static final TestAct1Perform GET_ARRANGE__HEAD_ACCEPT_1_KEY_VALUE_MAP =
      GET.arrange()
          .arrangeUrl(TEST_PATH)
          .arrangeHead()
          .arrangeAccept(APPLICATION_JSON)
          .arrangeKeyValue(TEST_HEADER_MAP_1_2)
          .act();

  public static final TestAct1Perform GET_ARRANGE__HEAD_AUT_CONTENT_TYPE_1 =
      GET.arrange()
          .arrangeUrl(TEST_PATH)
          .arrangeHead()
          .arrangeAuth(TEST_AUTH_VALUE)
          .arrangeContentType(APPLICATION_JSON)
          .act();

  public static final TestAct1Perform GET_ARRANGE__HEAD_AUT_KEY_VALUE_1 =
      GET.arrange()
          .arrangeUrl(TEST_PATH)
          .arrangeHead()
          .arrangeAuth(TEST_AUTH_VALUE)
          .arrangeKeyValue(TEST_HEADER_KEY_1, TEST_HEADER_VALUE_1)
          .act();

  public static final TestAct1Perform GET_ARRANGE__HEAD_AUT_KEY_VALUE_2 =
      GET.arrange()
          .arrangeUrl(TEST_PATH)
          .arrangeHead()
          .arrangeAuth(TEST_AUTH_VALUE)
          .arrangeKeyValue(TEST_HEADER_KEY_1, TEST_HEADER_VALUE_1)
          .arrangeKeyValue(TEST_HEADER_KEY_2, TEST_HEADER_VALUE_2)
          .act();

  public static final TestAct1Perform GET_ARRANGE__HEAD_AUT_KEY_VALUE_MAP =
      GET.arrange()
          .arrangeUrl(TEST_PATH)
          .arrangeHead()
          .arrangeAuth(TEST_AUTH_VALUE)
          .arrangeKeyValue(TEST_HEADER_MAP_1_2)
          .act();

  public static final TestAct1Perform GET_ARRANGE__HEAD_CONTENT_TYPE_KEY_VALUE_1 =
      GET.arrange()
          .arrangeUrl(TEST_PATH)
          .arrangeHead()
          .arrangeContentType(APPLICATION_XML)
          .arrangeKeyValue(TEST_HEADER_KEY_1, TEST_HEADER_VALUE_1)
          .act();

  public static final TestAct1Perform GET_ARRANGE__HEAD_CONTENT_TYPE_KEY_VALUE_2 =
      GET.arrange()
          .arrangeUrl(TEST_PATH)
          .arrangeHead()
          .arrangeContentType(APPLICATION_XML)
          .arrangeKeyValue(TEST_HEADER_KEY_1, TEST_HEADER_VALUE_1)
          .arrangeKeyValue(TEST_HEADER_KEY_2, TEST_HEADER_VALUE_2)
          .act();

  public static final TestAct1Perform GET_ARRANGE__HEAD_CONTENT_TYPE_KEY_VALUE_MAP =
      GET.arrange()
          .arrangeUrl(TEST_PATH)
          .arrangeHead()
          .arrangeContentType(APPLICATION_XML)
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
