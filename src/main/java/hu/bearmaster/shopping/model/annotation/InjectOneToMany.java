package hu.bearmaster.shopping.model.annotation;

import static org.immutables.annotate.InjectAnnotation.Where.FIELD;

import javax.persistence.OneToMany;

import org.immutables.annotate.InjectAnnotation;

@InjectAnnotation(type = OneToMany.class, target = FIELD, code = "([[*]])")
public @interface InjectOneToMany {

    String mappedBy() default "";

    Class targetEntity() default void.class;

}
