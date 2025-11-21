package com.tecsup.logs.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import com.tecsup.logs.dto.LogProcesoDTO;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaProducerService {

    private static final Logger logger = LoggerFactory.getLogger(KafkaProducerService.class);

    @Value("${kafka.topic.name}")
    private String topicName;

    private final KafkaTemplate<String, LogProcesoDTO> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, LogProcesoDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

   
    public void sendMessage(LogProcesoDTO param) {

        logger.info("Enviando sistema: {}", param.getSistema());
        logger.info("Enviando entidad: {}", param.getEntidad());
        logger.info("Enviando identificador: {}", param.getIdentificador());
        logger.info("Enviando mensaje: {}", param.getContenido());
        
        CompletableFuture<SendResult<String, LogProcesoDTO>> future = 
            kafkaTemplate.send(topicName, param);

        future.whenComplete((result, ex) -> {
            if (ex == null) {
                logger.info("Mensaje enviado exitosamente: [{}] con offset: [{}]",
                        param.getContenido(), result.getRecordMetadata().offset());
            } else {
                logger.error("Error al enviar mensaje: [{}]", param.getContenido(), ex);
            }
        });
    }
    

}
