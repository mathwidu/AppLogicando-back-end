// CreateUserDTO.java
package br.com.logicando.logicando_backend.dto;

import lombok.Data;

@Data
public class CreateUserDTO {
    private String username;
    private String name;
    private String password;
    private String role; // "ALUNO" ou "PROFESSOR"
}
