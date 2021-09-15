package com.the4me.pizzariadosmelhores.service;

import java.util.List;


import com.the4me.pizzariadosmelhores.shared.ClienteDTO;

public interface ClienteService {

    List<ClienteDTO> obterTodos();

    ClienteDTO obterPorId(String idCliente);

    ClienteDTO adicionar(ClienteDTO cliente);
    
    ClienteDTO atualizar(String idCliente, ClienteDTO cliente);

    void deletar(String idCliente);

}
