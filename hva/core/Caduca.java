package hva.core;

import java.io.Serializable;

/**
 * The Caduca class represents a deciduous tree in the veterinary hotel.
 * This class extends the Tree class and implements the Serializable interface,
 * allowing instances of this class to be serialized and deserialized.
 * Deciduous trees have specific biological cycles depending on the current season.
 */
public class Caduca extends Tree implements Serializable {

    public Caduca(String id, String nome, int idade, int dificuldadeLimpeza) {
        super(id, nome, idade, dificuldadeLimpeza);  // Calls the constructor of the superclass Tree.
    }

    /**
     * Returns the type of the tree as a string.
     *
     * @return "CADUCA".
     */
    public String get_tipo(){
        return "CADUCA";
    }

    /**
     * Returns the biological cycle of the tree based on the current season.
     *
     * @param e The current season in the hotel.
     *
     * @return A string representing the current state of the tree based on the season:
     */

    public String ciclo_biologico(Estacao e) {
        if (e.getNome().equals("Outono")) {
            return "LARGARFOLHAS";
        }
        if (e.getNome().equals("Inverno")) {
            return "SEMFOLHAS";
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
     * Different seasons demand varying levels of effort for tree maintenance.
     *
     * @param e The current season.
     * @return An integer representing the effort level for the given season:
     *         - 0 for Inverno,
     *         - 1 for Primavera,
     *         - 2 for Verão,
     *         - 5 for Outono.
     */
    public int esforco_sazonal(Estacao e){
        int valor = 0;
        if(e.getNome().equals("Inverno"))
            valor = 0;
        if(e.getNome().equals("Primavera"))
            valor = 1;
        if(e.getNome().equals("Verão"))
            valor = 2;
        if(e.getNome().equals("Outono"))
            valor = 5;
        return valor;
    }
}
