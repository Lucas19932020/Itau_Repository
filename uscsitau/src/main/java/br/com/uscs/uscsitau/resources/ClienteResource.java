package br.com.uscs.uscsitau.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.uscs.uscsitau.models.clientes;
import br.com.uscs.uscsitau.repository.ClienteRepository;


@RestController
@RequestMapping(value="/cadastro")

public class ClienteResource {

    @Autowired
    ClienteRepository clienteRepository;

    @GetMapping("/clientes")
    public List<clientes> listacCiesntes(){
        return (List<clientes>) clienteRepository.findAll();
    }

}
