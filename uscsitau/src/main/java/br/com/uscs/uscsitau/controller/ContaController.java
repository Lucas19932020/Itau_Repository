package br.com.uscs.uscsitau.controller;

import java.util.List;
import java.util.Random;

import br.com.uscs.uscsitau.controller.dto.ContaDTO;
import br.com.uscs.uscsitau.errorhandling.AppException;
import br.com.uscs.uscsitau.errorhandling.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

	@GetMapping("/{num_conta}")
	public ResponseEntity getContaByPK(@PathVariable(value = "num_conta") String num_conta) {
		try {

			num_conta = num_conta.replaceAll("/\\D/g", "");

			num_conta = num_conta.replaceAll("([0-9]{8})([0-9]{1})", "$1-$2");

			if (contaRepository.getContaByNumConta(num_conta).isEmpty()) {

				return ResponseEntity.status(404).body(new AppException(ErrorCode.ACCOUNT_NOT_FOUND));
			}

			ContaVO contaCorrenteVO = contaRepository.getContaByNumConta(num_conta).get(0);

			return ResponseEntity.ok().body(contaCorrenteVO);

		} catch (Exception ex) {

			return ResponseEntity.status(500).body(new AppException(ErrorCode.BAD_REQUEST));
		}
	}

}
