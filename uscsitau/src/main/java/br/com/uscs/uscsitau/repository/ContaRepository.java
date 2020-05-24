package br.com.uscs.uscsitau.repository;

import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import br.com.uscs.uscsitau.model.Conta;

@Repository
public interface ContaRepository extends CrudRepository<Conta, String>{

}
