package hva.core;

import java.io.Serializable;

/**
 * The Perene class represents an evergreen tree in the veterinary hotel.
 * This class extends the Tree class and implements the Serializable interface,
 * allowing instances of this class to be serialized and deserialized.
 * Evergreen trees have specific biological cycles that vary with the seasons.
 */
public class Perene extends Tree implements Serializable {

    public Perene(String id, String nome, int idade, int dificuldadeLimpeza) {
        super(id, nome, idade, dificuldadeLimpeza); // Calls the constructor of the superclass Tree.
    }

    /**
     * Returns the type of the tree as a string.
     *
     * @return "PERENE".
     */
    public String get_tipo(){
        return "PERENE";
    }

    /**
     * Returns the biological cycle of the tree based on the current season.
     *
     * @param e The current season in the hotel.
     * @return A string representing the current state of the tree based on the season.
     */
    public String ciclo_biologico(Estacao e) {
        if (e.getNome().equals("Outono")) {
            return "COMFOLHAS";
        }
        if (e.getNome().equals("Inverno")) {
            return "LARGARFOLHAS";
        }
        if (e.getNome().equals("Verão")) {
            return "COMFOLHAS";
        }
        if (e.getNome().equals("Primavera")) {
            return "GERARFOLHAS";
        }
        return null;
    }

    /**
     * This method calculates the seasonal effort required to maintain a tree based on the current season.
     * Each season requires a different level of effort for maintenance, with winter requiring the most.
     *
     * @param e The current season.
     * @return An integer representing the effort level for the given season:
     *         - 2 for Inverno,
     *         - 1 for Primavera,
     *         - 1 for Verão,
     *         - 1 for Outono.
     */
    public int esforco_sazonal(Estacao e){
        int valor = 0;
        if(e.getNome().equals("Inverno"))
            valor = 2;
        if(e.getNome().equals("Primavera"))
            valor = 1;
        if(e.getNome().equals("Verão"))
            valor = 1;
        if(e.getNome().equals("Outono"))
            valor = 1;
        return valor;
    }
}
