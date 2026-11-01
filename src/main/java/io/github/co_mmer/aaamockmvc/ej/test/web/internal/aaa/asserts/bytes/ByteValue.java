package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.bytes;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.metadata.Since;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

@Since("2.1.0")
public final class ByteValue {

  private final byte[] value;

  @Since("2.1.0")
  public ByteValue(@Nullable byte[] value) {
    if (value == null) {
      throw new IllegalArgumentException("Bytes must not be null.");
    }
    this.value = value.clone();
  }

  @NonNull
  @Since("2.1.0")
  public byte[] value() {
    return this.value.clone();
  }

  @Since("2.1.0")
  public int length() {
    return this.value.length;
  }
}
