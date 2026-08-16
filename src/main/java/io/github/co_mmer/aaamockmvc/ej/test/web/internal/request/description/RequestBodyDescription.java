package io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.description;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.body.BinaryBody;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.body.EmptyBody;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.body.FormBody;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.body.MultipartBody;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.body.RequestBody;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.body.TextBody;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class RequestBodyDescription {

  public static String describe(RequestBody body) {
    Objects.requireNonNull(body, "body must not be null");

    if (body instanceof EmptyBody) {
      return "Body: <none>";
    }

    if (body instanceof TextBody textBody) {
      return "Body: text=" + textBody.value();
    }

    if (body instanceof BinaryBody binaryBody) {
      return "Body: binary=" + binaryBody.value().length + " bytes";
    }

    if (body instanceof FormBody formBody) {
      return "Body: form=" + formBody.values();
    }

    if (body instanceof MultipartBody multipartBody) {
      return "Body: multipart=" + multipartBody.parts().size() + " parts";
    }

    return "Body: " + body.getClass().getSimpleName();
  }
}
