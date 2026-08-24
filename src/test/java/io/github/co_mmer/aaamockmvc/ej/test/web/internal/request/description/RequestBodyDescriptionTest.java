package io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.description;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.body.EmptyBody;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.body.MultipartBody;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.body.TextBody;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.utils.StringUtils;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class RequestBodyDescriptionTest {

  private static final String ANY_TEXT = "request body";

  @Nested
  class DescribeBody {

    @Test
    void GIVEN_null_body_WHEN_describe_THEN_return_empty_description() {
      // Act
      var result = RequestBodyDescription.describe(null);

      // Assert
      assertThat(result, is(StringUtils.EMPTY));
    }

    @Test
    void GIVEN_empty_body_WHEN_describe_THEN_return_empty_description() {
      // Act
      var result = RequestBodyDescription.describe(new EmptyBody());

      // Assert
      assertThat(result, is(StringUtils.EMPTY));
    }

    @Test
    void GIVEN_text_body_WHEN_describe_THEN_return_text_description() {
      // Arrange
      var body = new TextBody(ANY_TEXT);

      // Act
      var result = RequestBodyDescription.describe(body);

      // Assert
      assertThat(result, is("Body: text=" + ANY_TEXT));
    }

    @Test
    void GIVEN_empty_text_body_WHEN_describe_THEN_return_text_description() {
      // Arrange
      var body = new TextBody(StringUtils.EMPTY);

      // Act
      var result = RequestBodyDescription.describe(body);

      // Assert
      assertThat(result, is("Body: text="));
    }

    @Test
    void GIVEN_multipart_body_WHEN_describe_THEN_return_part_count() {
      // Arrange
      var body = new MultipartBody();
      body.add("firstPart", null, null, new byte[] {1});
      body.add("secondPart", "file.txt", "text/plain", new byte[] {2});

      // Act
      var result = RequestBodyDescription.describe(body);

      // Assert
      assertThat(result, is("Body: multipart=2 parts"));
    }

    @Test
    void GIVEN_empty_multipart_body_WHEN_describe_THEN_return_zero_part_count() {
      // Arrange
      var body = new MultipartBody();

      // Act
      var result = RequestBodyDescription.describe(body);

      // Assert
      assertThat(result, is("Body: multipart=0 parts"));
    }
  }
}
