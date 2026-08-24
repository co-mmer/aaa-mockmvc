package io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.description;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.metadata.Since;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.body.EmptyBody;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.body.MultipartBody;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.body.RequestBody;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.body.TextBody;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.utils.StringUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@Since("2.1.0")
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class RequestBodyDescription {

  private static final String BODY_PREFIX = "Body: ";

  @Since("2.1.0")
  public static String describe(RequestBody body) {

    if (body instanceof EmptyBody) {
      return StringUtils.EMPTY;
    }

    if (body instanceof TextBody textBody) {
      return BODY_PREFIX + "text=" + textBody.value();
    }

    if (body instanceof MultipartBody multipartBody) {
      return BODY_PREFIX + "multipart=" + multipartBody.parts().size() + " parts";
    }

    return StringUtils.EMPTY;
  }
}
