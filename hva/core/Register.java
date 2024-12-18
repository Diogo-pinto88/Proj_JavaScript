package hva.core;

import hva.core.Animal;
import hva.core.Veterinarian;

import java.io.Serializable;

/**
 * The Register class represents a record of a vaccine application in the veterinary hotel.
 * It implements the Serializable interface to allow instances to be serialized and deserialized,
 * preserving information about the veterinarian who administered the vaccine and the animal that received it.
 */
public class Register implements Serializable {
    Veterinarian _veterinario;
    Animal _animal;
    Vaccine _vacina;
    public Register(Veterinarian veterinario, Animal animal, Vaccine vacina) {
        this._veterinario = veterinario;
        this._animal = animal;
        this._vacina = vacina;
    }
    public Veterinarian get_veterinario() {
        return _veterinario;
    }
    public Animal get_animal() {
        return _animal;
    }

    public Vaccine get_vacina() {
        return _vacina;
    }
    public String Obtemdano(Hotel h){
        return h.converteDano(_vacina, _animal);
    }
}

