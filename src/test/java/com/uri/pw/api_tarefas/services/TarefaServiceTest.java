package com.uri.pw.api_tarefas.services;

import com.uri.pw.api_tarefas.entities.Tarefa;
import com.uri.pw.api_tarefas.repositories.TarefaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import com.uri.pw.api_tarefas.exceptions.TarefaNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
class TarefaServiceTest {

    @Mock
    private TarefaRepository repository;

    @InjectMocks
    private TarefaService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Deve retronar a lista de tarefas")
    void findAll() {
        List<Tarefa> tarefas = new ArrayList<>();
        assertTrue(tarefas.add(new Tarefa()));
        tarefas.add(new Tarefa());

        when(repository.findAll()).thenReturn(tarefas);

        List<Tarefa> result = service.findAll();

        assertEquals(2, result.size());
    }

    @Test
    @DisplayName("Deve retronar uma tarefa existente pelo id")
    void findById() {
        Tarefa tarefa = new Tarefa();
        when(repository.findById(1L)).thenReturn(Optional.of(tarefa));

        Tarefa result = service.findById(1L);

        assertNotNull(result);
    }

    @Test
    @DisplayName("Deve retornar mensagem de erro de id não encontrado")
    void testFindByIdNotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(TarefaNotFoundException.class, () -> service.findById(1L));
    }

    @Test
    @DisplayName("Deve criar uma tarefa")
    void create() {
        Tarefa tarefa = new Tarefa();
        when(repository.save(tarefa)).thenReturn(tarefa);

        Tarefa result = service.create(tarefa);

        assertNotNull(result);
    }

    @Test
    @DisplayName("Deve atualizar uma tarefa existente pelo id")
    void update() {
        Tarefa existingTarefa = new Tarefa();
        existingTarefa.setId(1L);
        existingTarefa.setDescricao("Old Description");
        existingTarefa.setDataCriacao(LocalDateTime.now());
        existingTarefa.setDataLimite(LocalDateTime.now().plusDays(5));
        existingTarefa.setFinalizada(false);

        Tarefa newTarefa = new Tarefa();
        newTarefa.setDescricao("New Description");
        newTarefa.setDataCriacao(LocalDateTime.now().minusDays(1));
        newTarefa.setDataLimite(LocalDateTime.now().plusDays(10));
        newTarefa.setFinalizada(true);

        when(repository.findById(1L)).thenReturn(Optional.of(existingTarefa));
        when(repository.save(any(Tarefa.class))).thenReturn(existingTarefa);

        Tarefa updatedTarefa = service.update(1L, newTarefa);

        assertNotNull(updatedTarefa);
        assertEquals("New Description", updatedTarefa.getDescricao());
        assertEquals(newTarefa.getDataCriacao(), updatedTarefa.getDataCriacao());
        assertEquals(newTarefa.getDataLimite(), updatedTarefa.getDataLimite());
        assertTrue(updatedTarefa.getFinalizada());

        verify(repository).findById(1L);
        verify(repository).save(existingTarefa);
    }

    @Test
    @DisplayName("Deve deletar uma tarefa existente pelo id")
    void delete() {
        Tarefa existingTarefa = new Tarefa();
        existingTarefa.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(existingTarefa));

        service.delete(1L);

        verify(repository).deleteById(1L);
    }

    @Test
    @DisplayName("Deve atualizar o status da tarefa pelo id como finalizada=true")
    void updateFinalizada() {
        Tarefa tarefa = new Tarefa();
        when(repository.findById(1L)).thenReturn(Optional.of(tarefa));
        when(repository.save(tarefa)).thenReturn(tarefa);

        Tarefa result = service.updateComplete(1L);

        assertNotNull(result);
    }
}