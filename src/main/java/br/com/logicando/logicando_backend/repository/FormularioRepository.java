package br.com.logicando.logicando_backend.repository;

import br.com.logicando.logicando_backend.model.Formulario;
import br.com.logicando.logicando_backend.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface FormularioRepository extends JpaRepository<Formulario, UUID> {
    List<Formulario> findByCriador(UserModel criador);
}
