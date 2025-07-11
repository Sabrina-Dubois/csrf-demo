package co.simplon.p_business.dos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record Transfer(@NotBlank String ToAccountNumber, @Min(1) int amount) {
}