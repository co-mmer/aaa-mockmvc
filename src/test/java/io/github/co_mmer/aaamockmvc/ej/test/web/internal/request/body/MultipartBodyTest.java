package io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.body;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class MultipartBodyTest {

  private static final String ANY_NAME = "file";
  private static final String ANY_FILENAME = "document.txt";
  private static final String ANY_CONTENT_TYPE = "text/plain";
  private static final byte[] ANY_CONTENT = {1, 2, 3};
  private static final MultipartPart ANY_PART =
      new MultipartPart(ANY_NAME, ANY_FILENAME, ANY_CONTENT_TYPE, ANY_CONTENT);
  private static final MultipartPart ANY_FIRST =
      new MultipartPart("first", "first.txt", "text/plain", new byte[] {1});
  private static final MultipartPart ANY_SECOND =
      new MultipartPart("second", "second.json", "application/json", new byte[] {2});

  private MultipartBody body;

  @BeforeEach
  void setUp() {
    this.body = new MultipartBody();
  }

  @Nested
  class AddPart {

    @Test
    void GIVEN_part_WHEN_add_THEN_store_same_part() {
      // Act
      body.add(ANY_PART);

      // Assert
      assertThat(body.parts().size(), is(1));
      assertThat(body.parts().get(0), is(sameInstance(ANY_PART)));
    }

    @Test
    void GIVEN_multiple_parts_WHEN_add_THEN_preserve_insertion_order() {
      // Arrange
      // Act
      body.add(ANY_FIRST);
      body.add(ANY_SECOND);

      // Assert
      assertThat(body.parts().size(), is(2));
      assertThat(body.parts().get(0), is(sameInstance(ANY_FIRST)));
      assertThat(body.parts().get(1), is(sameInstance(ANY_SECOND)));
    }

    @Test
    void GIVEN_null_part_WHEN_add_THEN_return_meaningful_message() {
      // Act
      var ex = assertThrows(IllegalArgumentException.class, () -> body.add(null));

      // Assert
      assertThat(ex.getMessage(), is("Multipart part must not be null"));
      assertThat(body.parts(), is(List.of()));
    }
  }

  @Nested
  class AddPartValues {

    @Test
    void GIVEN_part_values_WHEN_add_THEN_create_and_store_part() {
      // Act
      body.add(ANY_NAME, ANY_FILENAME, ANY_CONTENT_TYPE, ANY_CONTENT);

      // Assert
      assertThat(body.parts().size(), is(1));

      var part = body.parts().get(0);
      assertThat(part.name(), is(ANY_NAME));
      assertThat(part.filename(), is(ANY_FILENAME));
      assertThat(part.contentType(), is(ANY_CONTENT_TYPE));
      assertThat(part.content(), is(ANY_CONTENT));
    }
  }

  @Nested
  class ReadParts {

    @Test
    void GIVEN_new_body_WHEN_parts_THEN_return_empty_list() {
      // Act
      var result = body.parts();

      // Assert
      assertThat(result, is(List.of()));
    }

    @Test
    @SuppressWarnings("all")
    void GIVEN_parts_WHEN_modify_returned_list_THEN_reject_modification() {
      // Arrange
      var part = new MultipartPart(ANY_NAME, ANY_FILENAME, ANY_CONTENT_TYPE, ANY_CONTENT);

      body.add(part);

      var parts = body.parts();

      // Act & Assert
      assertThrows(UnsupportedOperationException.class, () -> parts.add(part));
      assertThat(body.parts().size(), is(1));
      assertThat(body.parts().get(0), is(sameInstance(part)));
    }

    @Test
    void GIVEN_parts_snapshot_WHEN_body_changes_THEN_keep_snapshot_unchanged() {
      // Arrange
      body.add(ANY_FIRST);
      var snapshot = body.parts();

      // Act
      body.add(ANY_SECOND);

      // Assert
      assertThat(snapshot.size(), is(1));
      assertThat(snapshot.get(0), is(sameInstance(ANY_FIRST)));
      assertThat(body.parts().size(), is(2));
    }
  }
}
