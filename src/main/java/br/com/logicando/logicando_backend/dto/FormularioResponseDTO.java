// FormularioResponseDTO.java
package br.com.logicando.logicando_backend.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class FormularioResponseDTO {
    private UUID id;
    private String titulo;
    private String criadorNome;
    private LocalDateTime criadoEm;
}
