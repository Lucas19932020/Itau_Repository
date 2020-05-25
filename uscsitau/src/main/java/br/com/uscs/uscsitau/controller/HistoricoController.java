package br.com.uscs.uscsitau.controller;

import br.com.uscs.uscsitau.model.HistoricoVO;
import br.com.uscs.uscsitau.repository.HistoricoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value="/historico")
public class HistoricoController {

	@Autowired
	HistoricoRepository historicoRepository;

	@GetMapping("/listaHistorico")
	public List<HistoricoVO> listaHistoricos(){
		return (List<HistoricoVO>) historicoRepository.findAll();
	}

	@PostMapping("/salvaHistoricos")
	public ResponseEntity salvaHistorico(@RequestBody HistoricoVO historicoVO){



		historicoRepository.save(historicoVO);

		return ResponseEntity.ok().body(historicoVO);

	}

	@DeleteMapping("/deletaHistoricos")
	public void deletaHistorico(@RequestBody HistoricoVO historicoDeleta) {
		historicoRepository.delete(historicoDeleta);
	}

	@PutMapping("/atualizaHistorico")
	public HistoricoVO atualizaHistorico(@RequestBody HistoricoVO historicoAtual) {
		return historicoRepository.save(historicoAtual);
	}

}
