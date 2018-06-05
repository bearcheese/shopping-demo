package hu.bearmaster.shopping.model.annotation;

import javax.persistence.GeneratedValue;

import org.immutables.annotate.InjectAnnotation;
import org.immutables.annotate.InjectAnnotation.Where;

@InjectAnnotation(type = GeneratedValue.class, target = Where.FIELD)
public @interface InjectGeneratedValue {
}
