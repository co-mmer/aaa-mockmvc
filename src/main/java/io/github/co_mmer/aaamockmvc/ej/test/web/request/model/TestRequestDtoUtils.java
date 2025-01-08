package io.github.co_mmer.aaamockmvc.ej.test.web.request.model;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.util.CollectionUtils;

/**
 * Utility class for validating properties of request DTOs.
 *
 * <p>This class provides static methods to check the presence and validity of various properties in
 * request-related data transfer objects (DTOs), such as URL parameters, headers, and body content.
 * The methods mainly focus on determining whether certain fields are empty or null.
 *
 * <p>The constructor is private, and the class is designed to be used statically.
 *
 * @since 1.0.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestRequestDtoUtils {

  /**
   * Checks if the accepts in the provided {@code TestRequestHeadDto} are not empty.
   *
   * @param headDto the {@code TestRequestHeadDto} to be checked (must not be {@code null})
   * @return {@code true} if the accepts are not empty; {@code false} otherwise
   * @throws NullPointerException if the {@code headDto} is {@code null}
   * @since 1.0.0
   */
  public static boolean isNotEmptyAccepts(@NonNull TestRequestHeadDto headDto) {
    return !CollectionUtils.isEmpty(headDto.getAccepts());
  }

  /**
   * Checks if the content type in the provided {@code TestRequestHeadDto} is not null.
   *
   * @param headDto the {@code TestRequestHeadDto} to be checked (must not be {@code null})
   * @return {@code true} if the content type is not null; {@code false} otherwise
   * @throws NullPointerException if the {@code headDto} is {@code null}
   * @since 1.0.0
   */
  public static boolean isNotNullContentType(@NonNull TestRequestHeadDto headDto) {
    return headDto.getContentType() != null;
  }

  /**
   * Checks if the content in the provided {@code TestRequestBodyDto} is not null.
   *
   * @param bodyDto the {@code TestRequestBodyDto} to be checked (must not be {@code null})
   * @return {@code true} if the content is not null; {@code false} otherwise
   * @throws NullPointerException if the {@code bodyDto} is {@code null}
   * @since 1.0.0
   */
  public static boolean isNotNullContent(@NonNull TestRequestBodyDto bodyDto) {
    return bodyDto.getContent() != null;
  }

  /**
   * Checks if the files in the provided {@code TestRequestBodyDto} are not empty.
   *
   * @param bodyDto the {@code TestRequestBodyDto} to be checked (must not be {@code null})
   * @return {@code true} if the files are not empty; {@code false} otherwise
   * @throws NullPointerException if the {@code bodyDto} is {@code null}
   * @since 1.0.0
   */
  public static boolean isNotEmptyFiles(@NonNull TestRequestBodyDto bodyDto) {
    return !CollectionUtils.isEmpty(bodyDto.getFiles());
  }

  /**
   * Checks if the parameters in the provided {@code TestRequestUrlDto} are not empty.
   *
   * @param urlDto the {@code TestRequestUrlDto} to be checked (must not be {@code null})
   * @return {@code true} if the parameters are not empty; {@code false} otherwise
   * @throws NullPointerException if the {@code urlDto} is {@code null}
   * @since 1.0.0
   */
  public static boolean isNotEmptyParam(@NonNull TestRequestUrlDto urlDto) {
    return !CollectionUtils.isEmpty(urlDto.getParam());
  }

  /**
   * Checks if the key-value pairs in the provided {@code TestRequestHeadDto} are not empty.
   *
   * @param headDto the {@code TestRequestHeadDto} to be checked (must not be {@code null})
   * @return {@code true} if the key-value pairs are not empty; {@code false} otherwise
   * @throws NullPointerException if the {@code headDto} is {@code null}
   * @since 1.0.0
   */
  public static boolean isNotEmptyKeyValue(@NonNull TestRequestHeadDto headDto) {
    return !CollectionUtils.isEmpty(headDto.getKeyValue());
  }
}
