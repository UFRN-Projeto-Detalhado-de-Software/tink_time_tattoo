package com.eliasfs06.tinktime.model;

public class TatuadorFactory implements FuncionarioFactory {
    @Override
    public Funcionario createFuncionario() {
        return new Tatuador();
    }
}
