package hva.app.habitat;

import hva.core.Hotel;
import hva.app.exception.DuplicateHabitatKeyException;
import hva.core.exception.DuplicateHabitatIdException;
import hva.core.exception.UnknownHabitatIdException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;


/**
 * Add a new habitat to this zoo hotel.
 **/
class DoRegisterHabitat extends Command<Hotel> {

  DoRegisterHabitat(Hotel receiver) {
    super(Label.REGISTER_HABITAT, receiver);
    //FIXME add command fields
  }
  
  @Override
  protected void execute() throws CommandException {
    String id = Form.requestString(Prompt.habitatKey());
    String name = Form.requestString(Prompt.habitatName());
    int area = Form.requestInteger(Prompt.habitatArea());
    if(_receiver.checkIdHabitatExists(id)){
      throw new DuplicateHabitatKeyException(id);
    }
    else{
      try {
        _receiver.registerHabitat(id, name, area);
      }
      catch(DuplicateHabitatIdException e){
        //impossible to happen
      }
      _display.display();
      }
  }
}
