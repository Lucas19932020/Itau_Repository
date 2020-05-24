package br.com.uscs.uscsitau.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.uscs.uscsitau.model.HistoricoVO;
import br.com.uscs.uscsitau.repository.HistoricoRepository;

@RequestMapping(value="/historico")
public class HistoricoController {
	
	@Autowired
	HistoricoRepository historicoRepository;

	
	@GetMapping("/listaHistorico")
	public List<HistoricoVO> listaHistoricos(){
		return (List<HistoricoVO>) historicoRepository.findAll();
	}
	

    
	
}
