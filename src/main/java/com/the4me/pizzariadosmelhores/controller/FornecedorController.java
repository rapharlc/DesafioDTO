package com.the4me.pizzariadosmelhores.controller;

import java.util.List;

import com.the4me.pizzariadosmelhores.model.Fornecedor;
import com.the4me.pizzariadosmelhores.service.FornecedorServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/fornecedores")
public class FornecedorController {
    
    @Autowired
    FornecedorServiceImpl servicoFornecedor;

    @GetMapping
    public List<Fornecedor> obterTodos(){
       return servicoFornecedor.obterTodos();
    }

    @GetMapping("/{id}")
    public Fornecedor obterPorId(@PathVariable String id){
        /**
         * O @PathVariable pega o id que vem na url e transforma em uma variável ID do tipo String
         */
        return servicoFornecedor.obterPorId(id);
    }

    @PostMapping
    public Fornecedor adicionar(@RequestBody Fornecedor fornecedor){
        return servicoFornecedor.adicionar(fornecedor);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable String id){
        servicoFornecedor.deletar(id);
    }

    @PutMapping("/{id}")
    public Fornecedor atualizar(
        @PathVariable String id, 
        @RequestBody Fornecedor fornecedor){
            
        return servicoFornecedor.atualizar(id, fornecedor);
    }

    /**
     * Tipos de escrita
     * 
     * PascalCase: AnaClaraDaSilva
     * camelCase:  anaClaraDaSilva
     * kibab-case: ana-clara
     * snake_case: ana_clara
     */
}
