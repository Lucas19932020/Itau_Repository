package br.com.uscs.uscsitau.controller;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import br.com.uscs.uscsitau.utils.CpfCnpj;
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
    public String salvaClientes(@RequestBody Cliente cadastro) {

        try{

            CpfCnpj cpfCnpjCadastro = new CpfCnpj(cadastro.getCpf_cnpj(),
                    cadastro.getTipo_de_cliente());

            if (!cpfCnpjCadastro.isValid()) {
                throw new IllegalStateException();
            }

            List<Cliente> clientes = (List<Cliente>) clienteRepository.findAll();
            AtomicBoolean exists = new AtomicBoolean();
            clientes.forEach(item -> {
                CpfCnpj cpfCnpjCadastrado = new CpfCnpj(item.getCpf_cnpj(),
                        item.getTipo_de_cliente());

                if(cpfCnpjCadastrado.getNumber().equals(cpfCnpjCadastro.getNumber())) {
                    exists.set(true);
                    throw new IllegalArgumentException();
                }
            });

            if (!exists.get()) {
                clienteRepository.save(cadastro);
            }

        } catch (IllegalArgumentException ex) {
            return ("Cliente com cpf/cnpj " + cadastro.getCpf_cnpj() + " ja cadastrado");
        }

        catch (IllegalStateException ex) {
            return ("Cpf/Cnpj " + cadastro.getCpf_cnpj() + " invalido");
        }

        return ("Cliente "+ cadastro.getNome() +" cadastrado com sucesso");
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
