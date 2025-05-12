package br.com.logicando.logicando_backend.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name = "tb_formulario")
public class Formulario {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    private String titulo;

    @ManyToOne
    @JoinColumn(name = "criador_id")
    private UserModel criador;

    @CreationTimestamp
    private LocalDateTime criadoEm;
}
