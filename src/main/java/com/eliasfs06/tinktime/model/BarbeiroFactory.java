package com.eliasfs06.tinktime.model;

public class BarbeiroFactory implements FuncionarioFactory {
    @Override
    public Funcionario createFuncionario() {
        return new Barbeiro();
    }
}
