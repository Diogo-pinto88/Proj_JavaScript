package hva.app.animal;

import hva.app.exception.*;
import hva.core.Hotel;
import hva.core.Specie;
import hva.core.exception.*;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;



/**
 * Register a new animal in this zoo hotel.
 */
class DoRegisterAnimal extends Command<Hotel> {


  DoRegisterAnimal(Hotel receiver) {
    super(Label.REGISTER_ANIMAL, receiver);
  }
  
  @Override
  protected final void execute() throws CommandException {
    String id = Form.requestString(Prompt.animalKey());
    String name = Form.requestString(Prompt.animalName());
    String specieId = Form.requestString(Prompt.speciesKey());
    String habitatId = Form.requestString(hva.app.habitat.Prompt.habitatKey());
    try {
      if (!_receiver.checkIdSpecieExists(specieId)) {
        String specieName = Form.requestString(Prompt.speciesName());
        _receiver.registerSpecies(specieId, specieName);
      }
      _receiver.registerAnimal(id, name, habitatId, specieId);
    }
    catch (DuplicateSpecieIdException dsie){
      _display.addLine("A especie já existe.");
    }
    catch(DuplicateAnimalIdException daie){
      throw new DuplicateAnimalKeyException(daie.getKey());
    }
    catch(UnknownHabitatIdException uhie){
      throw new UnknownHabitatKeyException(uhie.getKey());
    }
    catch(UnknownSpeciesIdException usie){
      throw new UnknownSpeciesKeyException(usie.getKey());
    }
    _display.display();
  }
}

