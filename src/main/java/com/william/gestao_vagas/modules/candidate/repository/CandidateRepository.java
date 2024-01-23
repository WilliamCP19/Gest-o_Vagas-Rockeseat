package com.william.gestao_vagas.modules.candidate.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.william.gestao_vagas.modules.candidate.entities.Candidate;


public interface CandidateRepository extends JpaRepository <Candidate, UUID>{
    Optional<Candidate> findByUsernameOrEmail(String username, String email);
    Optional<Candidate> findByUsername(String username);
}
