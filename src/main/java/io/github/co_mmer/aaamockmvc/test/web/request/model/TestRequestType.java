package io.github.co_mmer.aaamockmvc.test.web.request.model;

/**
 * Enum representing the different types of HTTP requests that can be made in a test scenario.
 *
 * <p>The request types include the standard HTTP methods like GET, POST, PUT, PATCH, DELETE,
 * OPTIONS, and HEAD.
 *
 * @since 1.0.0
 */
public enum TestRequestType {
  /** The GET method requests data from a specified resource. */
  GET,

  /** The POST method submits data to be processed to a specified resource. */
  POST,

  /** The PUT method updates a current resource with new data. */
  PUT,

  /** The PATCH method applies partial modifications to a resource. */
  PATCH,

  /** The DELETE method removes the specified resource. */
  DELETE,

  /** The OPTIONS method describes the communication options for the target resource. */
  OPTIONS,

  /** The HEAD method retrieves the headers for a resource, without the body. */
  HEAD
}
