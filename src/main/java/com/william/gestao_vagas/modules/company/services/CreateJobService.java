package com.william.gestao_vagas.modules.company.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.william.gestao_vagas.exception.CompanyNotFoundException;
import com.william.gestao_vagas.modules.company.entities.Job;
import com.william.gestao_vagas.modules.company.repositories.CompanyRepository;
import com.william.gestao_vagas.modules.company.repositories.JobRepository;

@Service
public class CreateJobService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private CompanyRepository companyRepository;

    public Job execute (Job job) {
        companyRepository.findById(job.getCompanyId()).orElseThrow(() -> {
            throw new CompanyNotFoundException();
        });
        return jobRepository.save(job);
    }
}
