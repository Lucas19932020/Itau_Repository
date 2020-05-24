package br.com.uscs.uscsitau.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.uscs.uscsitau.model.Historico;

@Repository
public interface HistoricoRepository extends CrudRepository<Historico, String>{

}
