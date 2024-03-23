package com.william.gestao_vagas.modules.candidate.controller;

import java.util.UUID;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import com.william.gestao_vagas.modules.candidate.dto.ProfileCandidateResponseDTO;
import com.william.gestao_vagas.modules.candidate.entities.Candidate;
import com.william.gestao_vagas.modules.candidate.services.CreateCandidateService;
import com.william.gestao_vagas.modules.candidate.services.ProfileCandidateService;
import com.william.gestao_vagas.modules.candidate.services.ListAllJobsByFilterService;
import com.william.gestao_vagas.modules.company.entities.Job;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.ArraySchema;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/candidate")
@Tag(name = "Candidate", description = "Informações de candidato")
public class CandidateController {

    @Autowired
    private CreateCandidateService createCandidateService;
    
    @Autowired
    private ProfileCandidateService profileCandidateService;

    @Autowired
    private ListAllJobsByFilterService ListAllJobsByFilterService;

    @PostMapping("/")
    @Operation(summary = "Cadastro de Candidato", description = "Esta função é responsável por cadastrar um candidato")
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = Candidate.class))
        }),
        @ApiResponse(responseCode = "400", description = "Usuário já existe")
    })
    public ResponseEntity<Object> create (@Valid @RequestBody Candidate candidate) {
        try {
            var result = createCandidateService.execute(candidate);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.ok().body(e.getMessage());
        }
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('CANDIDATE')")
    @Operation(summary = "Perfil do Candidato", description = "Esta função é responsável por buscar as informações do candidato")
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = ProfileCandidateResponseDTO.class))
        }),
        @ApiResponse(responseCode = "400", description = "User Not Found")
    })
    @SecurityRequirement(name = "jwt_auth")
    
    public ResponseEntity<Object> get(HttpServletRequest request) {
        var idCandidate = request.getAttribute("candidate_id");
        try {
            var profile = profileCandidateService.execute(UUID.fromString(idCandidate.toString()));
            return ResponseEntity.ok().body(profile);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/job/")
    @PreAuthorize("hasRole('CANDIDATE')")

    @Operation(summary = "Listagem de vagas disponível para o candidato", description = "Esta função é responsável por listar todas as vagas disponíveis, baseada no filtro")
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {
            @Content(array = @ArraySchema(schema = @Schema(implementation = Job.class)))
        })
    })
    @SecurityRequirement(name = "jwt_auth")
    public List<Job> findJobByFilter (@RequestParam String filter) {
        return this.ListAllJobsByFilterService.execute(filter);
    }
}
