package hva.app.animal;

import hva.core.Animal;
import hva.core.Habitat;
import hva.core.Hotel;
import hva.app.habitat.Prompt;
import hva.app.exception.UnknownAnimalKeyException;
import hva.app.exception.UnknownHabitatKeyException;
import hva.core.exception.UnknownAnimalIdException;
import hva.core.exception.UnknownHabitatIdException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;


//FIXME add more imports if needed

/**
 * Transfers a given animal to a new habitat of this zoo hotel.
 */
class DoTransferToHabitat extends Command<Hotel> {

  DoTransferToHabitat(Hotel hotel) {
    super(Label.TRANSFER_ANIMAL_TO_HABITAT, hotel);
    //FIXME add command fields
  }
  
  @Override
  protected final void execute() throws CommandException {
    String idAnimal = Form.requestString(hva.app.animal.Prompt.animalKey());
    String idHabitat = Form.requestString(Prompt.habitatKey());
    try{
      Habitat h = _receiver.getHabitatById(idHabitat);
      Animal a = _receiver.getAnimalById(idAnimal);
      a.get_habitat().removeAnimal(a);
      h.addAnimal(a);
      a.changeHabitat(h);
    }
    catch (UnknownAnimalIdException uaie){
      throw new UnknownAnimalKeyException(uaie.getKey());
    }
    catch (UnknownHabitatIdException uhie){
      throw new UnknownHabitatKeyException(uhie.getKey());
    }
  }
}
