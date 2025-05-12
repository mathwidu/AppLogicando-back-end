package br.com.logicando.logicando_backend.controller;

import br.com.logicando.logicando_backend.dto.CreateRespostaDTO;
import br.com.logicando.logicando_backend.dto.RespostaResponseDTO;
import br.com.logicando.logicando_backend.model.UserModel;
import br.com.logicando.logicando_backend.service.RespostaService;
import br.com.logicando.logicando_backend.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/respostas")
public class RespostaController {

    @Autowired
    private RespostaService respostaService;

    @PostMapping
    public ResponseEntity<?> responder(@RequestBody List<CreateRespostaDTO> respostas, HttpServletRequest request) {
        UserModel aluno = (UserModel) request.getAttribute("usuarioLogado");

        if (!UserUtils.isAluno(aluno)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Apenas alunos podem enviar respostas.");
        }

        respostaService.salvarRespostas(respostas, aluno);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/formulario/{formularioId}")
    public ResponseEntity<?> listarRespostas(@PathVariable UUID formularioId, HttpServletRequest request) {
        UserModel user = (UserModel) request.getAttribute("usuarioLogado");

        if (!UserUtils.isProfessor(user)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Apenas professores podem visualizar respostas.");
        }

        return ResponseEntity.ok(respostaService.listarRespostasDeFormulario(formularioId));
    }
}
