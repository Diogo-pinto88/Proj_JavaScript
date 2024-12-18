package hva.app.habitat;

import hva.core.Habitat;
import hva.core.Hotel;
import hva.app.exception.UnknownHabitatKeyException;
import hva.core.exception.UnknownHabitatIdException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Change the area of a given habitat.
 **/
class DoChangeHabitatArea extends Command<Hotel> {

  DoChangeHabitatArea(Hotel receiver) {
    super(Label.CHANGE_HABITAT_AREA, receiver);
    //FIXME add command fields
  }
  
  @Override
  protected void execute() throws CommandException {
    String id = Form.requestString(Prompt.habitatKey());
    int area = Form.requestInteger(Prompt.habitatArea());
    try{
      Habitat h = _receiver.getHabitatById(id);
      h.set_area(area);
    }
    catch(UnknownHabitatIdException e){
      throw new UnknownHabitatKeyException(e.getKey());
    }
    _display.display();
  }
}
