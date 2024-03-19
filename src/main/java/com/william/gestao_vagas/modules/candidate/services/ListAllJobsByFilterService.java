package com.william.gestao_vagas.modules.candidate.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.william.gestao_vagas.modules.company.repositories.JobRepository;
import com.william.gestao_vagas.modules.company.entities.Job;

@Service
public class ListAllJobsByFilterService {
    @Autowired
    private JobRepository jobRepository;

    public List<Job> execute (String filter) {
        return this.jobRepository.findByDescriptionContainingIgnoreCase(filter);
    }
}
