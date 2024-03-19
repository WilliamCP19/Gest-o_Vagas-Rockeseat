package com.william.gestao_vagas.modules.company.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.william.gestao_vagas.modules.company.entities.Job;

public interface JobRepository extends JpaRepository <Job, UUID> {
    List<Job> findByDescriptionContainingIgnoreCase(String filter);
}