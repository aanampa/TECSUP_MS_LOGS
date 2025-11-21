package com.tecsup.logs.service;

import com.tecsup.logs.entity.LogProceso;
import com.tecsup.logs.repository.LogProcesoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LogProcesoService {
    
    private static final Logger logger = LoggerFactory.getLogger(LogProcesoService.class);
    
    private final LogProcesoRepository logProcesoRepository;
    
    public LogProcesoService(LogProcesoRepository logProcesoRepository) {
        this.logProcesoRepository = logProcesoRepository;
    }
    /* 
    @Transactional
    public LogProceso guardarLog(String contenido) {
        LogProceso log = new LogProceso();
        log.setContenido(contenido);

        log.setSistema("DEMO");
        log.setEntidad("Tabla");
        log.setIdentificador("999999");

        LogProceso logGuardado = logProcesoRepository.save(log);
        logger.info("Log guardado en BD con ID: {}", logGuardado.getIdLogProceso());
        
        return logGuardado;
    }
    */
    
    @Transactional
    public LogProceso guardarLog(String sistema, String entidad, String identificador, String contenido) {
        LogProceso log = new LogProceso();
        log.setContenido(contenido);
        log.setSistema(sistema);
        log.setEntidad(entidad);
        log.setIdentificador(identificador);

        LogProceso logGuardado = logProcesoRepository.save(log);
        logger.info("Log guardado en BD con ID: {}", logGuardado.getIdLogProceso());
        
        return logGuardado;
    }

    public List<LogProceso> obtenerTodosLosLogs() {
        return logProcesoRepository.findAllByOrderByFechaRegistroDesc();
    }
    
    public List<LogProceso> obtenerLogsRecientes() {
        return logProcesoRepository.findTop10ByOrderByFechaRegistroDesc();
    }
    
    public List<LogProceso> obtenerLogsRecientes(int cantidad) {
        Pageable pageable = PageRequest.of(0, cantidad, Sort.by(Sort.Direction.DESC, "fechaRegistro"));
        return logProcesoRepository.findAll(pageable).getContent();
    }
    
    public List<LogProceso> buscarLogs(String keyword) {
        return logProcesoRepository.findByContenidoContainingIgnoreCase(keyword);
    }
    
    public Long contarTotalLogs() {
        return logProcesoRepository.countTotalLogs();
    }
    
    public List<LogProceso> obtenerLogsPorRangoFechas(LocalDateTime inicio, LocalDateTime fin) {
        return logProcesoRepository.findByFechaRegistroBetween(inicio, fin);
    }
}
