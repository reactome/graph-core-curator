package org.reactome.server.graph.curator.domain.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ReactomeInstanceDefiningValue {
    public Category category() default Category.none;
    enum Category {
        all,
        any,
        none
    }
}
