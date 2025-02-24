package com.api.casadoconstrutor.horizonte.dto;

import com.api.casadoconstrutor.horizonte.user.UserRole;
import jakarta.validation.constraints.NotBlank;

public record RegisterDTO(
        @NotBlank String login,
        @NotBlank String password,
        UserRole role
) {
}
