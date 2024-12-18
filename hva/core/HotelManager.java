package hva.core;


import hva.core.exception.*;
import pt.tecnico.uilib.menus.CommandException;

import java.io.*;


/**
 * Class representing the manager of this application. It manages the current
 * zoo hotel.
 **/

public class HotelManager implements Serializable{

  /**
   * The current zoo hotel
   */
  private Hotel _hotel;
  private String _filename;


  public HotelManager(){
    _hotel = new Hotel(this);
    _filename = null;
  }

  /**
   * Create a New Hotel without any association.
   */
  public void newHotel(){
      _hotel = new Hotel(this);
      _filename = null;
  }

  /**
   * Saves the serialized application's state into the specified file. The current network is
   * associated to this file.
   *
   * @param filename the name of the file.
   * @throws FileNotFoundException           if for some reason the file cannot be created or opened.
   * @throws MissingFileAssociationException if the current network does not have a file.
   * @throws IOException                     if there is some error while serializing the state of the network to disk.
   **/
  public void saveAs(String filename) throws FileNotFoundException, IOException, MissingFileAssociationException {
    try{
      ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));
      _hotel.getManager().SetFilename(filename);
      out.writeObject(_hotel);
      _hotel.SetAsSaved();
    }
    catch (FileNotFoundException fnfe){
      throw new FileNotFoundException("Não foi possível encontrar o arquivo: " + filename);
    }
    catch (IOException e){
      throw new IOException("Erro ao serializar " + e);
    }
  }

  /**
   * Read's the information from the filename and deserializes that information.
   *
   * @param filename name of the file containing the serialized application's state
   *                 to load.
   * @throws UnavailableFileException if the specified file does not exist or there is
   *                                  an error while processing this file.
   **/
  public void load(String filename) throws UnavailableFileException {
    try {
      ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));
      _hotel = (Hotel) in.readObject();
    }
    catch (IOException | ClassNotFoundException e) {
      throw new UnavailableFileException(filename);
    }
  }

  /**
   * Read text input file and initializes the current zoo hotel (which should be empty)
   * with the domain entities represented in the import file.
   *
   * @param filename name of the text input file.
   * @throws ImportFileException if some error happens during the processing of the
   * import file.
   **/
  public void importFile(String filename) throws ImportFileException {
    try {
      _hotel = (new Parser(this)).parseFile(filename);
      _hotel.SetAsChanged();
    } catch (IOException | UnrecognizedEntryException e ) {
      throw new ImportFileException(filename, e);
    }
  }

  public final Hotel getHotel() {
    return _hotel;
  }
  public String getFilename(){
    return _filename;
  }
  public void SetFilename(String filename){
    _filename = filename;
  }
}