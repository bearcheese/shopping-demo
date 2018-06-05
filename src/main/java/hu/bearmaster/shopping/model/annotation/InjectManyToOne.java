package hu.bearmaster.shopping.model.annotation;

import static org.immutables.annotate.InjectAnnotation.Where.FIELD;

import javax.persistence.ManyToOne;

import org.immutables.annotate.InjectAnnotation;

@InjectAnnotation(type = ManyToOne.class, target = FIELD, code = "([[*]])")
public @interface InjectManyToOne {
    Class targetEntity() default void.class;
}
