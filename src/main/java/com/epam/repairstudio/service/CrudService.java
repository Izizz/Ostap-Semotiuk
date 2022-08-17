package com.epam.repairstudio.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CrudService<T,I> {

    T getById(I id);

    Page<T> getAll(Pageable pageable);

    T create(T dto);

    T updateById(I id, T dto);

    void deleteById(I id);
}
