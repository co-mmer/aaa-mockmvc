package io.github.co_mmer.aaamockmvc.ej.test;

import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestDataMockMvc.MOCK_MVC;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_A1_JSON;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.TEST_URI;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.act.mapper.TestActResultMapper;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestAAAContext;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestActResult;
import io.github.co_mmer.aaamockmvc.ej.testdata.testmock.MockTestActResultMapper;
import java.util.stream.Stream;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest(classes = WebApplicationContext.class)
class AAAMockMvcTest {

  @Autowired private WebApplicationContext context;

  @AfterEach
  void tearDown() {
    AAAMockMvc.clearContext();
  }

  @Nested
  class Constructors {

    @Test
    @SuppressWarnings("ConstantConditions")
    void GIVEN_null_WebApplicationContext_WHEN_ctor_THEN_NPE() {
      // Arrange

      // Act & Assert
      assertThrows(NullPointerException.class, () -> new AAAMockMvc((WebApplicationContext) null));
    }

    @Test
    @SuppressWarnings("ConstantConditions")
    void GIVEN_null_MockMvc_WHEN_ctor_THEN_NPE() {
      // Arrange

      // Act & Assert
      assertThrows(NullPointerException.class, () -> new AAAMockMvc((MockMvc) null));
    }

    @ParameterizedTest
    @MethodSource("provideNullMockMvcObjectMapper")
    @SuppressWarnings("ConstantConditions")
    void GIVEN_nulls_for_MockMvc_or_ObjectMapper_WHEN_ctor_THEN_NPE(MockMvc mvc, ObjectMapper om) {
      // Arrange

      // Act & Assert
      assertThrows(NullPointerException.class, () -> new AAAMockMvc(mvc, om));
    }

    static Stream<Arguments> provideNullMockMvcObjectMapper() {
      return Stream.of(
          Arguments.of(null, mock(ObjectMapper.class)),
          Arguments.of(mock(MockMvc.class), null),
          Arguments.of(null, null));
    }

    @Test
    void GIVEN_WebApplicationContext_WHEN_ctor_THEN_notNull() {
      // Arrange

      // Act
      var aaa = new AAAMockMvc(context);

      // Assert
      assertThat(aaa, is(notNullValue()));
    }

    @Test
    void GIVEN_WebApplicationContext_and_ObjectMapper_WHEN_ctor_THEN_notNull() {
      // Arrange

      // Act
      var aaa = new AAAMockMvc(context, new ObjectMapper());

      // Assert
      assertThat(aaa, is(notNullValue()));
    }

    @Test
    void GIVEN_MockMvc_WHEN_ctor_THEN_notNull() {
      // Arrange

      // Act
      var aaa = new AAAMockMvc(MOCK_MVC);

      // Assert
      assertThat(aaa, is(notNullValue()));
    }

    @Test
    void GIVEN_MockMvc_and_ObjectMapper_WHEN_ctor_THEN_notNull() {
      // Arrange

      // Act
      var aaa = new AAAMockMvc(MOCK_MVC, new ObjectMapper());

      // Assert
      assertThat(aaa, is(notNullValue()));
    }
  }

  @Nested
  class Arrange {

    @Test
    @SneakyThrows
    void GIVEN_fresh_instance_WHEN_arrange_THEN_environmentIsBoundIntoContext() {
      // Arrange
      var aaa = new AAAMockMvc(MOCK_MVC);

      // Act
      aaa.arrange();

      // Grab CURRENT
      var fieldCurrent = AAAMockMvc.class.getDeclaredField("CURRENT");
      fieldCurrent.setAccessible(true);
      var threadLocal = (ThreadLocal<?>) fieldCurrent.get(null);
      var step = threadLocal.get();

      // Grab context
      var fieldContext = step.getClass().getDeclaredField("context");
      fieldContext.setAccessible(true);
      var context = (TestAAAContext) fieldContext.get(step);

      // Extract environment from aaa (for identity check)
      var fieldEnvironment = AAAMockMvc.class.getDeclaredField("environment");
      fieldEnvironment.setAccessible(true);
      var environmentFromAaa = fieldEnvironment.get(aaa);

      // Assert
      assertThat(context.getEnvironment(), is(notNullValue()));
      assertThat(context.getEnvironment(), sameInstance(environmentFromAaa));
    }

