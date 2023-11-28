package com.eliasfs06.tinktime.model;

public class BodyPiercerFactory implements FuncionarioFactory {
    @Override
    public Funcionario createFuncionario() {
        return new BodyPiercer();
    }
}
