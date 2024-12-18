package hva.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Animal implements Serializable {
    private final String _id;
    private final String _nome;
    private final Specie _especie;
    private final List<String> _estadoSaude;
    private Habitat _habitat;

    public Animal(String id, String nome, Habitat habitat, Specie especie) {
        this._id = id;
        this._nome = nome;
        this._especie = especie;
        this._habitat = habitat;
        this._estadoSaude = new ArrayList<>();
    }

    public List<String> get_estadoSaude() { return _estadoSaude; }
    public String get_id() {
        return _id;
    }
    public String get_name() {
        return _nome;
    }
    public Specie get_specie() {
        return _especie;
    }
    public Habitat get_habitat() {
        return _habitat;
    }

    /**
     * This method changes the current habitat of an animal.
     *
     * @param h The new habitat to which the animal will be moved.
     */
    public void changeHabitat(Habitat h){
       this._habitat = h;
    }

    /**
     * This method updates the health status of the animal by adding a new health condition.
     *
     * @param estado The new health condition or status to be added to the animal's health history.
     */
    public void atualizaEstadoSaude(String estado) {
        _estadoSaude.add(estado);
    }

}

