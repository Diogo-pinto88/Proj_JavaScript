package hva.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * The Employee class is an abstract representation of an employee in the veterinary hotel.
 * It implements the Serializable interface to allow instances of employees
 * to be serialized and deserialized, preserving their state.
 * This class provides the basic attributes and methods for managing employees and serves as
 * a superclass for specific types of employees.
 */
public abstract class Employee implements Serializable {
    private final String _id;
    private final String _nome;
    private final List<String> _responsabilidades;

    public Employee(String id, String nome) {
        this._id = id;
        this._nome = nome;
        this._responsabilidades = new ArrayList<>();
    }
    public String get_id() {
        return _id;
    }
    public String get_nome() {
        return _nome;
    }

    /**
     * Abstract method to be implemented by subclasses, indicating the type of the employee.
     *
     * @return A string representing the type of the employee ("VET" or "TRT").
     */
    public abstract String get_tipo();

    /**
     * This method returns an unmodifiable list of responsibilities associated with an employee.
     * Responsibilities can include habitats or species that the employee is responsible for.
     *
     * @return An unmodifiable list of the employee's responsibilities (by their IDs).
     */
    public List<String> getResponsabilidadesById(){
        return Collections.unmodifiableList(_responsabilidades);
    }

    /**
     * This method adds a new responsibility to the employee's list of responsibilities.
     * A responsibility could be associated with a habitat, species, or other tasks assigned to the employee.
     *
     * @param responsibility The responsibility (by ID) to be added to the employee's list.
     */
    public void addResponsability(String responsibility){
        this._responsabilidades.add(responsibility);
    }

    /**
     * This method removes a specific responsibility from the employee's list of responsibilities.
     * The responsibility is identified by its ID and, if present, will be removed from the list.
     *
     * @param responsibility The responsibility (by ID) to be removed from the employee's list.
     */
    public void removeResponsability(String responsibility){
        this._responsabilidades.remove(responsibility);
    }

}
