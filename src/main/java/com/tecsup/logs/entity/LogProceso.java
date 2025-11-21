package com.tecsup.logs.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "log_proceso")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogProceso {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_log_proceso")
    private Long idLogProceso;
    
    @Column(nullable = false, length = 255)
    private String sistema;

    @Column(nullable = false, length = 255)
    private String entidad;

    @Column(nullable = false, length = 255)
    private String identificador;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String contenido;
    
    @Column(name = "fecha_registro", nullable = false, updatable = false)
    private LocalDateTime fechaRegistro;
    
    @PrePersist
    protected void onCreate() {
        fechaRegistro = LocalDateTime.now();
    }
}
