package com.tecsup.logs.consumer;

import com.tecsup.logs.dto.LogProcesoDTO;
import com.tecsup.logs.service.LogProcesoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {
    
    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);
    
    private final LogProcesoService logProcesoService;
    
    public KafkaConsumerService(LogProcesoService logProcesoService) {
        this.logProcesoService = logProcesoService;
    }

    /* 
    @KafkaListener(topics = "${kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(String sistema, String entidad, String identificador, String message) {
        try {

            logger.info("📨 Mensaje recibido de Kafka - sistema: {}", sistema);
            logger.info("📨 Mensaje recibido de Kafka - entidad: {}", entidad);
            logger.info("📨 Mensaje recibido de Kafka - identificador: {}", identificador);
            logger.info("📨 Mensaje recibido de Kafka - message:: {}", message);
            
            // Guardar el mensaje en la tabla log_proceso
            logProcesoService.guardarLog(sistema, entidad, identificador, message);
            
            logger.info("✅ Mensaje procesado y guardado exitosamente en log_proceso");
            
        } catch (Exception e) {
            logger.error("❌ Error procesando mensaje: {}", message, e);
        }
    }
    */
    
    @KafkaListener(topics = "${kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(LogProcesoDTO logMessage) {
        try {

            logger.info("- Mensaje recibido de Kafka - sistema: {}", logMessage.getSistema());
            logger.info("- Mensaje recibido de Kafka - entidad: {}", logMessage.getEntidad());
            logger.info("- Mensaje recibido de Kafka - identificador: {}", logMessage.getIdentificador());
            logger.info("- Mensaje recibido de Kafka - message:: {}", logMessage.getContenido());
            
            // Guardar el mensaje en la tabla log_proceso
            logProcesoService.guardarLog(logMessage.getSistema(), logMessage.getEntidad(), logMessage.getIdentificador(), logMessage.getContenido());
            
            logger.info("- Mensaje procesado y guardado exitosamente en log_proceso");
            
        } catch (Exception e) {
            logger.error("- Error procesando mensaje: {}", logMessage.getContenido(), e);
        }
    }
}
