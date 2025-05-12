// CreatePerguntaDTO.java
package br.com.logicando.logicando_backend.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class CreatePerguntaDTO {
    private UUID formularioId;
    private String texto;
    private String tipo;
}
