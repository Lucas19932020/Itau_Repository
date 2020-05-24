package br.com.uscs.uscsitau.controller;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.uscs.uscsitau.model.Cliente;
import br.com.uscs.uscsitau.repository.ClienteRepository;


@RestController
@RequestMapping(value="/clientes")

public class ClienteController {

    @Autowired
    ClienteRepository clienteRepository;

    @GetMapping("/listaClientes")
    public List<Cliente> listaClientes(){
        return (List<Cliente>) clienteRepository.findAll();
    }
    
  /* @GetMapping("/clinete/{id}")
    public clientes listaclienteunico(@PathVariable(value="cpf_cnpj") String cpf_cnpj)  {
    	return (clientes) clienteRepository.findById(cpf_cnpj);
    }*/ //Pesquisar sobre pesquisa de apenas um cliente


    @PostMapping("/salvaClientes")
    public Cliente salvaClientes(@RequestBody Cliente cadastro) {

        List<Cliente> clientes = (List<Cliente>) clienteRepository.findAll();

        clientes.forEach(item -> {
            if(item.getCpf_cnpj().equals(cadastro.getCpf_cnpj())) {
                System.out.println("Cliente com cpf/cnpj " + cadastro.getCpf_cnpj() + " já cadastrado");
            }
        });


    	System.out.println("Cliente "+ cadastro.getNome() +" cadastrado com sucesso"); ////// Apenas parece no terminal
        return clienteRepository.save(cadastro);
    }
    
    @DeleteMapping("/deletaClientes") //Se colocado apenas o CPF o clinete é deletado! Forma de se colocar no Postman  {"cpf_cnpj": "445.000.000-15"}
    public void deletaClientes(@RequestBody Cliente cadastro) {
    	clienteRepository.delete(cadastro);
    }
    
    @PutMapping("/atualizaClientes") //Utilizado para atualização de cadastros
    public Cliente atualizaClientes(@RequestBody Cliente cadastro) {
    	return clienteRepository.save(cadastro);
    }

}
