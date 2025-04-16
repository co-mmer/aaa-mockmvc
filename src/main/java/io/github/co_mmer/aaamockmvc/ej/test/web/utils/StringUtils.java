package io.github.co_mmer.aaamockmvc.ej.test.web.utils;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

/**
 * Utility class providing common operations for {@link String} handling.
 *
 * <p>This class includes methods for string comparison, validation, and other common tasks that
 * simplify working with {@link String} objects. It aims to reduce boilerplate code and improve the
 * readability of string-related operations.
 *
 * @since 1.4.1
 */
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class StringUtils {

  /**
   * Checks whether a given string is {@code null}, empty, or contains only whitespace characters.
   *
   * @param str the string to check
   * @return {@code true} if the string is {@code null}, empty, or blank (only whitespace); {@code
   *     false} otherwise
   * @since 1.4.1
   */
  public static boolean isBlank(String str) {
    return str == null || str.trim().isEmpty();
  }

  /**
   * Constant representing an empty string.
   *
   * @since 1.4.1
   */
  public static final String EMPTY = "";

  /**
   * Constant representing an empty array notation "[]".
   *
   * @since 1.4.1
   */
  public static final String EMPTY_ARRAY = "[]";

  /**
   * Constant representing an empty object notation "{}".
   *
   * @since 1.4.1
   */
  public static final String EMPTY_OBJECT = "{}";
}
