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

import br.com.uscs.uscsitau.model.ContaVO;
import br.com.uscs.uscsitau.repository.ContaRepository;

@RestController
@RequestMapping(value="/conta")
public class ContaController {
	
	@Autowired
	ContaRepository contaRepository;
	
	@GetMapping("/lista")
	public List<ContaVO> listaContas(){
		return (List<ContaVO>) contaRepository.findAll();
	}
	
	@PostMapping("/salvar")
		public ContaVO salvaConta(@RequestBody ContaVO contaVOSalva) {
		return contaRepository.save(contaVOSalva);
	}
	
	@DeleteMapping("/deletar")
	public void deletaConta(@RequestBody ContaVO contaVODeleta) {
		contaRepository.delete(contaVODeleta);
	}
	
	@PutMapping("/atualizar")
	public ContaVO atualizaConta(@RequestBody ContaVO contaVOAtual) {
		return contaRepository.save(contaVOAtual);
	}
}
