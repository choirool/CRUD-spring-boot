package com.choirool.mysqlcrud.dao;

import com.choirool.mysqlcrud.model.Address;

import org.springframework.data.repository.CrudRepository;

public interface AddressDao extends CrudRepository<Address, Long> {
    
}
