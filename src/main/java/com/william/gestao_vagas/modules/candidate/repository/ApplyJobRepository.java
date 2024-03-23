package com.william.gestao_vagas.modules.candidate.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.william.gestao_vagas.modules.candidate.entities.ApplyJob;

public interface ApplyJobRepository extends JpaRepository<ApplyJob, UUID> {
    
}
