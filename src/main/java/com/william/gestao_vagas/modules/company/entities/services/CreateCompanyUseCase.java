package com.william.gestao_vagas.modules.company.entities.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.william.gestao_vagas.exception.UserFoundException;
import com.william.gestao_vagas.modules.company.entities.Company;
import com.william.gestao_vagas.modules.company.repositories.CompanyRepository;

@Service
public class CreateCompanyUseCase {
    
    @Autowired
    private CompanyRepository companyRepository;

    public Company execute (Company company) {
        companyRepository.findByUsernameOrEmail(company.getUsername(), company.getEmail())
        .ifPresent((user) -> {
            throw new UserFoundException();
        });
        return companyRepository.save(company);
    }
}
