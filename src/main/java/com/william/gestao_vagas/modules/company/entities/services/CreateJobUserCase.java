package com.william.gestao_vagas.modules.company.entities.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.william.gestao_vagas.modules.company.entities.Job;
import com.william.gestao_vagas.modules.company.repositories.JobRepository;

@Service
public class CreateJobUserCase {

    @Autowired
    private JobRepository jobRepository;
    public Job execute (Job job) {
        return jobRepository.save(job);
    }
}
