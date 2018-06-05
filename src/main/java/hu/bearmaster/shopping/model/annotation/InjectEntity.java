package hu.bearmaster.shopping.model.annotation;

import static org.immutables.annotate.InjectAnnotation.Where.IMMUTABLE_TYPE;

import javax.persistence.Entity;

import org.immutables.annotate.InjectAnnotation;

@InjectAnnotation(type = Entity.class, target = IMMUTABLE_TYPE)
public @interface InjectEntity {
}
