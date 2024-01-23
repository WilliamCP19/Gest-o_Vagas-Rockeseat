package com.william.gestao_vagas.modules.company.Controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.william.gestao_vagas.modules.company.dto.CreateJobDTO;
import com.william.gestao_vagas.modules.company.entities.Job;
import com.william.gestao_vagas.modules.company.services.CreateJobService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/company/job")
public class JobController {

    @Autowired
    private CreateJobService createJobUserCase;

    @PostMapping("/")
    public Job create (@Valid @RequestBody CreateJobDTO createJobDTO, HttpServletRequest request) {
        var companyId = request.getAttribute("company_id");
        //job.setCompanyId(UUID.fromString(companyId.toString()));
        
        var job = Job.builder()
        .benefits(createJobDTO.getBenefits())
        .companyId(UUID.fromString(companyId.toString()))
        .description(createJobDTO.getDescription())
        .level(createJobDTO.getLevel())
        .build();
        return createJobUserCase.execute(job);
    }
}
