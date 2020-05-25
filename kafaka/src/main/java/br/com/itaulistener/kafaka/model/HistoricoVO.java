package br.com.itaulistener.kafaka.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.mapping.Table;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
@Table(value = "historico")
	public class HistoricoVO implements Serializable{
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
	    @Column(value = "tipo_de_transacao")
	    String tipo_de_transacao;
	    @Column(value = "status")
	    int status;
	    @Column(value = "data")
	    LocalDateTime data;

	    public String getCpf_cnpj() {
	        return num_conta;
	    }

	    public void setNum_conta(String num_conta) {
	        this.num_conta = num_conta;
	    }
}

