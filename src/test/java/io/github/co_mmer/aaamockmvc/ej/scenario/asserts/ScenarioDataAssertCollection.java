package io.github.co_mmer.aaamockmvc.ej.scenario.asserts;

import static org.mockito.Mockito.mock;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.co_mmer.aaamockmvc.ej.test.web.answer.TestAnswer;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.collection.TestAssertCollectionImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.collection.TestAssertLCollection;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.head.TestAssertHead;
import java.util.Collections;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.test.web.servlet.ResultActions;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ScenarioDataAssertCollection {

  private static final TestAssertCollectionImpl IMPL = new TestAssertCollectionImpl(
      mock(ResultActions.class), mock(ObjectMapper.class));
  private static final Class<?> MOCK_CLASS = mock(Class.class);

  public static final TestAssertLCollection NOT_EMPTY__EQUALS =
      IMPL.assertCollectionNotEmpty()
          .assertCollectionEquals(MOCK_CLASS, Collections.emptyList());

  public static final TestAssertHead NOT_EMPTY__EQUALS__HEAD =
      IMPL.assertCollectionNotEmpty()
          .assertCollectionEquals(MOCK_CLASS, Collections.emptyList())
          .assertHead();

  public static final TestAnswer NOT_EMPTY__EQUALS__ANSWER =
      IMPL.assertCollectionNotEmpty()
          .assertCollectionEquals(MOCK_CLASS, Collections.emptyList())
          .answer();

  public static final TestAssertLCollection NOT_EMPTY__EQUALS_IGNORE_ORDER =
      IMPL.assertCollectionNotEmpty()
          .assertCollectionEqualsIgnoreOrder(MOCK_CLASS, Collections.emptyList());

  public static final TestAssertHead NOT_EMPTY__EQUALS_IGNORE_ORDER__HEAD =
      IMPL.assertCollectionNotEmpty()
          .assertCollectionEqualsIgnoreOrder(MOCK_CLASS, Collections.emptyList())
          .assertHead();

  public static final TestAnswer NOT_EMPTY__EQUALS_IGNORE_ORDER__ANSWER =
      IMPL.assertCollectionNotEmpty()
          .assertCollectionEqualsIgnoreOrder(MOCK_CLASS, Collections.emptyList())
          .answer();

  public static final TestAssertHead EMPTY__HEAD =
      IMPL.assertCollectionEmpty()
          .assertHead();

  public static final TestAnswer EMPTY__ANSWER =
      IMPL.assertCollectionEmpty()
          .answer();
}
