package org.radar.analysis.annotations;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PACKAGE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Documented
@Retention(RUNTIME)
@Target({TYPE, METHOD, PACKAGE})

public @interface Rationale {

    String id() default "";

    String[] context() default {};

    String[] justification() default {};

    String[] consequence() default {};

    String[] alternative() default {};

    String[] decision() default {};

    String[] pattern() default {};

    String[] tactic() default {};

    String[] strategy() default {};
}
