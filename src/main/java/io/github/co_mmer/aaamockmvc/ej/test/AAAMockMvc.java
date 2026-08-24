package io.github.co_mmer.aaamockmvc.ej.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.co_mmer.aaamockmvc.ej.test.web.act.TestAct;
import io.github.co_mmer.aaamockmvc.ej.test.web.answer.TestAnswer;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.TestArrange;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.TestAssert;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.context.TestAAAContext;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.context.TestEnvironment;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.step.TestStepDto;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.step.TestStepImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.step.TestStepValidator;
import lombok.NonNull;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Fluent, thread-safe test helper for the AAA (Arrange–Act–Assert–Answer) pattern on top of
 * Spring's {@link org.springframework.test.web.servlet.MockMvc}.
 *
 * <p><strong>What it does</strong>
 *
 * <ul>
 *   <li>Provides a clear, guided flow for tests: {@code arrange() → actPerform() →
 *       asserts()/answer()}.
 *   <li>Stores per-test state in a {@link ThreadLocal}, so parallel test execution is safe.
 *   <li>Lets you start from a {@link org.springframework.web.context.WebApplicationContext} or an
 *       already-built {@link org.springframework.test.web.servlet.MockMvc}, and optionally plug in
 *       a custom {@link com.fasterxml.jackson.databind.ObjectMapper}.
 * </ul>
 *
 * <p><strong>Typical usage</strong>
 *
 * <pre>{@code
 * @Autowired
 * AAAMockMvc aaa;
 *
 * aaa.arrange()
 *      .get("/api/v1/user")
 *      .query("key", "value");
 *
 * aaa.actPerform()
 *      .perform();
 *
 * aaa.asserts()
 *      .status()
 *      .isOk()
 *      .content()
 *      .asClass(User.class)
 *      .isNotNull()
 *      .isEqualTo(TestUser.USER1);
 *
 * }</pre>
 *
 * <p><strong>Lifecycle and pitfalls</strong>
 *
 * <ul>
 *   <li>Start each step with {@link #arrange()} (creates a fresh thread-local arrange).
 *   <li>Calling {@link #act()} or {@link #asserts()} without a prior {@code arrange()} throws an
 *       {@link IllegalStateException}.
 *   <li>Attempting to {@code arrange()} again while a arrange is active throws an {@link
 *       IllegalArgumentException}.
 *   <li>Framework-internal cleanup is performed automatically when the fluent chain completes;
 *       {@link #clearContext()} is provided for advanced/manual cleanup (e.g., custom runners).
 * </ul>
 *
 * @since 1.0.0
 */
public final class AAAMockMvc {

  private static final ThreadLocal<TestStepImpl> CURRENT = new ThreadLocal<>();

  private final TestEnvironment environment;

  /**
   * Creates an instance backed by a {@link org.springframework.web.context.WebApplicationContext}.
   * Builds an internal {@link org.springframework.test.web.servlet.MockMvc} with default
   * configuration and a default {@link com.fasterxml.jackson.databind.ObjectMapper}.
   *
   * @param webApplicationContext the Spring {@code WebApplicationContext} used to initialize {@code
   *     MockMvc} (must not be {@code null})
   * @throws NullPointerException if {@code webApplicationContext} is {@code null}
   * @since 1.0.0
   */
  public AAAMockMvc(@NonNull WebApplicationContext webApplicationContext) {
    var mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    this.environment = new TestEnvironment(mockMvc, new ObjectMapper());
  }

  /**
   * Creates an instance backed by a {@link org.springframework.web.context.WebApplicationContext}
   * with a custom {@link com.fasterxml.jackson.databind.ObjectMapper} for JSON serialization.
   *
   * @param webApplicationContext the Spring {@code WebApplicationContext} used to initialize {@code
   *     MockMvc} (must not be {@code null})
   * @param objectMapper the {@code ObjectMapper} to use for JSON serialization (must not be {@code
   *     null})
   * @throws NullPointerException if either argument is {@code null}
   * @since 1.0.0
   */
  public AAAMockMvc(
      @NonNull WebApplicationContext webApplicationContext, @NonNull ObjectMapper objectMapper) {
    var mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    this.environment = new TestEnvironment(mockMvc, objectMapper);
  }

  /**
   * Creates an instance using an existing, fully configured {@link
   * org.springframework.test.web.servlet.MockMvc}. A default {@link
   * com.fasterxml.jackson.databind.ObjectMapper} is used for JSON serialization.
   *
   * @param mockMvc the {@code MockMvc} instance to perform requests with (must not be {@code null})
   * @throws NullPointerException if {@code mockMvc} is {@code null}
   * @since 1.0.0
   */
  public AAAMockMvc(@NonNull MockMvc mockMvc) {
    this.environment = new TestEnvironment(mockMvc, new ObjectMapper());
  }

  /**
   * Creates an instance using an existing {@link org.springframework.test.web.servlet.MockMvc} and
   * a custom {@link com.fasterxml.jackson.databind.ObjectMapper} for JSON serialization.
   *
   * @param mockMvc the {@code MockMvc} instance to perform requests with (must not be {@code null})
   * @param objectMapper the {@code ObjectMapper} to use for JSON serialization (must not be {@code
   *     null})
   * @throws NullPointerException if either argument is {@code null}
   * @since 1.0.0
   */
  public AAAMockMvc(@NonNull MockMvc mockMvc, @NonNull ObjectMapper objectMapper) {
    this.environment = new TestEnvironment(mockMvc, objectMapper);
  }

  /**
   * Starts a new Arrange phase and initializes a fresh thread-local test arrange for the current
   * thread. Must be the first call in a step; calling it again without completing/clearing the
   * previous arrange will fail fast.
   *
   * @return the next step in the fluent assertion chain, exposing only arrange-appropriate methods
   *     based on the current state.
   * @throws IllegalArgumentException if a arrange is already active for the current thread
   * @since 2.0.0
   */
  public TestArrange arrange() {
    var current = CURRENT.get();
    return current != null ? current.arrange() : newArrange();
  }

  private TestArrange newArrange() {
    var context = new TestAAAContext(this.environment);
    var step = new TestStepImpl(context);
    CURRENT.set(step);
    return step.arrange();
  }

  /**
   * Transitions from Arrange to Act. Binds the internal {@link TestEnvironment} (with {@code
   * MockMvc} and {@code ObjectMapper}) to the current test arrange so that HTTP interactions can be
   * performed.
   *
   * @return the next step in the fluent assertion chain, exposing only arrange-appropriate methods
   *     based on the current state.
   * @throws IllegalStateException if called before {@link #arrange()}
   * @since 2.0.0
   */
  public TestAct act() {
    TestStepValidator.preconditionsOfAct(CURRENT.get());
    var section = CURRENT.get();
    return section.act();
  }

  /**
   * Enters the Assert phase to verify outcomes (status, headers, body, etc.) after actions have
   * been executed.
   *
   * @return the next step in the fluent assertion chain, exposing only arrange-appropriate methods
   *     based on the current state.
   * @throws IllegalStateException if called before {@link #arrange()}
   * @since 2.0.0
   */
  public TestAssert asserts() {
    TestStepValidator.preconditionsOfAsserts(CURRENT.get());
    var step = CURRENT.get();
    return step.asserts();
  }

  /**
   * Enters the Answer phase for scenarios that validate returned values or captured responses
   *
   * @return the next step in the fluent assertion chain, exposing only arrange-appropriate methods
   *     based on the current state.
   * @throws IllegalStateException if called before {@link #arrange()}
   * @since 2.0.0
   */
  public TestAnswer answer() {
    TestStepValidator.preconditionsOfAnswer(CURRENT.get());
    var step = CURRENT.get();
    return step.answer();
  }

  /**
   * Runs a named AAA <b>step</b> as a single block and returns the <em>captured answer</em> from
   * within that block.
   *
   * <p><b>What it does:</b> Creates an isolated step context, invokes the given {@code block}
   * (where you call {@code arrange() → act().perform() → asserts()/answer()}), and then returns the
   * value produced by the {@code s.answer().asXxx(...)} call inside the block. If no answer was
   * captured, this method returns {@code null}. No additional network I/O occurs after {@code
   * perform()}—assertions and answers operate on the cached snapshot.
   *
   * <p><b>Semantics:</b>
   *
   * <ul>
   *   <li><b>No answer inside the block:</b> the return value is {@code null}.
   *   <li><b>Type inference:</b> {@code R} is inferred from assignment context (e.g., {@code
   *       UserResponse r = step(...)}). A mismatch between the captured answer type and {@code R}
   *       will cause a {@link ClassCastException} at runtime.
   *   <li><b>Isolated flow:</b> Each invocation uses a fresh step context; multiple sections can be
   *       executed in the same test safely.
   * </ul>
   *
   * <p><b>Typical usage — with implicit return:</b>
   *
   * <pre>{@code
   * UserResponse created = step("Create user", () -> {
   *   arrange()
   *      .post("/api/users")
   *      .body()
   *      .json(new User("alice"));
   *   act()
   *      .perform();
   *   asserts()
   *      .status()
   *      .isCreated()
   *      .content()
   *      .asClass(UserResponse.class)
   *      .isNotNull();
   *   answer().asObject(UserResponse.class);
   * });
   * }</pre>
   *
   * <p><b>Typical usage — no answer captured:</b>
   *
   * <pre>{@code
   * step("Update user", () -> {
   *   arrange()
   *      .put("/api/users/{id}", 42)
   *      .body()
   *      .json(new UpdateUser("alice", "active"));
   *   act()
   *     .perform();
   *   asserts()
   *      .status()
   *      .isOk();
   * });
   * }</pre>
   *
   * <p><b>Tip:</b> If your codebase prefers stricter type safety, provide an overload like {@code
   * step(String, Class<R>, Consumer<TestStepDto>)} that validates the captured answer type against
   * {@code R} and fails with a clear message instead of a {@code ClassCastException}.
   *
   * @param stepName human-readable step name used in logs and error messages (must not be {@code
   *     null} or blank)
   * @param block the step body; declare {@code arrange()}, execute with {@code act().perform()},
   *     and optionally capture an answer via {@code s.answer().asXxx(...)}
   * @param <R> the expected return type inferred from the assignment context
   * @return the value produced by the last answer call inside the block (one of):
   *     <ul>
   *       <li>{@code answer().asObject(Class)}
   *       <li>{@code answer().asList(Class)}
   *       <li>{@code answer().asSet(Class)}
   *       <li>{@code answer().asMap(Class, Class)}
   *       <li>{@code answer().asString()}
   *       <li>{@code answer().asByte()}
   *     </ul>
   *     or {@code null} if no answer was captured
   * @throws ClassCastException if the captured answer cannot be cast to {@code R}
   * @since 2.0.0
   */
  public <R> R step(@NonNull String stepName, @NonNull Runnable block) {
    var context = new TestAAAContext(this.environment);
    context.setStep(new TestStepDto(stepName));
    var step = new TestStepImpl(context);

    var prev = CURRENT.get();
    try {
      CURRENT.set(step);
      block.run();
    } finally {
      CURRENT.set(prev);
    }

    var answerResult = context.getAnswerResult();
    if (answerResult == null) {
      return null;
    }

    @SuppressWarnings("unchecked")
    R result = (R) answerResult.actualContent();
    return result;
  }

  /**
   * Clears the thread-local test arrange for the current thread.
   *
   * <p>Normally not required when using the fluent flow correctly; provided for advanced use cases
   * (e.g., custom runners or manual lifecycle management).
   *
   * @since 2.0.0
   */
  static void clearContext() {
    CURRENT.remove();
  }
}
