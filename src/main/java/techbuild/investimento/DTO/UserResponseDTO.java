package techbuild.investimento.DTO;

import java.util.List;
import java.util.UUID;

public record UserResponseDTO(
        UUID userId,
        String username,
        String email,
        List<AccountResponseDTO> accounts
) {}
