package br.com.uscs.uscsitau.controller;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

import br.com.uscs.uscsitau.errorhandling.AppException;
import br.com.uscs.uscsitau.errorhandling.ErrorCode;
import br.com.uscs.uscsitau.model.Conta;
import br.com.uscs.uscsitau.repository.ContaRepository;
import br.com.uscs.uscsitau.utils.CpfCnpj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    ContaRepository contaRepository;

    @GetMapping("/lista")
    public List<Cliente> listaClientes(){
        return (List<Cliente>) clienteRepository.findAll();
    }
    
  /* @GetMapping("/clinete/{id}")
    public clientes listaclienteunico(@PathVariable(value="cpf_cnpj") String cpf_cnpj)  {
    	return (clientes) clienteRepository.findById(cpf_cnpj);
    }*/ //Pesquisar sobre pesquisa de apenas um cliente


    @PostMapping("/salva")
    public ResponseEntity salvaClientes(@RequestBody Cliente cadastro) {


        CpfCnpj cpfCnpjCadastro = new CpfCnpj(cadastro.getCpf_cnpj());

        if (!cpfCnpjCadastro.isValid()) {
            return ResponseEntity.badRequest().body(new AppException(ErrorCode.CPF_CNPJ_INVALID));
        }

        List<Cliente> clientes = (List<Cliente>) clienteRepository.findAll();
        AtomicBoolean exists = new AtomicBoolean();
        clientes.forEach(item -> {
            CpfCnpj cpfCnpjCadastrado = new CpfCnpj(item.getCpf_cnpj(),
                    item.getTipo_de_cliente());

            if (cpfCnpjCadastrado.getNumber().equals(cpfCnpjCadastro.getNumber())) {
                exists.set(true);
            }
        });

        if (!exists.get()) {

			Conta conta = new Conta();
			conta.setAgencia(String.valueOf(new Random().nextInt(9999)));
			conta.setDac(1);
			conta.setSaldo(0);
			conta.setNum_conta(String.valueOf(new Random().nextInt(999999999)));
			contaRepository.save(conta);

			cadastro.setNum_conta(conta.getNum_conta());
            clienteRepository.save(cadastro);
        } else {
            return ResponseEntity.badRequest().body(new AppException(ErrorCode.CPF_CNPJ_ALREADY_EXISTS));
        }

        return ResponseEntity.ok().body(cadastro);
    }
    
    @DeleteMapping("/deleta") //Se colocado apenas o CPF o clinete é deletado! Forma de se colocar no Postman  {"cpf_cnpj": "445.000.000-15"}
    public ResponseEntity deletaClientes(@RequestBody Cliente cadastro) {

    	clienteRepository.delete(cadastro);

        return ResponseEntity.ok().body(cadastro);

    }
    
    @PutMapping("/atualiza") //Utilizado para atualização de cadastros
    public ResponseEntity atualizaClientes(@RequestBody Cliente cadastro) {

            CpfCnpj cpfCnpjCadastro = new CpfCnpj(cadastro.getCpf_cnpj());

            if (!cpfCnpjCadastro.isValid()) {
                return ResponseEntity.badRequest().body(new AppException(ErrorCode.CPF_CNPJ_INVALID));
            }

            List<Cliente> clientes = (List<Cliente>) clienteRepository.findAll();
            AtomicBoolean exists = new AtomicBoolean();
            clientes.forEach(item -> {
                CpfCnpj cpfCnpjCadastrado = new CpfCnpj(item.getCpf_cnpj(),
                        item.getTipo_de_cliente());

                if(cpfCnpjCadastrado.getNumber().equals(cpfCnpjCadastro.getNumber())) {
                    exists.set(true);
                }
            });

            if (!exists.get()) {
                return ResponseEntity.badRequest().body(new AppException(ErrorCode.CPF_CNPJ_NOT_FOUND));
            } else {
                clienteRepository.save(cadastro);
            }


        return ResponseEntity.ok().body(cadastro);

    }
    }
