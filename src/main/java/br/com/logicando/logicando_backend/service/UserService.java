package br.com.logicando.logicando_backend.service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.logicando.logicando_backend.dto.CreateUserDTO;
import br.com.logicando.logicando_backend.dto.UserResponseDTO;
import br.com.logicando.logicando_backend.model.UserModel;
import br.com.logicando.logicando_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserResponseDTO create(CreateUserDTO dto) {
        if (userRepository.findByUsername(dto.getUsername()) != null) {
            throw new RuntimeException("Usuário já existe");
        }

        UserModel user = new UserModel();
        user.setUsername(dto.getUsername());
        user.setName(dto.getName());
        user.setRole(dto.getRole());
        user.setPassword(BCrypt.withDefaults().hashToString(12, dto.getPassword().toCharArray()));

        var saved = userRepository.save(user);

        UserResponseDTO response = new UserResponseDTO();
        response.setId(saved.getId());
        response.setUsername(saved.getUsername());
        response.setName(saved.getName());
        response.setRole(saved.getRole());

        return response;
    }
}
