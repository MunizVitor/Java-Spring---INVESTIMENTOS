package techbuild.investimento.DTO;

import org.springframework.validation.annotation.Validated;

import java.util.UUID;

public record CreateUserDTO(
        @Validated
        String username,
        @Validated
        String email,
        @Validated
        String password) {
}
