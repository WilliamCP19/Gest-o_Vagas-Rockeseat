package com.william.gestao_vagas.modules.candidate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.william.gestao_vagas.exception.JobNotFoundException;
import com.william.gestao_vagas.exception.UserNotFoundException;
import com.william.gestao_vagas.modules.candidate.entities.Candidate;
import com.william.gestao_vagas.modules.candidate.repository.CandidateRepository;
import com.william.gestao_vagas.modules.candidate.services.ApplyJobCandidate;
import com.william.gestao_vagas.modules.company.repositories.JobRepository;

@ExtendWith(MockitoExtension.class)
public class ApplyJobCandidateTest {

    @InjectMocks
    private ApplyJobCandidate applyJobCandidate;

    @Mock
    private CandidateRepository candidateRepository;

    @Mock
    private JobRepository jobRepository;
    
    @Test
    @DisplayName("Usuário não existe. Não pode se candidatar")
    public void verificar_se_candidato_existe_para_se_candidatar () {
        try {
            applyJobCandidate.execute(null, null);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(UserNotFoundException.class);
        }
    }

    @Test
    @DisplayName("Job não existe. Não pode se candidatar")
    public void verificar_se_job_existe_para_se_candidar () {
        var idCandidate = UUID.randomUUID();
        var candidato = new Candidate();
        candidato.setId(idCandidate);

        when(candidateRepository.findById(idCandidate)).thenReturn(Optional.of(candidato));
        
        try {
            applyJobCandidate.execute(idCandidate, null);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(JobNotFoundException.class);
        }
    }
}
