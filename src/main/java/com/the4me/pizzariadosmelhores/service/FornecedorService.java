package com.the4me.pizzariadosmelhores.service;

import java.util.List;

import com.the4me.pizzariadosmelhores.model.Fornecedor;

public interface FornecedorService {

    List<Fornecedor> obterTodos();

    Fornecedor obterPorId(String idFornecedor);

    Fornecedor adicionar(Fornecedor fornecedor);
    
    Fornecedor atualizar(String idFornecedor, Fornecedor fornecedor);

    void deletar(String idFornecedor);

}
