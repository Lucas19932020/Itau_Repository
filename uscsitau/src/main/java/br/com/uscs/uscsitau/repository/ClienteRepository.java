package br.com.uscs.uscsitau.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.uscs.uscsitau.model.ClienteVO;

@Repository
public interface ClienteRepository extends CrudRepository<ClienteVO, String> {
	//clientes findById(String cpf_cnpj);
}
