package br.com.logicando.logicando_backend.controller;

import br.com.logicando.logicando_backend.dto.CreateUserDTO;
import br.com.logicando.logicando_backend.dto.UserResponseDTO;
import br.com.logicando.logicando_backend.model.UserModel;
import br.com.logicando.logicando_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/usuarios")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> criarUsuario(@RequestBody CreateUserDTO dto) {
        var user = userService.create(dto);
        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<UserResponseDTO> login(HttpServletRequest request) {
        UserModel user = (UserModel) request.getAttribute("usuarioLogado");
        return ResponseEntity.ok(new UserResponseDTO(user));
    }
}
