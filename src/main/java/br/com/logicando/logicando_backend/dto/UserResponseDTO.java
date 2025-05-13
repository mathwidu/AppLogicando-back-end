package br.com.logicando.logicando_backend.dto;

import br.com.logicando.logicando_backend.model.UserModel;
import lombok.Data;

import java.util.UUID;

@Data
public class UserResponseDTO {
    private UUID id;
    private String username;
    private String name;
    private String role;

    public UserResponseDTO(UserModel user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.name = user.getName();
        this.role = user.getRole();
    }

    public UserResponseDTO() {
        // Construtor vazio para serialização
    }
}
