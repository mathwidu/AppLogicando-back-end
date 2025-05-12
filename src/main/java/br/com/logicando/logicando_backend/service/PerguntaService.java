package br.com.logicando.logicando_backend.service;

import br.com.logicando.logicando_backend.dto.CreatePerguntaDTO;
import br.com.logicando.logicando_backend.dto.PerguntaResponseDTO;
import br.com.logicando.logicando_backend.model.Formulario;
import br.com.logicando.logicando_backend.model.Pergunta;
import br.com.logicando.logicando_backend.repository.FormularioRepository;
import br.com.logicando.logicando_backend.repository.PerguntaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PerguntaService {

    @Autowired
    private PerguntaRepository perguntaRepository;

    @Autowired
    private FormularioRepository formularioRepository;

    public PerguntaResponseDTO criar(CreatePerguntaDTO dto) {
        Formulario formulario = formularioRepository.findById(dto.getFormularioId())
                .orElseThrow(() -> new RuntimeException("Formulário não encontrado"));

        Pergunta p = new Pergunta();
        p.setTexto(dto.getTexto());
        p.setTipo(dto.getTipo());
        p.setFormulario(formulario);

        Pergunta salva = perguntaRepository.save(p);

        PerguntaResponseDTO resposta = new PerguntaResponseDTO();
        resposta.setId(salva.getId());
        resposta.setTexto(salva.getTexto());
        resposta.setTipo(salva.getTipo());

        return resposta;
    }

    public List<PerguntaResponseDTO> listarPorFormulario(UUID formularioId) {
        Formulario formulario = formularioRepository.findById(formularioId)
                .orElseThrow(() -> new RuntimeException("Formulário não encontrado"));

        return perguntaRepository.findByFormulario(formulario).stream().map(p -> {
            PerguntaResponseDTO dto = new PerguntaResponseDTO();
            dto.setId(p.getId());
            dto.setTexto(p.getTexto());
            dto.setTipo(p.getTipo());
            return dto;
        }).collect(Collectors.toList());
    }
}
