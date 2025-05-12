package br.com.logicando.logicando_backend.controller;

import br.com.logicando.logicando_backend.dto.CreatePerguntaDTO;
import br.com.logicando.logicando_backend.dto.PerguntaResponseDTO;
import br.com.logicando.logicando_backend.model.UserModel;
import br.com.logicando.logicando_backend.service.PerguntaService;
import br.com.logicando.logicando_backend.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/perguntas")
public class PerguntaController {

    @Autowired
    private PerguntaService perguntaService;

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody CreatePerguntaDTO dto, HttpServletRequest request) {
        UserModel usuario = (UserModel) request.getAttribute("usuarioLogado");

        if (!UserUtils.isProfessor(usuario)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Apenas professores podem criar perguntas.");
        }

        return ResponseEntity.ok(perguntaService.criar(dto));
    }

    @GetMapping("/formulario/{formularioId}")
    public List<PerguntaResponseDTO> listarPorFormulario(@PathVariable UUID formularioId) {
        return perguntaService.listarPorFormulario(formularioId);
    }
}
