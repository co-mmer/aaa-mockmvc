package io.github.co_mmer.aaamockmvc.ej.test;

import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestDataMockMvc.MOCK_MVC;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_A1_JSON;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.BASE_URI;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestAAAContext;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestEnvironment;
import io.github.co_mmer.aaamockmvc.ej.testdata.testmock.MockTestActResultMapper;
import java.util.stream.Stream;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest(classes = WebApplicationContext.class)
class AAAMockMvcTest {

  @Autowired private WebApplicationContext applicationContext;

  @AfterEach
  void tearDown() {
    AAAMockMvc.clearContext();
  }

  @Nested
  class Constructors {

    @Test
    @SuppressWarnings("ConstantConditions")
    void WHEN_constructor_with_null_WebApplicationContext_THEN_throws_NullPointerException() {
      // Arrange

      // Act & Assert
      assertThrows(NullPointerException.class, () -> new AAAMockMvc((WebApplicationContext) null));
    }

    @Test
    @SuppressWarnings("ConstantConditions")
    void WHEN_constructor_with_null_MockMvc_THEN_throws_NullPointerException() {
      // Arrange

      // Act & Assert
      assertThrows(NullPointerException.class, () -> new AAAMockMvc((MockMvc) null));
    }

    @ParameterizedTest
    @MethodSource("provideNullMockMvcObjectMapper")
    @SuppressWarnings("ConstantConditions")
    void WHEN_constructor_with_null_MockMvc_or_null_ObjectMapper_THEN_throws_NullPointerException(
        MockMvc mvc, ObjectMapper om) {
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
    void WHEN_constructor_with_WebApplicationContext_THEN_returns_nonNull_instance() {
      // Arrange

      // Act
      var aaa = new AAAMockMvc(applicationContext);

      // Assert
      assertThat(aaa, is(notNullValue()));
    }

    @Test
    void
        WHEN_constructor_with_WebApplicationContext_and_ObjectMapper_THEN_returns_nonNull_instance() {
      // Arrange

      // Act
      var aaa = new AAAMockMvc(applicationContext, new ObjectMapper());

      // Assert
      assertThat(aaa, is(notNullValue()));
    }

    @Test
    void WHEN_constructor_with_MockMvc_THEN_returns_nonNull_instance() {
      // Arrange

      // Act
      var aaa = new AAAMockMvc(MOCK_MVC);

      // Assert
      assertThat(aaa, is(notNullValue()));
    }

    @Test
    void WHEN_constructor_with_MockMvc_and_ObjectMapper_THEN_returns_nonNull_instance() {
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

    @Test
    void GIVEN_stepContext_WHEN_arrange_THEN_reuseExistingStepAndKeepMetadata() {
      // Arrange
      var aaa = new AAAMockMvc(MOCK_MVC);

      aaa.step(
          "Add Napoleon",
          () -> {
            var stepBefore = getCurrentStep();
            assertThat(stepBefore, is(notNullValue()));

            // Act
            aaa.arrange();

            // Assert
            var stepAfter = getCurrentStep();
            assertThat(stepAfter, sameInstance(stepBefore));

            var context = getField(stepAfter, "context", TestAAAContext.class);
            assertThat(context, is(notNullValue()));
            assertThat(context.getStep(), is(notNullValue()));
            assertThat(context.getStep().name(), is("Add Napoleon"));

            var envFromAaa = getField(aaa, "environment", TestEnvironment.class);
            assertThat(context.getEnvironment(), sameInstance(envFromAaa));
          });
    }

    @SneakyThrows
    private static Object getCurrentStep() {
      var f = AAAMockMvc.class.getDeclaredField("CURRENT");
      f.setAccessible(true);
      @SuppressWarnings("unchecked")
      var tl = (ThreadLocal<Object>) f.get(null);
      return tl.get();
    }

    @SneakyThrows
    private static <T> T getField(Object target, String name, Class<T> type) {
      var f = target.getClass().getDeclaredField(name);
      f.setAccessible(true);
      return type.cast(f.get(target));
    }
  }

  @Nested
  class InternalStepPreconditionsSuccess {

    @Test
    void WHEN_arrange_THEN_not_throw_Exception() {
      // Arrange
      var aaa = new AAAMockMvc(MOCK_MVC);

      // Act & Assert
      assertDoesNotThrow(aaa::arrange);
    }

    @Test
    void WHEN_arrange_act_THEN_not_throw_Exception() {
      // Arrange
      var aaa = new AAAMockMvc(MOCK_MVC);

      // Act & Assert
      assertDoesNotThrow(
          () -> {
            aaa.arrange().get(BASE_URI);
            aaa.act();
          });
    }

    @Test
    void WHEN_arrange_act_perform_asserts_THEN_not_throw_Exception() {
      // Arrange
      var aaa = new AAAMockMvc(MOCK_MVC);
      var mockTestActResultMapper = MockTestActResultMapper.mockWithResult();

      // Act & Assert
      assertDoesNotThrow(
          () -> {
            aaa.arrange().get(BASE_URI);
            aaa.act().perform();
            aaa.asserts().status().isOk();
          });

      mockTestActResultMapper.close();
    }

    @Test
    void WHEN_arrange_act_perform_asserts_answer_THEN_not_throw_Exception() {
      // Arrange
      var aaa = new AAAMockMvc(MOCK_MVC);
      var mockTestActResultMapper = MockTestActResultMapper.mockWithResult();

      // Act & Assert
      assertDoesNotThrow(
          () -> {
            aaa.arrange().get(BASE_URI);
            aaa.act().perform();
            aaa.asserts().status().isOk();
            aaa.answer().asString();
          });

      mockTestActResultMapper.close();
    }

    @Test
    void WHEN_arrange_act_perform_answer_THEN_not_throw_Exception() {
      // Arrange
      var aaa = new AAAMockMvc(MOCK_MVC);
      var mockTestActResultMapper = MockTestActResultMapper.mockWithResult();

      // Act & Assert
      assertDoesNotThrow(
          () -> {
            aaa.arrange().get(BASE_URI);
            aaa.act().perform();
            aaa.answer().asString();
          });

      mockTestActResultMapper.close();
    }
  }

  @Nested
  class InternalStepPreconditionsFailed {

    @Test
    void WHEN_act_THEN_noArrangeException() {
      // Arrange
      var aaa = new AAAMockMvc(MOCK_MVC);

      // Act
      var exception = assertThrows(IllegalStateException.class, aaa::act);

      // Assert
      assertThat(
          exception.getMessage(),
          is(
              "Act error: No 'arrange()' step configured. Call 'arrange().get|post|put|patch|delete|head|options(...)' before 'act()'"));
    }

    @Test
    @SuppressWarnings("java:S5778")
    void WHEN_act_perform_THEN_noArrangeException() {
      // Arrange
      var aaa = new AAAMockMvc(MOCK_MVC);

      // Act
      var exception = assertThrows(IllegalStateException.class, () -> aaa.act().perform());

      // Assert
      assertThat(
          exception.getMessage(),
          is(
              "Act error: No 'arrange()' step configured. Call 'arrange().get|post|put|patch|delete|head|options(...)' before 'act()'"));
    }

    @Test
    @SuppressWarnings("java:S5778")
    void WHEN_act_assert_THEN_noArrangeException() {
      // Arrange
      var aaa = new AAAMockMvc(MOCK_MVC);

      // Act
      var exception =
          assertThrows(
              IllegalStateException.class,
              () -> {
                aaa.act();
                aaa.asserts();
              });

      // Assert
      assertThat(
          exception.getMessage(),
          is(
              "Act error: No 'arrange()' step configured. Call 'arrange().get|post|put|patch|delete|head|options(...)' before 'act()'"));
    }

    @Test
    @SuppressWarnings("java:S5778")
    void WHEN_act_answer_THEN_noArrangeException() {
      // Arrange
      var aaa = new AAAMockMvc(MOCK_MVC);

      // Act
      var exception =
          assertThrows(
              IllegalStateException.class,
              () -> {
                aaa.act();
                aaa.answer();
              });

      // Assert
      assertThat(
          exception.getMessage(),
          is(
              "Act error: No 'arrange()' step configured. Call 'arrange().get|post|put|patch|delete|head|options(...)' before 'act()'"));
    }

    @Test
    void WHEN_assert_THEN_noAssertException() {
      // Arrange
      var aaa = new AAAMockMvc(MOCK_MVC);

      // Act
      var exception = assertThrows(IllegalStateException.class, aaa::asserts);

      // Assert
      assertThat(
          exception.getMessage(),
          is(
              "Assert error: No 'arrange()' / 'act()' steps configured. Call 'arrange().get|post|put|patch|delete|head|options(...)' followed by 'act().perform()' before 'asserts()'"));
    }

    @Test
    void WHEN_answer_THEN_noAssertException() {
      // Arrange
      var aaa = new AAAMockMvc(MOCK_MVC);

      // Act
      var exception = assertThrows(IllegalStateException.class, aaa::answer);

      // Assert
      assertThat(
          exception.getMessage(),
          is(
              "Answer error: No 'arrange()' / 'act()' steps configured. Call 'arrange().get|post|put|patch|delete|head|options(...)' followed by 'act().perform()' before 'answer()'"));
    }
  }

  @Nested
  class Step {

    @ParameterizedTest(name = "arrangeUrl=true, arrange=false, act={0}, assert={1}, answer={2}")
    @CsvSource({
      "false, false, false", // GIVEN_arrangeUrl
      "true,  false, false", // GIVEN_arrangeUrl_act
      "true,  true,  false", // GIVEN_arrangeUrl_act_assert
      "true,  false, true" // GIVEN_arrangeUrl_act_answer
    })
    void GIVEN_arrangeUrl_variants_WHEN_step_THEN_not_throw_Exception(
        boolean act, boolean assertStep, boolean answer) {

      // Arrange
      var aaa = new AAAMockMvc(MOCK_MVC);
      var mock = MockTestActResultMapper.mockWithResult();

      // Act & Assert
      assertDoesNotThrow(callStep(aaa, true, false, act, assertStep, answer));
      mock.close();
    }

    @Test
    void GIVEN_arrange_act_WHEN_step_THEN_not_throw_Exception() {
      //  Arrange
      var aaa = new AAAMockMvc(MOCK_MVC);
      var mockTestActResultMapper = MockTestActResultMapper.mockWithResult();

      // Act
      var exception =
          assertThrows(IllegalStateException.class, callStep(aaa, false, true, true, false, false));

      // Assert
      assertThat(
          exception.getMessage(),
          is(
              "Step 'StepTest' · Act error: No 'arrange()' step configured. Call 'arrange().get|post|put|patch|delete|head|options(...)' before 'act()'"));
      mockTestActResultMapper.close();
    }

    @Test
    void GIVEN_act_WHEN_step_THEN_throw_IllegalStateException() {
      //  Arrange
      var aaa = new AAAMockMvc(MOCK_MVC);
      var mockTestActResultMapper = MockTestActResultMapper.mockWithResult();

      // Act
      var exception =
          assertThrows(
              IllegalStateException.class, callStep(aaa, false, false, true, false, false));

      // Assert
      assertThat(
          exception.getMessage(),
          is(
              "Step 'StepTest' · Act error: No 'arrange()' step configured. Call 'arrange().get|post|put|patch|delete|head|options(...)' before 'act()'"));
      mockTestActResultMapper.close();
    }

    @Test
    void GIVEN_act_assert_WHEN_step_THEN_throw_IllegalStateException() {
      //  Arrange
      var aaa = new AAAMockMvc(MOCK_MVC);
      var mockTestActResultMapper = MockTestActResultMapper.mockWithResult();

      // Act
      var exception =
          assertThrows(IllegalStateException.class, callStep(aaa, false, false, true, true, false));

      // Assert
      assertThat(
          exception.getMessage(),
          is(
              "Step 'StepTest' · Act error: No 'arrange()' step configured. Call 'arrange().get|post|put|patch|delete|head|options(...)' before 'act()'"));
      mockTestActResultMapper.close();
    }

    @Test
    void GIVEN_act_answer_WHEN_step_THEN_throw_IllegalStateException() {
      //  Arrange
      var aaa = new AAAMockMvc(MOCK_MVC);
      var mockTestActResultMapper = MockTestActResultMapper.mockWithResult();

      // Act
      var exception =
          assertThrows(IllegalStateException.class, callStep(aaa, false, false, true, false, true));

      // Assert
      assertThat(
          exception.getMessage(),
          is(
              "Step 'StepTest' · Act error: No 'arrange()' step configured. Call 'arrange().get|post|put|patch|delete|head|options(...)' before 'act()'"));
      mockTestActResultMapper.close();
    }

    @Test
    void GIVEN_assert_WHEN_step_THEN_throw_IllegalStateException() {
      //  Arrange
      var aaa = new AAAMockMvc(MOCK_MVC);
      var mockTestActResultMapper = MockTestActResultMapper.mockWithResult();

      // Act
      var exception =
          assertThrows(
              IllegalStateException.class, callStep(aaa, false, false, false, true, false));

      // Assert
      assertThat(
          exception.getMessage(),
          is(
              "Step 'StepTest' · Assert error: No 'act()' step configured. Call 'act().perform()' before 'asserts()'"));
      mockTestActResultMapper.close();
    }

    @Test
    void GIVEN_arrange_assert_WHEN_step_THEN_throw_IllegalStateException() {
      //  Arrange
      var aaa = new AAAMockMvc(MOCK_MVC);
      var mockTestActResultMapper = MockTestActResultMapper.mockWithResult();

      // Act
      var exception =
          assertThrows(IllegalStateException.class, callStep(aaa, true, false, false, true, false));

      // Assert
      assertThat(
          exception.getMessage(),
          is(
              "Step 'StepTest' · Assert error: No 'act()' step configured. Call 'act().perform()' before 'asserts()'"));
      mockTestActResultMapper.close();
    }

    @Test
    void GIVEN_answer_WHEN_step_THEN_throw_IllegalStateException() {
      //  Arrange
      var aaa = new AAAMockMvc(MOCK_MVC);
      var mockTestActResultMapper = MockTestActResultMapper.mockWithResult();

      // Act
      var exception =
          assertThrows(
              IllegalStateException.class, callStep(aaa, false, false, false, false, true));

      // Assert
      assertThat(
          exception.getMessage(),
          is(
              "Step 'StepTest' · Answer error: No 'act()' step configured. Call 'act().perform()' before 'answer()'"));
      mockTestActResultMapper.close();
    }

    @Test
    void GIVEN_arrange_answer_WHEN_step_THEN_throw_IllegalStateException() {
      //  Arrange
      var aaa = new AAAMockMvc(MOCK_MVC);
      var mockTestActResultMapper = MockTestActResultMapper.mockWithResult();

      // Act
      var exception =
          assertThrows(IllegalStateException.class, callStep(aaa, true, false, false, false, true));

      // Assert
      assertThat(
          exception.getMessage(),
          is(
              "Step 'StepTest' · Answer error: No 'act()' step configured. Call 'act().perform()' before 'answer()'"));
      mockTestActResultMapper.close();
    }

    private static Executable callStep(
        AAAMockMvc aaa,
        boolean withArrangeUrl,
        boolean withArrange,
        boolean withAct,
        boolean withAssert,
        boolean withAnswer) {

      return () ->
          aaa.step(
              "StepTest",
              () -> {
                if (withArrangeUrl) {
                  aaa.arrange().get(BASE_URI);
                }
                if (withArrange) {
                  aaa.arrange();
                }
                if (withAct) {
                  aaa.act().perform();
                }
                if (withAssert) {
                  aaa.asserts().status().isOk();
                }
                if (withAnswer) {
                  aaa.answer().asString();
                }
              });
    }

    @Test
    void GIVEN_no_answer_was_captured_WHEN_step_THEN_return_null() {
      //  Arrange
      var aaa = new AAAMockMvc(MOCK_MVC);
      var mockTestActResultMapper = MockTestActResultMapper.mockWithResult();

      // Act
      String result =
          aaa.step(
              "No answer",
              () -> {
                aaa.arrange().get(BASE_URI);
                aaa.act().perform();
                aaa.asserts().status().isOk();
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
              () -> {
                aaa.arrange().get(BASE_URI);
                aaa.act().perform();
                aaa.asserts().status().isOk();
                aaa.answer().asString();
              });

      // Assert
      assertThat(result, is(TEST_A1_JSON));
      mockTestActResultMapper.close();
    }

    @Test
    void GIVEN_cast_mismatch_WHEN_step_THEN_throw_ClassCastException() {
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
                    () -> {
                      aaa.arrange().get(BASE_URI);
                      aaa.act().perform();
                      aaa.asserts().status().isOk();
                      aaa.answer().asString();
                    });
          });

      mockTestActResultMapper.close();
    }
  }
}
