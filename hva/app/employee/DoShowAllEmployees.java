package hva.app.employee;

import hva.core.Employee;
import hva.core.Hotel;
import pt.tecnico.uilib.menus.Command;

import java.util.ArrayList;
import java.util.List;


/**
 * Show all employees of this zoo hotel.
 **/
class DoShowAllEmployees extends Command<Hotel> {

  DoShowAllEmployees(Hotel receiver) {
    super(Label.SHOW_ALL_EMPLOYEES, receiver);
  }
  
  @Override
  protected void execute(){
    for(Employee e : _receiver.get_funcionarios()){
      _display.add(e.get_tipo() + "|" + e.get_id() + "|" + e.get_nome());
      if(e.getResponsabilidadesById().isEmpty())
        _display.add("\n");
      else{
        _display.add("|");
        boolean first = true;
        for(String s : e.getResponsabilidadesById()){
          if(!first)
            _display.add(",");
          _display.add(s);
          first = false;
        }
        _display.add("\n");
      }
    }
    _display.display();
  }
}
