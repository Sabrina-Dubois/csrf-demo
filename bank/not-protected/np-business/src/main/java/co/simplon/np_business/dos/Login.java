package co.simplon.np_business.dos;

import jakarta.validation.constraints.NotBlank;

public record Login(@NotBlank String username, @NotBlank String password) {

}