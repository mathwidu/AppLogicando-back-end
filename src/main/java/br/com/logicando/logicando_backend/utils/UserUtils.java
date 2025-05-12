package br.com.logicando.logicando_backend.utils;

import br.com.logicando.logicando_backend.model.UserModel;

public class UserUtils {
    public static boolean isAluno(UserModel user) {
        return "ALUNO".equalsIgnoreCase(user.getRole());
    }

    public static boolean isProfessor(UserModel user) {
        return "PROFESSOR".equalsIgnoreCase(user.getRole());
    }
}
