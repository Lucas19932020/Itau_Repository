package br.com.uscs.uscsitau.repository;

import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import br.com.uscs.uscsitau.model.ContaVO;

@Repository
public interface ContaRepository extends CrudRepository<ContaVO, String>{

}
