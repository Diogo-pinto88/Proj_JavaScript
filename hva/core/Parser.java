package hva.core;

import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

import hva.core.exception.*;

/**
 * The Parser class is responsible for reading and parsing data from a file to populate the hotel with animals.
 * It validates the entries and handles errors through exceptions, ensuring that only valid data is processed
 * and added to the system.
 */

public class Parser {
    private final Hotel _hotel;

    Parser(HotelManager h) {
        _hotel = new Hotel(h);
    }

    public Hotel parseFile(String filename) throws IOException, UnrecognizedEntryException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;

            while ((line = reader.readLine()) != null)
                parseLine(line);
        }
        return _hotel;
    }

    private void parseLine(String line) throws UnrecognizedEntryException {
        String[] components = line.split("\\|");
        switch(components[0]) {
            case "ESPÉCIE" -> parseSpecies(components);
            case "ANIMAL" -> parseAnimal(components);
            case "ÁRVORE" -> parseTree(components, line);
            case "HABITAT" -> parseHabitat(components, line);
            case "TRATADOR" -> parseEmployee(components, "TRT");
            case "VETERINÁRIO" -> parseEmployee(components, "VET");
            case "VACINA" -> parseVaccine(components, line);
            default -> throw new UnrecognizedEntryException ("tipo de entrada inválido: " + components[0]);
        }
    }

    // Parse a line with format ANIMAL|id|nome|idEspécie|idHabitat
    private void parseAnimal(String[] components) throws UnrecognizedEntryException {
        try {
            String id = components[1];
            String name = components[2];
            String habitatId = components[4];
            String speciesId = components[3];

            _hotel.registerAnimal(id, name, habitatId, speciesId);
        } catch ( DuplicateAnimalIdException | UnknownHabitatIdException | UnknownSpeciesIdException e ) {
            throw new UnrecognizedEntryException("Invalid entry: " + e.getKey());
        }
    }

    // Parse a line with format ESPÉCIE|id|nome
    private void parseSpecies(String[] components) throws UnrecognizedEntryException {
        try {
            String id = components[1];
            String name = components[2];

            _hotel.registerSpecies(id, name);
        } catch (DuplicateSpecieIdException e) {
            throw new UnrecognizedEntryException("Invalid entry: " + e.getKey());
        }
    }

    // Parse a line with format TRATADOR|id|nome|idHabitat1,...,idHabitatN or
    // VETERINÁRIO|id|nome|idEspécie1,...,idEspécieN
    private void parseEmployee(String[] components, String empType) throws UnrecognizedEntryException {
        try {
            String id = components[1];
            String name = components[2];

            _hotel.registerEmployee(id, name, empType);

            if (components.length == 4) {
                for(String responsibility : components[3].split(","))
                    _hotel.addResponsibility(components[1], responsibility);
            }
        } catch (UnknownEmployeeIdException | DuplicateEmployeeIdException e) {
            throw new UnrecognizedEntryException("Invalid entry: " + e.getKey());
        }
    }

    // Parse a line with format VACINA|id|nome|idEspécie1,...,idEspécieN
    private void parseVaccine(String[] components, String empType) throws UnrecognizedEntryException {
        try {
            String id = components[1];
            String name = components[2];
            String[] speciesIds = components.length == 4 ? components[3].split(",") : new String[0];
            _hotel.registerVaccine(id, name, speciesIds);
        } catch (UnknownSpeciesIdException | DuplicateVaccineIdException e) {
            throw new UnrecognizedEntryException("Invalid entry: " + e.getKey());
        }
    }

    // Parse a line with format ÁRVORE|id|nome|idade|dificuldade|tipo
    private void parseTree(String[] components, String line) throws UnrecognizedEntryException {
        try {
            String id = components[1];
            String name = components[2];
            int age = Integer.parseInt(components[3]);
            int diff = Integer.parseInt(components[4]);
            String type = components[5];

            _hotel.createTree(id, name, type, age, diff);
        } catch (DuplicateTreeIdException e) {
            throw new UnrecognizedEntryException("Invalid entry: " + line);
        }
    }

    // Parse a line with format HABITAT|id|nome|área|idÁrvore1,...,idÁrvoreN
    private void parseHabitat(String[] components, String line) throws UnrecognizedEntryException {
        try {
            String id = components[1];
            String name = components[2];
            int area = Integer.parseInt(components[3]);

            Habitat hab = _hotel.registerHabitat(id, name, area);

            if (components.length == 5) {
                String[] listOfTree = components[4].split(",");
                for (String treeKey : listOfTree) {
                    hab.addTree(_hotel.getTreeById(treeKey));
                }
                // adicionar a árvore com id treeKey ao habitat referenciado por hab
            }
        } catch ( DuplicateHabitatIdException | UnknownTreeIdException e) {
            throw new UnrecognizedEntryException("Invalid entry: " + e.getKey());
        }
    }
}
