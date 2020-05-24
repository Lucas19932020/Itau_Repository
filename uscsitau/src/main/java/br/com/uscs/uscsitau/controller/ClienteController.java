package br.com.uscs.uscsitau.controller;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

import br.com.uscs.uscsitau.controller.dto.ClienteDTO;
import br.com.uscs.uscsitau.errorhandling.AppException;
import br.com.uscs.uscsitau.errorhandling.ErrorCode;
import br.com.uscs.uscsitau.model.ContaVO;
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

import br.com.uscs.uscsitau.model.ClienteVO;
import br.com.uscs.uscsitau.repository.ClienteRepository;


@RestController
@RequestMapping(value="/clientes")

public class ClienteController {

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    ContaRepository contaRepository;

    @GetMapping("/lista")
    public List<ClienteVO> listaClientes(){
        return (List<ClienteVO>) clienteRepository.findAll();
    }
    
  /* @GetMapping("/clinete/{id}")
    public clientes listaclienteunico(@PathVariable(value="cpf_cnpj") String cpf_cnpj)  {
    	return (clientes) clienteRepository.findById(cpf_cnpj);
    }*/ //Pesquisar sobre pesquisa de apenas um cliente


    @PostMapping("/salvar")
    public ResponseEntity salvaClientes(@RequestBody ClienteDTO clienteDTO) {


        CpfCnpj cpfCnpjCadastro = new CpfCnpj(clienteDTO.getCpf_cnpj());
        ClienteVO clienteVO = new ClienteVO();

        if (!cpfCnpjCadastro.isValid()) {
            return ResponseEntity.badRequest().body(new AppException(ErrorCode.CPF_CNPJ_INVALID));
        }

        List<ClienteVO> clienteVOS = (List<ClienteVO>) clienteRepository.findAll();
        AtomicBoolean exists = new AtomicBoolean();
        clienteVOS.forEach(item -> {
            CpfCnpj cpfCnpjCadastrado = new CpfCnpj(item.getCpf_cnpj(),
                    item.getTipo_de_cliente());

            if (cpfCnpjCadastrado.getNumber().equals(cpfCnpjCadastro.getNumber())) {
                exists.set(true);
            }
        });

        if (!exists.get()) {

			ContaVO contaVO = new ContaVO();
			contaVO.setAgencia(String.valueOf(new Random().nextInt(9999)));
			contaVO.setDac(1);
			contaVO.setSaldo(0);
			contaVO.setNum_conta(String.valueOf(new Random().nextInt(999999999)));
			contaRepository.save(contaVO);

            clienteVO.setNome(clienteDTO.getNome());
            clienteVO.setCpf_cnpj(new CpfCnpj(clienteDTO.getCpf_cnpj()).getCpfCnpj());
            clienteVO.setTipo_de_cliente(new CpfCnpj(clienteDTO.getCpf_cnpj()).isPJ() ? "PJ" : "PF");
            clienteVO.setEndereco(clienteDTO.getEndereco());
            clienteVO.setRenda(clienteDTO.getRenda());
            clienteVO.setRazao_social(clienteDTO.getRazao_social());
            clienteVO.setIncr_estadual(clienteDTO.getIncr_estadual());
            clienteVO.setNum_conta(contaVO.getNum_conta());

            clienteRepository.save(clienteVO);

        } else {
            return ResponseEntity.badRequest().body(new AppException(ErrorCode.CPF_CNPJ_ALREADY_EXISTS));
        }

        return ResponseEntity.ok().body(clienteVO);
    }
    
    @DeleteMapping("/deletar") //Se colocado apenas o CPF o clinete é deletado! Forma de se colocar no Postman  {"cpf_cnpj": "445.000.000-15"}
    public ResponseEntity deletaClientes(@RequestBody ClienteVO cadastro) {

    	clienteRepository.delete(cadastro);

        return ResponseEntity.ok().body(cadastro);

    }
    
    @PutMapping("/atualizar") //Utilizado para atualização de cadastros
    public ResponseEntity atualizaClientes(@RequestBody ClienteDTO clienteDTO) {

            CpfCnpj cpfCnpjCadastro = new CpfCnpj(clienteDTO.getCpf_cnpj());
            ClienteVO clienteVO = new ClienteVO();

            if (!cpfCnpjCadastro.isValid()) {
                return ResponseEntity.badRequest().body(new AppException(ErrorCode.CPF_CNPJ_INVALID));
            }

            List<ClienteVO> clienteVOS = (List<ClienteVO>) clienteRepository.findAll();
            AtomicBoolean exists = new AtomicBoolean();
            clienteVOS.forEach(item -> {
                CpfCnpj cpfCnpjCadastrado = new CpfCnpj(item.getCpf_cnpj(),
                        item.getTipo_de_cliente());

                if(cpfCnpjCadastrado.getNumber().equals(cpfCnpjCadastro.getNumber())) {
                    exists.set(true);
                }
            });

            if (!exists.get()) {
                return ResponseEntity.badRequest().body(new AppException(ErrorCode.CPF_CNPJ_NOT_FOUND));
            } else {

                clienteVO.setNome(clienteDTO.getNome());
                clienteVO.setCpf_cnpj(new CpfCnpj(clienteDTO.getCpf_cnpj()).getCpfCnpj());
                clienteVO.setTipo_de_cliente(new CpfCnpj(clienteDTO.getCpf_cnpj()).isPJ() ? "PJ" : "PF");
                clienteVO.setEndereco(clienteDTO.getEndereco());
                clienteVO.setRenda(clienteDTO.getRenda());
                clienteVO.setRazao_social(clienteDTO.getRazao_social());
                clienteVO.setIncr_estadual(clienteDTO.getIncr_estadual());
                clienteVO.setNum_conta(clienteDTO.getNum_conta());
                clienteRepository.save(clienteVO);
            }


        return ResponseEntity.ok().body(clienteVO);

    }
    }
