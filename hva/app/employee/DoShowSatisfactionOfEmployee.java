package hva.app.employee;

import hva.core.Employee;
import hva.core.Hotel;
import hva.app.exception.UnknownEmployeeKeyException;
import hva.core.exception.UnknownEmployeeIdException;
import hva.core.exception.UnknownHabitatIdException;
import hva.core.exception.UnknownSpeciesIdException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Show the satisfaction of a given employee.
 **/
class DoShowSatisfactionOfEmployee extends Command<Hotel> {

  DoShowSatisfactionOfEmployee(Hotel receiver) {
    super(Label.SHOW_SATISFACTION_OF_EMPLOYEE, receiver);
    //FIXME add command fields
  }
  
  @Override
  protected void execute() throws CommandException {
    String id = Form.requestString(Prompt.employeeKey());
    try {
      Employee e = _receiver.getEmployeeById(id);
      if(e.get_tipo().equals("VET"))
        _display.addLine(_receiver.getSatisfacaoVet(e));
      else
        _display.addLine(_receiver.getSatisfacaoTrt(e));
    }
    catch(UnknownEmployeeIdException e){
      throw new UnknownEmployeeKeyException(e.getKey());
    }
    _display.display();
  }
}
