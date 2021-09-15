package com.the4me.pizzariadosmelhores.service;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;

import com.the4me.pizzariadosmelhores.model.Fornecedor;
import com.the4me.pizzariadosmelhores.repository.FornecedorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FornecedorServiceImpl implements FornecedorService {
    
    @Autowired
    FornecedorRepository repositorioFornecedor;

    @Override
    public List<Fornecedor> obterTodos() {
        // Pega todos os Fornecedors do banco.
        return repositorioFornecedor.findAll();
    }

    @Override
    public Fornecedor obterPorId(String idFornecedor) {
        Optional<Fornecedor> Fornecedor = repositorioFornecedor.findById(idFornecedor);
       
        if(Fornecedor.isEmpty()){
            throw new InputMismatchException("Fornecedor não encontrado com o ID: " + idFornecedor);
        }

        return Fornecedor.get();
    }

    @Override
    public Fornecedor adicionar(Fornecedor fornecedor) {
       // Remover o id para garantir que vou adicionar.
       fornecedor.setId(null);

       // Metodo save serve para adicionar
       return repositorioFornecedor.save(fornecedor);
    }

    @Override
    public void deletar(String idFornecedor) {
        Optional<Fornecedor> Fornecedor = repositorioFornecedor.findById(idFornecedor);

        if(Fornecedor.isEmpty()){
            throw new InputMismatchException("Não é possível deletar o Fornecedor com o ID: " + idFornecedor + " - Fornecedor não encontrado");
        }

        repositorioFornecedor.deleteById(idFornecedor);
    }

    @Override
    public Fornecedor atualizar(String idFornecedor, Fornecedor fornecedor) {
        // Passando o id do Fornecedor para o proprio Fornecedor.
        // Eles vem separado na requisição  do tipo PUT 
        fornecedor.setId(idFornecedor);
        
        // Metodo save serve para atualizar
        return repositorioFornecedor.save(fornecedor);

        /**
         * O metodo save adiciona caso o id seja null, caso tenha id, ele atualiza.
        */
    }


}
