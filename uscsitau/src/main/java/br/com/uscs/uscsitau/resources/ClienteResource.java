package br.com.uscs.uscsitau.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.uscs.uscsitau.models.clientes;
import br.com.uscs.uscsitau.repository.ClienteRepository;


@RestController
@RequestMapping(value="/cadastrados")

public class ClienteResource {

    @Autowired
    ClienteRepository clienteRepository;

    @GetMapping("/clientes")
    public List<clientes> listacCiesntes(){
        return (List<clientes>) clienteRepository.findAll();
    }
    
  /* @GetMapping("/clinete/{id}")
    public clientes listaclienteunico(@PathVariable(value="cpf_cnpj") String cpf_cnpj)  {
    	return (clientes) clienteRepository.findById(cpf_cnpj);
    }*/ //Pesquisar sobre pesquisa de apenas um cliente
    
    @PostMapping("/clientes")
    public clientes salvaclientes(@RequestBody clientes cadastro) {
    	System.out.println("Cliente cadastrado com sucesso"); //Apenas parece no terminal
		return clienteRepository.save(cadastro);
    }
    
    @DeleteMapping("/clientes") //Se colocado apenas o CPF o clinete é deletado! Forma de se colocar no Postman  {"cpf_cnpj": "445.000.000-15"}
    public void deletaclientes(@RequestBody clientes cadastro) {
    	clienteRepository.delete(cadastro);
    }
    
    @PutMapping("/clientes") //Utilizado para atualização de cadastros
    public clientes atualizaclientes(@RequestBody clientes cadastro) {
    	return clienteRepository.save(cadastro);
    }
		
  
    
    

}
