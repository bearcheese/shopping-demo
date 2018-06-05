package hu.bearmaster.shopping.dal.spring;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

//Based on: https://stackoverflow.com/a/47832258
public class InterfaceBasedJpaRepositoryFactoryBean<T extends Repository<S, I>, S, I> extends JpaRepositoryFactoryBean<T, S, I> {

    public InterfaceBasedJpaRepositoryFactoryBean(Class<? extends T> repositoryInterface) {
        super(repositoryInterface);
    }

    protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
        return new InterfaceBasedJpaRepositoryFactory(entityManager);
    }
}
