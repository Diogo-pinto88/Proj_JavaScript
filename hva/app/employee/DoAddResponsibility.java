package hva.app.employee;

import hva.app.exception.UnknownHabitatKeyException;
import hva.app.exception.UnknownSpeciesKeyException;
import hva.core.Hotel;
import hva.app.exception.UnknownEmployeeKeyException;
import hva.app.exception.NoResponsibilityException;
import hva.core.exception.UnknownEmployeeIdException;
import hva.core.exception.UnknownHabitatIdException;
import hva.core.exception.UnknownSpeciesIdException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

import java.util.List;

/**
 * Add a new responsability to an employee: species to veterinarians and 
 * habitats to zookeepers.
 **/
class DoAddResponsibility extends Command<Hotel> {

  DoAddResponsibility(Hotel receiver) {
    super(Label.ADD_RESPONSABILITY, receiver);
  }
  
  @Override
  protected void execute() throws CommandException {
    String id = Form.requestString(Prompt.employeeKey());
    String responsibility = Form.requestString(Prompt.responsibilityKey());
    try {
      if (!_receiver.checkIdEmployeeExists(id))
        throw new UnknownEmployeeKeyException(id);
      if (!_receiver.checkIfResponsibilityExists(responsibility)) {
        throw new NoResponsibilityException(id, responsibility);
      }
      List<String> l = _receiver.getEmployeeById(id).getResponsabilidadesById();
      for (String s : l) {
        if (s.equals(responsibility))
          return;
      }
      _receiver.addResponsibility(id, responsibility);
    }
    catch (UnknownEmployeeIdException ueie){
      throw new UnknownEmployeeKeyException(ueie.getKey());
    }
    _display.display();
  }
}
