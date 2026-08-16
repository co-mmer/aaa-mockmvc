package io.github.co_mmer.aaamockmvc.ej.test.web.internal.metadata;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(value = RetentionPolicy.SOURCE)
@Target({TYPE, METHOD, CONSTRUCTOR, FIELD})
@Since("2.0.0")
public @interface Since {

  String value();
}