    @Test
    @SneakyThrows
    void GIVEN_fresh_instance_WHEN_arrange_THEN_contextHasNullAsMetadata() {
      // Arrange
      var aaa = new AAAMockMvc(MOCK_MVC);

      // Act
      aaa.arrange();

      // Grab CURRENT
      var fieldCurrent = AAAMockMvc.class.getDeclaredField("CURRENT");
      fieldCurrent.setAccessible(true);
      var threadLocal = (ThreadLocal<?>) fieldCurrent.get(null);
      var step = threadLocal.get();
      assertThat(step, is(notNullValue()));

      // Grab context
      var fieldContext = step.getClass().getDeclaredField("context");
      fieldContext.setAccessible(true);

      // Assert
      var context = (TestAAAContext) fieldContext.get(step);
      assertThat(context, is(notNullValue()));
      assertThat(context.getStep(), is(nullValue()));
    }
  }

  @Nested
  class ArrangePreconditions {

    @Test
    void GIVEN_no_arrange_WHEN_asserts_THEN_IllegalState_with_message() {
      // Arrange
      var aaa = new AAAMockMvc(MOCK_MVC);

      // Act & Assert
      var ex = assertThrows(IllegalStateException.class, aaa::asserts);
      assertThat(
          ex.getMessage(),
          is(
              "Assert error: No 'arrange()' step configured. Call arrange().get|post|put|patch|delete|head|options(...) before 'asserts()'"));
    }

    @Test
    void GIVEN_no_arrange_WHEN_answer_THEN_IllegalState_with_message() {
      // Arrange
      var aaa = new AAAMockMvc(MOCK_MVC);

      // Act & Assert
      var ex = assertThrows(IllegalStateException.class, aaa::answer);
      assertThat(
          ex.getMessage(),
          is(
              "Answer error: No arrange step configured. Call arrange().get|post|put|patch|delete|head|options(...) before 'answer()'"));
    }

    @Test
    void GIVEN_arrange_active_WHEN_arrange_again_THEN_IllegalState_with_message() {
      // Arrange
      var aaa = new AAAMockMvc(MOCK_MVC);
      aaa.arrange();

      // Act & Assert
      var ex = assertThrows(IllegalStateException.class, aaa::arrange);
      assertThat(ex.getMessage(), is("Arrange error: Arrange phase already started"));
    }
  }

  @Nested
  class ActPreconditions {

    @Test
    void GIVEN_no_arranged_called_WHEN_act_THEN_throwIllegalStateWithMessage() {
      // Arrange
      var aaa = new AAAMockMvc(MOCK_MVC);

      // Act & Assert
      var ex = assertThrows(IllegalStateException.class, aaa::act);
      assertThat(
          ex.getMessage(),
          is(
              "Act error: No arrange step configured. Call arrange().get|post|put|patch|delete|head|options(...) before act().perform()"));
    }

    @Test
    void GIVEN_arranged_called_WHEN_act_called_THEN_throwNoException() {
      // Arrange
      var aaa = new AAAMockMvc(MOCK_MVC);
      aaa.arrange();

      // Act & Assert
      assertDoesNotThrow(aaa::act);
    }
  }

  @Nested
  class AssertPreconditions {

    @Test
    void GIVEN_no_act_perform_WHEN_asserts_THEN_IllegalState_with_message() {
      // Arrange
      var aaa = new AAAMockMvc(MOCK_MVC);
      aaa.arrange();
      aaa.act();

      // Act & Assert
      var ex = assertThrows(IllegalStateException.class, aaa::asserts);
      assertThat(
          ex.getMessage(),
          is(
              "Assert error: No 'act()' step configured. Call 'act().perform()' before 'asserts()'"));
    }

