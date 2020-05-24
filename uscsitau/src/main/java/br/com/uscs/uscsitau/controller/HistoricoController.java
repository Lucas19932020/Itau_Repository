package br.com.uscs.uscsitau.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.uscs.uscsitau.model.Conta;
import br.com.uscs.uscsitau.model.Historico;
import br.com.uscs.uscsitau.repository.HistoricoRepository;

@RequestMapping(value="/historico")
public class HistoricoController {
	
	@Autowired
	HistoricoRepository historicoRepository;

	
	@GetMapping("/listaHistoricos")
	public List<Historico> listaHistoricos(){
		return (List<Historico>) historicoRepository.findAll(); 
	}
	
	@PostMapping("/salvaHistoricos")
	public Historico salvaHistorico(@RequestBody Historico historicoSalvo){
		return historicoRepository.save(historicoSalvo);
	}

	@DeleteMapping("/deletaHistoricos")
	public void deletaHistorico(@RequestBody Historico historicoDeleta) {
		historicoRepository.delete(historicoDeleta);
	}
	
	@PutMapping("/atualizaHistorico")
	public Historico atualizaHistorico(@RequestBody Historico historicoAtual) {
		return historicoRepository.save(historicoAtual);
	}

}
