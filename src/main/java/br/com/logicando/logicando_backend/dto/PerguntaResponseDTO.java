// PerguntaResponseDTO.java
package br.com.logicando.logicando_backend.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class PerguntaResponseDTO {
    private UUID id;
    private String texto;
    private String tipo;
}
