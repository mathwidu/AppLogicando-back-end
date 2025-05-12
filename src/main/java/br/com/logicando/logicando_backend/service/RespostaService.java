package br.com.logicando.logicando_backend.service;

import br.com.logicando.logicando_backend.dto.CreateRespostaDTO;
import br.com.logicando.logicando_backend.dto.RespostaResponseDTO;
import br.com.logicando.logicando_backend.model.Pergunta;
import br.com.logicando.logicando_backend.model.Resposta;
import br.com.logicando.logicando_backend.model.UserModel;
import br.com.logicando.logicando_backend.repository.PerguntaRepository;
import br.com.logicando.logicando_backend.repository.RespostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RespostaService {

    @Autowired
    private RespostaRepository respostaRepository;

    @Autowired
    private PerguntaRepository perguntaRepository;

    public void salvarRespostas(List<CreateRespostaDTO> respostasDTO, UserModel aluno) {
        for (CreateRespostaDTO dto : respostasDTO) {
            Pergunta pergunta = perguntaRepository.findById(dto.getPerguntaId())
                    .orElseThrow(() -> new RuntimeException("Pergunta não encontrada"));

            Resposta resposta = new Resposta();
            resposta.setPergunta(pergunta);
            resposta.setAluno(aluno);
            resposta.setRespostaTexto(dto.getRespostaTexto());

            respostaRepository.save(resposta);
        }
    }

    public List<RespostaResponseDTO> listarRespostasDeFormulario(UUID formularioId) {
        return respostaRepository.findAll().stream()
                .filter(r -> r.getPergunta().getFormulario().getId().equals(formularioId))
                .map(r -> {
                    RespostaResponseDTO dto = new RespostaResponseDTO();
                    dto.setId(r.getId());
                    dto.setPerguntaTexto(r.getPergunta().getTexto());
                    dto.setRespostaTexto(r.getRespostaTexto());
                    dto.setAlunoNome(r.getAluno().getName());
                    return dto;
                }).collect(Collectors.toList());
    }
}
