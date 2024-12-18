package hva.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * The Veterinarian class represents a veterinarian employee in the veterinary hotel.
 * It extends the Employee class and implements the Serializable interface to allow instances
 * to be serialized and deserialized, preserving their state and information about the species they can treat.
 */
public class Veterinarian extends Employee implements Serializable {

    public Veterinarian(String id, String nome) {
        super(id, nome);
    }
    /**
     * Gets the type of the employee as a string.
     *
     * @return "VET" indicating that this employee is a veterinarian.
     */
    public String get_tipo(){
        return "VET";
    }
}
