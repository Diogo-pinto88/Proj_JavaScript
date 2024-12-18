package hva.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * The Vaccine class represents a vaccine in the veterinary hotel.
 * It implements the Serializable interface to allow instances to be serialized and deserialized,
 * preserving information about the vaccine, the species it applies to, and the records of its usage.
 */
public class Vaccine implements Serializable {
    private final String _id;
    private final String _nome;
    private final List<Specie> _especies;

    public Vaccine(String id, String nome, List<Specie> especies) {
        this._id = id;
        this._nome = nome;
        this._especies = especies;
    }

    public String get_id() {
        return _id;
    }
    public String get_nome() {
        return _nome;
    }
    public List<Specie> get_especies() {
        _especies.sort(Comparator.comparing(Specie::get_id));
        return Collections.unmodifiableList(_especies);
    }


}
