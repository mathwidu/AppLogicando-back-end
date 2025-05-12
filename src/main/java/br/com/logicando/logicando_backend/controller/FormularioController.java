package br.com.logicando.logicando_backend.controller;

import br.com.logicando.logicando_backend.dto.CreateFormularioDTO;
import br.com.logicando.logicando_backend.dto.FormularioResponseDTO;
import br.com.logicando.logicando_backend.model.UserModel;
import br.com.logicando.logicando_backend.service.FormularioService;
import br.com.logicando.logicando_backend.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/formularios")
public class FormularioController {

    @Autowired
    private FormularioService formularioService;

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody CreateFormularioDTO dto, HttpServletRequest request) {
        UserModel professor = (UserModel) request.getAttribute("usuarioLogado");

        if (!UserUtils.isProfessor(professor)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Apenas professores podem criar formulários.");
        }

        return ResponseEntity.ok(formularioService.criar(dto, professor));
    }

    @GetMapping
    public List<FormularioResponseDTO> listar() {
        return formularioService.listarTodos(); // liberado para todos
    }
}
