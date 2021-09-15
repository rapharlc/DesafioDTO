package com.the4me.pizzariadosmelhores.repository;

import com.the4me.pizzariadosmelhores.model.Fornecedor;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FornecedorRepository extends MongoRepository<Fornecedor, String> {
    
}
