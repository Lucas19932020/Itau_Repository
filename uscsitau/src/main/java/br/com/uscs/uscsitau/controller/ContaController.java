package br.com.uscs.uscsitau.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.uscs.uscsitau.model.Conta;
import br.com.uscs.uscsitau.repository.ContaRepository;

@RestController
@RequestMapping(value="/conta")
public class ContaController {
	
	@Autowired
	ContaRepository contaRepository;
	
	@GetMapping("/listaContas")
	public List<Conta> listaContas(){
		return (List<Conta>) contaRepository.findAll();
	}
	
	@PostMapping("/salvaConta")
		public Conta salvaConta(@RequestBody Conta contaSalva) {
		
		
		/*if (!exits.get()) {
			contaRepository.save(contaSalva);
			Conta conta = new Conta();
			conta.setAgencia(0001);
			conta.setDac(1);
			conta.setSaldo(0);
			contaRepository.save(conta);
		}*/
		return contaRepository.save(contaSalva);
	}
	
	@DeleteMapping("/deletaConta")
	public void deletaConta(@RequestBody Conta contaDeleta) {
		contaRepository.delete(contaDeleta);
	}
	
	@PutMapping("/atualizaConta")
	public Conta atualizaConta(@RequestBody Conta contaAtual) {
		return contaRepository.save(contaAtual);
	}
}
