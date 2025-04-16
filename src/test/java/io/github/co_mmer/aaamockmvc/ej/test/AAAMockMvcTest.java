package io.github.co_mmer.aaamockmvc.ej.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.co_mmer.aaamockmvc.ej.test.web.request.TestRequestDelete;
import io.github.co_mmer.aaamockmvc.ej.test.web.request.TestRequestGet;
import io.github.co_mmer.aaamockmvc.ej.test.web.request.TestRequestHead;
import io.github.co_mmer.aaamockmvc.ej.test.web.request.TestRequestOption;
import io.github.co_mmer.aaamockmvc.ej.test.web.request.TestRequestPatch;
import io.github.co_mmer.aaamockmvc.ej.test.web.request.TestRequestPost;
import io.github.co_mmer.aaamockmvc.ej.test.web.request.TestRequestPut;
import io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestDataMockMvc;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest(classes = WebApplicationContext.class)
class AAAMockMvcTest {

  private AAAMockMvc aaaMockMvc;

  @Autowired private WebApplicationContext context;

  @BeforeEach
  public void setUp() {
    this.aaaMockMvc = new AAAMockMvc(context);
  }

  @Test
  @SuppressWarnings("ConstantConditions")
  void GIVEN_WebApplicationContext_null_WHEN_call_constructor_THEN_throw_NullPointerException() {
    assertThrows(NullPointerException.class, () -> new AAAMockMvc((WebApplicationContext) null));
  }

  @Test
  @SuppressWarnings("ConstantConditions")
  void GIVEN_MockMvc_null_WHEN_call_constructor_THEN_throw_NullPointerException() {
    assertThrows(NullPointerException.class, () -> new AAAMockMvc((MockMvc) null));
  }

  @ParameterizedTest()
  @MethodSource("provideNullMockMvcObjectMapper")
  @SuppressWarnings("ConstantConditions")
  void GIVEN_provideNullMockMvcObjectMapper_WHEN_call_constructor_THEN_throw_NullPointerException(
      MockMvc mockMvc, ObjectMapper objectMapper) {

    assertThrows(NullPointerException.class, () -> new AAAMockMvc(mockMvc, objectMapper));
  }

  private static Stream<Arguments> provideNullMockMvcObjectMapper() {
    return Stream.of(
        Arguments.of(null, mock(ObjectMapper.class)),
        Arguments.of(mock(MockMvc.class), null),
        Arguments.of(null, null));
  }

  @Test
  void WHEN_get_THEN_return_expected_class() {
    // Act
    var get = this.aaaMockMvc.get();

    // Assert
    assertThat(get.getClass(), is(TestRequestGet.class));
  }

  @Test
  void WHEN_post_THEN_return_expected_class() {
    // Act
    var post = this.aaaMockMvc.post();

    // Assert
    assertThat(post.getClass(), is(TestRequestPost.class));
  }

  @Test
  void WHEN_put_THEN_return_expected_class() {
    // Act
    var put = this.aaaMockMvc.put();

    // Assert
    assertThat(put.getClass(), is(TestRequestPut.class));
  }

  @Test
  void WHEN_patch_THEN_return_expected_class() {
    // Act
    var patch = this.aaaMockMvc.patch();

    // Assert
    assertThat(patch.getClass(), is(TestRequestPatch.class));
  }

  @Test
  void WHEN_delete_THEN_return_expected_class() {
    // Act
    var delete = this.aaaMockMvc.delete();

    // Assert
    assertThat(delete.getClass(), is(TestRequestDelete.class));
  }

  @Test
  void WHEN_option_THEN_return_expected_class() {
    // Act
    var option = this.aaaMockMvc.options();

    // Assert
    assertThat(option.getClass(), is(TestRequestOption.class));
  }

  @Test
  void WHEN_head_THEN_return_expected_class() {
    // Act
    var head = this.aaaMockMvc.head();

    // Assert
    assertThat(head.getClass(), is(TestRequestHead.class));
  }

  @Test
  void WHEN_call_constructor_context_THEN_return_not_null() {
    // Act
    var aaaMockMvcContext = new AAAMockMvc((WebApplicationContext) this.context);

    // Assert
    assertThat(aaaMockMvcContext, is(notNullValue()));
  }

  @Test
  void WHEN_call_constructor_context_ObjectMapper_THEN_return_not_null() {
    // Act
    var aaaMockMvcContext = new AAAMockMvc(this.context, new ObjectMapper());

    // Assert
    assertThat(aaaMockMvcContext, is(notNullValue()));
  }

  @Test
  void WHEN_call_constructor_mockMvc_THEN_return_not_null() {
    // Act
    var aaaMockMvcDefaultObjectMapper = new AAAMockMvc(TestDataMockMvc.MOCK_MVC);

    // Assert
    assertThat(aaaMockMvcDefaultObjectMapper, is(notNullValue()));
  }

  @Test
  void WHEN_call_constructor_mockMvc_ObjectMapper_THEN_return_not_null() {
    // Act
    var aaaMockMvcObjectMapper = new AAAMockMvc(TestDataMockMvc.MOCK_MVC, new ObjectMapper());

    // Assert
    assertThat(aaaMockMvcObjectMapper, is(notNullValue()));
  }
}
