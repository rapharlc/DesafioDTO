package com.the4me.pizzariadosmelhores.repository;

import com.the4me.pizzariadosmelhores.model.Produto;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends MongoRepository<Produto, String> {
    
}
