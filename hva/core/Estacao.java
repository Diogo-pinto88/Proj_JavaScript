package hva.core;

public enum Estacao {
    VERAO("Verão"),
    PRIMAVERA("Primavera"),
    OUTONO("Outono"),
    INVERNO("Inverno");
    private final String _nome;

    Estacao(String nome) {
        this._nome = nome;
    }

    public String getNome() {
        return _nome;
    }
}

