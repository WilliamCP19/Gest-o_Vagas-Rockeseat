package com.william.gestao_vagas.modules.candidate.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.william.gestao_vagas.exception.JobNotFoundException;
import com.william.gestao_vagas.exception.UserNotFoundException;
import com.william.gestao_vagas.modules.candidate.repository.CandidateRepository;
import com.william.gestao_vagas.modules.company.repositories.JobRepository;

@Service
public class ApplyJobCandidate {
    
    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private JobRepository jobRepository;

    public void execute (UUID idCandidate, UUID idJob) {
        var candidate = candidateRepository.findById(idCandidate)
        .orElseThrow(() -> {
            throw new UserNotFoundException();
        });

        var job = jobRepository.findById(idJob)
        .orElseThrow(() -> {
            throw new JobNotFoundException();
        });
        
    }
}
