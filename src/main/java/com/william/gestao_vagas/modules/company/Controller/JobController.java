package com.william.gestao_vagas.modules.company.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.william.gestao_vagas.modules.company.entities.Job;
import com.william.gestao_vagas.modules.company.services.CreateJobUserCase;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/company/job")
public class JobController {

    @Autowired
    private CreateJobUserCase createJobUserCase;

    @PostMapping("/")
    public Job create (@Valid @RequestBody Job job) {
        return createJobUserCase.execute(job);
    }
}
