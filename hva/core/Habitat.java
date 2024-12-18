package hva.core;

import hva.core.Animal;
import hva.core.Tree;
import hva.core.exception.UnknownSpeciesIdException;

import java.io.Serializable;
import java.util.*;


/**
 * The Habitat class represents a habitat in the veterinary hotel.
 * It implements the Serializable interface to allow instances to be serialized and deserialized,
 * preserving information about the habitat, the animals it contains, and the trees associated with it.
 */
public class Habitat implements Serializable {

    private final String _id;
    private final String _nome;
    private int _area;
    private final List<Animal> _animais;
    private final List<Tree> _arvores;
    private final Map<Specie, Influence> _influencia;



    public Habitat(String id, String nome, int area) {
        this._id = id;
        this._nome = nome;
        this._area = area;
        this._animais = new ArrayList<>();
        this._arvores = new ArrayList<>();
        this._influencia = new HashMap<>();
    }

    public String get_id() {
        return _id;
    }

    public String get_nome() {
        return _nome;
    }

    public int get_area() {
        return _area;
    }

    public List<Tree> get_arvores() {
        return _arvores;
    }

    public List<Animal> get_animais(){
        _animais.sort(Comparator.comparing(a -> a.get_id().toLowerCase()));
        return Collections.unmodifiableList(_animais);
    }

    /**
     * This method retrieves a list of all species that are associated with the animals currently in the system.
     * It gathers the species by iterating through the animals and extracting their species.
     *
     * @return A list of species associated with the animals in the system.
     */
    public List<Specie> getSpecies(){
        List<Specie> s = new ArrayList<Specie>();
        for(Animal a : _animais){
            s.add(a.get_specie());
        }
        return s;
    }

    /**
     * This method retrieves the influence associated with a specific species.
     * If no influence is found for the species, it defaults to "NEUTRA".
     *
     * @param s The species for which the influence is being retrieved.
     * @return The influence associated with the species, or "NEUTRA" if no specific influence is found.
     */
    public Influence getInfluencia(Specie s){
        return _influencia.getOrDefault(s, Influence.NEUTRA);
    }

    /**
     * This method updates the influence associated with a specific species.
     * If the species already has an influence, it will be replaced with the new one.
     * If the species does not have an influence, the new influence will be added.
     *
     * @param s The species whose influence is being updated.
     * @param inf The new influence to associate with the species.
     */
    public void changeInfluence(Specie s, Influence inf) {
        if(this._influencia.containsKey(s))
            this._influencia.replace(s,inf);
        else
            this._influencia.put(s,inf);
    }

    public void set_area(int _area) {
        this._area = _area;
    }

    /**
     * Adds an animal to the habitat.
     *
     * @param a The animal to be added to the habitat.
     */
    public void addAnimal(Animal a){
        _animais.add(a);
    }

    /**
     * This method removes a specific animal from the list of animals in the system.
     *
     * @param a The animal to be removed from the list.
     */
    public void removeAnimal(Animal a){
        _animais.remove(a);
    }

    /**
     * Adds a tree to the habitat.
     *
     * @param t The tree to be added to the habitat.
     */
    public void addTree(Tree t){
        _arvores.add(t);
    }

}
