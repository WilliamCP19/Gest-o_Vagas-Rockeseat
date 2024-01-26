package com.william.gestao_vagas.modules.company.services;

import java.time.Instant;
import java.util.Arrays;
import java.time.Duration;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.william.gestao_vagas.modules.candidate.dto.AuthCandidateRequestDTO;
import com.william.gestao_vagas.modules.candidate.dto.AuthCandidateResponseDTO;
import com.william.gestao_vagas.modules.company.dto.AuthCompanyDTO;
import com.william.gestao_vagas.modules.company.dto.AuthCompanyResponseDTO;
import com.william.gestao_vagas.modules.company.repositories.CompanyRepository;


@Service
public class AuthCompanyService {
    
    @Value("${security.token.secret}")
    private String secretKey;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Object execute (AuthCompanyDTO authCompanyDTO) throws AuthenticationException {
        var company = companyRepository.findByUsername(authCompanyDTO.getUsername()).orElseThrow (
            () -> {
                throw new UsernameNotFoundException("Company not found");
            }
        );
        var passwordMatches = passwordEncoder.matches(authCompanyDTO.getPassword(), company.getPassword());

        if (!passwordMatches) {
            throw new AuthenticationException();
        }

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        var expiresIn = Instant.now().plus(Duration.ofHours(2));
        var token = JWT.create().withIssuer("javagas")
            .withExpiresAt(expiresIn)
            .withSubject(company.getId().toString())
            .withClaim("roles", Arrays.asList("COMPANY"))
            .sign(algorithm);

        var authCompanyResponseDTO = AuthCompanyResponseDTO.builder()
            .access_token(token)
            .expires_in(expiresIn.toEpochMilli())
            .build();
        return authCompanyResponseDTO;
    }
}
