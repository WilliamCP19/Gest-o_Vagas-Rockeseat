package com.william.gestao_vagas.modules.candidate.controller;

import org.springframework.web.bind.annotation.RestController;

import com.william.gestao_vagas.modules.candidate.dto.AuthCandidateRequestDTO;
import com.william.gestao_vagas.modules.candidate.services.AuthCandidateService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/auth")
public class AuthCandidateController {

    @Autowired
    private AuthCandidateService authCandidateService;

    @PostMapping("candidate/")
    public ResponseEntity<Object> auth(@RequestBody AuthCandidateRequestDTO authCandidateRequestDTO) {

        try {
            var token = authCandidateService.execute(authCandidateRequestDTO);
            return ResponseEntity.ok().body(token);
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
