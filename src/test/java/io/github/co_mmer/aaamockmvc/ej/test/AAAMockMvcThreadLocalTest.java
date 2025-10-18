package io.github.co_mmer.aaamockmvc.ej.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.sameInstance;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class AAAMockMvcThreadLocalTest {

  @SuppressWarnings("unchecked")
  @SneakyThrows
  private static ThreadLocal<Object> currentTL() {
    var field = AAAMockMvc.class.getDeclaredField("CURRENT");
    field.setAccessible(true);
    return (ThreadLocal<Object>) field.get(null);
  }

  private static AAAMockMvc newAaa() {
    var mockMvc = MockMvcBuilders.standaloneSetup().build();
    return new AAAMockMvc(mockMvc, new ObjectMapper());
  }

  @Test
  @SneakyThrows
  void GIVEN_thread_THEN_isolated_between_threads() {
    // Arrange
    var aaaMockMvc = newAaa();
    var threadLocal = currentTL();

    var start = new CountDownLatch(1);
    var done = new CountDownLatch(2);

    var thread1Context = new Object[1];
    var runnable1 = createWorker(start, done, aaaMockMvc, threadLocal, thread1Context);

    var thread2Context = new Object[1];
    var runnable2 = createWorker(start, done, aaaMockMvc, threadLocal, thread2Context);

    var executor = Executors.newFixedThreadPool(2);
    executor.submit(runnable1);
    executor.submit(runnable2);

    // Act
    start.countDown();
    var finished = done.await(5, TimeUnit.SECONDS);
    executor.shutdownNow();

    // Assert
    assertThat("Threads finished in time", finished, is(true));
    assertThat("Thread 1 context", thread1Context[0], notNullValue());
    assertThat("Thread 2 context", thread2Context[0], notNullValue());
    assertThat("contexts must differ", thread1Context[0], is(not(sameInstance(thread2Context[0]))));
  }

  private Runnable createWorker(
      CountDownLatch startGate,
      CountDownLatch doneGate,
      AAAMockMvc aaa,
      ThreadLocal<Object> threadLocal,
      Object[] targetSlot) {
    return () -> {
      try {
        // Wait until both threads are allowed to start
        startGate.await();

        // Call arrange() to set a ThreadLocal value for this thread
        aaa.arrange();

        // Read the ThreadLocal value for this thread and store it for later verification
        targetSlot[0] = threadLocal.get();
      } catch (InterruptedException ignored) {
        // Ignore interruption in this test context
      } finally {
        // Always clear the ThreadLocal to avoid leaking state
        AAAMockMvc.clearContext();

        // Signal that this thread has completed its work
        doneGate.countDown();
      }
    };
  }

  @Test
  void GIVEN_thread_WHEN_clearContext_THEN_remove_in_same_thread() {
    // Arrange
    var aaaMockMvc = newAaa();
    var threadLocal = currentTL();

    // Act
    aaaMockMvc.arrange();

    // Assert
    assertThat(threadLocal.get(), notNullValue());

    // Act
    AAAMockMvc.clearContext();

    // Assert
    assertThat(threadLocal.get(), nullValue());
  }
}
