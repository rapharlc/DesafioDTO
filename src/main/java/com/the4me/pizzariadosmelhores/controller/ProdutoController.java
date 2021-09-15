package com.the4me.pizzariadosmelhores.controller;

import java.util.List;

import com.the4me.pizzariadosmelhores.service.ProdutoServiceImpl;
import com.the4me.pizzariadosmelhores.shared.ProdutoDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
    
    @Autowired
    ProdutoServiceImpl servicoProduto;

    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> obterTodos(){
       return new ResponseEntity<>(servicoProduto.obterTodos(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> obterPorId(@PathVariable String id){
        /**
         * O @PathVariable pega o id que vem na url e transforma em uma variável ID do tipo String
         */
        return new ResponseEntity<>(servicoProduto.obterPorId(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProdutoDTO> adicionar(@RequestBody ProdutoDTO produtoDto){
        
        ProdutoDTO produtoCadastrado = servicoProduto.adicionar(produtoDto);
        return new ResponseEntity<>(produtoCadastrado, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable String id){
        servicoProduto.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDTO> atualizar(@PathVariable String id, @RequestBody ProdutoDTO produtoDto){
        ProdutoDTO produtoAtualizado = servicoProduto.atualizar(id, produtoDto);
            
        return new ResponseEntity<>(produtoAtualizado, HttpStatus.OK);
    }

    
    
}
