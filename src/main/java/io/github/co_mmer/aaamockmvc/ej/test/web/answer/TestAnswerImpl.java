package io.github.co_mmer.aaamockmvc.ej.test.web.answer;

import static io.github.co_mmer.aaamockmvc.ej.test.web.mapper.TestGenericMapper.mapTo;
import static io.github.co_mmer.aaamockmvc.ej.test.web.mapper.TestGenericMapper.mapToList;
import static io.github.co_mmer.aaamockmvc.ej.test.web.mapper.TestGenericMapper.mapToMap;
import static io.github.co_mmer.aaamockmvc.ej.test.web.mapper.TestGenericMapper.mapToSet;

import io.github.co_mmer.aaamockmvc.ej.test.web.answer.exception.TestAnswerException;
import io.github.co_mmer.aaamockmvc.ej.test.web.mapper.exception.TestGenericMapperException;
import io.github.co_mmer.aaamockmvc.ej.test.web.request.context.TestRequestBean;
import io.github.co_mmer.aaamockmvc.ej.test.web.request.context.TestRequestContext;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.NonNull;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

/**
 * This class represents the implementation of the {@link TestAnswer} interface, providing methods
 * for executing HTTP requests and retrieving their responses in a testing context.
 *
 * @since 1.2.0
 */
public final class TestAnswerImpl implements TestAnswer {

  private final TestRequestBean bean;
  private final MockHttpServletRequestBuilder requestBuilder;

  /**
   * Constructs an instance of {@code TestAnswerImpl} with the specified request context and request
   * builder.
   *
   * @param context the {@link TestRequestContext} containing the request context and associated
   *     beans (must not be {@code null})
   * @param requestBuilder the {@link MockHttpServletRequestBuilder} used to build and perform the
   *     HTTP request (must not be {@code null})
   * @throws NullPointerException if either {@code context} or {@code requestBuilder} is {@code
   *     null}
   * @since 1.2.0
   */
  public TestAnswerImpl(
      @NonNull TestRequestContext context, @NonNull MockHttpServletRequestBuilder requestBuilder) {

    this.bean = context.bean();
    this.requestBuilder = requestBuilder;
  }

  /**
   * Performs the HTTP request and returns the actions result.
   *
   * @return the {@link ResultActions} for the performed request
   * @throws TestAnswerException if an error occurs while performing the request
   * @since 1.2.0
   */
  @Override
  public ResultActions answerAsResultActions() throws TestAnswerException {
    return this.resultActions();
  }

  private ResultActions resultActions() throws TestAnswerException {
    try {
      return this.bean.mvc().perform(this.requestBuilder);
    } catch (Exception e) {
      throw new TestAnswerException(e);
    }
  }

  /**
   * Retrieves the content of the response as a string.
   *
   * @return the response content as a string
   * @throws TestAnswerException if an error occurs while retrieving the content
   * @since 1.2.0
   */
  @Override
  public String answerAsString() throws TestAnswerException {
    try {
      return getResponse().getContentAsString();
    } catch (UnsupportedEncodingException e) {
      throw new TestAnswerException(e);
    }
  }

  private MockHttpServletResponse getResponse() throws TestAnswerException {
    return answerAsResultActions().andReturn().getResponse();
  }

  /**
   * Retrieves the response as a byte array.
   *
   * @return the response content as a byte array
   * @throws TestAnswerException if an error occurs while retrieving the response
   * @since 1.2.0
   */
  @Override
  public byte[] answerAsByte() throws TestAnswerException {
    return getResponse().getContentAsByteArray();
  }

  /**
   * Retrieves the response as an instance of the specified class type.
   *
   * @param resultType the target class to map the response content to (must not be {@code null})
   * @param <T> the type of the object to be returned
   * @return an instance of the specified type populated with the response data
   * @throws NullPointerException if the {@code targetClass} is {@code null}
   * @throws TestAnswerException if an error occurs during the mapping process
   * @since 1.3.0
   */
  @Override
  public <T> T answerAsObject(@NonNull Class<T> resultType) throws TestAnswerException {
    try {
      var result = this.resultActions().andReturn();
      return mapTo(this.bean.objectMapper(), result, resultType);
    } catch (TestGenericMapperException e) {
      throw new TestAnswerException(e);
    }
  }

  /**
   * Retrieves the response as a {@link List} of the specified class type.
   *
   * @param elementType the target class type for each element in the list (must not be {@code
   *     null})
   * @param <T> the type of each element in the list
   * @return a {@link List} populated with elements of the specified type
   * @throws NullPointerException if the {@code targetClass} is {@code null}
   * @throws TestAnswerException if an error occurs during the mapping process
   * @since 1.3.0
   */
  @Override
  public <T> List<T> answerAsList(@NonNull Class<T> elementType) throws TestAnswerException {
    try {
      var result = this.resultActions().andReturn();
      return mapToList(this.bean.objectMapper(), result, elementType);
    } catch (TestGenericMapperException e) {
      throw new TestAnswerException(e);
    }
  }

  /**
   * Retrieves the response as a {@link Set} of the specified class type.
   *
   * @param elementType the target class type for each element in the set (must not be {@code null})
   * @param <T> the type of each element in the set
   * @return a {@link Set} populated with elements of the specified type
   * @throws NullPointerException if the {@code targetClass} is {@code null}
   * @throws TestAnswerException if an error occurs during the mapping process
   * @since 1.3.0
   */
  @Override
  public <T> Set<T> answerAsSet(@NonNull Class<T> elementType) throws TestAnswerException {
    try {
      var result = this.resultActions().andReturn();
      return mapToSet(this.bean.objectMapper(), result, elementType);
    } catch (TestGenericMapperException e) {
      throw new TestAnswerException(e);
    }
  }

  /**
   * Retrieves the response as a {@link Map} with the specified key and value types.
   *
   * @param keyType the target class type for the map keys (must not be {@code null})
   * @param valueType the target class type for the map values (must not be {@code null})
   * @param <K> the type of keys in the map
   * @param <V> the type of values in the map
   * @return a {@link Map} populated with keys and values of the specified types
   * @throws NullPointerException if either {@code targetKeyClass} or {@code targetValueClass} is
   *     {@code null}
   * @throws TestAnswerException if an error occurs during the mapping process
   * @since 1.3.0
   */
  @Override
  public <K, V> Map<K, V> answerAsMap(@NonNull Class<K> keyType, @NonNull Class<V> valueType)
      throws TestAnswerException {

    try {
      var result = this.resultActions().andReturn();
      return mapToMap(this.bean.objectMapper(), result, keyType, valueType);
    } catch (TestGenericMapperException e) {
      throw new TestAnswerException(e);
    }
  }

  /**
   * Retrieves the value of a specific header from the response.
   *
   * @param key the name of the header to retrieve (must not be {@code null})
   * @return the value of the specified header, or {@code null} if not present
   * @throws TestAnswerException if an error occurs while retrieving the header
   * @since 1.2.0
   */
  @Override
  public String answerHeader(String key) throws TestAnswerException {
    return getResponse().getHeader(key);
  }

  /**
   * Performs the HTTP request but does not return the result.
   *
   * @throws TestAnswerException if an error occurs while performing the request
   * @since 1.2.0
   */
  @Override
  public void answerVoid() throws TestAnswerException {
    answerAsResultActions();
  }
}
