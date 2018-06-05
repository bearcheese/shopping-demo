package hu.bearmaster.shopping.dal.spring;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.util.Assert;

//Based on: https://stackoverflow.com/a/47832258
public class InterfaceBasedJpaRepositoryFactory extends JpaRepositoryFactory {

    private final Map<? extends Class<?>, ? extends Class<?>> interfaceToEntityClassMap;
    private final EntityManager entityManager;

    public InterfaceBasedJpaRepositoryFactory(EntityManager entityManager) {

        super(entityManager);

        this.entityManager = entityManager;

        interfaceToEntityClassMap = entityManager
            .getMetamodel()
            .getEntities()
            .stream()
            .flatMap(et -> Arrays.stream(et.getJavaType().getInterfaces()).map(it -> new AbstractMap.SimpleImmutableEntry<>(it, et.getJavaType())))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (possibleDuplicateInterface, value) -> value));
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T, I> JpaEntityInformation<T, I> getEntityInformation(Class<T> domainClass) {

        Assert.isTrue(domainClass.isInterface(), "You are using interface based jpa repository support. "
            + "The entity type used in DAO should be an interface");

        Class<T> domainInterface = domainClass;

        Class<?> entityClass = interfaceToEntityClassMap.get(domainInterface);

        Assert.notNull(entityClass, "Entity class for a interface" + domainInterface + " not found!");

        return (JpaEntityInformation<T, I>) JpaEntityInformationSupport.getEntityInformation(entityClass, entityManager);
    }
}
