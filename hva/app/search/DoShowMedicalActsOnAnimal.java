package hva.app.search;

import hva.app.animal.Prompt;
import hva.core.Animal;
import hva.core.Hotel;
import hva.app.exception.UnknownAnimalKeyException;
import hva.core.Register;
import hva.core.exception.UnknownAnimalIdException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Show all medical acts applied to a given animal.
 **/
class DoShowMedicalActsOnAnimal extends Command<Hotel> {

  DoShowMedicalActsOnAnimal(Hotel receiver) {
    super(Label.MEDICAL_ACTS_ON_ANIMAL, receiver);
    //FIXME add command fields
  }

  @Override
  protected void execute() throws CommandException {
    String id = Form.requestString(Prompt.animalKey());
    try {
      Animal a = _receiver.getAnimalById(id);
      for(Register r : _receiver.get_registos(a)){
        _display.addLine("REGISTO-VACINA|" + r.get_vacina().get_id() + "|" + r.get_veterinario().get_id() +
                "|" + r.get_animal().get_specie().get_id());
      }
    }
    catch(UnknownAnimalIdException uaie){
      throw new UnknownAnimalKeyException(uaie.getKey());
    }
    _display.display();
  }
}
