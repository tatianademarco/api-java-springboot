package com.uri.pw.api_tarefas.exceptions;

public class TarefaNotFoundException extends RuntimeException{

    public TarefaNotFoundException () {
        super("Departamento não encontrado!");
    }

    public TarefaNotFoundException(String msg) {
        super(msg);
    }
}
