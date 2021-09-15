package com.the4me.pizzariadosmelhores.service;

import java.util.List;

import com.the4me.pizzariadosmelhores.shared.ProdutoDTO;

public interface ProdutoService {

    List<ProdutoDTO> obterTodos();

    ProdutoDTO obterPorId(String idProduto);

    ProdutoDTO adicionar(ProdutoDTO produto);
    
    ProdutoDTO atualizar(String idProduto, ProdutoDTO produto);

    void deletar(String idProduto);

}
