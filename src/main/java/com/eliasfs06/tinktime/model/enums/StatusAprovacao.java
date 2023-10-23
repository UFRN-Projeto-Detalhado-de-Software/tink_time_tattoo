package com.eliasfs06.tinktime.model.enums;

public enum StatusAprovacao {
    APROVADO {
        @Override
        public String getDenominacao() {
            return "Aprovado";
        }
    },
    REPROVADO {
        @Override
        public String getDenominacao() {
            return "Reprovado";
        }
    },
    PENDENTE {
        @Override
        public String getDenominacao() {
            return "Pendente";
        }
    };

    public abstract String getDenominacao();
}
