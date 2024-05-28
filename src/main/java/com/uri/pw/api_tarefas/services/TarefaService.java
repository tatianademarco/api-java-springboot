package com.uri.pw.api_tarefas.services;

import com.uri.pw.api_tarefas.entities.Tarefa;
import com.uri.pw.api_tarefas.exceptions.TarefaNotFoundException;
import com.uri.pw.api_tarefas.repositories.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository repository;

    public List<Tarefa> findAll() {
        return repository.findAll();
    }

    public Tarefa findById(Long id) {
        Optional<Tarefa> result = repository.findById(id);
        if (result.isPresent()) {
            return result.get();
        }

        throw new TarefaNotFoundException();
    }

    public Tarefa create(Tarefa tarefa) {
        return repository.save(tarefa);
    }

    public Tarefa update(Long id, Tarefa newTarefa) {
        Tarefa currentTarefa = findById(id);
        currentTarefa.setDescricao(newTarefa.getDescricao());
        currentTarefa.setDataCriacao(newTarefa.getDataCriacao());
        currentTarefa.setDataLimite(newTarefa.getDataLimite());
        currentTarefa.setFinalizada(newTarefa.getFinalizada());
        Tarefa tarefa = repository.save(currentTarefa);
        return tarefa;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Tarefa updateComplete(Long id) {
        Tarefa currentTarefa = findById(id);
        currentTarefa.setFinalizada(true);
        Tarefa tarefa = repository.save(currentTarefa);
        return tarefa;
    }
}
