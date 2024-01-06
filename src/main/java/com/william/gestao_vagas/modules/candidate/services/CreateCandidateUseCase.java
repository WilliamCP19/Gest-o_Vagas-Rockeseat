package com.william.gestao_vagas.modules.candidate.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.william.gestao_vagas.exception.UserFoundException;
import com.william.gestao_vagas.modules.candidate.entities.Candidate;
import com.william.gestao_vagas.modules.candidate.repository.CandidateRepository;

@Service
public class CreateCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;
    
    public Candidate execute (Candidate candidate) {
        this.candidateRepository.findByUsernameOrEmail(candidate.getUsername(), candidate.getEmail())
        .ifPresent((user) -> {
            throw new UserFoundException();
        });
        return candidateRepository.save(candidate);
    }
}

