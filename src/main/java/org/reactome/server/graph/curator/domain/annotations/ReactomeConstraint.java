package org.reactome.server.graph.curator.domain.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ReactomeConstraint {
    public Constraint constraint() default Constraint.OPTIONAL;

     enum Constraint {
        NOMANUALEDIT,
        MANDATORY,
        OPTIONAL,
        REQUIRED
    }
}
