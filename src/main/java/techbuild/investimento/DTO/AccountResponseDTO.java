package techbuild.investimento.DTO;


import java.util.UUID;

public record AccountResponseDTO(
        UUID accountId,
        String description,
        BillingAddressDTO billingAddress
) {}