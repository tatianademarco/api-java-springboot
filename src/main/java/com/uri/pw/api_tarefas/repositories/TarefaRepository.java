package com.uri.pw.api_tarefas.repositories;

import com.uri.pw.api_tarefas.entities.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepository extends JpaRepository<Tarefa, Long>{
}
