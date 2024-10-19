package ej.aaamockmvc.test.scenario.res.data;

import static ej.aaamockmvc.test.testdata.testutil.TestBody.TEST_BODY_JSON;
import static ej.aaamockmvc.test.testdata.testutil.TestBody.TEST_BODY_XML;
import static ej.aaamockmvc.test.testdata.testutil.TestFiles.TEST_FILE_1;
import static ej.aaamockmvc.test.testdata.testutil.TestFiles.TEST_FILE_2;
import static ej.aaamockmvc.test.testdata.testutil.TestFiles.TEST_FILE_3;
import static ej.aaamockmvc.test.testdata.testutil.TestFiles.TEST_FILE_4;
import static ej.aaamockmvc.test.testdata.testutil.TestHeader.TEST_AUTH_VALUE;
import static ej.aaamockmvc.test.testdata.testutil.TestHeader.TEST_HEADER_KEY_1;
import static ej.aaamockmvc.test.testdata.testutil.TestHeader.TEST_HEADER_KEY_2;
import static ej.aaamockmvc.test.testdata.testutil.TestHeader.TEST_HEADER_MAP_1_2;
import static ej.aaamockmvc.test.testdata.testutil.TestHeader.TEST_HEADER_VALUE_1;
import static ej.aaamockmvc.test.testdata.testutil.TestHeader.TEST_HEADER_VALUE_2;
import static ej.aaamockmvc.test.testdata.testutil.TestObject.TEST_REQUEST_CONFIG;
import static ej.aaamockmvc.test.testdata.testutil.TestParameter.TEST_PARAM_KEY_1;
import static ej.aaamockmvc.test.testdata.testutil.TestParameter.TEST_PARAM_KEY_2;
import static ej.aaamockmvc.test.testdata.testutil.TestParameter.TEST_PARAM_KEY_VALUE_MAP_1_2;
import static ej.aaamockmvc.test.testdata.testutil.TestParameter.TEST_PARAM_VALUE_1;
import static ej.aaamockmvc.test.testdata.testutil.TestParameter.TEST_PARAM_VALUE_2;
import static ej.aaamockmvc.test.testdata.testutil.TestValue.TEST_PATH;
import static ej.aaamockmvc.test.testdata.testutil.TestValue.TEST_PATH_VAR_1;
import static ej.aaamockmvc.test.testdata.testutil.TestValue.TEST_PATH_VAR_2;
import static ej.aaamockmvc.test.testdata.testutil.TestValue.VAR_STRING_1;
import static ej.aaamockmvc.test.testdata.testutil.TestValue.VAR_STRING_2;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_PDF;
import static org.springframework.http.MediaType.APPLICATION_XML;

