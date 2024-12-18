package hva.app.employee;

import hva.app.exception.UnknownHabitatKeyException;
import hva.app.exception.UnknownSpeciesKeyException;
import hva.core.Hotel;
import hva.app.exception.NoResponsibilityException;
import hva.app.exception.UnknownEmployeeKeyException;
import hva.core.exception.UnknownEmployeeIdException;
import hva.core.exception.UnknownHabitatIdException;
import hva.core.exception.UnknownSpeciesIdException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

import java.util.List;
//FIXME add more imports if needed

/**
 * Remove a given responsability from a given employee of this zoo hotel.
 **/
class DoRemoveResponsibility extends Command<Hotel> {

  DoRemoveResponsibility(Hotel receiver) {
    super(Label.REMOVE_RESPONSABILITY, receiver);
    //FIXME add command fields
  }
  
  @Override
  protected void execute() throws CommandException {
    String id = Form.requestString(Prompt.employeeKey());
    String responsibility = Form.requestString(Prompt.responsibilityKey());
    try {
      if(!_receiver.checkIfResponsibilityExists(responsibility)){
        throw new NoResponsibilityException(id, responsibility);
      }
      List<String> l = _receiver.getEmployeeById(id).getResponsabilidadesById();
      for(String s : l){
        if(s.equals(responsibility)){
          _receiver.removeResponsibility(id, responsibility);
          return;
        }
      }
      throw new NoResponsibilityException(id, responsibility);
    }
    catch (UnknownEmployeeIdException ueie){
      throw new UnknownEmployeeKeyException(ueie.getKey());
    }
  }
}
