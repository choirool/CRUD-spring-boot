package com.choirool.mysqlcrud.dao;

import com.choirool.mysqlcrud.model.Ticket;

import org.springframework.data.repository.CrudRepository;

public interface TicketDao extends CrudRepository<Ticket, Long> {
}
