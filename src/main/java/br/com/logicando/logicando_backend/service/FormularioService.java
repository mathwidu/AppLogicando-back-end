package br.com.logicando.logicando_backend.service;

import br.com.logicando.logicando_backend.dto.CreateFormularioDTO;
import br.com.logicando.logicando_backend.dto.FormularioResponseDTO;
import br.com.logicando.logicando_backend.model.Formulario;
import br.com.logicando.logicando_backend.model.UserModel;
import br.com.logicando.logicando_backend.repository.FormularioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FormularioService {

    @Autowired
    private FormularioRepository formularioRepository;

    public FormularioResponseDTO criar(CreateFormularioDTO dto, UserModel criador) {
        Formulario f = new Formulario();
        f.setTitulo(dto.getTitulo());
        f.setCriador(criador);

        var salvo = formularioRepository.save(f);

        FormularioResponseDTO response = new FormularioResponseDTO();
        response.setId(salvo.getId());
        response.setTitulo(salvo.getTitulo());
        response.setCriadorNome(salvo.getCriador().getName());
        response.setCriadoEm(salvo.getCriadoEm());
        return response;
    }

    public List<FormularioResponseDTO> listarTodos() {
        return formularioRepository.findAll().stream().map(f -> {
            FormularioResponseDTO dto = new FormularioResponseDTO();
            dto.setId(f.getId());
            dto.setTitulo(f.getTitulo());
            dto.setCriadorNome(f.getCriador().getName());
            dto.setCriadoEm(f.getCriadoEm());
            return dto;
        }).collect(Collectors.toList());
    }
}
