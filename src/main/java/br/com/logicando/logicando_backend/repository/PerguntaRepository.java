package br.com.logicando.logicando_backend.repository;

import br.com.logicando.logicando_backend.model.Pergunta;
import br.com.logicando.logicando_backend.model.Formulario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PerguntaRepository extends JpaRepository<Pergunta, UUID> {
    List<Pergunta> findByFormulario(Formulario formulario);
}
