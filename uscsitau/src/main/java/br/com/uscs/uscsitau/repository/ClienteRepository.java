package br.com.uscs.uscsitau.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.uscs.uscsitau.model.Cliente;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, String> {
	//clientes findById(String cpf_cnpj);
}
