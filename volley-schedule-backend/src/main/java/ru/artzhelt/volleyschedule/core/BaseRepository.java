package ru.artzhelt.volleyschedule.core;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

/**
 * Created by Artyom Zheltyshev on 12.02.2024
 */
@NoRepositoryBean
public interface BaseRepository<T extends Identifiable> extends CrudRepository<T, Long>, JpaSpecificationExecutor<T> {

    @Override
    List<T> findAll();

}
