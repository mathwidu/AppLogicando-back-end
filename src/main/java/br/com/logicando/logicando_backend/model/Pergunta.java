package br.com.logicando.logicando_backend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity(name = "tb_pergunta")
public class Pergunta {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    private String texto;
    private String tipo; // "TEXTO", "RADIO", etc.

    @ManyToOne
    @JoinColumn(name = "formulario_id")
    private Formulario formulario;
}
