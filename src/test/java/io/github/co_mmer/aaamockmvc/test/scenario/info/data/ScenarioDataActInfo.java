package io.github.co_mmer.aaamockmvc.test.scenario.info.data;

import static io.github.co_mmer.aaamockmvc.test.testdata.testutil.TestHeader.TEST_AUTH_VALUE;
import static io.github.co_mmer.aaamockmvc.test.testdata.testutil.TestHeader.TEST_HEADER_KEY_1;
import static io.github.co_mmer.aaamockmvc.test.testdata.testutil.TestHeader.TEST_HEADER_KEY_2;
import static io.github.co_mmer.aaamockmvc.test.testdata.testutil.TestHeader.TEST_HEADER_MAP_1_2;
import static io.github.co_mmer.aaamockmvc.test.testdata.testutil.TestHeader.TEST_HEADER_VALUE_1;
import static io.github.co_mmer.aaamockmvc.test.testdata.testutil.TestHeader.TEST_HEADER_VALUE_2;
import static io.github.co_mmer.aaamockmvc.test.testdata.testutil.TestObject.TEST_REQUEST_CONFIG;
import static io.github.co_mmer.aaamockmvc.test.testdata.testutil.TestParameter.TEST_PARAM_KEY_1;
import static io.github.co_mmer.aaamockmvc.test.testdata.testutil.TestParameter.TEST_PARAM_KEY_2;
import static io.github.co_mmer.aaamockmvc.test.testdata.testutil.TestParameter.TEST_PARAM_KEY_VALUE_MAP_1_2;
import static io.github.co_mmer.aaamockmvc.test.testdata.testutil.TestParameter.TEST_PARAM_VALUE_1;
import static io.github.co_mmer.aaamockmvc.test.testdata.testutil.TestParameter.TEST_PARAM_VALUE_2;
import static io.github.co_mmer.aaamockmvc.test.testdata.testutil.TestValue.TEST_PATH;
import static io.github.co_mmer.aaamockmvc.test.testdata.testutil.TestValue.TEST_PATH_VAR_1;
import static io.github.co_mmer.aaamockmvc.test.testdata.testutil.TestValue.TEST_PATH_VAR_2;
import static io.github.co_mmer.aaamockmvc.test.testdata.testutil.TestValue.VAR_STRING_1;
import static io.github.co_mmer.aaamockmvc.test.testdata.testutil.TestValue.VAR_STRING_2;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_XML;

