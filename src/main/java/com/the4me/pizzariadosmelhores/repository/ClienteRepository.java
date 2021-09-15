package com.the4me.pizzariadosmelhores.repository;

import com.the4me.pizzariadosmelhores.model.Cliente;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends MongoRepository<Cliente, String> {
    
}
