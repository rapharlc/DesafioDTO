package com.the4me.pizzariadosmelhores.controller;

import java.util.List;


import com.the4me.pizzariadosmelhores.service.ClienteServiceImpl;
import com.the4me.pizzariadosmelhores.shared.ClienteDTO;

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
@RequestMapping("/api/clientes")
public class ClienteController {
    
    @Autowired
    ClienteServiceImpl servicoCliente;

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> obterTodos(){
       return new ResponseEntity<>(servicoCliente.obterTodos(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> obterPorId(@PathVariable String id){
        /**
         * O @PathVariable pega o id que vem na url e transforma em uma variável ID do tipo String
         */
        ClienteDTO clienteDto = servicoCliente.obterPorId(id);

        return new ResponseEntity<>(clienteDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> adicionar(@RequestBody ClienteDTO clienteDto){
        ClienteDTO clienteCadastrado = servicoCliente.adicionar(clienteDto);
        
        return new ResponseEntity<>(clienteCadastrado, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable String id){
        servicoCliente.deletar(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> atualizar(
        @PathVariable String id, 
        @RequestBody ClienteDTO clienteDto){
            
        ClienteDTO clienteAtualizado = servicoCliente.atualizar(id, clienteDto);
        return new ResponseEntity<>(clienteAtualizado, HttpStatus.OK);
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
