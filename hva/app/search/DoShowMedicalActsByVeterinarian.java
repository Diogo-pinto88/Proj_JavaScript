package hva.app.search;

import hva.app.employee.Prompt;
import hva.app.exception.UnknownEmployeeKeyException;
import hva.core.Employee;
import hva.core.Hotel;
import hva.app.exception.UnknownVeterinarianKeyException;
import hva.core.Register;
import hva.core.Veterinarian;
import hva.core.exception.UnknownEmployeeIdException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

import java.util.ArrayList;
import java.util.List;

//FIXME add more imports if needed

/**
 * Show all medical acts of a given veterinarian.
 **/
class DoShowMedicalActsByVeterinarian extends Command<Hotel> {

  DoShowMedicalActsByVeterinarian(Hotel receiver) {
    super(Label.MEDICAL_ACTS_BY_VET, receiver);
    //FIXME add command fields
  }
  
  @Override
  protected void execute() throws CommandException {
    String id = Form.requestString(Prompt.employeeKey());
    try{
      Employee e = _receiver.getEmployeeById(id);
      if(e.get_tipo().equals("VET")){
        for(Register r : _receiver.get_registos(/*(Veterinarian) e*/)){
          _display.addLine("REGISTO-VACINA|" + r.get_vacina().get_id() + "|" + r.get_veterinario().get_id() +
                  "|" + r.get_animal().get_specie().get_id());
        }
      }
      else {
        throw new UnknownVeterinarianKeyException(id);
      }
    }
    catch(UnknownEmployeeIdException ueie){
      throw new UnknownVeterinarianKeyException(ueie.getKey());
    }
    _display.display();
  }
}