import ej.aaamockmvc.test.web.act.TestAct1;
import ej.aaamockmvc.test.web.arrange.res.body.TestArrange1ResBody;
import ej.aaamockmvc.test.web.arrange.res.head.TestArrange2ResHead;
import ej.aaamockmvc.test.web.request.TestRequestPost;
import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ScenarioDataActPost {

  private static final TestRequestPost POST = new TestRequestPost(TEST_REQUEST_CONFIG);

  public static final TestAct1 POST_ARRANGE__URL = POST.arrange().arrangeUrl(TEST_PATH).act();

  public static final TestAct1 POST_ARRANGE__URL_VARIABLE =
      POST.arrange().arrangeUrl(TEST_PATH_VAR_1, VAR_STRING_1).act();

  public static final TestAct1 POST_ARRANGE__URL_VARIABLES =
      POST.arrange().arrangeUrl(TEST_PATH_VAR_2, VAR_STRING_1, VAR_STRING_2).act();

  public static final TestAct1 POST_ARRANGE__PARAM_KEY_VALUE_1 =
      POST.arrange()
          .arrangeUrl(TEST_PATH)
          .arrangeParam()
          .arrangeKeyValue(TEST_PARAM_KEY_1, TEST_PARAM_VALUE_1)
          .act();

  public static final TestAct1 POST_ARRANGE__PARAM_KEY_VALUE_2 =
      POST.arrange()
          .arrangeUrl(TEST_PATH)
          .arrangeParam()
          .arrangeKeyValue(TEST_PARAM_KEY_1, TEST_PARAM_VALUE_1)
          .arrangeKeyValue(TEST_PARAM_KEY_2, TEST_PARAM_VALUE_2)
          .act();

  public static final TestAct1 POST_ARRANGE__PARAM_KEY_VALUE_MAP =
      POST.arrange()
          .arrangeUrl(TEST_PATH)
          .arrangeParam()
          .arrangeKeyValue(TEST_PARAM_KEY_VALUE_MAP_1_2)
          .act();

  public static final TestAct1 POST_ARRANGE__HEAD_ACCEPT_1 =
      POST.arrange().arrangeUrl(TEST_PATH).arrangeHead().arrangeAccept(APPLICATION_JSON).act();

  public static final TestAct1 POST_ARRANGE__HEAD_ACCEPT_2 =
      POST.arrange()
          .arrangeUrl(TEST_PATH)
          .arrangeHead()
          .arrangeAccept(APPLICATION_JSON, APPLICATION_PDF)
          .act();

  public static final TestAct1 POST_ARRANGE__HEAD_AUTH =
      POST.arrange().arrangeUrl(TEST_PATH).arrangeHead().arrangeAuth(TEST_AUTH_VALUE).act();

  public static final TestAct1 POST_ARRANGE__HEAD_CONTENT_TYPE_1 =
      POST.arrange().arrangeUrl(TEST_PATH).arrangeHead().arrangeContentType(APPLICATION_JSON).act();

  public static final TestAct1 POST_ARRANGE__HEAD_CONTENT_TYPE_2 =
      POST.arrange()
          .arrangeUrl(TEST_PATH)
          .arrangeHead()
          .arrangeContentType(APPLICATION_JSON, APPLICATION_PDF)
          .act();
  public static final TestAct1 POST_ARRANGE__HEAD_KEY_VALUE_1 =
      POST.arrange()
          .arrangeUrl(TEST_PATH)
          .arrangeHead()
          .arrangeKeyValue(TEST_HEADER_KEY_1, TEST_HEADER_VALUE_1)
          .act();

  public static final TestAct1 POST_ARRANGE__HEAD_KEY_VALUE_2 =
      POST.arrange()
          .arrangeUrl(TEST_PATH)
          .arrangeHead()
          .arrangeKeyValue(TEST_HEADER_KEY_1, TEST_HEADER_VALUE_1)
          .arrangeKeyValue(TEST_HEADER_KEY_2, TEST_HEADER_VALUE_2)
          .act();

  public static final TestAct1 POST_ARRANGE__HEAD_KEY_VALUE_MAP =
      POST.arrange().arrangeUrl(TEST_PATH).arrangeHead().arrangeKeyValue(TEST_HEADER_MAP_1_2).act();

  public static final TestAct1 POST_ARRANGE__BODY_BINARY_FILE_1 =
      POST.arrange().arrangeUrl(TEST_PATH).arrangeBody().arrangeFile(TEST_FILE_1).act();

  public static final TestAct1 POST_ARRANGE__BODY_BINARY_FILE_2 =
      POST.arrange()
          .arrangeUrl(TEST_PATH)
          .arrangeBody()
          .arrangeFile(TEST_FILE_1)
          .arrangeFile(TEST_FILE_2)
          .act();

  public static final TestAct1 POST_ARRANGE__BODY_BINARY_FILES =
      POST.arrange()
          .arrangeUrl(TEST_PATH)
          .arrangeBody()
          .arrangeFiles(List.of(TEST_FILE_1, TEST_FILE_2))
          .act();

  public static final TestAct1 POST_ARRANGE__BODY_BINARY_FILE_1_FILES =
      POST.arrange()
          .arrangeUrl(TEST_PATH)
          .arrangeBody()
          .arrangeFile(TEST_FILE_1)
          .arrangeFiles(List.of(TEST_FILE_2, TEST_FILE_3))
          .act();

  public static final TestAct1 POST_ARRANGE__BODY_BINARY_FILE_2_FILES =
      POST.arrange()
          .arrangeUrl(TEST_PATH)
          .arrangeBody()
          .arrangeFile(TEST_FILE_1)
          .arrangeFile(TEST_FILE_2)
          .arrangeFiles(List.of(TEST_FILE_3, TEST_FILE_4))
          .act();

  public static final TestAct1 POST_ARRANGE__BODY_BINARY_FILES_FILES =
      POST.arrange()
          .arrangeUrl(TEST_PATH)
          .arrangeBody()
          .arrangeFiles(List.of(TEST_FILE_1, TEST_FILE_2))
          .arrangeFiles(List.of(TEST_FILE_3, TEST_FILE_4))
          .act();

  public static final TestAct1 POST_ARRANGE__BODY_JSON =
      POST.arrange().arrangeUrl(TEST_PATH).arrangeBody().arrangeJson(TEST_BODY_JSON).act();

  public static final TestAct1 POST_ARRANGE__BODY_CONTENT_JSON =
      POST.arrange()
          .arrangeUrl(TEST_PATH)
          .arrangeBody()
          .arrangeContent(TEST_BODY_JSON, APPLICATION_JSON)
          .act();

  public static final TestAct1 POST_ARRANGE__BODY_CONTENT_XML =
      POST.arrange()
          .arrangeUrl(TEST_PATH)
          .arrangeBody()
          .arrangeContent(TEST_BODY_XML, APPLICATION_XML)
          .act();

  public static final TestAct1 POST_ARRANGE__HEAD_ACCEPT_1_KEY_VALUE_1 =
      POST.arrange()
          .arrangeUrl(TEST_PATH)
          .arrangeHead()
          .arrangeAccept(APPLICATION_JSON)
          .arrangeKeyValue(TEST_HEADER_KEY_1, TEST_HEADER_VALUE_1)
          .act();

  public static final TestAct1 POST_ARRANGE__HEAD_ACCEPT_1_KEY_VALUE_2 =
      POST.arrange()
          .arrangeUrl(TEST_PATH)
          .arrangeHead()
          .arrangeAccept(APPLICATION_JSON)
          .arrangeKeyValue(TEST_HEADER_KEY_1, TEST_HEADER_VALUE_1)
          .arrangeKeyValue(TEST_HEADER_KEY_2, TEST_HEADER_VALUE_2)
          .act();

  public static final TestAct1 POST_ARRANGE__HEAD_ACCEPT_1_KEY_VALUE_MAP =
      POST.arrange()
          .arrangeUrl(TEST_PATH)
          .arrangeHead()
          .arrangeAccept(APPLICATION_JSON)
          .arrangeKeyValue(TEST_HEADER_MAP_1_2)
          .act();

  public static final TestAct1 POST_ARRANGE__HEAD_AUT_CONTENT_TYPE_1 =
      POST.arrange()
          .arrangeUrl(TEST_PATH)
          .arrangeHead()
          .arrangeAuth(TEST_AUTH_VALUE)
          .arrangeContentType(APPLICATION_JSON)
          .act();

  public static final TestAct1 POST_ARRANGE__HEAD_AUT_KEY_VALUE_1 =
      POST.arrange()
          .arrangeUrl(TEST_PATH)
          .arrangeHead()
          .arrangeAuth(TEST_AUTH_VALUE)
          .arrangeKeyValue(TEST_HEADER_KEY_1, TEST_HEADER_VALUE_1)
          .act();

  public static final TestAct1 POST_ARRANGE__HEAD_AUT_KEY_VALUE_2 =
      POST.arrange()
          .arrangeUrl(TEST_PATH)
          .arrangeHead()
          .arrangeAuth(TEST_AUTH_VALUE)
          .arrangeKeyValue(TEST_HEADER_KEY_1, TEST_HEADER_VALUE_1)
          .arrangeKeyValue(TEST_HEADER_KEY_2, TEST_HEADER_VALUE_2)
          .act();

  public static final TestAct1 POST_ARRANGE__HEAD_AUT_KEY_VALUE_MAP =
      POST.arrange()
          .arrangeUrl(TEST_PATH)
          .arrangeHead()
          .arrangeAuth(TEST_AUTH_VALUE)
          .arrangeKeyValue(TEST_HEADER_MAP_1_2)
          .act();

  public static final TestAct1 POST_ARRANGE__HEAD_ACCEPT_1_CONTENT_TYPE_1 =
      POST.arrange()
          .arrangeUrl(TEST_PATH)
          .arrangeHead()
          .arrangeAccept(APPLICATION_JSON)
          .arrangeContentType(APPLICATION_XML)
          .act();

  public static final TestAct1 POST_ARRANGE__HEAD_ACCEPT_1_AUTH =
      POST.arrange()
          .arrangeUrl(TEST_PATH)
          .arrangeHead()
          .arrangeAccept(APPLICATION_JSON)
          .arrangeAuth(TEST_AUTH_VALUE)
          .act();

  public static final TestAct1 POST_ARRANGE__HEAD_CONTENT_TYPE_KEY_VALUE_1 =
      POST.arrange()
          .arrangeUrl(TEST_PATH)
          .arrangeHead()
          .arrangeContentType(APPLICATION_XML)
          .arrangeKeyValue(TEST_HEADER_KEY_1, TEST_HEADER_VALUE_1)
          .act();

  public static final TestAct1 POST_ARRANGE__HEAD_CONTENT_TYPE_KEY_VALUE_2 =
      POST.arrange()
          .arrangeUrl(TEST_PATH)
          .arrangeHead()
          .arrangeContentType(APPLICATION_XML)
          .arrangeKeyValue(TEST_HEADER_KEY_1, TEST_HEADER_VALUE_1)
          .arrangeKeyValue(TEST_HEADER_KEY_2, TEST_HEADER_VALUE_2)
          .act();

  public static final TestAct1 POST_ARRANGE__HEAD_CONTENT_TYPE_KEY_VALUE_MAP =
      POST.arrange()
          .arrangeUrl(TEST_PATH)
          .arrangeHead()
          .arrangeContentType(APPLICATION_XML)
          .arrangeKeyValue(TEST_HEADER_MAP_1_2)
          .act();

  public static final TestArrange2ResHead POST_ARRANGE__PARAM_KEY_VALUE_1__HEAD =
      POST.arrange()
          .arrangeUrl(TEST_PATH)
          .arrangeParam()
          .arrangeKeyValue(TEST_PARAM_KEY_1, TEST_PARAM_VALUE_1)
          .arrangeHead();

  public static final TestArrange2ResHead POST_ARRANGE__PARAM_KEY_VALUE_2__HEAD =
      POST.arrange()
          .arrangeUrl(TEST_PATH)
          .arrangeParam()
          .arrangeKeyValue(TEST_PARAM_KEY_1, TEST_PARAM_VALUE_1)
          .arrangeKeyValue(TEST_PARAM_KEY_2, TEST_PARAM_VALUE_2)
          .arrangeHead();

  public static final TestArrange2ResHead POST_ARRANGE__PARAM_KEY_VALUE_MAP__HEAD =
      POST.arrange()
          .arrangeUrl(TEST_PATH)
          .arrangeParam()
          .arrangeKeyValue(TEST_PARAM_KEY_VALUE_MAP_1_2)
          .arrangeHead();

  public static final TestArrange1ResBody POST_ARRANGE__PARAM_KEY_VALUE_1__BODY =
      POST.arrange()
          .arrangeUrl(TEST_PATH)
          .arrangeParam()
          .arrangeKeyValue(TEST_PARAM_KEY_1, TEST_PARAM_VALUE_1)
          .arrangeBody();

  public static final TestArrange1ResBody POST_ARRANGE__PARAM_KEY_VALUE_2__BODY =
      POST.arrange()
          .arrangeUrl(TEST_PATH)
          .arrangeParam()
          .arrangeKeyValue(TEST_PARAM_KEY_1, TEST_PARAM_VALUE_1)
          .arrangeKeyValue(TEST_PARAM_KEY_2, TEST_PARAM_VALUE_2)
          .arrangeBody();

  public static final TestArrange1ResBody POST_ARRANGE__PARAM_KEY_VALUE_MAP__BODY =
      POST.arrange()
          .arrangeUrl(TEST_PATH)
          .arrangeParam()
          .arrangeKeyValue(TEST_PARAM_KEY_VALUE_MAP_1_2)
          .arrangeBody();
}
