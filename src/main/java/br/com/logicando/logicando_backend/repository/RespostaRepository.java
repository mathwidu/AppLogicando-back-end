package br.com.logicando.logicando_backend.repository;

import br.com.logicando.logicando_backend.model.Resposta;
import br.com.logicando.logicando_backend.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface RespostaRepository extends JpaRepository<Resposta, UUID> {
    List<Resposta> findByAluno(UserModel aluno);
}
