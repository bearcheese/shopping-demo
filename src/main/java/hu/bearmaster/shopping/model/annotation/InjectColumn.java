package hu.bearmaster.shopping.model.annotation;

import static org.immutables.annotate.InjectAnnotation.Where.FIELD;

import javax.persistence.Column;

import org.immutables.annotate.InjectAnnotation;

@InjectAnnotation(type = Column.class, target = FIELD, code = "([[*]])")
public @interface InjectColumn {

    String name() default "";
}
