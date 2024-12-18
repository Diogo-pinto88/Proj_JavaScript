package hva.app.employee;

import hva.core.Hotel;
import hva.app.exception.DuplicateEmployeeKeyException;
import hva.core.exception.DuplicateEmployeeIdException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Adds a new employee to this zoo hotel.
 **/
class DoRegisterEmployee extends Command<Hotel> {

  DoRegisterEmployee(Hotel receiver) {
    super(Label.REGISTER_EMPLOYEE, receiver);
    //FIXME add command fields
  }
  
  @Override
  protected void execute() throws CommandException {
    String id = Form.requestString(Prompt.employeeKey());
    String name = Form.requestString(Prompt.employeeName());
    String type = Form.requestString(Prompt.employeeType());
    while(!(type.equals("TRT")) && !(type.equals("VET"))){
      type = Form.requestString(Prompt.employeeType());
    }
    try{
      _receiver.registerEmployee(id, name, type);
    }
    catch(DuplicateEmployeeIdException deie) {
      throw new DuplicateEmployeeKeyException(deie.getKey());
    }
    _display.display();
  }
}
