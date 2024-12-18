package hva.app.animal;

import hva.app.exception.DuplicateAnimalKeyException;
import hva.core.Animal;
import hva.core.Hotel;
import hva.app.exception.UnknownAnimalKeyException;
import hva.core.Specie;
import hva.core.exception.DuplicateSpecieIdException;
import hva.core.exception.UnknownAnimalIdException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;


/**
 * Shows the satisfaction of a given animal.
 */
class DoShowSatisfactionOfAnimal extends Command<Hotel> {

  DoShowSatisfactionOfAnimal(Hotel receiver) {
    super(Label.SHOW_SATISFACTION_OF_ANIMAL, receiver);
  }
  
  @Override
  protected final void execute() throws CommandException{
    String id = Form.requestString(Prompt.animalKey());
    try{
      Animal a = _receiver.getAnimalById(id);
      _display.addLine(Math.round(_receiver.getSatisfacaoAnimal(a)));
    }
    catch(UnknownAnimalIdException uaie){
      throw new UnknownAnimalKeyException(uaie.getKey());
    }
    _display.display();
  }
}
