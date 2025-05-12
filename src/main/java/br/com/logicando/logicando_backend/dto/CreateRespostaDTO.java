// CreateRespostaDTO.java
package br.com.logicando.logicando_backend.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class CreateRespostaDTO {
    private UUID perguntaId;
    private String respostaTexto;
}
