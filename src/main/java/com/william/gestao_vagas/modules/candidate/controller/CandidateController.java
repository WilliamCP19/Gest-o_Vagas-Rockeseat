package com.william.gestao_vagas.modules.candidate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.william.gestao_vagas.modules.candidate.entities.Candidate;
import com.william.gestao_vagas.modules.candidate.services.CreateCandidateUseCase;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CreateCandidateUseCase createCandidateUseCase;
    
    @PostMapping("/")
    public ResponseEntity<Object> create (@Valid @RequestBody Candidate candidate) {
        try {
            var result = createCandidateUseCase.execute(candidate);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.ok().body(e.getMessage());
        }
        
    }
}
