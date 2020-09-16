package com.choirool.mysqlcrud.dao;

import com.choirool.mysqlcrud.model.Library;

import org.springframework.data.repository.CrudRepository;

public interface LibraryDao extends CrudRepository<Library, Long> {
    
}
