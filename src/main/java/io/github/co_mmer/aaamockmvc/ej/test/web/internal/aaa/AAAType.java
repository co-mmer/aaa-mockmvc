package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.metadata.Since;

@Since("2.1.0")
public interface AAAType<T> {

  Class<T> type();
}