import io.github.co_mmer.aaamockmvc.test.web.act.TestAct1;
import io.github.co_mmer.aaamockmvc.test.web.arrange.info.head.TestArrange1InfoHead;
import io.github.co_mmer.aaamockmvc.test.web.request.TestRequestHead;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ScenarioDataActInfo {

  private static final TestRequestHead HEAD = new TestRequestHead(TEST_REQUEST_CONFIG);

  public static final TestAct1 HEAD_ARRANGE__URL = HEAD.arrange().arrangeUrl(TEST_PATH).act();

  public static final TestAct1 HEAD_ARRANGE__URL_VARIABLE =
      HEAD.arrange().arrangeUrl(TEST_PATH_VAR_1, VAR_STRING_1).act();

  public static final TestAct1 HEAD_ARRANGE__URL_VARIABLES =
      HEAD.arrange().arrangeUrl(TEST_PATH_VAR_2, VAR_STRING_1, VAR_STRING_2).act();

  public static final TestAct1 HEAD_ARRANGE__PARAM_KEY_VALUE_1 =
      HEAD.arrange()
          .arrangeUrl(TEST_PATH)
          .arrangeParam()
          .arrangeKeyValue(TEST_PARAM_KEY_1, TEST_PARAM_VALUE_1)
          .act();

  public static final TestAct1 HEAD_ARRANGE__PARAM_KEY_VALUE_2 =
      HEAD.arrange()
          .arrangeUrl(TEST_PATH)
          .arrangeParam()
          .arrangeKeyValue(TEST_PARAM_KEY_1, TEST_PARAM_VALUE_1)
          .arrangeKeyValue(TEST_PARAM_KEY_2, TEST_PARAM_VALUE_2)
          .act();

  public static final TestAct1 HEAD_ARRANGE__PARAM_KEY_VALUE_MAP =
      HEAD.arrange()
          .arrangeUrl(TEST_PATH)
          .arrangeParam()
          .arrangeKeyValue(TEST_PARAM_KEY_VALUE_MAP_1_2)
          .act();

  public static final TestAct1 HEAD_ARRANGE__HEAD_AUTH =
      HEAD.arrange().arrangeUrl(TEST_PATH).arrangeHead().arrangeAuth(TEST_AUTH_VALUE).act();

  public static final TestAct1 HEAD_ARRANGE__HEAD_ACCEPT_1 =
      HEAD.arrange().arrangeUrl(TEST_PATH).arrangeHead().arrangeAccept(APPLICATION_JSON).act();

  public static final TestAct1 HEAD_ARRANGE__HEAD_ACCEPT_2 =
      HEAD.arrange()
          .arrangeUrl(TEST_PATH)
          .arrangeHead()
          .arrangeAccept(APPLICATION_XML, APPLICATION_JSON)
          .act();

  public static final TestAct1 HEAD_ARRANGE__HEAD_CONTENT_TYPE_1 =
      HEAD.arrange().arrangeUrl(TEST_PATH).arrangeHead().arrangeContentType(APPLICATION_JSON).act();

  public static final TestAct1 HEAD_ARRANGE__HEAD_CONTENT_TYPE_2 =
      HEAD.arrange()
          .arrangeUrl(TEST_PATH)
          .arrangeHead()
          .arrangeContentType(APPLICATION_JSON, APPLICATION_XML)
          .act();

  public static final TestAct1 HEAD_ARRANGE__HEAD_KEY_VALUE_1 =
      HEAD.arrange()
          .arrangeUrl(TEST_PATH)
          .arrangeHead()
          .arrangeKeyValue(TEST_HEADER_KEY_1, TEST_HEADER_VALUE_1)
          .act();

  public static final TestAct1 HEAD_ARRANGE__HEAD_KEY_VALUE_2 =
      HEAD.arrange()
          .arrangeUrl(TEST_PATH)
          .arrangeHead()
          .arrangeKeyValue(TEST_HEADER_KEY_1, TEST_HEADER_VALUE_1)
          .arrangeKeyValue(TEST_HEADER_KEY_2, TEST_HEADER_VALUE_2)
          .act();

  public static final TestAct1 HEAD_ARRANGE__HEAD_KEY_VALUE_MAP =
      HEAD.arrange().arrangeUrl(TEST_PATH).arrangeHead().arrangeKeyValue(TEST_HEADER_MAP_1_2).act();

  public static final TestAct1 HEAD_ARRANGE__HEAD_ACCEPT_1_AUTH =
      HEAD.arrange()
          .arrangeUrl(TEST_PATH)
          .arrangeHead()
          .arrangeAccept(APPLICATION_JSON)
          .arrangeAuth(TEST_AUTH_VALUE)
          .act();

  public static final TestAct1 HEAD_ARRANGE__HEAD_ACCEPT_1_CONTENT_TYPE_1 =
      HEAD.arrange()
          .arrangeUrl(TEST_PATH)
          .arrangeHead()
          .arrangeAccept(APPLICATION_XML)
          .arrangeContentType(APPLICATION_JSON)
          .act();

  public static final TestAct1 HEAD_ARRANGE__HEAD_ACCEPT_1_KEY_VALUE_1 =
      HEAD.arrange()
          .arrangeUrl(TEST_PATH)
          .arrangeHead()
          .arrangeAccept(APPLICATION_JSON)
          .arrangeKeyValue(TEST_HEADER_KEY_1, TEST_HEADER_VALUE_1)
          .act();

  public static final TestAct1 HEAD_ARRANGE__HEAD_ACCEPT_1_KEY_VALUE_2 =
      HEAD.arrange()
          .arrangeUrl(TEST_PATH)
          .arrangeHead()
          .arrangeAccept(APPLICATION_JSON)
          .arrangeKeyValue(TEST_HEADER_KEY_1, TEST_HEADER_VALUE_1)
          .arrangeKeyValue(TEST_HEADER_KEY_2, TEST_HEADER_VALUE_2)
          .act();

  public static final TestAct1 HEAD_ARRANGE__HEAD_ACCEPT_1_KEY_VALUE_MAP =
      HEAD.arrange()
          .arrangeUrl(TEST_PATH)
          .arrangeHead()
          .arrangeAccept(APPLICATION_JSON)
          .arrangeKeyValue(TEST_HEADER_MAP_1_2)
          .act();

  public static final TestAct1 HEAD_ARRANGE__HEAD_AUT_CONTENT_TYPE_1 =
      HEAD.arrange()
          .arrangeUrl(TEST_PATH)
          .arrangeHead()
          .arrangeAuth(TEST_AUTH_VALUE)
          .arrangeContentType(APPLICATION_JSON)
          .act();

  public static final TestAct1 HEAD_ARRANGE__HEAD_AUT_KEY_VALUE_1 =
      HEAD.arrange()
          .arrangeUrl(TEST_PATH)
          .arrangeHead()
          .arrangeAuth(TEST_AUTH_VALUE)
          .arrangeKeyValue(TEST_HEADER_KEY_1, TEST_HEADER_VALUE_1)
          .act();

  public static final TestAct1 HEAD_ARRANGE__HEAD_AUT_KEY_VALUE_2 =
      HEAD.arrange()
          .arrangeUrl(TEST_PATH)
          .arrangeHead()
          .arrangeAuth(TEST_AUTH_VALUE)
          .arrangeKeyValue(TEST_HEADER_KEY_1, TEST_HEADER_VALUE_1)
          .arrangeKeyValue(TEST_HEADER_KEY_2, TEST_HEADER_VALUE_2)
          .act();

  public static final TestAct1 HEAD_ARRANGE__HEAD_AUT_KEY_VALUE_MAP =
      HEAD.arrange()
          .arrangeUrl(TEST_PATH)
          .arrangeHead()
          .arrangeAuth(TEST_AUTH_VALUE)
          .arrangeKeyValue(TEST_HEADER_MAP_1_2)
          .act();

  public static final TestAct1 HEAD_ARRANGE__HEAD_CONTENT_TYPE_1_KEY_VALUE_1 =
      HEAD.arrange()
          .arrangeUrl(TEST_PATH)
          .arrangeHead()
          .arrangeContentType(APPLICATION_XML)
          .arrangeKeyValue(TEST_HEADER_KEY_1, TEST_HEADER_VALUE_1)
          .act();

  public static final TestAct1 HEAD_ARRANGE__HEAD_CONTENT_TYPE_1_KEY_VALUE_2 =
      HEAD.arrange()
          .arrangeUrl(TEST_PATH)
          .arrangeHead()
          .arrangeContentType(APPLICATION_XML)
          .arrangeKeyValue(TEST_HEADER_KEY_1, TEST_HEADER_VALUE_1)
          .arrangeKeyValue(TEST_HEADER_KEY_2, TEST_HEADER_VALUE_2)
          .act();

  public static final TestAct1 HEAD_ARRANGE__HEAD_CONTENT_TYPE_1_KEY_VALUE_MAP =
      HEAD.arrange()
          .arrangeUrl(TEST_PATH)
          .arrangeHead()
          .arrangeContentType(APPLICATION_XML)
          .arrangeKeyValue(TEST_HEADER_MAP_1_2)
          .act();

  public static final TestArrange1InfoHead HEAD_ARRANGE__PARAM_KEY_VALUE_1__HEAD =
      HEAD.arrange()
          .arrangeUrl(TEST_PATH)
          .arrangeParam()
          .arrangeKeyValue(TEST_PARAM_KEY_1, TEST_PARAM_VALUE_1)
          .arrangeHead();

  public static final TestArrange1InfoHead HEAD_ARRANGE__PARAM_KEY_VALUE_2__HEAD =
      HEAD.arrange()
          .arrangeUrl(TEST_PATH)
          .arrangeParam()
          .arrangeKeyValue(TEST_PARAM_KEY_1, TEST_PARAM_VALUE_1)
          .arrangeKeyValue(TEST_PARAM_KEY_2, TEST_PARAM_VALUE_2)
          .arrangeHead();

  public static final TestArrange1InfoHead HEAD_ARRANGE__PARAM_KEY_VALUE_MAP__HEAD =
      HEAD.arrange()
          .arrangeUrl(TEST_PATH)
          .arrangeParam()
          .arrangeKeyValue(TEST_PARAM_KEY_VALUE_MAP_1_2)
          .arrangeHead();
}
