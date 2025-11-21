package com.tecsup.logs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogProcesoDTO {
    private String sistema;
    private String entidad;
    private String identificador;
    private String contenido;
}
