package br.com.itaulistener.kafaka.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.itaulistener.kafaka.model.HistoricoVO;
import br.com.itaulistener.kafaka.repository.HistoricoRepository;

@RequestMapping(value="/historico")
public class HistoricoController {
	
	@Autowired
	HistoricoRepository historicoRepository;

	@GetMapping("/lista")
	public List<HistoricoVO> listaHistoricos(){
		return (List<HistoricoVO>) historicoRepository.findAll();
	}
	
	@PostMapping("/salva")
	public HistoricoVO salvaHistorico(@RequestBody HistoricoVO historicoSalvo){
		return historicoRepository.save(historicoSalvo);
	}

	@DeleteMapping("/deleta")
	public void deletaHistorico(@RequestBody HistoricoVO historicoDeleta) {
		historicoRepository.delete(historicoDeleta);
	}
	
	@PutMapping("/atualiza")
	public HistoricoVO atualizaHistorico(@RequestBody HistoricoVO historicoAtual) {
		return historicoRepository.save(historicoAtual);
	}

}