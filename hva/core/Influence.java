package hva.core;

public enum Influence {
    POSITIVA("POS"),
    NEGATIVA("NEG"),
    NEUTRA("NEU");

    private final String _nome;

    Influence(String nome){
        this._nome = nome;
    }

    public String getNome(){
        return _nome;
    }

}
