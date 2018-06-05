package hu.bearmaster.shopping.model.annotation;

import static org.immutables.annotate.InjectAnnotation.Where.FIELD;

import javax.persistence.JoinColumn;

import org.immutables.annotate.InjectAnnotation;

@InjectAnnotation(type = JoinColumn.class, target = FIELD, code = "([[*]])")
public @interface InjectJoinColumn {

    String name() default "";

    boolean nullable() default true;

}
