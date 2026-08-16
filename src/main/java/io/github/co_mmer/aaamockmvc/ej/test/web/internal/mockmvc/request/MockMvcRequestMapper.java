package io.github.co_mmer.aaamockmvc.ej.test.web.internal.mockmvc.request;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.metadata.Since;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.HttpMethod;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.Request;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.body.BinaryBody;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.body.EmptyBody;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.body.FormBody;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.body.MultipartBody;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.body.RequestBody;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.body.TextBody;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

@Since("2.0.2")
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class MockMvcRequestMapper {

  @Since("2.0.2")
  public static RequestBuilder map(Request source) {
    return (source.body() instanceof MultipartBody multipartBody)
        ? mapMultipart(source, multipartBody)
        : mapRegular(source);
  }

  private static RequestBuilder mapRegular(Request source) {
    var builder = request(mapHttpMethod(source.method()), source.path().value());
    applyCommon(builder, source);
    applyBody(builder, source.body());
    return builder;
  }

  private static void applyBody(MockHttpServletRequestBuilder builder, RequestBody body) {
    if (body == null || body instanceof EmptyBody) {
      return;
    }

    if (body instanceof TextBody textBody) {
      MockMvcBodyMapper.applyText(builder, textBody);
      return;
    }

    if (body instanceof BinaryBody binaryBody) {
      MockMvcBodyMapper.applyBinary(builder, binaryBody);
      return;
    }

    if (body instanceof FormBody formBody) {
      MockMvcBodyMapper.applyForm(builder, formBody);
    }
  }

  private static RequestBuilder mapMultipart(Request source, MultipartBody body) {
    var builder = multipart(mapHttpMethod(source.method()), source.path().value());
    applyCommon(builder, source);
    MockMvcBodyMapper.applyMultipart(builder, body);
    return builder;
  }

  private static void applyCommon(MockHttpServletRequestBuilder builder, Request source) {
    MockMvcQueryMapper.apply(builder, source.query().values());
    MockMvcHeaderMapper.apply(builder, source.headers().values());
  }

  private static org.springframework.http.HttpMethod mapHttpMethod(HttpMethod method) {
    return switch (method) {
      case GET -> org.springframework.http.HttpMethod.GET;
      case HEAD -> org.springframework.http.HttpMethod.HEAD;
      case POST -> org.springframework.http.HttpMethod.POST;
      case PUT -> org.springframework.http.HttpMethod.PUT;
      case PATCH -> org.springframework.http.HttpMethod.PATCH;
      case DELETE -> org.springframework.http.HttpMethod.DELETE;
      case OPTIONS -> org.springframework.http.HttpMethod.OPTIONS;
      case TRACE -> org.springframework.http.HttpMethod.TRACE;
    };
  }
}
