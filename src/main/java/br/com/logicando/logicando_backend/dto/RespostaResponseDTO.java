// RespostaResponseDTO.java
package br.com.logicando.logicando_backend.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class RespostaResponseDTO {
    private UUID id;
    private String perguntaTexto;
    private String respostaTexto;
    private String alunoNome;
}
