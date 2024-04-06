package com.william.gestao_vagas.modules.company.controllers;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.william.gestao_vagas.exception.CompanyNotFoundException;
import com.william.gestao_vagas.modules.company.dto.CreateJobDTO;
import com.william.gestao_vagas.modules.company.entities.Company;
import com.william.gestao_vagas.modules.company.repositories.CompanyRepository;
import com.william.gestao_vagas.utils.TestUtils;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class CreateJobControllerTest {
    
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private CompanyRepository companyRepository;

    @Before
    public void setup () {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
        mvc = MockMvcBuilders.webAppContextSetup(context)
            .apply(SecurityMockMvcConfigurers.springSecurity()).build();
    }

    @Test
    public void criar_novo_job () throws Exception {

        var company = Company.builder()
        .description("COMPANY_DESCRIPTION")
        .email("company@gmail.com")
        .password("123company123")
        .username("COMPANY_USERNAME")
        .nome("COMAPNY_NOME")
        .build();

        company = companyRepository.saveAndFlush(company);

        var createdJobDTO = CreateJobDTO.builder()
            .benefits("BENEFITS_TEST")
            .description("DESCRIPTION_TEST")
            .level("LEVEL_TEST").build();

        var result = mvc.perform (
            MockMvcRequestBuilders.post("/company/job/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtils.objectToJSON(createdJobDTO))
            .header("Authorization", 
            TestUtils.generateToken(company.getId(), "JAVAGAS_@123#"))
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void company_nao_existe_job_nao_criado () throws Exception {
        var createdJobDTO = CreateJobDTO.builder()
            .benefits("BENEFITS_TEST")
            .description("DESCRIPTION_TEST")
            .level("LEVEL_TEST").build();

        mvc.perform (MockMvcRequestBuilders.post("/company/job/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtils.objectToJSON(createdJobDTO))
            .header("Authorization", 
                TestUtils.generateToken(UUID.randomUUID(), "JAVAGAS_@123#"))
        ).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
