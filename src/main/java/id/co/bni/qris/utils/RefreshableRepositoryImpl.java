package id.co.bni.qris.utils;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Collection;

@NoRepositoryBean
public class RefreshableRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID> implements RefreshableRepository<T, ID> {
    private final EntityManager entityManager;

    public RefreshableRepositoryImpl(JpaEntityInformation entityInformation, EntityManager entityManager){
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void flush(){
        entityManager.flush();
    }

    @Override
    @Transactional
    public void refresh(T t) {
        entityManager.refresh(t);
    }

    @Override
    @Transactional
    public void refresh(Collection<T> s) {
        for (T t: s){
            entityManager.refresh(t);
        }
    }
}
