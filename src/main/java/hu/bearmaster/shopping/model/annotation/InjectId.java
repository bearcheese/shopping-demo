package hu.bearmaster.shopping.model.annotation;

import static org.immutables.annotate.InjectAnnotation.Where.FIELD;

import javax.persistence.Id;

import org.immutables.annotate.InjectAnnotation;

@InjectAnnotation(type = Id.class, target = FIELD)
public @interface InjectId {
}
