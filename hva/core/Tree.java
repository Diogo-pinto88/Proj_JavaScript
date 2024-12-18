package hva.core;

import java.io.Serializable;

import static java.lang.Math.log;

/**
 * The Tree class is an abstract representation of a tree in the veterinary hotel.
 * It implements the Serializable interface to allow instances of trees
 * to be serialized and deserialized, preserving their state.
 * This class provides the basic attributes and methods for managing trees and serves as
 * a superclass for specific types of trees.
 */
public abstract class Tree implements Serializable {

    private final String _id;
    private final String _nome;
    private double _idade;
    private int _dificuldadeLimpeza;

    public Tree(String id, String nome, int idade, int dificuldadeLimpeza) {
        this._id = id;
        this._nome = nome;
        this._idade = idade;
        this._dificuldadeLimpeza = dificuldadeLimpeza;
    }

    public String get_id() {
        return _id;
    }
    public String get_nome() {
        return _nome;
    }
    public double get_idade() {
        return _idade;
    }
    public int get_dificuldadeLimpeza() {
        return _dificuldadeLimpeza;
    }

    /**
     * Increases the age of the tree by 0.25 (representing the passage of one season).
     */
    public void add_Idade(){
        _idade += .25;
    }

    /**
     * Abstract method to be implemented by subclasses, indicating the type of the tree.
     *
     * @return A string representing the type of the tree ("CADUCA" or "PERENE").
     */
    public abstract String get_tipo();

    /**
     * Abstract method to be implemented by subclasses, representing the biological cycle of the tree based
     * on the current season.
     *
     * @param e The current season in the hotel.
     * @return A string representing the biological state of the tree based on the season.
     */
    public abstract String ciclo_biologico(Estacao e);

    /**
     * This abstract method defines the structure for calculating the seasonal effort required to maintain an object
     * based on the current season. The exact implementation will depend on the specific subclass.
     *
     * @param e The current season.
     * @return An integer representing the effort level for the given season.
     */
    public abstract int esforco_sazonal(Estacao e);

}


