package hva.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The Specie class represents a species in the veterinary hotel.
 * It implements the Serializable interface to allow instances to be serialized and deserialized,
 * preserving information about the species, the animals belonging to it, and the veterinarians who can treat it.
 */
public class Specie implements Serializable {
    private final String _name;
    private final String _id;
    private List<Animal> _animaisPertencentes;


    public Specie(String id, String name) {
        this._name = name;
        this._id = id;
        this._animaisPertencentes = new ArrayList<>();
    }


    public String get_name() { return _name; }
    public String get_id() {
        return _id;
    }
    public List<Animal> get_animaisPertencentes() {
        return _animaisPertencentes;
    }


    /**
     * Adds an animal to the list of animals belonging to this specie.
     *
     * @param a The animal to be added to this specie.
     */
    public void addAnimal(Animal a){
        _animaisPertencentes.add(a);
    }

}