    @Test
    void GIVEN_full_flow_WHEN_asserts_THEN_returns_api() {
      // Arrange
      var mockResult = Mockito.mock(TestActResult.class);
      var mockTestActResultMapper = Mockito.mockStatic(TestActResultMapper.class);
      mockTestActResultMapper.when(() -> TestActResultMapper.mapTo(any())).thenReturn(mockResult);

      var aaa = new AAAMockMvc(MOCK_MVC);
      aaa.arrange().get("/any");

      // Act
      aaa.act().perform();

      // Assert
      var asserts = aaa.asserts();
      assertThat(asserts, is(notNullValue()));
      mockTestActResultMapper.close();
    }
  }

  @Nested
  class AnswerPreconditions {

    @Test
    void GIVEN_no_act_perform_WHEN_answer_THEN_IllegalState_with_message() {
      // Arrange
      var aaa = new AAAMockMvc(MOCK_MVC);
      aaa.arrange();
      aaa.act();

      // Act & Assert
      var ex = assertThrows(IllegalStateException.class, aaa::answer);
      assertThat(
          ex.getMessage(),
          is("Answer error: No 'act()' step configured. Call 'act().perform()' before 'answer()'"));
    }

    @Test
    void GIVEN_full_flow_WHEN_answer_THEN_returns_api() {
      // Arrange
      var mockResult = Mockito.mock(TestActResult.class);
      var mockTestActResultMapper = Mockito.mockStatic(TestActResultMapper.class);
      mockTestActResultMapper.when(() -> TestActResultMapper.mapTo(any())).thenReturn(mockResult);

      var aaa = new AAAMockMvc(MOCK_MVC);
      aaa.arrange().get("/any");

      // Act
      aaa.act().perform();

      // Assert
      var answer = aaa.answer();
      assertThat(answer, is(notNullValue()));
      mockTestActResultMapper.close();
    }
  }

  @Nested
  class Step {

    @Test
    void GIVEN_no_answer_was_captured_WHEN_step_THEN_return_null() {
      //  Arrange
      var aaa = new AAAMockMvc(MOCK_MVC);
      var mockTestActResultMapper = MockTestActResultMapper.mockWithResult();

      // Act
      String result =
          aaa.step(
              "No answer",
              s -> {
                s.arrange().get(TEST_URI);
                s.act().perform();
                s.asserts().status().isOk();
              });

      // Assert
      assertThat(result, is(nullValue()));
      mockTestActResultMapper.close();
    }

    @Test
    void GIVEN_answer_was_captured_WHEN_step_THEN_return_expected_content() {
      //  Arrange
      var aaa = new AAAMockMvc(MOCK_MVC);
      var mockTestActResultMapper = MockTestActResultMapper.mockWithResult();

      // Act
      String result =
          aaa.step(
              "With answer",
              s -> {
                s.arrange().get(TEST_URI);
                s.act().perform();
                s.asserts().status().isOk();
                s.answer().asString();
              });

      // Assert
      assertThat(result, is(TEST_A1_JSON));
      mockTestActResultMapper.close();
    }

    @Test
    void cast_mismatch_surfaces_as_ClassCastException_at_assignment_site() {
      //  Arrange
      var aaa = new AAAMockMvc(MOCK_MVC);
      var mockTestActResultMapper = MockTestActResultMapper.mockWithResult();

      // Act & Assert
      assertThrows(
          ClassCastException.class,
          () -> {
            @SuppressWarnings("unused")
            Integer wrong =
                aaa.step(
                    "Type mismatch",
                    s -> {
                      s.arrange().get(TEST_URI);
                      s.act().perform();
                      s.asserts().status().isOk();
                      s.answer().asString();
                    });
          });

      mockTestActResultMapper.close();
    }
  }
}
