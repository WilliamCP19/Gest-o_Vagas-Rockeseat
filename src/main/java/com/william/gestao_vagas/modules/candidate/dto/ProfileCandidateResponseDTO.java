package com.william.gestao_vagas.modules.candidate.dto;

import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileCandidateResponseDTO {

    @Schema(example = "Desenvolvedor Java")
    private String description;

    @Schema(example = "WilliamCP")
    private String username;

    @Schema(example = "williamcruz@gmail.com")
    private String email;
    private UUID id;

    @Schema(example = "William da Cruz Pires")
    private String name;
}
