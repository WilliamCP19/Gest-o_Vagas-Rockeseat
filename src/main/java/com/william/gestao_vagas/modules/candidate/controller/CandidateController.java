package com.william.gestao_vagas.modules.candidate.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.william.gestao_vagas.modules.candidate.entities.Candidate;
import com.william.gestao_vagas.modules.candidate.services.CreateCandidateService;
import com.william.gestao_vagas.modules.candidate.services.ProfileCandidateService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CreateCandidateService createCandidateUseCase;
    
    @Autowired
    private ProfileCandidateService profileCandidateService;

    @PostMapping("/")
    public ResponseEntity<Object> create (@Valid @RequestBody Candidate candidate) {
        try {
            var result = createCandidateUseCase.execute(candidate);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.ok().body(e.getMessage());
        }
    }

    @GetMapping("/")
        public ResponseEntity<Object> get(HttpServletRequest httpServletRequest) {
            var idCandidate = httpServletRequest.getAttribute("candidate_id");
            try {
                var profile = profileCandidateService.execute(UUID.fromString(idCandidate.toString()));
                return ResponseEntity.ok().body(profile);
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }
}
