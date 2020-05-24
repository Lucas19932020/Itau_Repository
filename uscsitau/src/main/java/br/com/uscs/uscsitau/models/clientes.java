package br.com.uscs.uscsitau.models;

import java.io.Serializable;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Table(value = "cliente")
public class clientes implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(value = "nome")
    String nome;
    @PrimaryKeyColumn(
            name = "cpf_cnpj",
            ordinal = 1,
            type = PrimaryKeyType.PARTITIONED
    )
    String cpf_cnpj;
    @Column(value = "tipo_de_cliente")
    String tipo_de_cliente;
    @Column(value = "endereco")
    String endereco;
    @Column(value = "renda")
    double renda;
    @Column(value = "razao_social")
    String razao_social;
    @Column(value = "incr_estadual")
    String incr_estadual;
    @Column(value = "num_conta")
    String num_conta;
}
