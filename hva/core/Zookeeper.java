package hva.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The Zookeeper class represents a zookeeper employee in the veterinary hotel.
 * It extends the Employee class and implements the Serializable interface to allow instances
 * to be serialized and deserialized, preserving their state and information about the habitats they manage.
 */
public class Zookeeper extends Employee implements Serializable {

    public Zookeeper(String id, String nome) {
        super(id, nome);
    }

    /**
     * Gets the type of the employee as a string.
     *
     * @return "TRT" indicating that this employee is a zookeeper.
     */
    public String get_tipo(){
        return "TRT";
    }
}
