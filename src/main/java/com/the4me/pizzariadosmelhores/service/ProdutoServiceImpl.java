package com.the4me.pizzariadosmelhores.service;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.the4me.pizzariadosmelhores.model.Produto;
import com.the4me.pizzariadosmelhores.repository.ProdutoRepository;
import com.the4me.pizzariadosmelhores.shared.ProdutoDTO;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoServiceImpl implements ProdutoService {
    
    @Autowired
    ProdutoRepository repositorioProduto;

    @Override
    public List<ProdutoDTO> obterTodos() {
        List <Produto> produtos = repositorioProduto.findAll();
        ModelMapper mapper = new ModelMapper();
        List<ProdutoDTO> produtosDTO = produtos.stream().map(produto -> mapper.map(produto, ProdutoDTO.class)).collect(Collectors.toList());
        return produtosDTO;
    }

    @Override
    public ProdutoDTO obterPorId(String idProduto) {
        Optional<Produto> produto = repositorioProduto.findById(idProduto);
       
        if(produto.isEmpty()){
            throw new InputMismatchException("Produto não encontrado com o ID: " + idProduto);
        }

        return new ModelMapper().map(produto.get(), ProdutoDTO.class);
    }

    @Override
    public ProdutoDTO adicionar(ProdutoDTO produtoDto) {
       
       produtoDto.setId(null);
       ModelMapper mapper = new ModelMapper();
       Produto produto = mapper.map(produtoDto, Produto.class); 
       produto = repositorioProduto.save(produto);
       return mapper.map(produto, ProdutoDTO.class);
    }

    @Override
    public void deletar(String idProduto) {
        Optional<Produto> Produto = repositorioProduto.findById(idProduto);

        if(Produto.isEmpty()){
            throw new InputMismatchException("Não é possível deletar o Produto com o ID: " + idProduto + " - Produto não encontrado");
        }

        repositorioProduto.deleteById(idProduto);
    }

    @Override
    public ProdutoDTO atualizar(String idProduto, ProdutoDTO produtoDto) {
        // Passando o id do Produto para o proprio Produto.
        // Eles vem separado na requisição  do tipo PUT 
        produtoDto.setId(idProduto);
        ModelMapper mapper = new ModelMapper();
        Produto produto = mapper.map(produtoDto, Produto.class);
        produto = repositorioProduto.save(produto);
        
        // Metodo save serve para atualizar
        return mapper.map(produto, ProdutoDTO.class);

        /**
         * O metodo save adiciona caso o id seja null, caso tenha id, ele atualiza.
        */
    }


}
