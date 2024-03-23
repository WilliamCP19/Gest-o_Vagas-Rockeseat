package com.william.gestao_vagas.modules.company.Controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.william.gestao_vagas.modules.company.dto.CreateJobDTO;
import com.william.gestao_vagas.modules.company.entities.Job;
import com.william.gestao_vagas.modules.company.services.CreateJobService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/company/job")
public class JobController {

    @Autowired
    private CreateJobService createJobUserCase;

    @PostMapping("/")
    @PreAuthorize("hasRole('COMPANY')")
    @Tag(name = "Vagas", description = "Informações das  Vagas")
    @Operation(summary = "Cadastro de  Vagas", description = "Esta função é responsável por cadastrar as vagas dentro da empresa")
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = Job.class))
        })
    })
    @SecurityRequirement(name = "jwt_auth")
    public Job create (@Valid @RequestBody CreateJobDTO createJobDTO, HttpServletRequest request) {
        var companyId = request.getAttribute("company_id");
        
        var job = Job.builder()
        .benefits(createJobDTO.getBenefits())
        .companyId(UUID.fromString(companyId.toString()))
        .description(createJobDTO.getDescription())
        .level(createJobDTO.getLevel())
        .build();
        return createJobUserCase.execute(job);
    }
}
