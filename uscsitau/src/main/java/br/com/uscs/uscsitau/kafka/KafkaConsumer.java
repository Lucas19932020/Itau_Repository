package br.com.uscs.uscsitau.kafka;

import br.com.uscs.uscsitau.controller.dto.ClienteDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@EnableKafka
@KafkaListener(topics = "foo")
public class KafkaConsumer {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@KafkaHandler
	public void consume(@Payload ClienteDTO clienteDTO) {
		logger.info("TED de {} para {} no valor de  {}", clienteDTO.getNome(), clienteDTO.getEndereco(), clienteDTO.getCpf_cnpj());
	}

	@KafkaHandler
	public void consume(@Payload String text) {
		System.out.println("Evento de cliente: " + text);
	}

//    @KafkaHandler
//    public void consume(@Payload SaqueAtm saque) {
//        logger.info("Sque no ATM {} da conta {} no valor de  {}", saque.getNumeroAtm(), saque.getConta(),
//                saque.getValorSacado());
//    }


//	@KafkaHandler
//	public void handler(String mensagem)
//	{
//		System.out.println(mensagem);
//		mensagem = "Mensagem adicionada: " + mensagem;
//		kafkaTemplate.send("uscsdemoprod", mensagem);
//	}
}