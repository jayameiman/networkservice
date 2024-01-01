package id.co.bni.qris.utils;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Collection;

@NoRepositoryBean
public interface RefreshableRepository<T, ID> extends CrudRepository<T, ID> {
    void refresh(T t);

    void refresh(Collection<T> s);

    void flush();
}
