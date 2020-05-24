package br.com.uscs.uscsitau.model;

import java.time.LocalDateTime;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Table(value = "historico")
public class Historico {
    @PrimaryKeyColumn(
            name = "cpf_cnpj",
            ordinal = 1,
            type = PrimaryKeyType.PARTITIONED
    )
    String cpf_cnpj;
    @Column(value = "tipo_de_transacao")
    String tipo_de_transacao;
    @Column(value = "status")
    int status;
    @Column(value = "data")
    LocalDateTime data;

}
