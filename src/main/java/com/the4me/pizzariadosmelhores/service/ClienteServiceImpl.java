package com.the4me.pizzariadosmelhores.service;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.the4me.pizzariadosmelhores.model.Cliente;
import com.the4me.pizzariadosmelhores.repository.ClienteRepository;
import com.the4me.pizzariadosmelhores.shared.ClienteDTO;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class ClienteServiceImpl implements ClienteService {
    
    @Autowired
    ClienteRepository repositorioCliente;

    @Override
    public List<ClienteDTO> obterTodos() {
     
        // Obtendo a lista de cliente model do nosso banco de dados.
        List<Cliente> clientes = repositorioCliente.findAll();

        // Instanciando o nosso objeto de modelo, o cara que sabe converter as coisas.
        ModelMapper mapper = new ModelMapper();

        // Transformando nossa List<Cliente> em uma List<ClienteDTO>.
        // fizemos isso usando recursos do stream, no caso map.
        List<ClienteDTO> clientesDto = clientes.stream()
        .map(cliente -> mapper.map(cliente, ClienteDTO.class))
        .collect(Collectors.toList());

        // Retornando a List<ClienteDTO>
        return clientesDto;
    }

    @Override
    public ClienteDTO obterPorId(String idCliente) {
        // Retorna um otional de cliente.
        Optional<Cliente> cliente = repositorioCliente.findById(idCliente);
       
        // Verifica se esta vazio, se sim é porque não encontrou o cliente pelo id.
        if(cliente.isEmpty()){
            throw new InputMismatchException("Cliente não encontrado com o ID: " + idCliente);
        }
       
        // Removo o cliente de dentro do optionl e converto ele em um clienteDto.
        return new ModelMapper().map(cliente.get(), ClienteDTO.class);

    }

    @Override
    public ClienteDTO adicionar(ClienteDTO clienteDto) {
       
    // Removo o id do dto.
       clienteDto.setId(null);

    // Criando o modelmapper
       ModelMapper mapper = new ModelMapper();

    //  Converto o clienteDto  em um cliente para salvar no banco de dados.
       Cliente cliente = mapper.map(clienteDto, Cliente.class);

        // Salvo o cliente no banco e obtenho o id.
       cliente = repositorioCliente.save(cliente);

    //    Converto novamente o cliente para Dto e retorno para quem pediu.
       return mapper.map(cliente, ClienteDTO.class);
    }

    @Override
    public void deletar(String idCliente) {
        Optional<Cliente> cliente = repositorioCliente.findById(idCliente);

        if(cliente.isEmpty()){
            throw new InputMismatchException("Não é possível deletar o cliente com o ID: " + idCliente + " - Cliente não encontrado");
        }

        repositorioCliente.deleteById(idCliente);
    }

    @Override
    public ClienteDTO atualizar(String idCliente, ClienteDTO clienteDTO) {
        // Passando o id do cliente para o proprio cliente.
        // Eles vem separado na requisição  do tipo PUT 
        clienteDTO.setId(idCliente);
        ModelMapper mapper = new ModelMapper();
        Cliente cliente = mapper.map(clienteDTO, Cliente.class);
        // Metodo save serve para atualizar
        cliente = repositorioCliente.save(cliente);
        return mapper.map(cliente, ClienteDTO.class);

        /**
         * O metodo save adiciona caso o id seja null, caso tenha id, ele atualiza.
        */
    }


}
