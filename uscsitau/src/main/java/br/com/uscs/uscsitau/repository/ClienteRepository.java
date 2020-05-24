package br.com.uscs.uscsitau.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.uscs.uscsitau.models.clientes;

@Repository
public interface ClienteRepository extends CrudRepository<clientes, String> {}
