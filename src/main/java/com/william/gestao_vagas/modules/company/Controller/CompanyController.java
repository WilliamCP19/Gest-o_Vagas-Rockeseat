package com.william.gestao_vagas.modules.company.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.william.gestao_vagas.modules.company.entities.Company;
import com.william.gestao_vagas.modules.company.services.CreateCompanyService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CreateCompanyService createCompanyUseCase;

    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody Company company) {
        try {
            var result = createCompanyUseCase.execute(company);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
