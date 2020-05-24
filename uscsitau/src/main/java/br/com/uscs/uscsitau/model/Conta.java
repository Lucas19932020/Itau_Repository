package br.com.uscs.uscsitau.model;

import java.io.Serializable;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Table(value = "conta")
public class Conta implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    @PrimaryKeyColumn(
            name = "num_conta",
            ordinal = 1,
            type = PrimaryKeyType.PARTITIONED
    )
    String num_conta;
	@Column(value = "agencia")
    String agencia;
	@Column(value = "dac")
    int dac;
	@Column(value = "saldo")
    double saldo;
	
	
}
