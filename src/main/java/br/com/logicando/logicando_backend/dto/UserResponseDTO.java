// UserResponseDTO.java
package br.com.logicando.logicando_backend.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class UserResponseDTO {
    private UUID id;
    private String username;
    private String name;
    private String role;
}
