package com.william.gestao_vagas.modules.candidate.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.william.gestao_vagas.exception.UserNotFoundException;
import com.william.gestao_vagas.modules.candidate.dto.ProfileCandidateResponseDTO;
import com.william.gestao_vagas.modules.candidate.repository.CandidateRepository;

@Service
public class ProfileCandidateService {
    
    @Autowired
    private CandidateRepository candidateRepository;

    public ProfileCandidateResponseDTO execute (UUID idCandidate) {
        var candidate = candidateRepository.findById(idCandidate)
        .orElseThrow(() -> {
            throw new UserNotFoundException();
        });

        var CandidateDTO = ProfileCandidateResponseDTO.builder()
            .description(candidate.getDescription())
            .username(candidate.getUsername())
            .email(candidate.getEmail())
            .id(candidate.getId())
            .name(candidate.getName())
            .build();
        return CandidateDTO;
    }
}
